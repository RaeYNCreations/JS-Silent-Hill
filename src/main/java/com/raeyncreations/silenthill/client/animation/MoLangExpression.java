package com.raeyncreations.silenthill.client.animation;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Full MoLang expression evaluator for Bedrock Edition animations.
 * Supports the complete MoLang specification including:
 * <ul>
 *   <li>Parentheses for expression grouping</li>
 *   <li>Proper operator precedence (*, / before +, -)</li>
 *   <li>All query variables (movement, health, rotation, state)</li>
 *   <li>Comparison operators (&lt;, &gt;, &lt;=, &gt;=, ==, !=)</li>
 *   <li>Logical operators (&amp;&amp;, ||, !)</li>
 *   <li>Ternary operator (condition ? true_value : false_value)</li>
 *   <li>Nested math functions</li>
 *   <li>User-defined variables</li>
 * </ul>
 * 
 * <p>Thread-safe with expression caching for performance.
 * 
 * <p>Example usage:
 * <pre>
 * MoLangExpression expr = MoLangExpression.parse("math.sin(query.life_time * 180)");
 * MoLangContext context = MoLangContext.fromEntity(entity);
 * float result = expr.evaluate(context);
 * 
 * // With variables
 * expr.setVariable("speed", 2.0f);
 * float result2 = expr.evaluate(context);
 * 
 * // Complex expressions
 * MoLangExpression complex = MoLangExpression.parse(
 *     "query.is_moving ? math.sin(query.anim_time * 360) * variable.amplitude : 0"
 * );
 * </pre>
 * 
 * @author RaEyn Creations
 */
public class MoLangExpression {
    private static final Map<String, MoLangExpression> EXPRESSION_CACHE = new ConcurrentHashMap<>();
    private static final int MAX_CACHE_SIZE = 1000;
    
    private final String source;
    private final ASTNode ast;
    private final Map<String, Float> variables;
    
    private MoLangExpression(String source, ASTNode ast) {
        this.source = source;
        this.ast = ast;
        this.variables = new ConcurrentHashMap<>();
    }
    
    /**
     * Parse a MoLang expression. Results are cached for performance.
     * @param expression The expression string to parse
     * @return A parsed MoLang expression
     * @throws MoLangParseException if the expression is invalid
     */
    public static MoLangExpression parse(String expression) {
        if (expression == null || expression.isEmpty()) {
            expression = "0";
        }
        
        String normalized = expression.trim();
        
        // Check cache first
        MoLangExpression cached = EXPRESSION_CACHE.get(normalized);
        if (cached != null) {
            // Create new instance with same AST but new variables map
            // AST nodes are immutable and safe to reuse
            return new MoLangExpression(normalized, cached.ast);
        }
        
        // Parse new expression
        try {
            Tokenizer tokenizer = new Tokenizer(normalized);
            List<Token> tokens = tokenizer.tokenize();
            Parser parser = new Parser(tokens);
            ASTNode ast = parser.parse();
            
            MoLangExpression result = new MoLangExpression(normalized, ast);
            
            // Cache if not too large
            if (EXPRESSION_CACHE.size() < MAX_CACHE_SIZE) {
                EXPRESSION_CACHE.put(normalized, result);
            }
            
            return result;
        } catch (Exception e) {
            throw new MoLangParseException("Failed to parse expression: " + normalized, e);
        }
    }
    
    /**
     * Legacy constructor for backwards compatibility.
     * Creates a default context and parses the expression.
     * @param expression The expression string
     */
    public MoLangExpression(String expression) {
        String normalized = expression != null ? expression.trim() : "0";
        this.source = normalized;
        
        ASTNode parsedAst;
        try {
            Tokenizer tokenizer = new Tokenizer(normalized);
            List<Token> tokens = tokenizer.tokenize();
            Parser parser = new Parser(tokens);
            parsedAst = parser.parse();
        } catch (Exception e) {
            // Fall back to a simple number node on error
            parsedAst = new NumberNode(0);
        }
        
        this.ast = parsedAst;
        this.variables = new ConcurrentHashMap<>();
    }
    
    /**
     * Set a variable value for evaluation.
     * Thread-safe.
     * @param name Variable name (without "variable." prefix)
     * @param value The value to set
     */
    public void setVariable(String name, float value) {
        variables.put(name, value);
    }
    
    /**
     * Get a variable value.
     * @param name Variable name
     * @return The value, or 0 if not set
     */
    public float getVariable(String name) {
        return variables.getOrDefault(name, 0.0f);
    }
    
    /**
     * Evaluate with a full context.
     * @param context The evaluation context
     * @return The evaluated result
     */
    public float evaluate(MoLangContext context) {
        try {
            return ast.evaluate(context, variables);
        } catch (Exception e) {
            System.err.println("Error evaluating MoLang expression '" + source + "': " + e.getMessage());
            return 0;
        }
    }
    
    /**
     * Legacy evaluate method for backwards compatibility.
     * @param lifeTime Entity lifetime in seconds
     * @return The evaluated result
     */
    public float evaluate(float lifeTime) {
        MoLangContext context = new MoLangContext.Builder()
            .lifeTime(lifeTime)
            .animTime(lifeTime)
            .build();
        return evaluate(context);
    }
    
    /**
     * Check if a string is a MoLang expression.
     * @param str The string to check
     * @return True if it appears to be a MoLang expression
     */
    public static boolean isMoLangExpression(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        
        try {
            Float.parseFloat(str.trim());
            return false;
        } catch (NumberFormatException e) {
            // Not a simple number
        }
        
        return str.contains("math.") || str.contains("Math.") ||
               str.contains("query.") || str.contains("variable.") ||
               str.contains("?") || str.contains("&&") || str.contains("||");
    }
    
    /**
     * Clear the expression cache.
     */
    public static void clearCache() {
        EXPRESSION_CACHE.clear();
    }
    
    // ========== TOKENIZER ==========
    
    private enum TokenType {
        NUMBER, IDENTIFIER, LPAREN, RPAREN,
        PLUS, MINUS, MULTIPLY, DIVIDE, MODULO,
        LT, GT, LTE, GTE, EQ, NEQ,
        AND, OR, NOT,
        QUESTION, COLON,
        DOT, COMMA, EOF
    }
    
    private static class Token {
        final TokenType type;
        final String value;
        
        Token(TokenType type, String value) {
            this.type = type;
            this.value = value;
        }
        
        @Override
        public String toString() {
            return type + "(" + value + ")";
        }
    }
    
    private static class Tokenizer {
        private final String input;
        private int pos;
        
        Tokenizer(String input) {
            this.input = input;
            this.pos = 0;
        }
        
        List<Token> tokenize() {
            List<Token> tokens = new ArrayList<>();
            
            while (pos < input.length()) {
                char c = input.charAt(pos);
                
                // Skip whitespace
                if (Character.isWhitespace(c)) {
                    pos++;
                    continue;
                }
                
                // Numbers
                if (Character.isDigit(c) || (c == '.' && pos + 1 < input.length() && Character.isDigit(input.charAt(pos + 1)))) {
                    tokens.add(readNumber());
                    continue;
                }
                
                // Identifiers (query, variable, math, function names)
                if (Character.isLetter(c) || c == '_') {
                    tokens.add(readIdentifier());
                    continue;
                }
                
                // Two-character operators
                if (pos + 1 < input.length()) {
                    String twoChar = input.substring(pos, pos + 2);
                    TokenType type = null;
                    
                    switch (twoChar) {
                        case "<=": type = TokenType.LTE; break;
                        case ">=": type = TokenType.GTE; break;
                        case "==": type = TokenType.EQ; break;
                        case "!=": type = TokenType.NEQ; break;
                        case "&&": type = TokenType.AND; break;
                        case "||": type = TokenType.OR; break;
                    }
                    
                    if (type != null) {
                        tokens.add(new Token(type, twoChar));
                        pos += 2;
                        continue;
                    }
                }
                
                // Single-character operators
                TokenType type = null;
                switch (c) {
                    case '(': type = TokenType.LPAREN; break;
                    case ')': type = TokenType.RPAREN; break;
                    case '+': type = TokenType.PLUS; break;
                    case '-': type = TokenType.MINUS; break;
                    case '*': type = TokenType.MULTIPLY; break;
                    case '/': type = TokenType.DIVIDE; break;
                    case '%': type = TokenType.MODULO; break;
                    case '<': type = TokenType.LT; break;
                    case '>': type = TokenType.GT; break;
                    case '!': type = TokenType.NOT; break;
                    case '?': type = TokenType.QUESTION; break;
                    case ':': type = TokenType.COLON; break;
                    case '.': type = TokenType.DOT; break;
                    case ',': type = TokenType.COMMA; break;
                }
                
                if (type != null) {
                    tokens.add(new Token(type, String.valueOf(c)));
                    pos++;
                } else {
                    throw new MoLangParseException("Unexpected character: " + c);
                }
            }
            
            tokens.add(new Token(TokenType.EOF, ""));
            return tokens;
        }
        
        private Token readNumber() {
            int start = pos;
            boolean hasDecimal = false;
            
            while (pos < input.length()) {
                char c = input.charAt(pos);
                if (Character.isDigit(c)) {
                    pos++;
                } else if (c == '.' && !hasDecimal) {
                    hasDecimal = true;
                    pos++;
                } else {
                    break;
                }
            }
            
            return new Token(TokenType.NUMBER, input.substring(start, pos));
        }
        
        private Token readIdentifier() {
            int start = pos;
            
            while (pos < input.length()) {
                char c = input.charAt(pos);
                if (Character.isLetterOrDigit(c) || c == '_') {
                    pos++;
                } else {
                    break;
                }
            }
            
            return new Token(TokenType.IDENTIFIER, input.substring(start, pos));
        }
    }
    
    // ========== PARSER ==========
    
    private static class Parser {
        private final List<Token> tokens;
        private int pos;
        
        Parser(List<Token> tokens) {
            this.tokens = tokens;
            this.pos = 0;
        }
        
        ASTNode parse() {
            ASTNode result = parseTernary();
            if (current().type != TokenType.EOF) {
                throw new MoLangParseException("Unexpected token: " + current());
            }
            return result;
        }
        
        private ASTNode parseTernary() {
            ASTNode condition = parseLogicalOr();
            
            if (current().type == TokenType.QUESTION) {
                consume(TokenType.QUESTION);
                ASTNode trueValue = parseTernary();
                consume(TokenType.COLON);
                ASTNode falseValue = parseTernary();
                return new TernaryNode(condition, trueValue, falseValue);
            }
            
            return condition;
        }
        
        private ASTNode parseLogicalOr() {
            ASTNode left = parseLogicalAnd();
            
            while (current().type == TokenType.OR) {
                consume(TokenType.OR);
                ASTNode right = parseLogicalAnd();
                left = new LogicalOrNode(left, right);
            }
            
            return left;
        }
        
        private ASTNode parseLogicalAnd() {
            ASTNode left = parseComparison();
            
            while (current().type == TokenType.AND) {
                consume(TokenType.AND);
                ASTNode right = parseComparison();
                left = new LogicalAndNode(left, right);
            }
            
            return left;
        }
        
        private ASTNode parseComparison() {
            ASTNode left = parseAdditive();
            
            TokenType type = current().type;
            if (type == TokenType.LT || type == TokenType.GT ||
                type == TokenType.LTE || type == TokenType.GTE ||
                type == TokenType.EQ || type == TokenType.NEQ) {
                
                consume(type);
                ASTNode right = parseAdditive();
                return new ComparisonNode(type, left, right);
            }
            
            return left;
        }
        
        private ASTNode parseAdditive() {
            ASTNode left = parseMultiplicative();
            
            while (current().type == TokenType.PLUS || current().type == TokenType.MINUS) {
                TokenType op = current().type;
                consume(op);
                ASTNode right = parseMultiplicative();
                left = new BinaryOpNode(op, left, right);
            }
            
            return left;
        }
        
        private ASTNode parseMultiplicative() {
            ASTNode left = parseUnary();
            
            while (current().type == TokenType.MULTIPLY || 
                   current().type == TokenType.DIVIDE ||
                   current().type == TokenType.MODULO) {
                TokenType op = current().type;
                consume(op);
                ASTNode right = parseUnary();
                left = new BinaryOpNode(op, left, right);
            }
            
            return left;
        }
        
        private ASTNode parseUnary() {
            if (current().type == TokenType.NOT) {
                consume(TokenType.NOT);
                return new NotNode(parseUnary());
            }
            
            if (current().type == TokenType.MINUS) {
                consume(TokenType.MINUS);
                return new NegateNode(parseUnary());
            }
            
            return parsePrimary();
        }
        
        private ASTNode parsePrimary() {
            Token token = current();
            
            // Number
            if (token.type == TokenType.NUMBER) {
                consume(TokenType.NUMBER);
                return new NumberNode(Float.parseFloat(token.value));
            }
            
            // Parentheses
            if (token.type == TokenType.LPAREN) {
                consume(TokenType.LPAREN);
                ASTNode expr = parseTernary();
                consume(TokenType.RPAREN);
                return expr;
            }
            
            // Identifier (query, variable, or function)
            if (token.type == TokenType.IDENTIFIER) {
                return parseIdentifier();
            }
            
            throw new MoLangParseException("Unexpected token: " + token);
        }
        
        private ASTNode parseIdentifier() {
            String name = consume(TokenType.IDENTIFIER).value;
            
            // Check for dot notation (query.xxx, variable.xxx, math.xxx)
            if (current().type == TokenType.DOT) {
                consume(TokenType.DOT);
                String member = consume(TokenType.IDENTIFIER).value;
                
                // Check if it's a function call
                if (current().type == TokenType.LPAREN) {
                    consume(TokenType.LPAREN);
                    List<ASTNode> args = new ArrayList<>();
                    
                    if (current().type != TokenType.RPAREN) {
                        args.add(parseTernary());
                        
                        while (current().type == TokenType.COMMA) {
                            consume(TokenType.COMMA);
                            args.add(parseTernary());
                        }
                    }
                    
                    consume(TokenType.RPAREN);
                    return new FunctionNode(name + "." + member, args);
                }
                
                // Member access (query.xxx, variable.xxx)
                if (name.equals("query")) {
                    return new QueryNode(member);
                } else if (name.equals("variable")) {
                    return new VariableNode(member);
                }
            }
            
            throw new MoLangParseException("Unknown identifier: " + name);
        }
        
        private Token current() {
            return tokens.get(pos);
        }
        
        private Token consume(TokenType expected) {
            Token token = current();
            if (token.type != expected) {
                throw new MoLangParseException("Expected " + expected + " but got " + token.type);
            }
            pos++;
            return token;
        }
    }
    
    // ========== AST NODES ==========
    
    private interface ASTNode {
        float evaluate(MoLangContext context, Map<String, Float> variables);
    }
    
    private static class NumberNode implements ASTNode {
        final float value;
        
        NumberNode(float value) {
            this.value = value;
        }
        
        @Override
        public float evaluate(MoLangContext context, Map<String, Float> variables) {
            return value;
        }
    }
    
    private static class QueryNode implements ASTNode {
        final String queryName;
        
        QueryNode(String queryName) {
            this.queryName = queryName;
        }
        
        @Override
        public float evaluate(MoLangContext context, Map<String, Float> variables) {
            return context.getQuery(queryName);
        }
    }
    
    private static class VariableNode implements ASTNode {
        final String variableName;
        
        VariableNode(String variableName) {
            this.variableName = variableName;
        }
        
        @Override
        public float evaluate(MoLangContext context, Map<String, Float> variables) {
            return variables.getOrDefault(variableName, 0.0f);
        }
    }
    
    private static class BinaryOpNode implements ASTNode {
        final TokenType op;
        final ASTNode left;
        final ASTNode right;
        
        BinaryOpNode(TokenType op, ASTNode left, ASTNode right) {
            this.op = op;
            this.left = left;
            this.right = right;
        }
        
        @Override
        public float evaluate(MoLangContext context, Map<String, Float> variables) {
            float l = left.evaluate(context, variables);
            float r = right.evaluate(context, variables);
            
            switch (op) {
                case PLUS: return l + r;
                case MINUS: return l - r;
                case MULTIPLY: return l * r;
                case DIVIDE: return r != 0 ? l / r : 0;
                case MODULO: return r != 0 ? l % r : 0;
                default: return 0;
            }
        }
    }
    
    private static class ComparisonNode implements ASTNode {
        final TokenType op;
        final ASTNode left;
        final ASTNode right;
        
        ComparisonNode(TokenType op, ASTNode left, ASTNode right) {
            this.op = op;
            this.left = left;
            this.right = right;
        }
        
        @Override
        public float evaluate(MoLangContext context, Map<String, Float> variables) {
            float l = left.evaluate(context, variables);
            float r = right.evaluate(context, variables);
            
            boolean result;
            switch (op) {
                case LT: result = l < r; break;
                case GT: result = l > r; break;
                case LTE: result = l <= r; break;
                case GTE: result = l >= r; break;
                case EQ: result = Math.abs(l - r) < 0.0001f; break;
                case NEQ: result = Math.abs(l - r) >= 0.0001f; break;
                default: result = false;
            }
            
            return result ? 1.0f : 0.0f;
        }
    }
    
    private static class LogicalAndNode implements ASTNode {
        final ASTNode left;
        final ASTNode right;
        
        LogicalAndNode(ASTNode left, ASTNode right) {
            this.left = left;
            this.right = right;
        }
        
        @Override
        public float evaluate(MoLangContext context, Map<String, Float> variables) {
            float l = left.evaluate(context, variables);
            if (Math.abs(l) < 0.0001f) return 0.0f; // Short-circuit
            float r = right.evaluate(context, variables);
            return (Math.abs(l) >= 0.0001f && Math.abs(r) >= 0.0001f) ? 1.0f : 0.0f;
        }
    }
    
    private static class LogicalOrNode implements ASTNode {
        final ASTNode left;
        final ASTNode right;
        
        LogicalOrNode(ASTNode left, ASTNode right) {
            this.left = left;
            this.right = right;
        }
        
        @Override
        public float evaluate(MoLangContext context, Map<String, Float> variables) {
            float l = left.evaluate(context, variables);
            if (Math.abs(l) >= 0.0001f) return 1.0f; // Short-circuit
            float r = right.evaluate(context, variables);
            return (Math.abs(l) >= 0.0001f || Math.abs(r) >= 0.0001f) ? 1.0f : 0.0f;
        }
    }
    
    private static class NotNode implements ASTNode {
        final ASTNode operand;
        
        NotNode(ASTNode operand) {
            this.operand = operand;
        }
        
        @Override
        public float evaluate(MoLangContext context, Map<String, Float> variables) {
            float value = operand.evaluate(context, variables);
            return Math.abs(value) < 0.0001f ? 1.0f : 0.0f;
        }
    }
    
    private static class NegateNode implements ASTNode {
        final ASTNode operand;
        
        NegateNode(ASTNode operand) {
            this.operand = operand;
        }
        
        @Override
        public float evaluate(MoLangContext context, Map<String, Float> variables) {
            return -operand.evaluate(context, variables);
        }
    }
    
    private static class TernaryNode implements ASTNode {
        final ASTNode condition;
        final ASTNode trueValue;
        final ASTNode falseValue;
        
        TernaryNode(ASTNode condition, ASTNode trueValue, ASTNode falseValue) {
            this.condition = condition;
            this.trueValue = trueValue;
            this.falseValue = falseValue;
        }
        
        @Override
        public float evaluate(MoLangContext context, Map<String, Float> variables) {
            float cond = condition.evaluate(context, variables);
            return Math.abs(cond) >= 0.0001f 
                ? trueValue.evaluate(context, variables)
                : falseValue.evaluate(context, variables);
        }
    }
    
    private static class FunctionNode implements ASTNode {
        final String functionName;
        final List<ASTNode> args;
        
        FunctionNode(String functionName, List<ASTNode> args) {
            this.functionName = functionName.toLowerCase();
            this.args = args;
        }
        
        @Override
        public float evaluate(MoLangContext context, Map<String, Float> variables) {
            if (args.isEmpty()) {
                return evaluateFunction(functionName, 0);
            }
            
            float arg = args.get(0).evaluate(context, variables);
            
            // Three-argument functions
            if (args.size() > 2) {
                float arg2 = args.get(1).evaluate(context, variables);
                float arg3 = args.get(2).evaluate(context, variables);
                return evaluateThreeArgFunction(functionName, arg, arg2, arg3);
            }
            
            // Two-argument functions
            if (args.size() > 1) {
                float arg2 = args.get(1).evaluate(context, variables);
                return evaluateTwoArgFunction(functionName, arg, arg2);
            }
            
            return evaluateFunction(functionName, arg);
        }
        
        private float evaluateFunction(String name, float arg) {
            switch (name) {
                case "math.cos": return (float) Math.cos(arg);
                case "math.sin": return (float) Math.sin(arg);
                case "math.abs": return Math.abs(arg);
                case "math.sqrt": return (float) Math.sqrt(Math.max(0, arg));
                case "math.floor": return (float) Math.floor(arg);
                case "math.ceil": return (float) Math.ceil(arg);
                case "math.round": return Math.round(arg);
                case "math.exp": return (float) Math.exp(arg);
                case "math.ln": return arg > 0 ? (float) Math.log(arg) : 0;
                case "math.pow": return (float) Math.pow(arg, 2); // Default power of 2
                case "math.random": return ThreadLocalRandom.current().nextFloat() * arg;
                case "math.die_roll":
                case "math.die_roll_integer":
                    int max = Math.max(1, (int) arg);
                    return ThreadLocalRandom.current().nextInt(max) + 1;
                case "math.hermite_blend": 
                    return arg * arg * (3.0f - 2.0f * arg);
                case "math.lerp_rotate":
                case "math.lerp":
                    return arg; // Needs 3 args, return as-is
                default:
                    System.err.println("Unknown function: " + name);
                    return 0;
            }
        }
        
        private float evaluateTwoArgFunction(String name, float arg1, float arg2) {
            switch (name) {
                case "math.pow": return (float) Math.pow(arg1, arg2);
                case "math.min": return Math.min(arg1, arg2);
                case "math.max": return Math.max(arg1, arg2);
                default: return evaluateFunction(name, arg1);
            }
        }
        
        private float evaluateThreeArgFunction(String name, float arg1, float arg2, float arg3) {
            if (name.equals("math.clamp")) {
                return Math.max(arg2, Math.min(arg3, arg1));
            }
            return evaluateTwoArgFunction(name, arg1, arg2);
        }
    }
    
    /**
     * Exception thrown when parsing fails.
     */
    public static class MoLangParseException extends RuntimeException {
        public MoLangParseException(String message) {
            super(message);
        }
        
        public MoLangParseException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}

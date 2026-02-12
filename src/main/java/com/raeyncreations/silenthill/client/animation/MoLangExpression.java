package com.raeyncreations.silenthill.client.animation;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Basic MoLang expression evaluator for animation values.
 * Supports math operations and query variables commonly used in Bedrock animations.
 * Note: Does not support parentheses or complex operator precedence.
 */
public class MoLangExpression {
    private static final Pattern FUNCTION_PATTERN = Pattern.compile("(Math\\.[a-z_]+|math\\.[a-z_]+)\\(([^)]+)\\)");
    private static final Pattern QUERY_PATTERN = Pattern.compile("query\\.([a-z_]+)");
    private static final Pattern VARIABLE_PATTERN = Pattern.compile("variable\\.([a-z_]+)");
    
    private final String expression;
    private final Map<String, Float> variables;
    
    public MoLangExpression(String expression) {
        this.expression = expression != null ? expression.trim() : "0";
        this.variables = new HashMap<>();
    }
    
    /**
     * Set a variable value for evaluation.
     * @param name Variable name (without "variable." prefix)
     * @param value The value to set
     */
    public void setVariable(String name, float value) {
        variables.put(name, value);
    }
    
    /**
     * Evaluate the expression with the given context.
     * @param lifeTime Entity lifetime in seconds (for query.life_time)
     * @return The evaluated result
     */
    public float evaluate(float lifeTime) {
        try {
            return evaluateExpression(expression, lifeTime);
        } catch (Exception e) {
            // Log error and return 0 as fallback
            System.err.println("Error evaluating MoLang expression '" + expression + "': " + e.getMessage());
            return 0;
        }
    }
    
    private float evaluateExpression(String expr, float lifeTime) {
        expr = expr.trim();
        
        // Handle simple numeric values
        try {
            return Float.parseFloat(expr);
        } catch (NumberFormatException e) {
            // Not a simple number, continue parsing
        }
        
        // Replace query variables
        expr = replaceQueryVariables(expr, lifeTime);
        
        // Replace user variables
        expr = replaceUserVariables(expr);
        
        // Evaluate math functions
        expr = evaluateFunctions(expr, lifeTime);
        
        // Evaluate arithmetic operations
        return evaluateArithmetic(expr);
    }
    
    private String replaceQueryVariables(String expr, float lifeTime) {
        Matcher matcher = QUERY_PATTERN.matcher(expr);
        StringBuffer result = new StringBuffer();
        
        while (matcher.find()) {
            String queryName = matcher.group(1);
            float value = getQueryValue(queryName, lifeTime);
            matcher.appendReplacement(result, String.valueOf(value));
        }
        matcher.appendTail(result);
        
        return result.toString();
    }
    
    private String replaceUserVariables(String expr) {
        Matcher matcher = VARIABLE_PATTERN.matcher(expr);
        StringBuffer result = new StringBuffer();
        
        while (matcher.find()) {
            String varName = matcher.group(1);
            float value = variables.getOrDefault(varName, 0f);
            matcher.appendReplacement(result, String.valueOf(value));
        }
        matcher.appendTail(result);
        
        return result.toString();
    }
    
    private String evaluateFunctions(String expr, float lifeTime) {
        Matcher matcher = FUNCTION_PATTERN.matcher(expr);
        StringBuffer result = new StringBuffer();
        
        while (matcher.find()) {
            String functionName = matcher.group(1).toLowerCase();
            String argument = matcher.group(2);
            
            float argValue = evaluateExpression(argument, lifeTime);
            float functionResult = evaluateFunction(functionName, argValue);
            
            matcher.appendReplacement(result, String.valueOf(functionResult));
        }
        matcher.appendTail(result);
        
        return result.toString();
    }
    
    private float evaluateFunction(String functionName, float arg) {
        switch (functionName) {
            case "math.cos":
                return (float) Math.cos(arg);
            case "math.sin":
                return (float) Math.sin(arg);
            case "math.abs":
                return Math.abs(arg);
            case "math.sqrt":
                return (float) Math.sqrt(arg);
            case "math.floor":
                return (float) Math.floor(arg);
            case "math.ceil":
                return (float) Math.ceil(arg);
            case "math.round":
                return Math.round(arg);
            case "math.random":
                // Returns random value between 0 and arg
                return ThreadLocalRandom.current().nextFloat() * arg;
            default:
                return 0;
        }
    }
    
    private float getQueryValue(String queryName, float lifeTime) {
        switch (queryName) {
            case "life_time":
                return lifeTime;
            case "anim_time":
                return lifeTime;
            default:
                return 0;
        }
    }
    
    private float evaluateArithmetic(String expr) {
        expr = expr.trim();
        
        // Try to parse as a simple number
        try {
            return Float.parseFloat(expr);
        } catch (NumberFormatException e) {
            // Continue with arithmetic evaluation
        }
        
        // Handle addition and subtraction (lowest precedence)
        for (int i = expr.length() - 1; i >= 0; i--) {
            char c = expr.charAt(i);
            // Skip if '-' is at start (negative number)
            if (c == '-' && i == 0) {
                continue;
            }
            if ((c == '+' || c == '-') && i > 0) {
                String left = expr.substring(0, i).trim();
                String right = expr.substring(i + 1).trim();
                
                float leftVal = evaluateArithmetic(left);
                float rightVal = evaluateArithmetic(right);
                
                return c == '+' ? leftVal + rightVal : leftVal - rightVal;
            }
        }
        
        // Handle multiplication and division (higher precedence)
        for (int i = expr.length() - 1; i >= 0; i--) {
            char c = expr.charAt(i);
            if ((c == '*' || c == '/') && i > 0) {
                String left = expr.substring(0, i).trim();
                String right = expr.substring(i + 1).trim();
                
                float leftVal = evaluateArithmetic(left);
                float rightVal = evaluateArithmetic(right);
                
                return c == '*' ? leftVal * rightVal : leftVal / rightVal;
            }
        }
        
        // Handle modulo
        int modIndex = expr.indexOf('%');
        if (modIndex > 0) {
            String left = expr.substring(0, modIndex).trim();
            String right = expr.substring(modIndex + 1).trim();
            
            float leftVal = evaluateArithmetic(left);
            float rightVal = evaluateArithmetic(right);
            
            return leftVal % rightVal;
        }
        
        // If we can't parse it, return 0
        return 0;
    }
    
    /**
     * Check if a string is a MoLang expression (contains functions or variables).
     * @param str The string to check
     * @return True if it appears to be a MoLang expression
     */
    public static boolean isMoLangExpression(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        
        // Check for numeric value
        try {
            Float.parseFloat(str);
            return false; // It's a simple number, not a MoLang expression
        } catch (NumberFormatException e) {
            // Not a simple number, might be MoLang
        }
        
        // Check for MoLang patterns
        return str.contains("Math.") || str.contains("math.") || 
               str.contains("query.") || str.contains("variable.");
    }
}

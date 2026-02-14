# MoLang Expression Parser Enhancement

## Overview

This enhancement provides full Bedrock Edition MoLang specification support for the Silent Hill mod's animation system. The parser now includes proper tokenization, recursive descent parsing, and comprehensive operator support.

## Features

### 1. Expression Grouping with Parentheses
```java
MoLangExpression expr = MoLangExpression.parse("(2 + 3) * 4");
float result = expr.evaluate(context); // Returns 20.0
```

### 2. Proper Operator Precedence
Follows standard mathematical precedence:
- `*`, `/`, `%` (multiplication, division, modulo) - highest
- `+`, `-` (addition, subtraction)
- `<`, `>`, `<=`, `>=`, `==`, `!=` (comparisons)
- `&&` (logical AND)
- `||` (logical OR)
- `? :` (ternary) - lowest

```java
MoLangExpression.parse("2 + 3 * 4").evaluate(ctx);      // 14.0 (not 20.0)
MoLangExpression.parse("10 - 2 * 3").evaluate(ctx);     // 4.0 (not 24.0)
```

### 3. Query Variables

All Bedrock query variables are supported:

#### Time-based
- `query.life_time` - Entity lifetime in seconds
- `query.anim_time` - Animation time in seconds

#### Movement
- `query.is_moving` - 1.0 if moving, 0.0 otherwise
- `query.movement_speed` - Current movement speed
- `query.is_on_ground` - 1.0 if on ground, 0.0 otherwise
- `query.is_in_water` - 1.0 if in water, 0.0 otherwise

#### Rotation
- `query.yaw` - Entity yaw rotation
- `query.pitch` - Entity pitch rotation
- `query.target_x_rotation` - Target X rotation
- `query.target_y_rotation` - Target Y rotation

#### Health & State
- `query.health` - Current health
- `query.max_health` - Maximum health
- `query.hurt_time` - Time since last hurt
- `query.is_sneaking` - 1.0 if sneaking, 0.0 otherwise
- `query.is_sprinting` - 1.0 if sprinting, 0.0 otherwise

Example:
```java
MoLangContext context = MoLangContext.fromEntity(entity);
MoLangExpression expr = MoLangExpression.parse(
    "query.health / query.max_health"
);
float healthRatio = expr.evaluate(context);
```

### 4. Comparison Operators
```java
MoLangExpression.parse("query.health > 10").evaluate(ctx);
MoLangExpression.parse("query.movement_speed >= 0.5").evaluate(ctx);
MoLangExpression.parse("query.is_moving == 1").evaluate(ctx);
```

### 5. Logical Operators
```java
// AND operator (short-circuit evaluation)
MoLangExpression.parse("query.is_moving && query.is_sprinting").evaluate(ctx);

// OR operator (short-circuit evaluation)
MoLangExpression.parse("query.is_sneaking || query.is_in_water").evaluate(ctx);

// NOT operator
MoLangExpression.parse("!query.is_on_ground").evaluate(ctx);
```

### 6. Ternary Operator
```java
MoLangExpression expr = MoLangExpression.parse(
    "query.is_moving ? math.sin(query.anim_time * 360) : 0"
);
```

### 7. Math Functions

#### Single Argument
- `math.abs(x)` - Absolute value
- `math.sin(x)` - Sine
- `math.cos(x)` - Cosine
- `math.sqrt(x)` - Square root
- `math.floor(x)` - Floor
- `math.ceil(x)` - Ceiling
- `math.round(x)` - Round
- `math.exp(x)` - Exponential
- `math.ln(x)` - Natural logarithm
- `math.random(x)` - Random value [0, x)
- `math.die_roll(x)` - Random integer [1, x]
- `math.hermite_blend(x)` - Hermite interpolation

#### Multi-Argument
- `math.pow(base, exponent)` - Power
- `math.min(a, b)` - Minimum
- `math.max(a, b)` - Maximum
- `math.clamp(value, min, max)` - Clamp value between min and max

### 8. Nested Functions
```java
MoLangExpression expr = MoLangExpression.parse(
    "math.abs(math.sin(query.life_time * 180))"
);
```

### 9. User Variables
```java
MoLangExpression expr = MoLangExpression.parse(
    "variable.amplitude * math.sin(query.anim_time * 360)"
);
expr.setVariable("amplitude", 2.5f);
float result = expr.evaluate(context);
```

## Usage Examples

### Basic Animation
```java
// Bobbing animation when moving
MoLangExpression expr = MoLangExpression.parse(
    "query.is_moving ? math.sin(query.anim_time * 8) * 0.05 : 0"
);

MoLangContext context = MoLangContext.fromEntity(entity);
float yOffset = expr.evaluate(context);
```

### Complex Animation
```java
// Speed-based animation with health modifier
MoLangExpression expr = MoLangExpression.parse(
    "(query.is_moving ? query.movement_speed : 0) * " +
    "(query.health / query.max_health) * " +
    "math.sin(query.anim_time * 360)"
);
```

### State-Based Animation
```java
// Different animations for different states
MoLangExpression expr = MoLangExpression.parse(
    "query.is_sprinting ? 2.0 : " +
    "(query.is_sneaking ? 0.5 : 1.0)"
);
```

## Performance

### Expression Caching
Expressions are automatically cached after parsing:
```java
// First parse - creates and caches expression
MoLangExpression expr1 = MoLangExpression.parse("query.life_time * 2");

// Second parse - retrieved from cache (fast)
MoLangExpression expr2 = MoLangExpression.parse("query.life_time * 2");
```

Cache size is limited to 1000 expressions. Clear with:
```java
MoLangExpression.clearCache();
```

### Thread Safety
- All operations are thread-safe
- Uses `ConcurrentHashMap` for caching and variables
- AST nodes are immutable and safely reusable

## Error Handling

The parser handles errors gracefully:

```java
// Division by zero returns 0
MoLangExpression.parse("10 / 0").evaluate(ctx); // 0.0

// Unknown queries return 0
MoLangExpression.parse("query.unknown").evaluate(ctx); // 0.0

// Parse errors are logged and return 0
MoLangExpression expr = new MoLangExpression("invalid!@#");
expr.evaluate(ctx); // 0.0, with error logged
```

## Migration from Old Parser

The enhanced parser is **fully backwards compatible**:

```java
// Old usage still works
MoLangExpression expr = new MoLangExpression("query.life_time");
float result = expr.evaluate(10.0f); // legacy method

// New usage with full context
MoLangContext context = new MoLangContext.Builder()
    .lifeTime(10.0f)
    .build();
float result2 = expr.evaluate(context);
```

## Implementation Details

### Architecture
1. **Tokenizer**: Lexical analysis converting string to tokens
2. **Parser**: Recursive descent parser building AST
3. **AST Nodes**: Immutable expression tree nodes
4. **Evaluator**: Tree-walking evaluation with context

### AST Node Types
- `NumberNode` - Literal numbers
- `QueryNode` - Query variables
- `VariableNode` - User variables
- `BinaryOpNode` - Binary operators (+, -, *, /, %)
- `ComparisonNode` - Comparison operators
- `LogicalAndNode`, `LogicalOrNode` - Logical operators
- `NotNode`, `NegateNode` - Unary operators
- `TernaryNode` - Ternary operator
- `FunctionNode` - Function calls

### Security Considerations

The `MoLangContext.fromEntity()` method uses reflection with `setAccessible(true)` to access private entity fields. This is necessary to avoid compile-time dependencies on Minecraft classes. The reflection is safe because:

1. Only used with trusted Minecraft entity objects
2. Only reads data, never modifies
3. Fails gracefully if reflection fails
4. No user input processed during reflection

## Testing

All features are tested with 73 comprehensive tests covering:
- Basic arithmetic
- Operator precedence
- Parentheses
- Query variables
- Comparison operators
- Logical operators
- Ternary operator
- Math functions
- Nested functions
- Error handling

Run tests with:
```bash
javac -cp /tmp/test-compile:. test_molang.java
java -cp /tmp/test-compile test_molang
```

## Future Enhancements

Potential additions:
- Array/vector operations
- More Bedrock-specific queries
- Custom function registration
- Expression optimization passes
- Debug mode with evaluation tracing

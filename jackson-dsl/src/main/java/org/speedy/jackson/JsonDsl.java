package org.speedy.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.*;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Json dsl to simplify the creation of sample object documents for testing
 * Created by derammelaere on 11/01/2016.
 */
public abstract class JsonDsl extends TupleDsl {

    /**
     * create a object node and registers the given {@link JsonNodeFactory} in a {@link ThreadLocal} at the start and
     * removes it at the end
     *
     * @param jsonNodeFactory the object node factory
     * @param tuples          the child tuples
     * @return the json document
     */
    public static ObjectNode jsonDocument(JsonNodeFactory jsonNodeFactory, Tuple<?>... tuples) {
        jsonNodeFactoryThreadLocal.set(jsonNodeFactory);
        ObjectNode objectNode = new ObjectNode(jsonNodeFactoryThreadLocal.get());
        for (Tuple<?> tuple : tuples) {
            objectNode.put(tuple.getName(), tuple.getValue());
        }
        jsonNodeFactoryThreadLocal.remove();
        return objectNode;
    }

    /**
     * create object node and registers {@link JsonNodeFactory} in a {@link ThreadLocal} at the start (if not present)
     * and removes it at the end
     *
     * @param tuples the child tuples
     * @return the json document
     */
    public static ObjectNode jsonDocument(Tuple<?>... tuples) {
        ObjectNode objectNode = new ObjectNode(jsonNodeFactoryThreadLocal.get());
        for (Tuple<?> tuple : tuples) {
            objectNode.put(tuple.getName(), tuple.getValue());
        }
        jsonNodeFactoryThreadLocal.remove();
        return objectNode;
    }

    /**
     * create a object node
     *
     * @param tuples the child tuples
     * @return the object node
     */
    public static ObjectNode object(Tuple<?>... tuples) {
        ObjectNode objectNode = new ObjectNode(jsonNodeFactoryThreadLocal.get());
        for (Tuple<?> tuple : tuples) {
            objectNode.put(tuple.getName(), tuple.getValue());
        }
        return objectNode;
    }

    /**
     * create a object node with a tuple
     *
     * @param name  name of the tuple
     * @param value value of the tuple
     * @return the object node
     */
    public static ObjectNode object(String name, String value) {
        ObjectNode objectNode = new ObjectNode(jsonNodeFactoryThreadLocal.get());
        objectNode.put(name, value);
        return objectNode;
    }

    /**
     * create a object node with a tuple
     *
     * @param name  name of the tuple
     * @param value value node of the tuple
     * @return the object node
     */
    public static ObjectNode object(String name, JsonNode value) {
        ObjectNode objectNode = new ObjectNode(jsonNodeFactoryThreadLocal.get());
        objectNode.put(name, value);
        return objectNode;
    }

    /**
     * create a array node with json nodes
     *
     * @param jsonNodes an array of json nodes
     * @return the array node
     */
    public static ArrayNode array(JsonNode... jsonNodes) {
        ArrayNode arrayNode = new ArrayNode(jsonNodeFactoryThreadLocal.get());
        for (JsonNode jsonNode : jsonNodes) {
            arrayNode.add(jsonNode);
        }
        return arrayNode;
    }

    /**
     * create a array node with string objects
     *
     * @param stringObjects an array of string objects
     * @return the array node
     */
    public static ArrayNode array(String... stringObjects) {
        ArrayNode arrayNode = new ArrayNode(jsonNodeFactoryThreadLocal.get());
        for (String stringObject : stringObjects) {
            arrayNode.add(stringObject);
        }
        return arrayNode;
    }

    /**
     * create a array node with big decimal objects
     *
     * @param bigDecimalObjects an array of big decimal objects
     * @return the array node
     */
    public static ArrayNode array(BigDecimal... bigDecimalObjects) {
        ArrayNode arrayNode = new ArrayNode(jsonNodeFactoryThreadLocal.get());
        for (BigDecimal bigDecimalObject : bigDecimalObjects) {
            arrayNode.add(bigDecimalObject);
        }
        return arrayNode;
    }

    /**
     * create a array node with float primitives
     *
     * @param floatPrimitives an array of float primitives
     * @return the array node
     */
    public static ArrayNode array(float... floatPrimitives) {
        ArrayNode arrayNode = new ArrayNode(jsonNodeFactoryThreadLocal.get());
        for (float floatPrimitive : floatPrimitives) {
            arrayNode.add(floatPrimitive);
        }
        return arrayNode;
    }

    /**
     * create a array node with double primitives
     *
     * @param doublePrimitives an array of double primitives
     * @return the array node
     */
    public static ArrayNode array(double... doublePrimitives) {
        ArrayNode arrayNode = new ArrayNode(jsonNodeFactoryThreadLocal.get());
        for (double doublePrimitive : doublePrimitives) {
            arrayNode.add(doublePrimitive);
        }
        return arrayNode;
    }

    /**
     * create a array node with int primitives
     *
     * @param intPrimitives an array of int primitives
     * @return the array node
     */
    public static ArrayNode array(int... intPrimitives) {
        ArrayNode arrayNode = new ArrayNode(jsonNodeFactoryThreadLocal.get());
        for (int intPrimitive : intPrimitives) {
            arrayNode.add(intPrimitive);
        }
        return arrayNode;
    }

    /**
     * create a null node
     *
     * @return the null node
     */
    public static NullNode nil() {
        return jsonNodeFactoryThreadLocal.get().nullNode();
    }

    /**
     * creates a boolean node
     *
     * @param value the boolean value
     * @return the boolean node
     */
    public static BooleanNode bool(boolean value) {
        return jsonNodeFactoryThreadLocal.get().booleanNode(value);
    }

    /**
     * creates a binary node
     *
     * @param value the byte array
     * @return the binary node
     */
    public static BinaryNode binary(byte[] value) {
        return jsonNodeFactoryThreadLocal.get().binaryNode(value);
    }

    /**
     * creates a text node
     *
     * @param value a value string
     * @return the text node
     */
    public static TextNode text(String value) {
        return jsonNodeFactoryThreadLocal.get().textNode(value);
    }

    /**
     * creates a numeric node
     *
     * @param value a byte value
     * @return the numeric node
     */
    public static NumericNode number(byte value) {
        return jsonNodeFactoryThreadLocal.get().numberNode(value);
    }

    /**
     * creates a numeric node
     *
     * @param value a short value
     * @return the numeric node
     */
    public static NumericNode number(short value) {
        return jsonNodeFactoryThreadLocal.get().numberNode(value);
    }

    /**
     * creates a numeric node
     *
     * @param value a int value
     * @return the numeric node
     */
    public static NumericNode number(int value) {
        return jsonNodeFactoryThreadLocal.get().numberNode(value);
    }

    /**
     * creates a numeric node
     *
     * @param value a long value
     * @return the numeric node
     */
    public static NumericNode number(long value) {
        return jsonNodeFactoryThreadLocal.get().numberNode(value);
    }

    /**
     * creates a numeric node
     *
     * @param value a float value
     * @return the numeric node
     */
    public static NumericNode number(float value) {
        return jsonNodeFactoryThreadLocal.get().numberNode(value);
    }

    /**
     * creates a numeric node
     *
     * @param value a double value
     * @return the numeric node
     */
    public static NumericNode number(double value) {
        return jsonNodeFactoryThreadLocal.get().numberNode(value);
    }

    /**
     * creates a numeric node
     *
     * @param value a BigDecimal value
     * @return the numeric node
     */
    public static NumericNode number(BigDecimal value) {
        return jsonNodeFactoryThreadLocal.get().numberNode(value);
    }

    /**
     * creates a numeric node
     *
     * @param value a BigInteger value
     * @return the numeric node
     */
    public static NumericNode number(BigInteger value) {
        return jsonNodeFactoryThreadLocal.get().numberNode(value);
    }

    /**
     * creates a value node
     *
     * @param value a Byte value
     * @return the value node
     */
    public static ValueNode number(Byte value) {
        return jsonNodeFactoryThreadLocal.get().numberNode(value);
    }

    /**
     * creates a value node
     *
     * @param value a Short value
     * @return the value node
     */
    public static ValueNode number(Short value) {
        return jsonNodeFactoryThreadLocal.get().numberNode(value);
    }

    /**
     * creates a value node
     *
     * @param value a Integer value
     * @return the value node
     */
    public static ValueNode number(Integer value) {
        return jsonNodeFactoryThreadLocal.get().numberNode(value);
    }

    /**
     * creates a value node
     *
     * @param value a Long value
     * @return the value node
     */
    public static ValueNode number(Long value) {
        return jsonNodeFactoryThreadLocal.get().numberNode(value);
    }

    /**
     * creates a value node
     *
     * @param value a Float value
     * @return the value node
     */
    public static ValueNode number(Float value) {
        return jsonNodeFactoryThreadLocal.get().numberNode(value);
    }

    /**
     * creates a value node
     *
     * @param value a Double value
     * @return the value node
     */
    public static ValueNode number(Double value) {
        return jsonNodeFactoryThreadLocal.get().numberNode(value);
    }

    /**
     * create a array node with long primitives
     *
     * @param longPrimitives an array of long primitives
     * @return the array node
     */
    public static ArrayNode array(long... longPrimitives) {
        ArrayNode arrayNode = new ArrayNode(jsonNodeFactoryThreadLocal.get());
        for (long longPrimitive : longPrimitives) {
            arrayNode.add(longPrimitive);
        }
        return arrayNode;
    }

    /**
     * create a array node with boolean primitives
     *
     * @param booleanPrimitives an array of boolean primitives
     * @return the array node
     */
    public static ArrayNode array(boolean... booleanPrimitives) {
        ArrayNode arrayNode = new ArrayNode(jsonNodeFactoryThreadLocal.get());
        for (boolean booleanPrimitive : booleanPrimitives) {
            arrayNode.add(booleanPrimitive);
        }
        return arrayNode;
    }

}
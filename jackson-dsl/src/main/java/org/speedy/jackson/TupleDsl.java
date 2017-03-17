package org.speedy.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.*;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Tuple dsl to simplify the creation of key-value pairs in an json object node
 * Created by derammelaere on 2/02/2016.
 */
abstract class TupleDsl {

    protected final static ThreadLocal<JsonNodeFactory> jsonNodeFactoryThreadLocal = new ThreadLocal<JsonNodeFactory>() {

        @Override
        protected JsonNodeFactory initialValue() {
            return JsonNodeFactory.instance;
        }

    };

    /**
     * create a tuple
     *
     * @param name  the name of the tuple
     * @param value a byte value
     * @return the tuple
     */
    public static Tuple<NumericNode> tuple(String name, byte value) {
        return TupleObject.of(name, value);
    }

    /**
     * create a tuple
     *
     * @param name  the name of the tuple
     * @param value a byte array value
     * @return the tuple
     */
    public static Tuple<BinaryNode> tuple(String name, byte[] value) {
        return TupleObject.of(name, value);
    }

    /**
     * create a tuple
     *
     * @param name  the name of the tuple
     * @param value a int value
     * @return the tuple
     */
    public static Tuple<NumericNode> tuple(String name, int value) {
        return TupleObject.of(name, value);
    }

    /**
     * create a tuple
     *
     * @param name  the name of the tuple
     * @param value a short value
     * @return the tuple
     */
    public static Tuple<NumericNode> tuple(String name, short value) {
        return TupleObject.of(name, value);
    }

    /**
     * create a tuple
     *
     * @param name  the name of the tuple
     * @param value a long value
     * @return the tuple
     */
    public static Tuple<NumericNode> tuple(String name, long value) {
        return TupleObject.of(name, value);
    }

    /**
     * create a tuple
     *
     * @param name  the name of the tuple
     * @param value a float value
     * @return the tuple
     */
    public static Tuple<NumericNode> tuple(String name, float value) {
        return TupleObject.of(name, value);
    }

    /**
     * create a tuple
     *
     * @param name  the name of the tuple
     * @param value a double value
     * @return the tuple
     */
    public static Tuple<NumericNode> tuple(String name, double value) {
        return TupleObject.of(name, value);
    }

    /**
     * create a tuple
     *
     * @param name  the name of the tuple
     * @param value a BigInteger value
     * @return the tuple
     */
    public static Tuple<ValueNode> tuple(String name, BigInteger value) {
        return TupleObject.of(name, value);
    }

    /**
     * create a tuple
     *
     * @param name  the name of the tuple
     * @param value a BigDecimal value
     * @return the tuple
     */
    public static Tuple<ValueNode> tuple(String name, BigDecimal value) {
        return TupleObject.of(name, value);
    }

    /**
     * create a tuple
     *
     * @param name  the name of the tuple
     * @param value a boolean value
     * @return the tuple
     */
    public static Tuple<BooleanNode> tuple(String name, boolean value) {
        return TupleObject.of(name, value);
    }

    /**
     * create a tuple
     *
     * @param name  the name of the tuple
     * @param value a Byte value
     * @return the tuple
     */
    public static Tuple<ValueNode> tuple(String name, Byte value) {
        return TupleObject.of(name, value);
    }

    /**
     * create a tuple
     *
     * @param name  the name of the tuple
     * @param value a Boolean value
     * @return the tuple
     */
    public static Tuple<BooleanNode> tuple(String name, Boolean value) {
        return TupleObject.of(name, value);
    }

    /**
     * create a tuple
     *
     * @param name  the name of the tuple
     * @param value a Integer value
     * @return the tuple
     */
    public static Tuple<ValueNode> tuple(String name, Integer value) {
        return TupleObject.of(name, value);
    }

    /**
     * create a tuple
     *
     * @param name  the name of the tuple
     * @param value a Short value
     * @return the tuple
     */
    public static Tuple<ValueNode> tuple(String name, Short value) {
        return TupleObject.of(name, value);
    }

    /**
     * create a tuple
     *
     * @param name  the name of the tuple
     * @param value a Long value
     * @return the tuple
     */
    public static Tuple<ValueNode> tuple(String name, Long value) {
        return TupleObject.of(name, value);
    }

    /**
     * create a tuple
     *
     * @param name  the name of the tuple
     * @param value a Float value
     * @return the tuple
     */
    public static Tuple<ValueNode> tuple(String name, Float value) {
        return TupleObject.of(name, value);
    }

    /**
     * create a tuple
     *
     * @param name  the name of the tuple
     * @param value a Double value
     * @return the tuple
     */
    public static Tuple<ValueNode> tuple(String name, Double value) {
        return TupleObject.of(name, value);
    }

    /**
     * create a tuple
     *
     * @param name  the name of the tuple
     * @param value a string value
     * @return the tuple
     */
    public static Tuple<TextNode> tuple(String name, String value) {
        return TupleObject.of(name, value);
    }

    /**
     * create a tuple
     *
     * @param name     the name of the tuple
     * @param jsonNode a json node value
     * @return the tuple
     */
    public static <T extends JsonNode> Tuple<T> tuple(String name, T jsonNode) {
        return TupleObject.of(name, jsonNode);
    }

    /**
     * interface representing a tuple in an object node
     *
     * @param <T> the type of the value of tuple
     */
    public interface Tuple<T extends JsonNode> {

        String getLeft();

        T getRight();

        String getName();

        T getValue();
    }

    /**
     * object implementing the tuple interface
     *
     * @param <T> the type of the value of tuple
     */
    private static final class TupleObject<T extends JsonNode> implements Tuple<T> {
        private final String left;
        private final T right;

        private TupleObject(String left, T right) {
            this.left = left;
            this.right = right;
        }

        public String getLeft() {
            return left;
        }

        public T getRight() {
            return right;
        }

        public String getName() {
            return getLeft();
        }

        public T getValue() {
            return getRight();
        }

        static <T extends JsonNode> Tuple<T> of(String left, T right) {
            return new TupleObject<T>(left, right);
        }

        static Tuple<TextNode> of(String left, String right) {
            return new TupleObject<TextNode>(left, jsonNodeFactoryThreadLocal.get().textNode(right));
        }

        static Tuple<BooleanNode> of(String left, boolean right) {
            return new TupleObject<BooleanNode>(left, jsonNodeFactoryThreadLocal.get().booleanNode(right));
        }

        static Tuple<BooleanNode> of(String left, Boolean right) {
            return new TupleObject<BooleanNode>(left, jsonNodeFactoryThreadLocal.get().booleanNode(right));
        }

        static Tuple<NumericNode> of(String left, byte right) {
            return new TupleObject<NumericNode>(left, jsonNodeFactoryThreadLocal.get().numberNode(right));
        }

        static Tuple<BinaryNode> of(String left, byte[] right) {
            return new TupleObject<BinaryNode>(left, jsonNodeFactoryThreadLocal.get().binaryNode(right));
        }

        static Tuple<NumericNode> of(String left, short right) {
            return new TupleObject<NumericNode>(left, jsonNodeFactoryThreadLocal.get().numberNode(right));
        }

        static Tuple<NumericNode> of(String left, int right) {
            return new TupleObject<NumericNode>(left, jsonNodeFactoryThreadLocal.get().numberNode(right));
        }

        static Tuple<NumericNode> of(String left, long right) {
            return new TupleObject<NumericNode>(left, jsonNodeFactoryThreadLocal.get().numberNode(right));
        }

        static Tuple<NumericNode> of(String left, float right) {
            return new TupleObject<NumericNode>(left, jsonNodeFactoryThreadLocal.get().numberNode(right));
        }

        static Tuple<NumericNode> of(String left, double right) {
            return new TupleObject<NumericNode>(left, jsonNodeFactoryThreadLocal.get().numberNode(right));
        }

        static Tuple<ValueNode> of(String left, Byte right) {
            return new TupleObject<ValueNode>(left, jsonNodeFactoryThreadLocal.get().numberNode(right));
        }

        static Tuple<ValueNode> of(String left, Short right) {
            return new TupleObject<ValueNode>(left, jsonNodeFactoryThreadLocal.get().numberNode(right));
        }

        static Tuple<ValueNode> of(String left, Integer right) {
            return new TupleObject<ValueNode>(left, jsonNodeFactoryThreadLocal.get().numberNode(right));
        }

        static Tuple<ValueNode> of(String left, Long right) {
            return new TupleObject<ValueNode>(left, jsonNodeFactoryThreadLocal.get().numberNode(right));
        }

        static Tuple<ValueNode> of(String left, Float right) {
            return new TupleObject<ValueNode>(left, jsonNodeFactoryThreadLocal.get().numberNode(right));
        }

        static Tuple<ValueNode> of(String left, Double right) {
            return new TupleObject<ValueNode>(left, jsonNodeFactoryThreadLocal.get().numberNode(right));
        }

        static Tuple<ValueNode> of(String left, BigDecimal right) {
            return new TupleObject<ValueNode>(left, jsonNodeFactoryThreadLocal.get().numberNode(right));
        }

        static Tuple<ValueNode> of(String left, BigInteger right) {
            return new TupleObject<ValueNode>(left, jsonNodeFactoryThreadLocal.get().numberNode(right));
        }
    }
}

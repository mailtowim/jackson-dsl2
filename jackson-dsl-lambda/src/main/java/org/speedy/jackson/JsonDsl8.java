package org.speedy.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Json Dsl utility which adds closure support to {@link JsonDsl} <br/>
 * Remarque: Code needs to be compiled with Java 8u60 or higher and with -parameters flag to use closures with this dsl
 * Created by derammelaere on 2/02/2016.
 */
public abstract class JsonDsl8 extends JsonDsl {

    /**
     * create a object node and registers the given {@link JsonNodeFactory} in a {@link ThreadLocal} at the start and
     * removes it at the end
     *
     * @param jsonNodeFactory the object node factory
     * @param closures        the child tuple closures
     * @return the json document
     */
    public static ObjectNode jsonDocument(JsonNodeFactory jsonNodeFactory, TupleClosure<?>... closures) {
        return jsonDocument(jsonNodeFactory, (Tuple<?>[]) closures);
    }

    /**
     * create a object node and registers a {@link JsonNodeFactory} in a {@link ThreadLocal} at the start (if not present)
     * and removes it at the end
     *
     * @param closures the child tuple closures
     * @return the json document
     */
    public static ObjectNode jsonDocument(TupleClosure<?>... closures) {
        return jsonDocument((Tuple<?>[]) closures);
    }

    /**
     * create a object node
     *
     * @param closures the child tuples closures
     * @return the object node
     */
    public static ObjectNode object(TupleClosure<?>... closures) {
        return object((Tuple<?>[]) closures);
    }

    /**
     * functional interface for tuple
     * which requires a parameter with the name of the tuple and as a return value, the right value of this tuple
     */
    public interface TupleClosure<T extends JsonNode> extends Tuple<T>, Function<String, T>, Serializable {

        default void checkParameters() {
            if (getLambdaMethod().getParameterCount() != 1) {
                throw new IllegalArgumentException("One parameter needs to be passed to the tuple closure.");
            }
            if ("arg0".equals(getParameterName(0))) {
                throw new IllegalStateException("Code needs to be compiled with Java 8u60 or higher and -parameters flag");
            }
        }

        default Class<?> getLambdaClass(SerializedLambda lambda) {
            try {
                String className = lambda.getImplClass().replaceAll("/", ".");
                return Class.forName(className);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        default Method getLambdaMethod() {
            SerializedLambda lambda = getSerializedLambda();
            Class<?> containingClass = getLambdaClass(lambda);
            return Stream.of(containingClass.getDeclaredMethods())
                    .filter(method -> Objects.equals(method.getName(), lambda.getImplMethodName()))
                    .findFirst()
                    .orElseThrow(NoSuchMethodRuntimeException::new);
        }

		default String getLeft(){
            checkParameters();
            return getParameterName(0);
		}

        default String getName() {
            return getLeft();
        }

        default String getParameterName(int n) {
            return getLambdaMethod().getParameters()[n].getName();
        }

        default T getRight() {
            return apply(null);
        }

        default SerializedLambda getSerializedLambda() {
            try {
                Method replaceMethod = getClass().getDeclaredMethod("writeReplace");
                replaceMethod.setAccessible(true);
                return (SerializedLambda) replaceMethod.invoke(this);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        default T getValue() {
            return getRight();
        }
    }

    public static class NoSuchMethodRuntimeException extends RuntimeException {
        public NoSuchMethodRuntimeException() {
        }

        public NoSuchMethodRuntimeException(String var) {
            super(var);
        }
    }
}

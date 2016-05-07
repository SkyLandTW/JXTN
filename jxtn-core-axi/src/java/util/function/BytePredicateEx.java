package java.util.function;

/**
 * 可拋出例外的{@link BytePredicate}。
 *
 * @author AqD
 * @param <TException> 例外型態
 */
@FunctionalInterface
public interface BytePredicateEx<TException extends Exception> extends BytePredicate {

    boolean testEx(byte value) throws TException;

    @Deprecated
    @Override
    default boolean test(byte value) {
        try {
            return this.testEx(value);
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                throw (RuntimeException) e;
            } else {
                throw new RuntimeException(e);
            }
        }
    }
}

package java.util.function;

/**
 * 可拋出例外的{@link CharPredicate}。
 *
 * @author AqD
 * @param <TException> 例外型態
 */
@FunctionalInterface
public interface CharPredicateEx<TException extends Exception> extends CharPredicate {

    boolean testEx(char value) throws TException;

    @Deprecated
    @Override
    default boolean test(char value) {
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

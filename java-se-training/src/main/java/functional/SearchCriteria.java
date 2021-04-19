package functional;

@FunctionalInterface
public interface SearchCriteria<T> {

    boolean test(T trainer);

}

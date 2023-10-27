package Lab8.Abstractions;

public interface IConditional {
    default boolean UpdateCondition() {
        System.out.println("this items condition never changes: " + this);
        return false;
    }
}

package pl.dabrowska.michalowski.battleships.view.element;


@FunctionalInterface
public interface ViewElement<T> {
    String prepareText(T object);
}

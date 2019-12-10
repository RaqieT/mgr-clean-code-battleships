package pl.dabrowska.michalowski.battleships.view.element;

import pl.dabrowska.michalowski.battleships.exception.RenderingException;

@FunctionalInterface
public interface ViewElement<T> {
    String prepareText(T object) throws RenderingException;
}

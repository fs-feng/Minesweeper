package game;

import java.util.ArrayList;

public class Caretaker {
    ArrayList<Memento> listMemento = new ArrayList<>();

    public void addMemento(Memento memento) {
        listMemento.add(memento.save());
    }

    public void clearListMemento() {
        listMemento = new ArrayList<>();
    }

    public Memento getMemento() {
        int index = listMemento.size() - 2;

        Memento memento = listMemento.get(index);
        listMemento.remove(index);
        return memento;
    }
}

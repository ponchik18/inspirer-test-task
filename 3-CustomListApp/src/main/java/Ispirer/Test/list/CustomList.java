package Ispirer.Test.list;

import java.util.ArrayList;
import java.util.List;

public class CustomList<T> {
    private List<T> internalList;
    private List<ListChangedListener> listeners;

    public CustomList() {
        internalList = new ArrayList<>();
        listeners = new ArrayList<>();
    }

    public void add(T item) {
        internalList.add(item);
        fireListChangedEvent();
    }

    public T get(int index) {
        return internalList.get(index);
    }

    public int size() {
        return internalList.size();
    }

    public void addListChangedListener(ListChangedListener listener) {
        listeners.add(listener);
    }

    public void removeListChangedListener(ListChangedListener listener) {
        listeners.remove(listener);
    }

    private void fireListChangedEvent() {
        ListChangedEvent event = new ListChangedEvent(this);
        for (ListChangedListener listener : listeners) {
            listener.listChanged(event);
        }
    }
}
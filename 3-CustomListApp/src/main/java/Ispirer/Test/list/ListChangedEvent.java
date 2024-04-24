package Ispirer.Test.list;

import java.util.EventObject;

public class ListChangedEvent extends EventObject {
    public ListChangedEvent(Object source) {
        super(source);
    }
}
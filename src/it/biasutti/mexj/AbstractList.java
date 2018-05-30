package it.biasutti.mexj;

import java.util.ArrayList;

public abstract class AbstractList<T> {
    protected ArrayList<T> _items;

    public AbstractList() {
        _items = new ArrayList<T>();
    }

    public int size() {
        return _items.size();
    }


    public int findByObject(T item) {
        for (int i=0; i<_items.size(); i++){
            if (_items.get(i)== item){
                return i;
            }
        }
        return -1;
    }

    public Iterable<T> items () {
        return _items;
    }
}

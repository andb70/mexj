package it.biasutti.mexj;
import java.util.Collections;
public class ActionLogger<T> extends AbstractList<T>{
    public void add(T action) {
        _items.add(action);
    }
    public void shuffle() {
        Collections.shuffle(_items);
    }
    public void remove(T item) {
        int i = _items.indexOf(item);
        if (i<0){
            console.log("ActionLogger: rimozione fallita");
            return;
        }
        _items.remove(i);
    }
}

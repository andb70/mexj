package it.biasutti.mexj;

public class TaggedValue<V, T>{
    private V _value;
    private T _tag;

    public TaggedValue(V value, T tag) {
        _value = value;
        _tag = tag;
    }

    public V getValue() {
        return _value;
    }

    public void setValue(V _value) {
        this._value = _value;
    }

    public T getTag() {
        return _tag;
    }

    public void setTag(T _tag) {
        this._tag = _tag;
    }
}

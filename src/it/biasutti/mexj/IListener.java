package it.biasutti.mexj;

public interface IListener<T, M> {

    void newMessage(T sender, M message);

}

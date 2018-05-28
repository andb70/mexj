package it.biasutti.mexj;

public interface IListener<T> {

    void newMessage(T sender, Message message);

}

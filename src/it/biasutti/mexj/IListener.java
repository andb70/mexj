package it.biasutti.mexj;

public interface IListener<S, M> {

    void newMessage(S sender, M message);

}

package it.biasutti.mexj;

interface IPublisher<T> {
    boolean subscribe(T user);
    boolean unsubscribe(T user);
    void onNewMessage(T sender, Message message);
}

package it.biasutti.mexj;

interface IPublisher<T, M> {
    boolean subscribe(T user);
    boolean unsubscribe(T user);
    void onNewMessage(T sender, M message);
}

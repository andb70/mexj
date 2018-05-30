package it.biasutti.mexj;
import java.util.Map;
interface IPublisher<T, M> {
    T subscribe(T user, T followee);
    T unsubscribe(T user, T followee);
    T onNewMessage(T sender, M message);
    T onMute(T user, T followee);
    T onUnmute(T user, T followee);
    Map<T, M> getFollowers(T user);
}

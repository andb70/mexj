package it.biasutti.mexj;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class Broker<T> implements IPublisher<T, Message> {
    /* mappa degli utenti che hanno followers
            la chiave è l'utente seguito
            il valore è un TaggedValue<Map, Integer> in cui
                il valore è la mappa dei followers in cui
                    la chiave è l'utente che segue
                    il valore è -1 oppure l'ultimo messaggio visto
                il tag è l'ultimo messaggio che l'utente seguito ha spedito
     */
    Map<T, TaggedValue<Map, Integer>> _users = new HashMap<T, TaggedValue<Map, Integer>>();


    /* lista dei messaggi
            il valore è un TaggedValue<Message, T> in cui
                il valore è il messaggio ricevuto
                il tag è l'utente che l'ha spedito

     */
    List<TaggedValue<Message, T>> _messages = new ArrayList<TaggedValue<Message, T>>();


    @Override
    public T subscribe(T user, T followee) {
        try {
            TaggedValue<Map, Integer> followers;
            if (!_users.containsKey(followee)) {
                _users.put(followee,
                        new TaggedValue<Map, Integer>(
                                new HashMap<T, Integer>(),
                                getLastMessage()
                        ));
            }
            followers = _users.get(followee);
            if (!followers.getValue().containsKey(user)) {
                followers.getValue().put(user, -1);
            }
        } catch (Exception e) {
            console.log("Broker subscribe", e);
            return null;
        }
        return user;
    }

    @Override
    public T unsubscribe(T user, T followee) {
        try {
            TaggedValue<Map, Integer> followers;
            if (!_users.containsKey(followee)) {
                return null;
            }
            followers = _users.get(followee);
            if (!followers.getValue().containsKey(user)) {
                return null;
            }
            followers.getValue().remove(user);
        } catch (Exception e) {
            console.log("Broker unsubscribe", e);
            return null;
        }
        return user;
    }

    @Override
    public T onNewMessage(T sender, Message message) {
        _messages.add(new TaggedValue<Message, T>(
                message,
                sender
        ));
        if (!_users.containsKey(sender)) {
            console.log("OnMessage user not found");
            return null;
        }        
        /*
            aggiorna l'ultimo messaggio di sender
            per ogni follower con (mute = false)
            spedisci il messaggio
         */
        TaggedValue<Map, Integer> followers;
        followers = _users.get(sender);
        followers.getValue().forEach((user,lastMessage) -> {
            /*
            il valore è un TaggedValue<Map, Integer> in cui
                il valore è la mappa dei followers in cui
                    la chiave è l'utente che segue
                    il valore è -1 oppure l'ultimo messaggio visto
             */
            if ((int)lastMessage < 0){
                IListener<T, Message> listener = (IListener<T, Message>)user;
                listener.newMessage(sender, message);
            }
        });
        return sender;
    }

    @Override
    public T onMute(T user, T followee) {
        return _mute(user, followee, getLastMessage());
    }

    @Override
    public T onUnmute(T user, T followee) {
        return _mute(user, followee, -1);

    }

    @Override
    public Map<T, Message> getFollowers(T user) {
        if (!_users.containsKey(user)) {
            return null;
        }
        return  _users.get(user).getValue();
    }

    private int getLastMessage() {
        return _messages.size();
    }

    private T _mute(T user, T followee, Integer lastMessage) {
        // todo verificare ordine di user e followee
        try {
            TaggedValue<Map, Integer> followers;
            if (!_users.containsKey(followee)) {
                return null;
            }
            followers = _users.get(followee);
            if (!followers.getValue().containsKey(user)) {
                return null;
            }
            followers.getValue().replace(user, lastMessage);
        } catch (Exception e) {
            console.log("Broker (un)mute", e);
            return null;
        }
        return user;
    }
}

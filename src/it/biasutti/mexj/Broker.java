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
    protected Map<T, TaggedValue<Map<T,Integer>, Integer>> _users = new HashMap<>();


    /* lista dei messaggi
            il valore è un TaggedValue<Message, T> in cui
                il valore è il messaggio ricevuto
                il tag è l'utente che l'ha spedito
     */
    protected List<TaggedValue<Message, T>> _messages = new ArrayList<>();


    @Override
    public T subscribe(T user, T followee) {
        /*
            [user] wants to follow [followee]

            check _users and find [followee]
            if not found > _users.add(followee)
                followMap = _users.getvalue(followee)

            check followMap and find [user]
            if not found > followMap.add(user)

            _users:     Map<T, TaggedValue<Map, Integer>>
            user:       T
            followee:   T
            followMap:  TaggedValue<Map, Integer>
            followers:  Map
            mute:       Integer
            lastMessage:Integer

            followers.put(user, mute)
            followMap(followers, lastMessage)
            _users.put(followee, followMap)

         */

        if (user == null || followee == null) {
            if (user == null) {
                console.log(">>>>>>>>>    subscribe has a null followee");
            }
            if (followee == null) {
                console.log(">>>>>>>>>    subscribe has a null follower");
            }
        }


        TaggedValue<Map<T,Integer>, Integer> followMap;
        if (!_users.containsKey(followee)) {
            Map<T, Integer> m = new HashMap<>();
            m.put(user, -1);
            _users.put(
                    followee,
                    new TaggedValue<>(m, getLastMessage())
            );
        }
        followMap = _users.get(followee);
        Map<T, Integer> m = followMap.getValue();

        if (!m.containsKey(user)) {
            m.put(user, -1);
        }


        return user;
    }

    @Override
    public T unsubscribe(T user, T followee) {
        try {

            if (!_users.containsKey(followee)) {
                return null;
            }
            Map followers = _users.get(followee).getValue();
            if (!followers.containsKey(user)) {
                return null;
            }
            followers.remove(user);
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
        TaggedValue<Map<T, Integer>, Integer> followers;
        followers = _users.get(sender);
        followers.getValue().forEach((user, lastMessage) -> {
            /*
            il valore è un TaggedValue<Map, Integer> in cui
                il valore è la mappa dei followers in cui
                    la chiave è l'utente che segue
                    il valore è -1 oppure l'ultimo messaggio visto
             */
            if (lastMessage < 0) {
                IListener<T, Message> listener = (IListener<T, Message>) user;
                listener.newMessage(sender, message);
            }
        });
        return sender;
    }

    @Override
    public T onMute(T user, T followee) {
        Map<T, Integer> m = _follower(user, followee);
        if (m!=null) {
            m.replace(user, getLastMessage());
            return user;
        }
        return null;
    }

    @Override
    public T onUnmute(T user, T followee) {
        Map<T, Integer> m = _follower(user, followee);
        if (m!=null &&  _fireMessages(user, followee, m.get(user))) {
            m.replace(user, -1);
            return user;
        }
        return null;
    }


    private boolean _fireMessages(T user, T followee, Integer lastMessage) {
        for (int i = lastMessage; i< _messages.size(); i++) {
            TaggedValue<Message, T> m =_messages.get(i);
            if (m.getTag() == followee) {
                //console.log("send  to %s the message %s", user, m.getValue());
                IListener<T, Message> listener = (IListener<T, Message>) user;
                listener.newMessage(followee, m.getValue());
            }
        }
        return true;
    }

    /**
     * find a specific follower subscribed to followee
     * @param user the target follower
     * @param followee who is followed by user
     * @return the map containig the follower or null if we can't find it
     */
    private Map<T, Integer> _follower(T user, T followee) {
        Map<T, Integer> m = getFollowers(followee);
        if (m==null || !m.containsKey(user)) {
            return null;
        }
        return m;
    }

    @Override
    public Map<T, Integer> getFollowers(T user) {
        if (!_users.containsKey(user)) {
            return null;
        }
        return _users.get(user).getValue();
    }

    @Override
    public Map<T, Integer> getFollowees(T user) {
        Map<T, Integer> followees = new HashMap<>();
        _users.forEach((u, followers) -> {
            // followers: TaggedValue<Map, Integer>
            Map<T, Integer> follower = followers.getValue();
            if (follower.containsKey(user)) {
                Integer lastMessage = follower.get(user);
                followees.put(u, lastMessage);
            }
        });
        return followees;
    }

    private int getLastMessage() {
        return _messages.size();
    }
}

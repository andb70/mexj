package it.biasutti.mexj;

import java.util.ArrayList;

public class MessageList<T> extends AbstractList<T>{

    public void add(Message message, boolean muted) {
        _items.add((T) new MessageEnvelope(message, muted));
    }

    public Message[] getMessages(int num) {
        ArrayList<Message> al = new ArrayList<Message>();
        for (int i = _items.size()-1; i > -1; i--) {
            MessageEnvelope me = (MessageEnvelope) _items.get(i);
            if (!me.isMuted()) {
                al.add( me.getMessage());
            }
        }
        Message[] mr = new Message[al.size()];
        return al.toArray(mr);
    }

    /**
     * partendo dal presupposto che il mute non è rettroattivo
     * cerco tutti i messaggi in cui l'id corrisponde e verifico
     * se ha il mute:
     *  se ha il mute lo rimuovo e proseguo
     *  se non ha il mute concludo la ricerca
     */
    public void unmute (int id) {
        for (int i = _items.size()-1; i > -1; i--) {
            MessageEnvelope me = (MessageEnvelope) _items.get(i);
            if (me.getMessage().getId()==id) {
                if (!me.isMuted()) {
                    break;
                }
                me.unmute();
            }
        }
    }

}

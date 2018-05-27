package it.biasutti.mexj;
import java.util.*;
public class MessageHandler {
    ArrayList<Message> _messages;
    private static MessageHandler instance;
    private MessageHandler(){
        _messages = new ArrayList<Message>();
    }

    public static MessageHandler getInstance(){
        if(instance == null){
            instance = new MessageHandler();
        }
        return instance;
    }
    boolean follow(User follower, Object followee){
        return false;
    }
    boolean unfollow(User follower, Object followee){
       return false;
    }


    public void sendMessage(User sender, Message message) {
        _messages.add(message);
    }
}


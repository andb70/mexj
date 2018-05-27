package it.biasutti.mexj;

public class User implements IUser, IFollower {

    protected String _userName;
    protected IUsers _parent;
    protected MessageHandler _messageHandler;
    protected FollowList _followers;

    public User(String userName, Users parent) {
        _userName = userName;
        _parent = parent;
        _messageHandler = MessageHandler.getInstance();
        console.log("Creatol'utente con nome \"%s\".", _userName );
    }
    public FollowList getFollowers() {
        if (_followers == null) {
            _followers = new FollowList();
        }
        return _followers;
    }

    public String getUserName() {
        return _userName;
    }

    public User renameAs(String newName) {
        if (_parent.rename(this, newName)!= null) {
            _userName = newName;
        } else {
            console.log("Rename: L'utente \"%s\" non è membro dell'insieme\noppure non può cambiare nome in \"%s\"", _userName, newName);
        }
        return this;
    }

    @Override
    public void newMessage(User sender, Message message) {

    }




    public User follow(String followee) {
        User u = _parent.getUser(followee);
        if (u != null) {
            getFollowers().add(u);
        }
        return u;
    }

    public User unfollow(String followee) {
        User u = _parent.getUser(followee);
        if (u != null) {
            getFollowers().remove(u);
        }
        return u;
    }

    public User mute(String muteeName) {
        User u = _parent.getUser(muteeName);
        if (u != null) {
            u.mute(muteeName);
        }
        return u;
    }

    public User unmute(String muteeName) {
        User u = _parent.getUser(muteeName);
        if (u != null) {
            u.unmute(muteeName);
        }
        return u;
    }


    public User publish(String message) {
        Message m = new Message(message, _userName ,0 );
        _messageHandler.sendMessage(this, m);
        return this;
    }

    public Message[] queryFrom(int count) {
        return null;
    }

    public Message[] queryTo(int count) {
        return null;
    }

    public Query query(int count) {
        return new Query(count);
    }

}

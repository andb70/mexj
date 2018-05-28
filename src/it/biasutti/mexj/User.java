package it.biasutti.mexj;

public class User<T> implements IUser<T>, IListener<T> {

    private String _name;
    private int _id;
    private IUsers<IUser> _parent;
    private FollowList<IUser> _followers;
    private MessageList<T> _sent;
    private MessageList<T> _received;
    private MuteList<T> _muted;

    User(String userName, Users parent, int id) {
        _name = userName;
        _parent = parent;
        _id = id;
        _followers = new FollowList<IUser>();
        _sent = new MessageList<T>();
        _received = new MessageList<T>();
        _muted = new MuteList<T>();

        console.log("        user (%d) created with name \"%s\".",_id, _name );
    }
    public FollowList getFollowers() {
        return _followers;
    }
    private boolean hasFollowers() {
        return ( _followers.size()>0);
    }
    public MessageList getSentMessages() {
        return _sent;
    }

    public MessageList getReceivedMessages() {
        return _received;
    }

    /**
     * IUser
     */
    @Override
    public String getName() {
        return _name;
    }
    @Override
    public int getId() {
        return _id;
    }
    @Override
    public boolean signed() {
        return _id>-1;
    }
    @Override
    public IUser renameAs(String newName) {
        if (_parent.findByName(newName) > -1) {
            console.log("    can't rename \"%s\" to \"%s\"", _name, newName);
            return this;
        }

        console.log("    [%s].rename to \"%s\"", _name, newName);
        _name = newName;
        return this;
    }
    @Override
    public IUser follow(String followee) {
        if (_id == -1) {
            console.log("    [fake].follow");
            return this;
        }
        int i = _parent.findByName(followee);
        if (i==-1) {
            console.log("    [not a user].follow");
            return this;
        }
        IUser u = _parent.getUser(i);
        console.log("    [%s].follow %s", _name, followee);
        u.getFollowers().subscribe(this);
        return this;
    }
    
    @Override
    public IUser follow(IUser followee) {
        if (_id == -1) {
            console.log("    [fake].follow");
            return this;
        }
        int i = _parent.findByObject(followee);
        if (i==-1) {
            console.log("    [not a user].follow");
            return this;
        }
        console.log("    [%s].follow %s", _name, followee.getName());
        followee.getFollowers().subscribe(this);
        return this;
    }

    @Override
    public IUser unfollow(String unfollowee) {
        if (_id == -1) {
            console.log("    [fake].unfollow");
            return this;
        }
        int i = _parent.findByName(unfollowee);
        if (i==-1) {
            console.log("    [not a user].unfollow");
            return this;
        }
        IUser u = _parent.getUser(i);
        console.log("    [%s].unfollow %s", _name, unfollowee);
        u.getFollowers().unsubscribe(this);
        return this;
    }

    @Override
    public IUser unfollow(IUser unfollowee) {
        if (_id == -1) {
            console.log("    [fake].unfollow");
            return this;
        }
        int i = _parent.findByObject(unfollowee);
        if (i == -1) {
            console.log("    [not a user].unfollow");
            return this;
        }
        console.log("    [%s].unfollow %s", _name, unfollowee.getName());
        unfollowee.getFollowers().unsubscribe(this);
        return this;
    }
    @Override
    public IUser mute(String muteeName) {
        if (_id == -1) {
            console.log("    [fake].mute");
            return this;
        }
        int i = _parent.findByName(muteeName);
        if (i==-1) {
            console.log("    [not a user].mute");
            return this;
        }
        _muted.mute((T)_parent.getUser(i));
        return this;
    }
    @Override
    public IUser mute(IUser mutee) {
        if (_id == -1) {
            console.log("    [fake].mute");
            return this;
        }
        int i = _parent.findByObject(mutee);
        if (i == -1) {
            console.log("    [not a user].mute");
            return this;
        }
        _muted.mute((T)mutee);
        return this;
    }
    @Override
    public IUser unmute(String muteeName) {

        if (_id == -1) {
            console.log("    [fake].unmute");
            return this;
        }
        int i = _parent.findByName(muteeName);
        if (i==-1) {
            console.log("    [not a user].unmute");
            return this;
        }
        IUser u = _parent.getUser(i);
        _muted.unmute((T)u);
        _received.unmute(u.getId());
        return this;
    }
    @Override
    public IUser unmute(IUser mutee) {
        if (_id == -1) {
            console.log("    [fake].unmute");
            return this;
        }
        int i = _parent.findByObject(mutee);
        if (i == -1) {
            console.log("    [not a user].unmute");
            return this;
        }
        _muted.unmute((T)mutee);
        _received.unmute(mutee.getId());
        return this;
    }
    @Override
    public IUser publish(String message) {
        if (hasFollowers()) {
            Message m = new Message(message, _name ,_id );
            console.log("    [%s].publish(%s)",_name, m.toString());
            _sent.add(m, false);
            getFollowers().onNewMessage(this, m);
        }
        return this;
    }

    @Override
    public void newMessage(T sender, Message message) {
        console.log("    [%s] has received message from %s",_name, console.user(sender).getName());
        console.log(message.toString());
        if (_muted.isMuted(sender)) {
            console.log("    The user is muted and the message will be hidden");
        }
        console.log(message.toString());
        _received.add(message, _muted.isMuted(sender));
    }


    public String querySent(int count) {
        String[] s = queryResult(_sent.getMessages(count));
        String r ="";
        for (String value : s) {
            r = String.format("%s\n%s", r, value);
        }
        return r;
    }

    public String queryReceived(int count) {
        String[] s = queryResult(_received.getMessages(count));
        String r ="";
        for (String value : s) {
            r = String.format("%s\n%s", r, value);
        }
        return r;
    }

    private String[] queryResult(Message[] m) {
        String[] s = new String[m.length];
        for (int i = 0; i< m.length; i++){
            s[i] = formatMessage(m[i]);
        }
        return s;
    }

    private String formatMessage(Message m) {
        // Sender   Signature   Date    Message
        return String.format("%s\t%s\t%ts\t%s",
                _parent.getUser(m.getId()).getName(),
                m.getSignature(),
                m.getDate(),
                m.getMessage()
                );
    }
}

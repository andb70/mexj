package it.biasutti.mexj;

public class User implements IUser<FollowList, MessageList> , IListener<IUser, Message> {

    private String _name;
    private int _id;
    private IUsers<IUser> _parent;
    private FollowList<IUser, Message> _followers;
    private MessageList<Message> _sent;
    private MessageList<Message> _received;
    private MuteList<IUser> _muted;

    User(String userName, Users parent, int id) {
        _name = userName;
        _parent = parent;
        _id = id;
        _followers = new FollowList<IUser, Message>();
        _sent = new MessageList<Message>();
        _received = new MessageList<Message>();
        _muted = new MuteList<IUser>();

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
        return this.follow(_parent.getUser(followee));
    }
    
    @Override
    public IUser follow(IUser followee) {
        if (_id == -1) {
            console.log("    [fake].follow");
            return this;
        }
        int i = _parent.findByObject(followee);
        if (i==-1) {
            console.log("    follow[not a user]");
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
            console.log("    unfollow[not a user]");
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
            console.log("    unfollow[not a user]");
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
            console.log("    mute[not a user]");
            return this;
        }
        _muted.mute(_parent.getUser(i));
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
            console.log("    mute[not a user]");
            return this;
        }
        _muted.mute(mutee);
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
            console.log("    unmute[not a user]");
            return this;
        }
        IUser u = _parent.getUser(i);
        _muted.unmute(u);
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
            console.log("    unmute[not a user]");
            return this;
        }
        _muted.unmute(mutee);
        _received.unmute(mutee.getId());
        return this;
    }
    @Override
    public IUser publish(String message) {
        if (hasFollowers()) {
            Message m = new Message(message, _name ,_id );
            console.log("    [%s].publish \"%s\"",_name, message);
            _sent.add(m, false);
            getFollowers().onNewMessage(this, m);
        }
        return this;
    }

    @Override
    public void newMessage(IUser sender, Message message) {
        console.log("    [%s] has received message from %s",_name, console.user(sender).getName());
        //console.log(message.toString());
        if (_muted.isMuted(sender)) {
            console.log("    The user %s is muted and the message will be hidden", console.user(sender).getName());
        }
        console.log("    Message: %s", message.toString());
        _received.add(message, _muted.isMuted(sender));
    }


    public String querySent(int count) {
        return queryResult(_sent.getMessages(count));
    }

    public String queryReceived(int count) {
        return queryResult(_received.getMessages(count));
    }

    private String queryResult(Message[] messages) {
        String s ="";
        String z ="";
        for (Message message : messages) {
            s = String.format("%s%s%s\t%s\t%ts\t%s", s, z,
                    _parent.getUser(message.getId()).getName(),
                    message.getSignature(),
                    message.getDate(),
                    message.getMessage());
            z = "\n";
        }
        return s;
    }

}

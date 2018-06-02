package it.biasutti.mexj;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class User implements IUser<Message>, IListener<IUser, Message> {

    private String _name;
    private int _id;
    private IUsers<IUser, Message> _parent;
    private List<Message> _sent;
    private List<Message> _received;

    User(String userName, Users parent, int id) {
        _name = userName;
        _parent = parent;
        _id = id;
        _sent = new ArrayList<Message>();
        _received = new ArrayList<Message>();

        //console.log("        user (%d) created with name \"%s\".", _id, _name);
    }

    @Override
    public String toString() {
        return String.format("(%d) %s",_id,_name);
    }

    @Override
    public String listFollowees() {
        StringBuilder sb = new StringBuilder();
        Map<IUser, Integer> followes = _parent.getBroker().getFollowees( this);
        followes.forEach((user, lastMessage) ->{
            String s = String.format("\t\t%s %s\n",user.getName(), (lastMessage<0) ? "": "MUTED");
            sb.append(s);
        });
        return sb.toString();
    }

    @Override
    public String listFollowers() {
        if (!hasFollowers()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        getFollowers().forEach((user, lastMessage) ->{
            String s = String.format("\t\t%s %d\n",user.getName(), lastMessage);
            sb.append(s);
        });
        return sb.toString();
    }

    @Override
    public Map<IUser, Integer> getFollowers() {
        return _parent.getBroker().getFollowers(this);
    }

    private boolean hasFollowers() {
        return (getFollowers()!=null);
    }

    @Override
    public List<Message> getSentMessages() {
        return _sent;
    }

    @Override
    public List<Message> getReceivedMessages() {
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
        return _id > -1;
    }

    @Override
    public IUser renameAs(String newName) {
        if (newName.compareTo(_name)== 0){
            return this;
        }
        if (_parent.findByName(newName) > -1) {
            console.log("    can't rename \"%s\" to \"%s\"", _name, newName);
            return null;
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
        if (this == followee) {
            return null;
        }
        if (followee == null) {
            return null;
        }
        console.log("    [%s].follow %s", _name, followee.getName());
        return _parent.getBroker().subscribe(this, followee);
    }

    @Override
    public IUser unfollow(String followee) {
        return this.unfollow(_parent.getUser(followee));
    }

    @Override
    public IUser unfollow(IUser followee) {
        if (this == followee) {
            return null;
        }
        console.log("    [%s].unfollow %s", _name, followee.getName());
        return _parent.getBroker().unsubscribe(this, followee);
    }

    @Override
    public IUser mute(String muteeName) {
        return this.mute(_parent.getUser(muteeName));
    }

    @Override
    public IUser mute(IUser mutee) {
        if (this == mutee) {
            return null;
        }
        console.log("    [%s].mute %s", _name, mutee.getName());
        return _parent.getBroker().onMute(this, mutee);
    }

    @Override
    public IUser unmute(String muteeName) {
        return this.unmute(_parent.getUser(muteeName));
    }

    @Override
    public IUser unmute(IUser mutee) {
        if (this == mutee) {
            return null;
        }
        console.log("    [%s].unmute %s", _name, mutee.getName());
        _parent.getBroker().onUnmute(this, mutee);
        return this;
    }

    @Override
    public IUser publish(String message) {
        if (hasFollowers()) {
            Message m = new Message(message, _name, _id);
            console.log("    [%s].publish \"%s\"", _name, message);
            _sent.add(m);

            return _parent.getBroker().onNewMessage(this, m);
        }
        return null;
    }


    @Override
    public void newMessage(IUser sender, Message message) {
        console.log("    [%s] has received message from %s", _name, console.user(sender).getName());
        console.log("    Message: %s", message.toString());
        _received.add(message);
    }


    public String querySent(int count) {
        console.log("    [%s].query sent %s ", _name, count);
        return queryResult( _sent);
    }

    public String queryReceived(int count) {
        console.log("    [%s].query received %s ", _name, count);
        return queryResult(_received);
    }

    private String queryResult(List<Message> messages) {
        String s = "";
        String z = "";
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

package it.biasutti.mexj;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class User implements IUser<Message>, IListener<IUser, Message> {

    private String _name;
    private int _id;
    private IUsers<IUser> _parent;
    private List<Message> _sent;
    private List<Message> _received;

    User(String userName, Users parent, int id) {
        _name = userName;
        _parent = parent;
        _id = id;
        _sent = new ArrayList<Message>();
        _received = new ArrayList<Message>();

        console.log("        user (%d) created with name \"%s\".", _id, _name);
    }

    @Override
    public Map<IUser, Integer> getFollowers() {
        return _parent.getBroker().getFollowers(this);
    }

    private boolean hasFollowers() {
        return (getFollowers().size() > 0);
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
        return (IUser) _parent.getBroker().subscribe(this, followee);
    }

    @Override
    public IUser unfollow(String followee) {
        return this.unfollow(_parent.getUser(followee));
    }

    @Override
    public IUser unfollow(IUser followee) {
        return (IUser) _parent.getBroker().unsubscribe(this, followee);
    }

    @Override
    public IUser mute(String muteeName) {
        return this.mute(_parent.getUser(muteeName));
    }

    @Override
    public IUser mute(IUser mutee) {
        return (IUser) _parent.getBroker().onMute(this, mutee);
    }

    @Override
    public IUser unmute(String muteeName) {
        return this.unmute(_parent.getUser(muteeName));
    }

    @Override
    public IUser unmute(IUser mutee) {
        _parent.getBroker().onUnmute(this, mutee);
        return this;
    }

    @Override
    public IUser publish(String message) {
        if (hasFollowers()) {
            Message m = new Message(message, _name, _id);
            console.log("    [%s].publish \"%s\"", _name, message);
            _sent.add(m);

            return (IUser)_parent.getBroker().onNewMessage(this, m);
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
        return queryResult((Message[]) _sent.toArray());
    }

    public String queryReceived(int count) {
        return queryResult((Message[]) _sent.toArray());
    }

    private String queryResult(Message[] messages) {
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

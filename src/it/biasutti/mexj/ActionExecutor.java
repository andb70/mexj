package it.biasutti.mexj;

public class ActionExecutor implements IActionExecutor<Action> {
    protected IUsers<IUser, Message> _server;

    ActionExecutor(IUsers server) {
        _server = server;
    }

    @Override
    public boolean signUp(Action action) {
        return (_server.signUp(action.getUserName())!=null);
    }

    @Override
    public boolean rename(Action action) {
        return (_server.getUser(action.getUserName()).renameAs(action.getDest())!=null);
    }

    @Override
    public boolean follow(Action action) {
        return (_server.getUser(action.getUserName()).follow(action.getDest())!=null);
    }

    @Override
    public boolean unfollow(Action action) {
        return (_server.getUser(action.getUserName()).unfollow(action.getDest())!=null);
    }

    @Override
    public boolean mute(Action action) {
        return (_server.getUser(action.getUserName()).mute(action.getDest())!=null);
    }

    @Override
    public boolean unmute(Action action) {
        return (_server.getUser(action.getUserName()).unmute(action.getDest())!=null);
    }

    @Override
    public boolean publish(Action action) {
        return (_server.getUser(action.getUserName()).publish(action.getMessage())!=null);
    }

    @Override
    public boolean querySent(Action action) {
        String s =_server.getUser(action.getUserName()).querySent(action.getCount());
        console.log(s);
        return (s.length()>0);
    }

    @Override
    public boolean queryReceived(Action action) {
        String s =_server.getUser(action.getUserName()).queryReceived(action.getCount());
        console.log(s);
        return (s.length()>0);
    }
}

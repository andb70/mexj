package it.biasutti.mexj;

public class ActionExecutor implements IActionExecutor<Action> {
    protected IUsers _server;

    ActionExecutor(IUsers server) {
        _server = server;
    }

    @Override
    public boolean signUp(Action action) {
        return _server.signUp(action.getUserName()).signed();
    }

    @Override
    public boolean rename(Action action) {
        return _server.getUser(action.getUserName()).renameAs(action.getDest()).signed();
    }

    @Override
    public boolean follow(Action action) {
        return _server.getUser(action.getUserName()).follow(action.getDest()).signed();
    }

    @Override
    public boolean unfollow(Action action) {
        return _server.getUser(action.getUserName()).unfollow(action.getDest()).signed();
    }

    @Override
    public boolean mute(Action action) {
        return _server.getUser(action.getUserName()).mute(action.getDest()).signed();
    }

    @Override
    public boolean unmute(Action action) {
        return _server.getUser(action.getUserName()).unmute(action.getDest()).signed();
    }

    @Override
    public boolean publish(Action action) {
        return _server.getUser(action.getUserName()).publish(action.getMessage()).signed();
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

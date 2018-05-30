package it.biasutti.mexj;

interface IActionExecutor<A> {
    boolean signUp(A action);
    boolean rename(A action);
    boolean follow(A action);
    boolean unfollow(A action);
    boolean mute(A action);
    boolean unmute(A action);
    boolean publish(A action);
    boolean querySent(A action);
    boolean queryReceived(A action);
}

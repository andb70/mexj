package it.biasutti.mexj;

interface IUser<T> {
    String getName();
    IUser renameAs(String newName);
    int getId();
    boolean signed();

    FollowList getFollowers();
    MessageList getSentMessages();
    MessageList getReceivedMessages();

    IUser follow(String followee);
    IUser unfollow(String followee);
    IUser follow(IUser followee);
    IUser unfollow(IUser followee);

    IUser mute(String muteeName);
    IUser unmute(String muteeName);
    IUser mute(IUser mutee);
    IUser unmute(IUser mutee);

    IUser publish(String message);

    String querySent(int count);
    String queryReceived(int count);

}

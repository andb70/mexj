package it.biasutti.mexj;
import java.util.List;
import java.util.Map;
interface IUser<M> {
    String getName();
    IUser renameAs(String newName);
    int getId();
    boolean signed();

    Map<IUser, Integer> getFollowers();
    List<M> getSentMessages();
    List<M> getReceivedMessages();

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

    String listFollowees();
    String listFollowers();

}

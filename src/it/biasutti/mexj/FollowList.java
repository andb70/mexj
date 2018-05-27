package it.biasutti.mexj;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class FollowList implements Iterable{
    protected Hashtable<String, User> _followers;

    public FollowList() {
        _followers = new Hashtable<String, User>();
    }

    public int count() {
        return _followers.size();
    }
    public void add(User follower) {
        if (!_followers.contains(follower)) {
            _followers.put(follower.getUserName(), follower);
        }
    }
    public void remove(User follower) {
        if (_followers.contains(follower)) {
            _followers.remove(follower.getUserName());
        }
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer action) {

    }

    @Override
    public Spliterator spliterator() {
        return null;
    }
}

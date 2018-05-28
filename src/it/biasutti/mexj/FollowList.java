package it.biasutti.mexj;


public class FollowList<T> extends AbstractList<T> implements IPublisher<T>{

    @Override
    public boolean subscribe(T user) {
        if (findByObject(user) > -1){
            return false;
        }
        console.log("subscribe da %s",console.user(user).getName());
        _items.add(user);
        return true;
    }

    @Override
    public boolean unsubscribe(T user) {
        int i = findByObject(user);
        if (i<0) {
            return false;
        }
        console.log("unsubscribe da %s",console.user(user).getName());
        _items.remove(i);
        return true;
    }

    @Override
    public void onNewMessage(T sender, Message message) {
        /**
         * TODO: correggere i riferimenti e usare la lambda
         _items.forEach(
         (IListener)listener -> listener.newMessage(sender, message)
         );
         */
        for (int i = 0; i< _items.size(); i++) {
            listener(i).newMessage(sender, message);
        }
    }
    private IListener listener(int i) {
        return (IListener)_items.get(i);
    }
}

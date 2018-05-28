package it.biasutti.mexj;


public class MuteList<T> extends AbstractList<T> implements IMute<T>{
    @Override
    public boolean isMuted(T user) {
        return super.findByObject(user) > -1;
    }

    @Override
    public void mute(T user) {
        if (!isMuted(user)) {
            _items.add(user);
        }
    }

    @Override
    public void unmute(T user) {
        if (isMuted(user)) {
            _items.remove(user);
        }
    }
}

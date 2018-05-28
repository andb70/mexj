package it.biasutti.mexj;

public class Users<T> extends AbstractList<T> implements IUsers<T> {


    /**
     * se in _items NON esiste la chiave userName
     * crea un nuovo User con chiave = userName
     * <p>
     * restituisce lo User con chiave = userName
     */
    public IUser signUp(String userName) {
        int i = findByName(userName);

        if (i > -1) {
            console.log("    [%s] already signed", userName);
            return _item(i);
        }

        IUser u = new User(userName, this, _items.size());
        _items.add((T) u);
        console.log("    [%s].signup", userName);
        return u;
    }

    public IUser getUser(String userName) {
        int i = findByName(userName);
        if (i > -1) {
            return _item(i);
        }
        console.log("    user %s not present", userName);
        return fakeUser();
    }

    public IUser getUser(int id) {
        if (id > -1 && id < _items.size()) {
            return (IUser) _items.get(id);
        }
        console.log("    user with id=%d not present", id);
        return fakeUser();
    }

    @Override
    public int findByObject(T item) {
        return super.findByObject(item);
    }

    public int findByName(String userName) {
        for (int i = 0; i < _items.size(); i++) {
            if (userName.compareTo(_item(i).getName())==0) {
                return i;
            }
        }
        return -1;
    }
    private IUser fakeUser() {
        console.log("    fake user created");
        return new User("", this, -1);
    }


    private IUser _item(int i) {
        return (IUser) _items.get(i);
    }
}

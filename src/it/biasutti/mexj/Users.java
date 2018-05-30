package it.biasutti.mexj;
public class Users extends AbstractList<IUser> implements IUsers<IUser> {

    private IUser _fakeUser;

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
            return _items.get(i);
        }

        IUser u = new User(userName, this, _items.size());
        _items.add(u);
        it.biasutti.mexj.console.log("    [%s].signup", userName);
        return u;
    }

    /**
     * per evitare che la chiamata a getUser provochi una
     * NullPointerException quando l'utente richiesto non esiste
     * viene restituito comunque un utente fake,
     * è responsabilità del chiamante annullare le cui operazioni
     * che vengono compiute dal fakeUser
     */
    public IUser getUser(String userName) {
        int i = findByName(userName);
        if (i < 0) {
            console.log("    user %s not present", userName);
            return fakeUser();
        }
        return _items.get(i);
    }

    public IUser getUser(int id) {
        if (id < 0 || id > _items.size() - 1) {
            console.log("    user with id=%d not present", id);
            return fakeUser();
        }
        return _items.get(id);
    }

    private IUser fakeUser() {
        if (_fakeUser == null) {
            _fakeUser = new User("", this, -1);
            console.log("    fake user created");
        }
        console.log("    using fake user");
        return _fakeUser;
    }

    public int findByName(String userName) {
        for (int i = 0; i < _items.size(); i++) {
            if (userName.compareTo(_items.get(i).getName()) == 0) {
                return i;
            }
        }
        return -1;
    }
}

package it.biasutti.mexj;
public class Users extends AbstractList<IUser> implements IUsers<IUser> {
    private IPublisher<IUser, Message> _broker;


    public Users(IPublisher<IUser, Message> broker) {
        _broker = broker;
    }


    @Override
    public IPublisher getBroker() {
        return _broker;
    }

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
        console.log("    [%s].signup", userName);
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
            return null;
        }
        return _items.get(i);
    }

    public IUser getUser(int id) {
        if (id < 0 || id > _items.size() - 1) {
            console.log("    user with id=%d not present", id);
            return null;
        }
        return _items.get(id);
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

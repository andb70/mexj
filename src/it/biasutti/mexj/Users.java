package it.biasutti.mexj;
import java.util.Hashtable;

public class Users implements IUsers{
    protected Hashtable<String, User> _users;

    public Users() {
        _users = new Hashtable<String, User>();
    }

    public int count() {
        return _users.size();
    }

    /**
     * se in _users NON esiste la chiave userName
     *  crea un nuovo User con chiave = userName
     *
     * restituisce lo User con chiave = userName
     * */
    public User signUp(String userName) {
        console.log("    user signUp: " + userName);
        User u = getUser(userName);
        if (u == null) {
            u = new User(userName, this);
            _users.put(userName, u);
            console.log("    user created: " + userName);
        }
        return u;
    }

    public User getUser(String userName) {
        return _users.get(userName);
    }
    /**
     * se in _users NON esiste la chiave newName
     *  sostituisce la chiave di user con la chiave newName
     *  restituisce user
     *
     * altrimenti
     *  restituisce null
     *
     * */
    public User rename(User user, String newName) {
        // esiste user?
        if (getUser(user.getUserName()) == null) {
            return null;
        }
        // newName è libero?
        if (getUser(newName) != null) {
            return null;
        }
        _users.remove(user.getUserName());
        _users.put(newName, user);
        return user;
    }

    @Override
    public Message publish(User publisher, String message) {
        console.log("L'utente %s pubblica il messaggio %s", publisher.getUserName(), message.toString());
        return null;
    }
}

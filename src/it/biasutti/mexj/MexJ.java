package it.biasutti.mexj;

public class MexJ{
    Users _users;
    public MexJ() {
        _users = new Users();
    }

    public Users users() {
        return _users;
    }
    public User users(String userName) {
        User u = _users.getUser(userName);
        if (u == null) {
            console.log(userName + " non è un nome di utente valido");
            return new User("", _users);
        }
        return u;
     }


/*
    public User follow(User follower, User followee) {
        return null;
    }
*/


/*
*
*
*
*  public User signUp(String userName) {
        return null;
    }
    public User rename(String userName, String newName) {
        return null;
    }
    public User mute(String followerName, String followedName) {
        return null;
    }
    public User unMute(String followerName, String followedName) {
        return null;
    }
    public User publish(String userName, String sendMessage) {
        return null;
    }
    public Message[] queryFrom(String userName, int messages) {
        return null;
    }
    public Message[] queryTo(String userName, int messages) {
        return null;
    }



    */
}

package it.biasutti.mexj;

interface IUsers <T>{

    IUser signUp(String userName);
    IUser getUser(int id);
    IUser getUser(String userName);
    int findByName(String userName);
    int findByObject(T item);
    int size();
    /*
    User rename(User user, String newName);
    Message publish(User publisher, String message) ;

    User unFollow(String userName) ;
    Message[] queryFrom(int count) ;
    Message[] queryTo(int count) ;
    Query query (int count) ;*/

}

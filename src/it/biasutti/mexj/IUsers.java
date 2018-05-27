package it.biasutti.mexj;

interface IUsers {
    int count();

    User signUp(String userName);
    User getUser(String userName);
    User rename(User user, String newName);
    Message publish(User publisher, String message) ;

    /*

    User unFollow(String userName) ;
    Message[] queryFrom(int count) ;
    Message[] queryTo(int count) ;
    Query query (int count) ;*/

}

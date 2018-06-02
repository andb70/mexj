package it.biasutti.mexj;

interface IUsers <T, M>{
    IPublisher<T, M> getBroker();
    T signUp(String userName);
    T getUser(int id);
    T getUser(String userName);
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

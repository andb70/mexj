package it.biasutti.mexj;

public class Query {
    int _count;

    public Query(int count) {
        _count = count;
    }

    public Query from(String userName) {
        return this;
    }

    public Query to(String userName) {
        return this;
    }

    public Message[] result() {
        return null;
    }
}

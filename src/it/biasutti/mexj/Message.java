package it.biasutti.mexj;

import java.util.Date;

public class Message {
    String _message;
    String _signature;
    Date _date;
    int _id;
    public Message (String message, String signature, int id) {
        _message = message;
        _signature = signature;
        _date = new Date();
        _id = id;
    }

    @Override
    public String toString() {
        return String.format("received: %t\nfrom: %s (id: %i)\n%s",_date, _signature, _id, _message);
    }
}

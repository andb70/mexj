package it.biasutti.mexj;

import java.util.Date;

public class Message {
    private String _message;
    private String _signature;
    private Date _date;
    private int _id;
    public Message (String message, String signature, int id) {
        this(message,signature, id, new Date());
    }

    public Message (String message, String signature, int id, Date date) {
        _message = message;
        _signature = signature;
        _date = date;
        _id = id;
    }
    public int getId() {
        return _id;
    }
    public String getSignature() {
        return _signature;
    }
    public Date getDate() {
        return _date;
    }
    public String getMessage() {
        return _message;
    }

    @Override
    public String toString() {
        return String.format("  received: %tT\t  from: %s (id: %d)\t  %s",_date, _signature, _id, _message);
    }
}

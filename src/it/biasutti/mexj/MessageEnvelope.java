package it.biasutti.mexj;

public class MessageEnvelope {
    private Message _message;
    private boolean _muted;
    MessageEnvelope(Message message, boolean muted) {
        _message = message;
        _muted = muted;
    }

    public boolean isMuted() {
        return _muted;
    }
    public void unmute() {
        _muted = false;
    }
    public Message getMessage() {
        return _message;
    }
}

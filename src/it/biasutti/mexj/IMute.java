package it.biasutti.mexj;

interface IMute <T> {

    boolean isMuted(T user);
    void mute(T user);
    void unmute(T user);
}

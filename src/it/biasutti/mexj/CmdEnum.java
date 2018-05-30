package it.biasutti.mexj;

public enum CmdEnum implements IEnum{
    INVALID("?"),
    SIGN_UP("SIGN-UP"),
    RENAME("RENAME"),
    FOLLOW("FOLLOW"),
    UNFOLLOW("UNFOLLOW"),
    MUTE("MUTE"),
    UNMUTE("UNMUTE"),
    MESSAGE("MESSAGE"),
    QUERYRECEIVED("QUERY-R"),
    QUERYSENT("QUERY-Q");

    private String value;

    private CmdEnum(String s) {
        value = s;
    }

    public String getValue() {
        return value;
    }

    public CmdEnum eval(String s) {
        for (CmdEnum c : this.getClass().getEnumConstants()) {
            if (c.value.equals(s)) {
                return c;
            }
        }
        return this;
    }
}

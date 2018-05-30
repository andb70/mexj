package it.biasutti.mexj;

/**
 * Enumeratore per la selezione del tipo di file
 *
 * esempio d'uso:
 * ActionTypeEnum mioEnum;
 *
 * mioEnum = (ActionTypeEnum.INVALID).eval("Ciao");
 * restituisce INVALID
 *
 * mioEnum = (ActionTypeEnum.MESSAGE).eval("Ciao");
 * restituisce MESSAGE
 *
 * mioEnum = (ActionTypeEnum.INVALID).eval("A");
 * restituisce ADMINISTRATIVE
 *
 */
public enum ActionTypeEnum implements IEnum{
    INVALID("?"),
    ADMINISTRATIVE("A"),
    MESSAGE("M"),
    QUERYSENT("Q"),
    QUERYRECEIVED("R");

    private String value;

    private ActionTypeEnum(String s) {
        value = s;
    }

    public String getValue() {
        return value;
    }

    public ActionTypeEnum eval(String s) {
        for (ActionTypeEnum c : this.getClass().getEnumConstants()) {
            if (c.value.equals(s)) {
                return c;
            }
        }
        return this;
    }
}

package it.biasutti.mexj;


import java.util.Date;


public class Action {
    private Date _date;
    private String _userName;
    private int _count;
    private CmdEnum _cmd = CmdEnum.INVALID;
    private String _dest = "";
    private String _message = "";
    
    /**
     * Processa i parametri del costruttore fino ad ottenere un'azione
     * eseguibile in base al seguente schema:
     * 1. identitica il tipo di file sorgente
     * 2. in base al tipo tenta la decodifica degli argomenti
     * 3. finiti gli argomenti esce
     * 4. se i dati in ingresso non rispettano il paradigma promesso
     *    imposta       _cmd = INVALID
     *    e la successiva chiamata a .isValid() retituirà FALSE
     *    altrimenti    _cmd = [un comando valido]
     *    
     *  paradigmi possibili:
     *  Tipo di file
     *      param1  param2  ...     paramN
     *      
     *  A       Administrative
     *      Date    user    cmd             < SIGN-UP
     *      Date    user    cmd     dest
     *
     *  M       Message
     *      Date    user    msg
     *
     *  Q, R    Query
     *      user    count
     *
     *  Flusso decodifica:
     *  
     *  if (Q || R) {
     *      getUser
     *      getCount
     *      return
     *  }
     *
     *  getDate
     *  getUser
     *
     *  if (M) {
     *      getMsg
     *      return
     *  }
     *
     *  getCmd
     *  if (SIGN-UP)
     *      return
     *
     *  getDest
     *
     *
     */
    Action(String actionType, Object... args) {
        ActionTypeEnum aType;
        //aType = Util.validateType(actionType);
        aType = (ActionTypeEnum.INVALID).eval(actionType);

        if (aType == ActionTypeEnum.INVALID) {
            _cmd = CmdEnum.INVALID;
            return;
        }

        try {

            /*  Q, R    Query
             *      user    count             */
            if (aType == ActionTypeEnum.QUERYSENT || aType == ActionTypeEnum.QUERYRECEIVED) {
                _userName = Util.validateString(args[0]);
                _count = Util.validateNumber(args[1]);
                if(_count<0){
                    _cmd = CmdEnum.INVALID;
                    return;
                }
                if (aType == ActionTypeEnum.QUERYSENT) {
                    _cmd = CmdEnum.QUERYSENT;
                    return;
                }
                _cmd = CmdEnum.QUERYRECEIVED;
                return;
            }

            /*  M       Message
             *      Date    user    msg             */
            _date = Util.validateDate(args[0]);
            _userName = Util.validateString(args[1]);
            if (aType == ActionTypeEnum.MESSAGE) {
                _cmd = CmdEnum.MESSAGE;
                _message = Util.validateString(args[2]);
                return;
            }

            /*  A       Administrative
             *      Date    user    cmd             < SIGN-UP
             *      Date    user    cmd     dest             */
            //_cmd = Util.validateCmd(args[2]);
            _cmd = (CmdEnum.INVALID).eval((String)args[2]);
            if (_cmd == CmdEnum.SIGN_UP || _cmd == CmdEnum.INVALID) {
                return;
            }
            _dest = Util.validateString(args[3]);

        } catch (Exception e) {
            console.log("Action: generic ", e);
            _cmd = CmdEnum.INVALID;
        }

    }

    public boolean performBy(IActionExecutor executor) {

        switch (_cmd){
            case SIGN_UP:
                return executor.signUp(this);
            case RENAME:
                return executor.rename(this);
            case FOLLOW:
                return executor.follow(this);
            case UNFOLLOW:
                return executor.unfollow(this);
            case MUTE:
                return executor.mute(this);
            case UNMUTE:
                return executor.unmute(this);
            case MESSAGE:
                return executor.publish(this);
            case QUERYRECEIVED:
                return executor.queryReceived(this);
            case QUERYSENT:
                return executor.querySent(this);
            default:
                return false;
        }
    }
    public boolean isValid() {
        return _cmd != CmdEnum.INVALID;
    }

    public CmdEnum getCmd() {
        return _cmd;
    }

    public Date getDate() {
        return _date;
    }

    public String getUserName() {
        return _userName;
    }

    public int getCount() {
        return _count;
    }

    public String getDest() {
        return _dest;
    }

    public String getMessage() {
        return _message;
    }

}

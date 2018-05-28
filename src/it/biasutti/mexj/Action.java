package it.biasutti.mexj;


import java.util.Date;


public class Action {
    private int _type;
    private Date _date;
    private String _userName;
    private int _count;
    private CmdEnum _cmd = CmdEnum.INVALID;
    private String _dest = "";
    private String _message = "";

    Action(String fileName, Object... args) {

        _type = Util.validateType(fileName);
        _date = Util.validateDate(args[0]);
        _userName = (String) args[1];
        _count = Util.validateNumber(args[1]);
        try {
        switch (args.length) {
            case 4: //  RENAME FOLLOW UNFOLLOW MUTE UNMUTE
                //  Type    Date    User    Cmd     Dest
                _cmd = Util.validateCmd(args[2]);
                if (_type != 0 || _cmd == CmdEnum.INVALID) {
                    _cmd = CmdEnum.INVALID;
                    return;
                }
                _dest = (String) args[3];
                break;
            case 3: //  SIGN_UP MESSAGE
                //  Type    Date    User    Cmd
                //  Type    Date    User    Message

                _cmd = Util.validateCmd(args[2]);
                if (_type == 0 && _cmd == CmdEnum.SIGN_UP) {
                    // format Sign up
                    _dest = "";
                } else if (_type == 1) {
                    // format message
                    _cmd = CmdEnum.MESSAGE;
                    _message = (String) args[2];
                } else {
                    _cmd = CmdEnum.INVALID;
                    return;
                }

                break;
            case 2: //  QUERY
                //  Type    User    Count

                _userName = (String) args[0];
                if (_type == 2) {
                    _cmd = CmdEnum.QUERYFROM;
                    _dest = "";
                } else if (_type == 3) {
                    _cmd = CmdEnum.QUERYTO;
                    _dest = "";
                } else {
                    _cmd = CmdEnum.INVALID;
                    return;
                }
                break;
            default:
        }
        } catch (Exception e) {
            console.log("Action: generic " , e);
        }

    }


    public boolean isValid() {
        return _cmd!=CmdEnum.INVALID;
    }
    public CmdEnum getCmd() {
        return _cmd;
    }

    public int getType() {
        return _type;
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

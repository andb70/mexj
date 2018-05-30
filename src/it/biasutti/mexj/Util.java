package it.biasutti.mexj;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    public static String validateString(Object arg) {
        try {
            return (String) arg;
        } catch (Exception e) {
            // console.log("Action validateString", e);
        }
        return "";
    }

    public static int validateNumber(Object arg) {
        try {
            return Integer.parseInt((String) arg);
        } catch (Exception e) {
            // console.log("Action validateNumber", e);
        }
        return -1;
    }

    public static CmdEnum validateCmd(Object arg) {
        if (((String) arg).contentEquals("SIGN-UP")) {
            return CmdEnum.SIGN_UP;
        }
        if (((String) arg).contentEquals("RENAME")) {
            return CmdEnum.RENAME;
        }
        if (((String) arg).contentEquals("FOLLOW")) {
            return CmdEnum.FOLLOW;
        }
        if (((String) arg).contentEquals("UNFOLLOW")) {
            return CmdEnum.UNFOLLOW;
        }
        if (((String) arg).contentEquals("MUTE")) {
            return CmdEnum.MUTE;
        }
        if (((String) arg).contentEquals("UNMUTE")) {
            return CmdEnum.UNMUTE;
        }
        return CmdEnum.INVALID;
    }

    public static ActionTypeEnum validateType(Object arg) {
        if (((String) arg).startsWith("A")) {
            return ActionTypeEnum.ADMINISTRATIVE;
        }
        if (((String) arg).startsWith("M")) {
            return ActionTypeEnum.MESSAGE;
        }
        if (((String) arg).startsWith("Q")) {
            return ActionTypeEnum.QUERYSENT;
        }
        if (((String) arg).startsWith("R")) {
            return ActionTypeEnum.QUERYRECEIVED;
        }
        return ActionTypeEnum.INVALID;
    }

    public static Date validateDate(Object arg) {
        return Util.validateDate("yyy-MM-dd HH:mm:ss", arg);
    }

    public static Date validateDate(String format, Object arg) {
        String input = (String) arg;
        SimpleDateFormat ft = new SimpleDateFormat(format);
        Date t;
        try {
            t = ft.parse(input);
            //console.log(t);
            //console.log(ft.format(t));
        } catch (ParseException e) {
            //console.log("UvalidateDate: nparseable using " + ft);
            t = new Date();
        } catch (Exception e) {
            console.log("validateDate: generic " + ft, e);
            t = new Date();
        }
        return t;
    }
    public static String getFileName(String path){
        String[] f = path.split("/");
        if (f.length==0){
            console.log("nome di file non valido");
            return "";
        }
        return f[f.length-1];
    }
}

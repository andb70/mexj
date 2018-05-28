package it.biasutti.mexj;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
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

    public static int validateType(Object arg) {
        if (((String) arg).startsWith("A")) {
            return 0;
        }
        if (((String) arg).startsWith("M")) {
            return 1;
        }
        if (((String) arg).startsWith("Q")) {
            return 2;
        }
        if (((String) arg).startsWith("R")) {
            return 3;
        }
        return -1;
    }

    public static Date validateDate(Object arg) {
        String input = (String) arg;
        SimpleDateFormat ft = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        Date t;
        try {
            t = ft.parse(input);
            //console.log(t);
            //console.log(ft.format(t));
        } catch (ParseException e) {
            console.log("UvalidateDate: nparseable using " + ft);
            t = new Date();
        } catch (Exception e) {
            console.log("validateDate: generic " + ft, e);
            t = new Date();
        }
        return t;
    }

    public static String formatDate(String input) {
        SimpleDateFormat ft = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        Date t;
        String s;
        try {
            t = ft.parse(input);
            console.log(t);
            console.log(ft.format(t));
            s = ft.format(t);
        } catch (ParseException e) {
            console.log("Unparseable using " + ft);
            s = "";
        }
        return s;
    }
}

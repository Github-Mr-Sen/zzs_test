package cn.itcast.travel.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class  Utils {
    /*
    *
    *
    *判断字符串是否为空，或者空字符串
    * */
    public static boolean strIsnull(String string) {
        if (string == null||string.equalsIgnoreCase("null")||string.trim().length() ==0) {
            return true;
        }
        return false;
    }

    public static String getTimeNow(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:ss:mm");
        return formatter.format(LocalDateTime.now());
    }

    public Utils() {
    }
}

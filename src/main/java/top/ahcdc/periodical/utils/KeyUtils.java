package top.ahcdc.periodical.utils;

import java.util.Random;

public class KeyUtils {
    public static synchronized String createUniqueKey(){
        Random random = new Random();
        Integer key = random.nextInt(900000)+100000;
        return System.currentTimeMillis()+String.valueOf(key);
    }
}

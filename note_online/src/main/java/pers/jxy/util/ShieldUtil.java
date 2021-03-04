package pers.jxy.util;

import java.util.ArrayList;

/**
 * @author:靳新宇
 * @date : 02-03 10:21
 */
public class ShieldUtil {
    private static ArrayList<String> SHIELDS;

    private ShieldUtil(){

    }

    static {
        SHIELDS=new ArrayList<>();
        SHIELDS.add("色情片");
        SHIELDS.add("暴力");
        SHIELDS.add("血腥");
        SHIELDS.add("赌博");
        SHIELDS.add("黄片");
    }

    public static String detection(String text) {
        for (String word : SHIELDS) {
            text = text.replace(word, "******");
        }
        return text;
    }
}

package pers.jxy.util;

import java.io.*;
import java.util.ArrayList;

/**
 * @author:靳新宇
 * @date : 02-03 10:21
 */
public class ShieldUtil {
    public static ArrayList<String> SHIELDS = new ArrayList<>();

    private ShieldUtil() {

    }

    public static void initializeShield() {
        SHIELDS= new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/static/shieldWord.txt")));
            String shield = null;
            while ((shield = br.readLine()) != null) {
                SHIELDS.add(shield);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 返回0：失败
     * 返回1：已存在
     * 返回2：成功
     */
    public static Integer addShield(String shield) {
        PrintStream ps = null;
        try {
            if (SHIELDS.contains(shield)) {
                return 1;
            } else {
                ps = new PrintStream(new FileOutputStream("src/main/resources/static/shieldWord.txt", true));
                ps.println(shield);
                initializeShield();
                return 2;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return 0;
    }

    public static String detection(String text) {
        for (String word : SHIELDS) {
            text = text.replace(word, "******");
        }
        return text;
    }
}

package pers.jxy.util;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author:靳新宇
 * @date : 11-07 19:28
 */
public class NoteBookOnlineUtils {


    private NoteBookOnlineUtils() {
    }

    /**
     * 获取4位随机验证码
     */
    public static String getRandomStr(int nums) {
        String str = "";
        for (int j = 0; j < nums; j++) {
            str = str + getChar();
        }
        return str;
    }

    private static String getChar() {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ01234567890123456789";
        int num = (int) (0 + Math.random() * 72);
        return String.valueOf(str.charAt(num));
    }

    /**
     * SHA-256加密
     */
    public static String getSHA256Res(String str) {
        MessageDigest messageDigest;
        String res = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            res = byte2Hex(messageDigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    /**
     * 时间字符串转Date格式
     */
    public static Date strToDate(String strDate) {

        String FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";

        String[] REPLACE_STRING = new String[]{"GMT+0800", "GMT+08:00"};

        String SPLIT_STRING = "(中国标准时间)";

        try {
            strDate = strDate.split(Pattern.quote(SPLIT_STRING))[0].replace(REPLACE_STRING[0], REPLACE_STRING[1]);
            SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss z", Locale.US);
            Date date = sdf.parse(strDate);
            return date;
        } catch (Exception e) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = format.parse(strDate);
                return date;
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
            throw new RuntimeException("时间转化格式错误" + "[dateString=" + strDate + "]" + "[FORMAT_STRING=" + FORMAT_STRING + "]");
        }
    }

    /**
     * Util.Date -> Sql.Date
     */
    public static java.sql.Date utilDateToSql(Date date) {
        return new java.sql.Date(date.getTime());
    }

    /**
     * 将类别从数组转String
     */
    public static String getNoteBookType(String[] type) {
        String strType = "";
        if (type != null) {
            for (int i = 0; i < type.length; i++) {
                strType = strType + "-" + type[i] + "-";
            }
        }
        return strType;
    }

    /**
     * 将类别由String转数组
     */
    public static String[] getNoteBookTypeArr(String type) {
        String[] types = {};
        try {
            types = type.split("-");
            return types;
        } catch (Exception e) {
        } finally {
            return types;
        }
    }

    /**
     * 获取yyyy-MM-dd格式sql.Date
     */
    public static java.sql.Date getSqlDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date time = new Date();
        java.sql.Date date = new java.sql.Date(time.getTime());
        return date;
    }

    /**
     * 获取yyyy-MM-dd格式util.Date
     */
    public static Date getyMdDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        date = new Date(date.getTime());
        return date;
    }

    /**
     * 邮件发送
     */
    public static void sendMail(String title, String content, String email, JavaMailSenderImpl javaMailSender) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(title);
        message.setText(content);
        message.setTo(email);
        message.setFrom("1551903505@qq.com");
        javaMailSender.send(message);
    }

    /**
     * 获取近七天日期
     */
    public static ArrayList<Date> get7Date() {
        long l = System.currentTimeMillis();
        long m = 24 * 60 * 60 * 1000;
        long l2 = l - m;
        long l3 = l - m * 2;
        long l4 = l - m * 3;
        long l5 = l - m * 4;
        long l6 = l - m * 5;
        long l7 = l - m * 6;
        ArrayList<Date> dates = new ArrayList<>();
        dates.add(new Date(l));
        dates.add(new Date(l2));
        dates.add(new Date(l3));
        dates.add(new Date(l4));
        dates.add(new Date(l5));
        dates.add(new Date(l6));
        dates.add(new Date(l7));
        return dates;
    }

    public static List<String> get7yMdDate() {
        ArrayList<String> res = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Date> dates = get7Date();
        for (Date date : dates) {
            res.add(sdf.format(date));
        }
        Collections.reverse(res);
        return res;
    }

    public static List<String> get7MdDate() {
        ArrayList<String> res = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        ArrayList<Date> dates = get7Date();
        for (Date date : dates) {
            res.add(sdf.format(date));
        }
        Collections.reverse(res);
        return res;
    }

    /**
     * 获取今日日期
     */
    public static String getToday() {
        LocalDate now = LocalDate.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return df.format(now);
    }

    /**
     * 小数转百分数
     */
    public static String decToPro(Double d) {
        DecimalFormat df = new DecimalFormat("0.00%");
        return df.format(d);
    }

    /**
     * 获取当前时间
     */
    public static String getNow() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        return dateTimeFormatter.format(localDateTime);
    }

    /**
     * 上传图片
     */
    public static String uploadImg(MultipartFile file, Integer no, String path) {
        String filename = no + "-" + file.getOriginalFilename();
        try {
            File fileload = new File(path);
            if (!fileload.exists()) {
                fileload.mkdirs();
            }
            file.transferTo(new File(fileload, filename));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return filename;
        }
    }

    /**
     * 图片压缩
     */
    public static String imgZip(String path, String iden, String notePath) {
        UUID uuid = UUID.randomUUID();
        String newPath = "zip-" + uuid.toString() + ".jpg";
        try {
            File oldFile = new File(notePath + iden + "\\" + path);
            File newFile = new File(notePath + iden + "\\" + newPath);
            Thumbnails.of(oldFile).size(120, 120).toFile(newFile);
            oldFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return newPath;
        }
    }

    /**
     * 获取图片二进制文件
     */
    public static byte[] getImg(String path) {
        byte[] imageBytes = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(path));
            imageBytes = new byte[fileInputStream.available()];
            fileInputStream.read(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageBytes;
    }
}

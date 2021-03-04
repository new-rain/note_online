package pers.jxy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author:靳新宇
 * @date : 01-27 16:42
 */
@Data
@Getter
@Setter
@JsonIgnoreProperties(value = {"handler"})
public class Comment {
    private Integer cNo;
    private String cContent;
    private Integer cToNno;
    private Integer uNo;
    private String uName;
    private Integer toNo;
    private String toName;
    private Integer cType;
    private Date cTime;
    private String showTime;
    private List<Comment> comments;

    public void setcTime(Date cTime) {
        this.cTime = cTime;
        String s = cTime.toString();
        String year = s.substring(s.length() - 4, s.length());
        Calendar calendar = Calendar.getInstance();
        if (Integer.valueOf(year) == calendar.get(Calendar.YEAR)) {
            Format format = new SimpleDateFormat("MM-dd HH:mm:ss");
            this.showTime = format.format(cTime);
        } else {
            Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            this.showTime = format.format(cTime);
        }
    }
}

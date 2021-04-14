package pers.jxy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author:靳新宇
 * @date : 11-05 15:06
 */
@Data
@Setter
@Getter
@JsonIgnoreProperties(value = {"handler"})
public class User implements Serializable {
    private Integer no;
    private String password;
    private String salt;
    private String nickname;
    private String brief;
    private String email;
    private String createTime;
    private String name;
    private Date birthday;
    private Integer gender;
    private String job;
    private String address;
    private String nowAddress;
    private String headUrl;
    private Integer notebookNum;
    private Integer noteNum;
    private Integer getGoodNum;
    private Integer watchNum;
    private Integer getCommentNum;
    private Integer friendRe;
    private Integer hotNum;
    private Integer attentionNum;
    private byte[] headImg;

//    public void setHeadUrl(String headUrl) {
//        this.headUrl = headUrl;
//        this.headImg = NoteBookOnlineUtils.getImg("D:/note/user/" + headUrl);
//    }
}

package pers.jxy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author:靳新宇
 * @date : 01-05 16:05
 */
@Data
@Getter
@Setter
@JsonIgnoreProperties(value = {"handler"})
public class Message implements Serializable {
    private Integer mNo;
    private String mBody;
    private Integer mToWhoNo;
    private String listener;
    private Integer mWhoToNo;
    private String sender;
    private Boolean mState;
    private Date mTime;
    private Integer noteNo;
    private String noteName;
    private Integer mType;
}

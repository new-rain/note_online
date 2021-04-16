package pers.jxy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author:靳新宇
 * @date : 02-26 19:03
 */
@Data
@Setter
@Getter
@JsonIgnoreProperties(value = {"handler"})
public class Appeal implements Serializable {
    private Integer aNo;
    private Integer auNo;
    private String auName;
    private Integer anNo;
    private String anName;
    //申诉说明
    private String aBody;
    //申诉类型（被举报处理结果）
    private String aReason;
    //申诉结果类型
    private Boolean aType;
    //申诉结果（成功失败）
    private String ares;
    //被处理类型(文章要求修改还是被删除)
    private Integer adType;
    private String aTime;
    private String aaTime;
    private Boolean aState;
    private String admin;

    public void setaType(Boolean aType) {
        this.aType = aType;
        if(aType){
            this.ares="申诉成功";
        }else{
            this.ares="申诉失败";
        }
    }
}

package pers.jxy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author:靳新宇
 * @date : 02-26 19:03
 */
@Data
@Setter
@Getter
@JsonIgnoreProperties(value = {"handler"})
public class Appeal {
    private Integer aNo;
    private Integer auNo;
    private String auName;
    private Integer anNo;
    private String anName;
    //申诉说明
    private String aBody;
    //申诉类型（被举报处理结果）
    private String aReason;
    //申诉处理类型
    private Boolean aType;
    //被处理类型(文章要求修改还是被删除)
    private Integer adType;
    private String aTime;
    private String aaTime;
    private Boolean aState;
}

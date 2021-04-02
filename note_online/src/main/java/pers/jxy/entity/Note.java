package pers.jxy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author:靳新宇
 * @date : 11-30 07:24
 */
@Data
@Getter
@Setter
@JsonIgnoreProperties(value = {"handler"})
public class Note implements Serializable {
    private Integer nNo;
    private String nName;
    private String nBodyUrl;
    private String arthicle;
    private Integer nReadNum;
    private Integer nGoodNum;
    private Integer nCollectionNum;
    private Integer nCommentNum;
    private Integer nbNo;
    private Integer uNo;
    private String uName;
    private Boolean isGood;
    private Boolean isCollection;
    private String createTime;
    private Integer hotNum;
}

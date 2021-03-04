package pers.jxy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author:靳新宇
 * @date : 02-21 16:30
 */
@Data
@Getter
@Setter
@JsonIgnoreProperties(value = {"handler"})
public class Irregularity implements Serializable {
    private Integer iNo;
    private Integer iType;
    private String irType;
    private String iBody;
    private Integer irNo;
    private String irName;
    private Integer isNo;
    private String isName;
    private Integer inNo;
    private String inName;
    private Boolean iState;
    private Integer iResult;
    private String res;
    private String iTime;
    private String iaTime;

    /**
     * 笔记违规
     * 1.含有敏感词
     * 2.内容涉黄
     * 3.政治相关
     * 4.涉嫌广告
     * 5.内容侵权
     * 6.侮辱谩骂
     * 7.其他
     */
    public void setiType(Integer iType) {
        this.iType = iType;
        switch (iType) {
            case 1:
                this.irType = "含有敏感词";
                break;
            case 2:
                this.irType = "内容涉黄";
                break;
            case 3:
                this.irType = "政治相关";
                break;
            case 4:
                this.irType = "涉嫌广告";
                break;
            case 5:
                this.irType = "内容侵权";
                break;
            case 6:
                this.irType = "侮辱谩骂";
                break;
            default:
                this.irType = "其他";
        }
    }

    /**
     * 1.责令修改
     * 2.删除文章
     * 3.封禁帐号
     * 4.无违规
     */
    public void setiResult(Integer iResult) {
        this.iResult = iResult;
        switch (iResult) {
            case 1:
                this.res = "责令修改";
                break;
            case 2:
                this.res = "删除文章";
                break;
            case 3:
                this.res = "封禁帐号";
                break;
            case 4:
                this.res = "无违规";
                break;
            default:
                this.irType = "其他";
        }
    }
}

package pers.jxy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author:靳新宇
 * @date : 02-24 13:51
 */
@Data
@Setter
@Getter
@JsonIgnoreProperties(value = {"handler"})
public class Feedback implements Serializable {
    private Integer fNo;
    private Integer fType;
    private String feType;
    private String fBody;
    private Integer fuNo;
    private String fuName;
    private String fTime;
    private Boolean fState;

    public void setfType(Integer fType) {
        this.fType = fType;
        switch (fType) {
            case 1:
                this.feType = "系统错误";
                break;
            case 2:
                this.feType = "结果异常";
                break;
            case 3:
                this.feType = "体验建议";
                break;
            default:
                this.feType = "其他";
        }
    }
}

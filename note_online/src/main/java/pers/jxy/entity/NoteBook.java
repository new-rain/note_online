package pers.jxy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:靳新宇
 * @date : 11-08 21:01
 */
@Data
@Getter
@Setter
@JsonIgnoreProperties(value = {"handler"})
public class NoteBook implements Serializable {
    private Integer bNo;
    private String bName;
    private String uname;
    private String bDescribe;
    private String bType;
    private ArrayList<String> bTypes= new ArrayList<>();
    private Integer bPower;
    private String bCreateTime;
    private String bUpdateTime;
    private Integer bReadNum;
    private Integer bGoodNum;
    private Integer bCommentNum;
    private Integer bCollectionNum;
    private Integer bNoteNum;
    private Integer uNo;
    private String coverUrl;
    private List<Note> notes;
    private Boolean isGood;
    private Boolean isCollection;

    /**
     * 初始化setBType之后自动加载BTypes数组
     */
    public void setBType(String bType) {
        this.bType = bType;
        String[] split = bType.split("-");
        System.out.println(split);
        for (String str : split) {
            if (!(str.equals(""))) {
                this.bTypes.add(str);
            }
        }
    }

}

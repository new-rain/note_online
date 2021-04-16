package pers.jxy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author : 靳新宇
 * @date : 03-27 19:35
 */
@Data
@Setter
@Getter
@JsonIgnoreProperties(value = {"handler"})
public class Admin implements Serializable {
    private Integer id;
    private String password;
    private String name;
    private String mobile;
    private String email;
    private Integer irNum;
    private Integer apNum;
    private Integer achievement;
    private String headUrl;

}

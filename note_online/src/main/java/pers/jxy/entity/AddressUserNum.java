package pers.jxy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author:靳新宇
 * @date : 02-20 16:49
 */
@Data
@Getter
@Setter
@ToString
@JsonIgnoreProperties(value = {"handler"})
public class AddressUserNum implements Serializable, Comparable<AddressUserNum> {
    private String name;
    private Integer value;
    private String proportation;

    public AddressUserNum(String name, Integer value, String proportation) {
        this.name = name;
        this.value = value;
        this.proportation = proportation;
    }


    @Override
    public int compareTo(AddressUserNum o) {
        int result = o.getValue() - this.getValue();
        result = result == 0 ? o.getName().compareTo(this.getName()) : result;
        return result;
    }
}

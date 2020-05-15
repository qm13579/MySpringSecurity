package cn.people.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Field implements Serializable {

    private String id;

    /**表字段*/
    private String fieldName;

}

package com.example.analofurnitureinterface.pojo;


import lombok.Data;

import java.util.Date;

@Data
public class PersonVO {
    private Long id;

    private String name;

    private Integer age;

    private String address;

    private Date createTime;


    public PersonVO(Long id, String name, Integer age, String address,Date createTime) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.createTime=createTime;
    }
}

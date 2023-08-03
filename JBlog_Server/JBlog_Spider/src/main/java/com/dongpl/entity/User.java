package com.dongpl.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 使用数据库的自增长机制
    private Integer id;

    private String nickname;

    private String avatar;

}

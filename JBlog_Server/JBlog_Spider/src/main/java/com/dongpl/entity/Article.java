package com.dongpl.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 使用数据库的自增长机制
    private Integer id;

    @Column(name = "channel_id")
    private String channelId;

    private String title;

    private String content;

}

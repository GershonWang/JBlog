package com.dongpl.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "article")
public class Article {

    @Id
    private String id;

    private String channelId;

    private String title;

    private String content;

}

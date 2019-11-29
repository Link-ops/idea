package com.fh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class Album implements Serializable {
    private String id;
    private String title;
    private String score;
    private String author;
    private String announcer;
    private String chapterNumber;
    private String synopsis;
    private String state;
    private Date createTime;
    private Date uploadTime;
    private String img;
}

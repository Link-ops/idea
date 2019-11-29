package com.fh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class Chapter {
    private String id;
    private String title;
    private String time;
    private Date uploadTime;
    private String size;
    private String music;
    private String albumId;
}

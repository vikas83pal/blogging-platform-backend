package com.example.vikas_vlog_site.payload;

import java.sql.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {


    private String postTitle;

    private String imageName;

    private String content;

    private Date addedDate;

    private categoryDto category;

    private userDto user;


}

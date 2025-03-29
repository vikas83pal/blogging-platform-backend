package com.example.vikas_vlog_site.model;

import java.sql.Date;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "posts")
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int postId;

    @Length(max = 1000)
    private String postTitle;

    private String imageName;

    @Length(max = 1000)
    private String content;

    private Date addedDate;

    @ManyToOne
    private Catagory category;

    @ManyToOne
    private User user;

}

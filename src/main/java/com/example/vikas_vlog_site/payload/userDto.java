package com.example.vikas_vlog_site.payload;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class userDto {

    private int id;
    private String name;
    private String email;
    private String password;
    private String about;
}

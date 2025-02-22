package com.example.vikas_vlog_site.payload;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class userDto {

    private int id;

    @NotEmpty
    private String name;

    @Email(message = "Invalid email")
    private String email;

    @NotEmpty
    @Size(min = 3, max = 8, message = "Password must be in between 3 to 8 chacters")
    private String password;

    @NotEmpty
    private String about;
}

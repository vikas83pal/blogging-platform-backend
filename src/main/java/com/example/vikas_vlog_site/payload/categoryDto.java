package com.example.vikas_vlog_site.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class categoryDto {


    private Integer catagoryId;

    @NotEmpty
    private String categoryTitle;
    
    @NotEmpty
    private String categoryDesc;

}

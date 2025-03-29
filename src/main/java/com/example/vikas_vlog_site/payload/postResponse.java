package com.example.vikas_vlog_site.payload;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class postResponse {

    private String content;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalElement;
    private Integer totalPages;

    private boolean isLastPage;
}

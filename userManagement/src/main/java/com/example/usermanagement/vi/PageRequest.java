package com.example.usermanagement.vi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest {
    private Integer pageNum;
    private Integer pageSize;
}

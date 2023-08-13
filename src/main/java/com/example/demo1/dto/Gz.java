package com.example.demo1.dto;

import cn.hutool.core.date.DateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Gz {
    private DateTime start;
    private DateTime end;
    private Long between;


}

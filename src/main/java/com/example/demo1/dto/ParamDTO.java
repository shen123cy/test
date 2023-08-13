package com.example.demo1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
/**
 * 测试 @Validated
 */
public class ParamDTO {

    @NotNull
    private Long id;
    @NotBlank
    private String name;

    @Min(value = 20)
    private Integer age;


}

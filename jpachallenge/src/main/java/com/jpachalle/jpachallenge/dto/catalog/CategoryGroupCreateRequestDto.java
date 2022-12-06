package com.jpachalle.jpachallenge.dto.catalog;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class CategoryGroupCreateRequestDto {

    @Length(min = 2, max = 32)
    @NotBlank
    private String name;
}

package com.jpachalle.jpachallenge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
public class CreateResponseDto<T> {

    @NotNull
    private T id;
}

package com.cloud.dependencies.dtoservice.dto.actorservice;

import com.cloud.dependencies.dtoservice.util.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@ToString(includeFieldNames = false, callSuper = true)
@SuperBuilder
public class OwnerDto extends PersonDto {

    @NotNull(message = Constants.NOT_NULL_MESSAGE)
    private UserDto userId;

}

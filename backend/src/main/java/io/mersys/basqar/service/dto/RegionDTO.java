package io.mersys.basqar.service.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode()
public class RegionDTO implements Serializable {

    private String id;

    @NotBlank
    private String name;

}

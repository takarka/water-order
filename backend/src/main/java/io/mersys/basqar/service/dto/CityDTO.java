package io.mersys.basqar.service.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.mersys.basqar.service.dto.base.BaseCrudDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode()
public class CityDTO extends BaseCrudDTO implements Serializable {

    private String id;

    @NotBlank
    private String name;

}

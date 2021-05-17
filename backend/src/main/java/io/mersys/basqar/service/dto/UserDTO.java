package io.mersys.basqar.service.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode()
public class UserDTO implements Serializable {

    private String name;
    private String phone;

}

package io.mersys.basqar.service.dto;

import java.io.Serializable;

import io.mersys.basqar.service.dto.base.BaseSearchDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper=true)
public class UserSearchDTO extends BaseSearchDTO implements Serializable {
	
    private String name;

}

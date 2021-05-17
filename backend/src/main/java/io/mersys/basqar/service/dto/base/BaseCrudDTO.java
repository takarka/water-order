package io.mersys.basqar.service.dto.base;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseCrudDTO implements Serializable {
    private String id;
}

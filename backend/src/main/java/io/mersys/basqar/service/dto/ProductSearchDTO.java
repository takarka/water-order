package io.mersys.basqar.service.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode()
public class ProductSearchDTO implements Serializable {
    private String name;

}

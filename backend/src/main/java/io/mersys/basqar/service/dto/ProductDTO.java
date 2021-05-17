package io.mersys.basqar.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode()
public class ProductDTO implements Serializable {

	private String id;

	@NotBlank
	private String name;

	private String code;
	private String description;
	private String imageUrl;

    @JsonProperty(value = "productCategory")
    private ProductCategoryDTO productCategoryDTO;

	@NotNull
	@PositiveOrZero
	private BigDecimal price;
}

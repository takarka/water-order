package io.mersys.basqar.document;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@SuppressWarnings("serial")
@Document("product")
@TypeAlias("Product")
@Data
@EqualsAndHashCode()
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {

    @Id
    @JsonProperty("id")
    private String id;

    @NotBlank
    private String name;

    private String code;
    private String description;
    private String imageUrl;

    @JsonManagedReference
    @NotNull
    @DBRef(lazy = true)
    private ProductCategory productCategory;

    @NotNull
    @PositiveOrZero
    private BigDecimal price;

}

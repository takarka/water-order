package io.mersys.basqar.document;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@SuppressWarnings("serial")
@Document("product_category")
@TypeAlias("ProductCategory")
@Data
@EqualsAndHashCode()
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategory implements Serializable {

    @Id
    @JsonProperty("id")
    private String id;

    @NotBlank
    private String name;

    @DBRef
    private ProductCategory parent;

}

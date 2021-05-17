package io.mersys.basqar.document;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@SuppressWarnings("serial")
@Document("city")
@TypeAlias("City")
@Data
@EqualsAndHashCode()
@AllArgsConstructor
@NoArgsConstructor
public class City extends BaseDocument implements Serializable {

//    @Id
//    @JsonProperty("id")
//    private String id;

    @NotBlank
    private String name;

}

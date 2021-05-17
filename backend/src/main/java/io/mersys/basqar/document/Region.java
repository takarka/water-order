package io.mersys.basqar.document;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@SuppressWarnings("serial")
@Document("region")
@TypeAlias("Region")
@Data
@EqualsAndHashCode()
@AllArgsConstructor
@NoArgsConstructor
public class Region implements Serializable {

    @Id
    @JsonProperty("id")
    private String id;

    @NotBlank
    private String name;

}

package io.mersys.basqar.document;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.mersys.basqar.document.auth.User;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Document("address")
@TypeAlias("Address")
@Data
@EqualsAndHashCode()
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Address implements Serializable {

    @Id
    @JsonProperty("id")
    private String id;

    @NotNull
    private String name;

    @JsonManagedReference
    @NotNull
    @DBRef(lazy = true)
    private City city;

    @JsonManagedReference
    @NotNull
    @DBRef(lazy = true)
    private Region region;

    private String street;
    private String buildingNumber;
    private String entranceNumber;
    private String floor;
    private String entranceCode;
    private String flatNumber;

    @JsonManagedReference
    @NotNull
    @DBRef(lazy = true)
    private User user;


}

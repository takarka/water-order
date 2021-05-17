package io.mersys.basqar.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.mersys.basqar.document.City;
import io.mersys.basqar.document.Region;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode()
public class AddressSearchDTO implements Serializable {

    private String id;

    @NotNull
    private String name;

    @NotNull
    @JsonProperty(value = "city")
    private CityDTO cityDTO;

    @NotNull
    @JsonProperty(value = "region")
    private RegionDTO regionDTO;
    private String street;
    private String buildingNumber;
    private String entranceNumber;
    private String floor;
    private String entranceCode;
    private String flatNumber;
}

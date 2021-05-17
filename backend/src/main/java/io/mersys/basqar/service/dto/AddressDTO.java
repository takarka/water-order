package io.mersys.basqar.service.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode()
public class AddressDTO implements Serializable {

    private String id;

    @NotNull
    private String name;

    @JsonProperty(value = "city")
    @NotNull
    private CityDTO cityDTO;

    @JsonProperty(value = "region")
    @NotNull
    private RegionDTO regionDTO;

    private String street;
    private String buildingNumber;
    private String entranceNumber;
    private String floor;
    private String entranceCode;
    private String flatNumber;

}

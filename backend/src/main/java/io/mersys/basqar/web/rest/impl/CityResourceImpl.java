package io.mersys.basqar.web.rest.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.mersys.basqar.service.CityService;
import io.mersys.basqar.service.dto.CityDTO;
import io.mersys.basqar.service.dto.CitySearchDTO;
import io.mersys.basqar.web.rest.CityResource;
import io.mersys.basqar.web.rest.impl.base.BaseScrudResource;

@RestController
@RequestMapping("/api/city")
public class CityResourceImpl extends BaseScrudResource<CityDTO, CitySearchDTO> implements CityResource {

    private CityService service;

    public CityResourceImpl(CityService service) {
        super(service);
        this.service = service;
    }

}
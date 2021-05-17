package io.mersys.basqar.web.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.mersys.basqar.service.dto.RegionDTO;
import io.mersys.basqar.service.dto.RegionSearchDTO;

@RequestMapping("/api")
public interface RegionResource {


    @GetMapping(value = "/region")
    ResponseEntity<List<RegionDTO>> getAll();

    @PostMapping(value = "/region")
    ResponseEntity<RegionDTO> create(@Valid @RequestBody RegionDTO dto);

    @PutMapping(value = "/region")
    ResponseEntity<RegionDTO> update(@Valid @RequestBody RegionDTO dto);

    @DeleteMapping(value = "/region/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") String id);

    @GetMapping(value = "/region/{id}")
    ResponseEntity<RegionDTO> get(@PathVariable("id") String id);

    @PostMapping(value = "/region/search")
    ResponseEntity<List<RegionDTO>> getBySearch(@Valid @RequestBody RegionSearchDTO dto);
}

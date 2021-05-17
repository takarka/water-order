package io.mersys.basqar.web.rest;

import io.mersys.basqar.service.dto.AddressDTO;
import io.mersys.basqar.service.dto.AddressSearchDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/address")
public interface AddressResource {


    @GetMapping()
    ResponseEntity<List<AddressDTO>> getAll();

    @GetMapping(value="/my")
    ResponseEntity<List<AddressDTO>> getAllOfCurrentUser();

    @PostMapping()
    ResponseEntity<AddressDTO> create(@Valid @RequestBody AddressDTO dto);

    @PutMapping()
    ResponseEntity<AddressDTO> update(@Valid @RequestBody AddressDTO dto);

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") String id);

    @GetMapping(value = "/{id}")
    ResponseEntity<AddressDTO> get(@PathVariable("id") String id);

//    @PostMapping(value = "/search")
//    ResponseEntity<List<AddressDTO>> getBySearch(@Valid @RequestBody AddressSearchDTO dto);
}

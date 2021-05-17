package io.mersys.basqar.web.rest.impl;

import io.mersys.basqar.service.AddressService;
import io.mersys.basqar.service.dto.AddressDTO;
import io.mersys.basqar.service.dto.AddressSearchDTO;
import io.mersys.basqar.web.rest.AddressResource;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class AddressResourceImpl implements AddressResource {

    private AddressService service;

    @Override
    public ResponseEntity<List<AddressDTO>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @Override
    public ResponseEntity<List<AddressDTO>> getAllOfCurrentUser() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllOfCurrentUser());
    }

//    @Override
//    public ResponseEntity<List<AddressDTO>> getBySearch(@Valid @RequestBody AddressSearchDTO dto) {
//        return ResponseEntity.status(HttpStatus.OK).body(service.search(dto));
//    }

    @Override
    public ResponseEntity<AddressDTO> get(@PathVariable("id") String id) {
        return service.get(id).map(ResponseEntity.ok()::body)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public ResponseEntity<AddressDTO> create(@Valid @RequestBody AddressDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @Override
    public ResponseEntity<AddressDTO> update(@Valid @RequestBody AddressDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(dto));
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        return service.get(id).map(this::delete).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    private ResponseEntity<Void> delete(AddressDTO dto) {
        service.delete(dto.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
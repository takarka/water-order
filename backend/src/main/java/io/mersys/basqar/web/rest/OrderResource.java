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

import io.mersys.basqar.service.dto.OrderDTO;
import io.mersys.basqar.service.dto.OrderSearchDTO;

@RequestMapping("/api/order")
public interface OrderResource {

    @GetMapping()
    ResponseEntity<List<OrderDTO>> getAll();

    @GetMapping(value = "/my")
    ResponseEntity<List<OrderDTO>> getAllOfCurrentUser();

    @PostMapping()
    ResponseEntity<OrderDTO> create(@Valid @RequestBody OrderDTO dto);

    @PutMapping()
    ResponseEntity<OrderDTO> update(@Valid @RequestBody OrderDTO dto);

    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") String id);

    @GetMapping(value = "/{id}")
    ResponseEntity<OrderDTO> get(@PathVariable("id") String id);

    @PostMapping(value = "/search")
    ResponseEntity<List<OrderDTO>> getBySearch(@Valid @RequestBody OrderSearchDTO dto);

    @PostMapping(value = "/approve")
    ResponseEntity<Void> approve(@Valid @RequestBody List<String> ids);

    @PostMapping(value = "/cancel")
    ResponseEntity<Void> cancel(@Valid @RequestBody List<String> ids);
}

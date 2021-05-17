package io.mersys.basqar.service.impl;

import io.mersys.basqar.document.Address;
import io.mersys.basqar.document.auth.User;
import io.mersys.basqar.repository.AddressRepository;
import io.mersys.basqar.repository.UserRepository;
import io.mersys.basqar.security.UserPrincipal;
import io.mersys.basqar.service.AddressService;
import io.mersys.basqar.service.dto.AddressDTO;
import io.mersys.basqar.service.dto.AddressSearchDTO;
import io.mersys.basqar.service.mapper.AddressMapper;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private AddressMapper mapper;
    private AddressRepository repository;
    private UserRepository userRepo;

    @Override
    public AddressDTO create(AddressDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("To create Address, 'dto' must not be null");
        }
        if (dto.getId() != null) {
            throw new RuntimeException("To create Address, 'id' must be null");
        }

        return save(dto);
    }

    @Override
    public void delete(String id) {
        if (!ObjectId.isValid(id)) {
            throw new IllegalArgumentException("Address 'id' is not valid value: '" + id + "'");
        }

        repository.deleteById(id);
    }

    @Override
    public List<AddressDTO> getAllOfCurrentUser() {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepo.findById(principal.getId()).orElseThrow(() -> new IllegalArgumentException("User not forund"));

        return mapper.toDto(repository.findAllByUserId(user.getId()));
    }

    @Override
    public Optional<AddressDTO> get(String id) {
        if (!ObjectId.isValid(id)) {
            throw new IllegalArgumentException("Address 'id' is not valid value: '" + id + "'");
        }

        return repository.findById(id).map(mapper::toDto);
    }

    @Override
    public List<AddressDTO> getAll() {
        return mapper.toDto(repository.findAll());
    }

    private AddressDTO save(AddressDTO dto) {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepo.findById(principal.getId()).orElseThrow(() -> new IllegalArgumentException("User not forund"));

        Address doc = mapper.toDocument(dto);
        doc.setUser(user);
        doc = repository.save(doc);
        return mapper.toDto(doc);
    }

    @Override
    public AddressDTO update(AddressDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("To update Address, 'dto' must not be null");
        }
        if (dto.getName() == null) {
            throw new RuntimeException("To update Address, 'name' must not be null");
        }

        return save(dto);
    }

//    @Override
//    public List<AddressDTO> search(AddressSearchDTO dto) {
//        if (dto == null) {
//            throw new IllegalArgumentException("To search Address, 'dto' must not be null");
//        }
//
//        List<Address> list = repository.findBySearchDTO(dto);
//        return mapper.toDto(list);
//    }

}

package io.mersys.basqar.repository.impl;

import io.mersys.basqar.document.Address;
import io.mersys.basqar.repository.CustomizedAddressRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class CustomizedAddressRepositoryImpl implements CustomizedAddressRepository {

    @Autowired
    private MongoTemplate template;

    @Override
    public List<Address> findAllByUserId(String id) {
        final Query q = new Query();
        q.addCriteria(Criteria.where("user.$id").is(new ObjectId(id)));
        return template.find(q, Address.class);
    }

//    @Override
//    public List<Address> findBySearchDTO(AddressSearchDTO dto) {
//        final Query q = new Query();
//        if (dto.getName() != null && !dto.getName().trim().isEmpty()) {
//            q.addCriteria(Criteria.where("name").regex(Pattern.compile(Pattern.quote(dto.getName().trim()),
//                    Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)));
//        }
//
//        q.with(new Sort(Sort.Direction.DESC, "_id"));
//        return template.find(q, Address.class);
//    }

}

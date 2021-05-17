package io.mersys.basqar.repository.impl;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import io.mersys.basqar.document.Product;
import io.mersys.basqar.repository.CustomizedProductRepository;
import io.mersys.basqar.service.dto.ProductSearchDTO;

public class CustomizedProductRepositoryImpl implements CustomizedProductRepository {

    @Autowired
    private MongoTemplate template;

    @Override
    public List<Product> findBySearchDTO(ProductSearchDTO dto) {
        final Query q = new Query();
        if (dto.getName() != null && !dto.getName().trim().isEmpty()) {
            q.addCriteria(Criteria.where("name").regex(Pattern.compile(Pattern.quote(dto.getName().trim()),
                    Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)));
        }
//        if (dto.getType() != null) {
//            q.addCriteria(Criteria.where("type").is(dto.getType()));
//        }
        q.with(new Sort(Sort.Direction.DESC, "_id"));
        return template.find(q, Product.class);
    }

}

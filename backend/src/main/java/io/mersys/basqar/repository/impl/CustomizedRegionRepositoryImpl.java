package io.mersys.basqar.repository.impl;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import io.mersys.basqar.document.Region;
import io.mersys.basqar.repository.CustomizedRegionRepository;
import io.mersys.basqar.service.dto.RegionSearchDTO;

public class CustomizedRegionRepositoryImpl implements CustomizedRegionRepository {

    @Autowired
    private MongoTemplate template;

    @Override
    public List<Region> findBySearchDTO(RegionSearchDTO dto) {
        final Query q = new Query();
        if (dto.getName() != null && !dto.getName().trim().isEmpty()) {
            q.addCriteria(Criteria.where("name").regex(Pattern.compile(Pattern.quote(dto.getName().trim()),
                    Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)));
        }

        q.with(new Sort(Sort.Direction.DESC, "_id"));
        return template.find(q, Region.class);
    }

}

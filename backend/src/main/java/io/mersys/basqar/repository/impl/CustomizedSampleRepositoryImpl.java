package io.mersys.basqar.repository.impl;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import io.mersys.basqar.document.Sample;
import io.mersys.basqar.repository.CustomizedSampleRepository;
import io.mersys.basqar.service.dto.SampleSearchDTO;

public class CustomizedSampleRepositoryImpl implements CustomizedSampleRepository {

    @Autowired
    private MongoTemplate template;

    @Override
    public List<Sample> findBySearchDTO(SampleSearchDTO dto) {
        final Query q = new Query();
        if (dto.getName() != null && !dto.getName().trim().isEmpty()) {
            q.addCriteria(Criteria.where("name").regex(Pattern.compile(Pattern.quote(dto.getName().trim()),
                    Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)));
        }
        if (dto.getShortName() != null && !dto.getShortName().trim().isEmpty()) {
            q.addCriteria(Criteria.where("shortName").regex(Pattern.compile(Pattern.quote(dto.getShortName().trim()),
                    Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)));
        }
        if (dto.getType() != null) {
            q.addCriteria(Criteria.where("type").is(dto.getType()));
        }
        q.with(new Sort(Sort.Direction.DESC, "_id"));
        return template.find(q, Sample.class);
    }

}

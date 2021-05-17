package io.mersys.basqar.service.dto;

import java.io.Serializable;

import io.mersys.basqar.document.type.SampleType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode()
public class SampleSearchDTO implements Serializable {
    private String name;
    private String shortName;
    private SampleType type;
}

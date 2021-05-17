package io.mersys.basqar.document;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
//@Document
//@TypeAlias("BaseDocument")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseDocument implements Serializable {
	
    @Id
    private String id;
    
    private String createdBy;
    
//    private String createdAt;
    
//    private String updatedAt;
    
}

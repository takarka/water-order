package io.mersys.basqar.document;

import java.io.Serializable;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Document("order_time_range")
@TypeAlias("OrderTimeRange")
@Data
@EqualsAndHashCode()
@AllArgsConstructor
@NoArgsConstructor
public class OrderTimeRange implements Serializable {
	@Id
	@JsonProperty("id")
	private String id;

	@NotNull
	private LocalTime start;

	@NotNull
	private LocalTime end;
	
	@NotNull
	private Integer order;
}

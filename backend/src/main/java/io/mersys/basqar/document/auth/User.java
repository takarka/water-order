package io.mersys.basqar.document.auth;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.mersys.basqar.document.BaseDocument;
import io.mersys.basqar.document.type.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Document("user")
@TypeAlias("User")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class User extends BaseDocument implements Serializable {

	@NotNull
	private String name;

	@Email
	@NotNull
	private String email;
	
	@NotNull
	private String phone;
	
	private String imageUrl;

	@NotNull
	private Boolean active;
	
	@NotNull
	private Boolean emailVerified = false;

	@JsonIgnore
	private String password;

	@NotNull
	private AuthProvider provider;

	private String providerId;

	@NotNull
	private List<RoleEnum> roles;

}

package io.mersys.basqar.service.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.mersys.basqar.document.auth.AuthProvider;
import io.mersys.basqar.document.type.RoleEnum;
import io.mersys.basqar.service.dto.base.BaseCrudDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserMngtDTO extends BaseCrudDTO implements Serializable {

	@NotBlank
	private String name;

	@Email
	@NotBlank
	private String email;

	@NotBlank
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

	@NotNull
	private List<RoleEnum> roles;

}

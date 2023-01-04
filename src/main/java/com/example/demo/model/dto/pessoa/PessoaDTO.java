package com.example.demo.model.dto.pessoa;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PessoaDTO {

	private Long codigo;

	@NotNull
	@Size(max = 80)
	private String nome;

	@Email
	@NotNull
	@Size(max = 100)
	private String email;

	@NotNull
	private String estado;

	@NotNull
	private String telefone;

	@NotNull
	private String registro;
}

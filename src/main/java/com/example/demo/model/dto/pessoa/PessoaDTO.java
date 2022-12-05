package com.example.demo.model.dto.pessoa;

import com.example.demo.model.dto.pessoa.enuns.TipoStatus;
import com.example.demo.model.dto.pessoa.enuns.TipoUsuario;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
	@Size(max = 40)
	private String senha;

	@NotNull
	private Integer idade;

	@NotNull
	private String estado;

	@NotNull
	private TipoStatus status;

	@NotNull
	private TipoUsuario privilegio;
}

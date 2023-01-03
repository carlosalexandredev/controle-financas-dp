package com.example.demo.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.demo.model.dto.pessoa.enuns.TipoStatus;
import com.example.demo.model.dto.pessoa.enuns.TipoUsuario;
import lombok.Data;;

@Entity
@Data
@Table(name = "PESSOA" )
public class Pessoa {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

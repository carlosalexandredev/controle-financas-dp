package com.example.demo.model.perfil.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;;

@Entity
@Data
@Table(name = "PERFIL" )
public class Perfil {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PER_CODIGO")
	private Long codigo;
	
	@NotNull
	@Size(max = 80)
	@Column(name = "PER_NOME")
	private String nome;

	@Email
	@NotNull
	@Size(max = 100)
	@Column(name = "PER_EMAIL")
	private String email;

	@NotNull
	@Column(name = "PER_ESTADO")
	private String estado;

	@NotNull
	@Column(name = "PER_TELEFONE")
	private String telefone;

	@NotNull
	@Column(name = "PER_REGISTRO")
	private String registro;
}

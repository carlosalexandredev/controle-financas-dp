package com.example.demo.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.demo.model.dto.despesa.enuns.TipoDespesa;
import com.example.demo.model.util.enuns.TipoMoeda;
import lombok.Data;

@Entity
@Data
@Table(name ="DESPESA")
public class Despesa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DES_CODIGO")
	private Long codigo;
	@NotNull
	@Size(max = 50)
	@Column(name = "DES_NOME")
	private String nome;
	@NotNull
	@Size(max = 50)
	@Column(name = "DES_SETOR")
	private String setor;
	@Size(max = 80)
	@Column(name = "DES_DESCRICAO")
	private String descricao;
	@NotNull
	@Column(name = "DES_DATA")
	private LocalDate dataVencimento;
	@NotNull
	@Column(name = "DES_TIPO")
	private TipoDespesa tipodespesa;
	@NotNull
	@Column(name = "DES_VALOR")
	private BigDecimal valor;
	@NotNull
	@Column(name = "DES_MOEDA")
	private TipoMoeda tipomoeda;
	@ManyToOne
	@JoinColumn(name= "DES_PER")
	private Perfil perfil;
	
}

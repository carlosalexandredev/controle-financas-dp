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
	private Long codigo;
	@NotNull
	@Size(max = 50)
	private String nome;
	@NotNull
	@Size(max = 50)
	private String setor;
	@Size(max = 80)
	private String descricao;
	@NotNull
	@Column(name = "data_vencimento")
	private LocalDate dataVencimento;
	@NotNull
	private TipoDespesa tipodespesa;
	@NotNull
	private BigDecimal valor;
	@NotNull
	private TipoMoeda tipomoeda;
	@ManyToOne
	@JoinColumn(name= "codigo_pessoa")
	private Pessoa pessoa;
	
}

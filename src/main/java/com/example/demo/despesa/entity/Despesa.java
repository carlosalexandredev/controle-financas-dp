package com.example.demo.despesa.entity;

import com.example.demo.despesa.enuns.TipoDespesa;
import com.example.demo.fortune.util.enuns.TipoMoeda;
import com.example.demo.usuario.entity.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

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

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id")
	private User user;

}

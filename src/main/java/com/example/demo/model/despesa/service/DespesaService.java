package com.example.demo.model.despesa.service;

import com.example.demo.model.despesa.dto.DespesaDTO;
import com.example.demo.model.despesa.dto.ListaDespesaTipoDTO;
import com.example.demo.model.despesa.dto.ListaDespesaTipoDTOBuilder;
import com.example.demo.model.despesa.entity.Despesa;
import com.example.demo.model.despesa.enuns.TipoDespesa;
import com.example.demo.model.despesa.repository.DespesaRepository;
import com.example.demo.model.fortune.exceptions.PessoaInexistenteOuInativaException;
import com.example.demo.model.perfil.dto.PerfilDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DespesaService {
	
	@Autowired
	private DespesaRepository despesaDAO;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ApplicationEventPublisher publisher;

	/**
	 * @Method buscaDespesaAll()
	 * @Rule 1 - Realiza busca de todos despesas na base de dados.
	 **/
	public List<DespesaDTO> buscaDespesaAll(){
		List<Despesa> despesas = despesaDAO.findAll();

		return despesas.stream()
				.map(task -> modelMapper.map(task, DespesaDTO.class))
				.collect(Collectors.toList());
	}

	public ListaDespesaTipoDTO buscaDespesasTipo(){
		List<DespesaDTO> fixas = new ArrayList<>();
		List<DespesaDTO> variaveis = new ArrayList<>();
		List<DespesaDTO> extraordinarias = new ArrayList<>();
		List<DespesaDTO> regulares = new ArrayList<>();

		List<DespesaDTO> despesas = despesaDAO.findAll()
				.stream().map(task -> modelMapper.map(task, DespesaDTO.class))
				.collect(Collectors.toList());

		for (DespesaDTO despesa: despesas) {
			if(despesa.getTipodespesa().equals(TipoDespesa.FIXAS))
				fixas.add(despesa);
			if(despesa.getTipodespesa().equals(TipoDespesa.VARIAVEIS))
				variaveis.add(despesa);
			if(despesa.getTipodespesa().equals(TipoDespesa.EXTRAORDINARIAS))
				extraordinarias.add(despesa);
			if(despesa.getTipodespesa().equals(TipoDespesa.REGULARES))
				regulares.add(despesa);
		}

		return ListaDespesaTipoDTOBuilder.builder()
				.despesasFixas(fixas).totalFixas(calculaTotalDespesas(fixas))
				.despesasVariaveis(variaveis).totalVariaveis(calculaTotalDespesas(variaveis))
				.despesasExtraordinarias(extraordinarias).totalExtraordinarias(calculaTotalDespesas(extraordinarias))
				.despesasRegulares(regulares).totalRegulares(calculaTotalDespesas(regulares))
				.build();
	}

	public BigDecimal calculaTotalDespesas(List<DespesaDTO> despesas){
		return despesas.stream()
				.map(despesa -> despesa.getValor())
				.reduce(BigDecimal.ZERO,BigDecimal::add);
	}

	/**
	 * @Method buscaDespesasById(Long codigo)
	 * @Rule 1 - Realiza busca de uma única tarefa na base de dados.
	 **/
	public Optional<DespesaDTO> buscaDespesaById(Long codigo) {
		Optional<Despesa> despesa = despesaDAO.findById(codigo);
		return Optional.of(modelMapper.map(despesa.get(), DespesaDTO.class));
	}

	/**
	 * @Method criarDespesa(DespesaDTO despesa, HttpServletResponse response)
	 * @Rule 1 - Realiza inserção de despesa na base de dados.
	 * @Rule 2 - Regras checar Validations em DespesaDTO.
	 **/
	public DespesaDTO criarDespesa(DespesaDTO despesa, HttpServletResponse response) throws PessoaInexistenteOuInativaException {
		despesaDAO.save(modelMapper.map(despesa, Despesa.class));
		return modelMapper.map(despesa, DespesaDTO.class);
	}

	/**
	 * @Method removerDespesa(Long codigo)
	 * @Rule 1 - Remover o despesa pelo seu codigo.
	 **/
	public void removerDespesa(Long codigo) {
		despesaDAO.deleteById(codigo);
	}


	public PerfilDTO atualizaDespesa(Long codigo, DespesaDTO tarefa) throws PessoaInexistenteOuInativaException {
		Despesa despesaSalva = despesaDAO.getById(codigo);
		BeanUtils.copyProperties(tarefa, despesaSalva, "codigo");
		return modelMapper.map(despesaDAO.save(despesaSalva), PerfilDTO.class);
	}
}

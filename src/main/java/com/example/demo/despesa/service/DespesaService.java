package com.example.demo.despesa.service;

import com.example.demo.despesa.dto.DespesaDTO;
import com.example.demo.despesa.dto.ListaDespesaTipoDTO;
import com.example.demo.despesa.entity.Despesa;
import com.example.demo.despesa.enuns.TipoDespesa;
import com.example.demo.despesa.repository.DespesaRepository;
import com.example.demo.fortune.exceptions.PessoaInexistenteOuInativaException;
import com.example.demo.fortune.exceptions.ResourceNotFoundException;
import com.example.demo.perfil.dto.PerfilDTO;
import com.example.demo.usuario.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DespesaService {

	private final DespesaRepository despesaRepository;

	private final UserService userService;

	private final ModelMapper modelMapper;

	public ListaDespesaTipoDTO buscaDespesasByPerfil() {
		List<DespesaDTO> fixas = new ArrayList<>();
		List<DespesaDTO> variaveis = new ArrayList<>();
		List<DespesaDTO> extraordinarias = new ArrayList<>();
		List<DespesaDTO> regulares = new ArrayList<>();

		List<DespesaDTO> despesas = despesaRepository
				.findReceitasByUser(userService.getUser())
				.stream().map(task -> modelMapper.map(task, DespesaDTO.class))
				.collect(Collectors.toList());

		for (DespesaDTO despesa : despesas) {
			if (despesa.getTipodespesa().equals(TipoDespesa.FIXAS))
				fixas.add(despesa);
			if (despesa.getTipodespesa().equals(TipoDespesa.VARIAVEIS))
				variaveis.add(despesa);
			if (despesa.getTipodespesa().equals(TipoDespesa.EXTRAORDINARIAS))
				extraordinarias.add(despesa);
			if(despesa.getTipodespesa().equals(TipoDespesa.REGULARES))
				regulares.add(despesa);
		}

		return ListaDespesaTipoDTO.builder()
				.despesasFixas(fixas).totalFixas(calculaTotalDespesas(fixas))
				.despesasVariaveis(variaveis).totalVariaveis(calculaTotalDespesas(variaveis))
				.despesasExtraordinarias(extraordinarias).totalExtraordinarias(calculaTotalDespesas(extraordinarias))
				.despesasRegulares(regulares).totalRegulares(calculaTotalDespesas(regulares))
				.build();
	}

	public BigDecimal calculaTotalDespesas(List<DespesaDTO> despesas){
		return despesas.stream()
				.map(DespesaDTO::getValor)
				.reduce(BigDecimal.ZERO,BigDecimal::add);
	}

	/**
	 * @Method buscaDespesasById(Long codigo)
	 * @Rule 1 - Realiza busca de uma única tarefa na base de dados.
	 **/
	public Optional<DespesaDTO> buscaDespesaById(Long codigo) {
		Despesa despesa = despesaRepository
				.findById(codigo).orElseThrow(() -> new ResourceNotFoundException(codigo));
		return Optional.of(modelMapper.map(despesa, DespesaDTO.class));
	}

	/**
	 * @Method criarDespesa(DespesaDTO despesa, HttpServletResponse response)
	 * @Rule 1 - Realiza inserção de despesa na base de dados.
	 * @Rule 2 - Regras checar Validations em DespesaDTO.
	 **/
	public DespesaDTO criarDespesa(DespesaDTO despesaDTO, HttpServletResponse response) throws PessoaInexistenteOuInativaException {
		Despesa despesa = modelMapper.map(despesaDTO, Despesa.class);
		despesa.setUser(userService.getUser());
		return modelMapper.map(despesaRepository.save(despesa), DespesaDTO.class);
	}

	/**
	 * @Method removerDespesa(Long codigo)
	 * @Rule 1 - Remover o despesa pelo seu codigo.
	 **/
	public void removerDespesa(Long codigo) {
		despesaRepository
				.deleteById(codigo);
	}


	public PerfilDTO atualizaDespesa(Long codigo, DespesaDTO tarefa) {
		Despesa despesaSalva = despesaRepository
				.getById(codigo);
		BeanUtils.copyProperties(tarefa, despesaSalva, "codigo");
		return modelMapper.map(despesaRepository
				.save(despesaSalva), PerfilDTO.class);
	}
}

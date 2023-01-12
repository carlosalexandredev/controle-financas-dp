package com.example.demo.model.despesa.service;

import com.example.demo.model.despesa.dto.DespesaDTO;
import com.example.demo.model.despesa.dto.ListaDespesaTipoDTO;
import com.example.demo.model.despesa.dto.ListaDespesaTipoDTOBuilder;
import com.example.demo.model.despesa.entity.Despesa;
import com.example.demo.model.despesa.enuns.TipoDespesa;
import com.example.demo.model.despesa.repository.DespesaRepository;
import com.example.demo.model.fortune.exceptions.PessoaInexistenteOuInativaException;
import com.example.demo.model.fortune.exceptions.ResourceNotFoundException;
import com.example.demo.model.fortune.util.BeanUtilsCustom;
import com.example.demo.model.usuario.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
				.findDespesaByUser(userService.getUser())
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

		return ListaDespesaTipoDTOBuilder.builder()
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

	public Optional<DespesaDTO> buscaDespesaById(Long codigo) {
		Despesa despesa = despesaRepository
				.findById(codigo).orElseThrow(() -> new ResourceNotFoundException(codigo));
		return Optional.of(modelMapper.map(despesa, DespesaDTO.class));
	}

	public DespesaDTO criarDespesa(DespesaDTO despesaDTO, HttpServletResponse response) throws PessoaInexistenteOuInativaException {
		Despesa despesa = modelMapper.map(despesaDTO, Despesa.class);
		despesa.setUser(userService.getUser());
		return modelMapper.map(despesaRepository.save(despesa), DespesaDTO.class);
	}

	public void removerDespesa(Long codigo) {
		despesaRepository.deleteById(codigo);
	}


	public DespesaDTO atualizaDespesa(Long codigo, DespesaDTO despesaDTO) {
		Despesa despesa = despesaRepository.findById(codigo)
				.orElseThrow(() -> new ResourceNotFoundException(codigo));
		BeanUtilsCustom.copyNonNullProperties(despesaDTO, despesa);
		return modelMapper.map(despesaRepository.save(despesa), DespesaDTO.class);
	}
}

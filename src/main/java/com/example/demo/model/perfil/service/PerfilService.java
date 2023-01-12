package com.example.demo.model.perfil.service;

import com.example.demo.model.fortune.exceptions.PessoaInexistenteOuInativaException;
import com.example.demo.model.perfil.entity.Perfil;
import com.example.demo.model.perfil.dto.PerfilDTO;
import com.example.demo.model.perfil.repository.PerfilRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PerfilService {
	
	@Autowired
	private PerfilRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	/**
	 * @Method buscaAll()
	 * @Rule 1 - Realiza busca de todos os usuários na base de dados.
	 **/
	public List<PerfilDTO> buscaAll(){
		List<Perfil> perfils = repository.findAll();
		return perfils.stream()
				.map(user -> modelMapper.map(user, PerfilDTO.class))
				.collect(Collectors.toList());
	}

	/**
	 * @Method buscaById(Long codigo)
	 * @Rule 1 - Realiza busca de um único usuário na base de dados.
	 **/
	public Optional<PerfilDTO> buscaById(Long codigo) throws PessoaInexistenteOuInativaException {
		Optional<Perfil> usuario = Optional.of(buscarPessoaPeloCodigo(codigo));
		return Optional.of(modelMapper.map(usuario.get(), PerfilDTO.class));
	}

	/**
	 * @Method criarUser(PessoaDTO pessoa, HttpServletResponse response)
	 * @Rule 1 - Realiza inserção de usuário na base de dados.
	 * @Rule 2 - Regras checar Validations em PessoaDTO.
	 **/
	public PerfilDTO criarUser(PerfilDTO usuario, HttpServletResponse response){
		Perfil perfilSalva = repository.save(modelMapper.map(usuario, Perfil.class));
		return modelMapper.map(usuario, PerfilDTO.class);
	}

	/**
	 * @Method atualizaUser(Long codigo, PessoaDTO pessoa)
	 * @Rule 1 - Realiza atualização de usuário na base de dados.
	 * @Rule 2 - Regras checar Validations em PessoaDTO.
	 **/
	public PerfilDTO atualizaUser(Long codigo, PerfilDTO pessoa) throws PessoaInexistenteOuInativaException {
		Perfil perfilSalva = (buscarPessoaPeloCodigo(codigo));
		BeanUtils.copyProperties(pessoa, perfilSalva, "codigo");
		return modelMapper.map(repository.save(perfilSalva), PerfilDTO.class);
	}

	/**
	 * @Method atualizarPropriedadeAtiva(Long codigo, Boolean ativo)
	 * @Rule 1 - Realiza atualização para ativar ou desativar usuário na base de dados.
	 **/
//	public void atualizarPropriedadeAtiva(Long codigo, TipoStatus status) {
//		Pessoa pessoaSalva = (buscarPessoaPeloCodigo(codigo));
//		pessoaSalva.setStatus(status);
//		repository.save(pessoaSalva);
//	}

	/**
	 * @Method removerUser(Long codigo)
	 * @Rule 1 - Remover o usuário pelo seu codigo.
	 **/
	public void removerUser(Long codigo){
		repository.deleteById(codigo);
	}

	/**
	 * @Method buscarPessoaPeloCodigo(Long codigo)
	 * @Rule 1 - Realiza busca do usuário pelo seu codigo.
	 **/
	public Perfil buscarPessoaPeloCodigo(Long codigo) {
		Perfil perfilSalva =  repository.findById((codigo)).orElse(null);
		if(!Objects.nonNull(perfilSalva)){throw new EmptyResultDataAccessException(1);}
		return perfilSalva;
	}
}

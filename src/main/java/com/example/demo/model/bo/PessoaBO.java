package com.example.demo.model.bo;

import com.example.demo.model.dto.pessoa.enuns.TipoStatus;
import com.example.demo.model.entity.Pessoa;
import com.example.demo.model.bo.exceptionhandler.PessoaInexistenteOuInativaException;
import com.example.demo.model.dto.pessoa.PessoaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.model.dao.pessoa.PessoaDAO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaBO {
	
	@Autowired
	private PessoaDAO pessoaDAO;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ApplicationEventPublisher publisher;

	/**
	 * @Method buscaAll()
	 * @Rule 1 - Realiza busca de todos os usuários na base de dados.
	 **/
	public List<PessoaDTO> buscaAll(){
		List<Pessoa> pessoas = pessoaDAO.findAll();
		return pessoas.stream()
				.map(user -> modelMapper.map(user, PessoaDTO.class))
				.collect(Collectors.toList());
	}

	/**
	 * @Method buscaById(Long codigo)
	 * @Rule 1 - Realiza busca de um único usuário na base de dados.
	 **/
	public Optional<PessoaDTO> buscaById(Long codigo) throws PessoaInexistenteOuInativaException {
		Optional<Pessoa> usuario = Optional.of(buscarPessoaPeloCodigo(codigo));
		return Optional.of(modelMapper.map(usuario.get(), PessoaDTO.class));
	}

	/**
	 * @Method criarUser(PessoaDTO pessoa, HttpServletResponse response)
	 * @Rule 1 - Realiza inserção de usuário na base de dados.
	 * @Rule 2 - Regras checar Validations em PessoaDTO.
	 **/
	public PessoaDTO criarUser(PessoaDTO usuario, HttpServletResponse response){

		usuario.setSenha(usuario.getSenha());
		Pessoa pessoaSalva = pessoaDAO.save(modelMapper.map(usuario, Pessoa.class));
		return modelMapper.map(usuario, PessoaDTO.class);
	}

	/**
	 * @Method atualizaUser(Long codigo, PessoaDTO pessoa)
	 * @Rule 1 - Realiza atualização de usuário na base de dados.
	 * @Rule 2 - Regras checar Validations em PessoaDTO.
	 **/
	public PessoaDTO atualizaUser(Long codigo, PessoaDTO pessoa) throws PessoaInexistenteOuInativaException {
		pessoa.setSenha(pessoa.getSenha());
		Pessoa pessoaSalva = (buscarPessoaPeloCodigo(codigo));
		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
		return modelMapper.map(pessoaDAO.save(pessoaSalva), PessoaDTO.class);
	}

	/**
	 * @Method atualizarPropriedadeAtiva(Long codigo, Boolean ativo)
	 * @Rule 1 - Realiza atualização para ativar ou desativar usuário na base de dados.
	 **/
	public void atualizarPropriedadeAtiva(Long codigo, TipoStatus status) {
		Pessoa pessoaSalva = (buscarPessoaPeloCodigo(codigo));
		pessoaSalva.setStatus(status);
		pessoaDAO.save(pessoaSalva);
	}

	/**
	 * @Method removerUser(Long codigo)
	 * @Rule 1 - Remover o usuário pelo seu codigo.
	 **/
	public void removerUser(Long codigo){
		pessoaDAO.deleteById(codigo);
	}

	/**
	 * @Method buscarPessoaPeloCodigo(Long codigo)
	 * @Rule 1 - Realiza busca do usuário pelo seu codigo.
	 **/
	public Pessoa buscarPessoaPeloCodigo(Long codigo) {
		Pessoa pessoaSalva =  pessoaDAO.findById((codigo)).orElse(null);
		if(!Objects.nonNull(pessoaSalva)){throw new EmptyResultDataAccessException(1);}
		return pessoaSalva;
	}
}

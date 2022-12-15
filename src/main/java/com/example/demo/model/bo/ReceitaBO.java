package com.example.demo.model.bo;

import com.example.demo.model.bo.event.RecursoCriadoEvent;
import com.example.demo.model.bo.exceptionhandler.PessoaInexistenteOuInativaException;
import com.example.demo.model.dao.receita.ReceitaDAO;
import com.example.demo.model.dto.despesa.DespesaDTO;
import com.example.demo.model.dto.despesa.ListaDespesaTipoDTO;
import com.example.demo.model.dto.pessoa.PessoaDTO;
import com.example.demo.model.dto.receita.ReceitaDTO;
import com.example.demo.model.entity.Despesa;
import com.example.demo.model.entity.Receita;
import com.example.demo.model.util.ModelMapperUtil;
import com.example.demo.model.util.MonetarioUtil;
import com.example.demo.model.util.enuns.TipoMoeda;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReceitaBO {
    @Autowired
    private ReceitaDAO receitaDAO;

    private ModelMapper modelMapper = ModelMapperUtil.getInstance();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    MonetarioUtil monetarioUtil = MonetarioUtil.getInstance();


    @Autowired
    private ApplicationEventPublisher publisher;

    /**
     * @Method buscaDespesaAll()
     * @Rule 1 - Realiza busca de todos despesas na base de dados.
     **/
    public List<ReceitaDTO> buscaReceitasAll(){
        List<Receita> receitas = receitaDAO.findAll();
        List<ReceitaDTO> receitaDTOs = new ArrayList<>();
        if(!receitas.isEmpty()) {
            for (Receita receita : receitas) {
                ReceitaDTO receitaDto = ReceitaDTO.builder()
                        .codigo(receita.getCodigo())
                        .nome(receita.getNome())
                        .descricao(receita.getDescricao())
                        .data(receita.getData())
                        .dataformatada(receita.getData().format(formatter))
                        .valor(receita.getValor())
                        .valorformatado(monetarioUtil.monetarios(receita.getValor(), 17, receita.getTipomoeda()))
                        .tipomoeda(receita.getTipomoeda())
                        .build();
                receitaDTOs.add(receitaDto);
            }
        }

        return receitaDTOs;
    }



    public ListaDespesaTipoDTO buscaReceitaTipo(){

        List<DespesaDTO> listaVazia = Collections.emptyList();
        BigDecimal valorVazio = new BigDecimal('0');

        return ListaDespesaTipoDTO.builder()
                .build();
    }

    /**
     * @Method buscaDespesasById(Long codigo)
     * @Rule 1 - Realiza busca de uma única tarefa na base de dados.
     **/
    public Optional<ReceitaDTO> buscaReceitaById(Long codigo) {
        Optional<Receita> receita = receitaDAO.findById(codigo);
        return Optional.of(modelMapper.map(receita.get(), ReceitaDTO.class));
    }

    /**
     * @Method criarDespesa(DespesaDTO despesa, HttpServletResponse response)
     * @Rule 1 - Realiza inserção de despesa na base de dados.
     * @Rule 2 - Regras checar Validations em DespesaDTO.
     **/
    public ReceitaDTO criarDespesa(ReceitaDTO receita, HttpServletResponse response) throws PessoaInexistenteOuInativaException {
        Receita receitaSalva = receitaDAO.save(modelMapper.map(receita, Receita.class));
        publisher.publishEvent(new RecursoCriadoEvent(this, response, receitaSalva.getCodigo()));
        return modelMapper.map(receita, ReceitaDTO.class);
    }

    /**
     * @Method removerDespesa(Long codigo)
     * @Rule 1 - Remover o despesa pelo seu codigo.
     **/
    public void removerReceita(Long codigo) {
        receitaDAO.deleteById(codigo);
    }


    public ReceitaDTO atualizaReceita(Long codigo, ReceitaDTO receita) throws PessoaInexistenteOuInativaException {
        Receita receitaSalva = receitaDAO.getById(codigo);
        BeanUtils.copyProperties(receita, receitaSalva, "codigo");
        return modelMapper.map(receitaDAO.save(receitaSalva), ReceitaDTO.class);
    }
}

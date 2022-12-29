package com.example.demo.model.bo;

import com.example.demo.model.bo.exceptionhandler.PessoaInexistenteOuInativaException;
import com.example.demo.model.dao.receita.ReceitaDAO;
import com.example.demo.model.dto.despesa.DespesaDTO;
import com.example.demo.model.dto.despesa.ListaDespesaTipoDTO;
import com.example.demo.model.dto.receita.ReceitaDTO;
import com.example.demo.model.entity.Receita;
import com.example.demo.model.util.ModelMapperUtil;
import com.example.demo.model.util.MonetarioUtil;
import com.example.demo.model.util.TotalizadorMoedaDTO;
import com.example.demo.model.util.enuns.TipoMoeda;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ReceitaBO {
    @Autowired
    private ReceitaDAO receitaDAO;

    private ModelMapper modelMapper = ModelMapperUtil.getInstance();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    MonetarioUtil monetarioUtil = MonetarioUtil.getInstance();

    @Autowired
    private ApplicationEventPublisher publisher;

    public TotalizadorMoedaDTO buscaTotalizacaoReceitasAll(List<Receita> receita){
        List<Receita> receitas = receitaDAO.findAll();
        TotalizadorMoedaDTO totalizacao = new TotalizadorMoedaDTO();
        totalizacao.setTotalReal(calculaTotal(receitas, TipoMoeda.REAL));
        totalizacao.setTotalEuro(calculaTotal(receitas, TipoMoeda.EURO));
        totalizacao.setTotalDolar(calculaTotal(receitas, TipoMoeda.DOLAR));
        return totalizacao;
    }

    private String calculaTotal(List<Receita> receitas, TipoMoeda moeda) {
        BigDecimal total = receitas.stream()
                .filter(rec -> rec.getTipomoeda() == moeda)
                .map(rec -> rec.getValor())
                .reduce(BigDecimal.ZERO,BigDecimal::add);

        return monetarioUtil.monetarios(total, 17, moeda);
    }

    public TotalizadorMoedaDTO buscaReceitasAll(){
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
        TotalizadorMoedaDTO totalizacao = buscaTotalizacaoReceitasAll(receitas);
        totalizacao.setListaFinancas(receitaDTOs);
        return totalizacao;
    }



    public ListaDespesaTipoDTO buscaReceitaTipo(){

        List<DespesaDTO> listaVazia = Collections.emptyList();
        BigDecimal valorVazio = new BigDecimal('0');

        return ListaDespesaTipoDTO.builder()
                .build();
    }

    public ReceitaDTO buscaReceitaById(Long codigo) {
        Receita receita = receitaDAO.findById(codigo).get();
        return ReceitaDTO.builder()
                .codigo(receita.getCodigo())
                .nome(receita.getNome())
                .descricao(receita.getDescricao())
                .data(receita.getData())
                .dataformatada(receita.getData().format(formatter))
                .valor(receita.getValor())
                .valorformatado(monetarioUtil.monetarios(receita.getValor(), 17, receita.getTipomoeda()))
                .tipomoeda(receita.getTipomoeda())
                .build();
    }

    public ReceitaDTO criarDespesa(ReceitaDTO receita, HttpServletResponse response) throws PessoaInexistenteOuInativaException {
        Receita receitaSalva = receitaDAO.save(modelMapper.map(receita, Receita.class));
        return modelMapper.map(receita, ReceitaDTO.class);
    }

    public void removerReceita(Long codigo) {
        receitaDAO.deleteById(codigo);
    }


    public ReceitaDTO atualizaReceita(Long codigo, ReceitaDTO receita) throws PessoaInexistenteOuInativaException {
        Receita receitaSalva = receitaDAO.getById(codigo);
        BeanUtils.copyProperties(receita, receitaSalva, "codigo");
        receitaSalva = receitaDAO.save(receitaSalva);
        ReceitaDTO receitaDto = ReceitaDTO.builder()
                .codigo(receitaSalva.getCodigo())
                .nome(receitaSalva.getNome())
                .descricao(receitaSalva.getDescricao())
                .data(receitaSalva.getData())
                .dataformatada(receitaSalva.getData().format(formatter))
                .valor(receitaSalva.getValor())
                .valorformatado(monetarioUtil.monetarios(receitaSalva.getValor(), 17, receita.getTipomoeda()))
                .tipomoeda(receitaSalva.getTipomoeda())
                .build();
        return receitaDto;
    }
}

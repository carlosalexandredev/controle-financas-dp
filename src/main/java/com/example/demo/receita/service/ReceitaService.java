package com.example.demo.receita.service;

import com.example.demo.fortune.exceptions.ResourceNotFoundException;
import com.example.demo.fortune.util.DateUtil;
import com.example.demo.fortune.util.MonetarioUtil;
import com.example.demo.fortune.util.TotalizadorMoedaDTO;
import com.example.demo.fortune.util.enuns.TipoMoeda;
import com.example.demo.receita.dto.ReceitaDTO;
import com.example.demo.receita.dto.ReceitaDTOBuilder;
import com.example.demo.receita.entity.Receita;
import com.example.demo.receita.repository.ReceitaRepository;
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

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ReceitaService {

    private final ReceitaRepository repository;

    private final UserService userService;

    private final ModelMapper modelMapper;


    public TotalizadorMoedaDTO<String> buscaTotalizacaoReceitasAll(List<Receita> receitas) {
        TotalizadorMoedaDTO<String> totalizacao = new TotalizadorMoedaDTO<>();
        totalizacao.setTotalReal(calculaTotal(receitas, TipoMoeda.REAL));
        totalizacao.setTotalEuro(calculaTotal(receitas, TipoMoeda.EURO));
        totalizacao.setTotalDolar(calculaTotal(receitas, TipoMoeda.DOLAR));
        return totalizacao;
    }

    private String calculaTotal(List<Receita> receitas, TipoMoeda moeda) {
        BigDecimal total = receitas.stream()
                .filter(rec -> rec.getTipomoeda() == moeda)
                .map(Receita::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return MonetarioUtil.getInstance().monetarios(total, 17, moeda);
    }

    public TotalizadorMoedaDTO buscaReceitasByPerfil() {
        List<Receita> receitas = repository.findReceitasByUser(userService.getUser());
        List<ReceitaDTO> receitaDTOs = new ArrayList<>();

        if (!receitas.isEmpty()) {
            for (Receita receita : receitas) {
                ReceitaDTO receitaDto = ReceitaDTOBuilder.builder()
                        .codigo(receita.getCodigo())
                        .nome(receita.getNome())
                        .descricao(receita.getDescricao())
                        .data(receita.getData())
                        .dataformatada(receita.getData().format(DateUtil.FORMATTER))
                        .valor(receita.getValor())
                        .valorformatado(MonetarioUtil.getInstance().monetarios(receita.getValor(), 17, receita.getTipomoeda()))
                        .tipomoeda(receita.getTipomoeda())
                        .build();
                receitaDTOs.add(receitaDto);
            }
        }
        TotalizadorMoedaDTO totalizacao = buscaTotalizacaoReceitasAll(receitas);
        totalizacao.setListaFinancas(receitaDTOs);
        return totalizacao;
    }

    public ReceitaDTO buscaReceitaById(Long codigo) {
        Receita receita = repository.findById(codigo).orElseThrow(() -> new ResourceNotFoundException(codigo));
        return ReceitaDTOBuilder.builder()
                .codigo(receita.getCodigo())
                .nome(receita.getNome())
                .descricao(receita.getDescricao())
                .data(receita.getData())
                .dataformatada(receita.getData().format(DateUtil.FORMATTER))
                .valor(receita.getValor())
                .valorformatado(MonetarioUtil.getInstance().monetarios(receita.getValor(), 17, receita.getTipomoeda()))
                .tipomoeda(receita.getTipomoeda())
                .build();
    }

    public ReceitaDTO criarReceita(ReceitaDTO receitaDTO, HttpServletResponse response) {
        Receita receita = modelMapper.map(receitaDTO, Receita.class);
        receita.setUser(userService.getUser());
        return modelMapper.map(repository.save(receita), ReceitaDTO.class);
    }

    public void removerReceita(Long codigo) {
        repository.deleteById(codigo);
    }

    public ReceitaDTO atualizaReceita(Long codigo, ReceitaDTO receita) {
        Receita receitaSalva = repository.getById(codigo);
        BeanUtils.copyProperties(receita, receitaSalva, "codigo");
        receitaSalva = repository.save(receitaSalva);
        return ReceitaDTOBuilder.builder()
                .codigo(receitaSalva.getCodigo())
                .nome(receitaSalva.getNome())
                .descricao(receitaSalva.getDescricao())
                .data(receitaSalva.getData())
                .dataformatada(receitaSalva.getData().format(DateUtil.FORMATTER))
                .valor(receitaSalva.getValor())
                .valorformatado(MonetarioUtil.getInstance().monetarios(receitaSalva.getValor(), 17, receita.getTipomoeda()))
                .tipomoeda(receitaSalva.getTipomoeda())
                .build();
    }
}

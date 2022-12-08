package com.example.demo.model.bo;

import com.example.demo.model.bo.event.RecursoCriadoEvent;
import com.example.demo.model.bo.exceptionhandler.PessoaInexistenteOuInativaException;
import com.example.demo.model.dao.receita.ReceitaDAO;
import com.example.demo.model.dto.despesa.DespesaDTO;
import com.example.demo.model.dto.pessoa.PessoaDTO;
import com.example.demo.model.dto.receita.ReceitaDTO;
import com.example.demo.model.entity.Despesa;
import com.example.demo.model.entity.Receita;
import com.example.demo.model.util.ModelMapperUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReceitaBO {
    @Autowired
    private ReceitaDAO receitaDAO;

    private ModelMapper modelMapper = ModelMapperUtil.getInstance();

    @Autowired
    private ApplicationEventPublisher publisher;

    /**
     * @Method buscaDespesaAll()
     * @Rule 1 - Realiza busca de todos despesas na base de dados.
     **/
    public List<ReceitaDTO> buscaReceitasAll(){
        List<Receita> receitas = receitaDAO.findAll();

        return receitas.stream()
                .map(task -> modelMapper.map(task, ReceitaDTO.class))
                .collect(Collectors.toList());
    }

//    public ListaDespesaTipoDTO buscaDespesasTipo(){
//        List<DespesaDTO> fixas = new ArrayList<>();
//        List<DespesaDTO> variaveis = new ArrayList<>();
//        List<DespesaDTO> extraordinarias = new ArrayList<>();
//        List<DespesaDTO> regulares = new ArrayList<>();
//
//        List<ReceitaDTO> despesas = despesaDAO.findAll()
//                .stream().map(task -> modelMapper.map(task, DespesaDTO.class))
//                .collect(Collectors.toList());
//
//        for (ReceitaDTO despesa: despesas) {
//            if(despesa.getTipodespesa().equals(TipoDespesa.FIXAS))
//                fixas.add(despesa);
//            if(despesa.getTipodespesa().equals(TipoDespesa.VARIAVEIS))
//                variaveis.add(despesa);
//            if(despesa.getTipodespesa().equals(TipoDespesa.EXTRAORDINARIAS))
//                extraordinarias.add(despesa);
//            if(despesa.getTipodespesa().equals(TipoDespesa.REGULARES))
//                regulares.add(despesa);
//        }

//        return ListaDespesaTipoDTO.builder()
//                .despesasFixas(fixas).totalFixas(calculaTotalDespesas(fixas))
//                .despesasVariaveis(variaveis).totalVariaveis(calculaTotalDespesas(variaveis))
//                .despesasExtraordinarias(extraordinarias).totalExtraordinarias(calculaTotalDespesas(extraordinarias))
//                .despesasRegulares(regulares).totalRegulares(calculaTotalDespesas(regulares))
//                .build();
//    }

//    public BigDecimal calculaTotalDespesas(List<DespesaDTO> despesas){
//        return despesas.stream()
//                .map(despesa -> despesa.getValor())
//                .reduce(BigDecimal.ZERO,BigDecimal::add);
//    }

    /**
     * @Method buscaDespesasById(Long codigo)
     * @Rule 1 - Realiza busca de uma única tarefa na base de dados.
     **/
    public Optional<ReceitaDTO> buscaDespesaById(Long codigo) {
        Optional<Receita> despesa = receitaDAO.findById(codigo);
        return Optional.of(modelMapper.map(despesa.get(), ReceitaDTO.class));
    }

    /**
     * @Method criarDespesa(DespesaDTO despesa, HttpServletResponse response)
     * @Rule 1 - Realiza inserção de despesa na base de dados.
     * @Rule 2 - Regras checar Validations em DespesaDTO.
     **/
    public ReceitaDTO criarDespesa(DespesaDTO despesa, HttpServletResponse response) throws PessoaInexistenteOuInativaException {
        Receita receitaSalva = receitaDAO.save(modelMapper.map(despesa, Receita.class));
        publisher.publishEvent(new RecursoCriadoEvent(this, response, receitaSalva.getCodigo()));
        return modelMapper.map(despesa, ReceitaDTO.class);
    }

    /**
     * @Method removerDespesa(Long codigo)
     * @Rule 1 - Remover o despesa pelo seu codigo.
     **/
    public void removerReceita(Long codigo) {
        receitaDAO.deleteById(codigo);
    }


    public PessoaDTO atualizaReceita(Long codigo, DespesaDTO tarefa) throws PessoaInexistenteOuInativaException {
        Receita despesaSalva = receitaDAO.getById(codigo);
        BeanUtils.copyProperties(tarefa, despesaSalva, "codigo");
        return modelMapper.map(receitaDAO.save(despesaSalva), PessoaDTO.class);
    }
}

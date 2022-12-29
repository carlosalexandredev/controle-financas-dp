package com.example.demo.controller.conversor;

import com.example.demo.model.bo.DespesaBO;
import com.example.demo.model.dto.conversor.ConversorDTO;
import com.example.demo.model.dto.despesa.ListaDespesaTipoDTO;
import com.example.demo.model.util.MonetarioUtil;
import com.example.demo.model.util.cotacao.dto.CotacaoDTO;
import com.example.demo.model.util.cotacao.factory.CotacaoFabrica;
import com.example.demo.model.util.cotacao.template_method.CotacaoAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;

@Controller
@RequestMapping("/")
public class HomeConversorControllerImpl implements HomeConversorController {


    MonetarioUtil monetarioUtil = MonetarioUtil.getInstance();

    @Override
    public String conversorDados(ConversorDTO conversor, Model model) throws IOException {
        if(Objects.nonNull(conversor.getMoedaPrimaria()) && Objects.nonNull(conversor.getValor())){
            CotacaoAPI cotacao = CotacaoFabrica.getCotacao(conversor.getMoedaPrimaria());
            CotacaoDTO valorConvertido = cotacao.consultaCotacao(conversor.getValor());
            valorConvertido.setValorConvertidoFormatado(monetarioUtil.monetarios(valorConvertido.getValorConvertido(), 17, conversor.getMoedaPrimaria()));
            model.addAttribute("conversor", valorConvertido);
        }
        return "conversor";
    }
}



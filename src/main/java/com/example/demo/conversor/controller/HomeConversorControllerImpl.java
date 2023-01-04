package com.example.demo.conversor.controller;

import com.example.demo.conversor.dto.ConversorDTO;
import com.example.demo.fortune.util.MonetarioUtil;
import com.example.demo.fortune.util.cotacao.dto.CotacaoDTO;
import com.example.demo.fortune.util.cotacao.factory.CotacaoFabrica;
import com.example.demo.fortune.util.cotacao.template_method.CotacaoAPI;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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



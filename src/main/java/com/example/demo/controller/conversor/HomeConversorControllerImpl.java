package com.example.demo.controller.conversor;

import com.example.demo.model.bo.DespesaBO;
import com.example.demo.model.bo.exceptionhandler.PessoaInexistenteOuInativaException;
import com.example.demo.model.dto.despesa.DespesaDTO;
import com.example.demo.model.dto.despesa.ListaDespesaTipoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeConversorControllerImpl implements HomeConversorController {
    @Autowired
    private DespesaBO despesasBO;

    @Override
    public String conversor(ModelMap model){
        ListaDespesaTipoDTO despesas = despesasBO.buscaDespesasTipo();
        model.addAttribute("despesas", despesas);
        return "conversor";
    }

}

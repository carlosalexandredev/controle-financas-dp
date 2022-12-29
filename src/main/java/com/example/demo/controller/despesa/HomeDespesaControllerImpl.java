package com.example.demo.controller.despesa;

import com.example.demo.model.bo.DespesaBO;
import com.example.demo.model.bo.exceptionhandler.PessoaInexistenteOuInativaException;
import com.example.demo.model.dto.despesa.DespesaDTO;
import com.example.demo.model.dto.despesa.ListaDespesaTipoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeDespesaControllerImpl implements HomeDespesaController {
    @Autowired
    private DespesaBO despesasBO;

    @Override
    public String despesas(ModelMap model){
        ListaDespesaTipoDTO despesas = despesasBO.buscaDespesasTipo();
        model.addAttribute("despesas", despesas);
        return "despesa";
    }

    @Override
    public String salvarDespesa(@Valid @ModelAttribute DespesaDTO despesa, BindingResult result, RedirectAttributes attributes, HttpServletResponse response) throws PessoaInexistenteOuInativaException {
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique se todos os campos foram preechidos!");
            return "redirect:/despesa";
        }
        DespesaDTO depesaSalva = despesasBO.criarDespesa(despesa, response);
        return "redirect:/despesa";
    }

    @Override
    public String handleDeleteDespesa(@RequestParam(name = "codigo") Long codigo) {
        despesasBO.removerDespesa(codigo);
        return "redirect:/despesa";
    }

    @Override
    public String editarDespesa(@PathVariable long codigo, Model model) throws PessoaInexistenteOuInativaException {
        Optional<DespesaDTO> despesa = despesasBO.buscaDespesaById(codigo);
        model.addAttribute("despesa", despesa.get());
        return "updatedespesa";
    }

    @Override
    public String updateDespesa(@PathVariable("codigo") long codigo, @Valid DespesaDTO despesa,
                                BindingResult result, Model model, RedirectAttributes attributes, HttpServletResponse response) throws PessoaInexistenteOuInativaException {
        despesasBO.atualizaDespesa(codigo, despesa);
        if (result.hasErrors()) {
            despesa.setCodigo(codigo);
            model.addAttribute("despesa", despesa);
            attributes.addFlashAttribute("mensagem", "Verifique se todos os campos foram preechidos!");
            return "updatedespesa";
        }
        despesasBO.atualizaDespesa(codigo, despesa);
        model.addAttribute("despesa", despesa);
        return "updatedespesa";
    }

}

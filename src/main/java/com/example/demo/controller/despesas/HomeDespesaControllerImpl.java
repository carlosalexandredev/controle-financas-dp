package com.example.demo.controller.despesas;

import com.example.demo.model.despesa.dto.DespesaDTO;
import com.example.demo.model.despesa.service.DespesaService;
import com.example.demo.model.fortune.exceptions.PessoaInexistenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class HomeDespesaControllerImpl implements HomeDespesaController {
    @Autowired
    private DespesaService depesaService;

    @Override
    public String salvarDespesa(@Valid @ModelAttribute DespesaDTO despesa, BindingResult result, RedirectAttributes attributes, HttpServletResponse response) throws PessoaInexistenteOuInativaException {
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique se todos os campos foram preechidos!");
            return "redirect:/despesa";
        }
        DespesaDTO depesaSalva = depesaService.criarDespesa(despesa, response);
        return "redirect:/despesa";
    }

    @Override
    public String handleDeleteDespesa(@RequestParam(name = "codigo") Long codigo) {
        depesaService.removerDespesa(codigo);
        return "redirect:/despesa";
    }

    @Override
    public String editarDespesa(@PathVariable long codigo, Model model) throws PessoaInexistenteOuInativaException {
        Optional<DespesaDTO> despesa = depesaService.buscaDespesaById(codigo);
        model.addAttribute("despesa", despesa.get());
        return "updatedespesa";
    }

    @Override
    public String updateDespesa(@PathVariable("codigo") long codigo, @Valid DespesaDTO despesa,
                                BindingResult result, Model model, RedirectAttributes attributes, HttpServletResponse response) throws PessoaInexistenteOuInativaException {
        depesaService.atualizaDespesa(codigo, despesa);
        if (result.hasErrors()) {
            despesa.setCodigo(codigo);
            model.addAttribute("despesa", despesa);
            attributes.addFlashAttribute("mensagem", "Verifique se todos os campos foram preechidos!");
            return "updatedespesa";
        }
        depesaService.atualizaDespesa(codigo, despesa);
        model.addAttribute("despesa", despesa);
        return "updatedespesa";
    }

}

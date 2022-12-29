package com.example.demo.controller.investimento;

import com.example.demo.model.bo.InvestimentoBO;
import com.example.demo.model.bo.exceptionhandler.PessoaInexistenteOuInativaException;
import com.example.demo.model.dto.investimento.InvestimentoDTO;
import com.example.demo.model.dto.investimento.ListaInvestimentoTipoDTO;
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
public class HomeInvestimentoControllerImpl implements HomeInvestimentoController {
    @Autowired
    private InvestimentoBO investimentoBO;

    @Override
    public String investimento(ModelMap model){
        ListaInvestimentoTipoDTO investimentos = investimentoBO.buscaDespesasTipo();
        model.addAttribute("investimentos", investimentos);
        return "investimento";
    }

    @Override
    public String salvarInvestimento(@Valid @ModelAttribute InvestimentoDTO investimento, BindingResult result, RedirectAttributes attributes, HttpServletResponse response) throws PessoaInexistenteOuInativaException {
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique se todos os campos foram preechidos!");
            return "redirect:/investimento";
        }
        InvestimentoDTO investimentoSalvo = investimentoBO.criarInvestimento(investimento, response);
        return "redirect:/investimento";
    }

    @Override
    public String handleDeleteInvestimento(@RequestParam(name = "codigo") Long codigo) {
        investimentoBO.removerInvestimento(codigo);

        return "redirect:/investimento";
    }

    @Override
    public String editarInvestimento(@PathVariable long codigo, Model model) {
        InvestimentoDTO investimento = investimentoBO.buscaInvestimentoById(codigo);
        model.addAttribute("investimento", investimento);
        return "updateinvestimento";
    }

//    @Override
//    public String updateInvestimento(@PathVariable("codigo") long codigo, @Valid InvestimentoDTO despesa,
//                                BindingResult result, Model model, RedirectAttributes attributes, HttpServletResponse response) throws PessoaInexistenteOuInativaException {
//        investimentoBO.atualizaDespesa(codigo, despesa);
//        if (result.hasErrors()) {
//            despesa.setCodigo(codigo);
//            model.addAttribute("despesa", despesa);
//            attributes.addFlashAttribute("mensagem", "Verifique se todos os campos foram preechidos!");
//            return "updatedespesa";
//        }
//        investimentoBO.atualizaDespesa(codigo, despesa);
//        model.addAttribute("despesa", despesa);
//        return "updatedespesa";
//    }

}

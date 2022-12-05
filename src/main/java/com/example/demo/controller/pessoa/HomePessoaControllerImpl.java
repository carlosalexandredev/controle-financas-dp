package com.example.demo.controller.pessoa;

import com.example.demo.model.bo.PessoaBO;
import com.example.demo.model.bo.exceptionhandler.PessoaInexistenteOuInativaException;
import com.example.demo.model.dto.pessoa.PessoaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomePessoaControllerImpl implements HomePessoaController {

    @Autowired
    private PessoaBO pessoaBO;

    @Override
    public String funcionario(ModelMap model){
        List<PessoaDTO> funcionarios = pessoaBO.buscaAll();
        model.addAttribute("pessoas", funcionarios);
        return "funcionario";
    }

    @Override
    public String salvarPessoa(@Valid @ModelAttribute PessoaDTO funcionario, BindingResult result, RedirectAttributes attributes, HttpServletResponse response){
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique se todos os campos foram preechidos!");
            return "redirect:/funcionario";
        }
        PessoaDTO user = pessoaBO.criarUser(funcionario, response);
        return "redirect:/funcionario";
    }

    @Override
    public String handleDeletePessoa(@RequestParam(name="codigo")Long codigo) {
        pessoaBO.removerUser(codigo);
        return "redirect:/funcionario";
    }


    @Override
    public String editarPessoa(@PathVariable long codigo, Model model) throws PessoaInexistenteOuInativaException {
        Optional<PessoaDTO> pessoa = Optional.ofNullable(pessoaBO.buscaById(codigo).orElseThrow(PessoaInexistenteOuInativaException::new));
        model.addAttribute("pessoa", pessoa.get());
        return "updateusuario";
    }

    @Override
    public String atualizaPessoa(@PathVariable("codigo") long codigo, @Valid PessoaDTO pessoa,
                                 BindingResult result, Model model, RedirectAttributes attributes) throws PessoaInexistenteOuInativaException {
        pessoaBO.atualizaUser(codigo, pessoa);
        if (result.hasErrors()) {
            pessoa.setCodigo(codigo);
            model.addAttribute("pessoa", pessoa);
            attributes.addFlashAttribute("mensagem", "Verifique se todos os campos foram preechidos!");
            return "updateusuario";
        }
        model.addAttribute("pessoa", pessoa);
        return "updateusuario";
    }

}

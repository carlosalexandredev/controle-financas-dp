package com.example.demo.perfil.controller;

import com.example.demo.fortune.exceptions.PessoaInexistenteOuInativaException;
import com.example.demo.perfil.service.PerfilService;
import com.example.demo.perfil.dto.PerfilDTO;
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
public class HomePerfilControllerImpl implements HomePerfilController {

    @Autowired
    private PerfilService pessoaBO;

    @Override
    public String funcionario(ModelMap model){
        List<PerfilDTO> funcionarios = pessoaBO.buscaAll();
        model.addAttribute("perfils", funcionarios);
        return "funcionario";
    }

    @Override
    public String salvarPessoa(@Valid @ModelAttribute PerfilDTO funcionario, BindingResult result, RedirectAttributes attributes, HttpServletResponse response){
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique se todos os campos foram preechidos!");
            return "redirect:/perfis";
        }
        PerfilDTO user = pessoaBO.criarUser(funcionario, response);
        return "redirect:/perfis";
    }

    @Override
    public String handleDeletePessoa(@RequestParam(name="codigo")Long codigo) {
        pessoaBO.removerUser(codigo);
        return "redirect:/perfis";
    }


    @Override
    public String editarPessoa(@PathVariable long codigo, Model model) throws PessoaInexistenteOuInativaException {
        Optional<PerfilDTO> pessoa = Optional.ofNullable(pessoaBO.buscaById(codigo).orElseThrow(PessoaInexistenteOuInativaException::new));
        model.addAttribute("pessoa", pessoa.get());
        return "updateusuario";
    }

    @Override
    public String atualizaPessoa(@PathVariable("codigo") long codigo, @Valid PerfilDTO pessoa,
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

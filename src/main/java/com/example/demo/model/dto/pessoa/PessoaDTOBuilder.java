package com.example.demo.model.dto.pessoa;

import com.example.demo.model.dto.pessoa.enuns.TipoStatus;
import com.example.demo.model.dto.pessoa.enuns.TipoUsuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public final class PessoaDTOBuilder {
    private Long codigo;
    private @NotNull @Size(max = 80) String nome;
    private @Email @NotNull @Size(max = 100) String email;
    private @NotNull @Size(max = 40) String senha;
    private @NotNull Integer idade;
    private @NotNull String estado;
    private @NotNull TipoStatus status;
    private @NotNull TipoUsuario privilegio;

    private PessoaDTOBuilder() {
    }

    public static PessoaDTOBuilder aPessoaDTO() {
        return new PessoaDTOBuilder();
    }

    public PessoaDTOBuilder codigo(Long codigo) {
        this.codigo = codigo;
        return this;
    }

    public PessoaDTOBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public PessoaDTOBuilder email(String email) {
        this.email = email;
        return this;
    }

    public PessoaDTOBuilder senha(String senha) {
        this.senha = senha;
        return this;
    }

    public PessoaDTOBuilder idade(Integer idade) {
        this.idade = idade;
        return this;
    }

    public PessoaDTOBuilder estado(String estado) {
        this.estado = estado;
        return this;
    }

    public PessoaDTOBuilder status(TipoStatus status) {
        this.status = status;
        return this;
    }

    public PessoaDTOBuilder privilegio(TipoUsuario privilegio) {
        this.privilegio = privilegio;
        return this;
    }

    public PessoaDTO build() {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setCodigo(codigo);
        pessoaDTO.setNome(nome);
        pessoaDTO.setEmail(email);
        pessoaDTO.setSenha(senha);
        pessoaDTO.setIdade(idade);
        pessoaDTO.setEstado(estado);
        pessoaDTO.setStatus(status);
        pessoaDTO.setPrivilegio(privilegio);
        return pessoaDTO;
    }
}

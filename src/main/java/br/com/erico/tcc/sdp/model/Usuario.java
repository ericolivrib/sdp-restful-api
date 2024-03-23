package br.com.erico.tcc.sdp.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @Column(name = "uuid_usuario", nullable = false)
    private UUID id;
    @Column(name = "siape", nullable = false, unique = true)
    private Integer siape;
    @Column(name = "nome", length = 50, nullable = false, unique = true)
    private String nome;
    @Column(name = "email", unique = true, length = 50, nullable = false)
    private String email;
    @Column(name = "senha")
    private byte[] senha;
    @Column(name = "codigo_cadastro", length = 5)
    private String codigoCadastro;
    @Column(name = "cadastro_validado")
    private boolean cadastroValidado;
    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;
    @Column(name = "ultimo_acesso")
    private LocalDate ultimoAcesso;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Projeto> projetos;
    @OneToMany(mappedBy = "responsavel", cascade = CascadeType.ALL)
    private List<ParecerTecnico> pareceresTecnicos;
    @OneToMany(mappedBy = "usuario")
    private List<UsuarioDepartamento> departamentos;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getSiape() {
        return siape;
    }

    public void setSiape(Integer siape) {
        this.siape = siape;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getSenha() {
        return senha;
    }

    public void setSenha(byte[] senha) {
        this.senha = senha;
    }

    public String getCodigoCadastro() {
        return codigoCadastro;
    }

    public void setCodigoCadastro(String codigoCadastro) {
        this.codigoCadastro = codigoCadastro;
    }

    public boolean isCadastroValidado() {
        return cadastroValidado;
    }

    public void setCadastroValidado(boolean cadastroValidado) {
        this.cadastroValidado = cadastroValidado;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDate getUltimoAcesso() {
        return ultimoAcesso;
    }

    public void setUltimoAcesso(LocalDate ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }

    public List<ParecerTecnico> getPareceresTecnicos() {
        return pareceresTecnicos;
    }

    public void setPareceresTecnicos(List<ParecerTecnico> pareceresTecnicos) {
        this.pareceresTecnicos = pareceresTecnicos;
    }

    public List<UsuarioDepartamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<UsuarioDepartamento> departamentos) {
        this.departamentos = departamentos;
    }

}

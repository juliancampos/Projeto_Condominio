package com.una.adm.model;

import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Daniel Lanna
 */
@Entity
@Table(name = "proprietario")
public class Proprietario {
    
    public Proprietario() {
    }
    
    public Proprietario(Long id) {
        this.id = id;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "cpf", nullable = false)
    private String cpf;
    @Column(name = "senha", nullable = false)
    private String senha;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "cel", nullable = false)
    private Long cel;
    @Column(name = "tel", nullable = false)
    private Long tel;
    @OneToMany(mappedBy = "proprietario", fetch = FetchType.EAGER)
    private List<Apartamento> apartamento;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "condominio_id")
    private Condominio condominio;
    @OneToMany(mappedBy = "proprietario", fetch = FetchType.EAGER)
    private List<EventoMobile> evento;
    
    public List<EventoMobile> getEvento() {
        return evento;
    }
    
    public void setEvento(List<EventoMobile> evento) {
        this.evento = evento;
    }
    
    public Condominio getCondominio() {
        return condominio;
    }
    
    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
    }
    
    public List<Apartamento> getApartamento() {
        return apartamento;
    }
    
    public void setApartamento(List<Apartamento> apartamento) {
        this.apartamento = apartamento;
    }
    
    public String getCpf() {
        return cpf;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public Long getCel() {
        return cel;
    }
    
    public void setCel(Long cel) {
        this.cel = cel;
    }
    
    public Long getTel() {
        return tel;
    }
    
    public void setTel(Long tel) {
        this.tel = tel;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getSenha() {
        return senha;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.apartamento);
        hash = 47 * hash + Objects.hashCode(this.condominio);
        hash = 47 * hash + Objects.hashCode(this.evento);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.equals("")) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return true;
    }
    
}

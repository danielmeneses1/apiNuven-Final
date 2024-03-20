package com.example.ListaDeTarefas.model;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

@Entity
@Table
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="titulo", length = 100, nullable = true)
    @NotBlank(message = "O titulo da tarefa não pode estar em branco")
    private String titulo;

    @Column(name="descricao", length = 100, nullable = false)
    private String descricao;

    @Column(name = "data_criacao")
    private Date dataCriacao;

    @Column(name= "data_vencimento")
    private Date dataVencimento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusTarefa status;

    //Construtor padrão
    public Tarefa() {
        this.dataCriacao = new Date();
        //this.dataVencimento = new Date();
        this.status = StatusTarefa.PENDENTE;
        this.descricao = "Tarefa sem descrição";
    }

    //getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public boolean isAtrasada(){
        return dataVencimento != null && dataVencimento.before(new Date());
    }
    public StatusTarefa getStatus() {
        if(isAtrasada()){
            return StatusTarefa.ATRASADA;
        }
        return status;
    }

    public void setStatus(StatusTarefa status) {
        this.status = status;
    }
}

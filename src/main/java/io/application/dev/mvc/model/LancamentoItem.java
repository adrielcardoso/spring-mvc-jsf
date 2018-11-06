package io.application.dev.mvc.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "item")
public class LancamentoItem
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "oid")
    private Long id;

    @Column(name = "valor")
    private float valor;

    @Column(name = "descricao")
    private String descricao;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "lancamento_item",
            joinColumns = { @JoinColumn(name = "oid_item") },
            inverseJoinColumns = { @JoinColumn(name = "oid_lancamento") }
    )
    private List<Lancamento> lancamento = new ArrayList<>();

    public List<Lancamento> getLancamento() {
        return lancamento;
    }

    public void setLancamento(List<Lancamento> lancamento) {
        this.lancamento = lancamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}


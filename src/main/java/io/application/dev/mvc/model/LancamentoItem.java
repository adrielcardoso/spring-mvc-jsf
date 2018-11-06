package io.application.dev.mvc.model;

import javax.persistence.*;
import java.util.*;

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

    @ManyToMany(mappedBy = "itens", cascade = CascadeType.ALL)
    private List<Lancamento> lancamento = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LancamentoItem item = (LancamentoItem) o;
        return Objects.equals(id, item.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


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


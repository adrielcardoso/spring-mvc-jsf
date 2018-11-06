package io.application.dev.mvc.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "lancamento")
public class Lancamento
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "oid")
    private Long id;

    @Column(name="dt_inicial")
    private Date dtInicial;

    @Column(name="dt_final")
    private Date dtFinal;

    @Column(name = "vl_total")
    private float vlTotal;

    @Column(name = "observacao")
    private String observacao;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "lancamento_item",
            joinColumns = { @JoinColumn(name = "oid_lancamento") },
            inverseJoinColumns = { @JoinColumn(name = "oid_item") }
    )
    private List<LancamentoItem> itens = new ArrayList<>();


    public void addItem(LancamentoItem item) {
        this.itens.add(item);
        item.getLancamento().add(this);
    }

    public void removeItem(LancamentoItem item) {
        this.itens.remove(item);
        item.getLancamento().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LancamentoItem)) return false;
        return id != null && id.equals(((LancamentoItem) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    public List<LancamentoItem> getItens() {
        return itens;
    }

    public void setItens(List<LancamentoItem> itens) {
        this.itens = itens;
    }

    public Date getDtInicial() {
        return dtInicial;
    }

    public void setDtInicial(Date dtInicial) {
        this.dtInicial = dtInicial;
    }

    public Date getDtFinal() {
        return dtFinal;
    }

    public void setDtFinal(Date dtFinal) {
        this.dtFinal = dtFinal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getVlTotal() {
        return vlTotal;
    }

    public void setVlTotal(float vlTotal) {
        this.vlTotal = vlTotal;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}


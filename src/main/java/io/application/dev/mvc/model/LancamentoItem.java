package io.application.dev.mvc.model;

import javax.persistence.*;

@Entity
@Table(name = "lancamento_item")
public class LancamentoItem
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "oid")
    private Long oid;

    @Column(name = "valor")
    private float valor;

    @Column(name = "descricao")
    private String descricao;

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
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


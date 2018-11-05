package io.application.dev.mvc.presentation;

import io.application.dev.mvc.DAO.LancamentoItemDAO;
import io.application.dev.mvc.model.LancamentoItem;
import org.primefaces.event.RowEditEvent;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.List;
import java.io.Serializable;

@ManagedBean(name = "LancamentoItemBean")
@RequestScoped
public class LancamentoItemBean implements Serializable
{
    private List<LancamentoItem> itens;
    private LancamentoItem selectedItem;
    private LancamentoItemDAO service;

    private float valor;
    private String descricao;

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

    @PostConstruct
    public void init() {

        if(this.service == null){
            this.service = new LancamentoItemDAO();
        }

        this.itens = this.service.obter();
    }

    public List<LancamentoItem> getItens() {
        return itens;
    }

    public LancamentoItem getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(LancamentoItem selectedItem) {
        this.selectedItem = selectedItem;
    }

    public void setService(LancamentoItemDAO service) {
        this.service = service;
    }

    public void onRowEdit(RowEditEvent event)
    {
        String message = "Item Editado com sucesso";
        try{
            this.service.editar(
                    (LancamentoItem) event.getObject()
            );
        }catch (Exception e){
            message = e.getMessage();
        }
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(message, ((LancamentoItem) event.getObject()).getOid().toString()));
    }

    public void onRowDelete(LancamentoItem lancamentoItem)
    {
        String message = "Item Deletado com sucesso";
        try{
            this.service.deletar(lancamentoItem);
        }catch (Exception e){
            message = e.getMessage();
        }
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(message));
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Editar Cancelado", ((LancamentoItem) event.getObject()).getOid().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onAddNew() {
        LancamentoItem temp = new LancamentoItem();
        temp.setValor(this.valor);
        temp.setDescricao(this.descricao);
        this.service.guardar(temp);
        FacesMessage msg = new FacesMessage("Cadastrado com sucesso", Long.toString(1));
        FacesContext.getCurrentInstance().addMessage(null, msg);

        this.valor = 0;
        this.descricao = null;
    }
}
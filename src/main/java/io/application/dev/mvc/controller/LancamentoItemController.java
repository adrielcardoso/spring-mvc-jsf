package io.application.dev.mvc.controller;

import io.application.dev.mvc.model.LancamentoItem;
import io.application.dev.mvc.service.LancamentoItemService;
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
public class LancamentoItemController implements Serializable
{
    private List<LancamentoItem> itens;
    private LancamentoItem selectedItem;
    private LancamentoItemService service;
    private LancamentoItem newLancamentoItem;

    @PostConstruct
    public void init() {
        this.newLancamentoItem = new LancamentoItem();
        if(this.service == null){
            this.service = new LancamentoItemService();
        }
        this.itens = this.service.obter();
    }

    public List<LancamentoItem> getItens() {
        return itens;
    }

    public LancamentoItem getNewLancamentoItem() {
        return newLancamentoItem;
    }

    public void setNewLancamentoItem(LancamentoItem newLancamentoItem) {
        this.newLancamentoItem = newLancamentoItem;
    }

    public LancamentoItem getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(LancamentoItem selectedItem) {
        this.selectedItem = selectedItem;
    }

    public void setService(LancamentoItemService service) {
        this.service = service;
    }

    public void onRowEdit(RowEditEvent event)
    {
        String message = "Item Editado com sucesso";
        try{
            this.service.editar(
                    (LancamentoItem) event.getObject()
            );
            this.itens = this.service.obter();
        }catch (Exception e){
            message = e.getMessage();
        }
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(message, ((LancamentoItem) event.getObject()).getId().toString()));
    }

    public void onRowDelete(LancamentoItem lancamentoItem)
    {
        String message = "Item Deletado com sucesso";
        try{
            this.service.deletar(lancamentoItem);
            this.itens = this.service.obter();
        }catch (Exception e){
            message = e.getMessage();
        }
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(message));
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Editar Cancelado", ((LancamentoItem) event.getObject()).getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onAddNew() {

        this.service.guardar(this.newLancamentoItem);
        this.itens = this.service.obter();

        FacesMessage msg = new FacesMessage("Cadastrado com sucesso", Long.toString(1));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
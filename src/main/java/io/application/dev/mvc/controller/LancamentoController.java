package io.application.dev.mvc.controller;

import io.application.dev.mvc.model.Lancamento;
import io.application.dev.mvc.model.LancamentoItem;
import io.application.dev.mvc.service.LancamentoItemService;
import io.application.dev.mvc.service.LancamentoService;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "LancamentoBean")
@RequestScoped
public class LancamentoController implements Serializable
{
    private List<Lancamento> data;

    private LancamentoService service;
    private LancamentoItemService lancamentoItemDAO;

    private Lancamento newLancamento;
    private LancamentoItem newItem;

    /**
     * Lista de itens cadastrados
     */
    private DualListModel itensDisponiveis;

    private Lancamento currentLancamento;

    @PostConstruct
    public void init() {
        this.newLancamento = new Lancamento();
        if(this.service == null){
            this.service = new LancamentoService();
        }

        if(this.lancamentoItemDAO == null){
            this.lancamentoItemDAO = new LancamentoItemService();
        }

        this.data = this.service.obter();

        List<LancamentoItem> itemSource = this.lancamentoItemDAO.obter();
        List<LancamentoItem> itemTarget = new ArrayList<>();

        this.itensDisponiveis = new DualListModel<>(itemSource, itemTarget);
    }

    public DualListModel getItensDisponiveis() {
        return itensDisponiveis;
    }

    public void setItensDisponiveis(DualListModel itensDisponiveis) {
        this.itensDisponiveis = itensDisponiveis;
    }

    public LancamentoItem getNewItem() {
        return newItem;
    }

    public void setNewItem(LancamentoItem newItem) {
        this.newItem = newItem;
    }

    public void onRowSelect(SelectEvent selectEvent)
    {
        this.currentLancamento = (Lancamento) selectEvent.getObject();
    }

    public List<Lancamento> getData() {
        return data;
    }

    public void setData(List<Lancamento> data) {
        this.data = data;
    }

    public Lancamento getNewLancamento() {
        return newLancamento;
    }

    public void setNewLancamento(Lancamento newLancamento) {
        this.newLancamento = newLancamento;
    }

    public Lancamento getCurrentLancamento() {
        return currentLancamento;
    }

    public void setCurrentLancamento(Lancamento currentLancamento) {
        this.currentLancamento = currentLancamento;
    }

    //    public void onRowEdit(RowEditEvent event)
//    {
//        String message = "Item Editado com sucesso";
//        try{
//            this.service.editar(
//                    (LancamentoItem) event.getObject()
//            );
//        }catch (Exception e){
//            message = e.getMessage();
//        }
//        FacesContext.getCurrentInstance().addMessage(null,
//                new FacesMessage(message, ((LancamentoItem) event.getObject()).getOid().toString()));
//    }
//
//    public void onRowDelete(LancamentoItem lancamentoItem)
//    {
//        String message = "Item Deletado com sucesso";
//        try{
//            this.service.deletar(lancamentoItem);
//        }catch (Exception e){
//            message = e.getMessage();
//        }
//        FacesContext.getCurrentInstance().addMessage(null,
//                new FacesMessage(message));
//    }
//
//    public void onRowCancel(RowEditEvent event) {
//        FacesMessage msg = new FacesMessage("Editar Cancelado", ((LancamentoItem) event.getObject()).getOid().toString());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }

    public void onAddNew(){
        this.service.guardar(this.newLancamento);
        this.data = this.service.obter();
        FacesMessage msg = new FacesMessage("Cadastrado com sucesso");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onAddNewItem(){

        if(this.getCurrentLancamento() == null){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Selecione primeiro um Lançamento", ""));
            return;
        }

        if(this.itensDisponiveis.getTarget().size() == 0){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Insira primeiro um Item", ""));
            return;
        }

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, String.valueOf(this.itensDisponiveis.getTarget().size()) + " itens", ""));

        try{

            this.service.editar(
                    this.getCurrentLancamento()
            );

            this.data = this.service.obter();

            FacesMessage msg = new FacesMessage("Adicionado com sucesso");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }catch (Exception e){
            FacesMessage msg = new FacesMessage(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void transferEvent(TransferEvent event){
        int size = this.itensDisponiveis.getTarget().size();
        this.itensDisponiveis.getTarget().forEach(single -> {
            this.getCurrentLancamento().getItens().add((LancamentoItem) single);
        });
    }
}
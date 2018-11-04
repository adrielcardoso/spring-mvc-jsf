package io.application.dev.mvc.presentation;

import io.application.dev.mvc.DAO.LancamentoItemDAO;
import io.application.dev.mvc.model.LancamentoItem;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean(name = "LancamentoItemBean")
@RequestScoped
public class LancamentoItemBean
{
    public List<LancamentoItem> obtener() {
        LancamentoItemDAO lancamentoItemDAO = new LancamentoItemDAO();

        return lancamentoItemDAO.obtenerItens();
    }

//    public String editar(Long id) {
//        ClienteDAO clienteDAO= new ClienteDAO();
//        Cliente c= new Cliente();
//        c=clienteDAO.buscar(id);
//        System.out.println("******************************************");
//        System.out.println(c);
//
//        Map<String, Object> sessionMap=FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
//        sessionMap.put("cliente", c);
//        return "/faces/editar.xhtml";
//    }
//
//    public String actualizar(Cliente cliente) {
//        ClienteDAO clienteDAO = new ClienteDAO();
//        clienteDAO.editar(cliente);
//        return "/faces/index.xhtml";
//    }
}
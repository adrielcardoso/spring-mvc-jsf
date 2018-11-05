package io.application.dev.mvc.DAO;

import io.application.dev.mvc.model.JPAUtil;
import io.application.dev.mvc.model.Lancamento;
import io.application.dev.mvc.model.LancamentoItem;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class LancamentoItemDAO
{
    EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();

    public List<LancamentoItem> obter(){
        Query q=entity.createQuery("SELECT c FROM LancamentoItem c");
        return q.getResultList();
    }

    public void guardar(LancamentoItem lancamentoItem) {
        entity.getTransaction().begin();
        entity.persist(lancamentoItem);
        entity.getTransaction().commit();
    }

    public void editar(LancamentoItem lancamentoItem) throws Exception{

        LancamentoItem temp = this.lancamentoExiste(lancamentoItem);

        temp.setValor(lancamentoItem.getValor());
        temp.setDescricao(lancamentoItem.getDescricao());

        entity.getTransaction().begin();
        entity.merge(temp);
        entity.getTransaction().commit();
    }

    public void deletar(LancamentoItem lancamentoItem) throws Exception{

        /**
         * validate object
         */
        LancamentoItem temp = this.lancamentoExiste(lancamentoItem);

        entity.getTransaction().begin();
        entity.remove(temp);
        entity.getTransaction().commit();
    }

    public LancamentoItem lancamentoExiste(LancamentoItem lancamentoItem) throws Exception{
        LancamentoItem temp = this.buscar(lancamentoItem.getOid());
        if(temp == null){
            throw new Exception("Item nao encontrado, tente novamente");
        }
        return temp;
    }

    public LancamentoItem buscar(Long id) {
        return entity.find(LancamentoItem.class,id);
    }
}

package io.application.dev.mvc.DAO;

import io.application.dev.mvc.model.JPAUtil;
import io.application.dev.mvc.model.LancamentoItem;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class LancamentoItemDAO
{
    public List<LancamentoItem> obter(){
        EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
        Query q=entity.createQuery("SELECT c FROM LancamentoItem c");
        List<LancamentoItem> list = q.getResultList();
        return list;
    }

    public void guardar(LancamentoItem lancamentoItem) {
        EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
        entity.getTransaction().begin();
        entity.persist(lancamentoItem);
        entity.getTransaction().commit();
    }

    public void editar(LancamentoItem lancamentoItem) throws Exception{
        EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
        LancamentoItem temp = entity.find(LancamentoItem.class,lancamentoItem.getId());
        if(temp == null){
//            throw new Exception("lancamento nao encontrado");
        }

        temp.setValor(lancamentoItem.getValor());
        temp.setDescricao(lancamentoItem.getDescricao());

        entity.getTransaction().begin();
        entity.merge(temp);
        entity.getTransaction().commit();
    }

    public void deletar(LancamentoItem lancamentoItem){

        EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();

        /**
         * validate object
         */
        LancamentoItem temp = entity.find(LancamentoItem.class,lancamentoItem.getId());

        entity.getTransaction().begin();
        entity.remove(temp);
        entity.getTransaction().commit();
    }
}

package io.application.dev.mvc.DAO;

import io.application.dev.mvc.model.JPAUtil;
import io.application.dev.mvc.model.Lancamento;
import io.application.dev.mvc.model.LancamentoItem;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class LancamentoDAO
{
    public List<Lancamento> obter(){
        EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
        Query q=entity.createQuery("SELECT c FROM Lancamento c");
        List<Lancamento> list = q.getResultList();
        return list;
    }

    public void guardar(Lancamento lancamento) {
        EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
        entity.getTransaction().begin();
        entity.persist(lancamento);
        entity.getTransaction().commit();
    }

    public void editar(Lancamento lancamento) throws Exception{
        EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
        Lancamento temp = entity.find(Lancamento.class,lancamento.getId());
        if(temp == null){
            throw new Exception("lancamento nao encontrado");
        }
        temp.setItens(
                lancamento.getItens()
        );
        entity.merge(temp);
    }

    public void deletar(Lancamento lancamento){
        EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();

        /**
         * validate object
         */
        entity.getTransaction().begin();
        entity.remove(lancamento);
        entity.getTransaction().commit();
    }
}

package io.application.dev.mvc.DAO;

import io.application.dev.mvc.model.JPAUtil;
import io.application.dev.mvc.model.Lancamento;

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

    public void editar(Lancamento lancamento){
        EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
        entity.getTransaction().begin();
        entity.merge(lancamento);
        entity.getTransaction().commit();
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

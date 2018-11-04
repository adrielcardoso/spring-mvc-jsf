package io.application.dev.mvc.DAO;

import io.application.dev.mvc.model.JPAUtil;
import io.application.dev.mvc.model.LancamentoItem;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class LancamentoItemDAO
{
    EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();

    public List<LancamentoItem> obtenerItens()
    {
        Query q=entity.createQuery("SELECT c FROM LancamentoItem c");
        return q.getResultList();
    }
}

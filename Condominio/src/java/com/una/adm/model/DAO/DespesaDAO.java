package com.una.adm.model.DAO;

import static com.una.adm.model.DAO.DAO.getSession;
import com.una.adm.model.Despesa;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Daniel Lanna
 */
public class DespesaDAO extends Entity {

    Session session = getSession();

    public void incluirDespesa(Despesa pDespesa) {
        session.beginTransaction();
        try {
            session.persist(pDespesa);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        session.beginTransaction();
        try {
            Query query = session.createQuery("DELETE from Despesa despesa where despesa.idDesp = :lId");
            query.setParameter("lId", id);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }

    }

    public void alterar(Despesa pDespesa) {
        session.beginTransaction();
        try {
            session.merge(pDespesa);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }

    }

    public Despesa obter(int id) {
        Despesa lDespesa = new Despesa();
        Query query = session.createQuery("from Despesa despesa where despesa.idDesp = :lId");
        query.setParameter("lId", id);
        try {
            lDespesa = (Despesa) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lDespesa;
    }

    public Collection<Despesa> obterTodos(int idCond) {
        List<Despesa> lCollDespesas = new ArrayList<Despesa>();
        Query query = session.createQuery("from Despesa desp where desp.bloco.condominio.idCond = :IdCo");
        query.setParameter("IdCo", idCond);
        try {
            lCollDespesas = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lCollDespesas;
    }

    public Collection<Despesa> obterDespesasPorIdCond(Despesa pdespesa) {
        List<Despesa> lCollDespesas = new ArrayList<Despesa>();
        try {
            Query query = session.createQuery("from Despesa desp where desp.condominio.idCond = :IdCo");
            query.setParameter("IdCo", pdespesa.getBloco().getCondominio());
            lCollDespesas = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lCollDespesas;
    }
}

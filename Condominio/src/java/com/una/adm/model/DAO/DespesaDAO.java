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

    public void incluirDespesa(Despesa pDespesa) {
        Session session = getSession();
        try {
            session.persist(pDespesa);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void excluir(int id) {
        Session session = getSession();
        try {
            Query query = session.createQuery("DELETE from Despesa despesa where despesa.idDesp = :lId");
            query.setParameter("lId", id);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public void alterar(Despesa pDespesa) {
        Session session = getSession();
        try {
            session.merge(pDespesa);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public Despesa obter(int id) {
        Session session = getSession();
        Despesa lDespesa = new Despesa();
        Query query = session.createQuery("from Despesa despesa where despesa.idDesp = :lId");
        query.setParameter("lId", id);
        try {
            lDespesa = (Despesa) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lDespesa;
    }

    public Collection<Despesa> obterTodos(int idCond) {
        Session session = getSession();
        List<Despesa> lCollDespesas = new ArrayList<Despesa>();
        Query query = session.createQuery("from Despesa desp where desp.bloco.condominio.idCond = :IdCo");
        query.setParameter("IdCo", idCond);
        try {
            lCollDespesas = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lCollDespesas;
    }

    public Collection<Despesa> obterDespesasPorIdCond(Despesa pdespesa) {
        Session session = getSession();
        List<Despesa> lCollDespesas = new ArrayList<Despesa>();
        try {
            Query query = session.createQuery("from Despesa desp where desp.condominio.idCond = :IdCo");
            query.setParameter("IdCo", pdespesa.getBloco().getCondominio());
            lCollDespesas = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lCollDespesas;
    }
}

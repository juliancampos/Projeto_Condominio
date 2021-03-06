/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.una.adm.model.DAO;

import com.una.adm.model.Bloco;
import static com.una.adm.model.DAO.DAO.getSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Daniel Lanna
 */
public class BlocoDAO extends DAO {

    public void incluirBloco(Bloco pBloco) {
        Session session = getSession();
        try {
            session.persist(pBloco);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void excluir(Long id) {
        Session session = getSession();
        try {
            Query query = session.createQuery("DELETE from Bloco bloco where bloco.idBloco = :lId");
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

    public void alterar(Bloco pBloco) {
        Session session = getSession();
        try {
            Query query = session.createQuery("update Bloco bloco set bloco.NomeBloco = :lNome, bloco.obsBloco = :lObs where bloco.idBloco = :lId");
            query.setParameter("lNome", pBloco.getNomeBloco());
            query.setParameter("lObs", pBloco.getObsBloco());
            query.setParameter("lId", pBloco.getIdBloco());
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public Bloco obter(Long id) {
        Session session = getSession();
        Bloco lBloco = new Bloco();
        Query query = session.createQuery("from Bloco bloco where bloco.idBloco = :lId");
        query.setParameter("lId", id);
        try {
            lBloco = (Bloco) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lBloco;
    }

    public Collection<Bloco> obterTodos(int idCond) {
        Session session = getSession();
        List<Bloco> lCollBlocos = new ArrayList<Bloco>();
        Query query = session.createQuery("From Bloco bloco  where bloco.condominio.idCond = :IdCo");
        query.setParameter("IdCo", idCond);
        try {
            lCollBlocos = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lCollBlocos;
    }

    public Collection<Bloco> obterBlocosPorIdCond(Bloco pbloco) {
        Session session = getSession();
        List<Bloco> lCollBlocos = new ArrayList<Bloco>();
        try {
            Query query = session.createQuery("from Bloco bloco where bloco.condominio = :IdCo");
            query.setParameter("IdCo", pbloco.getCondominio());
            lCollBlocos = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lCollBlocos;
    }
}

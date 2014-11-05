/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.una.adm.model.DAO;

import com.una.adm.model.Condominio;
import static com.una.adm.model.DAO.DAO.getSession;
import com.una.adm.model.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Daniel Lanna
 */
public class CondominioDAO extends DAO {

    public void incluir(Condominio pCondominio, Usuario pUsuario) {
        Session session = getSession();
        try {
            session.persist(pCondominio);
            session.getTransaction().commit();
            Condominio lCond = obterCondminioPorNome(pCondominio);
            pUsuario.setCondominio(lCond);
            new UsuarioDAO().alterar(pUsuario, false);
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
            Query query = session.createQuery("DELETE from Condominio cond where cond.idCond = :lId");
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

    public void alterar(Condominio pCondominio) {
        Session session = getSession();
        try {
            session.merge(pCondominio);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Condominio obter(int id) {
        Session session = getSession();
        Condominio lCondominio = new Condominio();
        Query query = session.createQuery("from Condominio cond where cond.idCond = :lId");
        query.setParameter("lId", id);
        try {
            lCondominio = (Condominio) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lCondominio;
    }

    public Condominio obterCondminioPorNome(Condominio pCond) {
        Session session = getSession();
        if (!session.isOpen()) {
            session = getSession();
        }
        Condominio lCond = new Condominio();
        Query query = session.createQuery("from Condominio cond where cond.NomeCond = :lnome");
        query.setParameter("lnome", pCond.getNomeCond());
        try {
            lCond = (Condominio) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lCond;
    }
}

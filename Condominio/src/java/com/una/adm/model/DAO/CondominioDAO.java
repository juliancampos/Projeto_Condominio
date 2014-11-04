/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.una.adm.model.DAO;

import com.una.adm.model.Condominio;
import static com.una.adm.model.DAO.DAO.getSession;
import com.una.adm.model.Usuario;
import java.util.ArrayList;
import java.util.Collection;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Daniel Lanna
 */
public class CondominioDAO extends DAO {

    Session session = getSession();

    public void incluir(Condominio pCondominio, Usuario pUsuario) {
        session.beginTransaction();
        try {
            session.persist(pCondominio);
            session.getTransaction().commit();
            Condominio lCond = obterCondminioPorNome(pCondominio);
            pUsuario.setCondominio(lCond);
            new UsuarioDAO().alterar(pUsuario, false);
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void excluir(int id) {
        session.beginTransaction();
        try {
            Query query = session.createQuery("DELETE from Condominio cond where cond.idCond = :lId");
            query.setParameter("lId", id);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }

    }

    public void alterar(Condominio pCondominio) {
        session.beginTransaction();
        try {
            session.merge(pCondominio);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public Condominio obter(int id) {
        Condominio lCondominio = new Condominio();
        Query query = session.createQuery("from Condominio cond where cond.idCond = :lId");
        query.setParameter("lId", id);
        try {
            lCondominio = (Condominio) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lCondominio;
    }

    public Condominio obterPorUsuario(Long id) {
        int i = 0;
        Condominio cond = new Condominio();

        return cond;
    }

    public Collection<Condominio> obterTodos() {
        Collection<Condominio> lCondominios = new ArrayList<Condominio>();
        return lCondominios;
    }

    public Condominio obterCondminioPorNome(Condominio pCond) {
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
        }
        return lCond;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.una.adm.model.DAO;

import com.una.adm.model.Apartamento;
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
public class ApartamentoDAO extends DAO {

    public void incluirApartamento(Apartamento pApartamento) {
        Session session = getSession();
        try {
            session.persist(pApartamento);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void excluir(Apartamento ap) {
        Session session = getSession();
        try {
            session.delete(ap);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public void alterar(Apartamento pApartamento) {
        Session session = getSession();
        try {
            session.merge(pApartamento);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public Apartamento obter(int id) {
        Session session = getSession();
        Apartamento lApartamento = new Apartamento();
        Query query = session.createQuery("from Apartamento apartamento where apartamento.idApart = :lId");
        query.setParameter("lId", id);
        try {
            lApartamento = (Apartamento) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lApartamento;
    }

    public Collection<Apartamento> obterTodos(int idCond) {
        Session session = getSession();
        List<Apartamento> lCollApartamentos = new ArrayList<Apartamento>();
        Query query = session.createQuery("from Apartamento apartamento where apartamento.bloco.condominio.idCond = :IdCo");
        query.setParameter("IdCo", idCond);
        try {
            lCollApartamentos = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lCollApartamentos;
    }

    public Apartamento obterApartamentoPorCPF(Apartamento pApartamento) {
        Session session = getSession();
        Apartamento lApartamento = new Apartamento();
        Query query = session.createQuery("from Apartamento apartamento where apartamento.cpfApart = :lCpf");
        try {
            lApartamento = (Apartamento) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lApartamento;
    }

    public void inserirBlocoApartamento(Apartamento pApartamento) {
        Session session = getSession();
        try {
            Query query = session.createQuery("UPDATE Apartamento apartamento set  id_bloco = :liDBloco where idApart = :IdApart");
            query.setParameter("liDBloco", pApartamento.getBloco().getIdBloco());
            query.setParameter("IdApart", pApartamento.getIdApart());
            query.executeUpdate();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}

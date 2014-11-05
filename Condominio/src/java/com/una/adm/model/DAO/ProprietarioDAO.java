/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.una.adm.model.DAO;

import static com.una.adm.model.DAO.DAO.getSession;
import com.una.adm.model.Proprietario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Daniel Lanna
 */
public class ProprietarioDAO {

    public void incluirProprietario(Proprietario pProprietario) {
        Session session = getSession();
        try {
            session.merge(pProprietario);
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
            Query query = session.createQuery("DELETE from Proprietario proprietario where proprietario.id = :lId");
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

    public void alterar(Proprietario pProprietario) {
        Session session = getSession();
        try {
            session.merge(pProprietario);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public Proprietario obter(Long id) {
        Session session = getSession();
        Proprietario lProprietario = new Proprietario();
        Query query = session.createQuery("from Proprietario proprietario where proprietario.id = :lId");
        query.setParameter("lId", id);
        try {
            lProprietario = (Proprietario) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lProprietario;
    }

    public Collection<Proprietario> obterTodos(int idCond) {
        Session session = getSession();
        List<Proprietario> lCollProprietarios = new ArrayList<Proprietario>();
        Query query = session.createQuery("from Proprietario prop where prop.condominio.idCond = :IdCo");
        query.setParameter("IdCo", idCond);
        try {
            lCollProprietarios = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lCollProprietarios;
    }

    public Proprietario obterProprietarioMobile(String cpf) {
        Session session = getSession();
        Proprietario lProprietario = new Proprietario();
        Query query = session.createQuery("from Proprietario proprietario where proprietario.cpf = :cpf");
        query.setParameter("cpf", cpf);
        try {
            lProprietario = (Proprietario) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lProprietario;
    }
}

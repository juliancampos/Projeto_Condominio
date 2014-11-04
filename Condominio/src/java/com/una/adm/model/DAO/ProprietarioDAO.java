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

    Session session = getSession();

    public void incluirProprietario(Proprietario pProprietario) {
        session.beginTransaction();
        try {
            session.merge(pProprietario);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void excluir(Long id) {
        session.beginTransaction();
        try {
            Query query = session.createQuery("DELETE from Proprietario proprietario where proprietario.id = :lId");
            query.setParameter("lId", id);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }

    }

    public void alterar(Proprietario pProprietario) {
        session.beginTransaction();
        try {
            session.merge(pProprietario);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }

    }

    public Proprietario obter(Long id) {
        Proprietario lProprietario = new Proprietario();
        Query query = session.createQuery("from Proprietario proprietario where proprietario.id = :lId");
        query.setParameter("lId", id);
        try {
            lProprietario = (Proprietario) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lProprietario;
    }

    public Collection<Proprietario> obterTodos(int idCond) {
        List<Proprietario> lCollProprietarios = new ArrayList<Proprietario>();
        Query query = session.createQuery("from Proprietario prop where prop.condominio.idCond = :IdCo");
        query.setParameter("IdCo", idCond);
        try {
            lCollProprietarios = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lCollProprietarios;
    }

    public Proprietario obterProprietarioMobile(String cpf) {
        Proprietario lProprietario = new Proprietario();
        Query query = session.createQuery("from Proprietario proprietario where proprietario.cpf = :cpf");
        query.setParameter("cpf", cpf);
        try {
            lProprietario = (Proprietario) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lProprietario;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.una.adm.model.DAO;

import static com.una.adm.model.DAO.DAO.getSession;
import com.una.adm.model.EventoMobile;
import java.util.ArrayList;
import java.util.Collection;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Daniel Lanna
 */
public class EventoDAO {

    public void incluir(EventoMobile pEventoMobile) {
        Session session = getSession();
        try {
            session.persist(pEventoMobile);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void excluir(EventoMobile pEventoMobile) {
        Session session = getSession();
        try {
            session.delete(pEventoMobile);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public void alterar(EventoMobile pEventoMobile) {
        Session session = getSession();
        try {
            session.merge(pEventoMobile);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public EventoMobile obterEvento(Long id) {
        Session session = getSession();
        EventoMobile lEventoMobile = new EventoMobile();
        Query query = session.createQuery("from EventoMobile event where event.idEvento = :lId");
        query.setParameter("lId", id);
        try {
            lEventoMobile = (EventoMobile) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lEventoMobile;
    }

    public Collection<EventoMobile> obterEventosProprietario(EventoMobile pEventoMobile) {
        Session session = getSession();
        Collection<EventoMobile> lEventos = new ArrayList<EventoMobile>();
        Query query = session.createQuery("from EventoMobile event where event.proprietario.id = :lIdProp");
        query.setParameter("lIdProp", pEventoMobile.getProprietario().getId());
        try {
            lEventos = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lEventos;
    }

    public Collection<EventoMobile> obterEventosCondominio(EventoMobile pEventoMobile) {
        Session session = getSession();
        Collection<EventoMobile> lEventos = new ArrayList<EventoMobile>();
        Query query = session.createQuery("from EventoMobile event where event.condominio.idCond = :lIdCond");
        query.setParameter("lIdCond", pEventoMobile.getCondominio().getIdCond());
        try {
            lEventos = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lEventos;
    }

}

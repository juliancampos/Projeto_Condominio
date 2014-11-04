/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.una.adm.model.DAO;

/**
 *
 * @author Daniel Lanna
 */
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

    private static HibernateUtil me;
    private SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    private HibernateUtil() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            serviceRegistry = new ServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public Session getSession() {
        Session toReturn = sessionFactory.openSession();
        toReturn.beginTransaction();
        return toReturn;
    }

    public static HibernateUtil getInstance() {
        if (me == null) {
            me = new HibernateUtil();
        }
        return me;
    }

}

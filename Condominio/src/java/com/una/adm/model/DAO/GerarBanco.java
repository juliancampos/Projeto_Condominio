package com.una.adm.model.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Daniel Lanna
 */
public class GerarBanco {

    public static void main(String args[]) {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("condominio");
        EntityManager em = emFactory.createEntityManager();
        em.close();
        emFactory.close();
    }
}

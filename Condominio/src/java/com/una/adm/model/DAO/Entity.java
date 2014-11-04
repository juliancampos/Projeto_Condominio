package com.una.adm.model.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Daniel Lanna
 */
public class Entity {

    protected EntityManager criarEntityManager(EntityManagerFactory emFactory) {
        EntityManager em = emFactory.createEntityManager();
        return em;
    }

    protected EntityManagerFactory criarEntityManagerFactory(String pEntidade) {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory(pEntidade);
        return emFactory;
    }

    protected void fechar(EntityManagerFactory emFactory, EntityManager em) {
        em.close();
        emFactory.close();
    }
}

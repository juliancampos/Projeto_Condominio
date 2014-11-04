package com.una.adm.model.DAO;

import org.hibernate.Session;

/**
 *
 * @author Daniel Lanna
 */
public class DAO {

    protected static Session getSession() {
        return HibernateUtil.getInstance().getSession();
    }
}

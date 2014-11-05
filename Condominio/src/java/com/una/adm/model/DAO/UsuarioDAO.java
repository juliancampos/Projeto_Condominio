package com.una.adm.model.DAO;

import static com.una.adm.model.DAO.DAO.getSession;
import com.una.adm.model.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Daniel Lanna
 */
public class UsuarioDAO extends DAO {

    public void incluirUsuario(Usuario pUsuario) {
        Session session = getSession();
        try {
            session.persist(pUsuario);
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
            session.delete(id);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public void alterar(Usuario pUsuario, boolean isComitar) {
        Session session = getSession();
        try {
            if (isComitar) {
                session.merge(pUsuario);
                session.getTransaction().commit();
            } else {
                session.merge(pUsuario);
                session.beginTransaction().commit();
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public Usuario obter(Long id) {
        Session session = getSession();
        Usuario lUsuario = new Usuario();
        Query query = session.createQuery("from Condominio cond where cond.idCond = :lId");
        query.setParameter("lnome", id);
        try {
            lUsuario = (Usuario) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lUsuario;
    }

    public Usuario obterUsuarioLogin(Usuario usu) throws Exception {
        Session session = getSession();
        Usuario lUsu = new Usuario();
        Query query = session.createQuery("from Usuario us where us.email = :lemail and senha = :lsenha");
        query.setParameter("lemail", usu.getEmail());
        query.setParameter("lsenha", usu.getSenha());
        try {
            lUsu = (Usuario) query.uniqueResult();
        } catch (Exception e) {
            throw new Exception();
        } finally {
            session.close();
        }
        return lUsu;

    }

    public Usuario obterUsuarioLogado(Usuario usu) {
        Session session = getSession();
        Usuario lUsu = new Usuario();
        Query query = session.createQuery("from Usuario us where us.email = :lemail");
        query.setParameter("lemail", usu.getEmail());
        try {
            lUsu = (Usuario) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return lUsu;
    }
}

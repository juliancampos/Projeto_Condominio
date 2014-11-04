package com.una.adm.model.DAO;

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
public class UsuarioDAO extends DAO {

    Session session = getSession();

    public void incluirUsuario(Usuario pUsuario) {
        session.beginTransaction();
        try {
            session.persist(pUsuario);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }

    }

    public void excluir(Long id) {
        session.getTransaction().begin();
        try {
            session.delete(id);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }

    }

    public void alterar(Usuario pUsuario, boolean isComitar) {
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
        }

    }

    public Usuario obter(Long id) {
        Usuario lUsuario = new Usuario();
        Query query = session.createQuery("from Condominio cond where cond.idCond = :lId");
        query.setParameter("lnome", id);
        try {
            lUsuario = (Usuario) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lUsuario;
    }

    public Usuario obterPorUsuario(Long id) {
        int i = 0;
        Usuario bloc = new Usuario();

        String sql = "select * from usuario where idUsuario = " + id;
        return bloc;
    }

    public Collection<Usuario> obterTodos() {
        Collection<Usuario> lUsuario = new ArrayList<Usuario>();
        return lUsuario;
    }

    public Collection<Usuario> obterUsuariosPorIdCond(Usuario pusuario) {
        Collection<Usuario> lUsuario = new ArrayList<Usuario>();
        return lUsuario;
    }

    public Usuario obterUsuarioLogin(Usuario usu) throws Exception {
        Usuario lUsu = new Usuario();
        Query query = session.createQuery("from Usuario us where us.email = :lemail and senha = :lsenha");
        query.setParameter("lemail", usu.getEmail());
        query.setParameter("lsenha", usu.getSenha());
        try {
            lUsu = (Usuario) query.uniqueResult();
        } catch (Exception e) {
            throw new Exception();
        }
        return lUsu;

    }

    public Usuario obterUsuarioLogado(Usuario usu) {
        Usuario lUsu = new Usuario();
        Query query = session.createQuery("from Usuario us where us.email = :lemail");
        query.setParameter("lemail", usu.getEmail());
        try {
            lUsu = (Usuario) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lUsu;
    }
}

package com.una.adm.model.DAO;

import com.una.adm.model.Apartamento;
import com.una.adm.model.Bloco;
import com.una.adm.model.Condominio;
import static com.una.adm.model.DAO.DAO.getSession;
import java.math.BigDecimal;
import java.text.NumberFormat;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Daniel Lanna
 */
public class BoletoDAO {

    Session session = getSession();
    public static Query query;

    public static void main(String[] args) {
        Condominio pCondominio = new Condominio();
        pCondominio.setIdCond(34);
        Bloco pBloco = new Bloco();
        pBloco.setIdBloco(3L);
        Apartamento pApartamento = new Apartamento();
        pApartamento.setIdApart(2);
        System.out.println("Condominio = " + new BoletoDAO().obterValorPorCondominio(pCondominio));
        System.out.println("Bloco = " + new BoletoDAO().obterValorPorBloco(pCondominio, pBloco));
        System.out.println("Apartamento = " + new BoletoDAO().obterValorPorApartamento(pCondominio, pApartamento));

    }

    public Double obterValorPorCondominio(Condominio pCondominio) {
        Double valorTotal = 0D;
        try {
            Query query = session.createSQLQuery("select sum(valor) from despesa where tipo = 1 and condominio_id = :lIdCond");
            query.setParameter("lIdCond", pCondominio.getIdCond());
            valorTotal = (Double) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valorTotal;
    }

    public Double obterValorPorBloco(Condominio pCondominio, Bloco pBloco) {
        Double valorTotal = 0D;
        try {
            Query query = session.createSQLQuery("select sum(valor) / (select count(id) from proprietario where proprietario.bloco_id = :lBloco) \n"
                    + "from despesa where tipo = 2 and despesa.condominio_id = :lIdCond");
            query.setParameter("lBloco", pBloco.getIdBloco());
            query.setParameter("lIdCond", pCondominio.getIdCond());
            valorTotal = (Double) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valorTotal;
    }

    public Double obterValorPorApartamento(Condominio pCondominio, Apartamento pApartamento) {
        Double valorTotal = 0D;
        try {
            Query query = session.createSQLQuery("select sum(valor) from despesa where tipo = 3 and apartamento_id = :lIdApart and condominio_id = :lIdCond");
            query.setParameter("lIdApart", pApartamento.getIdApart());
            query.setParameter("lIdCond", pCondominio.getIdCond());
            valorTotal = (Double) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valorTotal;
    }
    
    public Double obterApartamento(Condominio pCondominio, Apartamento pApartamento) {
        Double valorTotal = 0D;
        try {
            Query query = session.createSQLQuery("select apartamento_id from despesa where tipo = 3 and apartamento_id = :lIdApart and condominio_id = :lIdCond");
            query.setParameter("lIdApart", pApartamento.getIdApart());
            query.setParameter("lIdCond", pCondominio.getIdCond());
            valorTotal = (Double) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return valorTotal;
    }
}

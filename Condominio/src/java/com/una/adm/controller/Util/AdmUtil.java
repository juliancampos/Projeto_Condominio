/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.una.adm.controller.Util;

import com.una.adm.model.Condominio;
import com.una.adm.model.Proprietario;
import com.una.adm.model.Usuario;
import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Daniel Lanna
 */
public class AdmUtil {

    public Usuario obterUsuarioLogado(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(true);
        Usuario usuario = (Usuario) session.getAttribute("Usuario");
        return usuario;
    }

    public Condominio obterCondominioUsuario(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(true);
        Condominio cond = (Condominio) session.getAttribute("Condominio");
        return cond;
    }

    public void inserirCondominioSessao(HttpServletRequest request, Usuario pUsuario) throws Exception {
        HttpSession session = request.getSession(true);
        session.removeAttribute("Condominio");
        session.setAttribute("Condominio", pUsuario.getCondominio());
    }

    public void inserirProprietarioSessaoMobile(HttpServletRequest request, Proprietario pProprietario) throws Exception {
        HttpSession session = request.getSession(true);
        session.setAttribute("ProprietarioMobile", pProprietario);
    }

    public Proprietario obterProprietarioSessaoMobile(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(true);
        Proprietario prop = (Proprietario) session.getAttribute("ProprietarioMobile");
        return prop;
    }

    public static String formatCPF(String cpf) {
        String pattern = "###.###.###-##";
        MaskFormatter mask;
        try {
            mask = new MaskFormatter(pattern);
            mask.setValueContainsLiteralCharacters(false);
            return mask.valueToString(cpf);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String formatCNPJ(String cpf) {
        String pattern = "##.###.###/####-##";
        MaskFormatter mask;
        try {
            mask = new MaskFormatter(pattern);
            mask.setValueContainsLiteralCharacters(false);
            return mask.valueToString(cpf);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String formatCep(String cpf) {
        String pattern = "#####-###";
        MaskFormatter mask;
        try {
            mask = new MaskFormatter(pattern);
            mask.setValueContainsLiteralCharacters(false);
            return mask.valueToString(cpf);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public int getMod11(String num) {

        //variáveis de instancia  
        int soma = 0;
        int resto = 0;
        int dv = 0;
        String[] numeros = new String[num.length() + 1];
        int multiplicador = 2;

        for (int i = num.length(); i > 0; i--) {
            //Multiplica da direita pra esquerda, incrementando o multiplicador de 2 a 9  
            //Caso o multiplicador seja maior que 9 o mesmo recomeça em 2  
            if (multiplicador > 9) {
                // pega cada numero isoladamente    
                multiplicador = 2;
                numeros[i] = String.valueOf(Integer.valueOf(num.substring(i - 1, i)) * multiplicador);
                multiplicador++;
            } else {
                numeros[i] = String.valueOf(Integer.valueOf(num.substring(i - 1, i)) * multiplicador);
                multiplicador++;
            }
        }

        //Realiza a soma de todos os elementos do array e calcula o digito verificador  
        //na base 11 de acordo com a regra.       
        for (int i = numeros.length; i > 0; i--) {
            if (numeros[i - 1] != null) {
                soma += Integer.valueOf(numeros[i - 1]);
            }
        }
        resto = soma % 11;
        dv = 11 - resto;

        //retorna o digito verificador  
        return dv;
    }
}

package com.una.adm.controller.boleto;

import com.una.adm.controller.Util.AdmUtil;
import com.una.adm.model.Apartamento;
import com.una.adm.model.Bloco;
import com.una.adm.model.BoletoModel;
import com.una.adm.model.Condominio;
import com.una.adm.model.DAO.BoletoDAO;
import com.una.adm.model.Proprietario;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;
import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.view.BoletoViewer;
import org.jrimum.domkee.comum.pessoa.endereco.CEP;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.comum.pessoa.endereco.UnidadeFederativa;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.SacadorAvalista;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo.Aceite;

/**
 *
 * @author Daniel Lanna
 */
public class GerarBoleto {

    public static void main(String args[]) {
//        /*
//         * INFORMANDO DADOS SOBRE O CEDENTE.
//         */
//        Cedente cedente = new Cedente("PROJETO JRimum", "00.000.208/0001-00");
//
//        /*
//         * INFORMANDO DADOS SOBRE O SACADO.
//         */
//        Sacado sacado = new Sacado("JavaDeveloper Pronto Para Férias", "222.222.222-22");
//
//        // Informando o endereço do sacado.
//        Endereco enderecoSac = new Endereco();
//        enderecoSac.setUF(UnidadeFederativa.RN);
//        enderecoSac.setLocalidade("Natal");
//        enderecoSac.setCep(new CEP("59064-120"));
//        enderecoSac.setBairro("Grande Centro");
//        enderecoSac.setLogradouro("Rua poeta dos programas");
//        enderecoSac.setNumero("1");
//        sacado.addEndereco(enderecoSac);
//
//        /*
//         * INFORMANDO DADOS SOBRE O SACADOR AVALISTA.
//         */
//        SacadorAvalista sacadorAvalista = new SacadorAvalista("JRimum Enterprise", "00.000.000/0001-91");
//
//        // Informando o endereço do sacador avalista.
//        Endereco enderecoSacAval = new Endereco();
//        enderecoSacAval.setUF(UnidadeFederativa.DF);
//        enderecoSacAval.setLocalidade("Brasília");
//        enderecoSacAval.setCep(new CEP("59000-000"));
//        enderecoSacAval.setBairro("Grande Centro");
//        enderecoSacAval.setLogradouro("Rua Eternamente Principal");
//        enderecoSacAval.setNumero("001");
//        sacadorAvalista.addEndereco(enderecoSacAval);
//
//        /*
//         * INFORMANDO OS DADOS SOBRE O TÍTULO.
//         */
//
//        // Informando dados sobre a conta bancária do título.
//        ContaBancaria contaBancaria = new ContaBancaria(BancosSuportados.BANCO_BRADESCO.create());
//        contaBancaria.setNumeroDaConta(new NumeroDaConta(123456, "0"));
//        contaBancaria.setCarteira(new Carteira(30));
//        contaBancaria.setAgencia(new Agencia(1234, "1"));
//
//        Titulo titulo = new Titulo(contaBancaria, sacado, cedente, sacadorAvalista);
//        titulo.setNumeroDoDocumento("123456");
//        titulo.setNossoNumero("99345678912");
//        titulo.setDigitoDoNossoNumero("5");
//        titulo.setValor(BigDecimal.valueOf(0.23));
//        titulo.setDataDoDocumento(new Date());
//        titulo.setDataDoVencimento(new Date());
//        titulo.setTipoDeDocumento(TipoDeTitulo.DM_DUPLICATA_MERCANTIL);
//        titulo.setAceite(Aceite.A);
//        titulo.setDesconto(new BigDecimal(0.05));
//        titulo.setDeducao(BigDecimal.ZERO);
//        titulo.setMora(BigDecimal.ZERO);
//        titulo.setAcrecimo(BigDecimal.ZERO);
//        titulo.setValorCobrado(BigDecimal.ZERO);
//
//        /*
//         * INFORMANDO OS DADOS SOBRE O BOLETO.
//         */
//        Boleto boleto = new Boleto(titulo);
//
//        boleto.setLocalPagamento("Pagável preferencialmente na Rede X ou em "
//                + "qualquer Banco até o Vencimento.");
//        boleto.setInstrucaoAoSacado("Senhor sacado, sabemos sim que o valor "
//                + "cobrado não é o esperado, aproveite o DESCONTÃO!");
//        boleto.setInstrucao1("PARA PAGAMENTO 1 até Hoje não cobrar nada!");
//        boleto.setInstrucao2("PARA PAGAMENTO 2 até Amanhã Não cobre!");
//        boleto.setInstrucao3("PARA PAGAMENTO 3 até Depois de amanhã, OK, não cobre.");
//        boleto.setInstrucao4("PARA PAGAMENTO 4 até 04/xx/xxxx de 4 dias atrás COBRAR O VALOR DE: R$ 01,00");
//        boleto.setInstrucao5("PARA PAGAMENTO 5 até 05/xx/xxxx COBRAR O VALOR DE: R$ 02,00");
//        boleto.setInstrucao6("PARA PAGAMENTO 6 até 06/xx/xxxx COBRAR O VALOR DE: R$ 03,00");
//        boleto.setInstrucao7("PARA PAGAMENTO 7 até xx/xx/xxxx COBRAR O VALOR QUE VOCÊ QUISER!");
//        boleto.setInstrucao8("APÓS o Vencimento, Pagável Somente na Rede X.");
//
//        /*
//         * GERANDO O BOLETO BANCÁRIO.
//         */
//        // Instanciando um objeto "BoletoViewer", classe responsável pela
//        // geração do boleto bancário.
//        BoletoViewer boletoViewer = new BoletoViewer(boleto);
//
//        // Gerando o arquivo. No caso o arquivo mencionado será salvo na mesma
//        // pasta do projeto. Outros exemplos:
//        // WINDOWS: boletoViewer.getAsPDF("C:/Temp/MeuBoleto.pdf");
//        // LINUX: boletoViewer.getAsPDF("/home/temp/MeuBoleto.pdf");
//        File arquivoPdf = boletoViewer.getPdfAsFile("MeuPrimeiroBoleto.pdf");
//
//        // Mostrando o boleto gerado na tela.
//        mostreBoletoNaTela(arquivoPdf);
        gerarBoleto();
    }
    private static BoletoModel boletoModel = new BoletoModel();

    /*
     * GERANDO O BOLETO BANCÁRIO.
     */
    public static Boleto gerarBoletoPorApartamento(Condominio pCondominio, Bloco pBloco, Apartamento pApartamento, Proprietario pProprietario) {
        /*
         * INFORMANDO DADOS SOBRE O CEDENTE.
         */
        Cedente cedente = new Cedente(pCondominio.getNomeCond(), new AdmUtil().formatCNPJ(pCondominio.getIdentificao().toString()));
        boletoModel.setCedente(cedente);
        /*
         * INFORMANDO DADOS SOBRE O SACADO.
         */
        Sacado sacado = new Sacado(pProprietario.getNome(), pProprietario.getCpf());
        boletoModel.setSacado(sacado);

        boletoModel.setEndereco(setarEndereco(pCondominio));

        /*
         * INFORMANDO DADOS SOBRE O SACADOR AVALISTA.
         */
        SacadorAvalista sacadorAvalista = new SacadorAvalista(pCondominio.getNomeCond(), new AdmUtil().formatCNPJ(pCondominio.getIdentificao().toString()));

        sacado.addEndereco(boletoModel.getEndereco());

        sacadorAvalista.addEndereco(boletoModel.getEndereco());

        /*
         * INFORMANDO OS DADOS SOBRE O BOLETO.
         */
        Boleto boleto = new Boleto(criarTitulo(pCondominio, boletoModel.getSacado(), boletoModel.getCedente(), sacadorAvalista, pBloco));

        boleto.setLocalPagamento("Pagável preferencialmente na Rede Bradesco ou em "
                + "qualquer Banco até o Vencimento.");
        boleto.setInstrucaoAoSacado("Sr(a)." + pProprietario.getNome() + ", Pague até a data do vencimento para evitar transtornos!");
        boleto.setInstrucao1("APÓS o Vencimento, Pagável Somente na Rede Bradesco");
//        boleto.setInstrucao2("PARA PAGAMENTO 2 até Amanhã Não cobre!");
//        boleto.setInstrucao3("PARA PAGAMENTO 3 até Depois de amanhã, OK, não cobre.");
//        boleto.setInstrucao4("PARA PAGAMENTO 4 até 04/xx/xxxx de 4 dias atrás COBRAR O VALOR DE: R$ 01,00");
//        boleto.setInstrucao5("PARA PAGAMENTO 5 até 05/xx/xxxx COBRAR O VALOR DE: R$ 02,00");
//        boleto.setInstrucao6("PARA PAGAMENTO 6 até 06/xx/xxxx COBRAR O VALOR DE: R$ 03,00");
//        boleto.setInstrucao7("PARA PAGAMENTO 7 até xx/xx/xxxx COBRAR O VALOR QUE VOCÊ QUISER!");
//        boleto.setInstrucao8("APÓS o Vencimento, Pagável Somente na Rede X.");

        return boleto;

    }

    public static void gerarBoleto() {
        Condominio pCondominio = new Condominio();
        pCondominio.setNomeCond("Nome do Condomínio deve vir Aqui!!");
        pCondominio.setCep("3330669");
        pCondominio.setNumero(58);
        pCondominio.setIdCond(34);
        pCondominio.setIdentificao("42242970000143");
        pCondominio.setEstado("Minas Gerais");
        pCondominio.setConta("996658");
        pCondominio.setDigitoConta(new AdmUtil().getMod11(pCondominio.getConta()));
        pCondominio.setAgencia("886");
        pCondominio.setDigitoAgencia(9);
        Bloco pBloco = new Bloco();
        pBloco.setIdBloco(3L);
        Apartamento pApartamento = new Apartamento();
        pApartamento.setIdApart(2);
        Proprietario prop = new Proprietario();
        prop.setNome("Dalai");
        prop.setCpf("63035781443");

        // Instanciando um objeto "BoletoViewer", classe responsável pela
        // geração do boleto bancário.
        BoletoViewer boletoViewer = new BoletoViewer(gerarBoletoPorApartamento(pCondominio, pBloco, pApartamento, prop));

        // Gerando o arquivo. No caso o arquivo mencionado será salvo na mesma
        // pasta do projeto. Outros exemplos:
        // WINDOWS: boletoViewer.getAsPDF("C:/Temp/MeuBoleto.pdf");
        // LINUX: boletoViewer.getAsPDF("/home/temp/MeuBoleto.pdf");
        File arquivoPdf = boletoViewer.getPdfAsFile("NOMEDOCONDOMINO_DESPESA.pdf");

        // Mostrando o boleto gerado na tela.
        mostreBoletoNaTela(arquivoPdf);
    }

    /**
     * Exibe o arquivo na tela.
     *
     * @param arquivoBoleto
     */
    private static void mostreBoletoNaTela(File arquivoBoleto) {

        java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

        try {
            desktop.open(arquivoBoleto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Endereco setarEndereco(Condominio pCondominio) {
        // Informando o endereço do sacado.
        Endereco enderecoSac = new Endereco();
        enderecoSac.setUF(UnidadeFederativa.MG);
        enderecoSac.setLocalidade(pCondominio.getEstado());
        enderecoSac.setCep(new CEP(new AdmUtil().formatCep(pCondominio.getCep())));
        enderecoSac.setBairro(pCondominio.getBairro());
        enderecoSac.setLogradouro(pCondominio.getRua());
        enderecoSac.setNumero(String.valueOf(pCondominio.getNumero()));
        return enderecoSac;
    }

    private static ContaBancaria setarContaBancaria(Condominio pCondominio) throws NumberFormatException {
        // Informando dados sobre a conta bancária do título.
        ContaBancaria contaBancaria = new ContaBancaria(BancosSuportados.BANCO_BRADESCO.create());
        contaBancaria.setNumeroDaConta(new NumeroDaConta(Integer.valueOf(pCondominio.getConta()), String.valueOf(pCondominio.getDigitoConta())));
        contaBancaria.setCarteira(new Carteira(30));
        contaBancaria.setAgencia(new Agencia(Integer.valueOf(pCondominio.getAgencia()), String.valueOf(pCondominio.getDigitoAgencia())));
        return contaBancaria;
    }

    private static Titulo criarTitulo(Condominio pCondominio, Sacado sacado, Cedente cedente, SacadorAvalista sacadorAvalista, Bloco pBloco) throws NumberFormatException {
        Random random = new Random(999999999);
        Titulo titulo = new Titulo(setarContaBancaria(pCondominio), sacado, cedente, sacadorAvalista);
        titulo.setNumeroDoDocumento(String.valueOf(random.nextInt(150000)));
        titulo.setNossoNumero(setarNossoNumero());
        titulo.setDigitoDoNossoNumero(String.valueOf(new AdmUtil().getMod11(titulo.getNossoNumero().toString())));
        titulo.setValor(BigDecimal.valueOf(new BoletoDAO().obterValorPorBloco(pCondominio, pBloco)));
        titulo.setDataDoDocumento(new Date());
        titulo.setDataDoVencimento(new Date());
        titulo.setTipoDeDocumento(TipoDeTitulo.RC_RECIBO);
        titulo.setAceite(Aceite.A);
        titulo.setDesconto(BigDecimal.ZERO);
        titulo.setDeducao(BigDecimal.ZERO);
        titulo.setMora(BigDecimal.ZERO);
        titulo.setAcrecimo(BigDecimal.ZERO);
        titulo.setValorCobrado(BigDecimal.ZERO);
        return titulo;
    }

    private static String setarNossoNumero() {
        Random random = new Random(999999999);
        String a = String.valueOf(random.nextDouble()).replace("-", "").replace(".", "");
        String b = a.substring(0, 11);
        return b;
    }
}

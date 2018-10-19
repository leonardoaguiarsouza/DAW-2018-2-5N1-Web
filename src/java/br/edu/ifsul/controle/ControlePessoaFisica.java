/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CidadeDAO;
import br.edu.ifsul.dao.PessoaFisicaDAO;
import br.edu.ifsul.dao.DAOGenerico;
import br.edu.ifsul.dao.PaisDAO;
import br.edu.ifsul.dao.ProdutoDAO;
import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.Endereco;
import br.edu.ifsul.modelo.PessoaFisica;
import br.edu.ifsul.modelo.Produto;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author 201613260113
 */
@ManagedBean(name = "controlePessoaFisica")
@ViewScoped
public class ControlePessoaFisica implements Serializable{
    private PessoaFisicaDAO<PessoaFisica> dao;
    private PessoaFisica objeto;
    private CidadeDAO<Cidade> daoCidade;
    private ProdutoDAO<Produto> daoProduto;
    private Endereco endereco;
    private Boolean novoEndereco;

    public ControlePessoaFisica() {
        dao = new PessoaFisicaDAO<>();
        daoCidade = new CidadeDAO<>();
        daoProduto = new ProdutoDAO<>();
        
    }

    public String listar(){
        return "/privado/pessoafisica/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new PessoaFisica();
    }
    
    public void salvar(){
        boolean persistiu;
        if(objeto.getId() == null){
            persistiu = dao.persist(objeto);
        } else {
            persistiu = dao.merge(objeto);
        }
        if(persistiu){
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());            
        }
    }
    
    public String cancelar(){
        return "listar?faces-redirect=true";
    }
    
    public void editar(Integer id){
        try {
            objeto = dao.localizar(id);
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void remover(Integer id){
        objeto = dao.localizar(id);
        if(dao.remover(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }
    
    public void novoEndereco(){
        endereco = new Endereco();
        novoEndereco = true;
    }
    
    public void editarEndereco(int index){
        endereco = objeto.getEnderecos().get(index);
        novoEndereco = false;
    }
    
    public void salvarEndereco(){
        if(novoEndereco){
            objeto.adicionarEndereco(endereco);
        }
        Util.mensagemInformacao("Endereço atualizado com sucesso!");
    }
    
    public void removerEndereco(int index){
        objeto.removerEndereco(index);
        Util.mensagemInformacao("Endereço removido com sucesso!");
    }
    
    /**
     * @return the da
     */
    public PessoaFisicaDAO getDao() {
        return dao;
    }

    /**
     * @param da the da to set
     */
    public void setDao(PessoaFisicaDAO dao) {
        this.dao = dao;
    }

    /**
     * @return the objeto
     */
    public PessoaFisica getObjeto() {
        return objeto;
    }

    /**
     * @param objeto the objeto to set
     */
    public void setObjeto(PessoaFisica objeto) {
        this.objeto = objeto;
    }   

    /**
     * @return the daoCidade
     */
    public CidadeDAO<Cidade> getDaoCidade() {
        return daoCidade;
    }

    /**
     * @param daoCidade the daoCidade to set
     */
    public void setDaoCidade(CidadeDAO<Cidade> daoCidade) {
        this.daoCidade = daoCidade;
    }

    /**
     * @return the daoProduto
     */
    public ProdutoDAO<Produto> getDaoProduto() {
        return daoProduto;
    }

    /**
     * @param daoProduto the daoProduto to set
     */
    public void setDaoProduto(ProdutoDAO<Produto> daoProduto) {
        this.daoProduto = daoProduto;
    }

    /**
     * @return the endereco
     */
    public Endereco getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    /**
     * @return the novoEndereco
     */
    public Boolean getNovoEndereco() {
        return novoEndereco;
    }

    /**
     * @param novoEndereco the novoEndereco to set
     */
    public void setNovoEndereco(Boolean novoEndereco) {
        this.novoEndereco = novoEndereco;
    }
    
}

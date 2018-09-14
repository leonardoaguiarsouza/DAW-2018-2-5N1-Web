/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.EstadoDAO;
import br.edu.ifsul.dao.PaisDAO;
import br.edu.ifsul.modelo.Estado;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author 201613260113
 */
@ManagedBean(name = "controleEstado")
@SessionScoped
public class ControleEstado implements Serializable{
    private EstadoDAO dao;
    private Estado objeto;
    private PaisDAO daoPais;

    public ControleEstado() {
        dao = new EstadoDAO();
        daoPais = new PaisDAO();
    }

    public String listar(){
        return "/privado/estado/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Estado();
        return "formulario?faces-redirect=true";
    }
    
    public String salvar(){
        if(dao.salvar(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
            return "listar?faces-redirect=true";
        } else {
            Util.mensagemErro(dao.getMensagem());
            return "formulario?faces-redirect=true";
        }
    }
    
    public String cancelar(){
        return "listar?faces-redirect=true";
    }
    
    public String editar(Integer id){
        try {
            objeto = dao.localizar(id);
            return "formulario?faces-redirect=true";
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
            return "listar?faces-redirect=true";
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
    
    /**
     * @return the da
     */
    public EstadoDAO getDao() {
        return dao;
    }

    /**
     * @param da the da to set
     */
    public void setDao(EstadoDAO dao) {
        this.dao = dao;
    }

    /**
     * @return the objeto
     */
    public Estado getObjeto() {
        return objeto;
    }

    /**
     * @param objeto the objeto to set
     */
    public void setObjeto(Estado objeto) {
        this.objeto = objeto;
    }

    /**
     * @return the daoPais
     */
    public PaisDAO getDaoPais() {
        return daoPais;
    }

    /**
     * @param daoPais the daoPais to set
     */
    public void setDaoPais(PaisDAO daoPais) {
        this.daoPais = daoPais;
    }
    
    
}

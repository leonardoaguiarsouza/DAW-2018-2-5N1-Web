/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author 201613260113
 */
@ManagedBean(name = "controleAjax")
@ViewScoped
public class ControleAjax implements Serializable{
    private String entrada;
    private String saida;

    public ControleAjax() {
    }

    public void processar(){
        saida = "Você digitou: '" + entrada + "' usando ajax do JSF";
        entrada = "";
    }
            
    /**
     * @return the entrada
     */
    public String getEntrada() {
        return entrada;
    }

    /**
     * @param entrada the entrada to set
     */
    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    /**
     * @return the saida
     */
    public String getSaida() {
        return saida;
    }

    /**
     * @param saida the saida to set
     */
    public void setSaida(String saida) {
        this.saida = saida;
    }
    
    
}

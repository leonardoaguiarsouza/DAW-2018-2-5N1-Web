/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.util.Util;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author 201613260113
 */
public class DAOGenerico<TIPO> {
    private List<TIPO> listaObjetos;
    private List<TIPO> listaTodos;
    private EntityManager em;
    Class classePersistente;
    private String mensagem;

    public DAOGenerico() {
        em = EntityManagerUtil.getEntityManager();
    }

    public boolean persist(TIPO obj){
        try {
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
            mensagem = "Objeto persistido com sucesso!";
            return true;
        } catch (Exception e) {
            mensagem = "Erro ao persistir: " + Util.getMensagemErro(e);
            return false;
        }
    }
    
    public boolean merge(TIPO obj){
        try {
            em.getTransaction().begin();
            em.merge(obj);
            em.getTransaction().commit();
            mensagem = "Objeto atualizado com sucesso!";
            return true;
        } catch (Exception e) {
            mensagem = "Erro ao atualiazr: " + Util.getMensagemErro(e);
            return false;
        }
    }
    
    public boolean remover(TIPO obj){
        try {
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
            mensagem = "Objeto removido com sucesso!";
            return true;
        } catch (Exception e) {
            mensagem = "Erro ao remover: " + Util.getMensagemErro(e);
            return false;
        }
    }
    
    public TIPO localizar(Object id){
        rollback();
        TIPO obj = (TIPO) em.find(classePersistente, id);
        return obj;
    }
    
    /**
     * @return the listaObjetos
     */
    public List<TIPO> getListaObjetos() {
        String jpql = " from " + classePersistente.getSimpleName();
        return em.createQuery(jpql).getResultList();
    }

    /**
     * @return the listaTodos
     */
    public List<TIPO> getListaTodos() {
        String jpql = " from " + classePersistente.getSimpleName();
        return em.createQuery(jpql).getResultList();
    }
    
    public void rollback(){
        if(em.getTransaction().isActive() == false){
            em.getTransaction().begin();
        }
        em.getTransaction().rollback();
    }
    
    /**
     * @param listaObjetos the listaObjetos to set
     */
    public void setListaObjetos(List<TIPO> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }


    /**
     * @param listaTodos the listaTodos to set
     */
    public void setListaTodos(List<TIPO> listaTodos) {
        this.listaTodos = listaTodos;
    }

    /**
     * @return the em
     */
    public EntityManager getEm() {
        return em;
    }

    /**
     * @param em the em to set
     */
    public void setEm(EntityManager em) {
        this.em = em;
    }

    /**
     * @return the classePersistente
     */
    public Class getClassePersistente() {
        return classePersistente;
    }

    /**
     * @param classePersistente the classePersistente to set
     */
    public void setClassePersistente(Class classePersistente) {
        this.classePersistente = classePersistente;
    }

    /**
     * @return the mensagem
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * @param mensagem the mensagem to set
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    
    
}

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
    protected String ordem = "id";
    protected String filtro = "";
    private Integer maximoobjetos = 0;
    private Integer posicaoAtual = 0;
    private Integer totalObjetos = 0;

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
        String where = "";
        // filtrar a entrada p/ proteger de sql injection
        filtro = filtro.replaceAll("[';-]", "");
        if (filtro.length() > 0){
            if (ordem.equals("id")){
                try {
                    Integer.parseInt(filtro);
                    where += " where " + ordem + " = '" + filtro + "' ";
                } catch (Exception e) {
                    
                }
            } else {
                where += " where upper(" + ordem + ") like '" + filtro.toUpperCase() + "%' ";
            }
        }
        jpql += where;
        jpql += " order by " + ordem;
        totalObjetos = em.createQuery(jpql).getResultList().size();
        return em.createQuery(jpql).setFirstResult(posicaoAtual).setMaxResults(maximoobjetos).getResultList();
    }

    public void primeiro(){
        posicaoAtual = 0;
    }
    
    public void anterior(){
        posicaoAtual -= maximoobjetos;
        if(posicaoAtual < 0){
            posicaoAtual = 0;
        }
    }
    
    public void proximo(){
        if(posicaoAtual + maximoobjetos < totalObjetos){
            posicaoAtual += maximoobjetos;
        }
    }
    
    public void ultimo(){
        int resto = totalObjetos % maximoobjetos;
        if(resto > 0){
            posicaoAtual = totalObjetos - resto;
        } else {
            posicaoAtual = totalObjetos - maximoobjetos;
        }
    }
    
    public String getMensagemNavegacao(){
        int ate = posicaoAtual + maximoobjetos;
        if(ate < totalObjetos){
            ate = totalObjetos;
        }
        return "Listando de " + (posicaoAtual + 1) + " até " + ate + " de " + totalObjetos + " registros";
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

    /**
     * @return the ordem
     */
    public String getOrdem() {
        return ordem;
    }

    /**
     * @param ordem the ordem to set
     */
    public void setOrdem(String ordem) {
        this.ordem = ordem;
    }

    /**
     * @return the filtro
     */
    public String getFiltro() {
        return filtro;
    }

    /**
     * @param filtro the filtro to set
     */
    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    /**
     * @return the maximoobjetos
     */
    public Integer getMaximoobjetos() {
        return maximoobjetos;
    }

    /**
     * @param maximoobjetos the maximoobjetos to set
     */
    public void setMaximoobjetos(Integer maximoobjetos) {
        this.maximoobjetos = maximoobjetos;
    }

    /**
     * @return the posicaoAtual
     */
    public Integer getPosicaoAtual() {
        return posicaoAtual;
    }

    /**
     * @param posicaoAtual the posicaoAtual to set
     */
    public void setPosicaoAtual(Integer posicaoAtual) {
        this.posicaoAtual = posicaoAtual;
    }

    /**
     * @return the totalObjetos
     */
    public Integer getTotalObjetos() {
        return totalObjetos;
    }

    /**
     * @param totalObjetos the totalObjetos to set
     */
    public void setTotalObjetos(Integer totalObjetos) {
        this.totalObjetos = totalObjetos;
    }
    
    
    
}

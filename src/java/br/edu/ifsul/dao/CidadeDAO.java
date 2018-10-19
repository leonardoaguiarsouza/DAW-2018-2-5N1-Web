/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;


/**
 *
 * @author 201613260113
 */
public class CidadeDAO<TIPO> extends DAOGenerico<Cidade> implements Serializable{
    
    public CidadeDAO(){
        super();
        classePersistente = Cidade.class;
        //inicializar a ordem padrao
        ordem = "nome";
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exercicio.repository;

import com.exercicio.models.Produto;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Igor
 */
public interface ProdutoRepository extends CrudRepository<Produto, Long>{
    
}

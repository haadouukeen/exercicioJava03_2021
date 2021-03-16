/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exercicio.controllers;

import com.exercicio.repository.ProdutoRepository;
import com.exercicio.models.Produto;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Igor
 */
@RestController
public class IndexController {
    
    @Autowired
    private ProdutoRepository pr;
    
    @RequestMapping("/")
    public ResponseEntity<Object> helloWorld(){
        Map<String, Object> map = new HashMap<>();
        map.put("resultado", "Hello World");
        
        return new ResponseEntity<>(map,HttpStatus.OK);
    }
    
    @RequestMapping("/produtos")
    public Iterable<Produto> GetProdutos(){
        Iterable<Produto> pro = pr.findAll();
        
        return pro;
    }
    
    @RequestMapping(value="/produto",method=RequestMethod.POST)
    public ResponseEntity<Object> PostProduto(@Valid @RequestBody Produto pessoa){
        Map<String, Object> map = new HashMap<>();
        HttpStatus status;
        
        if(pessoa.getDescricao().isEmpty()){
            map.put("resultado", "Preencha os campos");
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(map,status);
        }
        
        Produto p = new Produto();
        p.setDescricao(pessoa.getDescricao());
        pr.save(p);
        
        map.put("resultado", "Cadastrado com sucesso");
        status = HttpStatus.OK;
        
        return new ResponseEntity<>(map,status);
    }
    
    @RequestMapping(value="/produto",method=RequestMethod.PUT)
    public ResponseEntity<Object> PutProduto(@Valid @RequestBody Produto pessoa){
        Map<String, Object> map = new HashMap<>();
        HttpStatus status;
        
        if(pessoa.getId()==0 || pessoa.getDescricao().isEmpty()){
            map.put("resultado", "Preencha os campos");
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(map,status);
        }
        
        Produto p = pr.findById(pessoa.getId()).get();
        p.setDescricao(pessoa.getDescricao());
        pr.save(p);
        
        map.put("resultado", "Editado com sucesso");
        status = HttpStatus.OK;
        
        return new ResponseEntity<>(map,status);
    }
    
    @RequestMapping(value="/produto/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<Object> DeleteProduto(@PathVariable(value = "id") long id){
        Map<String, Object> map = new HashMap<>();
        HttpStatus status;
        
        if(id == 0){
            map.put("resultado", "Preencha os campos");
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(map,status);
        }
        
        Produto p = pr.findById(id).get();
        pr.delete(p);
        
        map.put("resultado", "Deletado com sucesso");
        status = HttpStatus.OK;
        
        return new ResponseEntity<>(map,status);
    }
}

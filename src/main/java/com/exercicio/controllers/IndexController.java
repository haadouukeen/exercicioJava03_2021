/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exercicio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Igor
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String helloWorld(){
        return "index";
    }
}

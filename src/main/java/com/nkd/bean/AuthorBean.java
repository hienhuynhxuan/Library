/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nkd.bean;

import com.nkd.pojo.Author;
import com.nkd.service.AuthorService;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author duynk
 */
@ManagedBean
@Named(value = "authorBean")
@SessionScoped
public class AuthorBean implements Serializable{
    private final static AuthorService authorService = new AuthorService();
    /**
     * Creates a new instance of AuthorBean
     */
    public AuthorBean() {
    }
    
    public List<Author> getAuthors() {
        return authorService.getAuthors();
    }
}

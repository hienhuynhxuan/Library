/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nkd.bean;

import com.nkd.pojo.Category;
import com.nkd.service.CategoryService;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author duynk
 */
@ManagedBean
@Named(value = "catBean")
@SessionScoped
public class CatBean implements Serializable {
    private final static CategoryService cateService = new CategoryService();
    /**
     * Creates a new instance of CatBean
     */
    public CatBean() {
    }
    
    public List<Category> getCategories() {
        return cateService.getCategories();
    }
}

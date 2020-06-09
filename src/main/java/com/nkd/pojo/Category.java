/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nkd.pojo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author duynk
 */
@Entity
@Table(name="category")
public class Category implements Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name="loai_sach")
    private String loaiSach;
    @Column(name="mieu_ta")
    private String mieuTa;
    
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<Book> book;

    @Override
    public String toString() {
        return String.valueOf(this.id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object obj) {
        Category cat = (Category)obj;
        return this.id == cat.id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.id;
        return hash;
    }
    
    
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the loaiSach
     */
    public String getLoaiSach() {
        return loaiSach;
    }

    /**
     * @param loaiSach the loaiSach to set
     */
    public void setLoaiSach(String loaiSach) {
        this.loaiSach = loaiSach;
    }

    /**
     * @return the mieuTa
     */
    public String getMieuTa() {
        return mieuTa;
    }

    /**
     * @param mieuTa the mieuTa to set
     */
    public void setMieuTa(String mieuTa) {
        this.mieuTa = mieuTa;
    }

    /**
     * @return the book
     */
    public Set<Book> getBook() {
        return book;
    }

    /**
     * @param book the book to set
     */
    public void setBook(Set<Book> book) {
        this.book = book;
    }
}

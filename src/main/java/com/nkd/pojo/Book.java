/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nkd.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
/**
 *
 * @author duynk
 */
@Entity
@Table(name="book")
public class Book implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ISBN;
    @Column(name="ten_sach")
    private String tenSach;
    @Column(name="nam_xuat_ban")
    private Date namXuatBan;
    @Column(name="lan_xuat_ban")
    private int lanXuatBan;
    @Column(name="anh_sach")
    private String anhSach;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loai_sach")
    private Category category;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="tacgia_sach",
            joinColumns = {
                @JoinColumn(name="id_sach")
            },
            inverseJoinColumns = {
                @JoinColumn(name="id_tac_gia")
            }
    )
    private Set<Author> author;

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
     * @return the ISBN
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * @param ISBN the ISBN to set
     */
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    /**
     * @return the tenSach
     */
    public String getTenSach() {
        return tenSach;
    }

    /**
     * @param tenSach the tenSach to set
     */
    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    /**
     * @return the namXuatBan
     */
    public Date getNamXuatBan() {
        return namXuatBan;
    }

    /**
     * @param namXuatBan the namXuatBan to set
     */
    public void setNamXuatBan(Date namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    /**
     * @return the lanXuatBan
     */
    public int getLanXuatBan() {
        return lanXuatBan;
    }

    /**
     * @param lanXuatBan the lanXuatBan to set
     */
    public void setLanXuatBan(int lanXuatBan) {
        this.lanXuatBan = lanXuatBan;
    }

    /**
     * @return the anhSach
     */
    public String getAnhSach() {
        return anhSach;
    }

    /**
     * @param anhSach the anhSach to set
     */
    public void setAnhSach(String anhSach) {
        this.anhSach = anhSach;
    }

    /**
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * @return the author
     */
    public Set<Author> getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(Set<Author> author) {
        this.author = author;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nkd.bean;

import com.nkd.pojo.Author;
import com.nkd.pojo.Book;
import com.nkd.pojo.Category;
import com.nkd.service.BookService;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author duynk
 */
@ManagedBean
@Named(value = "productBean")
@Dependent
public class ProductBean {

    /**
     * @return the imgFile
     */
    public Part getImgFile() {
        return imgFile;
    }

    /**
     * @param imgFile the imgFile to set
     */
    public void setImgFile(Part imgFile) {
        this.imgFile = imgFile;
    }
    private int bookID;
    private String ISBN;
    private String tenSach;
    private Date namXuatBan;
    private int lanXuatBan;
    private Category category;
    private Set<Author> authors;
    private Part imgFile;
    
    private static BookService bookService = new BookService();
    
    /**
     * Creates a new instance of ProductBean
     */
    public ProductBean() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            String bookId = FacesContext.getCurrentInstance().getExternalContext().
                    getRequestParameterMap().get("bookId");
            if (bookId != null && !bookId.isEmpty()) {
                Book b = bookService.getBookById(Integer.parseInt(bookId));
                this.bookID = b.getId();
                this.ISBN = b.getISBN();
                this.tenSach = b.getTenSach();
                this.namXuatBan = b.getNamXuatBan();
                this.lanXuatBan = b.getLanXuatBan();
                this.category = b.getCategory();
                this.authors = b.getAuthor();
            }
        }
    }
    public List<Book> getBooks() {
        return getBookService().getBooks(null);
    }
    
    public String addBooks() {
//        String bookId = FacesContext.getCurrentInstance().getExternalContext().
//                    getRequestParameterMap().get("bookId");
        Book book;
        if(bookID > 0)
            book = bookService.getBookById(this.bookID);
        else
            book = new Book();

        book.setISBN(this.ISBN);
        book.setTenSach(this.tenSach);
        book.setNamXuatBan(this.namXuatBan);
        book.setLanXuatBan(this.lanXuatBan);
        book.setCategory(this.category);
        book.setAuthor(this.authors);
        
        try {
            if (this.imgFile != null) {
                this.uploadFile();
                book.setAnhSach("img_sach/" + this.imgFile.getSubmittedFileName());
            }
            
            
            if (bookService.addOrSaveBook(book) == true)
                return "product_admin?faces-redirect=true";
        } catch (IOException ex) {
            Logger.getLogger(ProductBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "add_product";
    }

    public String deleteBook(Book book) throws Exception { 
        if (bookService.deleteBook(book))
            return "successful";
        throw new Exception("error");
        
    }
    
    private void uploadFile() throws IOException {
//        String path = FacesContext.getCurrentInstance().
//                getExternalContext().
//                getRealPath("resources/images/img_sach")
//                + "/" + this.imgFile.getSubmittedFileName();
//        String path = "C:\\Users\\duynk\\Documents\\NetBeansProjects\\Library\\src\\main\\webapp\\resources\\images\\img_sach\\" + this.imgFile.getSubmittedFileName();
        String path = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("uploadPath") + this.imgFile.getSubmittedFileName();
        try(InputStream in = this.imgFile.getInputStream();
                FileOutputStream out = new FileOutputStream(path)) {
            byte[] b = new byte[1024];
            int byteRead;
            while((byteRead = in.read(b)) != -1)
                out.write(b, 0, byteRead);
        }
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
     * @return the authors
     */
    public Set<Author> getAuthors() {
        return authors;
    }

    /**
     * @param authors the authors to set
     */
    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    /**
     * @return the bookService
     */
    public static BookService getBookService() {
        return bookService;
    }

    /**
     * @param aBookService the bookService to set
     */
    public static void setBookService(BookService aBookService) {
        bookService = aBookService;
    }

    /**
     * @return the bookID
     */
    public int getBookID() {
        return bookID;
    }

    /**
     * @param bookID the bookID to set
     */
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
}

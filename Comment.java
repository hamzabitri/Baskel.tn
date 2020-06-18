/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author onsks
 */
public class Comment {
    
     private int id;
    private String author;
    private String content;
    private String createdAt;
    private int article;

    public Comment() {
    }
    
    public Comment(int id, String author, String content, String createdAt, int article) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.createdAt = createdAt;
        this.article = article;
    }

    public Comment(String author, String content, String createdAt, int article) {
        this.author = author;
        this.content = content;
        this.createdAt = createdAt;
        this.article = article;
    }

    public Comment(String content) {
        this.content = content;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getArticle() {
        return article;
    }

    public void setArticle(int article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", author=" + author + ", content=" + content + ", createdAt=" + createdAt + ", article=" + article + '}';
    }
    
}

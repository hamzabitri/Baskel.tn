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
public class Article {
    
    private int id;
    private int category;
    private int comments;
    private int likes;  
    private  String title;
    private String content;
    private String image;
    private  String date;

    public Article() {
    }

    public Article(int id, int category, int comments, int likes, String title, String content, String image, String date) {
        this.id = id;
        this.category = category;
        this.comments = comments;
        this.likes = likes;
        this.title = title;
        this.content = content;
        this.image = image;
        this.date = date;
    }

    public Article(int category, int comments, int likes, String title, String content, String image, String date) {
        this.category = category;
        this.comments = comments;
        this.likes = likes;
        this.title = title;
        this.content = content;
        this.image = image;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", category=" + category + ", comments=" + comments + ", likes=" + likes + ", title=" + title + ", content=" + content + ", image=" + image + ", date=" + date + '}';
    }
    
    

}

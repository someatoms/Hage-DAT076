/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dat076hage.hage.model;

import java.io.Serializable;
import javax.persistence.*;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author stek
 */
@Entity
@NamedQueries({
    //@NamedQuery(name="Post.trending", query="SELECT c FROM Country c"),
    //@NamedQuery(name="Post.findByName", query="SELECT c FROM Country c WHERE c.name = :name"),
})
public class Post implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose private long postId;
    @Expose private String picture;
    
    @Expose private String text;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Expose private Date date;
    @Expose private String link;
    
    @Expose private List<String> hageTagList;
    
    @OneToMany(mappedBy = "post")
    @Expose private List<Comment> comments;
    
    @ManyToMany
    private List<User> agreeingUsers;
    
    @ManyToMany
    private List<User> disagreeingUsers;

    @Embedded
    @Expose private GPS position;
    
    @ManyToOne
    @Expose private User user;
    
    public Post(){
    } 
    
    public Post (User user, String text) {
        this.user = user;
        this.text = text;
        this.date = new Date();
        
        comments = new ArrayList<>();
        //comments.add(new Comment(user, this, "This is an awful comment"));
        agreeingUsers = new ArrayList<>();
        disagreeingUsers = new ArrayList<>();
    }

    
    public Post(User user, String text, String picturePath, String link, List<String> hageTags, GPS pos){
        this.user = user;
        this.text = text;
        this.picture = picturePath;
        this.link = link;
        this.hageTagList = new ArrayList<>(hageTags);
        this.position = pos;
        this.date = new Date();
    }
    
    public String getText(){
        return text;
    }
    
    public long getId(){
        return postId;
    }
    
    public GPS getPosition(){
        return new GPS(position);
    }
    
    public String getPicturePath(){
        return picture;
    }
    
    public Date getDate(){
        return new Date(date.getTime());
    }
    
    public String getLink(){
        return link;
    }
    
    public User getAuthor(){
        return user;
    }
    
    public List getHageTags(){
        return hageTagList;
    }
    
    public List<Comment> getComments(){
        return comments;
    }
    
    public List<User> getAgreeingUsers(){
        return agreeingUsers;
    }
    
    public List<User> getDisagreeingUsers(){
        return disagreeingUsers;
    }
    
    public void setText(String text){
        this.text = text;
    }
    
    public void setPosition(GPS pos){
        this.position = new GPS(pos);
    }
    
    public void setPicturePath(String path){
        this.picture = path;
    }
    
    public void setDate(Date date){
        this.date = new Date(date.getTime());
    }
    
    public void setLink(String link){
        this.link = link;
    }
    
    @Override
    public String toString() {
        return "Post{" +
                "text='" + text + '\'' +
                ", postId=" + postId +
                ", picturePath='" + picture + '\'' +
                ", postDate=" + date +
                ", link='" + link + '\'' +
                ", hageTagList=" + hageTagList +
                ", position=" + position +
                ", user=" + user +
                '}';
    }
}

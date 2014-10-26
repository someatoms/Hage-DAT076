/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dat076hage.hage.model;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author kim
 */
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @Column(nullable = false, updatable = false, length = 50)
    @Expose private String username;
    @Expose private String name;
    
    @Expose private String description;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Expose private Date memberSince;
    
    @ManyToMany
    @Expose private List<User> following;
    
    @ManyToMany(mappedBy = "following")
    @Expose private List<User> followers;
    
    @OneToMany(mappedBy = "user") 
    @Expose private List<Post> posts;
    
    @OneToMany(mappedBy = "user") 
    @Expose private List<Comment> comments;
    
    @ManyToMany(mappedBy = "agreeingUsers")
    @Expose private List<Post> agreements;
    
    @ManyToMany(mappedBy = "disagreeingUsers")
    @Expose private List<Post> disagreements;

    @Expose private String picture;

    private String twitterApiHash;
    private String passwordHash;

    public User(){
    }

    public User(String username, String description, String passwordHash, String twitterApiHash, String picture, String name){

        this.username = username;
        this.name = name;
        this.description = description;
        this.passwordHash = passwordHash;
        this.twitterApiHash = twitterApiHash;
        this.picture = picture;
        
        memberSince = new Date();
        posts = new ArrayList<>();
        following = new ArrayList<>();
        followers = new ArrayList<>();
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getDescription(){
        return description;
    }
    
    
    public Date getMemberDate(){
        return new Date(memberSince.getTime());
    }
    
    public List<Post> getPosts(){
        return new ArrayList<>(posts);
    }
    
    public List<Post> getPostsRange(int fromIndex, int toIndex) {
        return posts.subList(fromIndex, toIndex);
    }


    public List<User> getFollowing(){

        return new ArrayList<>(following);
    }
    
    public List<User> getFollowers(){
        return new ArrayList<>(followers);
    }
    
    public String getHash(){
        return passwordHash;
    }
    
    public String getTwitterApiHash(){
        return twitterApiHash;
    }
   
    public void setDescription(String description) {
        this.description = description;
    }
    
    // ACTIONS WITH POSTS
    
    public void assaignPost(Post post){
        posts.add(post);
    }
    
    public void removePost(Post post){
        for(int i = 0; i < posts.size(); i++){
            if(posts.get(i).getId() == post.getId()){
                posts.remove(i);
                break;
            }
        }
    }
    
    public Post createNewPost(String content){
        Post post = new Post(this, content);
        if(posts == null) {
            posts = new ArrayList<Post>();
        }
        posts.add(post);
        return post;
    }

    
    // ACTIONS WITH FOLLOWED USERS

    public void follow(User user){
        user.followers.add(this);
        following.add(user);
    }
    

    public void unfollow(User user){
        
        for(int i = 0; i < following.size(); i++){
            if(following.get(i).getUsername().equals(user.getUsername())){
                following.remove(i);
                break;
            }
        }
        
        for(int i = 0; i < user.followers.size(); i++){
            if(user.followers.get(i).getUsername().equals(user.getUsername())){
                user.followers.remove(i);
                break;
            }
        }
    }
    
    public void emptyUsersIAmFollowing(){
        following.clear();
    }
    
    public void emptyUsersWhoArefollowersOfMe(){
        followers.clear();
    }
    
    public void emptyRelations(){
        agreements.clear();
        disagreements.clear();
        comments.clear();
        followers.clear();
        following.clear();
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", description='" + description + '\'' +
                ", memberSince=" + memberSince +
                ", following=" + following +
                ", followers=" + followers +
                ", posts=" + posts +
                ", picture='" + picture + '\'' +
                ", twitterApiHash='" + twitterApiHash + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                '}';
    }
}

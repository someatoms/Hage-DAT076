/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dat076hage.hage;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kim
 */
public class User {
    @Expose private long id;
    @Expose private String firstname;
    @Expose private String lastname;
    private static int idCount;
    private static List<User> users = new ArrayList<User>();
    private List<Post> posts = new ArrayList<Post>();
    
    public User(){
        this("", "");
    }
    
    public User(String firstname, String lastname){
        this.id = createId();
        this.firstname = firstname;
        this.lastname = lastname;
        users.add(this);
    }
    
    private long createId(){
        return ++idCount;
    }
    
    public void addPost(Post post){
        posts.add(post);
    }
    
    public List<Post> getPosts(){
        return new ArrayList<Post>(posts);
    }
    
    public String toString(){
        return String.format("id:%s firstname:%s lastname:%s", id, firstname, lastname);
    }
    
    public static List<User> getUsers(){
        return new ArrayList<User>(users);
    }
    
    public static void initTestUsers(){
        if(User.getUsers().isEmpty()){
            new User("Kim", "Kling");
            new User("Simon", "Bengtsson");
            new User("Simon", "Planhage");
            new User("Caroline", "Kabat");
        }
    }
}

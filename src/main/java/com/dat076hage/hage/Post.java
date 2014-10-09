/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dat076hage.hage;

/**
 *
 * @author stek
 */
public class Post {
    
    private String content;
    private long postId;
    private static int idCount;
    
    public Post (String content) {
        this.content = content;
        this.postId = getId();
    }
    
    
    private int getId() {
        return ++idCount;
    }
    
}

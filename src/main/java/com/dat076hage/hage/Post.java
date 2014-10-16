/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dat076hage.hage;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author stek
 */
@Entity
public class Post implements Serializable {
    
    private String content;
    private long postId;
    private static int idCount;
    @ManyToOne
    private User user;
    
    public Post (String content) {
        this.content = content;
        this.postId = getId();
    }
    
    
    private int getId() {
        return ++idCount;
    }
    
}

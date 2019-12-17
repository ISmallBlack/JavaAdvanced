/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.zhangch.javaknowledge.JDK8.anotherjava8.examples.chapter1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Richard Warburton
 */
public abstract class MusicChapter {
    
    protected final List<Artist> artists;
    protected final List<Album> albums;

    public MusicChapter() {
        artists = new ArrayList<>();
        albums = new ArrayList<>();
        loadData("");
    }

    private void loadData(String file) {
        
    }
    
}

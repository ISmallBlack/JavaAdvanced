package com.zhangch.javaknowledge.JDK8.anotherjava8.examples.chapter4;


import com.zhangch.javaknowledge.JDK8.anotherjava8.examples.chapter1.Album;
import com.zhangch.javaknowledge.JDK8.anotherjava8.examples.chapter1.Track;

import java.util.List;

public class OrderImperative extends Order {

    public OrderImperative(List<Album> albums) {
        super(albums);
    }

    // BEGIN body
public long countRunningTime() {
    long count = 0;
    for (Album album : albums) {
        for (Track track : album.getTrackList()) {
            count += track.getLength();
        }
    }
    return count;
}

public long countMusicians() {
    long count = 0;
    for (Album album : albums) {
        count += album.getMusicianList().size();
    }
    return count;
}

public long countTracks() {
    long count = 0;
    for (Album album : albums) {
        count += album.getTrackList().size();
    }
    return count;
}
    // END body

}

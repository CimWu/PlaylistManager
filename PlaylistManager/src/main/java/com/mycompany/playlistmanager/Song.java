/* I hereby declare upon my word of honor that I have neither given nor 
 * received unauthorized help on this work.
 *  */
package com.mycompany.playlistmanager;

/**
 * Song class to instantiate and govern Song objects
 * @author whitw
 */
public class Song {
    private String artist;
    private String title;

    /**
     * constructor
     * @param artist
     * @param title 
     */
    public Song(String artist, String title) {
        this.artist = artist;
        this.title = title;
    }

    /**
     * getter for artist
     * @return 
     */
    public String getArtist() {
        return this.artist;
    }

    /**
     * getter for title
     * @return 
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * setter for artist
     * @param artist 
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * setter for title
     * @param title 
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * The remove method wasn't working for me, so I rewrote
     * the equals method instead of implementing Iterator
     * override the equals method for Song to properly
     * remove the indexed song object
     * @param song
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object song) {
        
        // make sure param is type song
        if (this == song) {
            return true;
        }
        
        // check if null or if not same class
        if (song == null || getClass() != song.getClass()) {
            return false;
        }
        
        // if neither above is good, cast it as a song obj
        Song otherSong = (Song) song;
        return artist.equals(otherSong.artist) 
                && title.equals(otherSong.title);
    }

    @Override
    public String toString() {
        return "\"" + title + "\", by " + artist;
    }
}


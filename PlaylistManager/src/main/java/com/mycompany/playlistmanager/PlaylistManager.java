/* I hereby declare upon my word of honor that I have neither given nor 
 * received unauthorized help on this work.
 *  */
package com.mycompany.playlistmanager;

/**
 * PlaylistManager program
 * This is the main and the menu
 * @author whitw
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class PlaylistManager {
    private DoubleList<Song> playlist;

    /**
     * Main
     * 
     * @param args
     */
    public static void main(String[] args) {
        PlaylistManager playlistManager = new PlaylistManager();
        System.out.println("Welcome to Chris Whitworth's Playlist Manager");
        System.out.println("Inputs are numerical and displayed on the left of the menu");
        playlistManager.menu();
    }

    /**
     * constructor for playlist
     */
    public PlaylistManager() {
        playlist = new DoubleList<>();
    }

    /**
     * menu system loop add / remove songs, print number of songs, print playlist
     * shuffle playlist, reverse playlist, save / load quit
     */
    // TODO: messy
    private void menu() {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        // menu logic
        while (!quit) {
            System.out.println("\nPlaylist Options");
            System.out.println("[1] Add a song");
            System.out.println("[2] Remove a song");
            System.out.println("[3] Song count");
            System.out.println("[4] Play");
            System.out.println("[5] Shuffle");
            System.out.println("[6] Reverse");
            System.out.println("[7] Save playlist");
            System.out.println("[8] Load a playlist");
            System.out.println("[9] Quit\n");

            System.out.print("What would you like to do? ");

            // TODO: sloppy menu
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();

                // TODO: change to switch if time permits
                if (choice == 1) {
                    addSong(scanner);
                } else if (choice == 2) {
                    removeSong(scanner);
                } else if (choice == 3) {
                    printNumberOfSongs();
                } else if (choice == 4) {
                    playPlaylist();
                } else if (choice == 5) {
                    shufflePlaylist();
                } else if (choice == 6) {
                    reversePlaylist();
                } else if (choice == 7) {
                    savePlaylist();
                } else if (choice == 8) {
                    loadPlaylist();
                } else if (choice == 9) {
                    quit = true;
                    System.out.println("\n\n\nGoodbye!");
                } else {
                    System.out.println("\nInvalid option. Please try again...");
                }
            } else {
                System.out.println("\nInvalid option. Please try again...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    /**
     * method to add a song
     * 
     * @param scanner
     */
    private void addSong(Scanner scanner) {
        System.out.print("What artist are you adding? ");
        String artist = scanner.nextLine();

        System.out.print("What song are you adding? ");
        String title = scanner.nextLine();

        Song song = new Song(artist, title);
        playlist.addStart(song);
        System.out.println("\nSong added to the playlist.\n");
        
    }

    /**
     * method to remove a song
     * 
     * @param scanner
     */
    private void removeSong(Scanner scanner) {
        System.out
                .print("\nWho is the Artist of the song you want to remove? ");
        String artist = scanner.nextLine();

        System.out
                .print("What's the title of the song you want to remove? ");
        String title = scanner.nextLine();

        Song song = new Song(artist, title);
        playlist.remove(song);

    }

    /**
     * method to print the # of songs in the playlist
     */
    private void printNumberOfSongs() {
        System.out.println("\nSong Count: [ " 
                + playlist.size() + " ]\n");
    }

    /**
     * "plays" the songs and prints the playlist
     */
    private void playPlaylist() {
        System.out.println("\n---- Playing ----");
        playlist.print();
        System.out.println("---- end ----");
    }

    /**
     * initiates shuffling the playlist
     */
    private void shufflePlaylist() {
        playlist.shuffle();
        System.out.println("\n** Playlist shuffled **\n");
    }

    /**
     * initiates reversing the playlist
     */
    private void reversePlaylist() {
        playlist.reverse();
        System.out.println("\n** Playlist reversed **\n");
    }

    /**
     * Saves the playlist to a custom .txt file
     */
    private void savePlaylist() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter desired .txt file name: ");
        String filename = scanner.nextLine();

        playlist.save(filename);
    }

    /**
     * Loads the playlist from a custom .txt file
     */
    private void loadPlaylist() {
        Scanner scanner = new Scanner(System.in);
        String filename = "";
        boolean isProperFile = false;

        // ask for file and repeat until valid .txt
        while (!isProperFile) {
            System.out.print("\nWhat file are you loading (include .txt)? ");
            filename = scanner.nextLine();
            
            if (filename.endsWith(".txt")) {
                isProperFile = true;
            } else {
                System.out.println("\nInvalid file format. Try again...\n\n");
            }
        }

        try (BufferedReader reader = 
                new BufferedReader(new FileReader(filename))) {
            
            String line;
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" - ");
                if (parts.length == 2) {
                    String artist = parts[0];
                    String title = parts[1];
                    Song song = new Song(artist, title);
                    playlist.addEnd(song);
                }
            }
            
            System.out.println("\nLoaded your playlist from " 
                    + filename + "\n");
            
        } catch (IOException e) {
            System.out.println("\nCould not load playlist " + e.getMessage());
        }
    }
}

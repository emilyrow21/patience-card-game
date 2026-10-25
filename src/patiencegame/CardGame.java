package patiencegame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class CardGame
{
    protected Deck deck;
    private String userName;
    private String filePath = "results";

    //abstract method to deal a card
    public abstract boolean dealCard();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    protected void writeResult(String line) {
        File file = new File(this.getFilePath());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.getFilePath(), true))) {
            if (file.exists()) {
                writer.newLine(); //this adds new line if the file already exists
            }
            writer.write(line);
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
    public void printDeck() {
        for (Card card : deck.getCards()) {
            System.out.print(card + " ");
        }
        System.out.println();
    }

    public void shuffleDeck() {
        deck.shuffle();
        System.out.println("Deck shuffled.");
    }
}

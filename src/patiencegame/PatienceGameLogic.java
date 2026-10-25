package patiencegame;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.io.*;
import java.util.*;

public class PatienceGameLogic extends CardGame {

    private ArrayList<Pile> piles;

    //top 10 results method
    public void showTopResults() {
        File file = new File(this.getFilePath());
        List<String> results = new ArrayList<>();

        //this reads each line from the results file
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                results.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading results file: " + e.getMessage());
            return;
        }

        //used help
        results.sort((a, b) -> {
            int scoreA = Integer.parseInt(a.split(",")[1].trim());
            int scoreB = Integer.parseInt(b.split(",")[1].trim());
            return Integer.compare(scoreA, scoreB); //asscending order
        });


        System.out.println("\nTop 10 Results:");
        results.stream().limit(10).forEach(System.out::println);
    }


    public PatienceGameLogic() {
        deck = new Deck();
        piles = new ArrayList<>();
    }


    public boolean dealCard() {
        Card card = deck.deal();
        if (card != null) {
            Pile newPile = new Pile();
            newPile.addCard(card);
            piles.add(newPile);
            System.out.println("Dealt card: " + card);
            return true;
        } else {

            System.out.println("No cards left to deal ");
            this.writeResult( this.getUserName() + "," + piles.size());
            return false;
        }
    }

    public void moveLastPileToPrevious() {
        int size = piles.size();
        if (size < 2) {
            System.out.println("Not enough piles.");
            return;
        }
        Pile lastPile = piles.get(size - 1);
        Pile previousPile = piles.get(size - 2);
        if (lastPile.canCombine(previousPile)) {
            previousPile.combine(lastPile);
            piles.remove(size - 1);
            System.out.println("move succesfull");
        } else {
            System.out.println("move not allowed.");
        }
    }

    public void moveLastPileBackTwo() {
        int size = piles.size();
        if (size < 3) {
            System.out.println("Not enough piles.");
            return;
        }
        Pile lastPile = piles.get(size - 1);
        Pile twoPilesBack = piles.get(size - 3);
        if (lastPile.canCombine(twoPilesBack)) {
            twoPilesBack.combine(lastPile);
            piles.remove(size - 1);
            System.out.println("move successful");
        } else {
            System.out.println("move not allowed.");
        }
    }
    //fr6

    public void amalgamatePiles(int pileIndex1, int pileIndex2) {
        if (pileIndex1 < 0 || pileIndex2 < 0 || pileIndex1 >= piles.size() || pileIndex2 >= piles.size()) {
            System.out.println("invalid pile number");
                    return;

        }
        Pile p1 = piles.get(pileIndex1);
        Pile p2 = piles.get(pileIndex2);

        if (p1.canCombine(p2)) {
            p1.combine(p2);
            //this removes the second pile
            piles.remove(pileIndex2);
            System.out.println("piles merged");
        } else {
            System.out.println("move not possible");
        }
    }
    //fr8
    public void playOnce() {
        //tries the two-pile jump first then adjacent move
        if (piles.size() >= 3 && piles.get(piles.size() - 1).canCombine(piles.get(piles.size() - 3))) {
            moveLastPileBackTwo();
            System.out.println("done a two pile jump move");
        } else if (piles.size() >= 2 && piles.get(piles.size() - 1).canCombine(piles.get(piles.size() - 2))) {
            moveLastPileToPrevious();
            System.out.println("done an adjacenet pile move");
        } else {
            System.out.println("move not allowed");
        }
    }
    //fr9
    public void playManyTimes(int moves) {
        for (int i = 0; i < moves; i++) {
            System.out.println("move " + (i + 1) + ":");
            playOnce();
        }
    }
    public void displayPiles() {
       for (int i = 0; i < piles.size(); i++) {
            System.out.println("Pile " + (i + 1) + ": " + piles.get(i));
        }
    }




    public ArrayList<String> getCardTableRepresentation() {
        ArrayList<String> cardStrings = new ArrayList<>();
        //each pile adds a string representing the card image
        for (Pile pile : piles) {
            if (!pile.isEmpty()) {
                cardStrings.add(pile.topCard().toString().toLowerCase() + ".gif");
            }
        }
        return cardStrings;
    }
//fr11
    public int getRemainingPiles() {
        return piles.size();
    }

}






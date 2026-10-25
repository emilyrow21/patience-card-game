package patiencegame;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    //list to hold all the cards
    private ArrayList<Card> cards;

        public Deck() {
            cards = new ArrayList<>();
            String[] suits = {"H", "C", "D", "S"};
            String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A"};

            //builds the full deck by iterating through all suits and ranks
            for (String suit : suits) {
                for (String rank : ranks) {
                    cards.add(new Card(rank, suit));
                }
            }
        }
        public void shuffle() {
            Collections.shuffle(cards);
        }

        public Card deal() {
            return cards.isEmpty() ? null : cards.remove(0);
        }

        public ArrayList<Card> getCards() {
            return new ArrayList<>(cards);
        }

        //this returns the filenames for the card images
        public ArrayList<String> getFilenames(){
            ArrayList<String> thecardswithgif = new ArrayList<String>();
            for(Card c : cards)
            {
                thecardswithgif.add(c.getRank() + c.getSuit() + ".gif");

            }
            return thecardswithgif;
        }
    }





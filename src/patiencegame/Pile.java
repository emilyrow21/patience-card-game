package patiencegame;

import java.util.ArrayList;
import java.util.Stack;

    public class Pile {
        //stack represnets the pile of the cards
        private Stack<Card> pile;

            public Pile() {
                pile = new Stack<>();
            }

            public void addCard(Card card) {
                pile.push(card);
            }

            public Card topCard() {
                return pile.isEmpty() ? null : pile.peek();
            }
            //determines if the pile can be combined with another pile

            public boolean canCombine(Pile otherPile) {
                if (this.topCard() == null || otherPile.topCard() == null) {
                    return false;
                }
                return this.topCard().matches(otherPile.topCard());
            }

            public void combine(Pile otherPile) {
                while (!otherPile.pile.isEmpty()) {
                    pile.push(otherPile.pile.pop());
                }
            }

            public boolean isEmpty() {
                return pile.isEmpty();
            }

            @Override
            public String toString() {
                return pile.toString();
            }

            public ArrayList<String> getFilenames(){
            ArrayList<String> thecardswithgif = new ArrayList<String>();
            for(Card c : pile)
            {
                thecardswithgif.add(c.getRank() + c.getSuit() + ".gif");

            }
            return thecardswithgif;
        }
        }



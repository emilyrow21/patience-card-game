package patiencegame;

public class Card {
        private String rank;
        private String suit;

        public Card(String rank, String suit) {
            this.rank = rank;
            this.suit = suit;
        }

        public String getRank() {
            return rank;
        }

        public String getSuit() {
            return suit;
        }


        //checks if the card matches another card
        public boolean matches(Card other) {
            return this.rank.equals(other.getRank()) || this.suit.equals(other.getSuit());
        }

        @Override
        public String toString() {
            return rank + suit;
        }
    }



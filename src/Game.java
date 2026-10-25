/**
 * The Game of patience main class
 * @author Chris Loftus and Lynda Thomas
 * @version 2.0
 */



import patiencegame.PatienceGameLogic;
//command-line interaction
import java.util.ArrayList;
import java.util.Scanner;
//for saving results (file handling)
import javafx.application.Application;
import javafx.stage.Stage;
import uk.ac.aber.dcs.cs12320.cards.gui.javafx.CardTable;


public class Game extends Application {

    private CardTable cardTable;
    private PatienceGameLogic patienceGameLogic;

    @Override
    public void start(Stage stage) {
        cardTable = new CardTable(stage);
        patienceGameLogic = new PatienceGameLogic();
        //the default username
        patienceGameLogic.setUserName("player1");

        // The interaction with this game is from a command line
        // menu. We need to create a separate non-GUI thread
        // to run this in. DO NOT REMOVE THIS.
        Runnable commandLineTask = () -> {
            Scanner scanner = new Scanner(System.in);
            String choice;
            String input;
            boolean finishGame = false;
            //game menu
            do {
                System.out.println(" MENU ");
                System.out.println("1 - Print the pack out");
                System.out.println("2 - Shuffle the deck of cards");
                System.out.println("3 - Deal a card");
                System.out.println("4 - Move last pile onto previous one");
                System.out.println("5 - Move last pile back over two piles");
                System.out.println("6 - amalgamate piles in the middle");
                System.out.println("7 - Display piles");
                System.out.println("8 - play for me once");
                System.out.println("9- play for me number of times");
                System.out.println("10 - top 10 results of all games so far");
                System.out.println("11 - change user name");
                System.out.println("12 - Quit");

                System.out.print("Enter your choice: ");
                choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        patienceGameLogic.printDeck();
                        break;
                    case "2":
                        patienceGameLogic.shuffleDeck();
                        break;
                    case "3":
                        if (patienceGameLogic.dealCard()) {
                            updateCardTable(); //updates the card display
                        } else {
                            finishGame = true;
                        }
                        break;
                    case "4":
                        patienceGameLogic.moveLastPileToPrevious();
                        updateCardTable();
                        break;
                    case "5":
                        patienceGameLogic.moveLastPileBackTwo();
                        updateCardTable();
                        break;

                    case "6":
                        int p1,p2;
                        System.out.println("Enter first position:");
                        //this gets first number from user
                        p1 = scanner.nextInt();
                        System.out.println("Enter second position:");
                        //this gets first number from user
                        p2 = scanner.nextInt();
                        patienceGameLogic.amalgamatePiles(p1,p2);
                        updateCardTable();
                        break;

                    case "7":
                        patienceGameLogic.displayPiles();
                        break;

                    case "8":
                        patienceGameLogic.playOnce();
                        updateCardTable();
                        break;

                    case "9":
                        //i gave a random number of moves
                        patienceGameLogic.playManyTimes(5);
                        updateCardTable();
                        break;

                    case "10":
                        patienceGameLogic.showTopResults();
                        updateCardTable();
                        break;

                    //this sets username to something other than player one
                    case "11":
                        input = scanner.nextLine();
                        patienceGameLogic.setUserName(input);
                        System.out.print("user set to: " + patienceGameLogic.getUserName());
                        break;

                    case "12":
                        System.out.println("Exiting the game");
                        finishGame = true;
                        break;

                    //this is if the user enters incorrect input
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } while (!finishGame); //or 8???
            scanner.close();
        };

        Thread commandLineThread = new Thread(commandLineTask);
        // This is how we start the thread.
        // This causes the run method to execute.
        commandLineThread.start();
    }
    //updates card table with the current state of piles

    private void updateCardTable() {
        try {
            ArrayList<String> cardStrings = patienceGameLogic.getCardTableRepresentation();
            cardTable.cardDisplay(cardStrings);
        } catch (NullPointerException e) {
            throw new RuntimeException("Invalid card in deck");
        }
    }

    // //////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        //Game game = new Game();
        //game.playGame();
        Application.launch(args);
    }
}


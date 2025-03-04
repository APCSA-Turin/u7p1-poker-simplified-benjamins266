package com.example.project;
import java.util.ArrayList;


public class Game{
    //determines the winner between two players
    public static String determineWinner(Player p1, Player p2,String p1Hand, String p2Hand,ArrayList<Card> communityCards){
        //creates and initializes a variable meant to store player 1's highest card
        int p1HighCard = 0;
        //creates and initializes a variable meant to store player 2's highest card
        int p2HighCard = 0;
        //if player 1 has a higher hand ranking than player 2, return Player 1 wins!
        if(Utility.getHandRanking(p1Hand) > Utility.getHandRanking(p2Hand)){
            return "Player 1 wins!";
        //if player 2 has a higher hand ranking than player 1, return Player 2 wins!
        } else if(Utility.getHandRanking(p1Hand) < Utility.getHandRanking(p2Hand)){
            return "Player 2 wins!";
        //if the 2 hands are equal ranking, it will check to see which players highest card is higher
        } else if(Utility.getHandRanking(p1Hand) == Utility.getHandRanking(p2Hand)) {
            //checks to see which of the 2 cards in player 1's hand is higher, and sets the highest ranked card to p1HighCard 
            if(Utility.getRankValue(p1.getHand().get(1).getRank()) >= Utility.getRankValue(p1.getHand().get(0).getRank())){
                p1HighCard = Utility.getRankValue(p1.getHand().get(1).getRank());
            } else {
                p1HighCard = Utility.getRankValue(p1.getHand().get(0).getRank());
            }
            //checks to see which of the 2 cards in player 2's hand is higher, and sets the highest ranked card to p2HighCard 
            if(Utility.getRankValue(p2.getHand().get(1).getRank()) >= Utility.getRankValue(p2.getHand().get(0).getRank())){
                p2HighCard = Utility.getRankValue(p2.getHand().get(1).getRank());
            } else {
                p2HighCard = Utility.getRankValue(p2.getHand().get(0).getRank());
            }
            //if player 2's highest card
            //is higher than player 1's highest card
            //it returns Player 2 wins!
            if(p2HighCard>p1HighCard){
                return "Player 2 wins!";
            //if player 1's highest card
            //is higher than player 2's highest card
            //it returns Player 1 wins!
            } else if(p2HighCard<p1HighCard){
                return "Player 1 wins!";
            }
        }
        //if both players highest cards are equal
        //it returns Tie!
        return "Tie!";
    

    }

    public static void play(){ //simulate card playing
    
    }
        
        

}
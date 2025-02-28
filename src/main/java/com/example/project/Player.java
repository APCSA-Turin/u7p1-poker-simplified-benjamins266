package com.example.project;
import java.util.ArrayList;


public class Player{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    public Player(){
        hand = new ArrayList<>();
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    public void addCard(Card c){
        hand.add(c);
    }

    public String playHand(ArrayList<Card> communityCards){   
        allCards = new ArrayList<>(hand);
        for (Card card1 : communityCards) {
            allCards.add(card1);
        }
        sortAllCards();
        String outcome = "Nothing";

        ArrayList<Integer> rankF = findRankingFrequency();
        ArrayList<Integer> suitF = findSuitFrequency();

        if (highCard()) {
            outcome = "High Card";
        }
        if(onePair()){
            outcome = "A Pair";
        }
        if(twoPair()){
            outcome = "Two Pair";
        }
        
        return outcome;
   }


    private boolean highCard() {
        int highestValue = Integer.MIN_VALUE;
        for (int i = 0; i < hand.size(); i ++) {
            if (Utility.getRankValue(hand.get(i).getRank()) > highestValue) {
                highestValue = Utility.getRankValue(hand.get(i).getRank());
            }
        }
        if (Utility.getRankValue(allCards.get(allCards.size() - 1).getRank()) == highestValue) {
            return true;
        }
        return false;
    }

    private boolean onePair(){
        ArrayList<Integer> rankFrequency = findRankingFrequency();
        for(int i = 0; i<rankFrequency.size(); i++){
            if(rankFrequency.get(i)>1){
                return true;
            }
        }
        return false;
    }

    private boolean twoPair(){
        ArrayList<Integer> rankFrequency = findRankingFrequency();
        int count = 0;
        for(int i = 0; i<rankFrequency.size(); i++){
            if(rankFrequency.get(i)>1){
                count++;
            }
        }
        if(count == 2){
            return true;
        }
        return false;

    }



    public void sortAllCards(){
        for(int i = 0; i<allCards.size(); i++){
            int lowestValue = i;
            for(int j = i; j<allCards.size(); j++){
                if(Utility.getRankValue(allCards.get(j).getRank())<Utility.getRankValue(allCards.get(lowestValue).getRank())){
                    lowestValue = j;
                }
            }
            Card temp = allCards.get(i);
            allCards.set(i, allCards.get(lowestValue));
            allCards.set(lowestValue,temp);
        }   
        }
 

    public ArrayList<Integer> findRankingFrequency(){
        ArrayList<Integer> frequency = new ArrayList<>();
        for(int i = 0; i<ranks.length; i++){
            int count = 0;
            for(int j = 0; j<allCards.size(); j++){
                if(Utility.getRankValue(allCards.get(j).getRank()) == Utility.getRankValue(ranks[i])){
                    count++;
                }
            }
            frequency.add(count);
        }
        return frequency; 
    }

    public ArrayList<Integer> findSuitFrequency(){
        ArrayList<Integer> suitFrequency = new ArrayList<>();
        for(int i = 0; i<suits.length; i++){
            int count = 0;
            for(int j = 0; j<allCards.size(); j++){
                if(allCards.get(j).getSuit().equals(suits[i])){
                    count++;
                }
            }
            suitFrequency.add(count);
        }
        return suitFrequency; 
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }

}

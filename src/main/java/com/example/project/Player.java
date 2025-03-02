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

        // ArrayList<Integer> rankF = findRankingFrequency();
        // ArrayList<Integer> suitF = findSuitFrequency();

        if (highCard()) {
            outcome = "High Card";
        }
        if(onePair()){
            outcome = "A Pair";
        }
        if(twoPair()){
            outcome = "Two Pair";
        }
        if(threeOfAKind()){
            outcome = "Three of a Kind";
        }
        if(straight()){
            outcome = "Straight";
        }
        if(flush()){
            outcome = "Flush";
        }
        if(fullHouse()){
            outcome = "Full House";
        }
        if(quads()){
            outcome = "Four of a Kind";
        }
        if (straightFlush()){
            outcome = "Straight Flush";
        }
        if(royalFlush()){
            outcome = "Royal Flush";
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

    private boolean threeOfAKind(){
        ArrayList<Integer> rankFrequency = findRankingFrequency();
        for(int i = 0; i<rankFrequency.size(); i++){
            if(rankFrequency.get(i)==3){
                return true;
            }
        }
        return false;
    }

    private boolean straight(){
        int num1 = Utility.getRankValue(allCards.get(0).getRank());
        int num2 = Utility.getRankValue(allCards.get(1).getRank());
        int num3 = Utility.getRankValue(allCards.get(2).getRank());
        int num4 = Utility.getRankValue(allCards.get(3).getRank());
        int num5 = Utility.getRankValue(allCards.get(4).getRank());
        if(num1+1 == num2){
            if(num2+1 == num3){
                if(num3+1 == num4){
                    if(num4+1 == num5){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean flush(){
        ArrayList<Integer> suitFrequency = findSuitFrequency();
        for(int i = 0; i<suitFrequency.size(); i++){
            if(suitFrequency.get(i) == 5){
                return true;
            }
        }
        return false;
    }

    private boolean fullHouse(){
        ArrayList<Integer> rankFrequency = findRankingFrequency();
        boolean threeKind = false;
        boolean twoKind = false;
        for(int i = 0; i<rankFrequency.size(); i++){
            if(rankFrequency.get(i) == 3){
                threeKind = true;
            }
            if(rankFrequency.get(i) == 2){
                twoKind = true;
            }
        }
        if(threeKind == true && twoKind == true){
            return true;
        } else {
            return false;
        }

    }

    private boolean quads(){
        ArrayList<Integer> rankFrequency = findRankingFrequency();
        for(int i = 0; i<rankFrequency.size(); i++){
            if(rankFrequency.get(i)==4){
                return true;
            }
        }
        return false;
    }

    private boolean straightFlush(){
        boolean isStraight = straight();
        boolean isFlush = flush();
        if(isFlush == true && isStraight == true){
            return true;
        } else {
            return false;
        }
    }

    private boolean royalFlush(){
        if(allCards.get(0).getRank().equals("10") && straightFlush() == true){
            return true;
        } else {
            return false;
        }
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

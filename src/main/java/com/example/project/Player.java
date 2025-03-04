package com.example.project;
import java.util.ArrayList;


public class Player{
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; //the current community cards + hand
    String[] suits  = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    
    //constructs a player object
    public Player(){
        hand = new ArrayList<>();
    }

    public ArrayList<Card> getHand(){return hand;}
    public ArrayList<Card> getAllCards(){return allCards;}

    //adds a card to the hand ArrayList
    public void addCard(Card c){
        hand.add(c);
    }

    //the logic behind the poker game,
    //returns the level of hand the player had.
    public String playHand(ArrayList<Card> communityCards){ 
        //sets the allCards array to the cards in the players hand  
        allCards = new ArrayList<>(hand);
        //adds the community cards to the allCards array
        for (Card card1 : communityCards) {
            allCards.add(card1);
        }
        //sorts the cards from lowest value to greatest
        sortAllCards();
        // initializes the outcome variable to "Nothing"
        String outcome = "Nothing";

        //if this is true, it sets the outcome variable to High Card
        if (highCard()) {
            outcome = "High Card";
        }
        //if this is true, it sets the outcome variable to A Pair
        if(onePair()){
            outcome = "A Pair";
        }
        //if this is true, it sets the outcome variable to Two Pair
        if(twoPair()){
            outcome = "Two Pair";
        }
        //if this is true, it sets the outcome variable to Three of a Kind
        if(threeOfAKind()){
            outcome = "Three of a Kind";
        }
        //if this is true, it sets the outcome variable to Straight
        if(straight()){
            outcome = "Straight";
        }
        //if this is true, it sets the outcome variable to Flush
        if(flush()){
            outcome = "Flush";
        }
        //if this is true, it sets the outcome variable to Full House
        if(fullHouse()){
            outcome = "Full House";
        }
        //if this is true, it sets the outcome variable to Four of a Kind
        if(quads()){
            outcome = "Four of a Kind";
        }
        //if this is true, it sets the outcome variable to Straight Flush
        if (straightFlush()){
            outcome = "Straight Flush";
        }
        //if this is true, it sets the outcome variable to Royal Flush
        if(royalFlush()){
            outcome = "Royal Flush";
        }
        
        //returns the outcome variable
        return outcome;
   }

   //determines if the player has a high card
    private boolean highCard() {
        //creates a highestValue variable that is meant to be the largest value of allCards
        int highestValue = Integer.MIN_VALUE;
        //iterates through the cards in hand, and checks if the card is greater than the highest value
        for (int i = 0; i < hand.size(); i ++) {
            if (Utility.getRankValue(hand.get(i).getRank()) > highestValue) {
                //if it is greater, the highestValue variable is set to the value of that card
                highestValue = Utility.getRankValue(hand.get(i).getRank());
            }
        }
        //if the highestValue card in allCards is equal to the highestValue card in the players hand, it returns true;
        if (Utility.getRankValue(allCards.get(allCards.size() - 1).getRank()) == highestValue) {
            return true;
        }
        //otherwise it returns false
        return false;
    }

    //determines if the player has a pair
    private boolean onePair(){
        //creates a rankFrequency variable, using the ranking frequency method
        ArrayList<Integer> rankFrequency = findRankingFrequency();
        //iterates through all of the values in the rankFrequency arraylist
        for(int i = 0; i<rankFrequency.size(); i++){
            //if any of the values is greater than 1, return true, because that means there is a pair
            if(rankFrequency.get(i)>1){
                return true;
            }
        }
        //otherwise return false
        return false;
    }

    //determines if the player has a two pair
    private boolean twoPair(){
        //creates a rankFrequency variable, using the ranking frequency method
        ArrayList<Integer> rankFrequency = findRankingFrequency();
        //creates and initializes a count variable to 0
        int count = 0;
        //iterates through the rankFrequency list
        for(int i = 0; i<rankFrequency.size(); i++){
            //if the frequencylist contains any pairs, count will be increased by 1
            if(rankFrequency.get(i)>1){
                count++;
            }
        }
        //if the count is equal to 2, then return true
        if(count == 2){
            return true;
        }
        //otherwise return false
        return false;

    }

    //determines if the player has a three of a kind
    private boolean threeOfAKind(){
        //creates a rankFrequency variable, using the ranking frequency method
        ArrayList<Integer> rankFrequency = findRankingFrequency();
        //iterates through the rankFrequency list
        for(int i = 0; i<rankFrequency.size(); i++){
            //if any of the values in the list are equal to 3, it returns true
            if(rankFrequency.get(i)==3){
                return true;
            }
        }
        //otherwise return false
        return false;
    }

    //determines if the player has a straight
    private boolean straight(){
        //creates 5 int variables containing the rank values of the 5 cards in allCards
        int num1 = Utility.getRankValue(allCards.get(0).getRank());
        int num2 = Utility.getRankValue(allCards.get(1).getRank());
        int num3 = Utility.getRankValue(allCards.get(2).getRank());
        int num4 = Utility.getRankValue(allCards.get(3).getRank());
        int num5 = Utility.getRankValue(allCards.get(4).getRank());
        //if the first card's rank value is 1 less then the second card
        if(num1+1 == num2){
            //then it checks if the second card's rank value is 1 less then the third card
            if(num2+1 == num3){
                //then it checks if the third card's rank value is 1 less then the fourth card
                if(num3+1 == num4){
                    //then it checks if the fourth card's rank value is 1 less then the fifth card
                    if(num4+1 == num5){
                        //if all of these are true, it returns true
                        return true;
                    }
                }
            }
        }
        //otherwise it returns false
        return false;
    }

    //determines if the player has a flush
    private boolean flush(){
        //creates a suitFrequency variable, using the suit frequency method
        ArrayList<Integer> suitFrequency = findSuitFrequency();
        //it iterates through the suitFrequency list
        for(int i = 0; i<suitFrequency.size(); i++){
            //it checks if any value in the suitFrequency list is equal to 5
            if(suitFrequency.get(i) == 5){
                //it returns true
                return true;
            }
        }
        //otherwise returns false
        return false;
    }

    //determines if the player has a full house
    private boolean fullHouse(){
        //creates a rankFrequency variable, using the ranking frequency method
        ArrayList<Integer> rankFrequency = findRankingFrequency();
        //creates and initializes a threeKind variable to false
        boolean threeKind = false;
        //creates and initializes a twoKind variable to false
        boolean twoKind = false;
        //iterates through the rankFrequency list
        for(int i = 0; i<rankFrequency.size(); i++){
            //if any value in the list is equal to 3, it sets threeKind to true
            if(rankFrequency.get(i) == 3){
                threeKind = true;
            }
            //if any value in the list is equal to 2, it sets twoKind to true
            if(rankFrequency.get(i) == 2){
                twoKind = true;
            }
        }
        //if both threeKind and twoKind are true, the program returns true
        if(threeKind == true && twoKind == true){
            return true;
        } else {
            return false;
        }

    }

    //determines if the player has a four of a kind
    private boolean quads(){
        //creates a rankFrequency variable, using the ranking frequency method
        ArrayList<Integer> rankFrequency = findRankingFrequency();
        //iterates through the rankFrequency list
        for(int i = 0; i<rankFrequency.size(); i++){
            //if any value in the frequency list is equal to 4, it returns true
            if(rankFrequency.get(i)==4){
                return true;
            }
        }
        return false;
    }

    //determines if the player has a straight flush
    private boolean straightFlush(){
        //creates an isStraight variable that is initialized to the boolean value returned by the straight method
        boolean isStraight = straight();
        //creates an isStraight variable that is initialized to the boolean value returned by the flush method
        boolean isFlush = flush();
        //if both isFlush and isStraight are true, the program returns true
        if(isFlush == true && isStraight == true){
            return true;
        } else {
            return false;
        }
    }

    //determines if the player has a royal flush
    private boolean royalFlush(){
        //if the player has a straight flush, and the lowest value card of allCards is a 10
        //it returns true
        if(allCards.get(0).getRank().equals("10") && straightFlush() == true){
            return true;
        } else {
            return false;
        }
    }

    public void sortAllCards(){
        //iterates through the allCards arrayList
        for(int i = 0; i<allCards.size(); i++){
            //creates a lowestValue variable and sets it to the value of i
            int lowestValue = i;
            //another iteration of the allCards arrayList, but it begins at the value of i
            for(int j = i; j<allCards.size(); j++){
                //if the value of the rank of card at position j is less than the value of the rank of the card at position i
                //it sets the lowestValue variable to the card at position j
                if(Utility.getRankValue(allCards.get(j).getRank())<Utility.getRankValue(allCards.get(lowestValue).getRank())){
                    lowestValue = j;
                }
            }
            //swaps the cards at position i, and position at the lowestValue
            Card temp = allCards.get(i);
            allCards.set(i, allCards.get(lowestValue));
            allCards.set(lowestValue,temp);
        }   
        }
 
    //creates a frequency list for ranking
    public ArrayList<Integer> findRankingFrequency(){
        //creates a new frequency array list
        ArrayList<Integer> frequency = new ArrayList<>();
        //iterates through all of the ranks
        for(int i = 0; i<ranks.length; i++){
            //creates a new count variable and sets to 0
            int count = 0;
            //iterates through the allCards arrayList
            for(int j = 0; j<allCards.size(); j++){
                //if the rank value of the card at position j in allCards
                //is equal to the rank value of the card at position i in the ranks array
                //it increases count by 1
                if(Utility.getRankValue(allCards.get(j).getRank()) == Utility.getRankValue(ranks[i])){
                    count++;
                }
            }
            //adds the count to the frequency arrayList
            frequency.add(count);
        }
        //returns the frequency arrayList
        return frequency; 
    }

    public ArrayList<Integer> findSuitFrequency(){
        //creates a new suitFrequency array list
        ArrayList<Integer> suitFrequency = new ArrayList<>();
        //iterates through all of the ranks
        for(int i = 0; i<suits.length; i++){
            //creates a new count variable and sets to 0
            int count = 0;
            //if the suit of the card at position j in allCards
            //is equal to the suit of the card at position i in the suits array
            //it increases count by 1
            for(int j = 0; j<allCards.size(); j++){
                if(allCards.get(j).getSuit().equals(suits[i])){
                    count++;
                }
            }
            //adds the count to the suitFrequency arrayList
            suitFrequency.add(count);
        }
        //returns the suitFrequency arrayList
        return suitFrequency; 
    }

   
    @Override
    public String toString(){
        return hand.toString();
    }

}

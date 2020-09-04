package sample;

import java.util.*;

public class CardsRepository {

    private final List<Card> allCards;

    public CardsRepository() {
        allCards = new ArrayList<Card>();
    }

    public List<Card> getAllCards() {
        return new ArrayList<Card>(allCards);
    }

    public void addCard(Card cardToAdd) {
        for (Card card : allCards) {
            if (cardToAdd.equals(card))
                throw new IllegalArgumentException("Duplicate Card!");
        }
        allCards.add(cardToAdd);
    }

    public void removeCard(Card cardToRemove) {
        if (!allCards.contains(cardToRemove))
            throw new IllegalArgumentException("Card does not exist");

        allCards.remove(cardToRemove);
    }

    public void updateCard(Card cardToUpdate) {
        if (!allCards.contains(cardToUpdate))
            throw new IllegalArgumentException("Card does not exist");

        allCards.set(allCards.indexOf(cardToUpdate), cardToUpdate);
    }

}

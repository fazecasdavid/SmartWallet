package sample;

import java.util.List;
import java.util.Objects;

public class Service {

    private final CardsRepository cardsRepository;


    public Service(CardsRepository cardsRepository) {
        this.cardsRepository = cardsRepository;
    }

    public List<Card> getAllCards() {
        return cardsRepository.getAllCards();
    }

    public void addCard(String id, String name, int balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative!");
        }

        Card cardToAdd = new Card(id, name, balance);
        cardsRepository.addCard(cardToAdd);
    }

    public void removeCardById(String id) {
        var allCards = cardsRepository.getAllCards();
        for (var card : allCards)
            if (Objects.equals(card.getId(), id)) {
                cardsRepository.removeCard(card);
                break;
            }
    }
    public void updateCard(Card cardToUpdate) {
        cardsRepository.updateCard(cardToUpdate);
    }
    public void withdrawFromCard(Card card, int amount){
        if (card.getBalance() < amount) {
            throw new IllegalArgumentException("Impossible Transaction");
        }
        card.withdraw(amount);
        updateCard(card);
    }

    public void depositToCard(Card card, int amount) {
        card.deposit(amount);
        updateCard(card);
    }

}




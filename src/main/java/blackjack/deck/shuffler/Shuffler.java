package blackjack.deck.shuffler;

import java.util.List;

import blackjack.card.Card;

public interface Shuffler {
    List<Card> shuffle(List<Card> deck);
}

package blackjack.domain.deck.shuffler;

import java.util.List;

import blackjack.domain.card.Card;

public interface Shuffler {
    List<Card> shuffle(List<Card> deck);
}

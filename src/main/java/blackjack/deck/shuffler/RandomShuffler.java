package blackjack.deck.shuffler;

import java.util.Collections;
import java.util.List;

import blackjack.card.Card;

public class RandomShuffler implements Shuffler {
    @Override
    public List<Card> shuffle(List<Card> deck) {
        Collections.shuffle(deck);
        return deck;
    }
}

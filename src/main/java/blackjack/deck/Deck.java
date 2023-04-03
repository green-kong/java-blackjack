package blackjack.deck;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import blackjack.card.Card;
import blackjack.card.Rank;
import blackjack.card.Shape;
import blackjack.deck.shuffler.Shuffler;

public class Deck {
    private final ArrayDeque<Card> deck;

    private Deck(ArrayDeque<Card> deck) {
        this.deck = deck;
    }

    public static Deck createDeck(Shuffler shuffler) {
        List<Card> cards = generateCards();
        ArrayDeque<Card> deck = new ArrayDeque<>(shuffler.shuffle(cards));
        return new Deck(deck);
    }

    private static List<Card> generateCards() {
        return Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(Shape.values()).map(shape -> Card.of(rank, shape)))
                .collect(Collectors.toList());
    }

    public int getSize() {
        return deck.size();
    }

    public Card drawCard() {
        if (deck.isEmpty()) {
            throw new IllegalArgumentException("덱이 비어있습니다.");
        }
        return deck.pollLast();
    }
}

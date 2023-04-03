package blackjack.card;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Card {
    private static final Map<String, Card> cardCacheTable = new HashMap<>();
    private final Rank rank;
    private final Shape shape;

    private Card(Rank rank, Shape shape) {
        this.rank = rank;
        this.shape = shape;
    }

    public static Card of(Rank rank, Shape shape) {
        String key = rank.name() + shape.name();
        Card card = cardCacheTable.get(key);
        if (card == null) {
            card = new Card(rank, shape);
            cardCacheTable.put(key, card);
        }
        return card;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return rank == card.rank && shape == card.shape;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, shape);
    }
}

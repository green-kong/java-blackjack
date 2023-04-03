package blackjack.deck.shuffler;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Shape;
import blackjack.domain.deck.shuffler.RandomShuffler;

class RandomShufflerTest {
    @Test
    @DisplayName("카드를 랜덤으로 섞는다.")
    void randomShuffle() {
        RandomShuffler randomShuffler = new RandomShuffler();
        List<Card> cards = new ArrayList<>();
        cards.add(Card.of(Rank.TWO, Shape.HEART));
        cards.add(Card.of(Rank.THREE, Shape.HEART));
        cards.add(Card.of(Rank.FOUR, Shape.HEART));
        cards.add(Card.of(Rank.FIVE, Shape.HEART));
        cards.add(Card.of(Rank.SIX, Shape.HEART));

        List<Card> shuffled = randomShuffler.shuffle(cards);

        assertThat(shuffled).containsExactlyInAnyOrderElementsOf(cards);
    }
}

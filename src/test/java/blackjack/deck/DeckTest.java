package blackjack.deck;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.deck.shuffler.CheatShuffler;
import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Shape;
import blackjack.domain.deck.Deck;
import blackjack.domain.deck.shuffler.RandomShuffler;

class DeckTest {
    @Test
    @DisplayName("랜덤셔플러를 사용하면 덱의 초기 카드수는 52장이다.")
    void checkLength() {
        Deck deck = Deck.createDeck(new RandomShuffler());

        assertThat(deck.getSize()).isEqualTo(52);
    }

    @Test
    @DisplayName("가장 위의 카드를 한장 가져온다")
    void draw() {
        CheatShuffler cheatShuffler = new CheatShuffler(List.of(
                Card.of(Rank.TWO, Shape.HEART),
                Card.of(Rank.FIVE, Shape.HEART),
                Card.of(Rank.TEN, Shape.HEART),
                Card.of(Rank.THREE, Shape.HEART)
        ));

        Deck deck = Deck.createDeck(cheatShuffler);
        Card card = deck.drawCard();

        assertThat(card).isEqualTo(Card.of(Rank.THREE, Shape.HEART));
    }

    @Test
    @DisplayName("draw 할 때 덱이 비어있으면 예외가 발생한다.")
    void drawException() {
        CheatShuffler cheatShuffler = new CheatShuffler(List.of(
                Card.of(Rank.TWO, Shape.HEART)
        ));
        Deck deck = Deck.createDeck(cheatShuffler);

        deck.drawCard();

        assertThatThrownBy(deck::drawCard)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("덱이 비어있습니다.");
    }
}

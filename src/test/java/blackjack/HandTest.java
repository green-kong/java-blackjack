package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.card.Card;
import blackjack.card.Rank;
import blackjack.card.Shape;
import blackjack.card.score.Score;

class HandTest {
    List<Card> cards;
    Hand hand;

    @BeforeEach
    void setUp() {
        cards = new ArrayList<>();
        hand = new Hand(cards);
    }

    @Test
    @DisplayName("핸드에 카드를 추가한다.")
    void addCard() {
        Card card = Card.of(Rank.TWO, Shape.HEART);
        hand.addCard(card);

        assertThat(cards).contains(card);
    }

    @Test
    @DisplayName("핸드에 있는 카드의 점수 합계를 구한다.")
    void calculateScoreSum() {
        hand.addCard(Card.of(Rank.TWO, Shape.HEART));
        hand.addCard(Card.of(Rank.THREE, Shape.HEART));
        hand.addCard(Card.of(Rank.FOUR, Shape.HEART));
        hand.addCard(Card.of(Rank.FIVE, Shape.HEART));
        hand.addCard(Card.of(Rank.SIX, Shape.HEART));

        Score score = hand.calculateScoreSum();

        assertThat(score).isEqualTo(new Score(20));
    }

    @Test
    @DisplayName("핸드에 ACE가 포함되어있고, 점수의 합계가 11점 이하이면 10점을 더한값을 구한다.")
    void calculateScoreSumWithAceAdditionalScore() {
        hand.addCard(Card.of(Rank.ACE, Shape.HEART));
        hand.addCard(Card.of(Rank.SIX, Shape.HEART));

        Score score = hand.calculateScoreSum();

        assertThat(score).isEqualTo(new Score(17));
    }

    @Test
    @DisplayName("핸드에 ACE가 포함되어있지만, 점수의 합계가 11점 초과이면 원래 점수를 반환한다.")
    void calculateScoreSumWithoutAceAdditionalScore() {
        hand.addCard(Card.of(Rank.ACE, Shape.HEART));
        hand.addCard(Card.of(Rank.SIX, Shape.HEART));
        hand.addCard(Card.of(Rank.SIX, Shape.DIAMOND));

        Score score = hand.calculateScoreSum();

        assertThat(score).isEqualTo(new Score(13));
    }
}

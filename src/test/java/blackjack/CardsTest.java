package blackjack;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Shape;
import blackjack.domain.card.score.Score;

class CardsTest {
    List<Card> cardList;
    Cards cards;

    @BeforeEach
    void setUp() {
        cardList = new ArrayList<>();
        cards = new Cards(cardList);
    }

    @Test
    @DisplayName("핸드에 카드를 추가한다.")
    void addCard() {
        Card card = Card.of(Rank.TWO, Shape.HEART);
        cards.addCard(card);

        assertThat(cardList).contains(card);
    }

    @Test
    @DisplayName("핸드에 있는 카드의 점수 합계를 구한다.")
    void calculateScoreSum() {
        cards.addCard(Card.of(Rank.TWO, Shape.HEART));
        cards.addCard(Card.of(Rank.THREE, Shape.HEART));
        cards.addCard(Card.of(Rank.FOUR, Shape.HEART));
        cards.addCard(Card.of(Rank.FIVE, Shape.HEART));
        cards.addCard(Card.of(Rank.SIX, Shape.HEART));

        Score score = cards.calculateScoreSum();

        assertThat(score).isEqualTo(new Score(20));
    }

    @Test
    @DisplayName("핸드에 ACE가 포함되어있고, 점수의 합계가 11점 이하이면 10점을 더한값을 구한다.")
    void calculateScoreSumWithAceAdditionalScore() {
        cards.addCard(Card.of(Rank.ACE, Shape.HEART));
        cards.addCard(Card.of(Rank.SIX, Shape.HEART));

        Score score = cards.calculateScoreSum();

        assertThat(score).isEqualTo(new Score(17));
    }

    @Test
    @DisplayName("핸드에 ACE가 포함되어있지만, 점수의 합계가 11점 초과이면 원래 점수를 반환한다.")
    void calculateScoreSumWithoutAceAdditionalScore() {
        cards.addCard(Card.of(Rank.ACE, Shape.HEART));
        cards.addCard(Card.of(Rank.SIX, Shape.HEART));
        cards.addCard(Card.of(Rank.SIX, Shape.DIAMOND));

        Score score = cards.calculateScoreSum();

        assertThat(score).isEqualTo(new Score(13));
    }

    @Test
    @DisplayName("카드의 점수 총합이 21점 초과이면, true를 반환한다.")
    void isBust() {
        cards.addCard(Card.of(Rank.TWO, Shape.HEART));
        cards.addCard(Card.of(Rank.TWO, Shape.DIAMOND));
        cards.addCard(Card.of(Rank.THREE, Shape.HEART));
        cards.addCard(Card.of(Rank.FOUR, Shape.HEART));
        cards.addCard(Card.of(Rank.FIVE, Shape.HEART));
        cards.addCard(Card.of(Rank.SIX, Shape.HEART));

        assertThat(cards.isBust()).isTrue();
    }

    @Test
    @DisplayName("블랙잭인 경우 true를 반환한다.")
    void isBlackjack() {
        cards.addCard(Card.of(Rank.ACE, Shape.HEART));
        cards.addCard(Card.of(Rank.KING, Shape.HEART));

        assertThat(cards.isBlackjack()).isTrue();
    }

    @Test
    @DisplayName("카드의 심볼을 리스트로 반환한다.")
    void getNames() {
        cards.addCard(Card.of(Rank.ACE, Shape.HEART));
        cards.addCard(Card.of(Rank.KING, Shape.HEART));
        cards.addCard(Card.of(Rank.TEN, Shape.SPADE));
        cards.addCard(Card.of(Rank.THREE, Shape.CLOVER));

        List<String> names = cards.getNames();

        assertThat(names).containsExactlyInAnyOrder("A하트", "K하트", "10스페이드", "3클로버");
    }
}

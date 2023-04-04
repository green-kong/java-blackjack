package blackjack.domain.participants;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Shape;

class DealerTest {
    Dealer dealer;

    @BeforeEach
    void setUp() {
        dealer = new Dealer();
    }

    @Test
    @DisplayName("카드를 드로우하면 카드를 저장한다.")
    void takeCard() {
        dealer.takeCard(Card.of(Rank.SEVEN, Shape.HEART));
        dealer.takeCard(Card.of(Rank.EIGHT, Shape.HEART));
        Participant participant = dealer.getParticipant();

        List<String> cardNames = participant.getCardNames();

        assertThat(cardNames).containsExactlyInAnyOrder("7하트", "8하트");
    }

    @Test
    @DisplayName("카드의 점수 총합이 16점 이하인 경우 true를 반환한다.")
    void isUnderScore() {
        dealer.takeCard(Card.of(Rank.SEVEN, Shape.HEART));
        dealer.takeCard(Card.of(Rank.EIGHT, Shape.HEART));

        assertThat(dealer.isUnderScore()).isTrue();
    }

    @Test
    @DisplayName("카드의 점수 총합이 16점 초과인 경우 false 반환한다.")
    void isUnderScoreFalse() {
        dealer.takeCard(Card.of(Rank.SEVEN, Shape.HEART));
        dealer.takeCard(Card.of(Rank.EIGHT, Shape.HEART));
        dealer.takeCard(Card.of(Rank.TWO, Shape.HEART));

        assertThat(dealer.isUnderScore()).isFalse();
    }

    @Test
    @DisplayName("stay를 하면 isDrawable이 false를 반환한다.")
    void stay() {
        dealer.stay();

        assertThat(dealer.isDrawable()).isFalse();
    }
}

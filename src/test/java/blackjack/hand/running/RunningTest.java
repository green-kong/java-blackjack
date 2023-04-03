package blackjack.hand.running;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.card.Card;
import blackjack.card.Cards;
import blackjack.card.Rank;
import blackjack.card.Shape;
import blackjack.hand.Hand;
import blackjack.hand.finished.Blackjack;
import blackjack.hand.finished.Bust;
import blackjack.hand.finished.Stay;

class RunningTest {
    Hand hand;

    @BeforeEach
    void setUp() {
        hand = new Hit(new Cards(new ArrayList<>()));
    }

    @Test
    @DisplayName("hit에서 stay를 하면 stay를 리턴한다.")
    void hitToStay() {
        Hand stay = hand.stay();

        assertThat(stay instanceof Stay).isTrue();
    }

    @Test
    @DisplayName("hit 상태에서 카드 추가시 22점 이상이 되면 Bust를 반환한다.")
    void hitToBust() {
        hand = hand.hit(Card.of(Rank.TEN, Shape.HEART));
        hand = hand.hit(Card.of(Rank.TEN, Shape.DIAMOND));
        hand = hand.hit(Card.of(Rank.TWO, Shape.HEART));

        assertThat(hand instanceof Bust).isTrue();
    }

    @Test
    @DisplayName("hit 상태에서 카드 추가시 22점 미만이면 자기자신을 반환한다.")
    void hitToHit() {
        hand = hand.hit(Card.of(Rank.TEN, Shape.HEART));
        Hand result = hand.hit(Card.of(Rank.TEN, Shape.DIAMOND));

        assertThat(result).isEqualTo(hand);
    }

    @Test
    @DisplayName("hit 상태에서 카드 추가시 blackjack인 경우 Blackjack을 반환한다.")
    void hitToBlackjack() {
        hand = hand.hit(Card.of(Rank.TEN, Shape.HEART));
        hand = hand.hit(Card.of(Rank.ACE, Shape.HEART));

        assertThat(hand instanceof Blackjack).isTrue();
    }
}

package blackjack.hand.running;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Shape;
import blackjack.domain.hand.Hand;
import blackjack.domain.hand.finished.Blackjack;
import blackjack.domain.hand.finished.Bust;
import blackjack.domain.hand.finished.Stay;
import blackjack.domain.hand.running.Hit;

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

    @Test
    @DisplayName("running의 구현체는 isDrawable을 실행하면 true를 반환한다.")
    void isDrawable() {
        assertThat(hand.isDrawable()).isTrue();
    }

    @Test
    @DisplayName("running의 구현체는 battle을 실행하면 UOE를 던진다.")
    void battle() {
        Blackjack other = new Blackjack(new Cards(new ArrayList<>()));
        
        assertThatThrownBy(() -> hand.battle(other))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}

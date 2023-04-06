package blackjack.domain.hand.finished;

import static blackjack.domain.card.Card.of;
import static blackjack.domain.card.Rank.SEVEN;
import static blackjack.domain.card.Shape.HEART;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Cards;

class FinishedTest {
    Finished finished = new Blackjack(new Cards(new ArrayList<>()));

    @Test
    @DisplayName("finished의 구현체는 hit시 UOE를 던진다.")
    void hit() {
        assertThatThrownBy(() -> finished.hit(of(SEVEN, HEART)))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("finished의 구현체는 stay시 UOE를 던진다.")
    void stay() {
        assertThatThrownBy(() -> finished.stay())
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("finished의 구현체는 isDrawable을 실행하면 false를 반환한다.")
    void isDrawable() {
        assertThat(finished.isDrawable()).isFalse();
    }
}

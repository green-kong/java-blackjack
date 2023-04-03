package blackjack.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    @DisplayName("카드를 생성한다.")
    void construct() {
        Card card = Card.of(Rank.TWO, Shape.HEART);
        assertThat(card).isEqualTo(Card.of(Rank.TWO, Shape.HEART));
    }
}

package blackjack.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Shape;
import blackjack.domain.card.score.Score;

class CardTest {

    public static Stream<Arguments> provideScoreResult() {
        return Stream.of(
                Arguments.of(Card.of(Rank.TWO, Shape.HEART), new Score(2)),
                Arguments.of(Card.of(Rank.THREE, Shape.HEART), new Score(3)),
                Arguments.of(Card.of(Rank.KING, Shape.HEART), new Score(10)),
                Arguments.of(Card.of(Rank.JACK, Shape.HEART), new Score(10)),
                Arguments.of(Card.of(Rank.ACE, Shape.HEART), new Score(1))
        );
    }

    public static Stream<Arguments> provideSymbolResults() {
        return Stream.of(
                Arguments.of(Card.of(Rank.SEVEN, Shape.HEART), "7하트"),
                Arguments.of(Card.of(Rank.EIGHT, Shape.HEART), "8하트"),
                Arguments.of(Card.of(Rank.ACE, Shape.DIAMOND), "A다이아몬드"),
                Arguments.of(Card.of(Rank.TEN, Shape.SPADE), "10스페이드")
        );
    }

    @Test
    @DisplayName("카드를 생성한다.")
    void construct() {
        Card card = Card.of(Rank.TWO, Shape.HEART);
        assertThat(card).isEqualTo(Card.of(Rank.TWO, Shape.HEART));
    }

    @ParameterizedTest(name = "카드의 점수를 반환한다.")
    @MethodSource("provideScoreResult")
    void score(Card card, Score expected) {
        Score result = card.score();

        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("에이스 카드이면 true를 반환한다.")
    void isAceTrue() {
        Card card = Card.of(Rank.ACE, Shape.HEART);

        assertThat(card.isAce()).isTrue();
    }

    @Test
    @DisplayName("에이스 카드가 아니면 false 반환한다.")
    void isAceFalse() {
        Card card = Card.of(Rank.TEN, Shape.HEART);

        assertThat(card.isAce()).isFalse();
    }

    @ParameterizedTest(name = "카드의 심볼을 반환한다.")
    @MethodSource("provideSymbolResults")
    void getSymbol(Card card, String expected) {
        String symbol = card.getSymbol();

        assertThat(symbol).isEqualTo(expected);
    }

}

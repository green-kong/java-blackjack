package blackjack.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.card.score.Score;

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
}

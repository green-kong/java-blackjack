package blackjack.card.score;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.score.Score;
import blackjack.domain.card.score.ScoreCalculator;

class ScoreCalculatorTest {
    @Test
    @DisplayName("점수를 합계를 구한다.")
    void add() {
        Score score1 = new Score(10);
        Score score2 = new Score(8);

        Score result = ScoreCalculator.add(score1, score2);

        assertThat(result).isEqualTo(new Score(18));
    }

    @Test
    @DisplayName("점수의 합계가 11점 이하라면 true를 반환한다.")
    void isAcceptableAdditionalScore() {
        Score score = new Score(11);

        assertThat(score.isAcceptableAdditionalScore()).isTrue();
    }

    @Test
    @DisplayName("점수의 합계가 11점 초과라면 false를 반환한다.")
    void isAcceptableAdditionalScoreFalse() {
        Score score = new Score(12);

        assertThat(score.isAcceptableAdditionalScore()).isFalse();
    }
}

package blackjack.card.score;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ScoreCalculatorTest {
    @Test
    @DisplayName("점수를 합계를 구한다.")
    void add() {
        Score score1 = new Score(10);
        Score score2 = new Score(8);

        Score result = ScoreCalculator.add(score1, score2);

        assertThat(result).isEqualTo(new Score(18));
    }
}

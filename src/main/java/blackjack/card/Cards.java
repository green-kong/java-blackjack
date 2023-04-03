package blackjack.card;

import static blackjack.card.score.Score.ACE_ADDITIONAL_SCORE;
import static blackjack.card.score.Score.ZERO_SCORE;

import java.util.List;

import blackjack.card.score.Score;
import blackjack.card.score.ScoreCalculator;

public final class Cards {
    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Score calculateScoreSum() {
        Score sum = calculateSumScore();
        sum = calculateFinalScore(sum);
        return sum;
    }

    private Score calculateFinalScore(Score sum) {
        if (sum.isAcceptableAdditionalScore() && containAce()) {
            sum = ScoreCalculator.add(sum, ACE_ADDITIONAL_SCORE);
        }
        return sum;
    }

    private Score calculateSumScore() {
        return cards.stream()
                .map(Card::score)
                .reduce(ZERO_SCORE, ScoreCalculator::add);
    }

    private boolean containAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public boolean isBust() {
        return calculateScoreSum().isBiggerThan(Score.LIMIT_SCORE);
    }

    public boolean isBlackjack() {
        return cards.size() == 2 && calculateScoreSum().equals(Score.LIMIT_SCORE);
    }
}

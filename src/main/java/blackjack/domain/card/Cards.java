package blackjack.domain.card;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.card.score.Score;
import blackjack.domain.card.score.ScoreCalculator;

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
            sum = ScoreCalculator.add(sum, Score.ACE_ADDITIONAL_SCORE);
        }
        return sum;
    }

    private Score calculateSumScore() {
        return cards.stream()
                .map(Card::score)
                .reduce(Score.ZERO_SCORE, ScoreCalculator::add);
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

    public List<String> getNames() {
        return cards.stream()
                .map(Card::getSymbol)
                .collect(Collectors.toUnmodifiableList());
    }
}

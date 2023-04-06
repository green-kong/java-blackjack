package blackjack.domain.hand;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.score.Score;
import blackjack.domain.result.Result;

public interface Hand {
    Hand hit(Card card);

    Hand stay();

    boolean isDrawable();

    Cards getCards();

    Score calculateScore();

    Result battle(Hand other);

    boolean isBlackjack();

    boolean isBust();
}

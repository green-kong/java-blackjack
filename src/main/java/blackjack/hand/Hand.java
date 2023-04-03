package blackjack.hand;

import blackjack.card.Card;
import blackjack.card.Cards;
import blackjack.card.score.Score;

public interface Hand {
    Hand hit(Card card);

    Hand stay();

    boolean isDrawable();

    Cards getCards();

    Score calculateScore();
}

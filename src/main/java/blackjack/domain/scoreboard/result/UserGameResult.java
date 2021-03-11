package blackjack.domain.scoreboard.result;

import blackjack.domain.card.Cards;
import blackjack.domain.scoreboard.WinOrLose;
import blackjack.domain.user.ParticipantName;
import blackjack.domain.user.User;

import java.util.Objects;

public class UserGameResult implements Resultable {
    private final GameResult gameResult;
    private final WinOrLose winOrLose;
    private final long income;

    public UserGameResult(User user, WinOrLose winOrLose, long income) {
        this.gameResult = new GameResult(user.getCards(), user.getName().toString());
        this.winOrLose = winOrLose;
        this.income = income;
    }

    public WinOrLose getWinOrLose() {
        return winOrLose;
    }

    public long getIncome(){
        return income;
    }

    @Override
    public Cards getCards() {
        return gameResult.getCards();
    }

    @Override
    public ParticipantName getName() {
        return gameResult.getName();
    }

    @Override
    public int getScore() {
        return gameResult.getScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserGameResult that = (UserGameResult) o;
        return Objects.equals(gameResult, that.gameResult) && winOrLose == that.winOrLose;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameResult, winOrLose);
    }
}
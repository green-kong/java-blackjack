package blackjack.dto;

import java.util.List;

import blackjack.domain.participants.Participant;

public class ScoreInfoDto {
    private final String name;
    private final List<String> cards;
    private final int score;

    private ScoreInfoDto(String name, List<String> cards, int score) {
        this.name = name;
        this.cards = cards;
        this.score = score;
    }

    public static ScoreInfoDto from(Participant participant) {
        String name = participant.getName().getValue();
        List<String> cards = participant.getCardNames();
        int score = participant.calculateScore().getValue();
        return new ScoreInfoDto(name, cards, score);
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }
}

package blackjack.dto;

import java.util.List;

import blackjack.domain.participants.Participant;

public class PlayerInfoDto {
    private final String name;
    private final List<String> cards;

    private PlayerInfoDto(String name, List<String> cards) {
        this.name = name;
        this.cards = cards;
    }

    public static PlayerInfoDto from(Participant participant) {
        return new PlayerInfoDto(participant.getName().getValue(), participant.getCardNames());
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return cards;
    }
}

package blackjack.domain.participants;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Shape;
import blackjack.domain.result.Result;

class ParticipantTest {
    Participant participant;

    @BeforeEach
    void setUp() {
        participant = new Participant("폴로");
    }

    @Test
    @DisplayName("블랙잭인 경우 상대방이 블랙잭이 아니면 항상 BLACKJACK_WIN을 반환한다.")
    void battle() {
        Participant other = new Participant("주노");
        participant.drawCard(Card.of(Rank.ACE, Shape.DIAMOND));
        participant.drawCard(Card.of(Rank.JACK, Shape.DIAMOND));

        Result result = participant.battle(other);

        assertThat(result).isEqualTo(Result.BLACKJACK_WIN);
    }
}

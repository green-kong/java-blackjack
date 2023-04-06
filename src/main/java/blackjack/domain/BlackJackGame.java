package blackjack.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import blackjack.domain.betting.BettingTable;
import blackjack.domain.betting.Money;
import blackjack.domain.betting.PrizeCalculator;
import blackjack.domain.card.Card;
import blackjack.domain.deck.Deck;
import blackjack.domain.deck.shuffler.Shuffler;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Participants;
import blackjack.domain.participants.Player;
import blackjack.domain.result.Result;

public class BlackJackGame {
    public static final int INITIAL_DRAW_COUNT = 2;
    private final Deck deck;
    private final Participants participants = new Participants();
    private final BettingTable bettingTable = new BettingTable();

    public BlackJackGame(Shuffler shuffler) {
        this.deck = Deck.createDeck(shuffler);
    }

    public void joinPlayers(List<String> names) {
        validateDuplicatedName(names);
        List<Player> players = createPlayers(names);
        participants.addPlayers(players);
        bettingTable.init(players);
    }

    private List<Player> createPlayers(List<String> names) {
        return names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    private void validateDuplicatedName(List<String> names) {
        Set<String> namesWithoutDuplication = new HashSet<>(names);
        if (namesWithoutDuplication.size() != names.size()) {
            throw new IllegalArgumentException("중복된 이름은 사용할 수 없습니다.");
        }
    }

    public List<Player> getPlayers() {
        return participants.getPlayers();
    }

    public void bet(Player player, int bettingAmount) {
        bettingTable.put(player, new Money(bettingAmount));
    }

    public void initialDrawToDealer() {
        for (int i = 0; i < INITIAL_DRAW_COUNT; i++) {
            drawToDealer();
        }
    }

    public void drawToDealer() {
        Card card = deck.drawCard();
        participants.drawToDealer(card);
    }

    public void initialDrawToPlayer(Player player) {
        for (int i = 0; i < INITIAL_DRAW_COUNT; i++) {
            drawToPlayer(player);
        }
    }

    public Participant getDealerInfo() {
        return participants.getDealerInfo();
    }

    public void drawToPlayer(Player player) {
        player.takeCard(deck.drawCard());
    }

    public boolean isDealerUnderScore() {
        return participants.isDealerUnderScore();
    }

    public Map<Player, Money> calculateBettingResult() {
        Map<Player, Money> bettingResultTable = new LinkedHashMap<>();
        Map<Player, Result> playerResultMap = participants.calculateWinning();
        for (Player player : playerResultMap.keySet()) {
            Money money = bettingTable.getMoney(player);
            Result result = playerResultMap.get(player);
            Money prize = PrizeCalculator.calculateByResult(money, result);
            bettingResultTable.put(player, prize);
        }
        return Collections.unmodifiableMap(new LinkedHashMap<>(bettingResultTable));
    }

    public boolean isDealerDrawable() {
        return participants.isDealerDrawable();
    }

    public void stayDealer() {
        participants.stayDealer();
    }
}

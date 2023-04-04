package blackjack.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.BlackJackGame;
import blackjack.domain.betting.Money;
import blackjack.domain.deck.shuffler.RandomShuffler;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Player;
import blackjack.dto.BettingResultDto;
import blackjack.dto.PlayerInfoDto;
import blackjack.dto.ScoreInfoDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Controller {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final BlackJackGame blackJackGame = new BlackJackGame(new RandomShuffler());

    public void run() {
        recruitPlayers();
        takeBettingMoney();
        initialDraw();
        drawAdditionalCardToPlayers();
        drawAdditionalCardToDealer();
        printCardResults();
        printBettingResults();
    }

    private void recruitPlayers() {
        try {
            List<String> playerNames = inputView.readPlayerNames();
            blackJackGame.joinPlayers(playerNames);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void takeBettingMoney() {
        try {
            List<Player> players = blackJackGame.getPlayers();
            getBettingAmountByEachPlayer(players);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void getBettingAmountByEachPlayer(List<Player> players) {
        for (Player player : players) {
            int bettingAmount = inputView.readBettingAmount(player.getName());
            blackJackGame.bet(player, bettingAmount);
        }
    }

    private void initialDraw() {
        blackJackGame.initialDrawToDealer();
        PlayerInfoDto dealerInfo = PlayerInfoDto.from(blackJackGame.getDealerInfo());
        outputView.printDealerFirstOpen(dealerInfo);
        initialDrawPlayers();
    }

    private void initialDrawPlayers() {
        List<Player> players = blackJackGame.getPlayers();
        for (Player player : players) {
            blackJackGame.initialDrawToPlayer(player);
            Participant playerInfo = player.getInfo();
            PlayerInfoDto playerInfoDto = PlayerInfoDto.from(playerInfo);
            outputView.printPlayerInfo(playerInfoDto);
        }
    }

    private void drawAdditionalCardToPlayers() {
        List<Player> players = blackJackGame.getPlayers();
        for (Player player : players) {
            drawAdditionalCardToPlayer(player);
        }
    }

    private void drawAdditionalCardToPlayer(Player player) {
        Command command = Command.Y;
        while (player.isDrawable() && command != Command.N) {
            command = decideDrawCard(player);
            if (!command.isYes()) {
                player.stay();
            }
        }
    }

    private Command decideDrawCard(Player player) {
        Command command;
        String commandInput = inputView.readAdditionalDrawCommand(player.getName());
        command = Command.find(commandInput);
        if (command.isYes()) {
            blackJackGame.drawToPlayer(player);
            PlayerInfoDto playerInfoDto = PlayerInfoDto.from(player.getInfo());
            outputView.printPlayerInfo(playerInfoDto);
        }
        return command;
    }

    private void drawAdditionalCardToDealer() {
        while (blackJackGame.isDealerUnderScore()) {
            outputView.printDealerDrawAdditionalCardMessage();
            blackJackGame.drawToDealer();
            Participant dealerInfo = blackJackGame.getDealerInfo();
            PlayerInfoDto dealerInfoDto = PlayerInfoDto.from(dealerInfo);
            outputView.printPlayerInfo(dealerInfoDto);
        }
        if (blackJackGame.isDealerDrawable()) {
            blackJackGame.stayDealer();
        }
    }

    private void printCardResults() {
        Participant dealerInfo = blackJackGame.getDealerInfo();
        ScoreInfoDto dealerScoreInfo = ScoreInfoDto.from(dealerInfo);
        List<Player> players = blackJackGame.getPlayers();
        List<ScoreInfoDto> playerDtos = getPlayersInfo(players);
        outputView.printCardResults(dealerScoreInfo, playerDtos);
    }

    private List<ScoreInfoDto> getPlayersInfo(List<Player> players) {
        return players.stream()
                .map(Player::getInfo)
                .map(ScoreInfoDto::from)
                .collect(Collectors.toUnmodifiableList());
    }

    private void printBettingResults() {
        Map<Player, Money> bettingResultTable = blackJackGame.calculateBettingResult();
        List<BettingResultDto> bettingResults = getBettingResults(bettingResultTable);
        outputView.printBettingResults(bettingResults);
    }

    private List<BettingResultDto> getBettingResults(Map<Player, Money> bettingResultTable) {
        return bettingResultTable.keySet()
                .stream()
                .map(player -> BettingResultDto.of(player.getName(), bettingResultTable.get(player)))
                .collect(Collectors.toUnmodifiableList());
    }
}
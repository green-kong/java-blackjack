package blackjack.controller;

import java.util.List;

import blackjack.domain.BlackJackGame;
import blackjack.domain.deck.shuffler.RandomShuffler;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Player;
import blackjack.dto.PlayerInfoDto;
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
        while (blackJackGame.isDealerDrawable()) {
            outputView.printDealerDrawAdditionalCardMessage();
            blackJackGame.drawToDealer();
            Participant dealerInfo = blackJackGame.getDealerInfo();
            PlayerInfoDto dealerInfoDto = PlayerInfoDto.from(dealerInfo);
            outputView.printPlayerInfo(dealerInfoDto);
        }
    }
}

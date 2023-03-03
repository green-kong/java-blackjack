import java.util.List;

import blackjackGame.BlackjackGame;
import player.DealerFirstOpenDto;
import player.DealerWinningDto;
import player.Name;
import player.Player;
import player.PlayerOpenDto;
import player.PlayerResultDto;
import player.PlayerWinningDto;
import view.InputView;
import view.OutputView;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final BlackjackGame blackjackGame;

    public Controller(InputView inputView, OutputView outputView, BlackjackGame blackjackGame) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.blackjackGame = blackjackGame;
    }

    void run() {
        setGame();
        showFirstDraw();
        playersHit();
        dealerHit();
        printFinalCards();
        printWinningResult();
    }

    private void printWinningResult() {
        blackjackGame.calculateWinning();
        DealerWinningDto dealerWinningResult = blackjackGame.getDealerWinningResult();
        List<PlayerWinningDto> playerWinningResults = blackjackGame.getPlayerWinningResults();
        outputView.printWinningResults(dealerWinningResult, playerWinningResults);
    }

    private void printFinalCards() {
        PlayerResultDto dealerResult = blackjackGame.getDealerResult();
        List<PlayerResultDto> playerResults = blackjackGame.getPlayerResults();

        outputView.printFinalResults(dealerResult, playerResults);
    }

    private void dealerHit() {
        while (blackjackGame.canDealerHit()) {
            blackjackGame.supplyAdditionalCardToDealer();
            outputView.printDealerHitMessage();
        }
    }

    private void playersHit() {
        for (int i = 0; i < blackjackGame.countPlayer(); i++) {
            Name userName = blackjackGame.findUserNameByIndex(i);
            String hitCommand = inputView.readHitCommand(userName);
            playerHit(i, userName, hitCommand);
        }
    }

    private void playerHit(int i, Name userName, String hitCommand) {
        while (hitCommand.equals("y") && !blackjackGame.isBust(i)) {
            blackjackGame.supplyAdditionalCard(i);
            PlayerOpenDto playerCard = blackjackGame.getPlayerCardsByIndex(i);
            outputView.printPlayerCard(playerCard);
            if (blackjackGame.isBust(i)) {
                break;
            }
            hitCommand = inputView.readHitCommand(userName);
        }
    }

    private void showFirstDraw() {
        DealerFirstOpenDto dealerFirstOpen = blackjackGame.getDealerFirstOpen();
        List<PlayerOpenDto> playersCards = blackjackGame.getPlayersCards();
        outputView.printFirstOpenCards(dealerFirstOpen, playersCards);
    }

    private void setGame() {
        List<String> names = inputView.readPlayerNames();
        for (String name : names) {
            blackjackGame.addPlayer(new Player(new Name(name)));
        }
        blackjackGame.supplyCardsToDealer();
        blackjackGame.supplyCardsToPlayers();
        outputView.printFirstDrawMessage(names);
    }
}
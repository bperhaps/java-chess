package chess;

import chess.console.ConsoleChessController;

public class Application {

    public static void main(String[] args) {
        ConsoleChessController chessController = new ConsoleChessController();
        chessController.run();
    }

}

package chess;

import chess.controller.ChessController;
import chess.domain.board.Board;
import chess.domain.command.*;
import chess.domain.game.ChessGame;
import chess.domain.piece.PieceFactory;

import java.util.Arrays;

public class Application {

    public static void main(String[] args) {
        Board board = new Board(PieceFactory.createPieces());
        ChessGame chessGame = new ChessGame(board);

        Commands commands = new Commands(
                Arrays.asList(
                        new StartCommand(chessGame),
                        new MoveCommand(chessGame),
                        new EndCommand(chessGame),
                        new StatusCommand(chessGame))
        );

        ChessController chessController = new ChessController(board, chessGame, commands);
        chessController.run();
    }

}

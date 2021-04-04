package chess.service;

import chess.controller.dto.MessageDto;
import chess.controller.dto.StatusDto;
import chess.domain.game.ChessGame;
import chess.repository.GameRepository;

public class StatusService {

    public static Object getStatus(String gameId) {
        ChessGame chessGame = null;
        try {
            chessGame = GameRepository.findByGameIdFromCache(gameId);
        } catch (RuntimeException e) {
            return new MessageDto(e.getMessage());
        }

        double whiteScore = chessGame.getWhiteScore();
        double blackScore = chessGame.getBlackScore();

        return new StatusDto(whiteScore, blackScore);
    }

}

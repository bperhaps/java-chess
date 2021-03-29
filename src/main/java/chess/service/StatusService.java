package chess.service;

import chess.controller.dto.MessageDto;
import chess.controller.dto.StatusDto;
import chess.domain.game.ChessGame;
import chess.repository.GameRepository;

public class StatusService {

    private final String gameId;

    public StatusService(String gameId) {
        this.gameId = gameId;
    }

    public StatusDto getStatus() {
        ChessGame chessGame = getChessGameByGameId(gameId);

        double whiteScore = chessGame.getWhiteScore();
        double blackScore = chessGame.getBlackScore();

        return new StatusDto(whiteScore, blackScore);
    }

    private ChessGame getChessGameByGameId(String gameId) {
        validateChessGameIdExist(gameId);

        return GameRepository.findByGameId(gameId).get();
    }

    private void validateChessGameIdExist(String gameId) {
        if (!GameRepository.findByGameId(gameId).isPresent()) {
            throw new IllegalArgumentException("게임 ID가 존재하지 않습니다.");
        }
    }

}

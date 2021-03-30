package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.Position;

import java.util.stream.IntStream;

public class KingMoveCondition extends MoveCondition {
    private static final int COUNT_OF_MOVABLE_PATH = 8;
    private static final int[] MOVABLE_ROW = {1, -1, 0, 0, -1, -1, 1, 1};
    private static final int[] MOVABLE_COLUMN = {0, 0, 1, -1, 1, -1, 1, -1};

    @Override
    public boolean isSatisfiedBy(final Board board, final ChessPiece piece, final Position target) {
        return !piece.isSamePosition(target) &&
                isRightPath(piece, target) &&
                isNotExistSameColorPieceOnPath(board, piece, target) &&
                isNotTheChessPieceGoOffTheBoard(target);
    }

    private boolean isRightPath(final ChessPiece piece, final Position target) {
        return IntStream.range(0, COUNT_OF_MOVABLE_PATH)
                .mapToObj(index -> new Position(
                        piece.getRow() + MOVABLE_ROW[index],
                        piece.getColumn() + MOVABLE_COLUMN[index])
                ).anyMatch(position -> position.equals(target));
    }

    public boolean isNotExistSameColorPieceOnPath(Board board, ChessPiece piece, Position target) {
        return board.getAllPieces().stream()
                .noneMatch(
                        pieceOnBoard -> pieceOnBoard.isSamePosition(target) &&
                                piece.isSameColor(pieceOnBoard)
                );
    }

}

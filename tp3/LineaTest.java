package linea;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class LineaTest {

    @Test
    void test01ShowEmptyBoardZeroPieces() {
        Linea linea = new Linea(0, 0, 'A');
        assertEquals(linea.show(), "");
    }

    @Test
    void test02EmptyBoardZeroPiecesDraw() {
        Linea linea = new Linea(0, 0, 'A');
        assertFalse(linea.isFinished());
    }

    @Test
    void test03ShowEmptyBoard5x5() {
        Linea linea = new Linea(5, 5, 'A');
        assertEquals(linea.show(), """
                | | | | | |
                | | | | | |
                | | | | | |
                | | | | | |
                | | | | | |
                """);
    }

    @Test
    void test04EmptyBoard5x5DrawStyleA() {
        Linea linea = new Linea(5, 5, 'A');
        assertFalse(linea.isFinished());
    }

    @Test
    void test05EmptyBoard5x5DrawStyleB() {
        Linea linea = new Linea(5, 5, 'B');
        assertFalse(linea.isFinished());
    }

    @Test
    void test06EmptyBoard5x5DrawStyleC() {
        Linea linea = new Linea(5, 5, 'C');
        assertFalse(linea.isFinished());
    }

    @Test
    void test07RedWinsHorizontallyStyleA() {
        Linea linea = new Linea(5, 5, 'A');
        redWinsHorizontally(linea);
        assertTrue(linea.isFinished());
    }

    @Test
    void test08RedWinsVerticallyStyleA() {
        Linea linea = new Linea(5, 5, 'A');
        redWinsVertically(linea);
        assertTrue(linea.isFinished());
    }

    @Test
    void test09BlueWinsHorizontallyStyleA() {
        Linea linea = new Linea(5, 5, 'A');
        blueWinsHorizontally(linea);
        assertTrue(linea.isFinished());
    }

    @Test
    void test10BlueWinsVerticallyStyleA() {
        Linea linea = new Linea(5, 5, 'A');
        blueWinsVertically(linea);
        assertTrue(linea.isFinished());
    }

    @Test
    void test11RedWinsDiagonallyStyleBLeftToRight() {
        Linea linea = new Linea(5, 5, 'B');
        redWinsDiagonallyLtoR(linea);
        assertTrue(linea.isFinished());
    }

    @Test
    void test12RedWinsDiagonallyStyleBRightToLeft() {
        Linea linea = new Linea(5, 5, 'B');
        redWinsDiagonallyRtoL(linea);
        assertTrue(linea.isFinished());
    }

    @Test
    void test13BlueWinsDiagonallyStyleBLeftToRight() {
        Linea linea = new Linea(5, 5, 'B');
        blueWinsDiagonallyLtoR(linea);
        assertTrue(linea.isFinished());
    }

    @Test
    void test14RedWinsHorizontallyStyleC() {
        Linea linea = new Linea(5, 5, 'C');
        redWinsHorizontally(linea);
        assertTrue(linea.isFinished());
    }

    @Test
    void test15RedWinsVerticallyStyleC() {
        Linea linea = new Linea(5, 5, 'C');
        redWinsVertically(linea);
        assertTrue(linea.isFinished());
    }

    @Test
    void test16BlueWinsHorizontallyStyleC() {
        Linea linea = new Linea(5, 5, 'C');
        blueWinsHorizontally(linea);
        assertTrue(linea.isFinished());
    }

    @Test
    void test17BlueWinsVerticallyStyleC() {
        Linea linea = new Linea(5, 5, 'C');
        blueWinsVertically(linea);
        assertTrue(linea.isFinished());
    }

    @Test
    void test18RedWinsDiagonallyStyleCLeftToRight() {
        Linea linea = new Linea(5, 5, 'C');
        redWinsDiagonallyLtoR(linea);
        assertTrue(linea.isFinished());
    }

    @Test
    void test19RedWinsDiagonallyStyleCRightToLeft() {
        Linea linea = new Linea(5, 5, 'C');
        redWinsDiagonallyRtoL(linea);
        assertTrue(linea.isFinished());
    }

    @Test
    void test20BlueWinsDiagonallyStyleCLeftToRight() {
        Linea linea = new Linea(5, 5, 'C');
        blueWinsDiagonallyLtoR(linea);
        assertTrue(linea.isFinished());
    }

    @Test
    void test21CannotPlaceMorePiecesInColumnError() {
        Linea linea = new Linea(5, 5, 'A');
        linea.playRedAt(0);
        linea.playBlueAt(0);
        linea.playRedAt(0);
        linea.playBlueAt(0);
        linea.playRedAt(0);
        assertThrowsLike(() -> linea.playBlueAt(0), Linea.IndexOutOfBounds);
    }

    @Test
    void test22RedTriesToPlacePieceInBlueTurnError() {
        Linea linea = new Linea(5, 5, 'A');
        linea.playRedAt(0);
        assertThrowsLike(() -> linea.playRedAt(0), TurnManager.InvalidTurn);
    }

    @Test
    void test23BlueTriesToPlacePieceInRedTurnError() {
        Linea linea = new Linea(5, 5, 'A');
        assertThrowsLike(() -> linea.playBlueAt(1), TurnManager.InvalidTurn);
    }

    @Test
    void test24CannotPlacePiecesAfterWinnerError() {
        Linea linea = new Linea(5, 5, 'A');
        redWinsHorizontally(linea);
        assertTrue(linea.isFinished());
        assertThrowsLike(() -> linea.playRedAt(2), NothingTurn.gameIsOver );
    }

    @Test
    void test25CannotPlacePiecesAfterBoardFullError() {
        Linea linea = new Linea(1, 1, 'A');
        linea.playRedAt(0);
        assertTrue(linea.isFinished());
        assertThrowsLike(() -> linea.playBlueAt(0), Linea.IndexOutOfBounds);
    }

    @Test
    void test26InvalidStyleError() {
        assertThrowsLike(() -> new Linea(5, 5, 'X'), ModeManager.invalidMode);
    }

    public void assertThrowsLike(Executable lambda, String error) {
        assertEquals(error, assertThrows(Exception.class, lambda).getMessage());
    }

    private Linea NuevaLinea() {
        Linea linea = new Linea(5,5, 'C');
        return linea;
    }

    private void redWinsHorizontally(Linea linea) {
        linea.playRedAt(0);
        linea.playBlueAt(0);
        linea.playRedAt(1);
        linea.playBlueAt(1);
        linea.playRedAt(2);
        linea.playBlueAt(2);
        linea.playRedAt(3);
    }

    private static void redWinsVertically(Linea linea) {
        linea.playRedAt(0);
        linea.playBlueAt(1);
        linea.playRedAt(0);
        linea.playBlueAt(1);
        linea.playRedAt(0);
        linea.playBlueAt(1);
        linea.playRedAt(0);
    }


    private static void blueWinsVertically(Linea linea) {
        linea.playRedAt(1);
        linea.playBlueAt(0);
        linea.playRedAt(1);
        linea.playBlueAt(0);
        linea.playRedAt(1);
        linea.playBlueAt(0);
        linea.playRedAt(3);
        linea.playBlueAt(0);
    }

    private void blueWinsHorizontally(Linea linea) {
        linea.playRedAt(0);
        linea.playBlueAt(0);
        linea.playRedAt(1);
        linea.playBlueAt(1);
        linea.playRedAt(2);
        linea.playBlueAt(2);
        linea.playRedAt(0);
        linea.playBlueAt(3);
        linea.playRedAt(2);
        linea.playBlueAt(3);
    }

    private void redWinsDiagonallyLtoR(Linea linea) {
        linea.playRedAt(0);
        linea.playBlueAt(1);
        linea.playRedAt(1);
        linea.playBlueAt(2);
        linea.playRedAt(3);
        linea.playBlueAt(2);
        linea.playRedAt(2);
        linea.playBlueAt(3);
        linea.playRedAt(3);
        linea.playBlueAt(4);
        linea.playRedAt(3);
    }

    private void redWinsDiagonallyRtoL(Linea linea) {
        linea.playRedAt(3);
        linea.playBlueAt(2);
        linea.playRedAt(2);
        linea.playBlueAt(1);
        linea.playRedAt(0);
        linea.playBlueAt(1);
        linea.playRedAt(1);
        linea.playBlueAt(0);
        linea.playRedAt(0);
        linea.playBlueAt(4);
        linea.playRedAt(0);
    }

    private void blueWinsDiagonallyLtoR(Linea linea) {
        linea.playRedAt(3);
        linea.playBlueAt(0);
        linea.playRedAt(1);
        linea.playBlueAt(1);
        linea.playRedAt(2);
        linea.playBlueAt(3);
        linea.playRedAt(1);
        linea.playBlueAt(2);
        linea.playRedAt(3);
        linea.playBlueAt(2);
        linea.playRedAt(2);
        linea.playBlueAt(3);
    }
}
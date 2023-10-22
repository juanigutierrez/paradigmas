package DoD;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Tests {
    private Class north;
    private Class south;
    private Class east;
    private Class west;
    private Direction originalDirection = new North();
    private Coordinates originalCoordinates = new Coordinates(0, 0);
    private Direction alternativeDirection = new East();
    private Coordinates alternativeCoordinates = new Coordinates(1,1);

    @BeforeEach public void setUp() {
        north = North.class;
        south = South.class;
        east = East.class;
        west = West.class;
    }

    @Test public void test01NemoOriginalStateIsCorrect() {
        check_conditions(NemoOriginalState(), 0, north, 0, 0);
    }
    @Test public void test02NoCommandDoesNotAffectNemo() {
        check_conditions(NemoOriginalState().readofcommand(""), 0, north, 0, 0);
    }
    @Test public void test03NemoAlternativeStateIsCorrect(){
        check_conditions(NemoAlternativeState(),0, east,1, 1);
    }
    @Test public void test04NemoDepthChangesDownward() {
        check_conditions(NemoOriginalState().readofcommand("d"), 1, north, 0, 0);
    }
    @Test public void test05NemoDoesNotMoveUpwardWhenOnTop() {
        check_conditions(NemoOriginalState().readofcommand("u"), 0, north, 0, 0);
    }
    @Test public void test06NemoTurnsRight() {
        check_conditions(NemoOriginalState().readofcommand("r"),  0, east, 0, 0);
    }
    @Test public void test07NemoTurnsLeft() {
        check_conditions(NemoOriginalState().readofcommand("l"),  0, west, 0, 0);
    }
    @Test public void test08AlternativeNemoTurnsCorrectly() {
        check_conditions(NemoAlternativeState().readofcommand("rrl"),  0, south, 1, 1);
    }
    @Test public void test09NemoMovesNorth() {
        check_conditions(NemoOriginalState().readofcommand("f"),  0, north, 0, 1);
    }
    @Test public void test10NemoDepthChangesUpward() {
        check_conditions(NemoOriginalState().readofcommand("du"),  0, north, 0, 0);
    }
    @Test public void test11NemoMovesEast() {
        check_conditions(NemoOriginalState().readofcommand("rf"), 0, east, 1, 0);
    }
    @Test public void test12NemoMovesWest() {
        check_conditions(NemoOriginalState().readofcommand("lf"),  0, west, -1, 0);
    }
    @Test public void test13NemoMovesSouth() {
        check_conditions(NemoOriginalState().readofcommand("llf"),  0, south, 0, -1);
    }
    @Test public void test14AlternativeMovesCorrectly() {
        check_conditions(NemoAlternativeState().readofcommand("rrlf"),  0, south, 1, 0);
    }
    @Test public void test15NemoTurnsInCircle() {
        check_conditions(NemoOriginalState().readofcommand("rrrr"), 0, north, 0, 0);
    }
    @Test public void test16NemoDoesNotMoveWhenCapsuleReleases() {
        check_conditions(NemoOriginalState().readofcommand("m"), 0, north, 0, 0);
    }
    @Test public void test17CapsuleReleaseDepthError() {
        assertThrowsLike(() -> NemoOriginalState().readofcommand("ddm"), DepthBellowOne.ERROR_NEMO_EXPLODED);
    }
    @Test public void test18CapsuleReleaseMoreThanOnceDoesNotAffect() {
        check_conditions(NemoOriginalState().readofcommand("mm"), 0, north, 0, 0);
    }
    @Test public void test19NemoDoesNotChangeAfterExplosion() {
        Nemo nemo = NemoOriginalState();
        assertThrowsLike( () -> nemo.readofcommand("ddmrluufd"), DepthBellowOne.ERROR_NEMO_EXPLODED);
        check_conditions(nemo, 2, north, 0, 0);
    }
    @Test public void test20NemoDoesNotMoveWhenDirectionChanges() {
        check_conditions(NemoOriginalState().readofcommand("rrlrrl"), 0, south, 0, 0);
    }
    public void assertThrowsLike(Executable lambda, String error) {
        assertEquals(error, assertThrows(Exception.class, lambda).getMessage());
    }
    private void check_conditions(Nemo nemo,  Integer profundidad, Class direction, int... coordenadas) {
        assertArrayEquals(nemo.getNemoCoordinates().getCoordinatesArray(), coordenadas);
        assertEquals(nemo.getNemoDepth(), profundidad);
        assertEquals(nemo.getNemoDirection().getClass(), direction);
    }
    private Nemo NemoOriginalState() {
        return new Nemo(originalCoordinates, originalDirection);
    }
    private Nemo NemoAlternativeState() {
        return new Nemo(alternativeCoordinates, alternativeDirection);
    }
}
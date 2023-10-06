package DoD;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Tests {
    @Test public void test0() {
        check_conditions(new Nemo(),0,0,0,"n",false);
    }

    @Test public void test1() {
        check_conditions(new Nemo().readofcomand(""),0,0,0,"n",false);
    }

    @Test public void test2() {
        check_conditions(new Nemo().readofcomand("dd"),0,0,2,"n",false);
    }

    @Test public void test3() {
        check_conditions(new Nemo().readofcomand("u"),0,0,0,"n",false);
    }

    @Test public void test4() {
        check_conditions(new Nemo().readofcomand("r"),0,0,0,"o",false);
    }

    @Test public void test5() {
        check_conditions(new Nemo().readofcomand("l"),0,0,0,"e",false);
    }

    @Test public void test6() {
        check_conditions(new Nemo().readofcomand("f"),0,1,0,"n",false);
    }

    @Test public void test7() {
        check_conditions(new Nemo().readofcomand("m"),0,0,0,"n",true);
    }

    @Test public void test8() {
        assertThrowsLike(()->new Nemo().readofcomand("ddddm"), "el sumergible estallo");
    }
    public void assertThrowsLike(Executable lambda, String error){
        assertEquals(error,assertThrows(Exception.class,lambda).getMessage());
    }

    private Void check_conditions(Nemo Sumerguible, Integer coordenateX, Integer coordenateY, Integer profundidad, String direction, Boolean capsule) {
        assertEquals( Sumerguible.getcoordenateX(),coordenateX);
        assertEquals( Sumerguible.getcoordenateY(),coordenateY);
        assertEquals( Sumerguible.getNemoDepth(),profundidad);
        assertEquals( Sumerguible.getNemoDirection(),direction );
        assertEquals( Sumerguible.getNemoCapsule(),capsule);
        return null;
    }
}

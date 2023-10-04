import org.junit.jupiter    .api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Tests {
    @Test public void test0() {
        check_conditions(new sumerguible(),0,0,0,"n",false);
    }

    @Test public void test1() {
        check_conditions(new sumerguible().readofcomand(""),0,0,0,"n",false);
    }

    @Test public void test2() {
        check_conditions(new sumerguible().readofcomand("d"),0,0,1,"n",false);
    }
    @Test public void test3() {
        check_conditions(new sumerguible().readofcomand("u"),0,0,0,"n",false);
    }

    @Test public void test4() {
        check_conditions(new sumerguible().readofcomand("r"),0,0,0,"o",false);
    }

    @Test public void test5() {
        check_conditions(new sumerguible().readofcomand("l"),0,0,0,"e",false);
    }

    @Test public void test6() {
        check_conditions(new sumerguible().readofcomand("f"),0,1,0,"n",false);
    }

    @Test public void test7() {
        check_conditions(new sumerguible().readofcomand("m"),0,0,0,"n",true);
    }

    @Test public void test8() {
        check_conditions(new sumerguible().readofcomand("du"),0,0,0,"n",false);
    }

    //@Test public void test9() {
      //  assertThrowsLike(()->new sumerguible().readofcomand("ddddm"), "el sumergible estallo");
        //}
    public void assertThrowsLike(Executable lambda, String error){
        assertEquals(error,assertThrows(Exception.class,lambda).getMessage());
    }
    private Void check_conditions(sumerguible sumerguible, Integer coordenateX, Integer coordenateY, Integer profundidad, String direccion, Boolean capsulaLiberada) {
        assertEquals( sumerguible.getcoordenateX(),coordenateX);
        assertEquals( sumerguible.getcoordenateY(),coordenateY);
        assertEquals( sumerguible.getprofundidad(),profundidad);
        assertEquals( sumerguible.getdireccion(),direccion);
        assertEquals( sumerguible.getcapsulaLiberada(),capsulaLiberada);
        return null;
    }
}

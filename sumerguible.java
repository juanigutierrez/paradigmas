import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class sumerguible {
    private int coordenateX;
    private int coordenateY;
    private int profundidad;
    private  List<String> cardinals_points = new ArrayList<String>(List.of("n","e","s","o"));
    private int direccion;
    private boolean capsulaLiberada;

    public sumerguible(){
        this.coordenateX = 0;
        this.coordenateY = 0;
        this.profundidad = 0;
        this.direccion = 0;
        this.capsulaLiberada = false;
    }
    public sumerguible readofcomand(String command){
        String[] commands = command.split("");
        Arrays.stream(commands).forEach(comando -> doo(comando));
        return this;
    }
    public Void doo(String command) {
        if (command == "") {
            return null;
        }
        if (command == "d") {
            profundidad += 1;
            System.out.println(profundidad);
            System.out.println("pase por aca");
        }
        if (command == "u" && profundidad > 0) {
            profundidad -= 1;
        }
        if (command == "r") {
            direccion = (direccion - 1 + 4) % 4;
        }
        if (command == "l") {
            direccion = (direccion + 1) % 4;
        }
        if (command == "f") {
            if (direccion == 0) {
                coordenateY += 1;
            }
            if (direccion == 1) {
                coordenateX -= 1;
            }
            if (direccion == 2) {
                coordenateY -= 1;
            }
            if (direccion == 3) {
                coordenateX += 1;
            }
        }
        if (command == "m"){
            if (profundidad <= 1){
                capsulaLiberada = true;
            }
            else{
                throw new RuntimeException("el sumergible estallo");

            }

        }
        return null;
    }

    public Integer getcoordenateX(){
        return coordenateX;
    }

    public Integer getcoordenateY(){
        return coordenateY;
    }

    public int getprofundidad(){
        return profundidad;
    }

    public String getdireccion() {
        return cardinals_points.get(direccion);
    }

    public boolean getcapsulaLiberada(){
        return capsulaLiberada;
    }

}
//el polimorfismo va a estar en como implementen do cada comando
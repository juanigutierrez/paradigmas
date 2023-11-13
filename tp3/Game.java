package linea;

/*El espacio de juego se define al iniciar, junto con la variante de triunfo.
La variante de triunfo puede ser,
- 'A' solo 4 en línea verticales u horizontales.
- 'B' solo 4 en línea diagonales
- 'C' 4 en línea en cualquier orientación.
Inician el juego las Rojas y alternan los turnos a partir de ahí
El juego puede terminar por triunfo o por empate. Una vez terminado no se puede seguir colocando fichas
*/
public class Game {
    public static void main( String[] args) throws Exception {
        System.out.println( "Dimensiones?");
        Linea game = new Linea( prompt( "Base? " ), prompt( "Altura? " ), 'C' );

        System.out.println( game.show() );

        while ( !game.isFinished() ) {
            game.playRedAt( prompt( "Negras? " ) );
            System.out.println( game.show() );

            if ( !game.isFinished() ) {
                game.playBlueAt( prompt( "Blancas? " ) );
                System.out.println( game.show() );
            }
        }
    }

    private static int prompt( String message ) {
        System.out.print( message );
        return Integer.parseInt( System.console().readLine() );
    }
}

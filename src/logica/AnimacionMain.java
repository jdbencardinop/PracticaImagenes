
package logica;

import java.util.Random;

/**
 *
 * @author Bencardino Perdomo
 */
public class AnimacionMain extends Animacion{
    
    private final java.util.Random forma;
    //private final int opcion;

    public AnimacionMain() {
        this.DefaultPantalla();
        this.forma = new Random();
       //this.opcion = forma.nextInt(6);
    }
    
    public static void main(String[] args) {
        Animacion game = new AnimacionMain();
        game.iniciar();
    }
    
    @Override
    public void actualizacion() {
        
//        this.contador++;
//        this.getGrafo().setxOffset(this.getContador());
//        this.getGrafo().setyOffset(this.getContador());
        
//        switch(0){
//            
//            case 2:
//                for (int i = 0; i < this.pixls.length; i++) {
//                    this.pixls[i] = (i * this.contador)%(i+1);
//                }
//                break;
//            case 3:
//                for (int i = 0; i < this.pixls.length; i++) {
//                    this.pixls[i] = (i * this.contador);
//                }
//                break;
//            case 4:
//                for (int i = 0; i < this.pixls.length; i++) {
//                    this.pixls[i] = i * 100 / this.contador;
//                }
//                break;
//            case 5:
//                for (int i = 0; i < this.pixls.length; i++) {
//                    this.pixls[i] = this.contador - i + 1000;
//                }
//                break;
//            case 1:
//                super.actualizacion();
//                break;
//            default:                
//                break;
//                
//        }
    }       
}

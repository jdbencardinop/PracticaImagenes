
package Gráfico;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Bencardino Perdomo
 */
public class Graficos {
    protected String direccion;
    protected int ancho;
    protected int alto;
    
    protected int[] pixls;

    public Graficos(String direccion) {
        this.direccion = direccion;
        BufferedImage imagen = null;
        
        try {
            imagen = ImageIO.read(Graficos.class.getResource(this.direccion));
        } catch (IOException ex) {
            Logger.getLogger(Graficos.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        if(imagen == null){
            //return;
        } else {
        
        this.ancho = imagen.getWidth();
        this.alto = imagen.getHeight();
        
        this.pixls = imagen.getRGB(0, 0, ancho, alto, this.pixls, 0, ancho);
        
            for (int i = 0; i < this.pixls.length; i++) {
                this.pixls[i] = (this.pixls[i] & 0xff)/64;
            }
            
            for (int i = 0; i < 8; i++) {
                System.out.println(this.pixls[i]);
            }
        }
    } 

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int[] getPixls() {
        return pixls;
    }

    public void setPixls(int[] pixls) {
        this.pixls = pixls;
    }    
}

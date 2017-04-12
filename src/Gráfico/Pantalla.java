
package Gráfico;

/**
 *
 * @author Bencardino Perdomo
 */
public class Pantalla {
    
    protected final int ANCHO_MAPA;
    protected final int ANCHO_MAPA_COVER;
    
    protected int[] cuadro;
    protected int[] colores;
    
    protected int xOffset;
    protected int yOffset;
    
    protected int ancho;
    protected int alto;
    
    protected Graficos grafo;

    public Pantalla(int ancho, int alto, Graficos grafo) {
        this.ANCHO_MAPA = 64;
        this.ANCHO_MAPA_COVER = this.ANCHO_MAPA - 1;
        this.cuadro = new int[this.ANCHO_MAPA*this.ANCHO_MAPA];
        this.colores = new int[this.ANCHO_MAPA*this.ANCHO_MAPA*4];
        this.xOffset = 0;
        this.yOffset = 0;
        this.ancho = ancho;
        this.alto = alto;
        this.grafo = grafo;
        
        for (int i = 0; i < (this.ANCHO_MAPA*this.ANCHO_MAPA); i++) {
            this.colores[i*4+0] = 0xff00ff;
            this.colores[i*4+1] = 0x00ffff;
            this.colores[i*4+2] = 0xffff00;
            this.colores[i*4+3] = 0xffffff;
        }
    }

    public void cuadro (int[] pixels, int offset, int row) {
        for (int yTile = 0; yTile < 10; yTile++) {
            
        }
    }

    public int[] getCuadro() {
        return cuadro;
    }

    public void setCuadro(int[] cuadro) {
        this.cuadro = cuadro;
    }

    public int[] getColores() {
        return colores;
    }

    public void setColores(int[] colores) {
        this.colores = colores;
    }

    public int getxOffset() {
        return xOffset;
    }

    public void setxOffset(int xOffset) {
        this.xOffset = xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    public void setyOffset(int yOffset) {
        this.yOffset = yOffset;
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

    public Graficos getGrafo() {
        return grafo;
    }

    public void setGrafo(Graficos grafo) {
        this.grafo = grafo;
    }

}


package logica;

import Gráfico.Graficos;
import Gráfico.Pantalla;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;

/**
 *
 * @author Bencardino Perdomo
 */
public abstract class Animacion extends Canvas implements Runnable{
    
    protected final int ANCHO;
    protected final int ALTO;
    protected final int ESCALA;
    
    protected String nombre;
    protected JFrame ventana;
    protected boolean running;
    
    protected BufferedImage image;
    protected int[] pixls;
    
    protected int contador;//se quitará después
    
    protected Pantalla grafo;

    public Animacion() {
        
        this.ANCHO = 160;
        this.ALTO = (this.ANCHO)/12*9;
        this.ESCALA = 3;
        this.nombre = "Tanques";
        this.ventana = new JFrame(nombre);
        this.running = false;
        
        this.setMinimumSize  (new Dimension(ANCHO*ESCALA,ALTO*ESCALA));
        this.setMaximumSize  (new Dimension(ANCHO*ESCALA,ALTO*ESCALA));
        this.setPreferredSize(new Dimension(ANCHO*ESCALA,ALTO*ESCALA));  
        
        this.image= new BufferedImage(ANCHO,ALTO,BufferedImage.TYPE_INT_RGB);
        this.pixls=((DataBufferInt)image.getRaster().getDataBuffer()).getData();
        
        this.contador = 0;
    }
    
    protected final void DefaultPantalla() {
        this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.ventana.setLayout(new BorderLayout());
        
        this.ventana.add(this,BorderLayout.CENTER);
        this.ventana.pack();
        
        this.ventana.setResizable(false);
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setVisible(true);
    }
    
    public final void iniPantalla() {
        grafo = new Pantalla(ANCHO,ALTO,new Graficos("/PruebaDosColores.png"));
    }
    
    public synchronized void iniciar() {
        this.running = true;
        new Thread(this).start();
    }
    
    public synchronized void parar() {
        this.running = false;
    }
    
    @Override
    public void run() {
        long tiempo = System.nanoTime();
        final double ranPorA = Math.pow(10, 9)/60D;
        
        int actualizaciones = 0;
        int cuadros = 0;
        
        long temporizador = System.currentTimeMillis();
        double tiempoTrans = 0;
        
        this.iniPantalla();
        
        while(this.running){            
            long ahora = System.nanoTime();
            tiempoTrans += (ahora - tiempo)/ranPorA;
            tiempo = ahora;
            
            if (tiempoTrans >= 1){
                actualizaciones++;
                this.actualizacion();
                tiempoTrans--;
            }
            
            cuadros++;
            this.cuadro();            
            
            if (System.currentTimeMillis()- temporizador >= 1000) {
                temporizador += 1000;
                System.out.println(actualizaciones+" ups, "+cuadros+" fps");
                actualizaciones = 0;
                cuadros = 0;
            }                        
        }
    }

    protected void cuadro() {//render method
        BufferStrategy bs = getBufferStrategy();
        
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        
        this.grafo.cuadro(this.pixls, 0, this.ANCHO);
        
        Graphics graph = bs.getDrawGraphics();
        
//        graph.setColor(Color.CYAN);
//        graph.fillRect(0, 0, getWidth(), getHeight());

        graph.drawImage(image, 0, 0, getWidth(),getHeight(), null);
        
        graph.dispose();
        bs.show();        
    }

    protected void actualizacion() {
               
//        for (int i = 0; i < this.pixls.length; i++) {
//            this.pixls[i] = i + this.contador;
//        }
        
    }        

    public int getANCHO() {
        return ANCHO;
    }

    public int getALTO() {
        return ALTO;
    }

    public int getESCALA() {
        return ESCALA;
    }

    public String getNombre() {
        return nombre;
    }

    public JFrame getVentana() {
        return ventana;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setVentana(JFrame ventana) {
        this.ventana = ventana;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int[] getPixls() {
        return pixls;
    }

    public void setPixls(int[] pixls) {
        this.pixls = pixls;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public Pantalla getGrafo() {
        return grafo;
    }

    public void setGrafo(Pantalla grafo) {
        this.grafo = grafo;
    }
    
}

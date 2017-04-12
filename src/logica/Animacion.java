
package logica;

import Gráfico.Graficos;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    protected Graficos grafo;

    public Animacion() {
        this.grafo = new Graficos("/PruebaDosColores.png");
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
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new BorderLayout());
        
        ventana.add(this,BorderLayout.CENTER);
        ventana.pack();
        
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
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
        
        while(this.running){            
            long ahora = System.nanoTime();
            tiempoTrans += (ahora - tiempo)/ranPorA;
            tiempo = ahora;
            
            if (tiempoTrans >= 1){
                actualizaciones++;
                this.actualizacion();
                tiempoTrans--;
            }
            
            try {
                Thread.sleep(2);
            } catch (InterruptedException ex) {
                Logger.getLogger(Animacion.class.getName()).log(Level.SEVERE,null,ex);
            } finally {
                cuadros++;
                this.cuadro();
            }
            
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
        
        Graphics graph = bs.getDrawGraphics();
        
//        graph.setColor(Color.CYAN);
//        graph.fillRect(0, 0, getWidth(), getHeight());
        
        graph.drawImage(image, 0, 0, getWidth(),getHeight(), null);
        
        graph.dispose();
        bs.show();        
    }

    protected void actualizacion() {
               
        for (int i = 0; i < this.pixls.length; i++) {
            this.pixls[i] = i + this.contador;
        }
        
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

    public Graficos getGrafo() {
        return grafo;
    }

    public void setGrafo(Graficos grafo) {
        this.grafo = grafo;
    }
    
}

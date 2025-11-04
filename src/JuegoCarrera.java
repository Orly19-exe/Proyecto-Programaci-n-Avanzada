import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

// ------------------- Clase Abstracta Entidad -------------------
abstract class Entidad {
    protected int x, y;
    protected int ancho, alto;
    protected int velocidad;
    protected Image imagen;

    public Entidad(int x, int y, int ancho, int alto, int velocidad, Image imagen) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidad = velocidad;
        this.imagen = imagen;
    }

    public abstract void actualizar();
    public void dibujar(Graphics g) { g.drawImage(imagen, x, y, ancho, alto, null); }
    public Rectangle obtenerLimites() { return new Rectangle(x, y, ancho, alto); }
}

// ------------------- Clase Auto -------------------
class Auto extends Entidad {
    private boolean moviendoIzquierda, moviendoDerecha;

    public Auto(int x, int y, String rutaImagen) {
        super(x, y, 70, 100, 10, new ImageIcon(rutaImagen).getImage());
    }

    @Override
    public void actualizar() {
        if (moviendoIzquierda && x > 0) x -= velocidad;
        if (moviendoDerecha && x < JuegoCarrera.ANCHO_PANTALLA - ancho) x += velocidad;
    }

    public void moverIzquierda() { moviendoIzquierda = true; }
    public void moverDerecha() { moviendoDerecha = true; }
    public void detener() { moviendoIzquierda = false; moviendoDerecha = false; }

    @Override
    public Rectangle obtenerLimites() {
        return new Rectangle(x + 10, y + 20, ancho - 20, alto - 40);
    }
}

// ------------------- Clase Obstaculo -------------------
class Obstaculo extends Entidad {
    private boolean moverLateral;
    private int direccion = 1;

    public Obstaculo(int x, int y, int velocidad, boolean moverLateral) {
        super(x, y, 70, 70, velocidad, imagenAleatoria());
        this.moverLateral = moverLateral;
    }

    @Override
    public void actualizar() {
        y += velocidad;
        if (moverLateral) {
            x += direccion * 3;
            if (x <= 0 || x >= JuegoCarrera.ANCHO_PANTALLA - ancho) direccion *= -1;
        }
    }

    public boolean fueraDePantalla() {
        return y > JuegoCarrera.ALTO_PANTALLA;
    }

    @Override
    public Rectangle obtenerLimites() {
        return new Rectangle(x + 10, y + 10, ancho - 20, alto - 20);
    }

    private static Image imagenAleatoria() {
        String[] archivos = {"imagenes/obstaculo1.png", "imagenes/obstaculo2.png", "imagenes/obstaculo3.png"};
        String ruta = archivos[new Random().nextInt(archivos.length)];
        Image img = new ImageIcon(ruta).getImage();

        if (img.getWidth(null) == -1) {
            BufferedImage placeholder = new BufferedImage(70, 70, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = placeholder.createGraphics();
            g2.setColor(Color.GRAY);
            g2.fillRect(0, 0, 70, 70);
            g2.dispose();
            return placeholder;
        }
        return img;
    }
}

// ------------------- Clase Principal del Juego -------------------
public class JuegoCarrera extends JPanel implements ActionListener, KeyListener {

    public static int ANCHO_PANTALLA = 800;
    public static int ALTO_PANTALLA = 600;

    private javax.swing.Timer temporizador;
    private Auto jugador;
    private ArrayList<Obstaculo> obstaculos;
    private int puntaje;
    private boolean perdido;
    private boolean iniciado;
    private String nivel = "";
    private int velocidadObstaculos = 5;
    private int frecuenciaObstaculos = 30;
    private Image fondo;
    private int fondoY = 0;
    private Font fuenteArcade;
    private JFrame ventana;

    public JuegoCarrera(JFrame ventana) {
        this.ventana = ventana;

        setPreferredSize(new Dimension(ANCHO_PANTALLA, ALTO_PANTALLA));
        fondo = new ImageIcon("imagenes/fondo.jpg").getImage();

        obstaculos = new ArrayList<>();
        jugador = null;
        perdido = false;
        iniciado = false;

        temporizador = new javax.swing.Timer(20, this);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();

        try {
            fuenteArcade = Font.createFont(Font.TRUETYPE_FONT, new File("imagenes/fuente_arcade.ttf")).deriveFont(24f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fuenteArcade);
        } catch (Exception e) {
            fuenteArcade = new Font("Arial", Font.BOLD, 24);
            System.out.println("⚠️ No se pudo cargar la fuente personalizada.");
        }

        addHierarchyListener(ev -> {
            if ((ev.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0 && isShowing()) {
                SwingUtilities.invokeLater(this::requestFocusInWindow);
            }
        });

        // Inicializar jugador directamente (Auto 1)
        jugador = new Auto(ANCHO_PANTALLA / 2 - 35, ALTO_PANTALLA - 150, "imagenes/auto1.png");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!perdido && iniciado) {
            jugador.actualizar();

            fondoY += 10;
            if (fondoY >= ALTO_PANTALLA) fondoY = 0;

            if (new Random().nextInt(frecuenciaObstaculos) == 0) {
                int x = new Random().nextInt(ANCHO_PANTALLA - 100);
                if (Math.abs(x - jugador.x) > 80) {
                    boolean moverLateral = nivel.equals("Difícil") || nivel.equals("Imposible");
                    obstaculos.add(new Obstaculo(x, -100, velocidadObstaculos, moverLateral));
                }
            }

            Iterator<Obstaculo> it = obstaculos.iterator();
            while (it.hasNext()) {
                Obstaculo obs = it.next();
                obs.actualizar();

                if (obs.obtenerLimites().intersects(jugador.obtenerLimites())) {
                    perdido = true;
                }

                if (obs.fueraDePantalla()) {
                    it.remove();
                    puntaje++;
                    if (puntaje % 10 == 0) {
                        velocidadObstaculos++;
                        if (frecuenciaObstaculos > 10) frecuenciaObstaculos -= 2;
                    }
                }
            }
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(fondo, 0, fondoY - ALTO_PANTALLA, ANCHO_PANTALLA, ALTO_PANTALLA, null);
        g.drawImage(fondo, 0, fondoY, ANCHO_PANTALLA, ALTO_PANTALLA, null);
        g.setFont(fuenteArcade);
        g.setColor(Color.WHITE);

        if (nivel.equals("") && !iniciado && !perdido) {
            g.drawString("Selecciona un nivel:", ANCHO_PANTALLA / 2 - 200, ALTO_PANTALLA / 2 - 100);
            g.drawString("[N] Normal", ANCHO_PANTALLA / 2 - 100, ALTO_PANTALLA / 2 - 50);
            g.drawString("[D] Difícil", ANCHO_PANTALLA / 2 - 100, ALTO_PANTALLA / 2);
            g.drawString("[I] Imposible", ANCHO_PANTALLA / 2 - 100, ALTO_PANTALLA / 2 + 50);
            return;
        }

        if (iniciado && !perdido) {
            jugador.dibujar(g);
            for (Obstaculo obs : obstaculos) obs.dibujar(g);
            g.drawString("Puntaje: " + puntaje, 50, 50);
            g.drawString("Nivel: " + nivel, ANCHO_PANTALLA - 250, 50);
        }

        if (perdido) {
            g.setColor(Color.RED);
            g.drawString("¡Has perdido!", ANCHO_PANTALLA / 2 - 200, ALTO_PANTALLA / 2 - 50);
            g.drawString("Presiona R para reiniciar", ANCHO_PANTALLA / 2 - 250, ALTO_PANTALLA / 2);
            g.drawString("Puntaje final: " + puntaje, ANCHO_PANTALLA / 2 - 200, ALTO_PANTALLA / 2 + 50);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();

        if (nivel.equals("") && !iniciado) {
            if (tecla == KeyEvent.VK_N) { nivel = "Normal"; velocidadObstaculos = 5; frecuenciaObstaculos = 30; iniciarJuego(); }
            else if (tecla == KeyEvent.VK_D) { nivel = "Difícil"; velocidadObstaculos = 8; frecuenciaObstaculos = 20; iniciarJuego(); }
            else if (tecla == KeyEvent.VK_I) { nivel = "Imposible"; velocidadObstaculos = 12; frecuenciaObstaculos = 10; iniciarJuego(); }
        } else if (!perdido && iniciado) {
            if (tecla == KeyEvent.VK_LEFT) jugador.moverIzquierda();
            else if (tecla == KeyEvent.VK_RIGHT) jugador.moverDerecha();
        } else if (tecla == KeyEvent.VK_R && perdido) {
            reiniciarJuego();
        }
    }

    private void iniciarJuego() {
        iniciado = true;
        temporizador.start();
    }

    @Override
    public void keyReleased(KeyEvent e) { if (jugador != null) jugador.detener(); }
    @Override public void keyTyped(KeyEvent e) {}

    private void reiniciarJuego() {
        obstaculos.clear();
        puntaje = 0;
        perdido = false;
        iniciado = false;
        nivel = "";
    }

    public static void main(String[] args) {
        JFrame ventana = new JFrame("Juego de Carreras");
        JuegoCarrera panelJuego = new JuegoCarrera(ventana);

        ventana.add(panelJuego);
        ventana.pack();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);

        SwingUtilities.invokeLater(() -> {
            panelJuego.requestFocusInWindow();
            panelJuego.requestFocus();
        });
    }
}


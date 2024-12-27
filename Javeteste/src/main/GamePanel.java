package main;

import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = scale * originalTileSize; // 48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = maxScreenCol * tileSize; //769pixel = 16 * 48 
    public final int screenHeight = maxScreenRow * tileSize;

    //FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    keyHandler keyH = new keyHandler();
    Thread gameThread; // é interessante usar a classe thread porque a contagem do tempo deve ser algo em paralelo ao rodar o jogo?
    Player player = new Player(this, keyH);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS; //9 zeros porque vamos trabalhar com o nanoTime() e isso representa um segundo em nanosegundos, assim, o valor ao lado representa 0,0016s segundos
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {

            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000; // convertendo pra milisegundos por causa do sleep;

                if (remainingTime < 0) {
                    remainingTime = 0;// não queremos que o processo durma um tempo negativo...
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {

                e.printStackTrace();
            }

            // a lógica do timer acima é bem simples. Definimos três variáveis: currenTime, nextDrawTime e drawInterval. O drawInterval é 1 segundo dividido pelo número de frames por segudndo FPS. nexDrawinterval é definido inicialmente como o tempo atual mais o drawInterval, e depois vai incrementando um drawInterval a cada loop. Definimos também o remainingTime, que é a diferença de tempo entre o nextDrawTime e o tempo atual. Cada interação do loop, como ultimo comando no laço definimos um sleeptim igual ao remainingTime. Por fim, não queremos um sleepTime como negativo, então testamos o remainingTime e se for negativo, defininmos como igual a zero. 
        }
    }

    public void update() {
        player.update();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
        player.draw(g2);

        g2.dispose();
    }

}

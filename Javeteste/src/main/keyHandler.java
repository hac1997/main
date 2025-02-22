package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    // pra explicar, cada tecla possui um número correspondente (referente ao código Unicode).  O método getKeyCode retorna o número correspondente à tecla que estamos apertando ou soltando, dependendo do evento (keyPressed e keyReleased);
    //No início do código definimos as variáveis booleanas upPressed, downPressed, leftPressed, rightPressed, com base no código abaixo.

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = true;
        };

        if (code == KeyEvent.VK_S) {
            downPressed = true;
        };
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        };
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        };
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        };

        if (code == KeyEvent.VK_S) {
            downPressed = false;
        };
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        };
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        };
    };
}

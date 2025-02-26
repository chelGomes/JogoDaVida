package TesteJogo;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import Jogo.JogoVida;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TesteJogoVida {
    
    private JogoVida jogo;

    /**
     * Configura��o inicial antes de cada teste.
     * Cria um novo jogo da vida com grid 5x5.
     */
    @Before
    public void setUp() {
        jogo = new JogoVida(5);
    }

    /**
     * Testa se uma c�lula isolada morre por solid�o na pr�xima gera��o.
     * Apenas uma c�lula � ativada e espera-se que ela morra.
     */
    @Test
    public void testCelulaMorrePorSolid�o() {
        jogo.setCelula(2, 2, 1); // Define uma c�lula viva isolada
        jogo.proximaGeracao();   // Calcula a pr�xima gera��o
        assertEquals(0, jogo.getCelula(2, 2)); // A c�lula deve morrer
    }

    /**
     * Testa se uma c�lula morre por superpopula��o.
     * C�lulas ao redor fazem com que a c�lula central morra na pr�xima gera��o.
     */
    @Test
    public void testCelulaMorrePorSuperpopulacao() {
        // Configura��o de c�lulas para simular superpopula��o
        jogo.setCelula(1, 1, 1);
        jogo.setCelula(1, 2, 1);
        jogo.setCelula(1, 3, 1);
        jogo.setCelula(2, 1, 1);
        jogo.setCelula(2, 2, 1); // C�lula central que deve morrer

        jogo.proximaGeracao(); // Calcula a pr�xima gera��o

        assertEquals(0, jogo.getCelula(2, 2)); // A c�lula central deve morrer
    }

    /**
     * Testa se uma c�lula continua viva na pr�xima gera��o.
     * A c�lula deve permanecer viva se tiver 2 ou 3 vizinhos vivos.
     */
    @Test
    public void testCelulaContinuaViva() {
        // Configura��o de um padr�o onde a c�lula deve continuar viva
        jogo.setCelula(1, 1, 1);
        jogo.setCelula(1, 2, 1);
        jogo.setCelula(2, 1, 1);

        jogo.proximaGeracao(); // Calcula a pr�xima gera��o

        assertEquals(1, jogo.getCelula(2, 2)); // A c�lula deve permanecer viva
    }

    /**
     * Testa se uma c�lula nasce por reprodu��o.
     * Se uma c�lula morta tiver exatamente 3 vizinhos vivos, ela nasce na pr�xima gera��o.
     */
    @Test
    public void testCelulaNascePorReproducao() {
        // Configura��o de c�lulas vizinhas para permitir reprodu��o
        jogo.setCelula(1, 1, 1);
        jogo.setCelula(1, 2, 1);
        jogo.setCelula(2, 1, 1);

        jogo.proximaGeracao(); // Calcula a pr�xima gera��o

        assertEquals(1, jogo.getCelula(2, 2)); // A c�lula deve nascer
    }
    
    /**
     * Testa se o m�todo getGridAsString() retorna a representa��o correta do grid.
     * O grid � inicializado com um padr�o espec�fico e comparado com a sa�da esperada.
     */
    @Test
    public void testGetGridAsString() {
        JogoVida jogo = new JogoVida(3);
        jogo.setCelula(0, 0, 1);
        jogo.setCelula(1, 1, 1);
        jogo.setCelula(2, 2, 1);

        // Representa��o esperada do grid
        String expected = "1 0 0 \n0 1 0 \n0 0 1 \n";
        
        assertEquals(expected, jogo.getGridAsString()); // Verifica se a sa�da � a esperada
    }
    
    /**
     * Testa se o m�todo imprimirGrid() exibe corretamente a representa��o do grid no console.
     * A sa�da do console � capturada e verificada.
     */
    @Test
    public void testImprimirGrid() {
        // Redireciona a sa�da do console para capturar a impress�o
        ByteArrayOutputStream saidaConsole = new ByteArrayOutputStream();
        System.setOut(new PrintStream(saidaConsole));

        JogoVida jogo = new JogoVida(5);
        jogo.setCelula(1, 2, 1);
        jogo.setCelula(2, 2, 1);
        jogo.setCelula(3, 2, 1);
        jogo.imprimirGrid(); // Imprime o grid no console

        // Restaura a sa�da original
        System.setOut(System.out);

        // Verifica se a sa�da cont�m c�lulas vivas na posi��o esperada
        String saida = saidaConsole.toString();
        assertTrue(saida.contains("O")); // Verifica se o caractere 'O' foi impresso
    }
}

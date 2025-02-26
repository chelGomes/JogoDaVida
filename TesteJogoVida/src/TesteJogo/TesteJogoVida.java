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
     * Configuração inicial antes de cada teste.
     * Cria um novo jogo da vida com grid 5x5.
     */
    @Before
    public void setUp() {
        jogo = new JogoVida(5);
    }

    /**
     * Testa se uma célula isolada morre por solidão na próxima geração.
     * Apenas uma célula é ativada e espera-se que ela morra.
     */
    @Test
    public void testCelulaMorrePorSolidão() {
        jogo.setCelula(2, 2, 1); // Define uma célula viva isolada
        jogo.proximaGeracao();   // Calcula a próxima geração
        assertEquals(0, jogo.getCelula(2, 2)); // A célula deve morrer
    }

    /**
     * Testa se uma célula morre por superpopulação.
     * Células ao redor fazem com que a célula central morra na próxima geração.
     */
    @Test
    public void testCelulaMorrePorSuperpopulacao() {
        // Configuração de células para simular superpopulação
        jogo.setCelula(1, 1, 1);
        jogo.setCelula(1, 2, 1);
        jogo.setCelula(1, 3, 1);
        jogo.setCelula(2, 1, 1);
        jogo.setCelula(2, 2, 1); // Célula central que deve morrer

        jogo.proximaGeracao(); // Calcula a próxima geração

        assertEquals(0, jogo.getCelula(2, 2)); // A célula central deve morrer
    }

    /**
     * Testa se uma célula continua viva na próxima geração.
     * A célula deve permanecer viva se tiver 2 ou 3 vizinhos vivos.
     */
    @Test
    public void testCelulaContinuaViva() {
        // Configuração de um padrão onde a célula deve continuar viva
        jogo.setCelula(1, 1, 1);
        jogo.setCelula(1, 2, 1);
        jogo.setCelula(2, 1, 1);

        jogo.proximaGeracao(); // Calcula a próxima geração

        assertEquals(1, jogo.getCelula(2, 2)); // A célula deve permanecer viva
    }

    /**
     * Testa se uma célula nasce por reprodução.
     * Se uma célula morta tiver exatamente 3 vizinhos vivos, ela nasce na próxima geração.
     */
    @Test
    public void testCelulaNascePorReproducao() {
        // Configuração de células vizinhas para permitir reprodução
        jogo.setCelula(1, 1, 1);
        jogo.setCelula(1, 2, 1);
        jogo.setCelula(2, 1, 1);

        jogo.proximaGeracao(); // Calcula a próxima geração

        assertEquals(1, jogo.getCelula(2, 2)); // A célula deve nascer
    }
    
    /**
     * Testa se o método getGridAsString() retorna a representação correta do grid.
     * O grid é inicializado com um padrão específico e comparado com a saída esperada.
     */
    @Test
    public void testGetGridAsString() {
        JogoVida jogo = new JogoVida(3);
        jogo.setCelula(0, 0, 1);
        jogo.setCelula(1, 1, 1);
        jogo.setCelula(2, 2, 1);

        // Representação esperada do grid
        String expected = "1 0 0 \n0 1 0 \n0 0 1 \n";
        
        assertEquals(expected, jogo.getGridAsString()); // Verifica se a saída é a esperada
    }
    
    /**
     * Testa se o método imprimirGrid() exibe corretamente a representação do grid no console.
     * A saída do console é capturada e verificada.
     */
    @Test
    public void testImprimirGrid() {
        // Redireciona a saída do console para capturar a impressão
        ByteArrayOutputStream saidaConsole = new ByteArrayOutputStream();
        System.setOut(new PrintStream(saidaConsole));

        JogoVida jogo = new JogoVida(5);
        jogo.setCelula(1, 2, 1);
        jogo.setCelula(2, 2, 1);
        jogo.setCelula(3, 2, 1);
        jogo.imprimirGrid(); // Imprime o grid no console

        // Restaura a saída original
        System.setOut(System.out);

        // Verifica se a saída contém células vivas na posição esperada
        String saida = saidaConsole.toString();
        assertTrue(saida.contains("O")); // Verifica se o caractere 'O' foi impresso
    }
}

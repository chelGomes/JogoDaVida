package teste;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/* casos de teste antes da primeira analise de cobertura */

	public class JogoDaVidaTest {
	    private JogoDaVida jogo;

	    @Before
	    public void setUp() {
	        jogo = new JogoDaVida(5);
	    }

	    @Test
	    public void testCelulaMorrePorSolidão() {
	        jogo.setCelula(2, 2, 1);
	        jogo.proximaGeracao();
	        assertEquals(0, jogo.getCelula(2, 2));
	    }

	    @Test
	    public void testCelulaMorrePorSuperpopulacao() {
	        jogo.setCelula(1, 1, 1);
	        jogo.setCelula(1, 2, 1);
	        jogo.setCelula(1, 3, 1);
	        jogo.setCelula(2, 1, 1);
	        jogo.setCelula(2, 2, 1);
	        jogo.proximaGeracao();
	        assertEquals(0, jogo.getCelula(2, 2));
	    }

	    @Test
	    public void testCelulaContinuaViva() {
	        jogo.setCelula(1, 1, 1);
	        jogo.setCelula(1, 2, 1);
	        jogo.setCelula(2, 1, 1);
	        jogo.proximaGeracao();
	        assertEquals(1, jogo.getCelula(2, 2));
	    }

	    @Test
	    public void testCelulaNascePorReproducao() {
	        jogo.setCelula(1, 1, 1);
	        jogo.setCelula(1, 2, 1);
	        jogo.setCelula(2, 1, 1);
	        jogo.proximaGeracao();
	        assertEquals(1, jogo.getCelula(2, 2));
	    }
	    
	    /* casos de teste após a primeira analise de cobertura */
	    
	    @Test
	    public void testCelulaNaBorda() {
	        jogo.setCelula(0, 0, 1); // Célula no canto superior esquerdo
	        jogo.setCelula(0, 1, 1);
	        jogo.setCelula(1, 0, 1);
	        jogo.proximaGeracao();
	        assertEquals(1, jogo.getCelula(0, 0)); // Verifica se a célula na borda continua viva
	    }
	    
	    @Test
	    public void testCelulaComTodosVizinhosMortos() {
	        jogo.setCelula(2, 2, 1); // Célula viva no centro
	        jogo.proximaGeracao();
	        assertEquals(0, jogo.getCelula(2, 2)); // Deve morrer por solidão
	    }
	    
	    @Test
	    public void testCelulaComTodosVizinhosVivos() {
	        for (int i = 0; i < 3; i++) {
	            for (int j = 0; j < 3; j++) {
	                jogo.setCelula(i, j, 1); // Preenche um bloco 3x3 de células vivas
	            }
	        }
	        jogo.proximaGeracao();
	        assertEquals(0, jogo.getCelula(1, 1)); // Célula central deve morrer por superpopulação
	    }
	    
	    /* casos de teste após a segunda analise de cobertura */
	    
	    @Test
	    public void testCelulaComUmVizinho() {
	        jogo.setCelula(1, 1, 1); // Célula viva
	        jogo.setCelula(1, 2, 1); // Um vizinho vivo
	        jogo.proximaGeracao();
	        assertEquals(0, jogo.getCelula(1, 1)); // Deve morrer por solidão
	    }
	    
	    @Test
	    public void testCelulaComDoisVizinhos() {
	        jogo.setCelula(1, 1, 1); // Célula viva
	        jogo.setCelula(1, 2, 1); // Primeiro vizinho vivo
	        jogo.setCelula(2, 1, 1); // Segundo vizinho vivo
	        jogo.proximaGeracao();
	        assertEquals(1, jogo.getCelula(1, 1)); // Deve continuar viva
	    }
	    
	    @Test
	    public void testCelulaComQuatroVizinhos() {
	        jogo.setCelula(1, 1, 1); // Célula viva
	        jogo.setCelula(1, 2, 1); // Primeiro vizinho vivo
	        jogo.setCelula(2, 1, 1); // Segundo vizinho vivo
	        jogo.setCelula(0, 1, 1); // Terceiro vizinho vivo
	        jogo.setCelula(1, 0, 1); // Quarto vizinho vivo
	        jogo.proximaGeracao();
	        assertEquals(0, jogo.getCelula(1, 1)); // Deve morrer por superpopulação
	    }
	    
	    /* apos a terceira analise de cobertura */
	    
	   /* @Test
	    public void testImprimirGrid() {
	        JogoDaVida jogo = new JogoDaVida(5); // Supondo um grid 5x5
	        jogo.imprimirGrid(); // Apenas garantir que o método é executado
	        assertNotNull(jogo); // Verifica que o objeto existe
	    }*/

	   
	}


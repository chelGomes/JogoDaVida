package teste;

public class main {
    public static void main(String[] args) {
        // Tamanho do grid (por exemplo, 5x5)
        int tamanho = 5;
        JogoDaVida jogo = new JogoDaVida(tamanho);

        // Inicializa��o do grid com algumas c�lulas vivas
        jogo.setCelula(1, 2, 1);
        jogo.setCelula(2, 2, 1);
        jogo.setCelula(3, 2, 1);

        // Exibindo a gera��o inicial
        System.out.println("Gera��o Inicial:");
        jogo.imprimirGrid();

        // sSimulando algumas gera��es
        int numGeracoes = 5;
        for (int i = 1; i <= numGeracoes; i++) {
            System.out.println("\nGera��o " + i + ":");
            jogo.proximaGeracao();
            jogo.imprimirGrid();
        }
    }
}
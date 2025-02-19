package teste;

public class main {
    public static void main(String[] args) {
        // Tamanho do grid (por exemplo, 5x5)
        int tamanho = 5;
        JogoDaVida jogo = new JogoDaVida(tamanho);

        // Inicialização do grid com algumas células vivas
        jogo.setCelula(1, 2, 1);
        jogo.setCelula(2, 2, 1);
        jogo.setCelula(3, 2, 1);

        // Exibindo a geração inicial
        System.out.println("Geração Inicial:");
        jogo.imprimirGrid();

        // sSimulando algumas gerações
        int numGeracoes = 5;
        for (int i = 1; i <= numGeracoes; i++) {
            System.out.println("\nGeração " + i + ":");
            jogo.proximaGeracao();
            jogo.imprimirGrid();
        }
    }
}
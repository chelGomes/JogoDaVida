package Jogo;

public class JogoVida {
    private int[][] grid;
    private int tamanho;

    public JogoVida(int tamanho) {
        this.tamanho = tamanho;
        this.grid = new int[tamanho][tamanho];
    }
    
    

    public void setCelula(int x, int y, int estado) {
        grid[x][y] = estado;
    }

    public int getCelula(int x, int y) {
        return grid[x][y];
    }

    public void proximaGeracao() {
        int[][] novaGrid = new int[tamanho][tamanho];

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                int vizinhosVivos = contarVizinhosVivos(i, j);

                if (grid[i][j] == 1) {
                    if (vizinhosVivos < 2 || vizinhosVivos > 3) {
                        novaGrid[i][j] = 0;
                    } else {
                        novaGrid[i][j] = 1;
                    }
                } else {
                    if (vizinhosVivos == 3) {
                        novaGrid[i][j] = 1;
                    } else {
                        novaGrid[i][j] = 0;
                    }
                }
            }
        }

        grid = novaGrid;
    }

    public int contarVizinhosVivos(int x, int y) {
        int count = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;

                int novoX = x + i;
                int novoY = y + j;

                if (novoX >= 0 && novoX < tamanho && novoY >= 0 && novoY < tamanho) {
                    count += grid[novoX][novoY];
                }
            }
        }

        return count;
    }

    /*public void imprimirGrid() {
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }*/
    
    
    public String getGridAsString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                sb.append(grid[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    
    /*public void imprimirGrid() {
        System.out.print(getGridAsString());
    }

    public static void main(String[] args) {
        // Tamanho do grid (por exemplo, 5x5)
        int tamanho = 5;
        JogoVida jogo = new JogoVida(tamanho);

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
    }*/
    
    public void imprimirGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j] == 1 ? "O" : "."); // Células vivas como 'O', mortas como '.'
            }
            System.out.println(); // Quebra de linha no final de cada linha do grid
        }
    }




}






package teste;

public class JogoDaVida {
    private int[][] grid;
    private int tamanho;

    public JogoDaVida(int tamanho) {
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

    int contarVizinhosVivos(int x, int y) {
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

    public void imprimirGrid() {
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }


}
package Algortimos;

public class WinogradeScaled {

    public static int[][] winogradScaled(int[][] a, int[][] b, int[][] c, int n, int p, int m) {
        WinogradOriginal metodo = new WinogradOriginal();

        int[][] copya = new int[n][p];
        int[][] copyb = new int[p][m];

        // Copiar matrices originales
        copyMatrix(a, copya);
        copyMatrix(b, copyb);

        // Calcular normas y lambda
        int aa = normInf(copya, n, p);
        int bb = normInf(copyb, p, m);
        int lambda = (int) Math.floor(0.5 + Math.log(bb / (double) aa) / Math.log(4));

        // Escalar las matrices
        multiplyWithScalar(copya, n, p, Math.pow(2, lambda)); // Aplicar escalar solo a la matriz A
        multiplyWithScalar(copyb, p, m, Math.pow(2, -lambda));

        // Realizar la multiplicación de Winograd Original
        c = metodo.winogradOriginal(copya, copyb, c, n, p, m);
        return c;
    }

    // Método auxiliar para imprimir una matriz
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    // Método auxiliar para copiar una matriz
    public static void copyMatrix(int[][] src, int[][] dest) {
        for (int i = 0; i < src.length; i++) {
            System.arraycopy(src[i], 0, dest[i], 0, src[i].length);
        }
    }

    public static void main(String[] args) {
        int[][] m1 = { {12, 44}, {19, 23} };
        int[][] m2 = { {66, 80}, {75, 78} };
        int m1l = m1.length;
        int m2l = m2.length;
        int p = m2[0].length;
        int[][] m3 = new int[m1l][m2l];

        int[][] resultado = winogradScaled(m1, m2, m3, m1l, m2l, p);
        System.out.println("Resultado:");
        printMatrix(resultado);
    }

    // Método auxiliar para multiplicar una matriz por un escalar
    public static void multiplyWithScalar(int[][] a, int n, int m, double scalar) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] *= scalar;
            }
        }
    }

    // Método auxiliar para calcular la norma infinita de una matriz
    public static int normInf(int[][] a, int n, int m) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < m; j++) {
                sum += Math.abs(a[i][j]);
            }
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }
}

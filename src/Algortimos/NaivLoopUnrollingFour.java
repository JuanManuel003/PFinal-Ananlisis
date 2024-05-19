package Algortimos;

public class NaivLoopUnrollingFour {

	public static int[][] naivLoopUnrollingFour(int[][] a, int[][] b, int[][] c, int n, int p, int m) {
		int i, j, k;
		int aux;

		if (p % 4 == 0) {
			for (i = 0; i < n; i++) {
				for (j = 0; j < m; j++) {
					aux = 0;
					for (k = 0; k < p; k += 4) {
						aux += a[i][k] * b[k][j] + a[i][k + 1] * b[k + 1][j] + a[i][k + 2] * b[k + 2][j]
								+ a[i][k + 3] * b[k + 3][j];
					}
					c[i][j] = aux;
				}
			}
		} else if (p % 4 == 1) {
			int PP = p - 1;
			for (i = 0; i < n; i++) {
				for (j = 0; j < m; j++) {
					aux = 0;
					for (k = 0; k < PP; k += 4) {
						aux += a[i][k] * b[k][j] + a[i][k + 1] * b[k + 1][j] + a[i][k + 2] * b[k + 2][j]
								+ a[i][k + 3] * b[k + 3][j];
					}
					c[i][j] = aux + a[i][PP] * b[PP][j];
				}
			}
		} else if (p % 4 == 2) {

			int PP = p - 2;
			int PPP = p - 1;
			for (i = 0; i < n; i++) {
				for (j = 0; j < m; j++) {
					aux = 0;
					for (k = 0; k < PP; k += 4) {
						aux += a[i][k] * b[k][j] + a[i][k + 1] * b[k + 1][j] + a[i][k + 2] * b[k + 2][j]
								+ a[i][k + 3] * b[k + 3][j];
					}
					c[i][j] = aux + a[i][PP] * b[PP][j] + a[i][PPP] * b[PPP][j];
				}
			}
		} else {
			int PP = p - 3;
			int PPP = p - 2;
			int PPPP = p - 1;
			for (i = 0; i < n; i++) {
				for (j = 0; j < m; j++) {
					aux = 0;
					for (k = 0; k < PP; k += 4) {
						aux += a[i][k] * b[k][j] + a[i][k + 1] * b[k + 1][j] + a[i][k + 2] * b[k + 2][j]
								+ a[i][k + 3] * b[k + 3][j];
					}
					c[i][j] = aux + a[i][PP] * b[PP][j] + a[i][PPP] * b[PPP][j] + a[i][PPPP] * b[PPPP][j];
				}
			}
		}
		return c;
	}

	public static void main(String[] args) {
		int[][] m1 = { {12, 44}, {19,23} };
		int[][] m2 = { {66, 80}, {75, 78} };
		int m1l = m1.length;
		int m2l = m2.length;
		int p = m2[0].length;
		int[][] m3 = new int[m1l][m2l];

		int[][] resultado = naivLoopUnrollingFour(m1, m2, m3, m1l, m2l, p);
		imprimirMatriz(resultado);

	}

	public static void imprimirMatriz(int[][] matriz) {
		String imprimir = "";
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				imprimir += " " + matriz[i][j];

			}
			imprimir += "\n";
		}
		System.out.println(imprimir);
	}

}

package Algortimos;

public class WinogradOriginal {
	public static int[][] winogradOriginal(int[][] a, int[][] b, int[][] c, int n, int p, int m) {
		int i, j, k;
		int aux;
		int upsilon = p % 2;
		int gamma = p - upsilon;
		int[] y = new int[m];
		int[] z = new int[n];

		for (i = 0; i < m; i++) {
			aux = 0;
			for (j = 0; j < gamma; j += 2) {
				aux += a[i][j] * a[i][j + 1];
			}
			y[i] = aux;
		}

		for (i = 0; i < n; i++) {
			aux = 0;
			for (j = 0; j < gamma; j += 2) {
				aux += b[j][i] * b[j + 1][i];
			}
			z[i] = aux;
		}

		if (upsilon == 1) {
			int PP = p - 1;
			for (i = 0; i < m; i++) {
				for (k = 0; k < n; k++) {
					aux = 0;
					for (j = 0; j < gamma; j += 2) {
						aux += (a[i][j] + b[j + 1][k]) * (a[i][j + 1] + b[j][k]);
					}
					c[i][k] = aux - y[i] - z[k] + a[i][PP] * b[PP][k];
				}
			}
		} else {
			for (i = 0; i < m; i++) {
				for (k = 0; k < n; k++) {
					aux = 0;
					for (j = 0; j < gamma; j += 2) {
						aux += (a[i][j] + b[j + 1][k]) * (a[i][j + 1] + b[j][k]);
					}
					c[i][k] = aux - y[i] - z[k];
				}
			}
		}

		// Liberar memoria
		y = null;
		z = null;

		return c;
	}

	public static void main(String[] args) {
		int[][] m1 = { {12, 44}, {19, 23} };
		int[][] m2 = { {66, 80}, {75, 78} };
		int m1l = m1.length;
		int m2l = m2.length;
		int p = m2[0].length;
		int[][] m3 = new int[m1l][m2l];

		int[][] resultado = winogradOriginal(m1, m2, m3, m1l, m2l, p);
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

package Algortimos;

import java.util.Arrays;

public class ParallelBlockTresCuatro {

	public static int[][] multiply(int[][] a, int[][] b, int blockSize) {
		int size = a.length;
		int[][] c = new int[size][size];

		Arrays.stream(new int[] { 0 }).parallel().forEach(ib -> {
			for (ib = 0; ib < size; ib += blockSize) {
				for (int jb = 0; jb < size; jb += blockSize) {
					for (int kb = 0; kb < size; kb += blockSize) {
						for (int i = ib; i < ib + blockSize && i < size; i++) {
							for (int j = jb; j < jb + blockSize && j < size; j++) {
								for (int k = kb; k < kb + blockSize && k < size; k++) {
									c[i][k] += a[i][j] * b[j][k];
								}
							}
						}
					}
				}
			}
		});
		return c;
	}

	public static void main(String[] args) {
		int[][] m1 = { {12, 44}, {19, 23} };
		int[][] m2 = { {66, 80}, {75, 78} };


		int[][] resultado = multiply(m1, m2, (int) Math.sqrt(m1.length));
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

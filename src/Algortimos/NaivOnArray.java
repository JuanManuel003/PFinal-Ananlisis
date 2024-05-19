package Algortimos;

public class NaivOnArray {

	public static int[][] naivOnArray(int[][] a, int[][] b, int[][] c, int n, int p , int m){
	    for (int i = 0; i < n; i++) {
	        for (int j = 0; j < m; j++) {
	            c[i][j] = 0;
	            for (int k = 0; k < p; k++) {
	                c[i][j] += a[i][k] * b[k][j];
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

		int[][] resultado = naivOnArray(m1, m2, m3, m1l, m2l, p);
		imprimirMatriz(resultado);

	}

	public static void imprimirMatriz(int[][] matriz){
		String imprimir ="";
		for(int i=0; i<matriz.length; i++){
			for(int j=0; j<matriz[0].length; j++){
				imprimir += " " + matriz[i][j];

			}
			imprimir += "\n";
		}
		System.out.println(imprimir);
	}

}

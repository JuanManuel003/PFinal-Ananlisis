package Algortimos;

public class StrassenNaiv {
	public int[][] strassenNaiv(int[][] a, int[][] b, int[][] c, int n, int p, int m) {

        int MaxSize, k, mm, NewSize, i, j;

        MaxSize = max(n,p);


        if (MaxSize < 16) {
            MaxSize = 16;
        }
        k = (int) Math.floor(Math.log(MaxSize)/Math.log(2)) - 4;
        mm = (int) Math.floor(MaxSize * Math.pow(2,-k)) + 1;

        NewSize = mm * (int) Math.pow(2,k);

        // add zero rows and columns to use Strassens algorithm
        int[][] aNew = new int[NewSize][];
        int[][] bNew = new int[NewSize][];
        int[][] auxResult = new int[NewSize][];
        for (i = 0; i < NewSize; i++){
            aNew[i] = new int[NewSize];
            bNew[i] = new int[NewSize];
            auxResult[i] = new int[NewSize];
        }


        for (i = 0; i < NewSize; i++) {
            for (j = 0; j < NewSize; j++) {
                aNew[i][j] = 0;
                bNew[i][j] = 0;
            }
        }
        for (i = 0; i < n; i++) {
            for (j = 0; j < p; j++) {
                aNew[i][j] = a[i][j];
            }
        }
        for (i = 0; i < p; i++) {
            for (j = 0; j < m; j++) {
                bNew[i][j] = b[i][j];
            }
        }

        strassenNaivStep(aNew, bNew, auxResult, NewSize, mm);

        for (i = 0; i < n; i++) {
            for (j = 0; j < m; j++) {
                c[i][j] = auxResult[i][j]; //Result
            }
        }
        return c;
    }

    public int max (int n, int p){
        if (n < p){
            return p;
        } else {
            return n;
        }

    }


    private void minus(int[][] a, int[][] b, int[][] c, int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                c[i][j] = a[i][j] - b[i][j];
            }
        }
    }

    private void plus(int[][] a, int[][] b, int[][] c, int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                c[i][j] = a[i][j] + b[i][j];
            }
        }
    }

    public int[][] strassenNaivStep(int[][] a, int[][] b, int[][] c, int n, int m) {

        int i, j, NewSize;

        if ((n % 2 == 0) && (n > m)){
            NewSize = n / 2;

            // decompose A and B
            // create ResultPart, Aux1,...,Aux7 and Helper1, Helper2
            int[][] A11 = new int[NewSize][];
            int[][] A12 = new int[NewSize][];
            int[][] A21 = new int[NewSize][];
            int[][] A22 = new int[NewSize][];
            int[][] B11 = new int[NewSize][];
            int[][] B12 = new int[NewSize][];
            int[][] B21 = new int[NewSize][];
            int[][] B22 = new int[NewSize][];

            int[][] ResultPart11 = new int[NewSize][];
            int[][] ResultPart12 = new int[NewSize][];
            int[][] ResultPart21 = new int[NewSize][];
            int[][] ResultPart22 = new int[NewSize][];

            int[][] Helper1 = new int[NewSize][];
            int[][] Helper2 = new int[NewSize][];

            int[][] Aux1 = new int[NewSize][];
            int[][] Aux2 = new int[NewSize][];
            int[][] Aux3 = new int[NewSize][];
            int[][] Aux4 = new int[NewSize][];
            int[][] Aux5 = new int[NewSize][];
            int[][] Aux6 = new int[NewSize][];
            int[][] Aux7 = new int[NewSize][];

            for (i = 0; i < NewSize; i++){

                A11[i] = new int[NewSize];
                A12[i] = new int[NewSize];
                A21[i] = new int[NewSize];
                A22[i] = new int[NewSize];
                B11[i] = new int[NewSize];
                B12[i] = new int[NewSize];
                B21[i] = new int[NewSize];
                B22[i] = new int[NewSize];

                ResultPart11[i] = new int[NewSize];
                ResultPart12[i] = new int[NewSize];
                ResultPart21[i] = new int[NewSize];
                ResultPart22[i] = new int[NewSize];

                Helper1[i] = new int[NewSize];
                Helper2[i] = new int[NewSize];

                Aux1[i] = new int[NewSize];
                Aux2[i] = new int[NewSize];
                Aux3[i] = new int[NewSize];
                Aux4[i] = new int[NewSize];
                Aux5[i] = new int[NewSize];
                Aux6[i] = new int[NewSize];
                Aux7[i] = new int[NewSize];
            }

            //fill new matrices
            for( i = 0; i < NewSize; i++){
                for( j = 0; j < NewSize; j++){
                    A11[i][j] = a[i][j];
                }
            }

            for( i = 0; i < NewSize; i++){
                for( j = 0; j < NewSize; j++){
                    A12[i][j] = a[i][NewSize + j];
                }
            }
            for( i = 0; i < NewSize; i++){
                for( j = 0; j < NewSize; j++){
                    A21[i][j] = a[NewSize + i][j];
                }
            }

            for( i = 0; i < NewSize; i++) {
                for (j = 0; j < NewSize; j++) {
                    A22[i][j] = a[NewSize + i][NewSize + j];
                }
            }

            for( i = 0; i < NewSize; i++){
                for( j = 0; j < NewSize; j++){
                    B11[i][j] = b[i][j];
                }
            }
            for( i = 0; i < NewSize; i++){
                for( j = 0; j < NewSize; j++){
                    B12[i][j] = b[i][NewSize + j];
                }
            }
            for( i = 0; i < NewSize; i++){
                for( j = 0; j < NewSize; j++){
                    B21[i][j] = b[NewSize + i][j];
                }
            }
            for( i = 0; i < NewSize; i++){
                for( j = 0; j < NewSize; j++){
                    B22[i][j] = b[NewSize + i][NewSize + j];
                }
            }

            // computing the seven aux. variables
            plus(A11, A22, Helper1, NewSize, NewSize);
            plus(B11, B22, Helper2, NewSize, NewSize);
            strassenNaivStep(Helper1, Helper2, Aux1, NewSize, m);

            plus(A21, A22, Helper1, NewSize, NewSize);
            strassenNaivStep(Helper1, B11, Aux2, NewSize, m);

            minus(B12, B22, Helper1, NewSize, NewSize);
            strassenNaivStep(A11, Helper1, Aux3, NewSize, m);

            minus(B21, B11, Helper1, NewSize, NewSize);
            strassenNaivStep(A22, Helper1, Aux4, NewSize, m);

            plus(A11, A12, Helper1, NewSize, NewSize);
            strassenNaivStep(Helper1, B22, Aux5, NewSize, m);

            minus(A21, A11, Helper1, NewSize, NewSize);
            plus(B11, B12, Helper2, NewSize, NewSize);
            strassenNaivStep(Helper1, Helper2, Aux6, NewSize, m);

            minus(A12, A22, Helper1, NewSize, NewSize);
            plus(B21, B22, Helper2, NewSize, NewSize);
            strassenNaivStep(Helper1, Helper2, Aux7, NewSize, m);

            // computing the four parts of the result
            plus(Aux1, Aux4, ResultPart11, NewSize, NewSize);
            minus(ResultPart11, Aux5, ResultPart11, NewSize, NewSize);
            plus(ResultPart11, Aux7, ResultPart11, NewSize, NewSize);

            plus(Aux3, Aux5, ResultPart12, NewSize, NewSize);

            plus(Aux2, Aux4, ResultPart21, NewSize, NewSize);

            plus(Aux1, Aux3, ResultPart22, NewSize, NewSize);
            minus(ResultPart22, Aux2, ResultPart22, NewSize, NewSize);
            plus(ResultPart22, Aux6, ResultPart22, NewSize, NewSize);

            // store results in the "result matrix"
            for( i = 0; i < NewSize; i++){
                for( j = 0; j < NewSize; j++){
                    c[i][j] = ResultPart11[i][j];
                }
            }

            for( i = 0; i < NewSize; i++){
                for( j = 0; j < NewSize; j++){
                    c[i][NewSize + j] = ResultPart12[i][j];
                }
            }

            for( i = 0; i < NewSize; i++){
                for( j = 0; j < NewSize; j++){
                    c[NewSize + i][j] = ResultPart21[i][j];
                }
            }

            for( i = 0; i < NewSize; i++){
                for( j = 0; j < NewSize; j++){
                    c[NewSize + i][NewSize + j] = ResultPart22[i][j];
                }
            }

            // free helper variables
            A11 = null;
            A12 = null;
            A21 = null;
            A22 = null;

            B11 = null;
            B12 = null;
            B21 = null;
            B22 = null;

            ResultPart11 = null;
            ResultPart12 = null;
            ResultPart21 = null;
            ResultPart22 = null;

            Helper1 = null;
            Helper2 = null;

            Aux1 = null;
            Aux2 = null;
            Aux3 = null;
            Aux4 = null;
            Aux5 = null;
            Aux6 = null;
            Aux7 = null;

        } else {
            // use naiv algorithm
        	c = naivStandard(a, b, c, n, n, n);
        }
        return c;
    }

    public static int[][] naivStandard(int[][] a, int[][] b, int[][] c, int n, int p, int m) {
	    int aux; // variable auxiliar para almacenar el valor temporal del producto de las matrices
	    for (int i = 0; i < n; i++) { // iteraci�n sobre las filas de la matriz [A]
	        for (int j = 0; j < m; j++) { // iteraci�n sobre las columnas de la matriz [B]
	            aux = 0; // inicializaci�n de la variable auxiliar en cero
	            for (int k = 0; k < p; k++) { // iteraci�n sobre los elementos de la matriz [A] y [B] a multiplicar
	                aux += a[i][k] * b[k][j]; // se multiplica y se suma el resultado a la variable auxiliar
	            }
	            c[i][j] = aux; // se almacena el resultado final en la matriz C
	        }
	    }
	    return c; // se devuelve la matriz C resultante
	}

    public static void main(String[] args) {

    	int[][] m1 = { {12, 44}, {19, 23} };
		int[][] m2 = { {66, 80}, {75, 78} };

		int m1l = m1.length;
		int m2l = m2.length;
		int p = m2[0].length;
		int[][] m3 = new int[m1l][m2l];
		StrassenNaiv metodo = new StrassenNaiv();

		int[][] resultado = metodo.strassenNaiv(m1, m2, m3, m1l, m2l, p);
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

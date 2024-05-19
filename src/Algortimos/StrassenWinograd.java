package Algortimos;

public class StrassenWinograd {

	public int max (int n, int p){
        if (n < p){
            return p;
        } else {
            return n;
        }

    }

    public int[][] strassenWinograd(int[][] a, int[][] b, int[][] c, int n, int p, int m) {

        int MaxSize, k, mm, NewSize, i, j;
        MaxSize = max(n,p);
        MaxSize = max(MaxSize,m);
        if ( MaxSize < 16){
            MaxSize = 16; // otherwise it is not possible to compute k
        }
        k = (int) Math.floor(Math.log(MaxSize)/Math.log(2)) - 4;
        mm = (int) Math.floor(MaxSize * Math.pow(2,-k)) + 1;
        NewSize = mm * (int) Math.pow(2,k);


        // add zero rows and columns to use Strassens algorithm
        int[][] NewA = new int[NewSize][];
        int[][] NewB = new int[NewSize][];
        int[][] AuxResult = new int[NewSize][];
        for (i = 0; i < NewSize; i++){
            NewA[i] = new int[NewSize];
            NewB[i] = new int[NewSize];
            AuxResult[i] = new int[NewSize];
        }

        for( i = 0; i < NewSize; i++){
            for( j = 0; j < NewSize; j++){
                NewA[i][j] = 0;
                NewB[i][j] = 0;
            }
        }
        for( i = 0; i < n; i++){
            for( j = 0; j < p; j++){
                NewA[i][j] = a[i][j];
            }
        }
        for( i = 0; i < p; i++){
            for( j = 0; j < m; j++){
                NewB[i][j] = b[i][j];
            }
        }

        strassenWinogradStep(NewA, NewB, AuxResult, NewSize, mm);

        // extract the result
        for( i = 0; i < n; i++){
            for( j = 0; j < m; j++){
                c[i][j] = AuxResult[i][j];
            }
        }

        return c;
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

    private int[][] strassenWinogradStep(int[][] a, int[][] b, int[][] c, int n, int m) {

    	int i, j , NewSize;

        if( (n % 2 == 0) && (n > m) ) { // recursive use of StrassenNaivStep
            NewSize = n / 2;

            // decompose A and B
            // create ResultPart, Aux1,...,Aux7 and Helper1, Helper2
            int[][] A1 = new int[NewSize][];
            int[][] A2 = new int[NewSize][];
            int[][] B1 = new int[NewSize][];
            int[][] B2 = new int[NewSize][];

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
            int[][] Aux8 = new int[NewSize][];
            int[][] Aux9 = new int[NewSize][];

            for (i = 0; i < NewSize; i++){
                A1[i] = new int[NewSize];
                A2[i] = new int[NewSize];
                B1[i] = new int[NewSize];
                B2[i] = new int[NewSize];
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
                Aux8[i] = new int[NewSize];
                Aux9[i] = new int[NewSize];
            }

            // fill new matrices
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
            for( i = 0; i < NewSize; i++){
                for( j = 0; j < NewSize; j++){
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

            // computing the 4 + 9 aux. variables
            minus(A11, A21, A1, NewSize, NewSize);
            minus(A22, A1, A2, NewSize, NewSize);
            minus(B22, B12, B1, NewSize, NewSize);
            plus(B1, B11, B2, NewSize, NewSize);

            strassenWinogradStep(A11, B11, Aux1, NewSize, m);
            strassenWinogradStep(A12, B21, Aux2, NewSize, m);
            strassenWinogradStep(A2, B2, Aux3, NewSize, m);
            plus(A21, A22, Helper1, NewSize, NewSize);
            minus(B12, B11, Helper2, NewSize, NewSize);
            strassenWinogradStep(Helper1, Helper2, Aux4, NewSize, m);
            strassenWinogradStep(A1, B1, Aux5, NewSize, m);
            minus(A12, A2, Helper1, NewSize, NewSize);
            strassenWinogradStep(Helper1, B22, Aux6, NewSize, m);
            minus(B21, B2, Helper1, NewSize, NewSize);
            strassenWinogradStep(A22, Helper1, Aux7, NewSize, m);
            plus(Aux1, Aux3, Aux8, NewSize, NewSize);
            plus(Aux8, Aux4, Aux9, NewSize, NewSize);

            // computing the four parts of the result
            plus(Aux1, Aux2, ResultPart11, NewSize, NewSize);
            plus(Aux9, Aux6, ResultPart12, NewSize, NewSize);
            plus(Aux8, Aux5, Helper1, NewSize, NewSize);
            plus(Helper1, Aux7, ResultPart21, NewSize, NewSize);
            plus(Aux9, Aux5, ResultPart22, NewSize, NewSize);

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
            A1 = null;
            A2 = null;
            B1 = null;
            B2 = null;

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
		StrassenWinograd metodo = new StrassenWinograd();

		int[][] resultado = metodo.strassenWinograd(m1, m2, m3, m1l, m2l, p);
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

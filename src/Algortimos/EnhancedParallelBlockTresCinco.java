package Algortimos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.xml.bind.JAXBException;

import Conexion.Persistencia;

public class EnhancedParallelBlockTresCinco {

	 public static int[][] parallelBlockMatrixMultiply(int[][] A, int[][] B, int[][] C, int size, int bsize) {
	        // Crear un ExecutorService para administrar los hilos
	        ExecutorService executor = Executors.newFixedThreadPool(2);
	        // CountDownLatch para esperar que ambos hilos completen su tarea
	        CountDownLatch latch = new CountDownLatch(2);

	        // Primer hilo para la primera mitad de la multiplicaci�n
	        executor.submit(() -> {
	            for (int i1 = 0; i1 < size / 2; i1 += bsize) {
	                for (int j1 = 0; j1 < size; j1 += bsize) {
	                    for (int k1 = 0; k1 < size; k1 += bsize) {
	                        for (int i = i1; i < Math.min(i1 + bsize, size); i++) {
	                            for (int j = j1; j < Math.min(j1 + bsize, size); j++) {
	                                for (int k = k1; k < Math.min(k1 + bsize, size); k++) {
	                                    A[i][j] += B[i][k] * C[k][j];
	                                }
	                            }
	                        }
	                    }
	                }
	            }
	            latch.countDown(); // Se�al de que el hilo ha terminado
	        });

	        // Segundo hilo para la segunda mitad de la multiplicaci�n
	        executor.submit(() -> {
	            for (int i1 = size / 2; i1 < size; i1 += bsize) {
	                for (int j1 = 0; j1 < size; j1 += bsize) {
	                    for (int k1 = 0; k1 < size; k1 += bsize) {
	                        for (int i = i1; i < Math.min(i1 + bsize, size); i++) {
	                            for (int j = j1; j < Math.min(j1 + bsize, size); j++) {
	                                for (int k = k1; k < Math.min(k1 + bsize, size); k++) {
	                                    A[i][j] += B[i][k] * C[k][j];
	                                }
	                            }
	                        }
	                    }
	                }
	            }
	            latch.countDown(); // Se�al de que el hilo ha terminado
	        });

	        try {
	            latch.await(); // Espera a que ambos hilos terminen
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        // Apagar el ExecutorService
	        executor.shutdown();
	        return A;
	 }

	 public static void main(String[] args) {

		//Varibles externas necesarias
		Persistencia M1;
		Persistencia M2;
		Persistencia tiempoEjecucion;
		HashMap<String, Long> tiempos = new HashMap<>();

		
		try {
			M1 = Persistencia.fromXML("src/Matrices de prueba/Caso 1/matriz 1.xml");
			M2 = Persistencia.fromXML("src/Matrices de prueba/Caso 1/matriz 2.xml");
			int[][] m1 = M1.getMatriz();
			int[][] m2 = M2.getMatriz();
			int m1l = m1.length;
			int m2l = m2.length;
			int p = m2[0].length;
			int[][] m3 = new int[m1l][p];

			//Calcular el tiempo de ejecucion
			long startTimeNano = System.nanoTime();
			int[][] resultado = parallelBlockMatrixMultiply(m3, m1, m2, m1l, 1);
			long endTimeNano=System.nanoTime();
			long finalTime = endTimeNano-startTimeNano;

			//Guardar el tiempo de ejecucion
			tiempos.put("III-V EnhancedParallelBlock", finalTime);
			tiempoEjecucion = new Persistencia(tiempos);
			tiempoEjecucion.toXML("src/Tiempos de ejecucion/Tiempos-Caso 1.xml");
			
			tiempos.put("IVII-V EnhancedParallelBlock", finalTime);
			tiempoEjecucion = new Persistencia(tiempos);
			tiempoEjecucion.toXML("src/Tiempos de ejecucion/Tiempos-Caso 1.xml");

			//Mostrar el tiempo
			System.out.println("Tiempo que tarda el algoritmo III-V EnhancedParallelBlock es: "+finalTime+" nanosegundos");
			//imprimirMatriz(resultado);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

			
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

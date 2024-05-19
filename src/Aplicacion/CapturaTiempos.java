package Aplicacion;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.bind.JAXBException;

import Algortimos.EnhancedParallelBlockCuatroCinco;
import Algortimos.EnhancedParallelBlockTresCinco;
import Algortimos.NaivLoopUnrollingFour;
import Algortimos.NaivLoopUnrollingTwo;
import Algortimos.NaivOnArray;
import Algortimos.ParallelBlockCincoCuatro;
import Algortimos.ParallelBlockCuatroCuatro;
import Algortimos.ParallelBlockTresCuatro;
import Algortimos.SequentialBlockCincoTres;
import Algortimos.SequentialBlockCuatroTres;
import Algortimos.SequentialBlockTresTres;
import Algortimos.StrassenNaiv;
import Algortimos.StrassenWinograd;
import Algortimos.WinogradOriginal;
import Algortimos.WinogradeScaled;
import Conexion.Persistencia;

public class CapturaTiempos {
    public static void main(String[] args) {
        

        //Varibles externas necesarias
		Persistencia M1;
		Persistencia M2;
		Persistencia tiempoEjecucion;
		HashMap<String, Long> tiempos = new HashMap<>();
        String rutaTiemposEjecucion = "src/Tiempos de ejecucion/Tiempos-Caso 8.xml";


        try {
            //rutas
            M1 = Persistencia.fromXML("src/Matrices de prueba/Caso 8/matriz 1.xml");
            M2 = Persistencia.fromXML("src/Matrices de prueba/Caso 8/matriz 2.xml");

            //variables
            int[][] m1 = M1.getMatriz();
            int[][] m2 = M2.getMatriz();
            int m1l = m1.length;
            int m2l = m2.length;
            int p = m2[0].length;
            int[][] m3 = new int[m1l][p];
            long finalTime, endTimeNano, startTimeNano;

            //llamados a los algoritmos 
            //1.--------------------------------------------------------------------------------------------------------------
            EnhancedParallelBlockCuatroCinco algoritmo1 = new EnhancedParallelBlockCuatroCinco();
            
            startTimeNano = System.nanoTime();
            int[][] resultado1 = algoritmo1.parallelBlockMatrixMultiply(m3, m1, m2, m1l, 1);
            endTimeNano=System.nanoTime();
		    finalTime = endTimeNano-startTimeNano;

			//Guardar el tiempo de ejecucion
			tiempos.put("IV-V EnhancedParallelBlock", finalTime);
			tiempoEjecucion = new Persistencia(tiempos);
			tiempoEjecucion.toXML(rutaTiemposEjecucion);

            System.out.println("el tiempo de ejecucion del IV-V EnhancedParallelBlock es: "+finalTime+" nanosegundos");

            //2.--------------------------------------------------------------------------------------------------------------
            EnhancedParallelBlockTresCinco algoritmo2 = new EnhancedParallelBlockTresCinco();

            startTimeNano = System.nanoTime();
            int[][] resultado2 = algoritmo2.parallelBlockMatrixMultiply(m3, m1, m2, m1l, 1);;
            endTimeNano=System.nanoTime();
		    finalTime = endTimeNano-startTimeNano;

			//Guardar el tiempo de ejecucion
			tiempos.put("III-V EnhancedParallelBlock", finalTime);
			tiempoEjecucion = new Persistencia(tiempos);
			tiempoEjecucion.toXML(rutaTiemposEjecucion);

            System.out.println("el tiempo de ejecucion del III-V EnhancedParallelBlock es: "+finalTime+" nanosegundos");

            //3.-------------------------------------------------------------------------------------------------------------- 
            NaivLoopUnrollingFour algoritmo3 = new NaivLoopUnrollingFour();
            
            startTimeNano = System.nanoTime();
            int[][] resultado3 = algoritmo3.naivLoopUnrollingFour(m1, m2, m3, m1l, m2l, p);
            endTimeNano=System.nanoTime();
		    finalTime = endTimeNano-startTimeNano;

			//Guardar el tiempo de ejecucion
			tiempos.put("IV naivLoopUnrolling", finalTime);
			tiempoEjecucion = new Persistencia(tiempos);
			tiempoEjecucion.toXML(rutaTiemposEjecucion);

            System.out.println("el tiempo de ejecucion del IV naivLoopUnrolling es: "+finalTime+" nanosegundos");

            //4.-------------------------------------------------------------------------------------------------------------- 
            NaivLoopUnrollingTwo algoritmo4 = new NaivLoopUnrollingTwo();
            
            startTimeNano = System.nanoTime();
            int[][] resultado4 = algoritmo4.naivLoopUnrollingTwo(m1, m2, m3, m1l, m2l, p);
            endTimeNano=System.nanoTime();
		    finalTime = endTimeNano-startTimeNano;

			//Guardar el tiempo de ejecucion
			tiempos.put("II naivLoopUnrolling", finalTime);
			tiempoEjecucion = new Persistencia(tiempos);
			tiempoEjecucion.toXML(rutaTiemposEjecucion);

            System.out.println("el tiempo de ejecucion del II naivLoopUnrolling es: "+finalTime+" nanosegundos");

            //5.--------------------------------------------------------------------------------------------------------------
            NaivOnArray algoritmo5 = new NaivOnArray();
            
            startTimeNano = System.nanoTime();
            int[][] resultado5 = algoritmo5.naivOnArray(m1, m2, m3, m1l, m2l, p);
            endTimeNano=System.nanoTime();
		    finalTime = endTimeNano-startTimeNano;

			//Guardar el tiempo de ejecucion
			tiempos.put("NaivOnArray", finalTime);
			tiempoEjecucion = new Persistencia(tiempos);
			tiempoEjecucion.toXML(rutaTiemposEjecucion);

            System.out.println("el tiempo de ejecucion del NaivOnArray es: "+finalTime+" nanosegundos");      
            
            //6.--------------------------------------------------------------------------------------------------------------
            ParallelBlockCincoCuatro algoritmo6 = new ParallelBlockCincoCuatro();
            
            startTimeNano = System.nanoTime();
            int[][] resultado6 = algoritmo6.multiply(m1, m2, (int) Math.sqrt(m1.length));
            endTimeNano=System.nanoTime();
		    finalTime = endTimeNano-startTimeNano;

			//Guardar el tiempo de ejecucion
			tiempos.put("V-IV ParallelBlock", finalTime);
			tiempoEjecucion = new Persistencia(tiempos);
			tiempoEjecucion.toXML(rutaTiemposEjecucion);

            System.out.println("el tiempo de ejecucion del V-IV ParallelBlock es: "+finalTime+" nanosegundos");

            //7.-------------------------------------------------------------------------------------------------------------- 
            ParallelBlockCuatroCuatro algoritmo7 = new ParallelBlockCuatroCuatro();
            
            startTimeNano = System.nanoTime();
            int[][] resultado7 = algoritmo7.multiply(m1, m2, (int) Math.sqrt(m1.length));
            endTimeNano=System.nanoTime();
		    finalTime = endTimeNano-startTimeNano;

			//Guardar el tiempo de ejecucion
			tiempos.put("IV-IV ParallelBlock", finalTime);
			tiempoEjecucion = new Persistencia(tiempos);
			tiempoEjecucion.toXML(rutaTiemposEjecucion);

            System.out.println("el tiempo de ejecucion del IV-IV ParallelBlock es: "+finalTime+" nanosegundos");

            //8.-------------------------------------------------------------------------------------------------------------- 
            ParallelBlockTresCuatro algoritmo8 = new ParallelBlockTresCuatro();
            
            startTimeNano = System.nanoTime();
            int[][] resultado8 = algoritmo8.multiply(m1, m2, (int) Math.sqrt(m1.length));
            endTimeNano=System.nanoTime();
		    finalTime = endTimeNano-startTimeNano;

			//Guardar el tiempo de ejecucion
			tiempos.put("III-IV ParallelBlock", finalTime);
			tiempoEjecucion = new Persistencia(tiempos);
			tiempoEjecucion.toXML(rutaTiemposEjecucion);

            System.out.println("el tiempo de ejecucion del III-IV ParallelBlock es: "+finalTime+" nanosegundos");

            //9.-------------------------------------------------------------------------------------------------------------- 
            SequentialBlockCincoTres algoritmo9 = new SequentialBlockCincoTres();
            
            startTimeNano = System.nanoTime();
            int[][] resultado9 = algoritmo9.multiply(m1, m2, (int) Math.sqrt(m1.length));
            endTimeNano=System.nanoTime();
		    finalTime = endTimeNano-startTimeNano;

			//Guardar el tiempo de ejecucion
			tiempos.put("V-III SequentialBlock", finalTime);
			tiempoEjecucion = new Persistencia(tiempos);
			tiempoEjecucion.toXML(rutaTiemposEjecucion);

            System.out.println("el tiempo de ejecucion del V-III SequentialBlock es: "+finalTime+" nanosegundos");


            //10.-------------------------------------------------------------------------------------------------------------
            SequentialBlockCuatroTres algoritmo10 = new SequentialBlockCuatroTres();
            
            startTimeNano = System.nanoTime();
            int[][] resultado10 = algoritmo10.multiply(m1, m2, (int) Math.sqrt(m1.length));
            endTimeNano=System.nanoTime();
		    finalTime = endTimeNano-startTimeNano;

			//Guardar el tiempo de ejecucion
			tiempos.put("IV-III SequentialBlock", finalTime);
			tiempoEjecucion = new Persistencia(tiempos);
			tiempoEjecucion.toXML(rutaTiemposEjecucion);

            System.out.println("el tiempo de ejecucion del IV-III SequentialBlock es: "+finalTime+" nanosegundos");

            //11.-------------------------------------------------------------------------------------------------------------
            SequentialBlockTresTres algoritmo11 = new SequentialBlockTresTres();
            
            startTimeNano = System.nanoTime();
            int[][] resultado11 = algoritmo11.multiply(m1, m2, (int) Math.sqrt(m1.length));
            endTimeNano=System.nanoTime();
		    finalTime = endTimeNano-startTimeNano;

			//Guardar el tiempo de ejecucion
			tiempos.put("III-III SequentialBlock", finalTime);
			tiempoEjecucion = new Persistencia(tiempos);
			tiempoEjecucion.toXML(rutaTiemposEjecucion);

            System.out.println("el tiempo de ejecucion del III-III SequentialBlock es: "+finalTime+" nanosegundos");

            //12.-------------------------------------------------------------------------------------------------------------
            StrassenNaiv algoritmo12 = new StrassenNaiv();
            
            startTimeNano = System.nanoTime();
            int[][] resultado12 = algoritmo12.strassenNaiv(m1, m2, m3, m1l, m2l, p);
            endTimeNano=System.nanoTime();
		    finalTime = endTimeNano-startTimeNano;

			//Guardar el tiempo de ejecucion
			tiempos.put("StrassenNaiv", finalTime);
			tiempoEjecucion = new Persistencia(tiempos);
			tiempoEjecucion.toXML(rutaTiemposEjecucion);

            System.out.println("el tiempo de ejecucion del StrassenNaiv es: "+finalTime+" nanosegundos");

            //13.-------------------------------------------------------------------------------------------------------------
            StrassenWinograd algoritmo13 = new StrassenWinograd();
            
            startTimeNano = System.nanoTime();
            int[][] resultado13 = algoritmo13.strassenWinograd(m1, m2, m3, m1l, m2l, p);
            endTimeNano=System.nanoTime();
		    finalTime = endTimeNano-startTimeNano;

			//Guardar el tiempo de ejecucion
			tiempos.put("StrassenWinograd", finalTime);
			tiempoEjecucion = new Persistencia(tiempos);
			tiempoEjecucion.toXML(rutaTiemposEjecucion);

            System.out.println("el tiempo de ejecucion del StrassenWinograd es: "+finalTime+" nanosegundos");

            //14.-------------------------------------------------------------------------------------------------------------
            WinogradeScaled algoritmo14 = new WinogradeScaled();

    
            startTimeNano = System.nanoTime();
            int[][] resultado14 = algoritmo14.winogradScaled(m1, m2, m3, m1l, m2l, p);
            endTimeNano=System.nanoTime();
		    finalTime = endTimeNano-startTimeNano;

			//Guardar el tiempo de ejecucion
			tiempos.put("WinogradeScaled", finalTime);
			tiempoEjecucion = new Persistencia(tiempos);
			tiempoEjecucion.toXML(rutaTiemposEjecucion);

            System.out.println("el tiempo de ejecucion del WinogradeScaled es: "+finalTime+" nanosegundos");

            //15.-------------------------------------------------------------------------------------------------------------
            WinogradOriginal algoritmo15 = new WinogradOriginal();
    
            startTimeNano = System.nanoTime();
            int[][] resultado15 = algoritmo15.winogradOriginal(m1, m2, m3, m1l, m2l, p);
            endTimeNano=System.nanoTime();
		    finalTime = endTimeNano-startTimeNano;

			//Guardar el tiempo de ejecucion
			tiempos.put("WinogradOriginal", finalTime);
			tiempoEjecucion = new Persistencia(tiempos);
			tiempoEjecucion.toXML(rutaTiemposEjecucion);

            System.out.println("el tiempo de ejecucion del WinogradOriginal es: "+finalTime+" nanosegundos");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}

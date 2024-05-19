package Conexion;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Persistencia {

	private static final Persistencia PersistentArray = null;

	private int[][] matriz;
	private HashMap<String, Long> tiempos;

	// Constructor vacio requerido por JAXB
	public Persistencia() {
	}

	public Persistencia(int[][] matriz) {
		this.matriz = matriz;
	}

	public Persistencia(HashMap<String, Long> tiempos) {
		this.tiempos = tiempos;
	}

	public int[][] getMatriz() {
		return this.matriz;
	}

	public void setMatriz(int[][] matriz) {
		this.matriz = matriz;
	}

	public  HashMap<String, Long> getTiempos() {
        return tiempos;
    }

    public void setTiempos( HashMap<String, Long> tiempos) {
        this.tiempos = tiempos;
    }

	// Metodo para serializar el objeto a XML
	public void toXML(String filePath) throws JAXBException, IOException {
		JAXBContext context = JAXBContext.newInstance(Persistencia.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(this, new FileWriter(filePath));
	}

	// Metodo estatico para deserializar el XML a objeto
	public static Persistencia fromXML(String filePath) throws JAXBException, FileNotFoundException {
		JAXBContext context = JAXBContext.newInstance(Persistencia.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return (Persistencia) unmarshaller.unmarshal(new FileReader(filePath));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int [][] matriz = generarMatriz(2048);//para generar una nueva matriz aleatoria

		try {
			// Serializar el arreglo a XML
			Persistencia persistentArray = new Persistencia(matriz);
			persistentArray.toXML("src/Matrices de prueba/Caso 8/matriz 2.xml");

			// Deserializar el XML de vuelta al arreglo
			/*Persistencia newArray = Persistencia.fromXML("src/Matrices de prueba/Caso 1/matriz 2.xml");
			int[][] loadedM = newArray.getMatriz();
			imprimirMatriz(loadedM);*/
		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}
	}

	public static int[][] generarMatriz(int tam) {
		int[][] matriz = new int[tam][tam];
		Random aleatorio = new Random();

		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				matriz[i][j] = aleatorio.nextInt(999999) + 1000000;
			}
		}
		return matriz;
	}


	public static void imprimirMatriz (int[][] matriz){
		String mensaje = "";
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				mensaje += matriz[i][j] + " ";
			}
			mensaje  +="\n";
		}
		System.out.println(mensaje);
	}
}

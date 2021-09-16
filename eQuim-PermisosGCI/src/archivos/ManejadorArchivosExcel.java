package archivos;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class ManejadorArchivosExcel {

	public static String[] leerArchivoExcel(String rutaEntrada, String nombreArchivo) {

        // ruta de entrada.
		String archivo = rutaEntrada + nombreArchivo;

		// Creamos la lista resultado.
        ArrayList<String> listaResultado = new ArrayList<>();

        try {
            // Abrir el archivo. 
            FileInputStream inputStream = new FileInputStream(new File(archivo));
            // Leer el libro de trabajo.
            XSSFWorkbook libro = new XSSFWorkbook(inputStream);
            // Leer la hoja 0 del libro.
            XSSFSheet hoja = libro.getSheetAt(0);
			// Iterador de filas.
            Iterator<Row> iterador = hoja.iterator();
			// Determinar la cantidad de columnas para definir tamaño del array de fila.
			int cantColumnas = hoja.getRow(0).getPhysicalNumberOfCells();
            // variables para linea.
            String linea = "";
            Integer nroCampo = 0;
			// Comenzamos la iteración fila a fila.
            DataFormatter formatoDatos = new DataFormatter();
            while ( iterador.hasNext() ) {
                Row sigFila = iterador.next();
                // Preparo el iterador de celdas.
                Iterator<Cell> iteradorCelda = sigFila.cellIterator();
				// variables para linea.
                linea = "";
                nroCampo = 0;
                // Iteración de celdas.
				while(iteradorCelda.hasNext()) {
                    Cell celda = iteradorCelda.next();
                    // Lee el contenido de la celda.
                    String contenidoCelda = formatoDatos.formatCellValue(celda);
                    System.out.println("Celda " + nroCampo + ": " + contenidoCelda);
                    // Guardamos el contenido de la celda en la lista de linea.
                    if ( iteradorCelda.hasNext() ) {
                        linea = linea + contenidoCelda.toString() + ";";
                    } else {
                        linea = linea + contenidoCelda.toString();
                    }
                    //System.out.println(linea[nroCelda]);
                    //System.out.println("celda " + nroCelda + ": " + contenidoCelda);
					nroCampo++;
                }
                // Guardamos la lista de linea en la lista resultado.
                System.out.println("Guardamos linea: " + linea);
				listaResultado.add(linea);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
		// retornamos la lista resultado.
        //System.out.println("Elementos de la lista: " + listaResultado.size());
		return listaResultado.toArray(new String[0]);
    }
}

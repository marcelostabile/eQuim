package archivos;
    
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class ManejadorArchivosExcel {

	public static String[] leerArchivoExcel(String nombreArchivo) {

        // ruta de entrada.
        String rutaEntrada = "src\\entrada\\";
		String archivo = rutaEntrada + nombreArchivo;

		// Creamos la lista resultado.
		ArrayList<String[]> listaResultado = new ArrayList<>();

        try { 
            FileInputStream inputStream = new FileInputStream(new File(archivo));

			// Libro de trabajo y hoja.
            XSSFWorkbook libro = new XSSFWorkbook(inputStream);
            XSSFSheet hoja = libro.getSheetAt(0);

			// Iterador de filas.
            Iterator<Row> iterador = hoja.iterator();

			// Cálculo la cantidad de columnas para definir tamaño del array de fila.
			int cantColumnas = hoja.getRow(0).getPhysicalNumberOfCells();

            DataFormatter formatoDatos = new DataFormatter();

			String[] linea = new String[cantColumnas];
			// Iteración de filas.
            while (iterador.hasNext()) {
                Row sigFila = iterador.next();
                Iterator<Cell> iteradorCelda = sigFila.cellIterator();
				Integer nroCelda = 0;
                // Iteración de celdas.
				while(iteradorCelda.hasNext()) {
                    Cell celda = iteradorCelda.next();
                    String contenidoCelda = formatoDatos.formatCellValue(celda);
					linea[nroCelda] = contenidoCelda;
                    //System.out.println("celda " + nroCelda + ": " + contenidoCelda);
					nroCelda++;
                }
				listaResultado.add(linea);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		// retornamos la lista resultado.
		return listaResultado.toArray(new String[0]);
    }
}

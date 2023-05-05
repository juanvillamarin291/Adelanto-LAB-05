import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;

public class DeportistasColombianos {
    
    private static final String FILE_PATH = "C:\\Users\\Estudiante\\Downloads\\Deportistas Colombianos.csv";

    public static void main(String[] args) throws IOException {
        List<Deportista> deportistas = leerArchivoCSV(FILE_PATH);

        // Crear menu para seleccionar columna
        String[] opciones = {"Vigencia", "Facultad", "Título Profesional", "Género", "Metodología", "Sede"};
        String columnaSeleccionada = (String) JOptionPane.showInputDialog(null, "Seleccione una columna para generar la gráfica", "Seleccionar columna", JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

        CategoryDataset dataset = crearDataset(deportistas, columnaSeleccionada);
        JFreeChart chart = crearGrafica(dataset, columnaSeleccionada);
        ChartFrame frame = new ChartFrame("Deportistas Colombianos", chart);
        frame.pack();
        frame.setVisible(true);
    }

   private static List<Deportista> leerArchivoCSV(String filePath) throws IOException {
    List<Deportista> deportistas = new ArrayList<>();
    try (FileReader fileReader = new FileReader(new File(filePath));
         CSVParser csvParser = CSVFormat.DEFAULT.withHeader().parse(fileReader)) {
        for (CSVRecord record : csvParser) {
            String vigencia = record.get("VIGENCIA");
            String facultad = record.get("FACULTAD");
            String titulo = record.get("TI�TULO PROFESIONAL");
            String genero = record.get("GENERO");
            String metodologia = record.get("METODOLOGIA");
            String sede = record.get("SEDE");
        }
    }
    return deportistas;
}

    private static CategoryDataset crearDataset(List<Deportista> deportistas, String columnaSeleccionada) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Deportista deportista : deportistas) {
            String valor = "";
            switch (columnaSeleccionada) {
                case "Vigencia":
                    valor = deportista.getVigencia();
                    break;
                case "Facultad":
                    valor = deportista.getFacultad();
                    break;
                case "Título Profesional":
                    valor = deportista.getTitulo();
                    break;
                case "Género":
                    valor = deportista.getGenero();
                    break;
                case "Metodología":
                    valor = deportista.getMetodologia();
                    break;
                case "Sede":
                    valor = deportista.getSede();
                    break;
            }
            dataset.addValue(1, valor, deportista.getFacultad() + " (" + deportista.getSede() + ")");
        }
        return dataset;
    }

    private static JFreeChart crearGrafica(CategoryDataset dataset, String columnaSeleccionada) {
        String tituloGrafica = "Número de deportistas por blabla";
            switch (columnaSeleccionada) {
        case "Vigencia":
            tituloGrafica = "Deportistas Colombianos por Vigencia";
            break;
        case "Facultad":
            tituloGrafica = "Deportistas Colombianos por Facultad";
            break;
        case "Título Profesional":
            tituloGrafica = "Deportistas Colombianos por Título Profesional";
            break;
        case "Género":
            tituloGrafica = "Deportistas Colombianos por Género";
            break;
        case "Metodología":
            tituloGrafica = "Deportistas Colombianos por Metodología";
            break;
        case "Sede":
            tituloGrafica = "Deportistas Colombianos por Sede";
            break;
        default:
            tituloGrafica = "Deportistas Colombianos";
    }
    JFreeChart chart = ChartFactory.createBarChart(
            tituloGrafica,
            columnaSeleccionada,
            "Cantidad de deportistas",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
    );
    return chart;
}

    private static void Deportista(String vigencia, String facultad, String titulo, String genero, String metodologia, String sede) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

class Deportista {
private String vigencia;
private String facultad;
private String titulo;
private String genero;
private String metodologia;
private String sede;

public Deportista(String vigencia, String facultad, String titulo, String genero, String metodologia, String sede) {
    this.vigencia = vigencia;
    this.facultad = facultad;
    this.titulo = titulo;
    this.genero = genero;
    this.metodologia = metodologia;
    this.sede = sede;
}


public String getVigencia() {
    return vigencia;
}

public String getFacultad() {
    return facultad;
}

public String getTitulo() {
    return titulo;
}

public String getGenero() {
    return genero;
}

public String getMetodologia() {
    return metodologia;
}

public String getSede() {
    return sede;
}

}
}




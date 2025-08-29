import java.io.*;

/**
 * Clase TrialGroup para almacenar y analizar datos de cada grupo del ensayo
 */
class TrialGroup {
    // Variables de instancia
    private String fileName;
    private int count;
    private int sum;
    private int sumOfSquares;

    /**
     * Constructor que inicializa un objeto TrialGroup
     * @param fileName Nombre del archivo que contiene los datos del grupo
     */
    public TrialGroup(String fileName) throws IOException {
        this.fileName = fileName;
        this.count = 0;
        this.sum = 0;
        this.sumOfSquares = 0;

        // Leer archivo y calcular estadísticas
        leerDatosDelArchivo(fileName);
    }

    /**
     * Lee datos del archivo y calcula el recuento, suma y suma de cuadrados
     */
    private void leerDatosDelArchivo(String fileName) throws IOException {
        try (BufferedReader lector = new BufferedReader(new FileReader(fileName))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                linea = linea.trim();
                if (!linea.isEmpty()) {
                    try {
                        int valor = Integer.parseInt(linea);
                        count++;
                        sum += valor;
                        sumOfSquares += valor * valor;
                    } catch (NumberFormatException e) {
                        System.err.println("Advertencia: Número inválido en archivo " + fileName + ": " + linea);
                    }
                }
            }
        }
    }

    /**
     * Devuelve el promedio de los valores en el grupo
     */
    public double getAverage() {
        if (count == 0) return 0.0;
        return (double) sum / count;
    }

    /**
     * Devuelve la desviación estándar de los valores en el grupo
     */
    public double getStandardDeviation() {
        if (count == 0) return 0.0;

        double promedio = getAverage();
        double promedioDeCuadrados = (double) sumOfSquares / count;
        double cuadradoDelPromedio = promedio * promedio;
        double varianza = promedioDeCuadrados - cuadradoDelPromedio;

        if (varianza < 0) varianza = 0;

        return Math.sqrt(varianza);
    }

    /**
     * Devuelve el nombre del archivo
     */
    public String getFileName() {
        return fileName;
    }
}
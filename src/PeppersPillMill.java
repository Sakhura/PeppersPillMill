import java.io.*;
import java.util.Scanner;

public class PeppersPillMill {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Obtener nombres de archivos de entrada
            System.out.println("Enter the names of the three input files:");
            System.out.print("File 1 (Control/Experimental/Placebo): ");
            String archivo1 = scanner.nextLine().trim();

            System.out.print("File 2 (Control/Experimental/Placebo): ");
            String archivo2 = scanner.nextLine().trim();

            System.out.print("File 3 (Control/Experimental/Placebo): ");
            String archivo3 = scanner.nextLine().trim();

            // Obtener nombre del archivo de salida
            System.out.print("Enter the name of the output file: ");
            String archivoSalida = scanner.nextLine().trim();

            // Crear objetos TrialGroup para cada archivo
            TrialGroup grupo1 = new TrialGroup(archivo1);
            TrialGroup grupo2 = new TrialGroup(archivo2);
            TrialGroup grupo3 = new TrialGroup(archivo3);

            // Escribir resultados al archivo de salida
            escribirResultados(archivoSalida, grupo1, grupo2, grupo3);

            System.out.println("\nAnalysis complete! Results written to " + archivoSalida);

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void escribirResultados(String archivoSalida, TrialGroup grupo1,
                                           TrialGroup grupo2, TrialGroup grupo3) throws IOException {

        try (PrintWriter escritor = new PrintWriter(new FileWriter(archivoSalida))) {
            escritor.println("Statistics:");

            escritor.println(grupo1.getFileName() + ": " + grupo1.getAverage() + " " + grupo1.getStandardDeviation());
            escritor.println(grupo2.getFileName() + ": " + grupo2.getAverage() + " " + grupo2.getStandardDeviation());
            escritor.println(grupo3.getFileName() + ": " + grupo3.getAverage() + " " + grupo3.getStandardDeviation());

            escritor.println("Results:");

            boolean significativo12 = esDiferenciaSignificativa(grupo1, grupo2);
            escritor.println(grupo1.getFileName() + " vs. " + grupo2.getFileName() + " : " + significativo12);

            boolean significativo13 = esDiferenciaSignificativa(grupo1, grupo3);
            escritor.println(grupo1.getFileName() + " vs. " + grupo3.getFileName() + " : " + significativo13);

            boolean significativo23 = esDiferenciaSignificativa(grupo2, grupo3);
            escritor.println(grupo2.getFileName() + " vs. " + grupo3.getFileName() + " : " + significativo23);
        }
    }

    private static boolean esDiferenciaSignificativa(TrialGroup grupo1, TrialGroup grupo2) {
        double promedio1 = grupo1.getAverage();
        double promedio2 = grupo2.getAverage();
        double desviacion1 = grupo1.getStandardDeviation();
        double desviacion2 = grupo2.getStandardDeviation();

        double diferencia = Math.abs(promedio1 - promedio2);

        return (diferencia > desviacion1) && (diferencia > desviacion2);
    }
}
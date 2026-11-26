import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n===== TALLER DE ALGORITMOS MODULARES =====");
            System.out.println("1. Control de inventario de cafeteria");
            System.out.println("2. Control de parqueadero residencial");
            System.out.println("3. Control de produccion de fabrica");
            System.out.println("0. Salir");
            opcion = leerOpcion(sc);
            switch (opcion) {
                case 1: Inventario.ejecutar(sc); break;
                case 2: Parqueadero.ejecutar(sc); break;
                case 3: Produccion.ejecutar(sc); break;
                case 0: System.out.println("Hasta pronto."); break;
                default: System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);
        sc.close();
    }

    private static int leerOpcion(Scanner sc) {
        while (true) {
            System.out.print("Seleccione una opcion: ");
            try { return Integer.parseInt(sc.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Digite un numero entero."); }
        }
    }
}

import java.util.Scanner;

public class Produccion {
    private static final int MAX_OPERARIOS = 10;
    private static final int DIAS = 5;

    public static void ejecutar(Scanner sc) {
        String[] codigos = new String[MAX_OPERARIOS];
        String[] nombres = new String[MAX_OPERARIOS];
        int[][] produccion = new int[MAX_OPERARIOS][DIAS];
        int total = 0, op;
        do {
            System.out.println("\n--- CONTROL DE PRODUCCION ---");
            System.out.println("1. Registrar operario e ingresar produccion semanal\n2. Consultar produccion por operario\n3. Mostrar mejor operario\n4. Mostrar promedio general\n5. Detectar dias de baja produccion\n0. Volver al menu principal");
            op = leerEntero(sc, "Opcion: ", 0, 5);
            switch (op) {
                case 1:
                    if (total == MAX_OPERARIOS) { System.out.println("Ya se registraron los 10 operarios."); break; }
                    String codigo = leerTexto(sc, "Codigo del operario: ");
                    if (buscar(codigo, codigos, total) != -1) { System.out.println("Ese codigo ya esta registrado."); break; }
                    codigos[total] = codigo;
                    nombres[total] = leerTexto(sc, "Nombre: ");
                    for (int d = 0; d < DIAS; d++) produccion[total][d] = leerEntero(sc, "Produccion del dia " + (d + 1) + ": ", 0, 1000000);
                    total++;
                    System.out.println("Registro del operario completado.");
                    break;
                case 2:
                    String buscarCodigo = leerTexto(sc, "Codigo a consultar: ");
                    int pos = buscar(buscarCodigo, codigos, total);
                    if (pos == -1) System.out.println("Operario no encontrado.");
                    else { int suma = 0; System.out.println("Operario: " + nombres[pos]); for (int d = 0; d < DIAS; d++) { System.out.println("Dia " + (d + 1) + ": " + produccion[pos][d]); suma += produccion[pos][d]; } System.out.println("Total semanal: " + suma); }
                    break;
                case 3:
                    if (total == 0) { System.out.println("No hay operarios registrados."); break; }
                    int mejor = 0;
                    for (int i = 1; i < total; i++) if (sumar(produccion[i]) > sumar(produccion[mejor])) mejor = i;
                    System.out.println("Mejor operario: " + nombres[mejor] + " | Codigo: " + codigos[mejor] + " | Produccion: " + sumar(produccion[mejor]));
                    break;
                case 4:
                    if (total == 0) { System.out.println("No hay datos para calcular el promedio."); break; }
                    int acumulado = 0;
                    for (int i = 0; i < total; i++) acumulado += sumar(produccion[i]);
                    System.out.printf("Promedio general diario por operario: %.2f unidades%n", (double) acumulado / (total * DIAS));
                    break;
                case 5:
                    if (total == 0) { System.out.println("No hay datos registrados."); break; }
                    int[] porDia = new int[DIAS];
                    for (int d = 0; d < DIAS; d++) for (int i = 0; i < total; i++) porDia[d] += produccion[i][d];
                    double promedio = 0;
                    for (int d = 0; d < DIAS; d++) promedio += porDia[d];
                    promedio /= DIAS;
                    System.out.printf("Promedio de produccion diaria del grupo: %.2f%n", promedio);
                    boolean baja = false;
                    for (int d = 0; d < DIAS; d++) if (porDia[d] < promedio) { System.out.println("Dia " + (d + 1) + " presenta produccion baja: " + porDia[d]); baja = true; }
                    if (!baja) System.out.println("No se detectaron dias por debajo del promedio.");
                    break;
            }
        } while (op != 0);
    }

    private static int sumar(int[] valores) { int suma = 0; for (int v : valores) suma += v; return suma; }
    private static int buscar(String codigo, String[] codigos, int total) { for (int i = 0; i < total; i++) if (codigos[i].equalsIgnoreCase(codigo)) return i; return -1; }
    private static String leerTexto(Scanner sc, String msg) { String s; do { System.out.print(msg); s = sc.nextLine().trim(); if (s.isEmpty()) System.out.println("El dato no puede quedar vacio."); } while (s.isEmpty()); return s; }
    private static int leerEntero(Scanner sc, String msg, int min, int max) { while (true) { System.out.print(msg); try { int n = Integer.parseInt(sc.nextLine().trim()); if (n >= min && n <= max) return n; } catch (NumberFormatException e) { } System.out.println("Ingrese un entero entre " + min + " y " + max + "."); } }
}

import java.util.Scanner;

public class Inventario {
    private static final int MAX = 20;

    public static void ejecutar(Scanner sc) {
        String[] codigos = new String[MAX], nombres = new String[MAX], categorias = new String[MAX];
        double[] precios = new double[MAX];
        int[] cantidades = new int[MAX];
        int total = 0;
        double ventasDia = 0;
        int op;
        do {
            System.out.println("\n--- INVENTARIO DE CAFETERIA ---");
            System.out.println("1. Registrar producto\n2. Realizar venta\n3. Mostrar agotados\n4. Mostrar total vendido\n5. Listar productos\n0. Volver al menu principal");
            op = leerEntero(sc, "Opcion: ", 0, 5);
            switch (op) {
                case 1:
                    if (total == MAX) { System.out.println("Se alcanzo el limite de 20 productos."); break; }
                    String codigo = leerTexto(sc, "Codigo: ");
                    if (buscar(codigo, codigos, total) != -1) { System.out.println("Ese codigo ya existe."); break; }
                    codigos[total] = codigo;
                    nombres[total] = leerTexto(sc, "Nombre: ");
                    precios[total] = leerDecimal(sc, "Precio unitario: ", 0.01);
                    cantidades[total] = leerEntero(sc, "Cantidad disponible: ", 0, 1000000);
                    System.out.println("Categoria: 1.Bebidas 2.Panaderia 3.Snacks");
                    int cat = leerEntero(sc, "Seleccione: ", 1, 3);
                    categorias[total] = cat == 1 ? "Bebidas" : cat == 2 ? "Panaderia" : "Snacks";
                    total++;
                    System.out.println("Producto registrado.");
                    break;
                case 2:
                    String codVenta = leerTexto(sc, "Codigo del producto: ");
                    int pos = buscar(codVenta, codigos, total);
                    if (pos == -1) { System.out.println("Producto no encontrado."); break; }
                    int unidades = leerEntero(sc, "Unidades a vender: ", 1, 1000000);
                    if (unidades > cantidades[pos]) { System.out.println("Stock insuficiente. Disponibles: " + cantidades[pos]); break; }
                    cantidades[pos] -= unidades;
                    double subtotal = unidades * precios[pos];
                    ventasDia += subtotal;
                    System.out.printf("Venta realizada. Total: $%.2f%n", subtotal);
                    break;
                case 3:
                    System.out.println("\nProductos agotados:");
                    boolean hay = false;
                    for (int i = 0; i < total; i++) if (cantidades[i] == 0) { System.out.println(codigos[i] + " - " + nombres[i]); hay = true; }
                    if (!hay) System.out.println("No hay productos agotados.");
                    break;
                case 4: System.out.printf("Total vendido en esta ejecucion: $%.2f%n", ventasDia); break;
                case 5:
                    if (total == 0) System.out.println("No hay productos registrados.");
                    for (int i = 0; i < total; i++) System.out.printf("%s | %s | %s | $%.2f | Stock: %d%n", codigos[i], nombres[i], categorias[i], precios[i], cantidades[i]);
                    break;
            }
        } while (op != 0);
    }

    private static int buscar(String codigo, String[] codigos, int total) {
        for (int i = 0; i < total; i++) if (codigos[i].equalsIgnoreCase(codigo)) return i;
        return -1;
    }
    private static String leerTexto(Scanner sc, String msg) {
        String s; do { System.out.print(msg); s = sc.nextLine().trim(); if (s.isEmpty()) System.out.println("El dato no puede quedar vacio."); } while (s.isEmpty()); return s;
    }
    private static int leerEntero(Scanner sc, String msg, int min, int max) {
        while (true) { System.out.print(msg); try { int n = Integer.parseInt(sc.nextLine().trim()); if (n >= min && n <= max) return n; } catch (NumberFormatException e) { } System.out.println("Ingrese un entero entre " + min + " y " + max + "."); }
    }
    private static double leerDecimal(Scanner sc, String msg, double min) {
        while (true) { System.out.print(msg); try { double n = Double.parseDouble(sc.nextLine().trim().replace(',', '.')); if (n >= min) return n; } catch (NumberFormatException e) { } System.out.println("Ingrese un valor numerico mayor o igual a " + min + "."); }
    }
}

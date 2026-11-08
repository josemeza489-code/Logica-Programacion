import java.util.Scanner;
import java.time.LocalTime;

public class Main {
    static final int MAX = 40;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Vectores para guardar la informacion de cada ingreso.
        String[] placas = new String[MAX];
        String[] torres = new String[MAX];
        String[] apartamentos = new String[MAX];
        String[] tipos = new String[MAX];
        boolean[] tieneSalida = new boolean[MAX];

        // Matriz: [fila][0] hora de ingreso, [fila][1] minuto de ingreso,
        // [fila][2] hora de salida, [fila][3] minuto de salida.
        int[][] horas = new int[MAX][4];

        int total = 0;
        int opcion;

        do {
            System.out.println("\n===== CONTROL DE PARQUEADERO =====");
            System.out.println("1. Registrar ingreso");
            System.out.println("2. Registrar salida");
            System.out.println("3. Consultar vehiculo por placa");
            System.out.println("4. Mostrar ingresos por tipo");
            System.out.println("5. Mostrar vehiculo con mayor permanencia");
            System.out.println("6. Salir");

            opcion = leerNumero(sc, "Seleccione una opcion: ", 1, 6);

            switch (opcion) {
                case 1:
                    total = registrarIngreso(sc, placas, torres, apartamentos,
                            tipos, horas, tieneSalida, total);
                    break;
                case 2:
                    registrarSalida(sc, placas, horas, tieneSalida, total);
                    break;
                case 3:
                    consultarVehiculo(sc, placas, torres, apartamentos,
                            tipos, horas, tieneSalida, total);
                    break;
                case 4:
                    mostrarIngresosPorTipo(tipos, tieneSalida, total);
                    break;
                case 5:
                    mostrarMayorPermanencia(placas, tipos, horas, tieneSalida, total);
                    break;
                case 6:
                    System.out.println("Programa finalizado.");
                    break;
            }
        } while (opcion != 6);

        sc.close();
    }

    // Lee un numero entero y valida que este dentro del rango indicado.
    public static int leerNumero(Scanner sc, String mensaje, int minimo, int maximo) {
        int numero;
        while (true) {
            System.out.print(mensaje);
            try {
                numero = Integer.parseInt(sc.nextLine().trim());
                if (numero >= minimo && numero <= maximo) {
                    return numero;
                }
                System.out.println("Digite un numero entre " + minimo + " y " + maximo + ".");
            } catch (NumberFormatException e) {
                System.out.println("Entrada no valida. Digite un numero entero.");
            }
        }
    }

    // Evita almacenar textos vacios.
    public static String leerTexto(Scanner sc, String mensaje) {
        String texto;
        do {
            System.out.print(mensaje);
            texto = sc.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("Este campo no puede quedar vacio.");
            }
        } while (texto.isEmpty());
        return texto;
    }

    public static int registrarIngreso(Scanner sc, String[] placas, String[] torres,
            String[] apartamentos, String[] tipos, int[][] horas,
            boolean[] tieneSalida, int total) {

        if (total == MAX) {
            System.out.println("Ya se registraron los 40 ingresos permitidos para hoy.");
            return total;
        }

        String placa = leerTexto(sc, "Placa del vehiculo: ").toUpperCase();

        // Una placa puede volver a ingresar solamente si ya tiene una salida registrada.
        if (buscarVehiculoActivo(placa, placas, tieneSalida, total) != -1) {
            System.out.println("No se puede registrar: esa placa ya se encuentra en el parqueadero.");
            return total;
        }

        String torre = leerTexto(sc, "Torre visitada: ");
        String apartamento = leerTexto(sc, "Apartamento: ");

        System.out.println("Tipo de vehiculo:");
        System.out.println("1. Carro");
        System.out.println("2. Moto");
        System.out.println("3. Bicicleta");
        int opcionTipo = leerNumero(sc, "Seleccione el tipo: ", 1, 3);

        String tipo;
        if (opcionTipo == 1) {
            tipo = "Carro";
        } else if (opcionTipo == 2) {
            tipo = "Moto";
        } else {
            tipo = "Bicicleta";
        }

        int hora = leerNumero(sc, "Hora de ingreso (0-23): ", 0, 23);
        int minuto = leerNumero(sc, "Minuto de ingreso (0-59): ", 0, 59);

        placas[total] = placa;
        torres[total] = torre;
        apartamentos[total] = apartamento;
        tipos[total] = tipo;
        horas[total][0] = hora;
        horas[total][1] = minuto;
        horas[total][2] = 0;
        horas[total][3] = 0;
        tieneSalida[total] = false;

        total++;
        System.out.println("Ingreso registrado correctamente.");
        return total;
    }

    public static void registrarSalida(Scanner sc, String[] placas,
            int[][] horas, boolean[] tieneSalida, int total) {

        String placa = leerTexto(sc, "Placa del vehiculo que sale: ").toUpperCase();
        int posicion = buscarVehiculoActivo(placa, placas, tieneSalida, total);

        if (posicion == -1) {
            System.out.println("No se encontro un ingreso activo con esa placa.");
            return;
        }

        int hora = leerNumero(sc, "Hora de salida (0-23): ", 0, 23);
        int minuto = leerNumero(sc, "Minuto de salida (0-59): ", 0, 59);

        horas[posicion][2] = hora;
        horas[posicion][3] = minuto;
        tieneSalida[posicion] = true;

        int permanencia = calcularPermanencia(
                horas[posicion][0], horas[posicion][1], hora, minuto);

        System.out.println("Salida registrada correctamente.");
        mostrarDuracion(permanencia);
    }

    // Busca una placa que todavia no tenga salida. Devuelve -1 si no existe.
    public static int buscarVehiculoActivo(String placa, String[] placas,
            boolean[] tieneSalida, int total) {
        for (int i = 0; i < total; i++) {
            if (placas[i].equalsIgnoreCase(placa) && !tieneSalida[i]) {
                return i;
            }
        }
        return -1;
    }

    public static void consultarVehiculo(Scanner sc, String[] placas,
            String[] torres, String[] apartamentos, String[] tipos,
            int[][] horas, boolean[] tieneSalida, int total) {

        String placa = leerTexto(sc, "Digite la placa que desea consultar: ").toUpperCase();
        int posicion = -1;

        // Se recorre desde el final para mostrar el registro mas reciente de esa placa.
        for (int i = total - 1; i >= 0; i--) {
            if (placas[i].equalsIgnoreCase(placa)) {
                posicion = i;
                break;
            }
        }

        if (posicion == -1) {
            System.out.println("No existe ningun registro para esa placa.");
            return;
        }

        System.out.println("\n--- DATOS DEL VEHICULO ---");
        System.out.println("Placa: " + placas[posicion]);
        System.out.println("Torre: " + torres[posicion]);
        System.out.println("Apartamento: " + apartamentos[posicion]);
        System.out.println("Tipo: " + tipos[posicion]);
        System.out.println("Hora de ingreso: "
                + formatoHora(horas[posicion][0], horas[posicion][1]));

        if (tieneSalida[posicion]) {
            System.out.println("Hora de salida: "
                    + formatoHora(horas[posicion][2], horas[posicion][3]));
            int permanencia = calcularPermanencia(horas[posicion][0], horas[posicion][1],
                    horas[posicion][2], horas[posicion][3]);
            System.out.print("Tiempo de permanencia: ");
            mostrarDuracion(permanencia);
        } else {
            System.out.println("Estado: el vehiculo sigue dentro del parqueadero.");
        }
    }

    public static void mostrarIngresosPorTipo(String[] tipos,
            boolean[] tieneSalida, int total) {
        int carros = 0;
        int motos = 0;
        int bicicletas = 0;
        int dentro = 0;

        for (int i = 0; i < total; i++) {
            if (tipos[i].equals("Carro")) {
                carros++;
            } else if (tipos[i].equals("Moto")) {
                motos++;
            } else if (tipos[i].equals("Bicicleta")) {
                bicicletas++;
            }

            if (!tieneSalida[i]) {
                dentro++;
            }
        }

        System.out.println("\n--- INGRESOS DEL DIA POR TIPO ---");
        System.out.println("Carros: " + carros);
        System.out.println("Motos: " + motos);
        System.out.println("Bicicletas: " + bicicletas);
        System.out.println("Total de ingresos: " + total);
        System.out.println("Vehiculos que siguen dentro: " + dentro);
    }

    public static void mostrarMayorPermanencia(String[] placas, String[] tipos,
            int[][] horas, boolean[] tieneSalida, int total) {

        if (total == 0) {
            System.out.println("Todavia no hay vehiculos registrados.");
            return;
        }

        LocalTime ahora = LocalTime.now();
        int minutosActuales = ahora.getHour() * 60 + ahora.getMinute();
        int mayor = -1;
        int posicionMayor = -1;

        for (int i = 0; i < total; i++) {
            int permanencia;

            if (tieneSalida[i]) {
                permanencia = calcularPermanencia(horas[i][0], horas[i][1],
                        horas[i][2], horas[i][3]);
            } else {
                permanencia = calcularPermanenciaHastaAhora(
                        horas[i][0], horas[i][1], minutosActuales);
            }

            if (permanencia > mayor) {
                mayor = permanencia;
                posicionMayor = i;
            }
        }

        System.out.println("\n--- MAYOR TIEMPO DE PERMANENCIA ---");
        System.out.println("Placa: " + placas[posicionMayor]);
        System.out.println("Tipo: " + tipos[posicionMayor]);
        System.out.print("Permanencia: ");
        mostrarDuracion(mayor);
        if (!tieneSalida[posicionMayor]) {
            System.out.println("El vehiculo todavia se encuentra dentro.");
        }
    }

    // Calcula la permanencia entre dos horas. Admite una salida despues de medianoche.
    public static int calcularPermanencia(int horaEntrada, int minutoEntrada,
            int horaSalida, int minutoSalida) {
        int entrada = horaEntrada * 60 + minutoEntrada;
        int salida = horaSalida * 60 + minutoSalida;

        if (salida < entrada) {
            salida += 24 * 60;
        }
        return salida - entrada;
    }

    // Calcula cuanto lleva dentro un vehiculo que aun no ha salido.
    public static int calcularPermanenciaHastaAhora(int horaEntrada,
            int minutoEntrada, int minutosActuales) {
        int entrada = horaEntrada * 60 + minutoEntrada;
        int ahora = minutosActuales;

        if (ahora < entrada) {
            ahora += 24 * 60;
        }
        return ahora - entrada;
    }

    public static String formatoHora(int hora, int minuto) {
        return String.format("%02d:%02d", hora, minuto);
    }

    public static void mostrarDuracion(int minutos) {
        int horas = minutos / 60;
        int minutosRestantes = minutos % 60;
        System.out.println(horas + " hora(s) y " + minutosRestantes + " minuto(s).");
    }
}

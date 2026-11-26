# Taller de algoritmos modulares

## Casos seleccionados
Se desarrollaron los casos **2, 4 y 5** del taller:
1. Control de inventario para una cafeteria universitaria.
2. Control de parqueadero para conjunto residencial.
3. Control de produccion para una fabrica de empaques.

## Integrantes
- José Miguel Meza Mercado
- Juan David Figueroa
- Xavier Echeverrí



## Organización de archivos
- `Main.java`: contiene únicamente el menú principal y llama al módulo seleccionado.
- `Inventario.java`: contiene el módulo del caso 2.
- `Parqueadero.java`: contiene el módulo del caso 4.
- `Produccion.java`: contiene el módulo del caso 5.

Cada módulo tiene su propio menú y regresa al menú principal al elegir la opción 0. Se comparte un único `Scanner` para leer los datos.

## Análisis general del problema
El taller solicita escoger tres casos y permitir que el usuario elija cuál ejecutar desde un menú principal. Para resolverlo, cada caso se separó en un archivo Java independiente. El método `main` solo muestra el menú, recibe la opción y ejecuta el módulo correspondiente.

### Caso 2. Inventario de cafetería
**Entradas:** código, nombre, precio, cantidad, categoría, código del producto vendido y unidades a vender.

**Procesos:** registrar hasta 20 productos, comprobar que el código no esté repetido, buscar productos, verificar existencias antes de vender, descontar unidades y acumular el valor de las ventas.

**Salidas:** confirmación de registro o venta, inventario actualizado, productos agotados y total vendido durante la ejecución.

**Validaciones:** código único, campos de texto no vacíos, precio positivo, cantidad no negativa y stock suficiente para realizar una venta.

### Caso 4. Parqueadero residencial
**Entradas:** placa, torre, apartamento, tipo de vehículo y horas de ingreso y salida.

**Procesos:** almacenar hasta 40 ingresos, comprobar que una placa no tenga otro ingreso activo, registrar salidas, calcular permanencia y contar vehículos por tipo.

**Salidas:** confirmación de ingreso o salida, consulta de vehículo, tiempo de permanencia, cantidades por tipo y vehículo con mayor permanencia.

**Validaciones:** límite de 40 registros, placa no repetida mientras siga dentro, hora entre 0 y 23 y minuto entre 0 y 59.

### Caso 5. Producción de fábrica
**Entradas:** código y nombre de hasta 10 operarios, más la producción de cada uno durante cinco días.

**Procesos:** guardar los datos en vectores y una matriz de 10 filas por 5 columnas, sumar la producción semanal, comparar operarios y calcular el promedio diario del grupo.

**Salidas:** producción diaria y semanal por operario, mejor operario, promedio general y días cuya producción queda por debajo del promedio diario.

**Validaciones:** código único, datos de texto no vacíos y producción diaria igual o mayor que cero.

## Estructuras utilizadas
- **Variables:** contadores, opciones, acumuladores y resultados.
- **Condicionales:** validaciones, selección de opciones y clasificación de resultados.
- **Ciclos:** menús repetitivos y recorridos de vectores y matrices.
- **Vectores:** datos de productos, vehículos y operarios.
- **Matrices:** horarios del parqueadero y producción de los cinco días.
- **Funciones y procedimientos:** métodos separados para buscar, validar, calcular y mostrar información.

No se crean objetos propios ni se utilizan constructores, herencia o atributos de instancia. Java requiere que el código esté dentro de clases, pero la solución utiliza métodos `static` y arreglos con un enfoque procedural.

## Pruebas de escritorio

### Inventario
| Prueba | Datos | Resultado esperado |
|---|---|---|
| Registrar producto | P01, Agua, precio 2500, stock 8, Bebidas | Producto registrado |
| Venta válida | P01, 3 unidades | Stock queda en 5 y venta suma $7500 |
| Venta sin stock | P01, 7 unidades cuando quedan 5 | Venta rechazada; stock permanece en 5 |
| Producto agotado | Registrar stock 0 | Aparece en la lista de agotados |

### Parqueadero
| Prueba | Datos | Resultado esperado |
|---|---|---|
| Ingreso | ABC123, carro, 08:30 | Ingreso registrado |
| Placa duplicada | ABC123 intenta entrar sin salida | Ingreso rechazado |
| Salida | ABC123 sale a las 10:15 | Permanencia de 1 hora y 45 minutos |
| Reingreso | ABC123 vuelve después de salir | Nuevo ingreso permitido |

### Producción
| Prueba | Datos | Resultado esperado |
|---|---|---|
| Registrar operario | OP01, Ana, 10, 12, 8, 10, 10 | Total semanal: 50 |
| Producción negativa | Ingresar -3 en un día | Dato rechazado y se solicita nuevamente |
| Mejor operario | OP01 total 50; OP02 total 60 | Se identifica OP02 |
| Promedio general | Dos operarios con 50 y 60 unidades semanales | Promedio diario por operario: 11 |


**Importante:** el proyecto no puede publicar un repositorio en la cuenta del grupo sin autorización y acceso a esa cuenta. El grupo debe crear el repositorio y subir estos archivos.

# Sistema de control de parqueadero residencial

## Descripcion
Programa de consola desarrollado en Java para registrar hasta 40 ingresos de vehiculos durante el dia en un conjunto residencial.

## Integrantes
- Estudiante 1: Jose Miguel Meza
- Estudiante 2: Juan David Figueroa
- Estudiante 3: Xavier

Completar esta lista con los integrantes reales del grupo antes de entregar.

## Analisis del problema

### Entradas
- Placa del vehiculo.
- Torre que visita.
- Numero de apartamento.
- Tipo de vehiculo: carro, moto o bicicleta.
- Hora y minuto de ingreso.
- Hora y minuto de salida.
- Opcion seleccionada en el menu.

### Procesos
1. Verificar que no se hayan registrado 40 ingresos.
2. Revisar que la placa no tenga otro ingreso sin salida registrada.
3. Guardar los datos del vehiculo en vectores y su horario en una matriz.
4. Al registrar la salida, buscar la placa que se encuentra activa y guardar la hora de salida.
5. Calcular la permanencia en minutos y convertirla a horas y minutos.
6. Recorrer los registros para contar los ingresos por tipo.
7. Comparar las permanencias para identificar el registro con mayor tiempo.

### Salidas
- Confirmacion o rechazo del ingreso.
- Confirmacion de salida y tiempo de permanencia.
- Datos del vehiculo consultado.
- Cantidad de ingresos de carros, motos y bicicletas.
- Vehiculo con mayor permanencia.

## Estructuras utilizadas
- **Vectores:** `placas`, `torres`, `apartamentos`, `tipos` y `tieneSalida`.
- **Matriz:** `horas`, con cuatro columnas: hora de ingreso, minuto de ingreso, hora de salida y minuto de salida.
- **Condicionales:** validan opciones, tipos, placas y estados de salida.
- **Ciclos:** controlan el menu, la validacion de datos y los recorridos de los registros.
- **Metodos static:** separan el registro, la consulta, las estadisticas y los calculos. No se crean objetos propios ni se aplican conceptos de POO.

## Funcionamiento
1. Ejecutar el programa.
2. Elegir una opcion del menu.
3. Para registrar un ingreso, escribir los datos solicitados.
4. Para registrar una salida, escribir la placa y la hora de salida.
5. Consultar una placa para ver su registro mas reciente.
6. Consultar las estadisticas o el registro con mayor permanencia.

El programa permite que una placa vuelva a ingresar cuando ya tiene una salida registrada. Cada nuevo ingreso ocupa uno de los 40 registros diarios.

**Nota sobre las horas:** el ejercicio solicita horas, pero no fechas. Por eso, el calculo considera que la permanencia no supera 24 horas y admite que la salida ocurra despues de medianoche. Para un vehiculo que aun no ha salido, la permanencia se calcula usando la hora actual del computador.

## Pruebas de escritorio

| Prueba | Datos o accion | Resultado esperado |
|---|---|---|
| 1. Ingreso correcto | Placa ABC123, carro, ingreso 08:30 | El sistema confirma el ingreso. |
| 2. Placa duplicada | Intentar registrar ABC123 otra vez sin salida | El sistema rechaza el ingreso. |
| 3. Salida | ABC123 sale a las 10:15 | Registra la salida y calcula 1 hora y 45 minutos. |
| 4. Reingreso | Registrar ABC123 despues de su salida | El sistema permite el nuevo ingreso. |
| 5. Consulta inexistente | Consultar una placa no registrada | Informa que no existe el registro. |
| 6. Hora invalida | Escribir hora 25 o minuto 70 | Solicita un valor dentro del rango permitido. |
| 7. Limite diario | Intentar registrar un ingreso despues de completar 40 | Rechaza el registro por superar el limite. |

## Compilar y ejecutar


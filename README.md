# Server Log Analyzer

Este proyecto es una aplicación en Java para simular y analizar entradas de logs de un servidor. Usa hilos y programación concurrente para generar, capturar y procesar solicitudes HTTP con distintos códigos de estado y métodos HTTP.

## Estructura del Proyecto

- **LogEntry.java**: Clase que representa una entrada de log, incluyendo fecha, IP, método HTTP y código de estado.
- **ServerLogAnalysis.java**: Contiene la lógica para generar entradas de log simuladas y analizarlas de forma concurrente.
- **Main.java**: Contiene el flujo principal de la aplicación, controles de ejecución y coordinación entre los hilos de generación y análisis de logs.

## Instrucciones de Uso

1. Compilar los archivos:
```bash
javac Main.java ServerLogAnalysis.java LogEntry.java
```

2. Ejecutar el programa:
```bash
java Main
```

3. Controles en consola:
- `[S]` Inicia la generación de logs.
- `[P]` Detiene la generación y realiza el análisis.
- `[Q]` Sale del programa.

## Funcionalidades

- Generación automática de logs con IPs y fechas aleatorias.
- Soporte para métodos HTTP: GET, POST, PUT, DELETE.
- Códigos de respuesta simulados: 200 (éxito) y 400 (error).
- Procesamiento concurrente usando `ExecutorService` para:
  - Contar códigos de estado.
  - Contar métodos HTTP.

## Requisitos

- Java 8 o superior.

## Autor

Proyecto de análisis de logs de servidor simulado.

# Pepper's Pill Mill - Análisis de Ensayo Clínico

## 📋 Descripción del Proyecto

Programa en Java para analizar datos de un ensayo clínico de un medicamento para el Alzheimer desarrollado por Pepper's Pill Mill. El programa realiza análisis estadístico de puntajes de pruebas de memoria de tres grupos de prueba para determinar la efectividad del medicamento.

## 🎯 Objetivo

El programa analiza los resultados de pruebas de memoria de tres grupos experimentales:
- **Grupo de Control**: Sin tratamiento
- **Grupo Experimental**: Recibe el nuevo medicamento
- **Grupo Placebo**: Recibe píldoras falsas (placebo) de azúcar

El estudio es **doble ciego**, lo que significa que el programa no sabe qué archivo corresponde a qué grupo experimental.

## 📁 Estructura del Proyecto

```
PeppersPillMill/
├── src/                         # Código fuente
│   ├── PeppersPillMill.java    # Clase principal con método main
│   └── TrialGroup.java          # Clase para manejo de datos de cada grupo
├── datos_prueba/                # Carpeta con archivos de prueba
│   ├── dataA1.txt, dataA2.txt, dataA3.txt  # Conjunto de prueba A
│   ├── dataB1.txt, dataB2.txt, dataB3.txt  # Conjunto de prueba B
│   ├── dataC1.txt, dataC2.txt, dataC3.txt  # Conjunto de prueba C
│   └── outputA.txt, outputB.txt, outputC.txt # Salidas esperadas
├── .idea/                       # Configuración de IntelliJ IDEA
├── .gitignore                   # Archivos ignorados por Git
├── PeppersPillMill.iml          # Archivo de módulo de IntelliJ
└── README.md                    # Este archivo
```

## 🚀 Cómo Ejecutar el Programa

### Requisitos Previos
- **Java JDK 24** puede ser JDK 8 o superior
- **IntelliJ IDEA** (recomendado) o cualquier IDE de Java
- Todos los archivos del proyecto descargados

### Opción 1: Ejecutar desde IntelliJ IDEA (Recomendado)

1. **Abrir el proyecto**
    - Abre IntelliJ IDEA
    - File → Open → Selecciona la carpeta `PeppersPillMill`

2. **Ejecutar el programa**
    - Abre `src/PeppersPillMill.java`
    - Click en el triángulo verde ▶️ junto a `public static void main`
    - O presiona `Shift + F10`

### Opción 2: Ejecutar desde Terminal/Línea de Comandos

1. **Navegar al directorio del proyecto**
   ```bash
   cd PeppersPillMill
   ```

2. **Compilar el código**
   ```bash
   javac src/*.java
   ```

3. **Ejecutar el programa**
   ```bash
   java -cp src PeppersPillMill
   ```

## 🧪 Cómo Realizar las Pruebas

### Prueba con Conjunto A (Diferencia Significativa Clara)

1. **Ejecuta el programa**
2. **Ingresa los siguientes archivos cuando se te solicite:**
   ```
   Enter the names of the three input files:
   File 1 (Control/Experimental/Placebo): datos_prueba/dataA1.txt
   File 2 (Control/Experimental/Placebo): datos_prueba/dataA2.txt
   File 3 (Control/Experimental/Placebo): datos_prueba/dataA3.txt
   Enter the name of the output file: datos_prueba/miOutputA.txt
   ```

3. **Resultado esperado en `miOutputA.txt`:**
   ```
   Statistics:
   dataA1.txt: 69.0 18.422811946062957
   dataA2.txt: 47.0 19.91983935678197
   dataA3.txt: 51.583333333333336 19.202683550888285
   Results:
   dataA1.txt vs. dataA2.txt : true
   dataA1.txt vs. dataA3.txt : false
   dataA2.txt vs. dataA3.txt : false
   ```

4. **Verificar resultados:**
    - Compara tu archivo `miOutputA.txt` con `outputA.txt`
    - Deben ser idénticos

### Prueba con Conjunto B (Grupos Grandes, Sin Diferencias)

1. **Ejecuta el programa nuevamente**
2. **Ingresa:**
   ```
   datos_prueba/dataB1.txt
   datos_prueba/dataB2.txt
   datos_prueba/dataB3.txt
   datos_prueba/miOutputB.txt
   ```

3. **Resultado esperado:**
    - Todos los resultados deben ser `false`
    - Los grupos tienen 100 datos cada uno
    - No hay diferencias significativas

### Prueba con Conjunto C (Resultados Mixtos)

1. **Ejecuta el programa**
2. **Ingresa:**
   ```
   datos_prueba/dataC1.txt
   datos_prueba/dataC2.txt
   datos_prueba/dataC3.txt
   datos_prueba/miOutputC.txt
   ```

3. **Resultado esperado:**
    - Solo `dataC1.txt vs. dataC3.txt : true`
    - Las demás comparaciones son `false`

## 📊 Formato de Archivos

### Archivos de Entrada
- **Formato**: Un número entero por línea
- **Rango**: Valores entre 0-100 (puntajes de memoria)
- **Ejemplo** (`dataA1.txt`):
  ```
  57
  96
  84
  72
  64
  ```

### Archivo de Salida
El programa genera un archivo con dos secciones:

1. **Statistics**: Muestra para cada archivo:
    - Nombre del archivo
    - Promedio de los valores
    - Desviación estándar

2. **Results**: Muestra comparaciones por pares:
    - `true` = Diferencia significativa
    - `false` = Sin diferencia significativa

## 🔍 Análisis Estadístico Explicado

### Cálculos Realizados

1. **Promedio (Average)**
   ```
   promedio = suma / recuento
   ```

2. **Desviación Estándar (Standard Deviation)**
   ```
   desviación = √(promedioDeCuadrados - cuadradoDelPromedio)
   
   Donde:
   - promedioDeCuadrados = sumaDeCuadrados / recuento
   - cuadradoDelPromedio = promedio²
   ```

### Criterio de Diferencia Significativa

Existe diferencia significativa entre dos grupos cuando:
```
|promedio1 - promedio2| > desviación1 
Y
|promedio1 - promedio2| > desviación2
```

**Ejemplo del Conjunto A:**
- Grupo 1: promedio=69.0, desviación=18.42
- Grupo 2: promedio=47.0, desviación=19.92
- Diferencia: |69.0 - 47.0| = 22.0
- ¿22.0 > 18.42? SÍ ✓
- ¿22.0 > 19.92? SÍ ✓
- **Resultado: true** (diferencia significativa)

## 🏗️ Estructura del Código

### Clase TrialGroup
```java
class TrialGroup {
    private String fileName;     // Nombre del archivo
    private int count;           // Número de datos
    private int sum;             // Suma de valores
    private int sumOfSquares;    // Suma de cuadrados
    
    // Métodos:
    + TrialGroup(String fileName)     // Constructor
    + getAverage()                    // Calcula promedio
    + getStandardDeviation()          // Calcula desviación
    + getFileName()                   // Retorna nombre
}
```

### Clase PeppersPillMill
```java
public class PeppersPillMill {
    + main(String[] args)             // Método principal
    - escribirResultados(...)         // Genera archivo de salida
    - esDiferenciaSignificativa(...)  // Determina significancia
}
```

## ⚠️ Manejo de Errores

El programa maneja automáticamente:
- ✅ Archivos no encontrados
- ✅ Líneas vacías en archivos
- ✅ Valores no numéricos (los ignora con advertencia)
- ✅ División por cero
- ✅ Errores de E/S

## 📝 Notas Importantes

1. **Estudio Doble Ciego**: El programa no sabe qué archivo corresponde a qué grupo (control, experimental o placebo)

2. **Tamaños Variables**: Los grupos pueden tener diferentes cantidades de datos

3. **Rutas de Archivos**:
    - Usa rutas relativas desde la raíz del proyecto
    - Ejemplo: `datos_prueba/dataA1.txt`

4. **Precisión**: Los números se muestran con precisión completa (todos los decimales)

## 🎓 Información de la Tarea

- **Curso**: CSE110 - Programación
- **Tarea**: 11.17 - Resolución de problema aplicado
- **Fecha de entrega**: Martes a las 23:59
- **Puntos totales**: 57
- **Intentos permitidos**: 1

## 📈 Interpretación de Resultados

### ¿Qué significa una diferencia significativa?

- **true**: El medicamento probablemente tuvo efecto
- **false**: No hay evidencia de que el medicamento sea efectivo

### Casos de Prueba Explicados

**Conjunto A**:
- Un grupo claramente mejor que otro (posible medicamento efectivo)

**Conjunto B**:
- Grupos muy grandes (100 datos c/u) sin diferencias claras
- Simula un medicamento no efectivo

**Conjunto C**:
- Resultados mixtos, solo una comparación significativa
- Simula efectividad parcial

## 🛠️ Solución de Problemas Comunes

### Error: "File not found"
- Verifica que estés en el directorio correcto
- Usa la ruta completa: `datos_prueba/dataA1.txt`

### Error: "NumberFormatException"
- Revisa que los archivos solo contengan números enteros
- Una línea por número

### Los resultados no coinciden
- Verifica la fórmula de desviación estándar
- Asegúrate de usar `Math.abs()` para la diferencia

## ✅ Lista de Verificación para Entrega

- [ ] El código compila sin errores
- [ ] Pasa todas las pruebas (A, B, C)
- [ ] Los archivos de salida coinciden con los esperados
- [ ] Código comentado apropiadamente
- [ ] Variables con nombres descriptivos
- [ ] Manejo de excepciones implementado
- [ ] README.md incluido

## 👥 Créditos

Desarrollado por Sabina Romero y Andres Perot

## Iconografía

Link: https://iconos8.es/icons/set/whatsapp-emoji

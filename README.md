# Proyecto Final - Programación Avanzada

## Título del Proyecto
**Juego de Carreras en Java**

---

## Autor(es)
- **Orly Steven Tigrero Bernabe** – Programador principal / Coordinador  
- **Steven Fernando Nieto Rodríguez** – Manual de Usuario  
- **Jostin Jeremy Gonzabay Sagbay** – Diagrama UML  
- **Luis Alberto Panchana Reyes** – Pruebas y Resultados  
- **Ángel Rodríguez Catuto** – Justificación Técnica  

📅 **Fecha de Entrega:** Noviembre 2025  

---

## 1. Título del Proyecto
### Juego de Carreras en Java

Este proyecto consiste en el desarrollo de un juego 2D en lenguaje **Java**, donde el jugador controla un automóvil que debe esquivar obstáculos mientras avanza por una carretera infinita.  
El juego cuenta con **tres niveles de dificultad**: *Normal*, *Difícil* e *Imposible*, cada uno con diferente velocidad y frecuencia de aparición de obstáculos.

---

## 2. Objetivo General
Desarrollar un videojuego interactivo en Java aplicando los conceptos de **programación orientada a objetos**, **estructuras de datos**, **herencia**, **manejo de eventos** y **temporizadores**.

---

## 3. Objetivos Específicos
- Implementar una jerarquía de clases que represente los elementos principales del juego (*auto*, *obstáculos*, *entorno*).  
- Utilizar **herencia** y **encapsulamiento** para organizar el código de manera modular y reutilizable.  
- Aplicar estructuras de datos como **ArrayList** para gestionar la aparición de los obstáculos.  
- Controlar el movimiento mediante eventos de teclado utilizando la interfaz **KeyListener**.  
- Incorporar **imágenes** y **fuentes personalizadas** para mejorar la experiencia visual.  
- Gestionar distintos niveles de dificultad que modifiquen la velocidad y frecuencia de los obstáculos.

---

## 4. Requisitos del Proyecto
- **Lenguaje:** Java (versión 17 o superior)  
- **IDE recomendado:** Visual Studio Code, Eclipse o IntelliJ IDEA  
- **Dependencias:**  
  - Biblioteca estándar de Java (`javax.swing`, `java.awt`, `java.util`)  
- **Archivos necesarios:**  
  - `src/JuegoCarrera.java`  
  - Carpeta `imagenes/` con todos los recursos gráficos  
  - Carpeta `documentos/` con los archivos de documentación

---

## 5. Entregables

El proyecto se entrega en un **repositorio GitHub** y debe contener:

### 1️⃣ Código Fuente Completo
- Organizado en carpetas y comentado adecuadamente.  
- Implementación del juego con clases bien definidas:
  - `Entidad`
  - `Auto`
  - `Obstaculo`
  - `JuegoCarrera`

---

### 2️⃣ Documentación (carpeta `documentos/`)
- **Manual de Usuario:**  
  Explicación de cómo ejecutar el juego, los controles y los niveles de dificultad.  
  *(Elaborado por Steven)*  

- **Diagrama UML:**  
  Representación gráfica de las clases, atributos, métodos y relaciones de herencia.  
  *(Elaborado por Jostin)*  

- **Justificación Técnica:**  
  Descripción de las decisiones de diseño, uso de herencia, y elección de estructuras de datos.  
  *(Elaborado por Ángel)*  

- **Pruebas y Resultados:**  
  Registro de las pruebas realizadas y conclusiones sobre el funcionamiento del juego.  
  *(Elaborado por Luis)*  

- **Informe Final del Proyecto:**  
  Documento con el resumen completo del desarrollo, conclusiones y aportes de cada integrante.  
  *(Elaborado por Orly)*

---

## 6. Instrucciones para Ejecutar el Juego

1. Clonar o descargar el repositorio desde GitHub.  
2. Abrir el proyecto en un IDE con soporte para Java.  
3. Verificar que la carpeta `imagenes/` esté en el mismo nivel que `src/`.  
4. Compilar y ejecutar la clase principal:

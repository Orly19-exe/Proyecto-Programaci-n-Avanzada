# Justificación Técnica – Juego de Carreras en Java  
*Autor:* Ángel Rodríguez  
*Fecha:* Noviembre 2025  

---

## 1. Diseño del proyecto  
El proyecto utiliza *programación orientada a objetos (POO)* para separar responsabilidades en distintas clases:

- *Entidad:* clase abstracta base para todos los objetos del juego.  
- *Auto:* representa al jugador y gestiona su movimiento.  
- *Obstáculo:* genera objetos aleatorios que el jugador debe esquivar.  
- *JuegoCarrera:* controla la lógica principal, el temporizador, el dibujo y las colisiones.

---

## 2. Uso de herencia y encapsulamiento  
La *herencia* permite que Auto y Obstaculo compartan atributos comunes de Entidad.  
El *encapsulamiento* asegura que cada clase maneje solo sus propias responsabilidades, evitando errores y repeticiones de código.

---

## 3. Estructuras de datos  
Se emplea un *ArrayList<Obstaculo>* para almacenar y recorrer dinámicamente los obstáculos activos en el juego, permitiendo añadir y eliminar elementos fácilmente.

---

## 4. Control de eventos  
Se implementa la interfaz *KeyListener* para detectar teclas y controlar el movimiento del jugador.  
También se utiliza *javax.swing.Timer* para actualizar la pantalla y la posición de los objetos en intervalos regulares (cada 20 ms).

---

## 5. Aspecto visual  
El juego usa imágenes y fuentes personalizadas para mejorar la estética y crear una experiencia fluida y dinámica.

---

## 6. Conclusión  
La estructura modular y orientada a objetos hace que el código sea *reutilizable, mantenible y fácil de entender*.  
Este diseño permite añadir nuevas funciones o niveles sin afectar la base del juego.

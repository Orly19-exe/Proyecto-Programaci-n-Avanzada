# Pruebas y Resultados – Juego de Carreras en Java  
*Autor:* Luis Panchana  
*Fecha:* Noviembre 2025  

---

## 1. Objetivo de las pruebas  
El objetivo principal de las pruebas fue *verificar el correcto funcionamiento del videojuego* “Juego de Carreras en Java”, asegurando que cada componente cumpla con su función prevista.  
Se evaluaron la *interacción del jugador, el **comportamiento de los obstáculos, el **rendimiento gráfico* y la *estabilidad general* del sistema bajo distintas condiciones.

---

## 2. Metodología de pruebas  
Para garantizar resultados confiables, se aplicó una metodología de *pruebas funcionales y de rendimiento*.  
Cada escenario fue ejecutado al menos *cinco veces* en diferentes niveles de dificultad (Normal, Difícil e Imposible).  

Las pruebas se realizaron en equipos con *Windows 10 y Java 17, utilizando un entorno **Visual Studio Code*.  
Los criterios de éxito se basaron en la ausencia de errores críticos, la fluidez del juego y la respuesta inmediata a las acciones del usuario.

---

## 3. Tipos de pruebas realizadas  

| Tipo de prueba             | Descripción                                               | Resultado |
|----------------------------|-----------------------------------------------------------|------------|
| Movimiento del auto        | Verificar que se mueva con las flechas izquierda y derecha. | Correcto |
| Colisión con obstáculo     | El juego se detiene al colisionar.                        | Correcto |
| Reinicio con R             | Reinicia correctamente después de perder.                 | Correcto |
| Cambio de nivel            | Niveles N, D, I cambian velocidad y frecuencia.           | Correcto |
| Generación aleatoria       | Obstáculos aparecen en posiciones diferentes.             | Correcto |
| Puntaje                    | Se incrementa al esquivar obstáculos.                     | Correcto |
| Rendimiento                | FPS estable y sin retrasos.                              | Correcto |

---

## 4. Análisis de resultados  
Los resultados demostraron que el juego *mantiene una tasa de actualización constante* y que los eventos del teclado responden sin retrasos perceptibles.  
Durante las pruebas prolongadas (más de 3 minutos de juego continuo), *no se detectaron pérdidas de memoria ni caídas de rendimiento*.  

El sistema respondió correctamente ante cambios de nivel de dificultad, aumentando la velocidad y cantidad de obstáculos de forma progresiva.  
También se comprobó que, al reiniciar con la tecla “R”, *las variables del juego se reinician correctamente*, incluyendo puntaje, lista de obstáculos y posición del jugador.

---

## 5. Errores detectados y correcciones  
Durante las primeras pruebas se detectaron algunos detalles menores:
- En versiones iniciales, los obstáculos no se eliminaban correctamente al salir de la pantalla, provocando un consumo de memoria innecesario.  
  → Solución: se implementó el método fueraDePantalla() en la clase Obstaculo.
- El auto podía moverse parcialmente fuera de los límites laterales.  
  → Solución: se añadieron condiciones de restricción en los métodos moverIzquierda() y moverDerecha().

---

## 6. Resultados finales  
El juego funciona correctamente en todos los niveles de dificultad y se adapta al ritmo del jugador.  
El rendimiento se mantiene estable (más de *60 FPS*) y no se detectaron errores críticos.  
Los recursos gráficos y de memoria se liberan adecuadamente al finalizar o reiniciar una partida.

---

## 7. Conclusión  
El Juego de Carreras en Java cumple satisfactoriamente con los objetivos del proyecto.  
La aplicación demuestra una *implementación sólida de la Programación Orientada a Objetos, junto con una **gestión eficiente de eventos y gráficos 2D*.  

El código resultante es estable, fluido y fácilmente escalable para futuras versiones que puedan incluir más niveles, nuevos tipos de obstáculos o mejoras visuales.

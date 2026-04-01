# Proyecto 2: Rutas óptimas para el Pumabús

**Modelado y programación.**  
Rosa Victoria Villa Padilla [2025-1] (12/11/2024)

**Equipo:** Christian.
- Leon Navarrete Adam Edmundo
- Morales Martínez Edgar Jesús
- Rubio Resendiz Marco Antonio
- Valencia Pérez Guillermo Emanuel

*Ciudad Universitaria, Cd. Mx. 2024*

---

## 📝 Problemática

El Pumabús es el servicio de transporte más utilizado por estudiantes, profesores y trabajadores de Ciudad Universitaria para moverse dentro del campus. Sin embargo, a pesar de su rol clave en la movilidad, la complejidad de sus rutas, la afluencia de estas y el desconocimiento del funcionamiento del sistema ha resultado un problema para múltiples usuarios, complicando el uso del servicio.

Actualmente, los usuarios carecen de herramientas para conocer una ruta óptima para llegar de un punto a otro de C.U. Por ello, nuestro objetivo con el 2do proyecto de Modelado y Programación es el desarrollo de un software que sea capaz de ofrecerle a los usuarios la ruta que deberían tomar para trasladarse por Ciudad Universitaria haciendo uso del Pumabús.

---

## 🏗️ Elección de patrones de diseño

### MVC (Modelo-Vista-Controlador)

Nuestro sistema se divide en tres responsabilidades esenciales: manejo de las rutas, visualización de las rutas y la interacción del usuario. Esta división es adecuada para segmentar el proyecto en sus tres componentes fundamentales.

*   **Modelo:** Parte encargada de procesar la información referente a cómo se construyen las rutas que estarán a disposición del usuario. Consiste en un paquete completo de nombre `edd/` que cuenta con las estructuras de datos que se emplearán para la lógica del software. En este caso es fundamental este componente, pues se trata de una implementación de Gráficas y Gráficas dirigidas que nos permiten realizar el cálculo de trayectorias óptimas. Esta sección también abarca el paquete `composite/` que se encarga de configurar las diferentes rutas del sistema de movilidad.
*   **Vista:** Parte encargada de convertir la información del modelo en algo visualizable y comprensible por el usuario. Este componente está comprendido por el paquete `graficable/` que nos permite procesar cualquier gráfica dirigida cuyo tipo sea una implementación de la interfaz `VerticeCoordenado` para proporcionar una gráfica (en este caso codificada en svg) que constituye una representación visual de la información contenida en la gráfica. Esta sección también abarca el paquete `menús/` que se encarga de proporcionar al usuario una interfaz gráfica con la que interactuar.
*   **Controlador:** Sección encargada de gestionar las entradas del usuario para constituir el modelo (una gráfica y una trayectoria) y proporcionarle al usuario un objeto visible (la gráfica en svg). Este componente se trata de la clase `ClienteRemoto` que se encarga de procesar las solicitudes del usuario a través de un proxy que puede acceder a los datos cargados por el servidor. En general se encarga de pedir y proporcionar la información que necesita el usuario para mostrar su ruta.

### Singleton

Se decidió implementar Singleton para la clase `Servidor` con el objetivo de garantizar que los usuarios accedan a la misma información de las rutas que haya sido leída por el servidor una vez este haya sido encendido. Esto permite evitar inconsistencias con la información, así como asegurar que todos los usuarios están actualizados.

### Proxy

El patrón Proxy será el encargado de gestionar el acceso de los usuarios a la información del sistema de rutas. Este patrón nos permite centralizar las solicitudes de los usuarios. Esta parte, en conjunto con Singleton, será particularmente útil si es que se busca manejar un número potencialmente alto de solicitudes (como lo haría idealmente una plataforma de movilidad).

> **Nota:** Se hace uso de hilos y sockets para las solicitudes de los usuarios; se crea un nuevo hilo por cada solicitud nueva. Así no se mezclan los accesos al servicio y dejamos al socket abierto a la espera de nuevos solicitantes.

### Builder

Se implementará el patrón de diseño Builder (paquete `graficable/GraficadorBuilder`) para estandarizar el proceso de construcción de gráficas svg. Pues al disponer de múltiples opciones para la creación de las distintas estructuras en la clase `GraficadorGrafo`, es fundamental contar con una clase que sea capaz de utilizar el graficador para constituir un objeto inmutable en un orden específico. Se busca que al momento de graficar se genere un bloque de código estable a la prioridad en la que las figuras se muestran en el objeto construido. Es decir, el Builder nos permite centralizar la lógica de graficado a una secuencia jerárquica de graficar objetos en un orden específico (primero aristas, después trayectorias y después vértices).

### Composite

Se decidió emplear Composite (paquete `composite`) para este proyecto pues, al tratarse de un sistema de movilidad con múltiples rutas, es fundamental que cada una de ellas forme parte del sistema pero que al mismo tiempo sea desacoplable del mismo. Es importante que el sistema completo esté compuesto por sistemas más básicos y que su tarea tenga el mismo enfoque.

El enfoque dado a este patrón será constituir una gráfica de gráficas dirigidas donde cada gráfica dirigida es una ruta del Pumabús y se conecta con las demás rutas a través de las estaciones que comparte con ellas. Esto es importante para permitirle al software incorporar una forma de componer una gráfica dirigida del sistema completo para la búsqueda de rutas, así como de permitirle a las rutas más básicas buscar las mismas rutas óptimas (dentro de la ruta concreta). Esto es importante por cuestiones del desacoplamiento del objeto compuesto como de los que lo componen.

### Factory

Se emplea el patrón de diseño Factory (paquete `factory/`) para la construcción de instancias de `GraficaDirigida` que representarán las rutas del sistema de movilidad. El objetivo es que cada una de las fábricas pueda fabricar las estaciones de una ruta correspondiente proporcionando información particular para las estaciones de la ruta a la que pertenece. Esto desacopla la lógica de fabricación de las estaciones de la creación de las gráficas dirigidas que las contienen, facilitando el encapsulamiento y manteniendo la consistencia de los datos contenidos en las gráficas. Por lo anterior tendremos una fábrica de estaciones por cada una de las 12 rutas del sistema de movilidad.

### Strategy

Se utiliza el patrón de diseño Strategy (paquete `strategy/`) para poder dar la posibilidad al usuario de escoger un criterio de optimización para la trayectoria que desee solicitar. De esta forma podemos implementar distintos tipos de algoritmos para el cálculo de rutas. Por el momento, se han implementado dos estrategias de optimización de rutas:

*   **MenorAfluencia:** Para considerar el dato de afluencia al momento de calcular la ruta, obteniendo la trayectoria menos concurrida.
*   **MenorNumeroDeParadas:** Para considerar que se recorra la menor cantidad de estaciones en el trayecto. De esta manera se da la trayectoria más fluida.

---

## 📐 Respecto al diagrama de clases

Es importante mencionar que el paquete `edd/` de nuestro proyecto comprende implementaciones propias de estructuras de datos para las cuales Java no proporciona una implementación de estas (las gráficas). Estas estructuras de datos emplean clases anidadas para su funcionamiento, por ello que se hace uso del operador de anidamiento para denotar esta parte.

---

## 💻 Respecto a la compilación del proyecto

El proyecto hace uso de un `pom.xml` para su instalación. Esto fue comentado en clase con la profesora y se nos dio permiso debido a dos aspectos particulares de nuestro proyecto:

*   **Uso de JavaFX:** El proyecto emplea la versión javafx 21.0.5 así como un plugin que nos permite configurar el plugin `javafx-maven-plugin` con la versión 0.0.8 del repositorio de Maven para que el usuario no tenga que contar previamente con una versión de JavaFX instalada en su sistema. Además, esto nos permite usar cualquier versión de Java para compilar el proyecto, relegando la ejecución del sistema a un comando de Maven.
*   **Uso de Batik:** Es esencial para nuestro proyecto el uso de la librería Batik, pues el programa genera en última instancia un archivo `.svg` para los cuales JavaFX no cuenta con un soporte para mostrarlos en las interfaces gráficas. Por ello que se decidió emplear Batik para trascodificar archivos `.svg` a archivos `.png` que puedan ser mostrados por las interfaces gráficas.

---

## 🚀 Respecto al uso del proyecto

El proyecto fue desarrollado en las versiones de Java 11.0.22 y Java 21.0.5 y se empleó Linux (Debian y Ubuntu) durante su desarrollo.

Para ejecutarlo (en cualquier sistema que cuente con Maven y Java) se deben usar los siguientes comandos en terminal:

**⚠️ Importante:** Para usar el programa es necesario que se utilicen al menos dos terminales, una para encender el servidor del sistema y otra para hacer uso del programa como usuario. Es necesario que el servidor esté encendido para que se pueda usar el programa.

1.  **Compilar todas las clases y generar los archivos `.class`:**
    ```bash
    mvn install  


2. **Encender el servidor (Terminal 1):**
    ```bash
    java -cp target/classes/ mx.unam.ciencias.modelado.proyecto2.proxy.ClienteRemoto

3. **Usar el programa como usuario (Terminal 2):**
    ```bash
    mvn javafx:run

## 🚀 Notas adicionales.

*   **Nota 1.** Una vez el servidor detecta que ya no hay usuarios usando el sistema se muestra el siguiente mensaje en la terminal 1:
    ```text
    No hay más clientes conectados. Cerrando el servidor.

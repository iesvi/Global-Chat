
# Entorno de desarrollo

Para el desarrollo de la aplicación se ha usado el IDE Intellij + Maven y Java. Lo proyectos han sido creados en la página web [**Spring Initializr**](https://start.spring.io/), que permite configurar un proyecto Maven con dependencias de Spring deseadas. Al descargar el proyecto, se puede importar desde Intellij y ya tendríamos el proyecto listo para comenzar a trabajar.  

Detalle de versiones:

-   Spring Boot: 2.5.0
-   Maven: 3.6.3
-   Java: 1.8
-   IntelliJ IDEA: 2020.3.2 (Ultimate Edition)
-   Sistema Operativo: Windows 10 Home.

He seguido los principios de la arquitectura limpia para el desarrollo de la aplicación:

![Imagen de arquitectura clean architecture, por Uncle Bob.](https://robertogarrido.com/wp-content/uploads/2017/02/cleanArquitecture.jpg)

Una arquitectura limpia se caracteriza por:

-   **Independiente de los frameworks**. Los frameworks deberían ser herramientas, y no obligarnos a actuar de una determinada manera debido a sus restricciones.
    
-   **Testable**. Debemos poder probar nuestras reglas de negocio sin pensar en base de datos, interface gráfica u otros componentes no esenciales de nuestro sistema.
    
-   **Independiente de la UI**. Si la UI cambia a menudo esto no puede afectar al resto de nuestro sistema, que tiene que ser independiente.
    
-   **Independiente de la base de datos**. Deberíamos poder cambiar de Oracle, a SQL Server, a MongoDB, a Casandra o a cualquier otra base de datos sin que afectara demasiado a nuestro sistema.
    
-   **Independiente de cualquier entidad externa**. No deberíamos saber nada de entidades externas, por lo que no deberemos depender de ellas.

## Veamos un ejemplo de como crear un proyecto usando Spring Initializr:

![IMG AUTH](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/entornoDesarrollo/img/initializrAuth.PNG)

En la parte de la izquierda podemos ver los detalles que configuran el proyecto. Podemos cambiar el lenguaje _**(Java, Kotlin, o Groovy)**_, la versión de Spring Boot, y los metadatos del proyecto, que darán nombre a las carpetas que lo contienen.

A la derecha disponemos de un botón que al pulsarlo nos permite buscar y agregar dependencias al proyecto.

En la imagen de arriba se muestra la configuración y las dependencias utilizadas para el microservicio de auth.

![IMG CHAT](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/entornoDesarrollo/img/initializrChat.PNG)

Y para el microservicio de chat se ha utilizado la siguiente configuración y las siguiente dependencias.

## Configuración IDE

La configuración del IDE que he utilizado para desarrollar los microservicios son ambas la misma, por lo que se debe configurar en ambos lados de igual forma:

1. Debemos dirigirnos a **File -> Project Structure -> Project**

	 - El SDK utilizado es: **coretto- 15 Amazon Corretto version 15.0.2**

	- Y como nivel de lenguaje: **15 - Text blocks**

![IMG CHAT](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/entornoDesarrollo/img/project.PNG)

2. En la misma pestaña nos dirigimos a **Modules.**

	-	Como nivel de lenguaje elegimos: **10 - Local variable type inference**

![IMG CHAT](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/entornoDesarrollo/img/modules.PNG)

3. Y lo último de esta pestaña, nos dirigimos a **Platform Settings -> SDKs**.

	-	E indicamos el path del JDK de Java, en mi caso lo tengo es esta dirección: **C:\Program Files\Java\jdk1.8.0_271**

![IMG CHAT](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/entornoDesarrollo/img/sdk.PNG)

Seguidamente le dariamos a **Apply** y aplicaríamos los cambios.

Y para terminar de configurar el proyecto, nos dirigimos a **File -> Settings -> Build, Execution, Deployment -> Compiler -> Java Compiler**

 - E indicamos en **Target bytecode version** la versión **10**.

![IMG CHAT](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/entornoDesarrollo/img/javacompiler.PNG)

E igual que lo que hicimos anteriormente, guardamos los cambios pulsando **Apply**.

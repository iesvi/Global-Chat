
# Tecnologías usadas en el proyecto

A continuación se describen las tecnologías que han sido usadas para el desarrollo de la aplicación:

## Spring
![️Spring](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/tecnologias/img/spring.png)

**Spring** es un framework de código abierto para la creación de aplicaciones empresariales Java, con soporte para Groovy y Kotlin. Tiene una estructura modular y una gran flexibilidad para implementar diferentes tipos de arquitectura según las necesidades de la aplicación.

Ha sido utilizado para crear la lógica de la aplicación, es decir, el **Backend**.

## MongoDB
![️Mongo DB](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/tecnologias/img/mongodb.png)

**MongoDB** (del inglés humongous, "enorme") es un sistema de base de datos NoSQL, orientado a documentos y de código abierto.  En lugar de guardar los datos en tablas, tal y como se hace en las bases de datos relacionales, MongoDB guarda estructuras de datos BSON (una especificación similar a JSON) con un esquema dinámico, haciendo que la integración de los datos en ciertas aplicaciones sea más fácil y rápida. 

MongoDB será la base de datos utilizada para almacenar los datos de la aplicación. Esta base de datos es muy fácil de crear mediante la utilización de Docker donde ésta correrá. Para crearla se utilizó el comando "docker run -p 27017:27017 bitnami/mongodb" en el CMD de Windows.


## Docker
![Tip. Eliminar imágenes y contenedores en Docker – Alberto Luebbert M.](https://www.albertoluebbert.com/blog/wp-content/uploads/2018/09/docker_twitter_share_new.png)

**Docker** es un proyecto de código abierto que automatiza el despliegue de aplicaciones dentro de contenedores de software, proporcionando una capa adicional de abstracción y automatización de virtualización de aplicaciones en múltiples sistemas operativos.

Gracias a Docker podremos correr en contenedores, que para haceros una idea son como máquinas virtuales donde en una de ellas se alojará la base de datos de MongoDB para almacenar los datos de la aplicación y en otra, el servidor de Jenkins para realizar la integración continua de ésta misma.

## Jenkins
![️Jenkins](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/tecnologias/img/jenkins.png)

**Jenkins** CI no es más que un sistema desplegado en un servidor que nos ayuda en la tarea de hacer integración continua y programar tareas automáticas cuando ocurra una determinada acción. A este tipo de servicios se los conoce como CI/CD (Coninuous integration / Continuous deploy).

La integración continua es una práctica de desarrollo de software, en la cual los programadores suben su código a un repositorio central donde automáticamente pasan las pruebas métricas y de calidad. Esta técnica se suele realizar regularmente para detectar fallos cuanto antes y así mantener el código siempre actualizado.

Es decir, la integración continua es una manera de automatizar tareas cuando se sube el código, aunque también se puede utilizar para desplegar las aplicaciones. En un repositorio compartido viene bien utilizar un herramienta de integración continua para mantaner una integridad en el código.

Jenkins nos será de gran ayuda para realizar la integración continua de nuestra aplicación, para finalmente poder recoger nuestros microservicios en contenedores y poder tenerlos listos de una manera más sencilla sin tener que desplegar la aplicación a mano.

## React
![Creación de aplicaciones React "The Agile Way"](https://i.blogs.es/3cc483/1-hsisluifmo6kblfpoktlow/450_1000.jpeg)

**React** es una biblioteca Javascript de código abierto diseñada para crear interfaces de usuario con el objetivo de facilitar el desarrollo de aplicaciones en una sola página.

Gracias a React se realizo el desarrollo del Frontend de la aplicación.

## Node.Js
![️Node JS](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/tecnologias/img/nodejs.png)

Para instalar los paquetes necesarios de React, se usará Node.JS, un gestor de paquetes que nos permite, además de descargar e instalar paquetes, crear un fichero de dependencias llamado **package.json**, en el que se guarde una referencia a todos los paquetes que se han instalado en nuestro entorno de trabajo. De esta forma podremos mover el código de React sin necesidad de arrastrar con él la carpeta **node_modules**, que es donde se almacenan todos estos paquetes instalados.

Para realizar la instalación de las dependencias se usó el comando:

    npm install "nombreDependencia" --save

Con la instrucción `--save` guardaremos las dependencias en el fichero **package.json**.
Y con el comando:

    npm install

Podremos instalar todas las dependencias del fichero **package.json** y reconstruir la carpeta **node_modules**.
Se muestra una captura de las dependencias usadas en el proyecto:

![enter image description here](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/tecnologias/img/dependencias.PNG)

## Firebase
![️Firebase](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/tecnologias/img/firebase.png)

**Firebase** es una plataforma para el desarrollo de aplicaciones web y aplicaciones móviles lanzada en 2011 y adquirida por Google en 2014.

Es una plataforma ubicada en la nube, integrada con Google Cloud Platform, que usa un conjunto de herramientas para la creación y sincronización de proyectos que serán dotados de alta calidad, haciendo posible el crecimiento del número de usuarios y dando resultado también a la obtención de una mayor monetización.

Con **Firebase** podremos realizar en la aplicación la autenticación mediante Facebook y hacer uso de los álbumes, cuyos álbumes creados e imágenes insertadas en ellos se almacenarán en la base de datos de "Firestore Database" de Firebase.

## Microservicios

![aplicación monolítica en comparación con microservicios](https://d1.awsstatic.com/Developer%20Marketing/containers/monolith_1-monolith-microservices.70b547e30e30b013051d58a93a6e35e77408a2a8.png)

Con las **arquitecturas monolíticas**, todos los procesos están estrechamente asociados y se ejecutan como un solo servicio. Esto significa que, si un proceso de una aplicación experimenta un pico de demanda, se debe escalar toda la arquitectura. Agregar o mejorar las características de una aplicación monolítica se vuelve más complejo a medida que crece la base de código. Esta complejidad limita la experimentación y dificulta la implementación de nuevas ideas. Las arquitecturas monolíticas aumentan el riesgo de la disponibilidad de la aplicación porque muchos procesos dependientes y estrechamente vinculados aumentan el impacto del error de un proceso.

Con una **arquitectura de microservicios**, una aplicación se crea con componentes independientes que ejecutan cada proceso de la aplicación como un servicio. Estos servicios se comunican a través de una interfaz bien definida mediante API ligeras. Los servicios se crean para las capacidades empresariales y cada servicio desempeña una sola función. Debido a que se ejecutan de forma independiente, cada servicio se puede actualizar, implementar y escalar para satisfacer la demanda de funciones específicas de una aplicación.

Los microservicios utilizados en la aplicación son:

 - Auth.
 - Chat.
 - Cloud Config.
 - Service Discovery.
 - Gateway.

## Json Web Token 
![JSON Web Tokens - jwt.io](http://jwt.io/img/logo-asset.svg)

**JWT (JSON Web Token)** es un estándar qué está dentro del documento RFC 7519.

En el mismo se define un mecanismo para poder propagar entre dos partes, y de forma segura, la identidad de un determinado usuario, además con una serie de claims o privilegios.

Estos privilegios están codificados en objetos de tipo JSON, que se incrustan dentro de del payload o cuerpo de un mensaje que va firmado digitalmente.

Gracias a Json Web Token hemos conseguido añadir una capa de seguridad a la aplicación que la hace más segura.

**Token JWT:**

En la práctica, se trata de una cadena de texto que tiene tres partes codificadas en Base64, cada una de ellas separadas por un punto, como la que vemos en la imagen siguiente:

![image](https://user-images.githubusercontent.com/72815060/120168944-bc01fc00-c1ff-11eb-9ed9-3a2b7a2c8908.png)

Podemos utilizar un  [debugger online](https://jwt.io/)  para decodificar ese token y visualizar su contenido. Si accedemos al mismo y pegamos dentro el texto completo, se nos mostrará lo que contiene:

![JSON Web Tokens: Artsy's Journey - Artsy Engineering](https://artsy.github.io/images/2016-10-26-jwt-artsy-journey/jwt-example.png)

Podemos ver el contenido del token sin necesidad de saber la clave con la cual se ha generado, aunque no podremos validarlo sin la misma.

Como hemos dicho, un token tres partes:

-   **Header**: encabezado dónde se indica, al menos, el algoritmo y el tipo de token, que en el caso del ejemplo anterior era el algoritmo HS256 y un token JWT.
    
-   **Payload**: donde aparecen los datos de usuario y privilegios, así como toda la información que queramos añadir, todos los datos que creamos convenientes.
    
-   **Signature**: una firma que nos permite verificar si el token es válido, y aquí es donde radica el quid de la cuestión, ya que si estamos tratando de hacer una comunicación segura entre partes y hemos visto que podemos coger cualquier token y ver su contenido con una herramienta sencilla, ¿dónde reside entonces la potencia de todo esto?

**Firma de un token JWT:**

La firma se construye de tal forma que vamos a poder verificar que el remitente es quien dice ser, y que el mensaje no se ha modificado por el camino.

Se construye como el HMACSHA256, que son las siglas de Hash-Based Message Authentication Code (Código de Autenticación de Mensajes), cifrado además con el algoritmo SHA de 256 bits. Se aplica esa función a:

-   **Codificación en Base64 de header.**
    
-   **Codificación en Base64 de payload.**
    
-   **Un secreto**, establecido por la aplicación.
    

De esta forma, si alguien modifica el token por el camino, por ejemplo, inyectando alguna credencial o algún dato malicioso, entonces podríamos verificar que la comprobación de la firma no es correcta, por lo que no podemos confiar en el token recibido y deberíamos denegar la solicitud de recursos que nos haya realizado, ya sea para obtener datos o modificarlos.

Si lo que estamos requiriendo es que el usuario esté autenticado, deberíamos denegar esa petición, por lo que siempre que trabajemos con JWT deberíamos verificar con esa firma si el token es válido o no lo es.

**Token JWT seguro:**

Aunque el algoritmo nos permita verificar la firma, y, por tanto, confiar o no, tanto el encabezado como el cuerpo si llevan muchos datos van en abierto, ya que Base64 no es un cifrado, es simplemente una codificación que es muy fácilmente decodificable.

JWT nos invita siempre a que la comunicación entre partes se realice con HTTPS para encriptar el tráfico, de forma que, si alguien lo interceptara, el propio tráfico a través de HTTP sobre esos sockets SSL, cifrara toda la comunicación, la del token y todo lo demás, y así añadir esa posibilidad de seguridad.

De hecho, **siempre deberíamos utilizar HTTPS y un servidor con certificado**  para hacer el despliegue de nuestras aplicaciones, no solamente con este con este tipo de token JWT.

**Ciclo de vida de un Token JWT:**

Vamos a ver ahora el ciclo de vida de un token JWT, si lo queremos utilizar en el marco de un proceso de autenticación.

Como hemos visto, JWT no es un estándar de autenticación, sino que simplemente un estándar que nos permite hacer una comunicación entre dos partes de identidad de usuario. Con este proceso, además, podríamos implementar la autenticación de usuario de una manera que fuera relativamente segura.

![alt text](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/tecnologias/img/jwt.PNG)

 1. Comenzaríamos desde el cliente, haciendo una petición POST para enviar el usuario y contraseña, y realizar el proceso de login.

 2. Se comprobaría que ese usuario y su contraseña son correctos, y de serlos, generar el token JWT para devolverlo al usuario.

 3. A partir de ahí la aplicación cliente, con ese token, haría peticiones solicitando recursos, siempre con ese token JWT dentro de un encabezado, que sería Authorization: Bearer XXXXXXX, siendo Bearer el tipo de prefijo seguido de todo el contenido del token.

 4. En el servidor se comprobaría el token mediante la firma, para verificar que el token es seguro, y, por tanto, podemos confiar en el usuario.

 5. Dentro del cuerpo del token, además, tenemos los datos de quién es el usuario que ha realizado esa petición, porque podemos contener en el payload todos los datos de usuario que queramos.

 6. Tras verificar que el token es correcto y saber quién es el que ha hecho la petición, podemos aplicar entonces el mecanismo de control de acceso, saber si puede acceder o no, y si es así, responder con el recurso protegido, de manera que lo podría recibir de una forma correcta.

De esta forma podríamos  **implementar el proceso de autenticación**, y hacerlo, además, con estos JSON Web Token.

## Web Sockets
![️HTTP vs WebSocket](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/tecnologias/img/httpvswebsocket.png)

### ¿Qué diferencia hay entre HTTP y WebSocket?

### Comunicación

Con WebSockets envías al servidor mensajes de cadenas simple con datos, y el servidor procesa los datos y las respuestas. La comunicación es más eficiente que HTTP si nos centramos en el tamaño del mensaje, y en la velocidad, especialmente para mensajes de gran tamaño, ya que en HTTP, por ejemplo, tienes que enviar los  _headers_  en cada petición. Esto suma  _bytes_. Además en REST, tienes los recursos en URLs y métodos de HTTP. Lo que significa que para cada petición, obtienes una respuesta.

Puede ser buena idea observar los  _benchmarkings_  realizados por  David Luecke  para comparar el rendimiento de  [HTTP vs WebSockets](https://blog.feathersjs.com/http-vs-websockets-a-performance-comparison-da2533f13a77). Verás que para más de 50 peticiones concurrentes, ¡Websockets pueden ser el 50% más rápidos que HTTP! Esto significa que en muchos casos y según las necesidades de tu proyecto, WebSockets pueden ser más rápidos que las APIs de HTTP tradicionales.

### Stateless vs Stateful

Otra de las diferencias más importantes  entre los dos, es que que WebSockets son protocolos  _stateful_, mientras las conexiones HTTP son  _stateless_. Esto quiere decir que WebSockets crean una conexión que se mantiene viva en el servidor hasta que el socket se cierre y se intercambian los mensajes de forma bidireccional. Mientras que en las conexiones HTTP, en que una petición significa una respuesta - válida o no -, el acceso desde diferentes servidores no interrumpe su funcionamiento, donde es ideal, por ejemplo para microservicios. Pues, cualquier servidor puede gestionar cualquier petición y no es necesario sincronizar cualquiera de los estados compartidos, excepto la base de datos.

Usaremos STOMP como subprotocolo sobre WebSockets.

El Protocolo de mensajes orientado a texto (STOMP) simple (o de transmisión continua) define un protocolo para que el servidor y el cliente se comuniquen con la mensajería.

## Kali Linux
![️Kali Linux](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/tecnologias/img/kalilinux.png)

**Kali Linux** es una distribución basada en [Debian GNU/Linux](https://es.wikipedia.org/wiki/Debian_GNU/Linux "Debian GNU/Linux") diseñada principalmente para la auditoría y [seguridad informática](https://es.wikipedia.org/wiki/Seguridad_inform%C3%A1tica "Seguridad informática") en general. Fue fundada y es mantenida por Offensive Security Ltd. Mati Aharoni y Devon Kearns, ambos pertenecientes al equipo de Offensive Security, desarrollaron la distribución a partir de la reescritura de [BackTrack](https://es.wikipedia.org/wiki/BackTrack), que se podría denominar como la antecesora de Kali Linux.

Con **Kali Linux** se realizarán pruebas de penetración en la aplicación para probar que la seguridad añadida a la aplicación hace su función.

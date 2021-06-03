
# Análisis de la aplicación.

## Gestión de usuario:

- Registrarse 
- IniciarSesión 
- CerrarSesión 
- ConsultarDatosUsuario 
- RecuperarContraseña 

## Gestión de chat:

- EnviarMensaje
- VerMensajesRecibidos

# Funcionalidades de la app

## Gestión de usuario

- Registrarse: El usuario se registrará en la aplicación y las contraseñas se guardarán encriptadas en la base de datos para mayor seguridad.
- Iniciar Sesión, Cerrar Sesión: Se realizará mediante un login normal de la página o través del una entidad externa como es facebook.
- Recuperar Contraseña: La contraseña será enviada por SMS al usuario.

## Gestión de chat

- Enviar Mensaje: Los mensajes enviados serán enviados cifrados y firmados.

## Gestión de album

- Crear album: Se podrán crear álbumes de foto donde se podrá almacenar sus fotos en ellos y así compartirlas con toda la comunidad.
 
## Gestión de seguridad

 - Session Timeout: Cuando se deje de utilizar la aplicación por un tiempo ésta mostrara un ventana para seguir logeado o deslogearse. Si no se selecciona alguna opción en un tiempo determinado la aplicación deslogeará al usuario.
 - Protección Contra Ataques SQL Inyection: Evita que un atacante pueda realizar consultas SQL que perjudiquen nuestra aplicación.
 - JWT (Json Web Token): Se define un mecanismo para poder propagar entre dos partes, y de forma segura, la identidad de un determinado usuario, además con una serie de claims o privilegios. Estos privilegios están codificados en objetos de tipo JSON, que se incrustan dentro de del payload o cuerpo de un mensaje que va firmado digitalmente.

## Gestión de errores

 - Guardar Salida Errores: Se guardarán en archivos de texto mensajes de registro, cuyo propósito es dejar constancia de una determinada transacción en tiempo de ejecución.

# Diagramas de casos de uso

![enter image description here](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/analisis/img/diagrama%20de%20caso%20de%20uso%20-%20usuario.png)

![enter image description here](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/analisis/img/diagrama%20de%20caso%20de%20uso%20-%20chat.png)

# Análisis de requisitos Gestión de usuario

|Nombre |Registrarse |
| - | - |
|ID: |CU-1 |
|Descripción |El usuario se registra en la aplicación con sus datos y se da de alta como usuario registrado. |
|Actores |Usuario |
|Precondiciones |Se requiere que el usuario no posea una cuenta igual a la que quiere registrar. |
|Curso normal |<p>1. El usuario selecciona la opción registrar. </p><p>2. El usuario introduce los datos a rellenar. </p><p>3. El usuario acepta los datos introducidos. </p><p>4. El sistema verifica si los datos introducidos son correctos. </p><p>5. El sistema introduce el nuevo usuario. </p>|
|Postcondiciones |El usuario se registra en la aplicación. |
|Alternativas/Excepciones |<p>**Alternativa 1:** </p><p>4.1 Los datos introducidos por el usuario no son correctos. </p><p>4.2 El sistema informa al usuario que los datos no son correctos. </p><p>4.3 Vuelve al paso 2 para introducir los datos a rellenar con opciones válidas. </p>|

|Nombre |IniciarSesión |
| - | - |
|ID: |CU-2 |
|Descripción |Permite al usuario poder iniciar sesión en el sistema. |
|Actores |Usuario |
|Precondiciones |Que el usuario se haya registrado previamente en el sistema |
|Curso normal |<p>1. El sistema solicita al usuario sus datos </p><p>2. El usuario introduce sus datos </p><p>3. El sistema valida los datos </p><p>4. El sistema da acceso al usuario </p>|
|Postcondiciones |Ninguna. |
|Alternativas/Excepciones |<p>**Alternativa 1:** </p><p>3.1 Los datos introducidos no son correctos</p><p>3.2 El sistema avisa al usuario y vuelve al paso 1</p><p>**Alternativa 2:** </p><p>4.1 El sistema no puede dar acceso al usuario</p><p>4.2 El sistema avisa al usuario y finaliza el caso de uso</p>||

|Nombre |CerrarSesión |
| - | - |
|ID: |CU-3 |
|Descripción |El usuario cierra sesión en la aplicación y pasa a ser un usuario sin loguearse. |
|Actores |Usuario |
|Precondiciones |El usuario haya iniciado sesión. |
|Curso normal |<p>1. El usuario pulsa el botón de iniciar sesión. </p><p>2. El sistema muestra una alerta de si está seguro de cerrar la sesión. </p><p>3. El usuario acepta cerrar sesión y la sesión se cierra. </p>|
|Postcondiciones |El sistema le manda un mensaje de que la sesión ha finalizado. |
|Alternativas/Excepciones |<p>**Alternativa 1:** </p><p>2.1 El usuario cancela el iniciar sesión. </p><p>2.2 Fin d  el caso de uso. </p>|


|Nombre |ConsultarDatosUsuario |
| - | - |
|ID: |CU-5 |
|Descripción |El usuario selecciona la opción de consultar los datos de la cuenta y el sistema mostrará los datos del usuario. |
|Actores |Usuario |
|Precondiciones |Ninguna. |
|Curso normal |<p>1. Include IniciarSesion. </p><p>2. El usuario selecciona ver los datos de la cuenta. </p><p>3. El sistema muestra los datos del usuario en el que se encuentra logeado. </p>|
|Postcondiciones |Ninguna. |
|Alternativas/Excepciones |Ninguna.|

|Nombre |RecuperarContraseña |
| - | - |
|ID: |CU-6 |
|Descripción |El usuario selecciona la opción de recuperar la contraseña y el sistema, introduce su número de teléfono y si el teléfono coincide con algún registro de la base de datos le enviará la contraseña por SMS a su teléfono. |
|Actores |Usuario |
|Precondiciones |El usuario debe estar registrado en la base de datos. |
|Curso normal |<p>1. El usuario selecciona recuperar contraseña. </p><p>2. El usuario introduce su número de teléfono. </p><p>3. El sistema busca una cuenta asociada a ese número de teléfono. </p><p>4. El sistema manda un SMS al usuario con su contraseña.</p>|
|Postcondiciones |El usuario podrá volver a entrar a la aplicación con su usuario y contraseña. |
|Alternativas/Excepciones |<p>**Alternativa 1:** </p><p>3.1 El sistema muestra que no encontró ninguna cuenta asociada a ese número.</p><p>3.2 El sistema vuelve a pedir al usuario que introduzca un número de teléfono válido.</p>|

|Nombre |EnviarMensaje|
| - | - |
|ID: |CU-7|
|Descripción |El usuario rellenará el recuadro para escribir el mensaje y le dará al botón de enviar mensaje.|
|Actores |Usuario|
|Precondiciones |Debe escribir algo antes de enviar el mensaje.|
|Curso normal |<p>1. Include IniciarSesion. </p><p>2. El usuario selecciona el usuario a quien desea enviar un mensaje.</p><p>3. El usuario introduce el mensaje que desea enviar. </p><p>4. El usuario acepta enviar el mensaje.</p><p>5. El sistema manda el mensaje al destinatario elegido.</p>|
|Postcondiciones |Ninguna. |
|Alternativas/Excepciones |<p>**Alternativa 1:** </p><p>4.1 EL sistema muestra que quedaron campos obligatorios sin rellenar.</p>|

|Nombre |VerMensajesRecibidos|
| - | - |
|ID: |CU-8 |
|Descripción |El usuario selecciona el usuario con el que quiere visualizar los mensajes recibidos. |
|Actores |Usuario |
|Precondiciones |Ninguna. |
|Curso normal |<p>1. Include IniciarSesion. </p><p>2. El usuario selecciona un usuario de su bandeja de entrada. </p><p>3. El sistema muestra los mensajes con ese usuario. </p>|
|Postcondiciones |Ninguna. |
|Alternativas/Excepciones |Ninguna.|

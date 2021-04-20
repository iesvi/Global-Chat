
# Global-Mail

![enter image description here](Im%C3%A1genes/icono.png)

# Análisis de requisitos funcionales

# Definición del problema

Global-Mail será una aplicación de servicio de correo donde a través de ésta podremos tanto enviar como recibir correos. Para una mejor experiencia se hará uso de distintas herramientas investigadas previamente para un correcto uso. A su vez, se pondrá en funcionamiento una infraestructura como es CI/CD con Jenkins, es decir, consiste en prácticas combinadas de integración continua y entrega continua, cuyo despliegue se realizará en la nube.

# Casos de uso

## Gestión de usuario:

- Registrarse 
- IniciarSesión 
- CerrarSesión 
- ModificarDatosUsuario 
- ConsultarDatosUsuario 
- RecuperarContraseña 

## Gestión de correos:

- EnviarCorreo 
- EliminarCorreoBandeja 
- EliminarCorreoPapelera 

# Funcionalidades de la app

## Gestión de usuario

 - Las cuentas de los usuarios estarán recogidas en una base de datos que cuentan con una gran seguridad donde la contraseña no se volverá visible si alguien consigue acceder a la base de datos, es decir, la contraseña de mostrará encriptada.
 - A la hora de registro de nuevos usuarios se planteará una opción de **Recuperar contraseña** por si el usuario la olvidó. Para recuperarla deberá introducir ciertos datos para autentificar su identidad, actualizar así su contraseña y poder volver a entrar en la aplicación con su nueva contraseña. También se intentará establecer un método de recuperación de contraseñas mediante un código de envío por SMS al móvil de la persona que deberá introducir para autentificar su identidad. Si el código es correcto podrá realizar la actualización de la contraseña.

## Gestión de correos

 - En Global-Mail también se podrán adjuntar archivos junto al mensaje a enviar. El destinatario por su parte podrá descargar esos archivos.
 - Esta aplicación también implementará una función poca vista que explicaré a continuación. En los envíos de correos SMTP los correos se mantendrán descargados en el correo del destinatario, pero el remitente podrá activar una opción al enviar un correo donde ese correo se podrá autodestruir. Es decir, una vez que el destinatario abra ese correo, tendrá un tiempo limitado de visión que el remitente definirá a la hora del envío. Una vez transcurrido ese tiempo, el mensaje se autodestruirá y ya no volverá a ver forma de recuperar ese correo. 
 
## Gestión de seguridad
 
 - También se implementará seguridad, como el uso del **Sesión Timeout**, donde si una persona deja abierta la aplicación en el ordenador y no le da uso, la aplicación detectará que no estará siendo usada y hará un cierre de sesión para aumentar así la seguridad. Si el usuario vuelve, introducirá de nuevo sus credenciales y podrá volver a acceder a su cuenta de correo.
 - A su vez se tendrá en cuenta la seguridad del envío de los mensajes por lo que estos irán cifrados y firmados. Para validar que el envío de los mensajes mantienen seguridad, se hará uso de la herramienta de **capturas de tráfico de mensajes en red** y comprobar que estos no pueden ser leídos por terceros.
 - Para continuar se tendrá en cuenta la **seguridad** respecto a los **ataques de SQL Inyection** (Inyección SQL) que pueda realizar un atacante, para así aumentar la seguridad de nuestros usuarios.
 -  Al mismo tiempo, se realizarán **Backups** de la base de datos utilizadas de forma automática, para así tener copias de seguridad de nuestra aplicación por si alguna vez surge algún problema tener ese respaldo. 

## Gestión de pruebas

 - En cuanto a la gestión de pruebas, se hará uso de un servidor SMTP falso para probar que los usuarios reciben los correos electrónicos enviados por otros usuarios.   

## Gestión de errores

 - Se hará uso de unas biblioteca que permite a los desarrolladores de software escribir mensajes de registro, cuyo propósito es dejar constancia de una determinada transacción en tiempo de ejecución. Estos posibles errores serán recogidos en archivos de texto cuyas salidas serán más entendibles por los usuarios. Incluyendo así, por ejemplo, una descripción del error ocurrido, la fechan en la que se produjo… Si los usuarios tienen alguna incidencia podrán comunicárselo por la **pestaña de Ayuda** al administrador de la aplicación o para agilizar el proceso, el usuario podrá ir a esos archivos de errores recogidos en una carpeta y comunicar al administrador los últimos pasos que se generó en el archivo de texto de informe de errores para que el administrador sepa dar una respuesta más rápida y certera al usuario de la solución más rápida. 

## Gestión de planificación de tareas

 - Para este caso se hará uso de unas librerías cuya función son la planificación de tareas en Java. Esto servirá por si se quiere que a una cierta hora y unos días en específico la aplicación ejecute una acción. Por ejemplo, la aplicación implementará un apartado de papelera. Esta papelera será utilizada cuando queramos eliminar un correo de nuestra bandeja de entrada. Éste no lo eliminará definitivamente por si eliminamos algún correo por error, pero si eliminamos de nuevo ese correo de la papelera, ese correo si se eliminará definitivamente. Estas librerías podrán entrar en juego por si, por ejemplo, cada semana, los domingo a las 13:00 de la tarde, queremos que todos los archivos almacenados en la papelera sean eliminados de forma automática por temas de almacenamiento y rendimiento, por lo que ser realizará sin tener que hacerlo nosotros a mano ya que esa acción ya se habrá programado con anterioridad. 

 
# Diagramas de casos de uso

![enter image description here](Im%C3%A1genes/diagrama%20de%20caso%20de%20uso%20-%20usuario.png)

![enter image description here](Im%C3%A1genes/diagrama%20de%20caso%20de%20uso%20-%20correo.png)

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


|Nombre |ModificarDatosUsuario |
| - | - |
|ID: |CU-4 |
|Descripción |El usuario selecciona la opción de modificar los datos de la cuenta y modifica aque- llos datos de la cuenta usuario. El sistema registra los nuevos datos del usuario y ac- tualiza los datos. |
|Actores |Usuario |
|Precondiciones |Ninguna. |
|Curso normal |<p>1. El usuario selecciona modificar datos de la cuenta. </p><p>2. El sistema muestra los datos del usuario a poder modificar. </p><p>3. El usuario introduce los nuevos datos a modificar. </p><p>4. El sistema verifica los nuevos datos introducidos. </p><p>5. Si los datos introducidos son aceptados por el sistema el usuario acepta modificar cambios. </p><p>6. El sistema actualiza los nuevos datos. </p>|
|Postcondiciones |El usuario posee ahora los datos modificados. |
|Alternativas/Excepciones |<p>**Alternativa 1:** </p><p>4.1 El sistema muestra que los datos introducidos por el usuario no son correctos.</p><p>4.2 Se vuelve al punto 3 hasta que se introduzcan datos válidos</p><p>**Alternativa 2:** </p><p>5.1 El usuario cancela modificar los datos.</p><p>5.2 Fin del caso de uso.</p><p>**Alternativa 3:** </p><p>6.1 Se produce un fallo en la conexión al actualizar.</p><p>6.2 El sistema muestra al usuario el error ocurrido y vuelve al paso 6.</p>||


|Nombre |ConsultarDatosUsuario |
| - | - |
|ID: |CU-5 |
|Descripción |El usuario selecciona la opción de consultar los datos de la cuenta y el sistema mostrará los datos del usuario. |
|Actores |Usuario |
|Precondiciones |Ninguna. |
|Curso normal |<p>1. Include IniciarSesion. </p><p>2. El usuario selecciona ver los datos de la cuenta. </p><p>3. El sistema muestra los datos del usuario en el que se encuentra logeado. </p>|
|Postcondiciones |Ninguna. |
|Alternativas/Excepciones |Ninguna. |

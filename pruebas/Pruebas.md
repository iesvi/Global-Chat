

# Pruebas

**Índice:**

- [Recoger log en archivos de texto.](##log)
- [Crear álbum e introducir imágenes en él.](#álbum-firebase)
- [Seguridad contra SQL Injection usando Kali Linux.](#seguridad)
- [Usuario guardados en la base de datos.](#usuarios-bd)
- [Accediendo a una URL no permitida.](#accediendo-a-una-url-no-permitida)

En esta parte de la documentación realizaré pruebas sobre la aplicación. En este enlace se puede ver un videotutorial en el que se explica cómo funciona la aplicación y se puede ver como funciona estando desplegada.

## Log

Se ha registrado un par de usuarios en la aplicación para ver la salida del log del microservicio de Auth en el archivo de texto **appLog.log** y este fue el resultado:

![2021-06-01 20-15-54](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/pruebas/img/logPruebaAuth.PNG)

Y se ha mirado en el archivo de log del microservicio de Chat y también dio resultado:

![2021-06-01 20-15-54](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/pruebas/img/logPruebaChat.PNG)

## Álbum Firebase

Se crearon un par de álbumes y se introdujeron algunas imágenes dentro. En las siguiente imágenes se muestra el resultado:

![2021-06-01 20-15-54](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/pruebas/img/pruebaAlbumCreado.PNG)

![2021-06-01 20-15-54](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/pruebas/img/pruebaAlbumFoto.PNG)

![2021-06-01 20-15-54](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/pruebas/img/pruebaFotosFirebase.PNG)

Cabe aclarar que para poder subir fotos a la Firestore Database de Firebase hay que cambiar las reglas de ésta y del Storage para permitir poder leer y escribir.

**Reglas para Firestorage Database:**

![2021-06-01 20-15-54](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/pruebas/img/reglasFirestorage.PNG)

**Reglas para Storage:**

![2021-06-01 20-15-54](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/pruebas/img/reglasStorage.PNG)

## Seguridad

Para probar la seguridad de la aplicación contra ataques de SQL Injection (Inyección SQL) que es un conocido ataque que se realiza en páginas web para ver si son vulnerables y poder así obtener información valiosa de ella, como por ejemplo, correos, contraseñas...

La inyección de **SQL** es un tipo de ciberataque encubierto en el cual un hacker inserta código propio en un sitio web con el fin de quebrantar las medidas de seguridad y acceder a datos protegidos. Una vez dentro, puede controlar la base de datos del sitio web y secuestrar la información de los usuarios.

Se ha utilizado Kali Linux que es una distribución basada en Debian GNU/Linux diseñada principalmente para la auditoría y seguridad informática en general. Con ello se atacará a nuestra propia aplicación.

Para poder atacarla primero tenemos que subir nuestra aplicación a un dominio público. Para hacerlo más fácil se ha utilizado **ngrok** que consiste en una herramienta en la nube con la que podremos exponer de forma muy simple servicios locales a una URL pública de forma segura. Trabaja, además, a través de firewalls y NATs, con lo que nos evitaremos los típicos problemas que generan.

Primero he tenido que descargarme el .exe desde la [página de Ngrok](https://ngrok.com/download).

Una vez descargado desde el cmd me he colocado en la carpeta donde tengo el ejecutable y he utilizado el siguiente comando para iniciar la herramienta:

![2021-06-01 20-15-54](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/pruebas/img/ngrokAntes.PNG)

**Cabe aclarar que la aplicación debe estar corriendo en el puerto indicado, en este caso React se inicia en el puerto 3000.**

Y una vez ejecutado el comando anterior nos dará automáticamente las URL'S para poder utilizarlas con nuestra aplicación corriendo en la nube de ngrok.

Utilizaremos la URL ->  http://c476a0df8fa1.ngrok.io/login

![2021-06-01 20-15-54](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/pruebas/img/ngrokDespues.PNG)

Si comprobamos he introducimos la URL en el navegador veremos que nuestra aplicación sale y funciona perfectamente:

![2021-06-01 20-15-54](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/pruebas/img/pruebaURLNgrok.PNG)

Ahora con esta URL podremos realizar el ataque. Se hará uso dentro de la terminal de Kali Linux del comando **"sqlmap -u http://c476a0df8fa1.ngrok.io/login --dbs"** incluyendo dentro la URL obtenida anteriormente y utilizando la herramienta sqlmap para realizar el ataque de SQL Inyection:

![2021-06-01 20-15-54](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/pruebas/img/pruebaKali1.PNG)

Durante la primera fase ya nos está diciendo que la aplicación a la que estamos atacando, que es la nuestra, parece estar protegida.

Seguidamente proceso a continuar con el ataque y a realizar más pruebas, el resultado es el esperado:

![2021-06-01 20-15-54](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/pruebas/img/pruebaKali2.PNG)

El ataque no surgió efecto y podemos asegurarnos que nuestra aplicación puede resistir esos tipos de ataques.

Por último, uno de los ataques que también se suelen hacer es el de CORS, haciéndose con las cookies de inicio de sesión que se almacena en el servidor y pudiendo un atacante poder entrar con esos datos a un usuario que no es suyo.

Como nuestra aplicación a sido construida de forma segura haciendo uso de Json Web Token, estos tienen la particularidad de que las cookies de inicio de sesión nunca estarán almacenadas en el servidor, ya que es el cliente quien posee esas cookies para entrar y salir que se crean y se destruyen, donde el servidor solo les da acceso o no según el token recibido.

Como conclusión nosotros no debemos preocuparnos por ese tipo de ataque ya que por la explicación de arriba, nosotros no estamos expuestos a ellos.

## Usuarios BD

Al registrarse los dos usuarios anteriores también se queda constancia de ello en la base de datos de mongo. He utilizado la aplicación Studio 3T para visualizarlo:

![2021-06-01 20-15-54](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/pruebas/img/pruebaBD.PNG)

## Accediendo a una URL no permitida

![2021-06-01 20-15-54](https://user-images.githubusercontent.com/72815060/120371851-b3501980-c316-11eb-8072-012393a00bcc.gif)

En el gif que aparece arriba se explicará que es lo que se está realizando:

- Al principio no se encuentra registrado por lo que la aplicación solo lo redirigirá a la URL de login (http://localhost:3000/login) ya que el servidor no recibe ningún token de inicio de sesión por por parte del usuario.

- Seguidamente inicia sesión y se puede ver en la URL que ahora nos permité navegar sin problemas entre las URL'S de la aplicación, usando como ejemplo la de chat (http://localhost:3000/chat).

- Y para terminar se deslogea para ver que de nuevo volvemos a no tener acceso ya que el token se borró al terminar la sesión.


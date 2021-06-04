

# Problemas encontrados durante el desarrollo de proyecto
## Recuperación de contraseña

La aplicación iba a contar con una opción de "Recuperar Contraseña", que sería de uso por si el usuario llegara a olvidarla, poder recuperarla introduciendo su número de teléfono (el que introdujo previamente al registrarse) y recibiendo por SMS la contraseña.

El código fue creado y era funcional pero a la hora de integrarlo en la aplicación no encontraba la forma de que funcionara.

Si desea comprobarlo por aquí te dejo el código:

    public  class  HttpJavaAltiria  {   
     
    public  static  void  main(String[] args)  {
    
	    // Se fija el tiempo máximo de espera para conectar con el servidor (5000)
	    // Se fija el tiempo máximo de espera de la respuesta del servidor (60000)
	    RequestConfig  config  =  RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(60000).build();

	    // Se inicia el objeto HTTP
	    HttpClientBuilder  builder  =  HttpClientBuilder.create();
	    builder.setDefaultRequestConfig(config);
	    CloseableHttpClient  httpClient  =  builder.build();

	    // Se fija la URL sobre la que enviar la petición POST
	    HttpPost  post  =  new  HttpPost("http://www.altiria.net/api/http");
	    
	    // Se crea la lista de parámetros a enviar en la petición POST
	    List<NameValuePair> parametersList  =  new  ArrayList<>();   
	    parametersList.add(new  BasicNameValuePair("cmd", "sendsms"));
	    parametersList.add(new  BasicNameValuePair("login", "samuel.condelopez@iesvalleinclan.es"));
	    parametersList.add(new  BasicNameValuePair("passwd", "qyp7vsry"));
	    parametersList.add(new  BasicNameValuePair("dest", "34...")); // Introducir teléfono después del prefijo +34 (Prefijo Español)
	    parametersList.add(new  BasicNameValuePair("msg", "Mensaje de prueba"));

	    try {
		    // Se fija la codificacion de caracteres de la peticion POST
		    post.setEntity(new  UrlEncodedFormEntity(parametersList, "UTF-8"));
	    } catch (UnsupportedEncodingException  uex) {
		    System.out.println("ERROR: codificación de caracteres no soportada");
	    }

	    CloseableHttpResponse  response  =  null;
	    
	    try {
		    System.out.println("Enviando petición");
		    
		    // Se envía la petición
		    response  =  httpClient.execute(post);
		    
		    // Se consigue la respuesta
		    String  resp  =  EntityUtils.toString(response.getEntity());
		    
		    // Error en la respuesta del servidor
		    if (response.getStatusLine().getStatusCode() !=  200) {
		    
		    System.out.println("ERROR: Código de error HTTP: "  +  response.getStatusLine().getStatusCode());
		    
		    System.out.println("Compruebe que ha configurado correctamente la direccion/url ");
		    
		    System.out.println("suministrada por Altiria");
		    
		    return;
	    
	    } else {
	    
		    // Se procesa la respuesta capturada en la cadena 'response'
		    if (resp.startsWith("ERROR")) {
		    
			    System.out.println(resp);
			    System.out.println("Código de error de Altiria. Compruebe las especificaciones");
			    
		    } else
		    System.out.println(resp);
	    }
	    
	    } catch (Exception  e) {
	    
		    System.out.println("Excepción");
		    e.printStackTrace();
		    return;
	    
	    } finally {
	    
	    // En cualquier caso se cierra la conexión
	    post.releaseConnection();
	    if (response  !=  null) {
	    
		    try {
			    response.close();
		    } catch (IOException  ioe) {
			    System.out.println("ERROR cerrando recursos");
		    }
		    
		  }
		    
	    }
		    
      }
    
    }

Lo único que se necesita hacer es copiar y pegar el código he introducir el número de teléfono en la línea    `parametersList.add(new  BasicNameValuePair("dest", "34..."));` después del número 34 que hace referencia al prefijo del número de teléfono español, introducir el mensaje personalizado en la línea `parametersList.add(new  BasicNameValuePair("msg", "Mensaje de prueba"));` y después ejecutar el programa.

## Intercambio de archivos e imágenes por chat

Se ha intentado implementar en el chat el intercambio de archivos e imágenes pero me ha sido imposible al tratar de implementarlo con el uso de Web Sockets y Json Web Token que tenía diseñado.

Como reemplazo se ha creado una sección de álbumes pudiéndose crear álbumes y realizar subida de fotos en ellos.

## Jenkins y Amazon Web Service

Al ser algo novedoso se me ha hecho complicado entender como funcionaba la integración continua y como llevarla a cabo en mi proyecto, ya que lo utilizaríamos para complementarla en Amazon Web Service y poder subir la aplicación a la nube.

Debido a los seguidos problemas no se ha podido implementar como se esperaba aunque el conocimiento ganado es más que agradecido al poder tener una visión de ello y entender como funciona.

![alt text](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/problemas/img/jenkinsaws.png)

La idea es que Jenkins vaya realizando pruebas de integración continua con todos los microservicios, incluido el Frontend, y que estos fueran alojados en distintos contenedores creados en Docker Hub, página que irá vinculada con Jenkins para guardar las imágenes.

Y a través del uso de un docker.compose, indicaremos todos los contenedores a desplegar dentro de una instancia de AWS, consiguiendo así un despliegue de nuestra aplicación en la nube.



# Diseño de la aplicación

## Diagrama de Clases

## FRONTEND

![enter image description here](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/dise%C3%B1o/img/TFG%202021-FRONT%20DC.png)

|Nombre| Descripción |
|--|--|
| VistaRegistro | Vista donde se podrá registrar el usuario en la aplicación. |
| VistaLogin | Vista donde se podrá logear el usuario en la aplicación. |
| VistaChat |  Vista donde se podrá ver el chat con el que estamos hablando. |
| VistaBandejaEntrada |  Vista donde se podrá ver los chats logeados en la aplicación. |
| VistaAlbumes | Vista donde se podrán crear álbumnes y poder almacenar fotos en él. |
| VistaFotosAlbum |  Vista donde se podrán ver las fotos almacenadas en un álbum. |
| VistaVerPerfil |  Vista donde se podrá ver los datos del usuario logeado. |

## AUTH-BACKEND

![enter image description here](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/dise%C3%B1o/img/TFG%202021-AUTH%20BACK%20DC.png)

|Nombre| Descripción |
|--|--|
| AuthApplication | Clase que se utilizará para poner en marcha el microservicio. |
| UserService | Clase que se utilizará para declarar los métodos que se utilizará en la clase UserController. |
| UserController | Clase que se utilizará para realizar interacciones de usuario con el frontend y el backend. |
| AuthService | Clase que se utilizará para declarar los métodos que se utilizará en la clase AuthController. |
| AuthController | Clase que se utilizará para realizar la autenticación de un usuario. |
| JwtConfig | Clase que se utilizará para declarar las partes que forman un Json Web Token. |
| JwtTokenAuthenticationFilter | Clase que se utilizará para verificar la autenticación de un token. |
| SecurityCredentialConfig | Clase que se utilizará para incluir la seguridad del token y la base de datos.|
| JwtTokenProvider | Clase que se utilizará para generar el token y validar el secreto de éste. |
| UserRepository | Clase que se utilizará para declarar los métodos que se utilizará en la clase UserServiceImpl. |
| User | Hace referencia a la entidad usuario. |
| Profile | Hace referencia al perfil del usuario. |

## CHAT-BACKEND

![enter image description here](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/dise%C3%B1o/img/TFG%202021-CHAT%20BACK%20DC.png)

|Nombre| Descripción |
|--|--|
| ChatApplication | Clase que se utilizará para poner en marcha el microservicio. |
| ChatService | Clase que se utilizará para declarar los métodos que se utilizará en la clase ChatController. |
| ChatController | Clase que se utilizará para realizar interacciones de chat entre el frontend y el backend. |
| SecurityConfig | Clase que se utilizará para incluir la configuración de CORS. |
| WebSocketConfig | Clase que se utilizará para incluir la configuración del Web Socket.|
| ChatMessageService | Clase que se utilizará para declarar los métodos que se utilizará en la clase ChatMessageServiceImpl. |
| ChatMessageServiceImpl | Clase que se utilizará para el envío de mensajes. |
| ChatRoomService | Clase que se utilizará para declarar los métodos que se utilizará en la clase ChatRoomServiceImpl.|
| ChatRoomServiceImpl | Clase que se utilizará para la creación de múltiples chats. |
| ChatMessageRepository | Clase que se utilizará para declarar los métodos que se utilizará en la clase ChatMessageServiceImpl. |
| ChatRoomRepository | Clase que se utilizará para declarar los métodos que se utilizará en la clase ChatRoomServiceImpl. |
| ChatMessage | Hace referencia a los mensajes de chat. |
| ChatNofication | Hace referencia a las notificaciones de chat. |
| ChatRoom | Hace referencia a la sala de chat con un usuario. |

## Base de datos

![enter image description here](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/dise%C3%B1o/img/user.PNG)

| Atributo | Descripción |
|--|--|
| id | Un ID único para cada usuario. |
| username | Atributo que hace referencia al nombre de usuario. |
| password | Atributo que hace referencia a la contraseña del usuario. |
| email | Atributo que hace referencia al email del usuario. |
| createAt | Atributo que hace referencia al momento en el que se creó el usuario. |
| updateAt | Atributo que hace referencia a la última vez que el usuario fue modificado. |
| userProfile | Atributo que hace referencia al perfil del usuario. |
| roles | Atributo que hace referencia al rol del usuario. Si es un rol de inicio de sesión normal o de Facebook. |

![enter image description here](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/dise%C3%B1o/img/chatMessage.PNG)

| Atributo | Descripción |
|--|--|
| id | Un ID único para cada mensaje de chat. |
| chatId | Atributo que hace referencia al id del chat.|
| senderId | Atributo que hace referencia al id del remitente. |
| recipientId | Atributo que hace referencia al id del destinatario. |
| senderName | Atributo que hace referencia al nombre del remitente. |
| recipientName | Atributo que hace referencia al nombre del destinatario. |
| content | Atributo que hace referencia al contenido del mensaje. |
| timestamp | Atributo que hace referencia al momento en el que se envió el mensaje.|
| status | Atributo que hace referencia al estado del mensaje, si fue leído o no.|

![enter image description here](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/dise%C3%B1o/img/chatRoom.PNG)

| Atributo | Descripción |
|--|--|
| id | Un ID único para cada sala de chat. |
| chatId| Atributo que hace referencia al id del chat. |
| senderId | Atributo que hace referencia al id del remitente. |
| recipientId | Atributo que hace referencia al id del destinatario. |

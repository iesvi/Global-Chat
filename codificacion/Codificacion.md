
# Codificación del proyecto
En este documento se va explicar todos los casos de uso, acompañados del código más significativo que los implementa al igual que como funciona la interfaz gráfica.

Para ello primero aclararemos que hace cada una de las anotaciones utilizadas en los microservicios:

-   **@Autowired**: Esta anotación declarada sobre una variable hará que Spring automáticamente la inicialice, es decir, evitamos así realizar un "new" para crear una instancia de la clase.
- **@Slf4j**: Esta anotación se utiliza para hacer uso del log y poder obtener por consola un mejor seguimiento de lo que ocurre en la aplicación.
- **@EnableWebSecurity**: Permite habilitar la configuración de seguridad en Spring.
- **@EnableGlobalMethodSecurity(prePostEnabled = true)**: Anotación que permite hacer uso de las anotaciones @PreAuthorize y @PostAuthorize que se utilizan para comprobar la autorización antes de y de después de ejecutar un método.	
- **@Component**: Anotación que indica que una clase con esta anotación es un component o Bean de Spring.
- **@Data**: Es una cómoda anotación que recoge en un conjunto las anotaciones @ToString, @EqualsAndHashCode, @Getter, @Setter y @RequiredArgsConstructor.
- **@Document(collection = "User")**: Esta anotación marca una clase como un objeto de dominio que queremos que persista en la base de datos y nos permite elegir el nombre de la colección que queremos usar. Esta anotación es el equivalente de Mongo de @Entity en JPA.
- **@EnableWebSocketMessageBroker**: Anotación utilizada para permitir el intercambio de mensajes por Web Sockets.
- **@Configuration**: Gracias a esta anotación Spring sabe donde se encuentra la configuración utilizada.
-   **@RequestMapping()**: Con RequestMapping asociamos la URL definida entre los paréntesis con el controlador donde se sitúe esta anotación. si no se introduce ninguna URL la dirección asociada será la dirección raíz (/).
-   **@RestController**: Esta anotación convierte la clase en la que se use en un servicio REST.
-   **@GetMapping()**: Esta anotación se usa para definir un controlador a peticiones de tipo GET que se hagan a la dirección definida entre los paréntesis.
-   **@PostMapping()**: Esta anotación se usa para definir un controlador a peticiones de tipo POST que se hagan a la dirección definida entre los paréntesis.
-   **@Service**: Con esta anotación definimos un componente Service.
-   **@Repository**: Con esta anotación definimos un componente Repository.
-   **@RequestBody**: Con esta anotación definimos un objeto que esperamos recibir en el cuerpo de una petición HTTP.
-   **@PathVariable()**: Con esta anotación definimos una variable que esperamos obtener en la URL de una petición HTTP.

La estructura de paquetes presentan el modelo de [arquitectura limpia](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/entornoDesarrollo/EntornoDesarrollo.md):
## AuthBackend

![alt text](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/codificacion/img/paqueteAuth.PNG)

Y en su interior encontramos las siguientes [clases](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/dise%C3%B1o/Dise%C3%B1o.md):

![alt text](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/codificacion/img/paqueteAuthAbierto.PNG)

## ChatBackend

![alt text](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/codificacion/img/paqueteChat.PNG)

Y de igual forma, en su interior encontramos las siguientes [clases](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/dise%C3%B1o/Dise%C3%B1o.md):

![alt text](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/codificacion/img/paqueteChatAbierto.PNG)

Siguiendo con la explicación de como funcionan los microservicios, las peticiones son recibidas por los controladores, mediante las anotaciones **@RequestMapping**,**@GetMapping** y **@PostMapping**.

![alt text](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/codificacion/img/controllerUser.PNG)

Las operaciones relacionadas con la base de datos se realizan usando una interfaz **NombreRepository** que extiende de **MongoRepository** y declarada como componente  **Repository** usando la anotación **@Repository**. MongoRepository es una interfaz que nos permite realiazar múltiples operaciones con una base de datos MongoDB. Algunas de estas operaciones están ya definidase en MongoRepository y otras sólo tenemos que declararlas como métodos en nuestra interfaz _NombreRepository_ utilizando palabras claves que, Spring interpretará generando los métodos automaticamente. Un ejemplo sería para buscar cualquier objeto a partir de un atributo de su clase, basta con definir un método tal que así: `Object findByAtributo(TipoAtributo valor)` y nos devolverá el primer objeto que encuentre en la base de datos con un atributo que tenga el valor parametrizado.  

Para poder usar la interfaz **NombreRepository**, tenemos que indicarle el tipo de documento que vamos a insertar en la base de datos, eso es lo que se indica en la extensión de MongoRepository entre **"<>"**.

![alt text](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/codificacion/img/repositoryUser.PNG)

Ahí escribiremos el nombre de la clase que se guardará en la base de datos, y el tipo de dato del atributo que funciona como identificador de cada objeto. Para indicar cual es el atributo que identifica a casa objeto de la clase de forma inequívoca, se usa la anotación **@Id**. Si declaramos este atributo como un tipo String, podremos guardar objetos donde el atributo esté vacío **(sólo si el atributo id es de tipo String)**, ya que MongoDB generará automáticamente un id aleatorio al insertar el documento en la base de datos. Además del id, tendremos que indicar a Spring cuál es la clase que se va a guardar en la base de datos usando la anotación **@Document**, ya que Spring es quién se encargará de hacer las conversiones de clase Java, a documento de MongoDB, cada vez que guardemos o recuperemos datos.

![alt text](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/codificacion/img/modelUser.PNG)

Por último, en el paquete service se crean los servicios, que son declarado como componente **Service** usando la anotación **@Service** y es donde se desarrolla la lógica de negocio del microservicio. 
 
Ejemplo de uno de los microservicios:

![alt text](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/codificacion/img/userService.PNG)

# Casos de uso

## Gestión de usuario

## Registrarse

El usuario obtiene una vista de registro para completar con sus datos.

![alt text](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/codificacion/img/registrarseCU.PNG)

**AuthController.java**

    @PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<?> createUser(@Valid @RequestBody SignUpRequest payload) { log.info("Creando usuario {}", payload.getUsername()); User user = User .builder() .username(payload.getUsername()) .email(payload.getEmail()) .password(payload.getPassword()) .userProfile(Profile .builder() .displayName(payload.getName()) .profilePictureUrl(payload.getProfilePicUrl()) .build()) .build(); try { userService.registerUser(user, Role.USER); } catch (UsernameAlreadyExistsException | EmailAlreadyExistsException e) { throw new BadRequestException(e.getMessage()); } URI location = ServletUriComponentsBuilder .fromCurrentContextPath().path("/users/{username}") .buildAndExpand(user.getUsername()).toUri(); return ResponseEntity .created(location) .body(new ApiResponse(true,"Usuario registrado correctamente.")); }

Se crea un punto de entrada donde se recibirá el objeto User, éste será creado mediante el patrón builder y se inserta toda la información en el payload, es decir, en la segunda parte del Json Web Token donde se guarda la información. Si el usuario el registrado correctamente devolverá un mensaje que se mostrará en el Frontend como que se ha registrado con éxito, de lo contrario le saltará un error.

![alt text](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/codificacion/img/contraseñaBD.PNG)

**UserService.java**

    public User registerUser(User user, Role role) { log.info("Registrando al usuario {}", user.getUsername()); if (userRepository.existsByUsername(user.getUsername())) { log.warn("El usuario {} ya existe.", user.getUsername()); throw new UsernameAlreadyExistsException( String.format("El usuario %s ya existe.", user.getUsername())); } if (userRepository.existsByEmail(user.getEmail())) { log.warn("El E-Mail {} ya existe.", user.getEmail()); throw new EmailAlreadyExistsException( String.format("El E-mail %s ya existe.", user.getEmail())); } user.setPassword(passwordEncoder.encode(user.getPassword())); user.setRoles(new HashSet<>() {{ add(role); }}); return userRepository.save(user); }

Se inserta el objeto en la base de datos usando la clase registerUser y donde la contraseña irá cifrada para que ésta no pueda ser visible.

## Iniciar Sesión

Una vez que el usuario se ha registrado podrá introducir sus datos.

![alt text](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/codificacion/img/loginCU.PNG)

**AuthController.java**

    @PostMapping("/signin") public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) { String token = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword()); return ResponseEntity.ok(new JwtAuthenticationResponse(token)); }

Se crea un punto de entrada donde se recibirá el token de autenticación del usuario y si éste es correcto y no ha sido manipulado, obtendrá un login exitoso.

**UserService.java**

    public String loginUser(String username, String password) { Authentication authentication = authenticationManager .authenticate(new UsernamePasswordAuthenticationToken(username, password)); return tokenProvider.generateToken(authentication); }

Con el método loginUser se encargará de devolver el token de autenticación para el usuario. Y para generar ese token se ha hecho uso de la clase **JwtTokenProvider**.

**JwtTokenProvider.java**

    public String generateToken(Authentication authentication) { Long now = System.currentTimeMillis(); return Jwts.builder() .setSubject(authentication.getName()) .claim("authorities", authentication.getAuthorities().stream() .map(GrantedAuthority::getAuthority).collect(Collectors.toList())) .setIssuedAt(new Date(now)) .setExpiration(new Date(now + jwtConfig.getExpiration() * 1000)) .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes()) .compact(); }

El método generateToken será el encargado de generar el token de autenticación el cual posee toda la configuración necesaria, construido mediante el patrón builder para insertar las tres partes que lo forman:

 - **Header**: encabezado dónde se indica, al menos, el algoritmo y el tipo de token, que en el caso del ejemplo anterior era el algoritmo HS256 y un token JWT.
 - **Payload**: donde aparecen los datos de usuario y privilegios, así como toda la información que queramos añadir, todos los datos que creamos convenientes.
 - **Signature**: una firma que nos permite verificar si el token es válido.

**JwtTokenAuthenticationFilter.java**

    @Override protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException { // 1.Obtenemos el header del token de autenticación String header = request.getHeader(jwtConfig.getHeader()); // 2.Validamos el header if (header == null || !header.startsWith(jwtConfig.getPrefix())) { chain.doFilter(request, response); // Si no es válido, se slata al siguiente filtro return; } // 3.Obtenemos el token String token = header.replace(jwtConfig.getPrefix(), ""); if (tokenProvider.validateToken(token)) { Claims claims = tokenProvider.getClaimsFromJWT(token); String username = claims.getSubject(); UsernamePasswordAuthenticationToken auth = userService.findByUsername(username) .map(InstaUserDetails::new) .map(userDetails -> { UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken( userDetails, null, userDetails.getAuthorities()); authentication .setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); return authentication; }) .orElse(null); SecurityContextHolder.getContext().setAuthentication(auth); } else { SecurityContextHolder.clearContext(); } chain.doFilter(request, response); }

Y con ayuda del método doFilterInternal validamos el token, incluido el payload con los datos de la base de datos.

## Cerrar Sesión

![alt text](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/codificacion/img/cerrarSesionCU.PNG)

    const logout = () => { localStorage.removeItem("accessToken"); props.history.push("/login"); };

Haciendo uso de un método del Frontend podemos cerrar sesión fácilmente eliminando el token de acceso del usuario y redirigiéndolo a la pantalla de login.

## Consultar Datos Usuario

![alt text](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/codificacion/img/datosUsuarioCU.PNG)

**UserController.java**

    @GetMapping(value = "/users/{username}", produces = MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<?> findUser(@PathVariable("username") String username) { log.info("Recuperando al usuario {}", username); return userService .findByUsername(username) .map(user -> ResponseEntity.ok(user)) .orElseThrow(() -> new ResourceNotFoundException(username)); }

Se crea un punto de entrada donde se recibirá el userName devolviendo un **ok** si se encontró algún resultado o saltará una excepción de mensaje de error al no encontrar ningún usuario con ese nick.

**UserService.java**

    public Optional<User> findByUsername(String username) { log.info("Recuperando usuario {}", username); return userRepository.findByUsername(username); }

El método de findByUsername es el que se encarga de buscar en la base de datos si hubo éxito o no.

# Gestión de chat

Lo primero en pensar en el modelo de mensaje a intercambiar entre servidor y cliente.

El modelo de mensaje de chat se ve así:

**ChatMessage.java**

    public class ChatMessage { @Id private String id; private String chatId; private String senderId; private String recipientId; private String senderName; private String recipientName; private String content; private Date timestamp; private MessageStatus status; }


El mensaje de chat es bastante sencillo, tiene los campos básicos para identificar un mensaje de chat entre el remitente y el destinatario.

También tiene un campo de estado, que indica si el mensaje se entrega al usuario:

**MessageStatus.java**

    public enum MessageStatus { RECEIVED, DELIVERED }

Cuando el servidor recibe un mensaje de chat, no lo envía directamente al usuario, sino que envía una notificación de chat para notificar al usuario que se ha recibido un nuevo mensaje. El mensaje se marcará como entregado una vez que el usuario lea el mensaje.

![alt text](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/codificacion/img/notificacionChatSinLeer.PNG)

![alt text](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/codificacion/img/notificacionChatLeido.PNG)

La notificación de chat se ve así:

**ChatNotification.java**

    public class ChatNotification { private String id; private String senderId; private String senderName; }

**Habilitar WebSocket y STOMP en Spring.**

El primer paso para habilitar WebSocket es configurar el STOMP (Protocolo simple de mensajería orientada a texto) y el message broker (protocolo MBWS -> MessageBroker WebSocket Subprotocol).

Se ha creado una clase con nombre WebSocketConfig con las anotaciones @Configuration y @EnableWebSocketMessageBroker para habilitar el manejo de mensajes de WebSocket, respaldado por el message broker.

**WebSocketConfig.java**

    @Configuration @EnableWebSocketMessageBroker public class WebSocketConfig implements WebSocketMessageBrokerConfigurer { @Override public void configureMessageBroker(MessageBrokerRegistry config) { config.enableSimpleBroker( "/user"); config.setApplicationDestinationPrefixes("/app"); config.setUserDestinationPrefix("/user"); } @Override public void registerStompEndpoints(StompEndpointRegistry registry) { registry .addEndpoint("/ws") .setAllowedOrigins("*") .withSockJS(); } @Override public boolean configureMessageConverters(List<MessageConverter> messageConverters) { DefaultContentTypeResolver resolver = new DefaultContentTypeResolver(); resolver.setDefaultMimeType(MimeTypeUtils.APPLICATION_JSON); MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter(); converter.setObjectMapper(new ObjectMapper()); converter.setContentTypeResolver(resolver); messageConverters.add(converter); return false; } }

El primer método **configureMessageBroker**, configura un intermediario de mensajes en memoria simple con un destino para enviar y recibir mensajes, este destino tiene el prefijo /user, también se utiliza el prefijo /app para los mensajes que están destinados al método anotado con @MessageMapping.

La ruta de destino del usuario /user es utilizada por el método ConvertAndSendToUser de SimpleMessagingTemplate para prefijar todos los destinos específicos del usuario con /user.

El segundo método registra el /ws punto final STOMP. El cliente utiliza este punto final para conectarse al servidor STOMP. También habilita las opciones de respaldo de SockJS, por lo que se pueden usar opciones de mensajería alternativas si WebSockets no están disponibles.

El último método configura un conversor de mensajes JSON, que Spring utiliza para convertir mensajes de chat a JSON.

**ChatController.java**

    @Controller public class ChatController { @Autowired private SimpMessagingTemplate messagingTemplate; @Autowired private ChatMessageService chatMessageService; @Autowired private ChatRoomService chatRoomService; @MessageMapping("/chat") public void processMessage(@Payload ChatMessage chatMessage) { var chatId = chatRoomService .getChatId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true); chatMessage.setChatId(chatId.get()); ChatMessage saved = chatMessageService.save(chatMessage); messagingTemplate.convertAndSendToUser( chatMessage.getRecipientId(),"/queue/messages", new ChatNotification( saved.getId(), saved.getSenderId(), saved.getSenderName())); } }

La anotación @MessageMapping asegura que si se envía un mensaje a /app/chat, el método processMessage se llama.

Este método persiste el mensaje en MongoDB, luego llama al convertAndSendToUser, método para enviar el mensaje de notificación al usuario destinatario.

**Generar el ID de chat.**

Para cada conversación entre dos usuarios, creamos una sala de chat y generamos un ID de chat único para identificar la conversación entre los dos usuarios.

El modelo de la sala de chat se ve así:

**ChatRoom.java**

    public class ChatRoom { private String id; private String chatId; private String senderId; private String recipientId; }

El  chatId se genera concatenando  senderId_recipientId, para cada conversación persistimos dos entradas con la misma chatId, una sala, entre remitente y destinatario y, la otra, entre destinatario y remitente, para asegurarnos de que ambos usuarios obtengan el mismo ID de chat.

**Ccomunicación chat Frontend y Backend**

Se usará SockJS y  Stomp.js para comunicarnos con nuestro servidor a través de STOMP sobre WebSocket.

    const connect = () => { const Stomp = require("stompjs"); var SockJS = require("sockjs-client"); SockJS = new SockJS("http://localhost:8080/ws"); stompClient = Stomp.over(SockJS); stompClient.connect({}, onConnected, onError); };

El método **connect()** establece una conexión a /ws, que es donde nuestro servidor espera las conexiones. También define una devolución onConnected de llamada a la que se llamará después de una conexión exitosa y, onError si se produjo un error al conectarse al servidor.

    const onConnected = () => { console.log("connected"); stompClient.subscribe( "/user/" + currentUser.id + "/queue/messages", onMessageReceived ); };

El método **onConnect()** se conecta al destino específico del usuario, por lo que recibe todos los mensajes enviados a ese destino.

Finalmente, el método sendMessage() envía un mensaje a la ruta /app/chat, ruta que se definió en la clase ChatController en el servidor.

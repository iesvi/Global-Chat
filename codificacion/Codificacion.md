
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

La estructura de paquetes presentan el modelo de arquitectura limpia:
## AuthBackend

![alt text](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/codificacion/img/paqueteAuth.PNG)

Y en su interior encontramos las siguientes [clases](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/dise%C3%B1o/Dise%C3%B1o.md):

![alt text](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/codificacion/img/paqueteAuthAbierto.PNG)

## ChatBackend

![alt text](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/codificacion/img/paqueteChat.PNG)


Y de igual forma, en su interior encontramos las siguientes [clases](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/dise%C3%B1o/Dise%C3%B1o.md):

![alt text](https://github.com/info-iesvi/proyectodam-samuelvalleinclan/blob/doc/codificacion/img/paqueteChatAbierto.PNG)

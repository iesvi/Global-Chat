#### Dominio Shared

En este carpeta se incluye todo lo relacionado con Dominio compartido a nivel general del proyecto.
En esta carpeta se incluirán en general interfaces que después tendrán sus implementaciones en la carpeta de infraestructura.

Ejemplos:
- Interfaces para logging
- Clases utils de dominio
- Clases ValueObjects que contiene lógica de dominio  
- Excepciones/Error de dominio --> DomainError
    las excepciones a nivel general, int y cualquier elemento de infraestructura a nivel de proyecto.
- DomainService o Clases que contenga lógica de negocio compartida por los CU que no se pueda meter dentro de una Entidad  

<img  alt="exchangeimg" width="200px" height="200px" align="right" src="https://cdn-icons-png.flaticon.com/512/2178/2178189.png" >

## Literatura APP

Proyecto para el programa ONE-Backend. La app está desarrollada en Java 20 con el framework Spring, usando JPA al tiempo que se realizan consultas y guardado de datos en una base de datos tipo PostgreSQL. Se basa en el consumo de la API de [Gutendex](https://gutendex.com/), de este modo esta app consulta los libros de la API al tiempo que los guarda en la DB para futuros procesos del menu mismo.

### Funcionalidades de la app

En esta app te encuentras nueve opciones:

        1- Buscar libro por título.
        2- Listar libros registrados.
        3- Listar autores registrados.
        4- Listar autores vivos en un determinado año.
        5- Listar libros por idioma.
        6- Estadisticas de los libros descargados.
        7- Listar libros más descargados de la API gutendex.
        8- Listar libros más descargados en nuestro registro.
        9- Buscar autor por nombre.
        0- Salir.
    
Te dejo aquí los atajos para que puedas verlas de forma más rápida:

- [1. Buscar libro por titulo](#buscar-libro-por-titulo)
- [2. Listar libros registrados](#ver-listado-de-libros-registrados)
- [3. Listar autores registrados](#ver-listado-de-autores-registrados)
- [4. Listar autores vivos en un determinado año](#ver-listado-de-autores-vivos-en-un-determinado-año)
- [5. Listar libros por idioma](#listar-libros-por-idioma)
- [6. Estadisticas de los libros registrados](#mostrar-estadisticas-de-los-libros-registrados)
- [7. Listar libros más descargados de la API gutendex](#listar-libros-más-descargados-de-la-API-gutendex)
- [8. Listar libros más descargados de nuestro registro](#listar-libros-más-descargados-de-la-base-de-datos)
- [9. Buscar autor por nombre](#buscar-autor-por-nombre)

    
A continuación exploramos cada opción del menú:

### Buscar libro por titulo

Primero se realiza una consulta a la API gutendex para buscar su libro. La app de fondo valida si el libro ya se encuentra registrado en la db, en tal caso solo mostrará el libro que ya esta registrado para evitar duplicados. 
De misma forma, se toma la lista de autores y se realiza una busqueda en la db, asi, si el autor ya existe lo traemos para hacer la relación con el nuevo libro (si no esta registrado el libro) o se crea el nuevo autor en caso de no encontrar ninguno en la db.
Una vez completadas todas las acciones, imprime en consola la nueva instancia creada.

Es importante notar que no es necesario anotar el titulo completo del libro.
Ejemplo de uso:
![image](https://github.com/PatoProgramador/Literatura-consola-app/assets/72218702/e83ed942-0646-479e-9e8c-969273099a84)

### Ver listado de libros registrados

Se realiza una consulta a la base de datos trayendo todos los libros registrados, posterior a ello se imprimen en consola todos. Finalmente, muestra cuantos libros hay registrados en total.
Se muestran en orden de registro.
Ejemplo de uso:

![image](https://github.com/PatoProgramador/Literatura-consola-app/assets/72218702/24b4b578-af1e-496c-aa75-c4c17d7328f6)
![image](https://github.com/PatoProgramador/Literatura-consola-app/assets/72218702/f6f4d6ae-8fc5-4894-8a8e-4721f4597cf7)

### Ver listado de autores registrados

Esta función actua igual que la de listar libros, se realiza una consulta a la db para traer a todos los autores e imprimirlos.
Adicionalmente muestra todos los libros relacionados a cada autor.
Ejemplo de uso:

![image](https://github.com/PatoProgramador/Literatura-consola-app/assets/72218702/e8ac09e7-6f26-4eb3-940e-5281bd919225)

### Ver listado de autores vivos en un determinado año

Se realiza una consulta a los autores de la base de datos filtrandolos por dos condiciones: 1) que su año de nacimiento sea menor o igual al parametro dado. 2) que su año de fallecimiento sea mayor o igual al parametro dado.
Así, se garantiza que el listado resultante de autores sean aquellos que estuvieron vivos al rededor del año dado.
Ejemplo de uso:
![image](https://github.com/PatoProgramador/Literatura-consola-app/assets/72218702/05ed6eba-e44d-4389-961f-91a9561c0870)

### Listar libros por idioma

En principio se muestra un pequeño menu con los idiomas disponibles:
![image](https://github.com/PatoProgramador/Literatura-consola-app/assets/72218702/1e5ae9e8-a79c-4acd-a72a-b0007febff74)

Posterior a ingresar la abrevitura, se traera de la base de datos el listado de libros que tengan este idioma en su columna "language". Ejemplo:
![image](https://github.com/PatoProgramador/Literatura-consola-app/assets/72218702/dc95fc88-626f-4fea-99eb-a915682957ce)

### Mostrar estadisticas de los libros registrados

En esta opcion se toma todo el listado de libros registrados en la base de datos, se toma la propiedad de "downloads" de cada uno para poder generar las estadisticas pertinentes a partir de la clase DoubleSummaryStatistics de Java.

Finalmente, se presenta un resumen de las estadisticas más importantes en cuestion de descargas de todos los libros registrados, como la media, el dato maximo, etc.
Ejemplo de uso:
![image](https://github.com/PatoProgramador/Literatura-consola-app/assets/72218702/7055a276-8d16-4e61-a000-afd7b1e10f48)


### Listar libros más descargados de la API gutendex

Aquí traemos el listado general de libros de la API gutendex y filtramos los 10 más descargados. Por defecto la API misma trae en su primera página los trae por los más descargados en orden descendente, asi que solo traemos los primeros 10.
Ejemplo de uso:
![image](https://github.com/PatoProgramador/Literatura-consola-app/assets/72218702/915bb816-393e-4ea3-b78c-1510271e52bf)

### Listar libros más descargados de la base de datos

Se hace una consulta a la base de datos para que traiga de forma descendente 10 libros segun la cantidad de descargas, asi, obtenemos los 10 más descargados que hay registrados.
Ejemplo de uso:
![image](https://github.com/PatoProgramador/Literatura-consola-app/assets/72218702/2f8f5394-1442-467f-8507-1633e1bdfdc7)

### Buscar autor por nombre

Aquí damos uso de las JPA Derived queries para consultar un autor por nombre en la db a partir de si el nombre esta contenido, ignorando el case sensitive.
Esta funcionalidad de usó en la primera opcion para poder buscar autores registrados y decidir si crearlos o no.
Ejemplo de uso:
![image](https://github.com/PatoProgramador/Literatura-consola-app/assets/72218702/43abfec0-6884-4f7b-a1ac-d45a5c2fe431)

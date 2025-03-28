# PARCIAL2TERCIO: Arquitecturas Empresariales

Diseñe un prototipo de sistema de microservicios que tenga un servicio (En la figura se representa con el nombre Math Services) para computar las funciones numéricas.  El servicio de las funciones numéricas debe estar desplegado en al menos dos instancias virtuales de EC2. Adicionalmente, debe implementar un service proxy que reciba las solicitudes de llamado desde los clientes  y se las delega a las dos instancias del servicio numérico usando un algoritmo de round-robin. El proxy deberá estar desplegado en otra máquina EC2. Asegúrese que se pueden configurar las direcciones y puertos de las instancias del servicio en el proxy usando variables de entorno del sistema operativo.  Finalmente, construya un cliente Web mínimo con un formulario que reciba el valor y de manera asíncrona invoke el servicio en el PROXY. Puede hacer un formulario para cada una de las funciones. El cliente debe ser escrito en HTML y JS.

Sus servicios matemáticos deben incluir dos funciones.
Una para calcular los factores de un número: factors(n) retorna un json con una lista de números enteros positivos. (Recibe solo enteros positivos)
Una para calcular los números primos hasta un número dado: primes(n), retorna en un json los números primos menores o iguales a n.

PARA AMBAS IMPLEMENTACIONES USE UN ALGORITMO  DE FUERZA BRUTA, ES DECIR EXPLORE CADA UNO DE LOS VALORES. Usted debe implemntar las dos funciones, no debe usar funciones de una librería o del API (si ya existen).

Por ejemplo, para un  número dado n los factores se pueden calcular así:
1 es un factor de todos los números
de ahí en adelante simplemente mirando el módulo puede verificar si es o no factor.
Puede mirar todos los numeros hasta n/2
n pertenece también a los factores.
Para los primos, puede usar su función de factores así:

1 es un número primo
de ahí en adelante recuerde que un número es primo si solo es divisible por 1 y por si mismo.
Es decir un número es primo si el tamaño del conjunto de factores es 2.

---

###  Prerrequisitos

Para elaborar este proyecto requerimos de las siguientes tecnologías:

- **[Maven](https://openwebinars.net/blog/que-es-apache-maven/)**: Apache Maven es una herramienta que estandariza la configuración de un proyecto en todo su ciclo de vida.

- **[Git](https://learn.microsoft.com/es-es/devops/develop/git/what-is-git)**: Es un sistema de control de versiones distribuido (VCS).

- **[Spring-boot](https://spring.io/guides/gs/rest-service)**: Entorno para realizar proyectos


---

###  Instalación
Primero clonamos el repositorio

git clone https://github.com/lalaro/PARCIAL2TERCIO.git

Se accede al repositorio que acabamos de clonar

cd Parcial-2

---
## Casos de uso 

EC2
https://amazonxxx.x.xxx.x.xxx:{port}/factors?value=13

Salida. El formato de la salida y la respuesta debe ser un JSON con el siguiente formato

{
"operation": "factors",
"input":  15,
"output":  "1,3,5,15"
}

Ejemplo 2 de un llamado:

EC2
https://amazonxxx.x.xxx.x.xxx:{port}/factors?value=112

Salida. El formato de la salida y la respuesta debe ser un JSON con el siguiente formato

{
"operation": "factors",
"input":  112,
"output":  "1, 2, 4, 7, 8, 14, 16, 28, 56, 112"
}

Ejemplo 3 de un llamado:

EC2
https://amazonxxx.x.xxx.x.xxx:{port}/primes?value=100

Salida. El formato de la salida y la respuesta debe ser un JSON con el siguiente formato

{
"operation": "primes",
"input":  100,
"output":  "2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89,97"
}

###  Run

Localmente

https://localhost:8080/factors?value=13

En AWS:

http://ec2-184-72-207-227.compute-1.amazonaws.com:42000/collatzsequence?number=6

Un [video]() de muestra del funcionamiento.



---

##  Construido con

* **[Maven](https://maven.apache.org/)**: Apache Maven es una herramienta que estandariza la configuración de un proyecto en todo su ciclo de vida.

* **[Git](https://rometools.github.io/rome/)**: Es un sistema de control de versiones distribuido (VCS).

* **[IntelliJ](https://www.jetbrains.com/idea/)**: Es un entorno de desarrollo integrado para el desarrollo de programas informáticos.

* **[Java 17](https://www.java.com/es/)**: Lenguaje de programación de propósito general, es decir, que sirve para muchas cosas, para web, servidores, aplicaciones móviles, entre otros. Java también es un lenguaje orientado a objetos, y con un fuerte tipado de variables.

- **[Spring-boot](https://spring.io/guides/gs/rest-service)**: Entorno para realizar proyectos

##  Autor

**[Laura Valentina Rodríguez]** - [Funciones matematicas](https://github.com/lalaro/PARCIAL2TERCIO.git)

##  Licencia

**©** Laura Valentina Rodríguez Ortegón, Estudiante de Ingeniería de Sistemas de la Escuela Colombiana de Ingeniería Julio Garavito.



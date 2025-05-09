# Proyecto de Bases de datos
Este repositorio es sobre un proyecto con una bases de datos con java y mysql.
# Creación de la base de datos
Vamos a hacer una base de datos sobre F1
## Hacer diagrama de entidades
Este seria el diagrama de entidades hecho con mermaid
```mermaid
erDiagram
Piloto{
    CodPil int PK
    NomPil string
    Ape1Pil string
    Ape2Pil string 
    CodEq int
}
Equipo{
    CodEq int PK
    NomEq string
}

Piloto}o--o|Equipo : esta
Equipo}o--o|Motor : usa
resultado||--o{GrandPrix : tiene
resultado||--o{Piloto : tiene
Motor{
    CodMot int PK
    NomMot String
}
resultado{
    CodPil int PK
    CodGp int PK
    Pos int
}
GrandPrix{
    CodGp int PK
    NomGP string
    FecGP date
    numGp int
}
```
# programa
El programa sera de gestión de la temporada con calculado de ranking de equipos y de pilotos,también se calculara los puntos según el sistema actual,también se permitirá insertar datos en cada una de las tablas.
## Menu
El programa nada mas iniciar el programa mostrara el siguiente menu:
```
1.Listar datos
2.Ver ranking
3.insertar en una tabla 
4.eliminar un campo
5.actualizar un campo
6.salir
```
<details>
  <summary><h1>Listar datos<h1></summary>
  La función de listar datos abrirá el siguiente submenu preguntando que datos quieres mostrar:

  ```
  1.pilotos
  2.Equipos
  3.Motor
  4.Resultados
  5.GPs
  ```
  a continuación enseñare que hace cada uno de los apartados
  <details>
  <summary><h2>Pilotos<h2></summary>
  en este menu se mostrara otro submenu preguntando como lo quieres listar 

  ```
  1.Listar todos los pilotos
  2.Listar los pilotos de un equipo
  ```
  a continuación enseñare que hace cada uno de los apartados
  <details>
  <summary><h3>Listar todos los pilotos</h3></summary>
  este mostrara todos los pilotos de la tabla pilotos de la siguiente manera

  ![ejemplolistartodpil](img/listartodoslospil.png)<br>
  (no se ven todos los pilotos porque hay deslizar para verlos todos)
  </details>
  <details>
  <summary><h3>Listar los pilotos de un equipo</h3></summary>
  Esta opción mostraras los pilotos agrupados por equipo de la siguiente manera:

  ![alt text](img/listarpiloporeq.png)
  </details>
  
  </details>
  <details>
  <summary><h2>Equipos</h2></summary>
  Esta opción muestra todos los equipos de la siguiente manera:

  ![alt text](img/listareq.png)

  </details>
  <details>
  <summary><h2>Motores</h2></summary>
  Esta opción devuelve todos los motores de la siguiente manera:

  ![alt text](img/listarmot.png)
  </details>
  <details>
  <summary><h2>Resultados</h2></summary>
  Al darle te sale el siguiente menu:

  ```
  1.Listar resultados de un gp
  2.Listar resultados de un piloto
  ```
  a continuación enseñare que hace cada uno de los apartados
  <details>
  <summary><h3>Listar resultados de un gp</h3></summary>
  Te pide el código del GP y te pone una confirmación:
  

  ![alt text](img/confirmarcodgp.png)<br>
  tras lo cual te pone los resultados del gp
  ![alt text](img/resporgp.png)
  </details>
  <details>
  <summary><h3>Listar resultados de un piloto</h3></summary>

  te pregunta el código del piloto y te envia un mensaje de confirmación:

  ![alt text](img/confirmacionpil.png)<br>
  y te pone los resultados: 

  ![alt text](img/reporpil.png)

  </details>
  
  </details>
  <details>
  <summary><h2>GP</h2></summary>
  al darle mostrara todo lo gps:

  ![alt text](img/lisgp.png)
  </details>

  </details>
  
</details>
<details>
<summary><h1>Mostrar Ranking</h1></summary>
Al darle saldrá el siguiente menu:

```
1.Ranking de pilotos
2.Ranking de equipos
3.Ranking de motores
```
Para los ranking se utiliza el sistema de puntos actual(sin punto por vuelta rápida):
| posicion | puntos |
| -------- | ------ |
| 1        | 25     |
| 2        | 18     |
| 3        | 15     |
| 4        | 12     |
| 5        | 10     |
| 6        | 8      |
| 7        | 6      |
| 8        | 4      |
| 9        | 2      |
| 10       | 1      |

<details>
<summary><h2>Ranking de pilotos</h2></summary>
Te muestra el ranking de la temporada por pilotos:

![alt text](img/rankingpil.png)

</details>
<details>
<summary><h2>Ranking de equipos</h2></summary>
muestra el ranking por equipos sumando los puntos de ambos pilotos de cada equipo:

![alt text](img/rankingeq.png)
</details>
<details>
<summary><h2>Ranking de motores</h2></summary>
muestra el ranking por motores sumando los puntos de los equipos que lleven ese motor:

![alt text](img/rankingmot.png)

</details>
</details>
<details>
<summary><h1>Insertar datos</h1></summary>
Al darle sale el siguiente menu se abre el siguiente submenu 

```
1.Equipos
2.Motor
3.pilotos
4.resultados
```
<details>
<summary><h2>Equipos</h2></summary>
Al darle te preguntara el nombre del equipo que deseas poner

![alt text](img/preguntapornom.png)<br>
despues te pregunta por el codigo del motor al ponerlo te pondra una confirmacion con el nombre del motor:
![alt text](img/confirmacionmot.png)


</details>
<details>
<summary><h2>Motor</h2></summary>
Al darle pregunta por el nombre del motor

![alt text](img/pedirnommot.png)<br>
al poner el nombre ya se guardaría ya que no tiene nada mas los motores
</details>
<details>
<summary><h2>Pilotos</h2></summary>
Al darle primero pide el nombre del piloto:

![alt text](img/pedirnompil.png)<br>
Después pregunta por el apellido del piloto

![alt text](img/pedirsurpil.png)<br>
Luego pedirá la fecha de nacimiento con formato dd/MM/yyyy

![alt text](img/pedirfecnac.png)<br>
luego pedira el codigo de equipo del cual pondra un mensaje de confirmacion con el nombre del equipo:

![alt text](img/confirmeq.png)

</details>
<details>
<summary><h2>Resultado</h2></summary>
Al darle te pide el código de gp al ponerlo lanza un mensaje de confirmación:

![alt text](img/confirmarcodgp.png)


</details>
</details>


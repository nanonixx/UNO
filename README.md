# UNO

_UNO es un programa que simula el clásico juego de cartas con el mismo nombre.  Está pensado para jugarse en una terminal de comandos_



### CÓMO COMPILAR Y EJECUTAR

#### Compilar

Go to {@code src/} folder and write:

 javac com/company/Main.java

#### Ejecutar

java com.company.Main



### CÓMO FUNCIONA

Al ejecutar el programa se abre un menú. Desde allí puedes ver las reglas del juego, comenzar una partida nueva o ver el registro del juego anterior.  Para elegir una opción debes introducir el número correspondiente.


### INSTRUCCIONES

#### Comienzo del juego

Cada jugador empieza con 7 cartas en su mano. Habrá un mazo para robar y otro mazo de juego, cuya primera carta será una carta aleatoria del mazo de robar (TOP CARD). El primer turno será siempre del jugador humano.

#### Decurso del juego

En cada turno el jugador tiene dos opciones: jugar una carta de cambio de color o que tenga el mismo valor o color que la carta del mazo de juego (TOP CARD) seleccionando el número al lado de dicha carta; o robar una carta del mazo para robar (escribiendo “0” en el teclado).

Si al robar una carta dicha carta es jugable, existe la opción de jugarla en ese momento.

Antes de jugar la última carta se tendrá que escribir “UNO” en el teclado, de lo contrario la útlima carta no se jugará, se robará una carta del mazo y se saltará el turno.

#### Objetivo del juego

Ganará el jugador que antes se quede sin cartas en su mano. En todo momento se podrá ver el número de cartas que tiene la máquina.

#### Cartas especiales

Hay algunas cartas que hacen acciones especiales al jugarlas:

TOMA DOS [ +2 ] : Si es jugada el otro jugador deberá robar dos cartas y se le saltará el turno.

PASA TURNO [ ø ]: Si es jugada se saltará el turno del otro jugador.

CAMBIO DE SENTIDO [ ⇆ ] : Si es jugada se cambiará el sentido de juego. En este caso, al ser solamente dos jugadores funciona igual que el “pasa turno”.

CAMBIO DE COLOR [ cc ]: Si es jugada el jugador podrá elegir el color que quiere que esté en juego."

MÁS CUATRO [ +4 ]: Si es jugada el otro jugador deberá robar cuatro cartas y se saltará el turno. Además el jugador podrá elegir el color que quiere que esté en juego.


### Repositorio

_https://github.com/nanonixx/UNO_

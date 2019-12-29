# Battleships
Authors: Karolina Dąbrowska & Dawid Michałowski
## Building executable jar
```
mvn clean compile assembly:single
```
## JVM run options
```
-Dbattleships.columns=10 <- sets column number to 10 (min value 2)  
-Dbattleships.rows=10 <- sets row number to 10 (min value 2)
```
## Game commands explanation
```
ship: Phase: PREPARATION, places a ship in location. Example: ship A1
unship: Phase: PREPARATION, remove ship from location. Example: unship A1
random: Phase: PREPARATION, places ships on random location. Example: random
clear: Phase: PREPARATION, remove all ships from game board. Example: clear
start: Phase: PREPARATION, starts the game (changes phase to IN_PROGRESS). Example: start
shoot: Phase: IN_PROGRESS, shoots a ship. Example: shoot A1
exit: Phase: ANY, exit game. Example: exit
help: Phase: ANY, prints help. Example: help
```
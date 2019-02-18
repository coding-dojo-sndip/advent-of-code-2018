# Jour 5

## Partie 1

En entrée, on dispose d'une liste de point définis par leurs coordonnées en deux dimensions.

En utilisant la [distance de Manhattan](https://fr.wikipedia.org/wiki/Distance_de_Manhattan), déterminer la zone autour de chaque point en comptant le nombre de positions qui sont le plus proche de ce point et qui ne sont pas à équidistance de plusieurs points.

L'objectif est de déterminer la taille de la plus grande zone qui ne soit pas infinie.
Par exemple, considéront la liste de coordonnées suivante :

```
1, 1
1, 6
8, 3
3, 4
5, 5
8, 9
```

Si on nomme les points correspondant avec des lettres de `A` à `F`, on peut les placer sur une grille dont l'origine `0, 0` est en haut à gauche :

```
..........
.A........
..........
........C.
...D......
.....E....
.B........
..........
..........
........F.
```

Cette représentation est partielle – elle s'étend en réalité infiniment dans les quatres sens.
En utilisant la distance de Manhattan, chaque position peut être associée à son point le plus proche en utilisant la lettre correspondante en minuscule :

```
aaaaa.cccc
aAaaa.cccc
aaaddecccc
aadddeccCc
..dDdeeccc
bb.deEeecc
bBb.eeee..
bbb.eeefff
bbb.eeffff
bbb.ffffFf
```

Les positions marquées par un `.` sont situées à égale distance d'au moins deux points et ne compte donc pas comme étant le plus proche d'aucun.

Dans cet exemple, les zones des coordonnée A, B, C et F sont infinies.
Cependant, les zones des coordonnées D et E sont finies : D est le plus proche de 9 positions et E est plus proche de 17 (dans les deux cas, le point lui-même entre dans le décompte).
Donc dans cet exemple, la taille de la zone la plus grande est 17.

__Quelle est la taille de la zone la plus grande qui ne soit pas infinie ?__
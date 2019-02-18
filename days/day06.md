# Jour 5

## Partie 1

En entrée, on dispose d’une liste de point définis par leurs coordonnées en deux dimensions.

En utilisant la [distance de Manhattan](https://fr.wikipedia.org/wiki/Distance_de_Manhattan), déterminer la zone autour de chaque point en comptant le nombre de positions qui sont le plus proche de ce point et qui ne sont pas à équidistance de plusieurs points.

L’objectif est de déterminer la taille de la plus grande zone qui ne soit pas infinie.
Par exemple, considéront la liste de coordonnées suivante :

```
1, 1
1, 6
8, 3
3, 4
5, 5
8, 9
```

Si on nomme les points correspondant avec des lettres de `A` à `F`, on peut les placer sur une grille dont l’origine `0, 0` est en haut à gauche :

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

Cette représentation est partielle – elle s’étend en réalité infiniment dans les quatres sens.
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

Les positions marquées par un `.` sont situées à égale distance d’au moins deux points et ne compte donc pas comme étant le plus proche d’aucun.

Dans cet exemple, les zones des coordonnée A, B, C et F sont infinies.
Cependant, les zones des coordonnées D et E sont finies : D est le plus proche de 9 positions et E est plus proche de 17 (dans les deux cas, le point lui-même entre dans le décompte).
Donc dans cet exemple, la taille de la zone la plus grande est 17.

__Quelle est la taille de la zone la plus grande qui ne soit pas infinie ?__

## Partie 2

Pour chaque position, on considère la somme des distances de Manhattan qui la sépare de chaque point de la liste en entrée.
On se fixe un seuil, par exemple 32 et dénombre les positions pour lesquelles cette somme est inférieur au seuil fixé.
À partir de la même liste de points que précédemment, on obtient la zone suivante :

```
..........
.A........
..........
...#*#..C.
..#D###...
..###E#...
.B.###....
..........
..........
........F.
```

En particulier, si on considère la position marquée d’une étoile situé aux coordonnées `3,4`, elle a été inclue dans la zone suite au calcul suivant :

 - Distance a point A : `abs(4-1) + abs(3-1) =  5`
 - Distance a point B : `abs(4-1) + abs(3-6) =  6`
 - Distance a point C : `abs(4-8) + abs(3-3) =  4`
 - Distance a point D : `abs(4-3) + abs(3-4) =  2`
 - Distance a point E : `abs(4-5) + abs(3-5) =  3`
 - Distance a point F : `abs(4-8) + abs(3-9) = 10`
 - Distance totale : `5 + 6 + 4 + 2 + 3 + 10 = 30`

Comme la somme des distances (30) est inférieure au seuil (32), la position est située dans la zone.

Cette zone, qui comprend aussi les points D et E, a une surface totale de 16.

Pour le calcul de la zone réelle, on se fixe un seuil de `10000`.

__Quelle est la taille de la zone contenant toutes les positions qui ont une distance totale les séparants de tous les points inférieure strictement à 10000 ?__
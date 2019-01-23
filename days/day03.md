# Jour 3

## Partie 1

On considère une grande pièce de tissu carrée de 1&nbsp;000 centimètres de côté.

En entrée, on dispose d’une liste de rectangles dont les côtés sont parallèles à ceux de la pièce de tissu.
Chaque rectangle est défini par :
 - la distance en centimètres entre la gauche de la pièce de tissu et la gauche du rectangle ;
 - la distance en centimètres entre le haut de la pièce de tissu et le haut du rectangle ;
 - la largeur du rectangle en centimètres ;
 - la hauteur du rectangle en centimètres.

Le rectangle défini ainsi : `#123 @ 3,2: 5x4` est le rectangle d’identifiant `123`, situé à `3` cm du bord gauche de la pièce de tissu, à `2` cm du haut, de `5` cm de large et de `2` cm de haut.

Graphiquement, si `#` représente un centimètre carré de rectangle et `.` un centimètre carré de la pièce de tissu hors du rectangle, on obtient le diagramme suivant :

```
...........
...........
...#####...
...#####...
...#####...
...#####...
...........
...........
...........
```

Beaucoup des rectangles se superposent. Par exemple, considérons les trois rectangles suivants :

```
#1 @ 1,3: 4x4
#2 @ 3,1: 4x4
#3 @ 5,5: 2x2
```

Visuellement, on peut les représenter ainsi :
```
........
...2222.
...2222.
.11XX22.
.11XX22.
.111133.
.111133.
........
```

Les `X` représentant les points de superposition entre rectangles. Il y a quatre centimètres carrés de tissu appartenant à au moins deux rectangles (le `1` et le `2`).

__Combien de centimètres carrés de tissu appartiennent à au moins deux rectangles ?__

## Partie 2

Il n’existe qu’un seul rectangle qui ne se superpose à aucun autre. Dans l'exemple précédent, il s'agit du rectangle dont l'identifiant est `3`.

__Quel est l’identifiant de l’unique rectangle qui ne se superpose à aucun autre ?__


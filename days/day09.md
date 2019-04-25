# Jour 9

## Partie 1

Le jeu favori des elfes est un jeu de bille.

Ils y jouent en plaçant chacun à leur tour une bille sur un cercle dans un ordre bien particulier.
Les billes sont numérotées de `1` en `1` en partant de `0`.

D’abord la bille numérotée `0` est placée sur le cercle : elle est à la fois sa propre bille précédente sa propre bille suivante.
Cette bille est désignée comme étant la bille courante.

Ensuite, chaque elfe joue à son tour en plaçant la bille restante portant le plus petit numéro sur le cercle.
Il la place entre les billes qui se situent `1` et `2` billes plus loin dans le sens horaire.
Cela signifie qu’il y a une bille entre la bille jouée et la bille courante.
La bille jouée devient la bille courante.

Cependant, si la boule à jouer porte un numéro multiple de 23, il se passe quelque chose de complètement différent.
D’abord, le joueur courant conserve la bille qu’il aurait du placer et ajoute sa valeur à son score.
De plus, la bille située `7` billes plus loin dans le sens anti-horaire est retirée du cercle et ajoutée au score du joueur.
La bille située immédiatement après la bille retirée dans le sens horaire devient la bille courante.

Par exemple, à 9 joueurs, avec 26 billes.
Après avoir placé la bille de valeur `0` au milieu, chaque joueur (marqué entre crochets) joue à son tour.
La bille courante est marquée entre parenthèses :

```
[-] (0)
[1]  0 (1)
[2]  0 (2) 1 
[3]  0  2  1 (3)
[4]  0 (4) 2  1  3 
[5]  0  4  2 (5) 1  3 
[6]  0  4  2  5  1 (6) 3 
[7]  0  4  2  5  1  6  3 (7)
[8]  0 (8) 4  2  5  1  6  3  7 
[9]  0  8  4 (9) 2  5  1  6  3  7 
[1]  0  8  4  9  2(10) 5  1  6  3  7 
[2]  0  8  4  9  2 10  5(11) 1  6  3  7 
[3]  0  8  4  9  2 10  5 11  1(12) 6  3  7 
[4]  0  8  4  9  2 10  5 11  1 12  6(13) 3  7 
[5]  0  8  4  9  2 10  5 11  1 12  6 13  3(14) 7 
[6]  0  8  4  9  2 10  5 11  1 12  6 13  3 14  7(15)
[7]  0(16) 8  4  9  2 10  5 11  1 12  6 13  3 14  7 15 
[8]  0 16  8(17) 4  9  2 10  5 11  1 12  6 13  3 14  7 15 
[9]  0 16  8 17  4(18) 9  2 10  5 11  1 12  6 13  3 14  7 15 
[1]  0 16  8 17  4 18  9(19) 2 10  5 11  1 12  6 13  3 14  7 15 
[2]  0 16  8 17  4 18  9 19  2(20)10  5 11  1 12  6 13  3 14  7 15 
[3]  0 16  8 17  4 18  9 19  2 20 10(21) 5 11  1 12  6 13  3 14  7 15 
[4]  0 16  8 17  4 18  9 19  2 20 10 21  5(22)11  1 12  6 13  3 14  7 15 
[5]  0 16  8 17  4 18(19) 2 20 10 21  5 22 11  1 12  6 13  3 14  7 15 
[6]  0 16  8 17  4 18 19  2(24)20 10 21  5 22 11  1 12  6 13  3 14  7 15 
[7]  0 16  8 17  4 18 19  2 24 20(25)10 21  5 22 11  1 12  6 13  3 14  7 15
```

Le but du jeu est d’avoir le plus haut score après que la dernière bille a été jouée.
Dans cet exemple, la dernière bille à être jouée est numérotée `25`, le score gagnant est `23 + 9 = 32` (puisque le joueur `5` a gardé la bille `23` et retiré la bille `9`, alors qu’aucun autre joueur n’a marqué de point durant cette courte partie).

Voici quelques autres exemples :

 - `10` jouers ; dernière bille valant `1618` points : le meilleur score est `8317`,
 - `13` jouers ; dernière bille valant `7999` points : le meilleur score est `146373`,
 - `17` jouers ; dernière bille valant `1104` points : le meilleur score est `2764`,
 - `21` jouers ; dernière bille valant `6111` points : le meilleur score est `54718`,
 - `30` jouers ; dernière bille valant `5807` points : le meilleur score est `37305`.
 
__Quel est le meilleur score à la fin de la partie ?__

## Partie 2

__Quel est le meilleur score à la fin de la partie si la dernière bille vaut 100 fois plus ?__

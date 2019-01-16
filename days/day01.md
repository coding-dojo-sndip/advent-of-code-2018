# Jour 1

## Partie 1

En entrée, vous disposez d'une suite de changement de fréquence.

Une valeur de `+6` signifie que la fréquence augmente de `6` ; une fréquence de `-3` signifie que la fréquence diminue de `3`.

Par exemple, si la suite de changement est `+1, -2, +3, +1` voici ce qu'il va se passer :

 * fréquence courante :  `0`, changement de +1 ; fréquence résultante :  `1`,
 * fréquence courante :  `1`, changement de -2 ; fréquence résultante : `-1`,
 * fréquence courante : `-1`, changement de +3 ; fréquence résultante :  `2`,
 * fréquence courante :  `2`, changement de +1 ; fréquence résultante :  `3`.

Dans cet exemple, la fréquence finale est `3`.

Autres exemples :

 * `+1, +1, +1` donne  `3`
 * `+1, +1, -2` donne  `0`
 * `-1, -2, -3` donne `-6`
 
En commençant avec une fréquence de zéro, __quelle est la fréquence résultante__ après avoir appliqué tous les changements de fréquence ?
 
 ## Partie 2
 
En fait, la liste de changement se répète infiniment.
 
Il faut trouver la première fréquence à être atteinte deux fois.
 
Par exemple, en utilisant la même liste que précédemment, `+1, -2, +3, +1`, les changements sont appliqués en boucle de la manière suivante :
 
  * fréquence courante  `0`, changement de `+1` ; fréquence résultante  `1`,
  * fréquence courante  `1`, changement de `-2` ; fréquence résultante `-1`,
  * fréquence courante `-1`, changement de `+3` ; fréquence résultante  `2`,
  * fréquence courante  `2`, changement de `+1` ; fréquence résultante  `3`,
  * (À ce momen`t`, on reprend la liste au début)
  * fréquence courante  `3`, changement de `+1` ; fréquence résultante  `4`,
  * fréquence courante  `4`, changement de `-2` ; fréquence résultante  `2`, déjà vue à l'étape 3.
  
Autres exemples :
  
 * `+1, -1` atteint en premier `0` deux fois
 * `+3, +3, +4, -2, -4` atteint en premier `10` deux fois,
 * `-6, +3, +8, +5, -6` atteint en premier `5` deux fois,
 * `+7, +7, -2, -7, -4` atteint en premier `14` deux fois.
  
  __Quelle est la première fréquence atteinte deux fois ?__
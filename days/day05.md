# Jour 4

## Partie 1

On considère un polymère composé de plus petites unités, qui, une fois activées réagissent entre elles de telle manière que deux unités du même type mais de polarités opposées sont détruites.

Le type des unités est représenté par une lettre ; la polarité des unités est représentée par la casse.

Par exemple, `r` et `R` sont des unités du même type mais de polarité opposées, alors que `r` et `s` sont de type différents et ne réagissent pas.

Par exemple :

 - Dans `aA`, `a` et `A` réagissent, ne laissant plus rien.
 - Dans `abBA`, `bB` se détruit, laissant `aA`, qui comme au-dessus, se détruit, ne laissant rien.
 - Dans `abAB`, aucune unité n'est adjacente à une unité du même type donc rien ne se passe.
 - Dans `aabAAB`, bien que `aa` et `AA` soient du même type, leur polarité sont identiques donc rien ne se passe.
 
 Considérons un exemple plus large, `dabAcCaCBAcCcaDA` :
 
```
dabAcCaCBAcCcaDA  Le premier 'cC' est supprimé.
dabAaCBAcCcaDA    Cela crée 'Aa', qui est supprimé.
dabCBAcCcaDA      Soit 'cC' ou 'Cc' est supprimé (le résultat est le même).
dabCBAcaDA        Plus rien ne se passe.
```

Après toutes les réactions possibles, le polymère résultant contient 10 unités.

__Combien d’unités subsistent après que le polymère a totalement réagit ?__

## Partie 2

Un type d’unité empêche le polymer de se réduire autant qu’il le pourrait.
L’objectif est de déterminer lequel, en retirant toutes les occurences de ce type, quelque soit leur polarité, puis en faisant réagir tout le polymère et en mesurant sa longueur après réaction complète.

Par exemple en utilisant le même polymère `dabAcCaCBAcCcaDA` que précédemment :

 - Supprimer les unités `A/a` produit `dbcCCBcCcD`. La réaction complète de ce polymère donne `dbCBcD`, de longueur 6.
 - Supprimer les unités `B/b` produit `daAcCaCAcCcaDA`. La réaction complète de ce polymère donne `daCAcaDA`, de longueur 8.
 - Supprimer les unités `C/c` produit `dabAaBAaDA`. La réaction complète de ce polymère donne `daDA`, de longueur 4.
 - Supprimer les unités `D/d` produit `abAcCaCBAcCcaA`. La réaction complète de ce polymère donne `abCBAc`, de longueur 6.
 
 Dans cet exemple, retirer les unités `C/c` était le meilleur choix, donnant une longueur finale de 4.
 
 __Quelle est la longueur du plus petit polymère qui peut être produit __en retirant toutes les unités d’un type et en faisant totalement réagir le polymère obtenu ?
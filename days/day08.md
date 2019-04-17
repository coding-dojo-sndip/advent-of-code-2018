# Jour 8

## Partie 1

En entrée, on dispose d’une série de nombres.
Ces nombres définissent une structure arborescente.

L’arbre est composé de nœuds ; un nœud unique englobant tous les autres forme la racine de l’arbre.

Plus en détail, un nœud est constitué par

 - un entête, qui contient toujours exactement deux nombres :
   - la quantité de nœud fils,
   - la quantité de métadonnées ;
 - zéro ou plusieurs nœuds fils (comme spécifié dans l’entête),
 - une ou plusieurs métadonnées (comme spécifié dans l’entête).

Chaque nœud fils contient lui-même son propre entête, ses nœuds fils et ses métadonnées.
Par exemple :

```
2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2
A----------------------------------
    B----------- C-----------
                     D-----
```

Dans cet exemple, chaque nœud de l’arbre est souligné et identifié par une lettre.
Ici, il y a quatre nœuds :

 - `A`, qui possède `2` nœuds fils, (`B` et `C`) et `3` métadonnées, (`1`, `1`, `2`) ;
 - `B`, qui possède `0` nœuds fils, et `3` métadonnées, (`10`, `11`, `12`) ;
 - `C`, qui possède `1` nœuds fils, (`D`) et `1` métadonnée, (`2`) ;
 - `D`, qui possède `0` nœuds fils, et `1` métadonnées, (`99`) ;
 
__Quelle est la somme de toutes les métadonnées__ de l’arbre ?

## Partie 2

Désormais il faut trouver la valeur du nœud racine.

La valeur d’un nœud dépend de s’il possède des nœuds fils :

Si un nœud ne possède pas de fils, sa valeur est celle de la somme de ses métadonnées ;

Si un nœud possède des fils, alors les métadonnées sont les indexes des fils qui entrent dans le calcul de la valeur.
Une métadonnée valant `1` fait référence au premier nœud fils, une ne métadonnée valant `2` fait référence au second nœud fils, et ainsi de suite.
Si le nœud référencé n’existe pas, la référence est ignorée.
Un nœud fils peut être référencé plusieurs fois et compte autant de fois qu’il est référencé.
Une métadonnée valant `0` ne fait référence à aucun fils.

Par exemple, en utilisant le même arbre que précédemment :

 - `C` possède une métadonnée, `2`.
 Comme `C` ne possède qu’un seul fils, `2` fait référence à un nœud fils inexistant et donc la valeur de `C` est `0`.
 - A possède trois métadonnées, `1`, `1` et `2`.
 Le `1` fait référence au premier fils de `A`, c’est-à-dire `B`, et le `2` fait référence au second fils de `A`, c’est-à-dire `C`.
 Comme `B` vaut `33` et `C` vaut `0`, la valeur de `A` est `33+33+0=66`.
 
Donc dans cet exemple, la valeur du nœud racine est `66`.

__Quelle est la valeur du nœud racine ?__
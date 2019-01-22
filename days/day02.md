# Jour 2

## Partie 1

En entrée, on dispose d’une liste d’identifiants.

Il faut compter les identifiants qui comportent au moins une lettre en double ; puis compter séparemment les identifiants qui comportent au moins une lettre en triple.

Par exemple avec la liste d’identifiants suivante :

 * `abcdef` ne contient aucune lettre en double ou en triple,
 * `bababc` contient deux `a` et trois `b` donc il compte deux fois,
 * `abbcde` contient deux `b`, mais aucune lettre en triple,
 * `abcccd` contient trois `c`, mais aucune lettre en double,
 * `aabcdd` contient deux `a` et deux `d`, mais cela ne compte qu’un seule fois,
 * `abcdee` contient deux `e`
 * `ababab` contient trois `a` et trois `b`, mais cela ne compte qu’un seule fois.

Dans cette liste d’identifiants, quatre d’entre eux contiennent une lettre en double et trois contiennent une lettre en triple.
En multipliant ces deux nombres ensembles on obtient une somme de controle de `4 * 3 = 12`.

__Quelle est la somme de contôle__ de la liste d’identifiants ?

## Partie 2

Dans la liste d’identifiants, il y a un unique couple d’identifiants presque identiques qui ne diffèrent que par un seul caractère.

Par exemple :

```
abcde
fghij <--
klmno
pqrst
fguij <--
axcye
wvxyz
```

Les lettres en commun de ces deux identifiants sont `fgij`

__Quelles sont les lettres communes entre les deux identifiants__ presque identiques ?
# Jour 7

## Partie 1

Il s'agit de construire une luge à partir d'un manuel détaillant l'enchainement des étapes de l'assemblage.

En entrée, on dispose d'une série de prérequis concernant les étapes qui doivent être terminées avant que les suivantes puissent débuter.
Chaque étape est identifiée par une lettre capitale.
Par exemple, supposons que nous disposions des instructions suivantes :

```
Step C must be finished before step A can begin.
Step C must be finished before step F can begin.
Step A must be finished before step B can begin.
Step A must be finished before step D can begin.
Step B must be finished before step E can begin.
Step D must be finished before step E can begin.
Step F must be finished before step E can begin.
```

Visuellement, les étapes s'enchainent de la manière suivante :

```
  -->A--->B--
 /    \      \
C      -->D----->E
 \           /
  ---->F-----
```

Notre premier objectif est de déterminer l'ordre dans lequel les étapes doivent être réalisées.
Si plus d'une étape est prête à être réalisée, il faut choisir la première par ordre alphabétique.
Dans cet exemple, les étapes doivent être réalisées de la manière suivante :

 - Au début, seule `C` est disponible donc elle est réalisée en premier.
 - Puis à la fois `A` et `F` sont disponibles. `A` étant la première dans l'ordre alphabétique, c'est elle qui est réalisée.
 - Ensuite, les étapes `B` et `C` sont aussi disponibles, en plus de `F` qui l'était déjà à l'étape précédente. C'est donc `B`, première par ordre alphabétique, qui est réalisée.
 - Après cela, seuls `D` et `F` sont disponibles. `E` n'est pas disponible puisque tous les étapes pré-requises ne sont pas terminées. Pour cette raison, `D` est l'étape réalisée ensuite.
 - `F` est la seule étape disponible, elle est donc réalisée.
 - Finalement, la dernière étape ,`E`, est réalisée.
 
 Dans cet exemple, l'ordre correct de réalisation des étapes est donc `CABDFE`.
 
 __Dans quel ordre les instructions doivent-elles être complétées ?__
 
 ## Partie 2
 
 Alors que l'on s'apprête à démarrer l'assemblage, quatre elfes viennent offrir leur aide.
 Désormais, il faut tenir compte du fait que plusieurs personnes peuvent travailler simultanément.
 
 Chaque étape dure 60 secondes plus un laps de temps correspondant à sa lettre : A=1, B=2, C=3 et ainsi de suite.
 Donc l'étape A dure `60 + 1 = 61` secondes, et l'étape Z dure `60 + 26 = 86` secondes.
 Il n'y a pas 
 
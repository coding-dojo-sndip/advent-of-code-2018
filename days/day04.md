# Jour 4

## Partie 1

On considère un entrepôt gardé chaque nuit par un agent. Les nuits sont réparties entre plusieurs agents, mais une nuit donnée, il n’y a qu’un seul agent qui travaille. Il peut lui arriver de s’endormir plusieurs fois durant son tour de garde, mais toujours entre minuit et une heure du matin, c’est-à-dire `00:00 - 00:59`.

On dipose en entrée d’un relevé minuté des tours de garde listant pêle-mêle l’horaire de début des tours de garde et les période d’éveil et de sommeil de l’agent de garde.

Par exemple, considérons le relevé suivant qui a été au préalable trié par ordre chronologique :

```
[1518-11-01 00:00] Guard #10 begins shift
[1518-11-01 00:05] falls asleep
[1518-11-01 00:25] wakes up
[1518-11-01 00:30] falls asleep
[1518-11-01 00:55] wakes up
[1518-11-01 23:58] Guard #99 begins shift
[1518-11-02 00:40] falls asleep
[1518-11-02 00:50] wakes up
[1518-11-03 00:05] Guard #10 begins shift
[1518-11-03 00:24] falls asleep
[1518-11-03 00:29] wakes up
[1518-11-04 00:02] Guard #99 begins shift
[1518-11-04 00:36] falls asleep
[1518-11-04 00:46] wakes up
[1518-11-05 00:03] Guard #99 begins shift
[1518-11-05 00:45] falls asleep
[1518-11-05 00:55] wakes up
```

Les dates sont écrites sous la forme `year-month-day hour:minute`. L’agent qui se réveille ou s’endort est touours celui qui a prit sont tour de garde le plus récemment.

Visuellement, le relevé montre qu’entre minuit et une heure, les agents sont endormis aux minutes suivantes :

```
Date   ID   Minute
            000000000011111111112222222222333333333344444444445555555555
            012345678901234567890123456789012345678901234567890123456789
11-01  #10  .....####################.....#########################.....
11-02  #99  ........................................##########..........
11-03  #10  ........................#####...............................
11-04  #99  ....................................##########..............
11-05  #99  .............................................##########.....
```

 - réveillé : `.`
 - endormi `#`

Trouver l’agent qui passe le plus de temps endormi au total ; puis pour cet agent, déterminer la minutes durant laquelle il est le plus souvent endormi.

Dans l’exemple précédent, l’agent #10 a passé le plus de temps endormi pour un total de 50 minutes (20 + 25 + 5), alors que le garde #99 n’a dormi que 30 minutes (10 + 10 + 10). L’agent #10 a été le plus souvent endormi durant la minute 24 (c’est arrivé lors de deux jours différents).

__Quel est l’identifiant de l’agent qui dort le plus multiplié par la minute durant laquelle il dort le plus ?__

(Dans l’exemple, la réponse serait `10 * 24 = 240`.)


## Partie 2

De tous les agents quel est celui qui dort le plus à la même minute ?

Dans l’exemple précédent, l’agent #99 dort durant la minute 45 plus souvent que n’importe quel agent à n’importe quelle minute.

__Quel est l’identifiant de l’agent qui dort le plus à la même minute multiplié par cette minute ?__

(Dans l’exemple, la réponse serait `99 * 45 = 4455`.)
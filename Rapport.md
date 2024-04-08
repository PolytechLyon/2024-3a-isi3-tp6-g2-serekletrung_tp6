# Compte Rendu du TP 1 : Patrons de Conceptions

Noms des étudiants du binôme :

## Exercices 1

C'est le pattern Composite qui reflète ce modèle. La classe `MobileObject` est un composant et la classe `Vehicle` est un composite. 
La classe TagAlongBike a été écrite dans le package "cycling". On ajoute juste à sa liste de composant un vélo simple.
Non, nous n'aurons pas besoin de réecrire les méthodes `getVelocity()` et `getMass()` pour la nouvelle classe. En effet,
comme ces méthodes utilisent les méthodes `getVelocity()` et `getMass()` des composants, elles sont déjà prêtes à être utilisées, elles
utiliseront directement le getVelocity() et getMass() du vélo simple ajouté.

## Exercices 2

## Exercices 3

Pour appliquer le pattern Singleton à la classe Clock, on commence par aller dans cette classe et déclarer une variable privée de type Clock.
On la nommera "instance". Ensuite, on déclare un constructeur privé pour empêcher la création d'instances de la classe Clock en dehors de celle-ci.
Finalement, on déclare un accesseur/getteur public getInstanc() qui retourne l'instance de la classe Clock. On déclare bien que notre variable
"instance" est static et final pour protéger son unicité et des modifications. Finalement, dans Wheel, au lieu de déclarer une nouvelle instance de
Clock, on utilise la méthode getInstance() pour récupérer l'instance de la classe Clock.

## Exercices 4

## Exercices 5

C'est fait, nous pouvons même désormais supprimer la méthode log() des classes ConsoleLogger et FileLogger.
Il faut juste redéfinir la méthode operationLog() dans ces classes.

## Exercices 6

Nous avons donc combinés le patron de méthode fabrique et la pattron Singleton. Nous avons donc utilisés une interface LoggerCreator et
avons implémentés des classes Creator pour chaque option possible de Logger avec le patron de méthode fabrique. Ensuite, nous avons utilisé
le patron Singleton pour s'assurer qu'il n'y ait qu'une seule instance de LoggerCreator.

## Exercices 7

## Exercices 8



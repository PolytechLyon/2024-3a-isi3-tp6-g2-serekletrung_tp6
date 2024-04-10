# Compte Rendu du TP 1 : Patrons de Conceptions

Noms des étudiants du binôme : LE TRUNG - SEREK

## Exercices 1

C'est le pattern Composite qui reflète ce modèle. La classe `MobileObject` est un composant et la classe `Vehicle` est un composite. 
La classe TagAlongBike a été écrite dans le package "cycling". On ajoute juste à sa liste de composant un vélo simple.
Non, nous n'aurons pas besoin de réecrire les méthodes `getVelocity()` et `getMass()` pour la nouvelle classe. En effet,
comme ces méthodes utilisent les méthodes `getVelocity()` et `getMass()` des composants, elles sont déjà prêtes à être utilisées, elles
utiliseront directement le getVelocity() et getMass() du vélo simple ajouté.

## Exercices 2

Le patron de conception qu'utilise la méthode `getVelocity()` pour parcourir les composants d'un véhicule est le patron de conception **Iterator**. Ce patron permet de parcourir les éléments d'une collection sans exposer la structure interne de cette collection. Il permet de parcourir les éléments d'une collection sans se soucier de la manière dont ils sont stockés.
Après changement des composants de set à list, la méthode `getVelocity()` reste inchangée. Cela montre que le patron de conception **Iterator** est bien utilisé.


## Exercices 3

Pour appliquer le pattern Singleton à la classe Clock, on commence par aller dans cette classe et déclarer une variable privée de type Clock.
On la nommera "instance". Ensuite, on déclare un constructeur privé pour empêcher la création d'instances de la classe Clock en dehors de celle-ci.
Finalement, on déclare un accesseur/getteur public getInstanc() qui retourne l'instance de la classe Clock. On déclare bien que notre variable
"instance" est static et final pour protéger son unicité et des modifications. Finalement, dans Wheel, au lieu de déclarer une nouvelle instance de
Clock, on utilise la méthode getInstance() pour récupérer l'instance de la classe Clock.

## Exercices 4
Les classes `Bike` et `Wheel` n'appartiennent pas au même paquetage. La classe Bike est dans le paquetage `fr.polytech.sim.cycling` tandis que la classe Wheel est dans le paquetage `fr.polytech.sim.transport`.  La classe `Wheel` est utilisée dans le constructeur de la classe `SimpleBike` (qui est une sous-classe de Bike). C'est une dépendance directe. Cependant, cette dépendance ne respecte pas le principe de séparation des préoccupations car la classe `Wheel` est dans un paquetage différent de `Bike`.  La classe Wheel utilise la méthode `getPush()` de la classe `Bike`. Cependant, cette méthode est déjà présente dans la classe `Vehicle` qui est une superclasse de `Bike`. La classe `Vehicle` est dans le même paquetage que `Wheel`, ce qui signifie qu'il y a déjà une abstraction de la classe `Bike` qui isole cette fonctionnalité.  Pour casser la dépendance cyclique entre les classes `Bike` et `Wheel`, nous pouvons faire en sorte que `Wheel` dépende de `Vehicle` plutôt que de `Bike`. Pour ce faire, nous devons modifier le constructeur de `Wheel` pour qu'il prenne une instance de `Vehicle` plutôt qu'une instance de `Bike`.  Voici comment nous pourrions modifier la classe `Wheel` : 

```java
package fr.polytech.sim.transport;

public class Wheel extends MobileObject {

    private Vehicle vehicle;

    public Wheel(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
```
Et voici comment nous pourrions modifier la classe `SimpleBike` :

```java
package fr.polytech.sim.cycling;

import fr.polytech.sim.transport.Wheel;

public class SimpleBike extends Bike {

    public SimpleBike() {
        components.add(new Wheel(this));
        components.add(new Wheel(this));
    }
}
```

## Exercices 5

C'est fait, nous pouvons même désormais supprimer la méthode log() des classes ConsoleLogger et FileLogger.
Il faut juste redéfinir la méthode operationLog() dans ces classes.

## Exercices 6

Nous avons donc combinés le patron de méthode fabrique et la pattron Singleton. Nous avons donc utilisés une interface LoggerCreator et
avons implémentés des classes Creator pour chaque option possible de Logger avec le patron de méthode fabrique. Ensuite, nous avons utilisé
le patron Singleton pour s'assurer qu'il n'y ait qu'une seule instance de LoggerCreator.

## Exercices 7

On implémente les classes `LoggerDecorator` et `TimestampedLoggerDecorator` comme suit : 
    
```java
package fr.polytech.sim.log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimestampedLoggerDecorator extends LoggerDecorator {
    public TimestampedLoggerDecorator(Logger logger) {
        super(logger);
    }

    @Override
    public void log(String format, Object... args) {
        // Add timestamp to the log
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        // Format to [dd/mm/yyy | hh:mm:ss] - message
        timestamp = "[" + timestamp.substring(0, 10) + " | " + timestamp.substring(11, 19) + "]";
        logger.log(timestamp + " - " + format, args);
    }
}
```

```java
package fr.polytech.sim.log;

public abstract class LoggerDecorator implements Logger {
    protected Logger logger;

    public LoggerDecorator(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void log(String message) {
        logger.log(message);
    }
}
```

Il nous suffit maintenant d'instancier un objet de type `TimestampedLoggerDecorator` et de lui passer un objet de type `Logger` pour obtenir un logger avec des timestamps. 
example : 
```java
private final Logger logger = new TimestampedLoggerDecorator(LoggerCreator.getLoggerCreator().create("Vehicle"));
```

## Exercise 8

La classe Context suit le patron de conception Façade vis-à-vis de l'outil ServiceLoader:
Comme dit dans le cours, la classe Context propose une interface simplifiée de ServiceLoader, avec un ensemble restreint de ses fonctionnalités.

Oui, il est possible d'avoir plusieurs lignes dans fr.polytech.sim.cycling.Bike, cependant, seule la première sera prise en compte dans notre cas.
La première ligne correspond en fait à la classe à injecter lorsque la classe Bike est demandé.

## Exercise 9

La méthode injectAll() retourne un Iterable. Cela suggère l'utilisation du patron de conception "Itérateur". Ce patron de conception fournit un moyen d'accéder aux éléments d'un objet agrégat de manière séquentielle sans exposer sa représentation sous-jacente.  
Voici comment nous pourrions implémenter la méthode injectAll() : 

```java

public static <T> Iterable<T> injectAll(Class<T> type) {
    return ServiceLoader.load(type);
}

```

Pour utiliser cette méthode dans la simulation de vélo, on peut modifier la classe BikeSimulator pour itérer sur tous les vélos retournés par Context.injectAll(Bike.class).

```java
public class BikeSimulator implements Simulation {
    private final Logger logger = new TimestampedLoggerDecorator(LoggerCreator.getLoggerCreator().create("BikeSimulator"));

    public void run() {
        for (Bike bike : Context.injectAll(Bike.class)) {
            this.logger.log("Bike's speed %.2f Km/h.", bike.getVelocity());
            this.logger.log("Bike's mass %.2f Kg.", bike.getMass());
        }
    }
}
```


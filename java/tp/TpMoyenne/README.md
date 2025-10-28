# TP - Moyenne

Le but du TP est de calculer la moyenne d'une matière et la moyenne générale des matières.

Il s'agit d'une application Java console toute simple avec son point d'entrée `main` classique.

## Classe Matière

Dans le cadre du TP, vous allez créer une classe **Matière** qui va contenir :

- le libellé
- les notes (une liste de `float`)
- une fonction pour calculer la moyenne de la matière

## Exemple de données

Ensuite, dans le `main`, vous allez instancier une fausse liste de matières pour tester le code.  
Par exemple, vous allez instancier les matières suivantes :

- Français avec 4 notes : (12, 8, 13, 6)
- Math avec 4 notes : (4, 9, 16, 18)
- Sport avec 5 notes : (15, 13, 17, 2, 6)

Donc ces 3 matières (je le répète) seront stockées dans une liste.

## Calcul de la moyenne générale

Le but sera de parcourir cette liste de matières (`for`) pour à chaque fois calculer sa moyenne et l'additionner dans une variable qui correspond à la somme des moyennes des matières.

Et c'est grâce à cette somme que vous allez pouvoir finalement calculer la moyenne générale.

## Note

Vous êtes totalement libre en ce qui concerne l'affichage console du résultat.

Par exemple, vous pouvez afficher la moyenne de chaque matière et la moyenne générale avec un `System.out.println` classique.

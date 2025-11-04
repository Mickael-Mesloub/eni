# TP - RPG

Dans une application **Java Console** (avec le `void main`), le but du TP :

---

## 🧩 Classes

### `Battler`
* name: String
* hp: int
* power: int
* attack(opponent: Battler)
* looseHP(amount: int)

### `Team`
* battlers: List<Battler>
* isLooser(): boolean

### `Game`
* playerTeam: Team
* enemyTeam: Team
* play()
* getWinnerTeam(): Team

---

## ⚔️ Fonction `attack`
La fonction `attack` va retirer des HP.

## 💀 Fonction `isLooser`
Elle permet de savoir si une équipe a perdu, donc si tous les combattant(e)s de l'équipe ont les HP à 0.

## 🏆 Fonction `getWinnerTeam`
Elle permet de récupérer l'équipe gagnante.

---

## 🎯 Objectif
Le but est d'instancier deux équipes qui vont se battre.  
Dans ces deux équipes, il n'y aura qu'une seule instance de combattant.

👉 Donc deux équipes composées d’un(e) seul(e) combattant(e).

Dans la fonction `play` de `Game`, vous allez mettre la logique du combat.

En gros, vous allez **boucler tant qu’une équipe n’a pas perdu** pour faire attaquer les combattants entre eux à l’aide de la méthode `attack`.

Lorsqu’on sort de la boucle, on affiche le vainqueur.

---

## 🚀 Évolutions
- ✅ **Système d’initiative** : chaque battler a une statistique d’initiative qui détermine l’ordre d’attaque
- ✅ **Statistiques aléatoires** : HP, puissance et initiative sont attribués aléatoirement à l’instanciation, pour rendre les combats plus variés

## 💡 Idées d'évolutions possibles
- Revoir structure du projet et isolation de responsabilités : ranger classes dans des packages et voir si besoin d'extraire des méthodes dans des fichiers séparés 
- Attaquer automatiquement l'ennemi le plus faible (avec le moins d'HP) ?
- Renommer Battler -> Character
- Ajout de classes de combattants (barbare, mage, archer...)
- Ajout de monstres ? Pour avoir Héros vs Monstres ?
- Ajout de compétences/attaques différentes selon la classe
- Ajout d'autres statistiques avec des valeurs aléatoires, mais adaptées à la classe (attaque physique, attaque magique, défense physique, défense magique...) 
- Ajout d'éléments de hasard (bonus ou malus) :
  - Exemples de malus : échec critique, infliger des dégâts à soi-même ou à un allié...
  - Exemples de bonus : coup critique, esquive, dégâts de zone...
- Ajout d'un compteur de tour + cooldown et effets de compétences selon le tour en cours... 
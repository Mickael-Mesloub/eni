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

# TP Java - Gestion d'un Inventaire (Débutant)

## 🎯 Objectif
Créer une petite application **Java** pour gérer un inventaire de jeu vidéo **sans interface graphique** ni commandes complexes.

**Durée estimée :** 1 heure

---

## ⚙️ Fonctionnalités à implémenter
- **Ajouter un objet** : stocker le nom et la quantité d'un objet
- **Afficher l'inventaire** : lister tous les objets ajoutés
- **Supprimer un objet** : retirer un objet existant

---

## 🧱 Étape 1 : Déclaration de la classe `Item`

### Instructions :
- Déclarez une classe `Item`
- Cette classe doit contenir deux attributs :
    - `name` (nom de l'objet)
    - `quantity` (quantité de l'objet)
- Ajoutez un **constructeur** pour initialiser ces attributs
- Créez des **getters** et **setters** pour ces attributs
- Implémentez une méthode `toString()` pour afficher les détails d’un objet sous forme de texte

---

## 🗃️ Étape 2 : Gestion de l'inventaire

### Instructions :
- Créez une classe `Inventory`
- Déclarez une **liste d’objets** de type `Item`

#### Méthodes à implémenter :
- `addItem(String name, int quantity)`
    - Vérifiez si l’objet existe déjà
    - Si oui, mettez à jour sa quantité
    - Sinon, ajoutez un nouvel objet à la liste

- `displayItems()`
    - Parcourt la liste et affiche chaque objet

- `removeItem(String name)`
    - Supprime l’objet correspondant au nom donné

💡 **Conseil :** utilisez des boucles pour parcourir la liste et chercher un objet par son nom.

---

## 🖥️ Étape 3 : Programme principal

### Instructions :
- Créez une classe `Main` contenant la méthode `main`
- Instanciez un objet `Inventory`
- Ajoutez des objets dans l’inventaire via `addItem()`
- Affichez l’inventaire via `displayItems()`
- Supprimez un objet via `removeItem()`
- Affichez à nouveau l’inventaire pour vérifier la suppression

💡 **Conseil :** commencez par ajouter quelques objets simples, affichez-les, puis supprimez-en un et affichez à nouveau la liste.

---

## ✅ Résultats attendus
- **Ajout d’objets :** l’inventaire stocke et affiche correctement les objets
- **Suppression :** un objet est retiré de la liste avec succès

# Mini Projet

## A réaliser par groupe de 2 ou 3.

On se propose de réaliser une application web J2EE, en respectant au mieux l'architecture MVC (DAO, JSP, Servlet).

On utilise la base de données "sample" fournie avec NetBeans.

Schéma de la base de données :

- Les clients (CUSTOMER) passent des commandes (PURCHASE_ORDER).
- Les commandes référencent des produits (PRODUCT).
- Les produits appartiennent a des catégories (PRODUCT_CODE).
- Les catégories sont associées à un taux de remise (DISCOUNT_CODE).
- Les clients sont localisés dans des zones géographiques (MICRO_MARKET).

On développera les tests unitaires du DAO en utilisant la technique présentée dans la partie JDBC : exemple de test avec HsqlDB.

L'application est destinée à deux catégories d'utilisateurs : Les clients et l'administrateur.

## Pour les clients :

**Le client doit s'authentifier pour accéder à l'édition des bons de commandes.**

La base sample ne contenant pas de mots de passe, on utilisera les champs suivants de la table CUSTOMER pour l'authentification :

- login : EMAIL
- password : CUSTOMER_ID

Une fois connecté, l'application doit permettre au client l'édition complète de ses commandes (ajout, modification, suppression).

## Pour l'administrateur :

**L'administrateur s'authentifie en utilisant un login / password prédéfini dans l'application.**

Une fois authentifié, l'administrateur a accès à une série de tableaux de bord graphiques qui lui permettent de visualiser des statistiques sur les commandes saisies :

- Visualiser les chiffres d'affaire par catégorie d'article, en choisissant la période (date de début / date de fin) sur laquelle doit porter la statistique.
- Visualiser les chiffres d'affaire par zone géographique, en choisissant la période (date de début / date de fin) sur laquelle doit porter la statistique.
- Visualiser les chiffres d'affaire par client, en choisissant la période (date de début / date de fin) sur laquelle doit porter la statistique.

Pour l'affichage des graphiques, on utilisera la bibliothèque javascript [Google Charts](https://developers.google.com/chart/).

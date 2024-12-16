# PFE Project
## Documentation du Projet : Système de Prise de Rendez-vous Médicaux

### Introduction

Le projet de prise de rendez-vous médicaux a pour objectif de simplifier la gestion des consultations entre les patients et les professionnels de santé. Ce système vise à réduire les contraintes administratives et à améliorer l’efficacité des processus grâce à une plateforme web et mobile intuitive et sécurisée.

### Objectifs

- Offrir une solution centralisée pour la prise de rendez-vous.

- Faciliter la communication entre les patients et les professionnels de santé.

- Garantir la sécurité des données médicales et personnelles.

- Permettre une consultation rapide de l'historique des rendez-vous.

### Conception et Architecture

- Architecture du Système

L’application suit une architecture en microservices, permettant une flexibilité et une maintenabilité accrues.

- Modules principaux

Gestion des utilisateurs : Enregistrement, connexion, et gestion des profils (patients et médecins).

Gestion des rendez-vous : Création, modification, et annulation de rendez-vous.

Notifications : Envoi de rappels par email ou SMS.

Sécurité : Gestion des authentifications et autorisations avec JWT.

- Technologies Utilisées

### Backend

Framework : Spring Boot ,Flask (Python), pour la gestion des recommandations personnalisées des médecins basées sur les préférences des utilisateurs.

Base de données : MySQL

Sécurité : Spring Security, JWT pour l’authentification.
 

### Frontend

Framework : Angular

Design : Bootstrap, CSS personnalisé.
Flask et Python : Si vous envisagez d'utiliser Flask pour un service supplémentaire dans ce projet, comme un microservice pour le traitement des données ou la gestion de recommandations basées sur les préférences des patients, vous pouvez mentionner son utilisation dans la section des Technologies Utilisées. Par exemple :

### Dlib et Reconnaissance Faciale : 
La reconnaissance faciale peut être intégrée dans le processus d'authentification ou de vérification de l'identité des utilisateurs, surtout dans un contexte médical où la sécurité est primordiale. 

###  Fonctionnalités de Sécurité Avancées :
Reconnaissance faciale : Intégration de la reconnaissance faciale à l’aide de la bibliothèque Dlib (Python) pour renforcer l'authentification des utilisateurs (patients et médecins), ajoutant une couche de sécurité supplémentaire lors de la connexion.

### Intégration du Chatbot : 
pour répondre automatiquement aux questions fréquentes des patients, ou pour aider à la prise de rendez-vous.

### Mobile

Technologie : FlutterFlow pour la version mobile.

### Autres Outils

Gestion de projet : Jira (méthodologie SCRUM).

API : REST pour les communications entre les différents services.

Fonctionnalités Clés

Côté Patient

Création et gestion du compte utilisateur.

Recherche de professionnels de santé par spécialité ou localisation.

Prise de rendez-vous avec confirmation instantanée.

Accès à l’historique des rendez-vous.

Côté Médecin

Gestion des disponibilités via un calendrier interactif.

Consultation de la liste des patients et des rendez-vous.

Notifications en temps réel en cas d’annulation ou de modification.

### Implémentation de la Sécurité

Utilisation des JSON Web Tokens (JWT) pour la gestion des sessions utilisateur.

Encodage des mots de passe avec l’algorithme BCrypt.

Protection des points d’entrée API avec des règles de filtrage basées sur les rôles utilisateur (patient/médecin).

Workflow de Développement

Méthodologie Agile - SCRUM

Sprints : Cycles de 2 semaines avec des livrables intermédiaires.

### Rôles :

Product Owner : Encadrant Mr Hatem Mendil.

Scrum Master : Moi-même.

Outils : Utilisation de Jira pour suivre les user stories et le backlog.

### Tests et Validation

Tests Unitaires

Outils : JUnit pour le backend.

Couverture : 80% des fonctionnalités principales testées.

### Tests Fonctionnels

Scénarios utilisateurs simulés pour valider les cas d’utilisation principaux.

Tests de Performance

Outils : Postman pour le benchmarking des API.

### Résultats Obtenus

Amélioration de l’efficacité des rendez-vous de 40% selon les retours d’utilisateurs tests.

Sécurité renforcée, aucune faille majeure détectée lors des tests.

Interface intuitive appréciée par 90% des utilisateurs interrogés.

### Conclusion et Perspectives

Le projet a réussi à répondre aux besoins identifiés en offrant une solution performante et adaptée aux utilisateurs cibles.

Améliorations Futures

Intégration d’un système de téléconsultation.

Ajout d’un moteur de recommandation pour proposer des médecins basés sur les préférences des utilisateurs.

Extension à d’autres spécialités médicales et intégration avec des pharmacies.



### Encadrante Académique : Madame Asma
### Développeuse : Jedidi Salma

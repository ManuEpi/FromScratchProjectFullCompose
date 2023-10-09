Création d'un projet from scratch qui a pour but de récupérer une liste de news via une API (https://newsapi.org/), l'afficher, et permettre de cliquer sur un élément afin d'y voir son détail. La liste s'affiche dans la langue définie sur le téléphone de l'utilisateur

Full compose (Android compose Navigation, pas de fragment, qu'une seule activité et des composants)

Clean Architecture modulaire, en MVVM 

Utilisation des librairies :

Dagger Hilt -> Injection de dépendance -> Choix par habitude

Retrofit -> Networking

Timber -> Pour la partie logs

Glide -> Afin de pouvoir charger une image à partir d'une URL dans un Composable Image

Architecture découpée comme ceci :

DATA -> Récupération de la data reçu par l'appel de service à l'API et gestion du cache

DOMAIN -> Traitement de la donnée

UI -> ViewModel et Composants -> Récupération de la data depuis la partie Domain, et affichage de celle-ci avec gestion des states


La partie UI demande à récuperer de manière asynchrone la liste de news, elle va contacter la partie USECASE qui elle va contacter la partie REPOSITORY

La partie REPOSITORY recevoir la réponse de l'API, mettre à jour le cache, et le Usecase sera prévenue (car il écoute le cache) et va envoyer l'info à la partie UI, application donc de la "Separation of Concerns"


TODO : Ajout des tests unitaires

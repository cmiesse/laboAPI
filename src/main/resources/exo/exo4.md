# Exo 4

- mettre en place un petite securité
    - infos sur utilisateurs en memoire (un avec role USER et un ADMIN)
    - Les get et getAll de les crud controller demande un authentification
    - les insert, update et delete demande le role ADMIN
    
- Mettez en place une gestion d'erreurs pour les exceptions suivantes:
    - ElementAlreadyExistException
    - ElementNotFoundException
    - erreur de validation
    
    Dans la réponse de la gestion d'erreur, renvoyez un objet Rapport contenant
    le message d'erreur, le code http de resultat et le chemin emprunté.
    
    Pour la gestion des erreurs de validations, l'objet renvoyé devra contenir
    2 listes. Une contenant les messages d'erreurs de validations globales, une autre
    contenant les messages validation de champ. 
    
    Faites en sorte ne de plus gérer les exceptions dans les ressources du controller.
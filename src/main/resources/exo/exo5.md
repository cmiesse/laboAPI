
# Exo 5 - La pagination

Mettez en place une ressource permettant
de récupérer des professeurs de manière paginé soit
avec 2 éléments par pages(par défaut), soit avec 3 éléments par pages.

Le nombre d'éléments récupérer peut être fourni en paramètre de requète.

Si le client fourni un paramètre invalide -> exception custom -> gestion

Dans la page renvoyée, il devra être de récupérer les infos suivantes:
1) les éléments de la page sous forme de liste
2) le nbr d'éléments de la page
3) le nbr d'éléments total
4) le nbr de pages
5) le numéro de la page
6) si il existe une page suivante, l'URI menant à la page suivante
7) si il existe un page précédente, l'URI menant à page précédente

Dans le cas où le client demande un page inexistante -> exception custom 2 -> gestion

<h1>
Application web de gestion et de description de monuments
<br /><br />
Adrien MEYRAND
<br />
M2 ICO
</h1>


--------------------------------------------------------------------------------------------- 	
--------------------------------------------------------------------------------------------- 	


## Présentation du projet

Ce projet est une application web de consultation et de gestion de monuments. 
Ces monuments sont localisés dans des communes notées lieux.
Ces lieux sont situés dans des départements français. Des personnages célèbres
sont rattachés à des monuments. Par exemple, Jean-Baptiste Poquelin dit Molière est rattaché au
square Molière ou à l’hôtel Lacoste, à Pézenas, commune localisée géographiquement dans l’Hérault.
De même, la cathédrale Saint Pierre est un monument localisé à Montpellier. Pour la simplicité du
schéma, nous avons choisi de considérer qu’un monument n’était situé que dans un et un seul lieu, ce
qui dans la réalité, n’est pas toujours le cas (la base de monuments historiques français Mérimée laisse
apparaître des monuments qui sont situés sur plusieurs communes à l’exemple d’un aqueduc qui peut
traverser plusieurs communes).

Voici comment j'ai modélisé le projet :

* [Architecture globale](./architecture_globale.png)

* [Diagramme UML](./diagramme_de_classes_UML.drawio.png)
 	
* [Diagramme cas d'utilisation](./diagramme_use_case_UML.drawio.png)

Pour réaliser cette application, j'ai utilisé le logiciel Eclipse IDE For Java Developers
fournissant un environnement de développement intégré pour le langage Java. En particulier,
l'extension Jakarta EE (anciennement Java EE) est utilisée, notamment en fournissant une API de mapping objet-relationnel.
Dans ce cadre, j'ai créé un projet Maven afin de faciliter la gestion des dépendances utilisées
dans mon code : ces dernières sont définies dans le fichier [pom.xml](./pom.xml). 
J'ai utilisé le framework Spring pour créer mon application web et la connecter à une base de données (BD).
Pour faciliter le développement, je me suis servi de l'outil Spring boot permettant notamment une configuration automatique
et rendant l'app autonome. Les différentes propriétés de Spring sont définies dans le fichier [application.properties](./src/main/resources/application.properties).

Dans "./src/main/java", six packages sont définis :

* <em>com.hai925iprojetwithspring.com.hai925iprojetwithspring</em> avec [Application.java](./src/main/java/com/hai925iprojetwithspring/com/hai925iprojetwithspring/Application.java) comme point d'entrée pour exécuter l'app Spring,

* <em>com.hai925iprojetwithspring.com.hai925iprojetwithspring.controller</em> où sont définis les classes définissant 
la logique liée aux actions d'un utilisateur,

* <em>com.hai925iprojetwithspring.com.hai925iprojetwithspring.model</em> où sont définis les classes contenant les données à afficher,

* <em>com.hai925iprojetwithspring.com.hai925iprojetwithspring.repository</em> avec des interfaces fournissant des méthodes permettant
de requêter sur la BD,

* <em>com.hai925iprojetwithspring.com.hai925iprojetwithspring.service</em> avec des classes où sont définies la logique métier de l'API,

* <em>com.hai925iprojetwithspring.com.hai925iprojetwithspring.security</em> pour pouvoir faire authentifier des utilisateurs avec des rôles donnés
et restreindre l'accès à certaines parties de l'application en fonction de ces rôles.

Une fois mon application lancée dans le main de [Application.java](./src/main/java/com/hai925iprojetwithspring/com/hai925iprojetwithspring/Application.java), 
on peut y accéder par le navigateur avec l'URL suivante : 
[http://localhost:8889/login](http://localhost:8889/login)

Le numéro du port rattaché à l'hôte local est défini dans la section "Server" du fichier [application.properties](./src/main/resources/application.properties).
J'ajoute également que j'ai rendu l'utilisation obligatoire
d'une authentification pour simplifier mon programme.


--------------------------------------------------------------------------------------------- 	


## Persistance des données

Initialement, toutes les informations sur les monuments, célébrités, lieux et départements
sont organisées et stockées dans une BD relationnelle (SGBD MariaDB) grâce au fichier [monumentsCelebrites.sql](./src/main/resources/monumentsCelebrites.sql). 

Afin d'avoir la connectivité entre Java et la BD relationnelle, j'ai utilisé l'interface de programmation JDBC (Java Database Connectivity).
Les modalités de cette connectivité sont décrites dans la section "BD" de [application.properties](./src/main/resources/application.properties)

Vu que nous manipulons des types de données complexes, j'ai mis en oeuvre le modèle ORM (Object Relational Mapping) de
l'API JPA (Java Persistance API) afin de faire persister les objets créés en Java dans ma BD relationnelle.
Pour gérer la persistance de ces objets (entités), j'ai utilisé le framework Hibernate. 


--------------------------------------------------------------------------------------------- 	


## Mapping relationnel-objet

Afin de lier le monde objet au monde relationnel, j'ai créé dans mon package model 4 classes Java Beans correspondant
aux tables définies dans ma modélisation UML. 
Par exemple, quand on crée une instance de Monument, on crée une entité qui va être persistée dans la table Monument de la BD relationnelle.
Il faut aussi prendre en compte les associations entre classes pour que l'une récupère les informations de l'autre.

Voici en détail le mapping : 

* Annotation JPA @Entity pour indiquer que la classe correspond à une table
de la base de données.
* Annotation @Column si l'attribut de la classe java a un nom différent de l'attribut de la table de la BD
* Annotation @Table pour indiquer le nom de la table associée et le schéma où est défini la
table dans la BD.
* Mapping des clés primaires : un attribut java avec @Id correspond à la clé primaire de la table
* Mapping des associations :
    * "localisedans" : plusieurs monuments pouvant être associés à un lieu, on met le décorateur
	@ManyToOne sur un attribut java <code>lieu</code> de type Lieu qui détient donc l'entité association. La récupération
	des données du lieu se fait grâce à la jointure sur ``code_lieu`` de la table relationnelle (@JoinColumn).
	L'association étant bidirectionnelle, on fait un @OneToMany sur l'attribut java <code>monuments</code> afin d'avoir la liste des monuments
	associés à un lieu.
    * "cheflieude" : relation unidirectionnelle @OneToOne possédée par l'attribut java de la classe Departement <code>chefLieu</code> de type Lieu avec jointure sur ``chef_lieu``
	pour avoir le nom du chef lieu associé au département.
    * relation d'agrégation-composition entre Lieu et Departement : association bidirectionnelle avec @ManyToOne possédée par l'attribut java de la classe Lieu <code>dept</code> de type Departement avec jointure sur ``dep``
	pour avoir le nom du département associé au lieu. @OneToMany sur l'attribut java de la classe Departement <code>lieux</code> afin d'avoir la liste des lieux 
	associés à un département.
    * "associea" : Ici, un monument peut être lié à plusieurs célébrités et réciproquement.
      Pour récupérer la liste des monuments associés à une célébrité, on réalise une jointure sur une table d'association (ici associea).
      La relation @ManyToMany est possédée par l'attribut java <code>listeMonuments</code> de la classe Celebrite et on lui ajoute le décorateur @JoinTable pour
      réaliser la jointure et donc la connexion de Celebrite vers Monument. Dans Monument, on fait la connexion vers Celebrite avec @ManyToMany sur l'attribut java <code>listeCelebrites</code> de la classe Monument.


--------------------------------------------------------------------------------------------- 	


## Couche présentation

Pour le front-end, j'ai utilisé [l'architecture MVC](./architecture_du_mvc.png) (Modèle-Vue-Contrôleur).
Les différents vues sont rendues grâce au moteur de template HTML Thymeleaf, notamment pour créer les formulaires
utiles aux interactions de l'opérateur : opérations de recherche, d'ajout/modification et de suppression sur la BD.
L'ensemble des fichiers HTML utilisés avec attributs Thymeleaf sont dans stockés dans le répertoire "./src/main/resources/templates".

J'ai réalisé une page d'accueil qui s'affiche après connexion avec une barre de navigation pour pouvoir consulter (ou gérer en administrateur)
les listes des entités d'intérêt. Pour chacune de ces dernières, on a une page dédiée avec la liste de consultation sous forme de tableau et les différents formulaires.

A noter que le formulaire d'ajout fait aussi office de formulaire de modification. 
De plus, pour les formulaires de recherche, j'ai fait en sorte de trouver un monument, lieu, célébrité ou département par identifiant 
(respectivement geohash, code insee, numéro de célébrité et numéro de département). Et j'ai rajouté pour les monuments les recherches par
propriétaire, par type de monument et par code insee.

Voici donc la liste des captures d'écran des interfaces résumant l'essentiel du fonctionnement du système :
* [Page de connexion](./page_login.png)
* [Page d'accueil pour le rôle USER](./page_accueil_user.png)
* [Page des monuments pour le rôle USER](./page_monuments_user.png)
* [Page donnant le monument recherché par identifiant](./page_recherche_monument_par_identifiant_user.png)
* [Page d'accueil pour le rôle ADMIN](./page_accueil_admin.png)
* [Formulaires supplémentaires des monuments pour le rôle ADMIN par rapport au USER](./formulaires_modification_BD_monuments_admin.png)
* [Page confirmant la suppression d'un monument par identifiant](./page_suppression_monument_admin.png)
* [Page confirmant l'ajout (ou modification) d'un monument ](./page_ajout-modification_monument_admin.png)
* [Page de déconnexion](./page_logout.png)

Les interfaces étant assez similaires pour les célébrités, les lieux et les départements, je ne les inclus pas.


--------------------------------------------------------------------------------------------- 	


## Couche contrôle et métier
la première permettant notamment la validation de données utilisateurs et la seconde opérant avec la conjonction de données utilisateurs et de données pérennes pour réaliser les tâches du scenario.

Dans la couche contrôle, j'ai créé un contrôleur par entité, par exemple : 
* [MonumentController.java](./src/main/java/com/hai925iprojetwithspring/com/hai925iprojetwithspring/controller/MonumentController.java) où chaque méthode de contrôleur amène à une ressource localisée par une route. 
    * La route <em>/monuments</em> amène au template [homeMonuments.html](./src/main/resources/templates/homeMonuments.html).
    * Les routes <em>monumentById</em> <em>monumentsByProprietaire</em>, <em>/monumentsByTypeM</em>, <em>monumentsByCodeInsee</em> amènent
    respectivement aux templates [monumentById.html](./src/main/resources/templates/monumentById.html), 
    [monumentByProprietaire.html](./src/main/resources/templates/monumentByProprietaire.html),
    [monumentByTypeM.html](./src/main/resources/templates/monumentByTypeM.html) et
    [monumentByCodeInsee.html](./src/main/resources/templates/monumentByCodeInsee.html)
    * La route <em>/suppressionMonument</em> amène au template [suppressionMonument.html](./src/main/resources/templates/suppressionMonument.html)
    * La route <em>/ajoutMonument</em> amène au template [ajoutMonument.html](./src/main/resources/templates/ajoutMonument.html)
* Pour ma [page d'accueil](./src/main/resources/templates/home.html), j'ai créé une route <em>/</em> gérée par 
[HomeController.java](./src/main/java/com/hai925iprojetwithspring/com/hai925iprojetwithspring/controller/HomeController.java)

Dans la couche service j'ai créé une classe par entité, par exemple :
* [MonumentService.java](./src/main/java/com/hai925iprojetwithspring/com/hai925iprojetwithspring/service/MonumentService.java).
Les méthodes définies dans cette classe sont appelées par le contrôleur pour que ce dernier récupère les données du modèle.
Par exemple, la méthode <code>getMonuments()</code> va faire appel à la méthode <code>findAll()</code>. Cette dernière est fournie par l'interface IMonumentRepository qui elle-même
étend CrudRepository<Monument, String>. Dans ce cas, une méthode de contrôleur peut récupérer la liste de tous les monuments via le service de Monument. 
afin d'avoir les informations de la base de données via le repository.

Dans la couche repository, on a 4 repositories pour chaque entité, par exemple :
* [IMonumentRepository.java](./src/main/java/com/hai925iprojetwithspring/com/hai925iprojetwithspring/service/MonumentService.java).
Son rôle est de communiquer avec la BD afin d'avoir différentes informations sur les monuments.
J'ai défini des requêtes personnalisées qui peuvent ne pas être possible par les méthodes fournies par l'interface CrudRepository<Monument, String> :


<code> 


// sélection des monuments par propriétaire<br />
@Query("SELECT m FROM Monument m WHERE proprietaire=:proprietaire")<br />
public Iterable<Monument> findByProprietaire(@Param("proprietaire") String proprietaire);


// sélection des monuments par type de monument<br />
@Query("SELECT m FROM Monument m WHERE typeM=:typeM")<br />
public Iterable<Monument> findByTypeM(@Param("typeM") String typeM);


// sélection des monuments par code insee<br />
@Query("SELECT m FROM Monument m WHERE m.lieu.codeInsee=:codeInsee")<br />
public Iterable<Monument> findByCodeInsee(@Param("codeInsee") String codeInsee);


</code>


--------------------------------------------------------------------------------------------- 	


## Couche sécurité

J'ai utilisé le framework Spring Security.
La configuration s'effectue dans le fichier [SecurityConfig.java](./src/main/java/com/hai925iprojetwithspring/com/hai925iprojetwithspring/security/SecurityConfig.java) du package security.
Notamment, il y a une page de login par défaut que j'ai mise en place avec la méthode <code>formLogin(Customizer.withDefaults())</code>.
Deux rôles ont été définis pour pouvoir se connecter au système :
* ADMIN qui peut avoir accès à toutes les routes.
* USER n'a pas le droit d'ajout/modification et de suppression dans la base de données, il ne peut faire que de la consultation.
Par ailleurs, j'ai fait en sorte de ne pas afficher les formulaires d'ajout/modification et de suppression.
Cela grâce à des attributs thymeleaf adaptés à Spring Security. Par exemple, sur ma balise de formulaire
de suppression, je définis l'attribut <em>sec:authorize="hasRole('ADMIN')"</em>.
A noter qu'une protection csrf a dû être désactivée pour que l'admin puisse modifier la BD.

--------------------------------------------------------------------------------------------- 	


## Conclusion et perspective sur le travail

Pour conclure, je dirais que ce projet m'a permis de mieux comprendre le fonctionnement global
d'une application web. Cela me servira pour plus tard si jamais j'entame une carrière de développeur web.
J'ai eu quelques difficultés dans le mapping, mais cela a été résolu.
Faire ce projet m'a pris beaucoup de temps et m'a parfois paru comme une montagne insurmontable.
Mais j'ai réussi à faire l'essentiel : quelque chose qui fonctionne. J'ai compris que l'organisation et 
la planification des tâches à effectuer étaient importantes, et que faire une modélisation avant l'implémentation de code
aidait beaucoup.

## Ouverture : Problèmes à résoudre
* Lorsque je crée mon monument, je ne peux ajouter qu'une seule célébrité dans ma liste.
* Si je veux modifier un monument, je ne peux pas enlever une célébrité associée, je ne peux
qu'ajouter. Penser à mettre la possibilité d'enlever au moins une célébrité associée.
* A implémenter : par exemple pouvoir connaître la distance entre
        deux monuments cibles dans la même commune ou dans deux communes différentes
        

--------------------------------------------------------------------------------------------- 	
--------------------------------------------------------------------------------------------- 	


<p>Fait à Montpellier, le 04/12/2023</p>





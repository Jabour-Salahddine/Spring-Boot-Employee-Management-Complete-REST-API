# --- Stage 1: Build Stage ---
# Utilise une image Maven avec JDK 21 pour construire le projet.
# Cela nous donne tous les outils nécessaires (Java, Maven) pour la construction.
FROM maven:3.9.8-eclipse-temurin-21 AS build

# Définit le répertoire de travail à l'intérieur de l'image.
WORKDIR /app

ENV LANG C.UTF-8

# Copie d'abord le pom.xml pour tirer parti du cache de couches de Docker.
# Si le pom.xml ne change pas, Docker n'aura pas à re-télécharger les dépendances.
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copie le reste du code source de l'application.
COPY src ./src

# Construit l'application et exécute les tests.
# Le résultat sera un fichier .jar dans le dossier /app/target/
RUN mvn package -DskipTests


# --- Stage 2: Runtime Stage ---
# Utilise une image JRE (Java Runtime Environment) beaucoup plus légère pour l'exécution.
# C'est une bonne pratique pour réduire la taille de l'image finale et sa surface d'attaque.
FROM eclipse-temurin:21-jre-jammy

# Définit le répertoire de travail.
WORKDIR /app

# Copie SEULEMENT le fichier .jar généré depuis le stage "build".
# C'est la magie du multi-stage build !
COPY --from=build /app/target/*.jar app.jar

# Expose le port 8080 pour que l'application soit accessible de l'extérieur du conteneur.
EXPOSE 8080

# La commande qui sera exécutée au démarrage du conteneur.
ENTRYPOINT ["java", "-jar", "app.jar"]
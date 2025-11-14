# Benchmark de Performances des Web Services REST

Ce projet évalue les performances de différentes stacks REST sur un même domaine métier et base de données PostgreSQL.

| Catégorie              | Détails |
|------------------------|---------|
| **Variantes**          | A: JAX-RS (Jersey) + JPA/Hibernate <br> C: Spring Boot + @RestController + JPA/Hibernate <br> D: Spring Boot + Spring Data REST |
| **Modèle de Données**  | Category: id, code, name, updated_at <br> Item: id, sku, name, price, stock, category_id, updated_at <br> Relation: Category (1) — Item (N) |
| **Endpoints Category** | GET /categories?page=&size= <br> GET /categories/{id} <br> POST /categories <br> PUT /categories/{id} <br> DELETE /categories/{id} |
| **Endpoints Item**     | GET /items?page=&size= <br> GET /items/{id} <br> GET /items?categoryId=&page=&size= <br> POST /items <br> PUT /items/{id} <br> DELETE /items/{id} |
| **Endpoints Relations**| GET /categories/{id}/items?page=&size= |
| **Jeu de Données**     | Categories: 2 000 <br> Items: 100 000 (~50 par catégorie) |
| **Environnement**      | Java 17 <br> PostgreSQL 14+ <br> HikariCP (maxPoolSize=20, minIdle=10) <br> Monitoring: Prometheus + JMX Exporter, Grafana <br> Test: JMeter + InfluxDB v2 |
| **Scénarios JMeter**   | READ-heavy: lectures intenses <br> JOIN-filter: jointures + filtres <br> MIXED: lectures + écritures <br> HEAVY-body: POST/PUT gros payload |
| **Structure Projet**   | db/: scripts SQL <br> jmeter/: plans + CSV <br> variants/: code A/C/D <br> monitoring/: configs Prometheus/Grafana <br> docker-compose.yml: services test |
| **Installation**       | 1. `docker-compose up -d` <br> 2. Initialisation DB via scripts db/ <br> 3. Lancer variante: `cd variants/A-jaxrs-jersey && mvn spring-boot:run` <br> 4. Tests JMeter: `jmeter -n -t jmeter/read-heavy.jmx` |
| **Livrables**          | Code des variantes <br> Fichiers JMeter <br> Dashboards Grafana <br> Tableaux résultats T0-T7 <br> Analyse et recommandations |

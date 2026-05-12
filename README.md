# 🚀 Projeto Java + Neo4j — Introdução a Grafos

## 📚 Descrição

Este projeto tem como objetivo demonstrar, na prática, o uso de grafos utilizando o banco de dados Neo4j integrado com Java.

---

## 🧠 Conceitos Trabalhados

- Grafos
- Vértices (Nodes)
- Arestas (Relationships)
- Banco de dados orientado a grafos
- Linguagem Cypher
- Integração Java + Neo4j

---

## 🏗️ Estrutura do Projeto

📁 projeto
 ┣ 📄 Neo4jExample.java
 ┗ 📄 pom.xml

---

## ⚙️ Pré-requisitos

- Java JDK 11+
- Maven
- Docker

---

## 🐳 Subindo o Neo4j com Docker

```bash
docker run \
  --name neo4j \
  -p 7474:7474 \
  -p 7687:7687 \
  -d \
  -e NEO4J_AUTH=neo4j/senha123 \
  neo4j
```

---

## 🌐 Acessando a Interface

http://localhost:7474

Usuário: neo4j  
Senha: senha123  

---

## 💻 Dependência Maven

```xml
<dependency>
    <groupId>org.neo4j.driver</groupId>
    <artifactId>neo4j-java-driver</artifactId>
    <version>5.20.0</version>
</dependency>
```

---

## ▶️ Executando

```bash
mvn compile
mvn exec:java
```

---

## 🧪 Teste no Neo4j

```cypher
MATCH (n) RETURN n
```

---

## 👨‍🏫 Autor

Prof. Dr. André Silva


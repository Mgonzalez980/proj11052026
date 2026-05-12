package org.example;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;

public class SistemaJogos implements AutoCloseable {

    private final Driver driver;

    public SistemaJogos(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    @Override
    public void close() {
        driver.close();
    }

    public void adicionarJogador(String nomeJogador) {
        try (Session session = driver.session()) {
            session.executeWrite(tx -> {
                tx.run("MERGE (j:Jogador {nome: $nome})",
                        Values.parameters("nome", nomeJogador));
                return null;
            });
            System.out.println("Vértice (Jogador) criado: " + nomeJogador);
        }
    }

    public void adicionarJogo(String tituloJogo) {
        try (Session session = driver.session()) {
            session.executeWrite(tx -> {
                tx.run("MERGE (g:Jogo {titulo: $titulo})",
                        Values.parameters("titulo", tituloJogo));
                return null;
            });
            System.out.println("Vértice (Jogo) criado: " + tituloJogo);
        }
    }

    public void adicionarRelacionamentoJoga(String nomeJogador, String tituloJogo) {
        try (Session session = driver.session()) {
            session.executeWrite(tx -> {
                tx.run("MATCH (j:Jogador {nome: $nomeJogador}) " +
                                "MATCH (g:Jogo {titulo: $tituloJogo}) " +
                                "MERGE (j)-[:JOGA]->(g)",
                        Values.parameters("nomeJogador", nomeJogador, "tituloJogo", tituloJogo));
                return null;
            });
            System.out.println("Aresta criada: " + nomeJogador + " -> JOGA -> " + tituloJogo);
        }
    }



    public static void main(String[] args) {
        String uri = "bolt://localhost:7687";
        String user = "neo4j";
        String password = "senha123";

        try (SistemaJogos app = new SistemaJogos(uri, user, password)) {

            System.out.println("--- Cadastrando Vértices ---");
            app.adicionarJogador("Tiago");
            app.adicionarJogador("Maria");
            app.adicionarJogador("Gonza");
            app.adicionarJogador("Luccas");
            app.adicionarJogador("João");


            app.adicionarJogo("Elden Ring");
            app.adicionarJogo("Minecraft");
            app.adicionarJogo("The Sims 4");
            app.adicionarJogo("Rainbow Six Siege");
            app.adicionarJogo("CS");
            app.adicionarJogo("Stardew Valley");



            app.adicionarRelacionamentoJoga("Tiago", "Elden Ring");
            app.adicionarRelacionamentoJoga("Tiago", "Minecraft");
            app.adicionarRelacionamentoJoga("Maria", "The Sims 4");
            app.adicionarRelacionamentoJoga("Maria", "Minecraft");
            app.adicionarRelacionamentoJoga("Luccas", "Minecraft");
            app.adicionarRelacionamentoJoga("Luccas", "Rainbow Six Siege");
            app.adicionarRelacionamentoJoga("Luccas", "CS");
            app.adicionarRelacionamentoJoga("Gonza", "CS");
            app.adicionarRelacionamentoJoga("Gonza", "Rainbow Six Siege");
            app.adicionarRelacionamentoJoga("João", "Minecraft");
            app.adicionarRelacionamentoJoga("João", "Stardew Valley");




        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
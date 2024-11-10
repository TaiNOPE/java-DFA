package com.mycompany.lfa_afd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class LFA_AFD {
    public static void main(String[] args) {
        automataExample("abba");
        externalFileExample("abba");
    }


    /*
    Carrega um automato salvo em um aqruivo externo.
    Retorna um objeto DFA configurado com os dados lidos.
    */
    public static DFA loadFromFile(String fileName){
        try{
            // Leitor do arquivo com nome passado por fileName
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            
            // pega os dados do automato
            String[] alphabet = reader.readLine().split(";");
            String[] states = reader.readLine().split(";");
            String initialState = reader.readLine();
            String[] finalStates = reader.readLine().split(";");
            String[][] moveset = new String[alphabet.length][states.length];
            for(int letterId = 0; letterId < alphabet.length; letterId++){
                moveset[letterId] = reader.readLine().split(";");
            }
            
            for(String s: states){
                System.out.println(s);;
            }
            // Fecha o arquivo
            fr.close();
            reader.close();
            
            // Constroi o objeto com os dados lidos e retorna
            DFA result = new DFA(alphabet, states, moveset);
            result.setInitialState(initialState);
            result.setFinalStates(finalStates);
            return result;
        }catch(Exception e){
            System.out.println("\n\n" + e.toString() + "\n\n");
        }
        
        // Se ser algum erro, retorna null
        return null;
    }
    
    
    /*
    Exemplo usando um automato salvo em um arquivo externo
    */
    public static void externalFileExample(String word){
        DFA automata = loadFromFile("automata1.txt");
        
        if(automata.testInput(word)){
            System.out.println("A palavra " + word + " foi ACEITA");
        }else{
            System.out.println("A palavra " + word + " foi RECUSADA");
        }
    }
    
    
    /*
    Exemplo construindo um automato usando codigo
    */
    public static void automataExample(String word){
        // Alfabeto aceito pelo automato
        String[] alphabet   = {"a", "b"};
        // Estados do automato
        String[] states     = {"q0", "q1", "q2", "qf"};
        /*
        Matriz de transição do automato.
        E convertido em uma matrix bidimensional na classe DFA.
        */
        String[][] moveset    = {{"q1", "qf", "q1", "qf"}, { "q2", "q2", "qf", "qf"}};

        // Construtor do automato
        DFA automata = new DFA(alphabet, states, moveset);
        automata.setInitialState("q0");
        automata.setFinalState("qf");

        // Testa uma palavra no automato
        if(automata.testInput(word)){
            System.out.println("A palavra " + word + " foi ACEITA");
        }else{
            System.out.println("A palavra " + word + " foi RECUSADA");
        }
    }
}

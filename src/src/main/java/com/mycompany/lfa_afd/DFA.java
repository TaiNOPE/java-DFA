package com.mycompany.lfa_afd;

import java.util.HashMap;

public class DFA {
    private HashMap<String, Integer> alphabet = new HashMap<>();
    private HashMap<String, Integer> states = new HashMap<>();
    private String initialState;
    private String[] finalStates;
    private String[][] moveset;

    
    public DFA(String[] alphabet, String[] statesArgs, String[] movesetArgs){       
        int counter;

        // Atribui número de índice no HashMap para cada letra do alfabeto.
        counter = 0;
        for(String alph: alphabet){
            this.alphabet.put(alph, counter++);
        }
        
        // Atribui número de índice no HashMap para cada estado.
        counter = 0;
        for(String st: statesArgs){
            this.states.put(st, counter++);
        }
        
        /*
        constrói o array[][] do moveset. Pega os vetores alphabet e
        states transformando em uma "tabela", onde cada linha representa
        uma letra do alfabeto, e cada coluna representa um estado.
        */
        this.moveset = new String[this.alphabet.size()][this.states.size()];
        counter = 0;
        for(int alph = 0; alph < alphabet.length; alph++){            
            for(int state = 0; state < statesArgs.length; state++){
                this.moveset[alph][state] = movesetArgs[counter];
                counter++;
            }
        }
    }

    
    // Estado inicial do DFA
    public void setInitialState(String stateName){
        this.initialState = stateName;
    }
    
    
    // Define múltiplos estados finalis do DFA
    public void setFinalStates(String[] finalStates){
        this.finalStates = finalStates;
    }
    
    
    // Define um único estado final para o DFA
    public void setFinalState(String state){
        this.finalStates = new String[1];
        this.finalStates[0] = state;
    }

    
    /*
    Recebe uma palara, se a palavra for aceita retorna true, retorna falso caso
    contrário.
    Nesse caso só funciona se as palavras do alfabero forem de um
    único caractere.
    */
    public boolean testInput(String input){
        String currentState = initialState;
        System.out.println("Statring in: " + initialState);
        for(int i = 0; i < input.length(); i++){
            char word = input.charAt(i);
            int alph = alphabet.get(String.valueOf(word));
            int stat = states.get(currentState);
            currentState = moveset[alph][stat];
            System.out.println("Input: " + word + ", going to " + currentState);
        }
        
        for(String state: finalStates){
            if(currentState.equals(state)){
                return true;
            }
        }
        
        return false;
    }
}

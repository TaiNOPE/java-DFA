package com.mycompany.lfa_afd;


public class LFA_AFD {
    public static void main(String[] args) {
        // Alfabeto aceito pelo automato
        String[] alphabet   = {"a", "b"};
        // Estados do automato
        String[] states     = {"q0", "q1", "q2", "qf"};
        /*
        Matriz de transição do automato.
        E convertido em uma matrix bidimensional na classe DFA.
        */
        String[] moveset    = {"q1", "qf", "q1", "qf", "q2", "q2", "qf", "qf"};

        // Construtor do automato
        DFA automata = new DFA(alphabet, states, moveset);
        automata.setInitialState("q0");
        automata.setFinalState("qf");

        // Testa uma palavra no automato
        String word = "bababababababababa";
        if(automata.testInput(word)){
            System.out.println("A palavra \"" + word + "\" foi aceita.");
        }else{
            System.out.println("A palavra \"" + word + "\" foi recusada aceito");
        }
    }

}

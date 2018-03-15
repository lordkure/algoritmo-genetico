/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cleber.machinelearn;

import org.junit.Test;

/**
 *
 * @author sou_c
 */
public class Testes {

//    @Test
//    public void testeGerarPopulacao() {
//        Populacao pop = new Populacao();
//        System.out.println(pop.printPopulacao());
//    }
    
//    @Test
//    public void testeCrossOver(){
//        AlgoritmoGenetico ag = new AlgoritmoGenetico(0.8f, 0);
//        
//        Individuo pai = new Individuo();
//        Individuo mae = new Individuo();
//        
//        System.err.println("PAI: " + pai.printIndividuo());
//        System.out.println("MÃE: " + mae.printIndividuo());
//        
//        Individuo[] filhos = ag.crossover(pai, mae);
//        
//        System.out.println("FILHO 1: " + filhos[0].printIndividuo());
//        System.out.println("FILHO 2: " + filhos[1].printIndividuo());
//    }

    @Test
    public void testeAG(){
        Populacao pop = new Populacao();
        AlgoritmoGenetico ag = new AlgoritmoGenetico(0.8f, 0.01f);
        System.out.println(ag.executaAG(pop).printPopulacao());
    }
    
}

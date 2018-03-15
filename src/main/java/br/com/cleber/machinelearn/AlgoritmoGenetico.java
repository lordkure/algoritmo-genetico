/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cleber.machinelearn;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sou_c
 */
public class AlgoritmoGenetico {
    
    private double taxaCorssover;
    private double taxaMutacao;
    private static final int PAI = 0;
    private static final int MAE = 1;

    public AlgoritmoGenetico(double taxaCorssover, double taxaMutacao) {
        this.taxaCorssover = taxaCorssover;
        this.taxaMutacao = taxaMutacao;
    }
    
    public Populacao executaAG(Populacao pop){
        // inicio do AG
        // avaliar pop
        Populacao novaPop = new Populacao();
        List<Individuo> popBuffer = new ArrayList<>();
        
        for (int i = 0; i < Constantes.sizePopulacao/2; i++) {
            // selecionar os pares para cruzar
            Individuo pai = roleta(pop);
            Individuo mae = roleta(pop);
            // realizar cruzamento
            Individuo[] filhos = crossover(pai, mae);
            // aplicar mutacao
            Individuo filho1 = mutacao(filhos[0]);
            Individuo filho2 = mutacao(filhos[1]);
            popBuffer.add(filho1);
            popBuffer.add(filho2);
        }       
        // matar os velhos
        // criar os novos
        for (int i = 0; i < Constantes.sizePopulacao; i++) {
            novaPop.setIndividuo(i, popBuffer.get(i));
        }
        popBuffer = null; 
        // reavaliar pop
        novaPop.atualizarValores();
        
        return novaPop;
    }
    
    public Individuo[] crossover(Individuo pai, Individuo mae){
        
        int pontoDeCorte = Constantes.random.nextInt(Constantes.sizeCromossomo-1);
        
        Individuo[] novosInd = new Individuo[2];
        novosInd[PAI] = new Individuo();
        novosInd[MAE] = new Individuo();
        
        Individuo paiBuffer = new Individuo();
        Individuo maeBuffer = new Individuo();
        
        for (int i = 0; i < Constantes.sizeCromossomo; i++) {
            paiBuffer.setGene(i, pai.getGene(i));
            maeBuffer.setGene(i, mae.getGene(i));
            
            novosInd[PAI].setGene(i, pai.getGene(i));
            novosInd[MAE].setGene(i, mae.getGene(i));
        }
        
        if ( Constantes.random.nextDouble() < taxaCorssover ) {
            System.out.println("crossover ON, ponto de corte: " + pontoDeCorte);
            for (int i = pontoDeCorte; i < Constantes.sizeCromossomo; i++) {
                novosInd[PAI].setGene(i, maeBuffer.getGene(i));
                novosInd[MAE].setGene(i, paiBuffer.getGene(i));
            }
        }
        
        return novosInd;
    }
    
    public Individuo mutacao(Individuo ind){
        if ( Constantes.random.nextDouble() <= taxaMutacao ) {
            int geneParaMudar = Constantes.random.nextInt(Constantes.sizeCromossomo);
            ind.mudarBit(geneParaMudar);
        }
        return ind;
    }
    
    public Individuo roleta(Populacao pop){
        double numSorteado = Constantes.random.nextDouble() * 100;
        for (Individuo ind : pop.getPopulacao()) {
            if ( numSorteado >= ind.getFaixaDaRoleta()[0] && numSorteado <= ind.getFaixaDaRoleta()[1] ) {
                return ind;
            }
        }
        // nunca vai acontecer
        return null;
    }
    
}

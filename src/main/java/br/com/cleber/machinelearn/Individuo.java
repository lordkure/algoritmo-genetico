package br.com.cleber.machinelearn;

import java.util.BitSet;

/**
 * Configuracao e criacao dos individuos
 *
 * @author cleber
 */
public class Individuo {

    private BitSet cromossomo;
    private double fitness;
    private double fitnessPercent;
    private double[] faixaDaRoleta = {0, 0};

    public Individuo() {
        this.cromossomo = new BitSet(Constantes.sizeCromossomo);
        for (int i = 0; i < Constantes.sizeCromossomo; i++) {
            double d = Constantes.random.nextDouble();
            cromossomo.set(i, ( d > 0.5f));
        }
    }

    public boolean getGene(int position) {
        return cromossomo.get(position);
    }

    public void setGene(int position, boolean gene) {
        this.cromossomo.set(position, gene);
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getFitnessPercent() {
        return fitnessPercent;
    }

    public void setFitnessPercent(double fitnessPercent) {
        this.fitnessPercent = fitnessPercent;
    }

    public double[] getFaixaDaRoleta() {
        return faixaDaRoleta;
    }

    public void setFaixaDaRoleta(double inicio, double fim) {
        this.faixaDaRoleta[0] = inicio;
        this.faixaDaRoleta[1] = fim;
    }

    /**
     * Seto a bit com o valor inverso do que já existe
     *
     * @param position
     */
    public void mudarBit(int position) {
        if (position < cromossomo.length()) {
            cromossomo.set(position, (!cromossomo.get(position)));
        }
    }

    public int getDecimal() {
        int bitInteger = 0;
        for (int i = 0; i < 32; i++) {
            if (cromossomo.get(i)) {
                bitInteger |= (1 << i);
            }
        }
        return bitInteger;
    }
    
    public String printIndividuo(){
        String result = "Bits: ";
        for (int i = Constantes.sizeCromossomo ; i > 0 ; i--) {
            result += ( (cromossomo.get(i) ? 0 : 1) ) + " ";
        }
        
        result += " || DECIMAL: " + getDecimal() + " || APTIDAO: " + getFitness() + " || PORCENTAGEM: " + getFitnessPercent();
        
        return result;
    } 

}

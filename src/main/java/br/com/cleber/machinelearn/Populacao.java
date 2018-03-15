/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cleber.machinelearn;

/**
 *
 * @author cleber
 */
public class Populacao {

    private Individuo[] populacao;

    public Populacao() {
        // inicio a população
        populacao = new Individuo[Constantes.sizePopulacao];
        for (int i = 0; i < populacao.length; i++) {
            // inicio cada individuo da população
            populacao[i] = new Individuo();
        }        
        //avaliação da população
        calcularFitness();
        calcularFitnessPercent();
        calcularRangeRoleta();
    }

    // calcula o Fitness de cada um utilizando a função na Contantes
    public void calcularFitness() {
        for (int i = 0; i < Constantes.sizePopulacao; i++) {
            this.populacao[i].setFitness(Constantes.function1(this.populacao[i].getDecimal()));
        }
    }

    public void calcularFitnessPercent() {
        double somatoriaFitness = 0;
        for (int i = 0; i < Constantes.sizePopulacao; i++) {
            somatoriaFitness += this.populacao[i].getFitness();
        }
        for (int i = 0; i < Constantes.sizePopulacao; i++) {
            this.populacao[i].setFitnessPercent((this.populacao[i].getFitness() * 100) / somatoriaFitness);
        }
    }

    // calcula o range na roleta
    public void calcularRangeRoleta() {
        //ordernar individuos em ordem crescente
        ordenaIndividuos();

        double somatoria = 0;

        for (int i = 0; i < Constantes.sizePopulacao; i++) {
            if (i == 0) {
                somatoria += populacao[i].getFitnessPercent();
                populacao[i].setFaixaDaRoleta(0, somatoria);
            } else if (i == (Constantes.sizePopulacao - 1)) {
                populacao[i].setFaixaDaRoleta(somatoria, 100f);
            } else {
                populacao[i].setFaixaDaRoleta(somatoria, somatoria + populacao[i].getFitnessPercent());
                somatoria += populacao[i].getFitnessPercent();
            }
        }

    }
    
    public Double getMediaPopulacao(){
        double somaPop = 0;
        for (Individuo individuo : populacao) {
            somaPop += individuo.getFitness();
        }
        return somaPop / Constantes.sizePopulacao;
    }

    public void atualizarValores() {
        // calcular fitness
        calcularFitness();
        // calcular fitnesspercent
        calcularFitnessPercent();
        // calcular range roleta
        calcularRangeRoleta();
    }

    public void ordenaIndividuos() {
        Individuo aux;
        for (int i = 0; i < Constantes.sizePopulacao; i++) {
            for (int j = 0; j < Constantes.sizePopulacao; j++) {
                if (populacao[i].getFitnessPercent() < populacao[j].getFitnessPercent()) {
                    aux = populacao[i];
                    populacao[i] = populacao[j];
                    populacao[j] = aux;
                }
            }
        }
    }//ordenaIndividuos

    public Individuo[] getPopulacao() {
        return populacao;
    }

    public void setIndividuo(int position, Individuo individuo) {
        this.populacao[position] = individuo;
    }
    
    public String printPopulacao(){
        String result = "";
        for (int i = 0; i < Constantes.sizePopulacao; i++) {
            result = result + populacao[i].printIndividuo() + " || FAIXA ROLETA: "
                            + populacao[i].getFaixaDaRoleta()[0] + ":" + populacao[i].getFaixaDaRoleta()[1] + "\n";
        }
        return result;
    }

}

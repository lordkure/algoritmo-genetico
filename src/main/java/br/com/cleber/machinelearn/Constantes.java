package br.com.cleber.machinelearn;

import java.util.Random;

/**
 * Método auxiliar onde se encontra as configuracoes do programa
 * @author cleber
 */
public abstract class Constantes {
    
    public static int sizeCromossomo = 9;
    public static int sizePopulacao = 500;
    public static int functionXSize = (int) Math.pow(2, sizeCromossomo); // tamanho do cromossomo exponencial em 2
    public static Random random = new Random(System.currentTimeMillis()); // objeto randomico gerado pelo tempo atual, evitando numeros repitidos
    
    
    /**
     * Função f(x) = 100 + | x + sen ( raiz quadrada de | x | )
     * @return double
     */
    public static double function1(double x){
        return (double) (100 + Math.abs(x * Math.sin(Math.sqrt(Math.abs(x)))));
    }
    
}

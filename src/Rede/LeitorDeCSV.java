/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rede;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Gustavo Eloi
 */
public class LeitorDeCSV {
    
    
    public ArrayList<Integer[]> lerArquivoDeValores(String path) throws IOException {
       try{ 
        String linha ="";
        int quantidadeDeValoresPorLinha;
        int contadorDeLinhas=0;
        
        BufferedReader leitorDeArquivo = new BufferedReader(new FileReader(path));
        //PARTE QUE LE O CABEÇALHO, É UM NOP
        linha = leitorDeArquivo.readLine();
        
        quantidadeDeValoresPorLinha = linha.split(",").length;   
        //
        
        ArrayList<Integer[]> matrizDeValores = new ArrayList<Integer[]>();
        
        //LACO PARA LER OS VALORES
        while(( (linha = leitorDeArquivo.readLine()) != null)){
            String [] valores =  linha.split(",");
            
            //SEMPRE O ULTIMO VALOR DA LINHA É O VALOR ESPERADO(VALOR DE CLASSE)
            matrizDeValores.add(new Integer[quantidadeDeValoresPorLinha]);
            contadorDeLinhas ++;
            for(int j=0;j<valores.length;j++){
                matrizDeValores.get(contadorDeLinhas)[j]=Integer.parseInt(valores[j]);
            }
            
            
        }        
                
      return matrizDeValores;
        
     }catch(FileNotFoundException e){
         System.out.println("ARQUIVO NAO ENCONTRADO");
         return null;
     }
    }
}

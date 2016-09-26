package Rede;

import com.sun.istack.internal.NotNull;
import java.util.ArrayList;

public class NeuronioSaida extends Neuronio {
    
    private float net;
    private float erro;
    private final ArrayList<Float> listaPesos;
    private final ArrayList<Float> listaEntrada;
    
    public NeuronioSaida(@NotNull final int numeroIdentificador) {
        super(numeroIdentificador);
        listaEntrada = new ArrayList<Float>();
        listaPesos = new ArrayList<Float>();
        net = 0.0f;
        erro = 0.0f;
    }
    
    public void setEntrada(int identificador, float valor){
        listaEntrada.set(identificador, valor);
    }
    
    public void setPeso(int identificador, float valor){
        listaPesos.set(identificador, valor);
    }
    
    public float getPeso(int identificador){
        return listaPesos.get(identificador);
    }

    public void calculaValorComFuncaoLogistica() {
        valor = (float)(1.0f/(1 + Math.pow(Math.E, -net)));
    }
    
    public void calculaValorComFuncaoTangenteHiperbolica() {
        valor = (float)((1 - Math.pow(Math.E, -2*net))/(1 + Math.pow(Math.E, -2*net)));
    }

    public void calculaNET() {
        net = 0.0f;
        for(int i = 0; i < listaEntrada.size(); i++)
            net += listaEntrada.get(i) * listaPesos.get(i);
    }
    
    public float getNET(){
        return net;
    }
    
    public void calculaErroLogistico(int valorEsperado){
        erro = (valorEsperado - valor)*(valor*(1 - valor));
    }
    
    public void calculaErroTangenteHiperbolico(int valorEsperado){
        erro = (valorEsperado - valor)*(1 - valor*valor);
    }
    
    public float getErro(){
        return erro;
    }
    
    void criaListaDePesosAleatorios(int quantidadeElementosCamadaOculta) {
        int sinal;
        float valor;
        for(int i = 0; i < quantidadeElementosCamadaOculta; i++){
            sinal = (int) Math.random();
            sinal = (int) Math.pow(-1,sinal);
            valor = sinal *(float)Math.random();
            listaPesos.add(valor);
        }
    }

    void criarListaDeEntrada(int quantidadeElementosCamadaOculta) {
        for(int i = 0; i < quantidadeElementosCamadaOculta; i++)
            listaPesos.add(1f);
    }

    void atualizarListaDePesosAleatorios(int quantidadeElementosCamadaOculta) {
        int sinal;
        float valor;
        for(int i = listaPesos.size(); i < quantidadeElementosCamadaOculta; i++){
            sinal = (int) Math.random();
            sinal = (int) Math.pow(-1,sinal);
            valor = sinal *(float)Math.random();
            listaPesos.add(valor);
        }
        
        for(int i = listaPesos.size(); i > quantidadeElementosCamadaOculta; i--){
            listaPesos.remove(i-1);
        }        
    }

    void atualizarListaDeEntrada(int quantidadeElementosCamadaOculta) {
        for(int i = listaEntrada.size(); i < quantidadeElementosCamadaOculta; i++)
            listaPesos.add(1f);
        
        for(int i = listaEntrada.size(); i > quantidadeElementosCamadaOculta; i--)
            listaPesos.remove(i-1);
    }

}

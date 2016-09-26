package Rede;

import com.sun.istack.internal.NotNull;
import java.util.ArrayList;

public class NeuronioOculto extends Neuronio {
    
    private ArrayList<NeuronioSaida> listaNeuroniosCamadaSaida;
    private float net;
    private final ArrayList<Float> listaPesos;
    private final ArrayList<Float> listaEntrada;
    private float erro;
    
    public NeuronioOculto(@NotNull final int numeroIdentificador) {
        super(numeroIdentificador);
        listaNeuroniosCamadaSaida = null;
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
    
    public void setListaNeuroniosCamadaSaida(ArrayList<NeuronioSaida> listaNeuroniosCamadaSaida){
        this.listaNeuroniosCamadaSaida = listaNeuroniosCamadaSaida;
    }
    
    public void atualizaProximaCamada(){
        for(NeuronioSaida neuronio:listaNeuroniosCamadaSaida)
            neuronio.setEntrada(numeroIdentificador, valor);
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
    
    public void calculaErroLogistico(){
        erro = 0.0f;
        for(NeuronioSaida neuronio:listaNeuroniosCamadaSaida)
            erro += neuronio.getErro()*neuronio.getPeso(numeroIdentificador);
        erro *= (valor*(1 - valor));
        
    }
    
    public void calculaErroTangenteHiperbolico(){
        erro = 0.0f;
        for(NeuronioSaida neuronio:listaNeuroniosCamadaSaida)
            erro += neuronio.getErro()*neuronio.getPeso(numeroIdentificador);
        erro *= (1 - valor*valor);
    }
    
    public float getPeso(int identificador){
        return listaPesos.get(identificador);
    }
    
    public float getErro(){
        return erro;
    }

    void criaListaDePesosAleatorios(int quantidadeElementosCamadaEntrada) {
        int sinal;
        float valor;
        for(int i = 0; i < quantidadeElementosCamadaEntrada; i++){
            sinal = (int) Math.random();
            sinal = (int) Math.pow(-1,sinal);
            valor = sinal *(float)Math.random();
            listaPesos.add(valor);
        }
    }

    void criarListaDeEntrada(int quantidadeElementosCamadaEntrada) {
        for(int i = 0; i < quantidadeElementosCamadaEntrada; i++)
            listaPesos.add(1f);
    }

}

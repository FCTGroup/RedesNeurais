package Rede;

import com.sun.istack.internal.NotNull;
import java.util.ArrayList;

public class NeuronioEntrada extends Neuronio {

    private ArrayList<NeuronioOculto> listaNeuroniosCamadaOculta;
    
    public NeuronioEntrada(@NotNull final int numeroIdentificador) {
        super(numeroIdentificador);
        listaNeuroniosCamadaOculta = null;
    }
    
    public void setListaNeuroniosCamadaOculta(ArrayList<NeuronioOculto> listaNeuroniosCamadaOculta){
        this.listaNeuroniosCamadaOculta = listaNeuroniosCamadaOculta;
    }
    
    public void atualizaProximaCamada(){
        for(NeuronioOculto neuronio:listaNeuroniosCamadaOculta)
            neuronio.setEntrada(numeroIdentificador, valor);
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

}

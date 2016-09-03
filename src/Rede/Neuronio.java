package Rede;

import com.sun.istack.internal.NotNull;

public abstract class Neuronio {

    protected float valor;

    protected final int numeroIdentificador;
        
    public Neuronio(@NotNull final int numeroIdentificador){
        this.numeroIdentificador = numeroIdentificador;
        valor = 0.0f;
    }

    public float getValor() {
        return valor;
    }

}

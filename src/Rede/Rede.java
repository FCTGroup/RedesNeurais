package Rede;

import java.util.ArrayList;

public class Rede {

        public static final int FUNCAO_LOGISTICA = 1;

	public static final int FUNCAO_TANGENTE_HIPERBOLICA = 2;

	public static final int PARADA_POR_ITERACAO = 1;

	public static final int PARADA_POR_ERRO = 2;
    
        private ArrayList<NeuronioEntrada> listaNeuronioEntrada;
        
        private ArrayList<NeuronioOculto> listaNeuronioOculto;
        
        private ArrayList<NeuronioSaida> listaNeuronioSaida;
        
	private float erro;

	private int funcaoTransferencia;

	private int tipoParada;

	private float taxaAprendizado;

	private int matrizConfusao[][];

	private float matrizAmostras[][];
        
        private int quantidadeAmostras;
        
        private int quantidadeElementosCamadaOculta;
        
        private int quantidadeElementosCamadaEntrada;
        
        private int quantidadeElementosCamadaSaida;
        
        public Rede(){
            erro = 0.0f;
            funcaoTransferencia =  FUNCAO_LOGISTICA;
            tipoParada = PARADA_POR_ITERACAO;
            taxaAprendizado = 1;
            listaNeuronioEntrada = new ArrayList<NeuronioEntrada>();
            listaNeuronioOculto = new ArrayList<NeuronioOculto>();
            listaNeuronioSaida = new ArrayList<NeuronioSaida>();
            matrizConfusao = null;
            matrizAmostras = null;
            quantidadeAmostras = 0;
            quantidadeElementosCamadaOculta = 0;
            quantidadeElementosCamadaEntrada = 0;
            quantidadeElementosCamadaSaida = 0;
        }

	public void carregaArquivoTreinamento(String url) {

	}

	private void criarConexaoNeuronios() {

	}

	public void carregaArquivoTeste(String url) {

	}

	public void testar() {
            float[] vetorResultado;
            float[] vetorEntrada;
            float resultadoAtual;
            int posicaoResultadoDaAmostra = quantidadeElementosCamadaEntrada;
            int posicaoResultadoAtual;
            int resultadoEsperado;
            int resultadoObtido;
            
            for(int numeroAmostra = 0; numeroAmostra < quantidadeAmostras; numeroAmostra++){
                vetorEntrada = matrizAmostras[numeroAmostra];
                vetorResultado = calcular(vetorEntrada);
                
                resultadoEsperado = (int)matrizAmostras[numeroAmostra][posicaoResultadoDaAmostra];
                
                posicaoResultadoAtual = 0;
                resultadoAtual = 0;
                resultadoObtido = 0;
                while(posicaoResultadoAtual < quantidadeElementosCamadaSaida){
                    if(vetorResultado[posicaoResultadoAtual] > resultadoAtual){
                        resultadoAtual = vetorResultado[posicaoResultadoAtual];
                        resultadoObtido = posicaoResultadoAtual;
                    }
                    posicaoResultadoAtual++;
                }
                matrizConfusao[resultadoEsperado][resultadoObtido]++;
            }
	}
        
        public float[] calcular(float entrada[]){
            float vetorResultado[] = new float[quantidadeElementosCamadaSaida];
            int numeroNeuronio = 0;
            
            for(; numeroNeuronio < quantidadeElementosCamadaEntrada; numeroNeuronio++){
                listaNeuronioEntrada.get(numeroNeuronio).setValor(
                        entrada[numeroNeuronio]);
                listaNeuronioEntrada.get(numeroNeuronio).atualizaProximaCamada();
            }
            
            for(NeuronioOculto neuronio:listaNeuronioOculto){
                if(funcaoTransferencia == FUNCAO_LOGISTICA)
                    neuronio.calculaValorComFuncaoLogistica();
                else if(funcaoTransferencia == FUNCAO_TANGENTE_HIPERBOLICA)
                    neuronio.calculaValorComFuncaoTangenteHiperbolica();
                neuronio.atualizaProximaCamada();
            }
            
            numeroNeuronio = 0;
            for(NeuronioSaida neuronio:listaNeuronioSaida){
                if(funcaoTransferencia == FUNCAO_LOGISTICA)
                    neuronio.calculaValorComFuncaoLogistica();
                else if(funcaoTransferencia == FUNCAO_TANGENTE_HIPERBOLICA)
                    neuronio.calculaValorComFuncaoTangenteHiperbolica();
                vetorResultado[numeroNeuronio++] = neuronio.getValor();
            }
            
            return vetorResultado;
        }

	public void treinar(int quantidadeIteracoes) {
            int contadorDeIteracoes = 0;
            do{
                for(int numeroAmostra = 0; numeroAmostra < quantidadeAmostras; numeroAmostra++)
                    iterar(numeroAmostra);
                contadorDeIteracoes++;
            }while(contadorDeIteracoes < quantidadeIteracoes);
	}

	public void treinar(float erroMaximo) {
            do{
                for(int numeroAmostra = 0; numeroAmostra < quantidadeAmostras; numeroAmostra++)
                    iterar(numeroAmostra);
            }while(erro > erroMaximo);
	}

	private void iterar(int numeroAmostra) {
            
            aplicarEntradasNosNeuronios(numeroAmostra);

            calculaNETCamadaOculta();
            
            aplicaFuncaoTransferenciaCamadaOculta();
            
            calculaNETCamadaSaida();
            
            calculaValorCamadaSaida();
            
            calculaErroCamadaSaida(numeroAmostra);
            
            calculaErroCamadaOculta();
            
            atualizaPesoCamadaSaida();
            
            atualizaPesoCamadaOculta();
            
            calculaErroDaRede();
            
	}

    private void aplicarEntradasNosNeuronios(int iteracao) {
        listaNeuronioEntrada.stream().map((neuronio) -> {
            neuronio.setValor(
                    matrizAmostras[iteracao][neuronio.getNumeroIdentificador()]);
                return neuronio;
            }).forEach((neuronio) -> {
                neuronio.atualizaProximaCamada();
            });
    } 

    private void calculaNETCamadaSaida() {
        listaNeuronioSaida.stream().forEach((neuronio) -> {
            neuronio.calculaNET();
            });
    }

    private void calculaErroCamadaOculta() {
        listaNeuronioOculto.stream().forEach((neuronio) -> {
            if(funcaoTransferencia == FUNCAO_LOGISTICA)
                neuronio.calculaErroLogistico();
            else if(funcaoTransferencia == FUNCAO_TANGENTE_HIPERBOLICA)
                neuronio.calculaErroTangenteHiperbolico();
            });
    }

    private void atualizaPesoCamadaSaida() {
        float novoPeso;
            for(NeuronioSaida neuronio:listaNeuronioSaida){
                for(int posicaoLista = 0; posicaoLista < quantidadeElementosCamadaOculta; posicaoLista++){
                    novoPeso = neuronio.getPeso(posicaoLista);
                    novoPeso += taxaAprendizado*neuronio.getErro()*listaNeuronioOculto.get(posicaoLista).getValor();
                    neuronio.setPeso(posicaoLista, novoPeso);
                }
            }
    }

    private void atualizaPesoCamadaOculta() {
        float novoPeso;
        for(NeuronioOculto neuronio:listaNeuronioOculto){
                for(int posicaoLista = 0; posicaoLista < quantidadeElementosCamadaOculta; posicaoLista++){
                    novoPeso = neuronio.getPeso(posicaoLista);
                    novoPeso += taxaAprendizado*neuronio.getErro()*listaNeuronioEntrada.get(posicaoLista).getValor();
                    neuronio.setPeso(posicaoLista, novoPeso);
                }
            }
    }

    private void calculaErroDaRede() {
        erro = 0;
        listaNeuronioSaida.stream().forEach((neuronio) -> {
            erro += Math.pow(neuronio.getErro(),2);
            });
            erro /= 2;
    }

    private void calculaNETCamadaOculta() {
        listaNeuronioOculto.stream().forEach((neuronio) -> {
            neuronio.calculaNET();
            });
    }

    private void aplicaFuncaoTransferenciaCamadaOculta() {
        listaNeuronioOculto.stream().map((neuronio) -> {
            if(funcaoTransferencia == FUNCAO_LOGISTICA)
                neuronio.calculaValorComFuncaoLogistica();
            else if(funcaoTransferencia == FUNCAO_TANGENTE_HIPERBOLICA)
                neuronio.calculaValorComFuncaoTangenteHiperbolica();
                return neuronio;
            }).forEach((neuronio) -> {
                neuronio.atualizaProximaCamada();
            });
    }

    private void calculaValorCamadaSaida() {
        listaNeuronioSaida.stream().forEach((neuronio) -> {
            if(funcaoTransferencia == FUNCAO_LOGISTICA)
                neuronio.calculaValorComFuncaoLogistica();
            else if(funcaoTransferencia == FUNCAO_TANGENTE_HIPERBOLICA)
                neuronio.calculaValorComFuncaoTangenteHiperbolica();
            });
    }

    private void calculaErroCamadaSaida(int iteracao) {
        listaNeuronioSaida.stream().forEach((neuronio) -> {
            if(funcaoTransferencia == FUNCAO_LOGISTICA)
                neuronio.calculaErroLogistico(
                        (int)matrizAmostras[iteracao][quantidadeAmostras]);
            else if(funcaoTransferencia == FUNCAO_TANGENTE_HIPERBOLICA)
                neuronio.calculaErroTangenteHiperbolico(
                        (int)matrizAmostras[iteracao][quantidadeAmostras]);
            });
    }

}

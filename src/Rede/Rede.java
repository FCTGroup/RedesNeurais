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
        }

	public void carregaArquivoTreinamento(String url) {

	}

	private void criarConexaoNeuronios() {

	}

	public void carregaArquivoTeste(String url) {

	}

	public void testar() {

	}

	public void treinar(int quantidadeIteracoes) {

	}

	public void treinar(float erroMaximo) {

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

package Rede;

import java.io.IOException;
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

	public void carregaArquivoTreinamento(String url) throws IOException {
            LeitorDeCSV leitorTreinamento = new LeitorDeCSV();
            ArrayList<Integer[]> valoresTreinamento = leitorTreinamento.lerArquivoDeValores(url);
            int numeroDeLinhas=valoresTreinamento.get(0).length, numeroDeColunas=valoresTreinamento.size();
        
             this.matrizAmostras= new float[numeroDeLinhas][numeroDeColunas]; 
            //LACO PRA CONVERTER A ESTRUTURA matriz array normal
                for(int i=0;i<numeroDeLinhas;i++){
                    Integer[] valores = valoresTreinamento.get(i);
                    for(int j=0;j<numeroDeColunas;j++){
                        this.matrizAmostras[i][j]= valores[j];
                    }
                }
            //
            
            //Calculado quantidade de Neuronios de Cada Camada
            this.quantidadeElementosCamadaEntrada=numeroDeColunas-1;
            this.quantidadeElementosCamadaOculta = this.quantidadeElementosCamadaEntrada*2;
            this.quantidadeElementosCamadaSaida =this.quantidadeElementosCamadaEntrada;
            
            //TODO CRIAR A CONEXAO DOS NEURONIOS
	}

	public void criarConexaoNeuronios() {

	}

	public void carregaArquivoTeste(String url) throws IOException {
            LeitorDeCSV leitorTreinamento = new LeitorDeCSV();
            ArrayList<Integer[]> valoresTreinamento = leitorTreinamento.lerArquivoDeValores(url);
            int numeroDeLinhas=valoresTreinamento.get(0).length, numeroDeColunas=valoresTreinamento.size();
        
             this.matrizAmostras= new float[numeroDeLinhas][numeroDeColunas]; 
            //LACO PRA CONVERTER A ESTRUTURA matriz array normal
                for(int i=0;i<numeroDeLinhas;i++){
                    Integer[] valores = valoresTreinamento.get(i);
                    for(int j=0;j<numeroDeColunas;j++){
                        this.matrizAmostras[i][j]= valores[j];
                    }
                }
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
                matrizConfusao[resultadoEsperado][resultadoObtido]++;//isto esta certo? a matriz recebe ++, ta estranho 
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

    public ArrayList<NeuronioEntrada> getListaNeuronioEntrada() {
        return listaNeuronioEntrada;
    }

    public void setListaNeuronioEntrada(ArrayList<NeuronioEntrada> listaNeuronioEntrada) {
        this.listaNeuronioEntrada = listaNeuronioEntrada;
    }

    public ArrayList<NeuronioOculto> getListaNeuronioOculto() {
        return listaNeuronioOculto;
    }

    public void setListaNeuronioOculto(ArrayList<NeuronioOculto> listaNeuronioOculto) {
        this.listaNeuronioOculto = listaNeuronioOculto;
    }

    public ArrayList<NeuronioSaida> getListaNeuronioSaida() {
        return listaNeuronioSaida;
    }

    public void setListaNeuronioSaida(ArrayList<NeuronioSaida> listaNeuronioSaida) {
        this.listaNeuronioSaida = listaNeuronioSaida;
    }

    public float getErro() {
        return erro;
    }

    public void setErro(float erro) {
        this.erro = erro;
    }

    public int getFuncaoTransferencia() {
        return funcaoTransferencia;
    }

    public void setFuncaoTransferencia(int funcaoTransferencia) {
        this.funcaoTransferencia = funcaoTransferencia;
    }

    public int getTipoParada() {
        return tipoParada;
    }

    public void setTipoParada(int tipoParada) {
        this.tipoParada = tipoParada;
    }

    public float getTaxaAprendizado() {
        return taxaAprendizado;
    }

    public void setTaxaAprendizado(float taxaAprendizado) {
        this.taxaAprendizado = taxaAprendizado;
    }

    public int[][] getMatrizConfusao() {
        return matrizConfusao;
    }

    public void setMatrizConfusao(int[][] matrizConfusao) {
        this.matrizConfusao = matrizConfusao;
    }

    public float[][] getMatrizAmostras() {
        return matrizAmostras;
    }

    public void setMatrizAmostras(float[][] matrizAmostras) {
        this.matrizAmostras = matrizAmostras;
    }

    public int getQuantidadeAmostras() {
        return quantidadeAmostras;
    }

    public void setQuantidadeAmostras(int quantidadeAmostras) {
        this.quantidadeAmostras = quantidadeAmostras;
    }

    public int getQuantidadeElementosCamadaOculta() {
        return quantidadeElementosCamadaOculta;
    }

    public void setQuantidadeElementosCamadaOculta(int quantidadeElementosCamadaOculta) {
        this.quantidadeElementosCamadaOculta = quantidadeElementosCamadaOculta;
    }

    public int getQuantidadeElementosCamadaEntrada() {
        return quantidadeElementosCamadaEntrada;
    }

    public void setQuantidadeElementosCamadaEntrada(int quantidadeElementosCamadaEntrada) {
        this.quantidadeElementosCamadaEntrada = quantidadeElementosCamadaEntrada;
    }

    public int getQuantidadeElementosCamadaSaida() {
        return quantidadeElementosCamadaSaida;
    }

    public void setQuantidadeElementosCamadaSaida(int quantidadeElementosCamadaSaida) {
        this.quantidadeElementosCamadaSaida = quantidadeElementosCamadaSaida;
    }
    
    public String imprimirMatrizDeConfusao(){
        String matrizImpressa ="";
        
        
        for(int i=0;i<matrizConfusao[0].length;i++){
            for(int j=0;j<matrizConfusao[0].length;j++){
                matrizImpressa+=""+matrizConfusao[i][j]+" ";
            }
            matrizImpressa+='\n';
        }
        
        return matrizImpressa;
    }
}

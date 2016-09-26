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
        
        private float matrizAmostrasTeste[][];
        
        private int quantidadeAmostras;
        
        private int quantidadeAmostrasTeste;
        
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
            int numeroDeColunas=valoresTreinamento.get(0).length;
            int numeroDeLinhas=valoresTreinamento.size();
        
             this.matrizAmostras= new float[numeroDeLinhas][numeroDeColunas]; 
            //LACO PRA CONVERTER A ESTRUTURA matriz array normal
                for(int i=0;i<numeroDeLinhas;i++){
                    Integer[] valores = valoresTreinamento.get(i);
                    for(int j=0;j<numeroDeColunas;j++){
                        this.matrizAmostras[i][j]= valores[j];
                    }
                }
            //
            int quantidadeDeClasses = 0;
            for(int i = 0; i < numeroDeLinhas; i++)
                if(matrizAmostras[i][numeroDeColunas-1] > quantidadeDeClasses)
                    quantidadeDeClasses = (int) (matrizAmostras[i][numeroDeColunas-1]);
            
            setQuantidadeAmostras(numeroDeLinhas);
            
            //Calculado quantidade de Neuronios de Cada Camada
            setQuantidadeElementosCamadaEntrada(numeroDeColunas-1);
            setQuantidadeElementosCamadaSaida(quantidadeDeClasses);
            setQuantidadeElementosCamadaOculta(
                    (int) Math.round(
                            Math.pow(this.quantidadeElementosCamadaEntrada*this.quantidadeElementosCamadaSaida,1f/2f)
                    )
            );
            
            //TODO CRIAR A CONEXAO DOS NEURONIOS
            setListaNeuronioEntrada();
            setListaNeuronioOculto();
            setListaNeuronioSaida();
            
	}

	protected void criarConexaoNeuronios() {
            for(NeuronioEntrada neuronio:listaNeuronioEntrada)
                neuronio.setListaNeuroniosCamadaOculta(listaNeuronioOculto);
            
            for(NeuronioOculto neuronio:listaNeuronioOculto){
                neuronio.criaListaDePesosAleatorios(quantidadeElementosCamadaEntrada);
                neuronio.criarListaDeEntrada(quantidadeElementosCamadaEntrada);
                neuronio.setListaNeuroniosCamadaSaida(listaNeuronioSaida);
            }
            
            for(NeuronioSaida neuronio:listaNeuronioSaida){
                neuronio.criaListaDePesosAleatorios(quantidadeElementosCamadaOculta);
                neuronio.criarListaDeEntrada(quantidadeElementosCamadaOculta);
            }
	}
        
        protected void atualizarConexaoNeuronios() {
          
            for(NeuronioOculto neuronio:listaNeuronioOculto){
                neuronio.criaListaDePesosAleatorios(quantidadeElementosCamadaEntrada);
                neuronio.criarListaDeEntrada(quantidadeElementosCamadaEntrada);
                neuronio.setListaNeuroniosCamadaSaida(listaNeuronioSaida);
            }
            
            for(NeuronioSaida neuronio:listaNeuronioSaida){
                neuronio.atualizarListaDePesosAleatorios(quantidadeElementosCamadaOculta);
                neuronio.atualizarListaDeEntrada(quantidadeElementosCamadaOculta);
            }
	}

	public void carregaArquivoTeste(String url) throws IOException {
            LeitorDeCSV leitorTreinamento = new LeitorDeCSV();
            ArrayList<Integer[]> valoresTreinamento = leitorTreinamento.lerArquivoDeValores(url);
            int numeroDeLinhas=valoresTreinamento.size();
            int numeroDeColunas=valoresTreinamento.get(0).length;
        
             this.matrizAmostrasTeste= new float[numeroDeLinhas][numeroDeColunas]; 
            //LACO PRA CONVERTER A ESTRUTURA matriz array normal
                for(int i=0;i<numeroDeLinhas;i++){
                    Integer[] valores = valoresTreinamento.get(i);
                    for(int j=0;j<numeroDeColunas;j++){
                        this.matrizAmostrasTeste[i][j]= valores[j];
                    }
                }
                
            
            quantidadeAmostrasTeste = numeroDeLinhas;    
	}

	public void testar() {
            float[] vetorResultado;
            float[] vetorEntrada;
            float resultadoAtual;
            int posicaoResultadoDaAmostra = quantidadeElementosCamadaEntrada;
            int posicaoResultadoAtual = 0;
            int resultadoEsperado = 0;
            int posicaoResultadoObtido = 0;
            matrizConfusao = new int[quantidadeElementosCamadaSaida][quantidadeElementosCamadaSaida];
            
            for(int numeroAmostra = 0; numeroAmostra < quantidadeAmostrasTeste; numeroAmostra++){
                vetorEntrada = matrizAmostrasTeste[numeroAmostra];
                
                vetorResultado = calcular(vetorEntrada);
                
                resultadoEsperado = (int)matrizAmostrasTeste[numeroAmostra][posicaoResultadoDaAmostra];
                
                posicaoResultadoAtual = 0;
                resultadoAtual = 0;
                posicaoResultadoObtido = 0;
                System.out.println("\n");
                while(posicaoResultadoAtual < quantidadeElementosCamadaSaida){
                    System.out.print((posicaoResultadoAtual!=0?",":"")+vetorResultado[posicaoResultadoAtual]);
                    
                    if(vetorResultado[posicaoResultadoAtual] > resultadoAtual){
                        resultadoAtual = vetorResultado[posicaoResultadoAtual];
                        posicaoResultadoObtido = posicaoResultadoAtual;
                    }
                    posicaoResultadoAtual++;
                }
                matrizConfusao[resultadoEsperado-1][posicaoResultadoObtido]++;//isto esta certo? a matriz recebe ++, ta estranho 
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
                for(int numeroAmostra = 0; numeroAmostra < quantidadeAmostras; numeroAmostra++){
                    iterar(numeroAmostra);
                }
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
        for(NeuronioEntrada neuronio:listaNeuronioEntrada){
            neuronio.setValor(
                    matrizAmostras[iteracao][neuronio.getNumeroIdentificador()]);
            neuronio.atualizaProximaCamada();
        }
    } 

    private void calculaNETCamadaSaida() {
        for(NeuronioSaida neuronio:listaNeuronioSaida){
            neuronio.calculaNET();
        }
    }

    private void calculaErroCamadaOculta() {
        for(NeuronioOculto neuronio:listaNeuronioOculto){
            if(funcaoTransferencia == FUNCAO_LOGISTICA)
                neuronio.calculaErroLogistico();
            else if(funcaoTransferencia == FUNCAO_TANGENTE_HIPERBOLICA)
                neuronio.calculaErroTangenteHiperbolico();
        }
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
                for(int posicaoLista = 0; posicaoLista < quantidadeElementosCamadaEntrada; posicaoLista++){
                    novoPeso = neuronio.getPeso(posicaoLista);
                    novoPeso += taxaAprendizado*neuronio.getErro()*listaNeuronioEntrada.get(posicaoLista).getValor();
                    neuronio.setPeso(posicaoLista, novoPeso);
                }
            }
    }

    private void calculaErroDaRede() {
        erro = 0;
        for(NeuronioSaida neuronio:listaNeuronioSaida){
            erro += Math.pow(neuronio.getErro(),2);
            erro /= 2;
        }
    }

    private void calculaNETCamadaOculta() {
        for(NeuronioOculto neuronio:listaNeuronioOculto){
            neuronio.calculaNET();
        }
    }

    private void aplicaFuncaoTransferenciaCamadaOculta() {
        for(NeuronioOculto neuronio:listaNeuronioOculto){
            if(funcaoTransferencia == FUNCAO_LOGISTICA)
                neuronio.calculaValorComFuncaoLogistica();
            else if(funcaoTransferencia == FUNCAO_TANGENTE_HIPERBOLICA)
                neuronio.calculaValorComFuncaoTangenteHiperbolica();
            neuronio.atualizaProximaCamada();
        }
    }

    private void calculaValorCamadaSaida() {
        for(NeuronioSaida neuronio:listaNeuronioSaida){
            if(funcaoTransferencia == FUNCAO_LOGISTICA)
                neuronio.calculaValorComFuncaoLogistica();
            else if(funcaoTransferencia == FUNCAO_TANGENTE_HIPERBOLICA)
                neuronio.calculaValorComFuncaoTangenteHiperbolica();
        }
    }

    private void calculaErroCamadaSaida(int iteracao) {
        for(NeuronioSaida neuronio:listaNeuronioSaida){
            if(funcaoTransferencia == FUNCAO_LOGISTICA)
                neuronio.calculaErroLogistico(
                        (int)matrizAmostras[iteracao][quantidadeElementosCamadaEntrada]);
            else if(funcaoTransferencia == FUNCAO_TANGENTE_HIPERBOLICA)
                neuronio.calculaErroTangenteHiperbolico(
                        (int)matrizAmostras[iteracao][quantidadeElementosCamadaEntrada]);
        }
    }

    public ArrayList<NeuronioEntrada> getListaNeuronioEntrada() {
        return listaNeuronioEntrada;
    }

    private void setListaNeuronioEntrada() {
        for(int i = 0; i < quantidadeElementosCamadaEntrada; i++)
            listaNeuronioEntrada.add(new NeuronioEntrada(i));
    }

    public ArrayList<NeuronioOculto> getListaNeuronioOculto() {
        return listaNeuronioOculto;
    }

    private void setListaNeuronioOculto() {
        for(int i = 0; i < quantidadeElementosCamadaOculta; i++)
            listaNeuronioOculto.add(new NeuronioOculto(i));
    }

    public ArrayList<NeuronioSaida> getListaNeuronioSaida() {
        return listaNeuronioSaida;
    }

    private void setListaNeuronioSaida() {
        for(int i = 0; i < quantidadeElementosCamadaSaida; i++)
            listaNeuronioSaida.add(new NeuronioSaida(i));
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
    
    public String imprimirRede(){
        String rede = "----";
        
        for(int i = 0; i < quantidadeElementosCamadaEntrada; i++)
            rede += (i!=0?",":"")+listaNeuronioEntrada.get(i).getNumeroIdentificador();
        
        rede+="\n";
        
        for(int i = 0; i < quantidadeElementosCamadaOculta; i++)
            rede += (i!=0?",":"")+listaNeuronioOculto.get(i).getNumeroIdentificador()+"("+listaNeuronioOculto.get(i)+")";
        
        rede+="\n";
        
        for(int i = 0; i < quantidadeElementosCamadaSaida; i++)
            rede += (i!=0?",":"")+listaNeuronioSaida.get(i).getNumeroIdentificador();
        
        rede+="\n";
        
        rede+= "------\n";
        
        return rede;
    }

    void atualizarCamadaOculta() {
        int i = listaNeuronioOculto.size();
        NeuronioOculto novoNeuronio;
        while(i < quantidadeElementosCamadaOculta){
            novoNeuronio = new NeuronioOculto(i++);
            novoNeuronio.criaListaDePesosAleatorios(quantidadeElementosCamadaEntrada);
            novoNeuronio.criarListaDeEntrada(quantidadeElementosCamadaEntrada);
            listaNeuronioOculto.add(novoNeuronio);
            
        }
        
        while(i > quantidadeElementosCamadaOculta)
            listaNeuronioOculto.remove((i-- - 1));
        
    }
}

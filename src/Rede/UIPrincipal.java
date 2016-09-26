/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rede;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gustavo Eloi
 */
public class UIPrincipal extends javax.swing.JFrame {
    Rede redeNeural;
    /**
     * Creates new form UIPrincipal
     */
    public UIPrincipal() throws IOException {
        
        //MUDANDO AS CONFIGURAÇOES DO RADIO GROUP
        //this.condicaoDeParadaGroup.add(this.iteracoesRadio);
        //this.condicaoDeParadaGroup.add(this.erroMaximoRedeRadio);
        //this.condicaoDeParadaGroup.clearSelection();
        //
        initComponents();
        qtdNeuroniosCOTxt.setEnabled(false);
        botTreinar.setEnabled(false);
        realizarTesteBut.setEnabled(false);
        jButton2.setEnabled(false);
        realizarTesteBut.setEnabled(false);
        iteracoesRadio.setSelected(true);
        
    }
    
    public void iniciarRedeNeural(String url) throws IOException{
        redeNeural = new Rede();
        redeNeural.carregaArquivoTreinamento(url);
        redeNeural.criarConexaoNeuronios();
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        condicaoDeParadaGroup = new javax.swing.ButtonGroup();
        panelOpcoes = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        textTaxaAprendizado = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        comboOpParada = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        iteracoesRadio = new javax.swing.JRadioButton();
        erroMaximoRedeRadio = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        qtdNeuroniosCOTxt = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        numeroIteracoesTxt = new javax.swing.JTextField();
        erroMaxTxt = new javax.swing.JTextField();
        botTreinar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        carregarArquivoTeste = new javax.swing.JButton();
        realizarTesteBut = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        mconfTxt = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Taxa de Aprendizado :");

        textTaxaAprendizado.setText("1");
        textTaxaAprendizado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textTaxaAprendizadoActionPerformed(evt);
            }
        });

        jLabel2.setText("Função de Tranferência :");

        comboOpParada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Logística", "Tangente Hiperbólica" }));
        comboOpParada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboOpParadaActionPerformed(evt);
            }
        });

        jLabel3.setText("Condição de Parada :");

        iteracoesRadio.setText("Iterações");
        iteracoesRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iteracoesRadioActionPerformed(evt);
            }
        });

        erroMaximoRedeRadio.setText("Erro Máximo da Rede");
        erroMaximoRedeRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                erroMaximoRedeRadioActionPerformed(evt);
            }
        });

        jLabel4.setText("Quantidade de Neurônios na Camada Oculta: ");

        qtdNeuroniosCOTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qtdNeuroniosCOTxtActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel5.setText("Quantidade de Iterações :");

        jLabel6.setText("Erro Máximo da Rede:");

        numeroIteracoesTxt.setText("50");
        numeroIteracoesTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numeroIteracoesTxtActionPerformed(evt);
            }
        });

        erroMaxTxt.setText("1");

        botTreinar.setText("Treinar");
        botTreinar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botTreinarActionPerformed(evt);
            }
        });

        jButton1.setText("Carregar CSV de treinamento");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Atualizar Quantidade");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Atualizar valores da tabela na rede");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelOpcoesLayout = new javax.swing.GroupLayout(panelOpcoes);
        panelOpcoes.setLayout(panelOpcoesLayout);
        panelOpcoesLayout.setHorizontalGroup(
            panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcoesLayout.createSequentialGroup()
                .addGroup(panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane1)
                    .addGroup(panelOpcoesLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addGroup(panelOpcoesLayout.createSequentialGroup()
                                .addGroup(panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelOpcoesLayout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textTaxaAprendizado, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelOpcoesLayout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addGroup(panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(erroMaximoRedeRadio)
                                            .addComponent(iteracoesRadio))))
                                .addGap(80, 80, 80)
                                .addGroup(panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelOpcoesLayout.createSequentialGroup()
                                        .addGroup(panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(numeroIteracoesTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                                            .addComponent(erroMaxTxt)))
                                    .addGroup(panelOpcoesLayout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(botTreinar)
                                            .addGroup(panelOpcoesLayout.createSequentialGroup()
                                                .addComponent(qtdNeuroniosCOTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton2))))))
                            .addGroup(panelOpcoesLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comboOpParada, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 103, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOpcoesLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelOpcoesLayout.setVerticalGroup(
            panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcoesLayout.createSequentialGroup()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textTaxaAprendizado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(qtdNeuroniosCOTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(14, 14, 14)
                .addGroup(panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(comboOpParada, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(iteracoesRadio)
                    .addComponent(jLabel5)
                    .addComponent(numeroIteracoesTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelOpcoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(erroMaximoRedeRadio)
                    .addComponent(jLabel6)
                    .addComponent(erroMaxTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(botTreinar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3))
        );

        carregarArquivoTeste.setText("Carregar teste.csv");
        carregarArquivoTeste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carregarArquivoTesteActionPerformed(evt);
            }
        });

        realizarTesteBut.setText("Testar");
        realizarTesteBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                realizarTesteButActionPerformed(evt);
            }
        });

        mconfTxt.setColumns(20);
        mconfTxt.setRows(5);
        jScrollPane2.setViewportView(mconfTxt);

        jLabel7.setText("Matriz de Confusão:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addComponent(panelOpcoes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(carregarArquivoTeste)
                                .addGap(18, 18, 18)
                                .addComponent(realizarTesteBut))
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(carregarArquivoTeste)
                    .addComponent(realizarTesteBut))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textTaxaAprendizadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textTaxaAprendizadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textTaxaAprendizadoActionPerformed

    private void numeroIteracoesTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numeroIteracoesTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numeroIteracoesTxtActionPerformed

    private void comboOpParadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboOpParadaActionPerformed
         int opcao = comboOpParada.getSelectedIndex();
        
        if(opcao == 1){
            this.redeNeural.setFuncaoTransferencia(redeNeural.FUNCAO_LOGISTICA);
        }else{
            if(opcao == 2){
                this.redeNeural.setFuncaoTransferencia(redeNeural.FUNCAO_TANGENTE_HIPERBOLICA);
            }
            else{
                //NAO SEI O QUE ESCREVO XD
            }
        }
    }//GEN-LAST:event_comboOpParadaActionPerformed

    private void iteracoesRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iteracoesRadioActionPerformed
        this.iteracoesRadio.setSelected(true);
        this.erroMaximoRedeRadio.setSelected(false);
        this.numeroIteracoesTxt.setEnabled(true);
        this.erroMaxTxt.setEnabled(false);
        
    }//GEN-LAST:event_iteracoesRadioActionPerformed

    private void erroMaximoRedeRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_erroMaximoRedeRadioActionPerformed
        this.iteracoesRadio.setSelected(false);
        this.erroMaximoRedeRadio.setSelected(true);
        this.numeroIteracoesTxt.setEnabled(false);
        this.erroMaxTxt.setEnabled(true);
    }//GEN-LAST:event_erroMaximoRedeRadioActionPerformed

    private void botTreinarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botTreinarActionPerformed
        //TODO PARTE QUE PEGA OS VALORES DE PESO DAS TABELAS
        
        if(comboOpParada.getSelectedIndex() == 0 )
            JOptionPane.showMessageDialog(null, "É necessário selecionar um tipo de parada", "Erro", JOptionPane.ERROR_MESSAGE);
        else{
            //SETANDO TAXA DE APRENDIZADO
            this.redeNeural.setTaxaAprendizado(Float.parseFloat(this.textTaxaAprendizado.getText()));
            //PARTE PARA COLOCAR NUMERO DE ITERAÇÕES OU ERRO MAXIMO
           if(this.iteracoesRadio.isSelected()){
               this.redeNeural.setTipoParada(redeNeural.PARADA_POR_ITERACAO);
               this.redeNeural.treinar(Integer.parseInt(this.numeroIteracoesTxt.getText()));
           }else{
               this.redeNeural.setTipoParada(redeNeural.PARADA_POR_ERRO);
               this.redeNeural.treinar(Float.parseFloat(this.erroMaxTxt.getText()));
           }

           JOptionPane.showMessageDialog(null,"Treinamento Realizado");
           definirTabela();
        }
    }//GEN-LAST:event_botTreinarActionPerformed

    private void carregarArquivoTesteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carregarArquivoTesteActionPerformed
                JFileChooser JFC = new JFileChooser();  
        FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("CSV", "csv");
	JFC.setFileFilter(extensionFilter);
	JFC.setFileSelectionMode(JFileChooser.FILES_ONLY);
        JFC.setMultiSelectionEnabled(false);
	if(JFC.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            String url = JFC.getSelectedFile().getAbsolutePath();
            try {
                redeNeural.carregaArquivoTeste(url);
                realizarTesteBut.setEnabled(true);
                
            } catch (IOException ex) {
                Logger.getLogger(UIPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }//GEN-LAST:event_carregarArquivoTesteActionPerformed

    private void realizarTesteButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_realizarTesteButActionPerformed
        this.redeNeural.testar();
        JOptionPane.showMessageDialog(null,"Teste Realizado com sucesso");
        this.mconfTxt.setText("");
        this.mconfTxt.setText(redeNeural.imprimirMatrizDeConfusao());
    }//GEN-LAST:event_realizarTesteButActionPerformed

    private void qtdNeuroniosCOTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qtdNeuroniosCOTxtActionPerformed
        if((qtdNeuroniosCOTxt.getText()!= null)&&(qtdNeuroniosCOTxt.getText() != "")){
            
        }
            this.redeNeural.setQuantidadeElementosCamadaOculta(Integer.parseInt(this.qtdNeuroniosCOTxt.getText()));
    }//GEN-LAST:event_qtdNeuroniosCOTxtActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JFileChooser JFC = new JFileChooser();  
        FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("CSV", "csv");
	JFC.setFileFilter(extensionFilter);
	JFC.setFileSelectionMode(JFileChooser.FILES_ONLY);
        JFC.setMultiSelectionEnabled(false);
	if(JFC.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            String url = JFC.getSelectedFile().getAbsolutePath();
            try {
                iniciarRedeNeural(url);
                liberarComponentes();
                qtdNeuroniosCOTxt.setText(String.valueOf(redeNeural.getQuantidadeElementosCamadaOculta()));
                definirTabela();
                System.out.println(redeNeural.imprimirRede());
            } catch (IOException ex) {
                Logger.getLogger(UIPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
            
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try{
            redeNeural.setQuantidadeElementosCamadaOculta(Integer.parseInt(qtdNeuroniosCOTxt.getText()));
            redeNeural.atualizarCamadaOculta();
            redeNeural.atualizarConexaoNeuronios();
            definirTabela();
                        System.out.println(redeNeural.imprimirRede());
            
            
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Quantidade inválida", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        NeuronioOculto neuronio;
        float novoPeso;
        for(int i = 0; i < jTable1.getRowCount(); i++)
            for(int j = 0; j < jTable1.getColumnCount(); j++){
                neuronio = redeNeural.getListaNeuronioOculto().get(i);
                novoPeso = Float.parseFloat((String.valueOf(jTable1.getValueAt(i, j))));
                neuronio.setPeso(j, novoPeso);
            }
    }//GEN-LAST:event_jButton3ActionPerformed

    public void liberarComponentes(){
        qtdNeuroniosCOTxt.setEnabled(true);
        botTreinar.setEnabled(true);
        jButton2.setEnabled(true);
    }
    
    public void definirTabela(){
        DefaultTableModel DTM = new DefaultTableModel();
        
        for(NeuronioEntrada neuronio:redeNeural.getListaNeuronioEntrada())
            DTM.addColumn("Peso x"+neuronio.numeroIdentificador);
        
        Float[] pesos = new Float[redeNeural.getQuantidadeElementosCamadaEntrada()];
        int i;
        for(NeuronioOculto neuronio:redeNeural.getListaNeuronioOculto()){
            i = 0;
            while(i < redeNeural.getQuantidadeElementosCamadaEntrada())
                pesos[i] = neuronio.getPeso(i++);
            DTM.addRow(pesos);
        }
        
        jTable1.setModel(DTM);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UIPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UIPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UIPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UIPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new UIPrincipal().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(UIPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botTreinar;
    private javax.swing.JButton carregarArquivoTeste;
    private javax.swing.JComboBox<String> comboOpParada;
    private javax.swing.ButtonGroup condicaoDeParadaGroup;
    private javax.swing.JTextField erroMaxTxt;
    private javax.swing.JRadioButton erroMaximoRedeRadio;
    private javax.swing.JRadioButton iteracoesRadio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea mconfTxt;
    private javax.swing.JTextField numeroIteracoesTxt;
    private javax.swing.JPanel panelOpcoes;
    private javax.swing.JTextField qtdNeuroniosCOTxt;
    private javax.swing.JButton realizarTesteBut;
    private javax.swing.JTextField textTaxaAprendizado;
    // End of variables declaration//GEN-END:variables
}

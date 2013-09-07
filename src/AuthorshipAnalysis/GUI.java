package AuthorshipAnalysis;


import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author david122288
 */
public class GUI extends javax.swing.JFrame {

    private MetricsTableModel mtm = new MetricsTableModel();
    /**
     * Creates new form GUI
     */
    public GUI() {
                mtm.setData();
        
        initComponents();
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                // do some actions here, for example
                // print first column value from selected row
                System.out.println(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
                buildSecondaryTable(jTable1.getValueAt(jTable1.getSelectedRow(), 1));
            }
        });
        jTable1.setModel(mtm);
        knownAuthorPane.setVisible(false);
        unknownAuthorPane.setVisible(true);
        newAuthorTextField.setEnabled(false);
    }
    
    private void buildSecondaryTable(Object value) {
        if (value instanceof Double) {
            if(jScrollPane2.isVisible()) {
                jScrollPane2.setVisible(false);
                this.setSize(this.getSize().width - 200, this.getSize().height);
            }
        } else {
            if(!jScrollPane2.isVisible()) {
                jScrollPane2.setVisible(true);
                this.setSize(this.getSize().width + 200, this.getSize().height);
            }
            Map<Integer, Double> al = new HashMap<Integer, Double>();
            al.put(1, 1.0);
            al.put(2, 2.0);
            al.put(3, 3.0);
            al.put(4, 4.0);
            MetricsTableModel mtm2 = new MetricsTableModel();
            mtm2.setData(al);
            jTable2.setModel(mtm2);
        }
    }

    class MetricsTableModel extends AbstractTableModel {
        Object[] column;
        Object[][] data;
        
        public void setData() {
            Map<Integer, Double> al = new HashMap<Integer, Double>();
            al.put(1, 1.0);
            al.put(2, 2.0);
            al.put(3, 3.0);
            al.put(4, 4.0);
//            MetricsTableModel al = new MetricsTableModel();


            Object[][] newData = {
                {"hello", (double)1},
                {"goodbye", al}
                };
            data = newData;
        }
        
        public void setData(Map<?, ?> subMetricMap) {
            Object[] columnName = {"Metric","Value"};
            setData(subMetricMap,columnName);
        }
        
        public void setData(Object[][] newData) {
            Object[] columnName= {"Metric","Value"};
            setData(newData, columnName);
        }
        
        public void setData(Book newBook) {
            Object[] columnName = {"Metric","Value"};
            setData(newBook, columnName);
        }
        
        public void setData(Map<?, ?> subMetricMap, Object[] columnName){
            Object[][] newData = new Object[subMetricMap.size()][2];
            int iterator = 0;
            for (Map.Entry<?, ?> entry : subMetricMap.entrySet())
            {
                newData[iterator][0] = entry.getKey();
                newData[iterator][1] = entry.getValue();
                iterator++;
            }
            data = newData;
            column = columnName;
        }
        
        public void setData(Object[][] newData, Object[] columnName){
            data = newData;
            column = columnName;
        }
        
        public void setData(Book newBook, Object[] columnName){
            Object[][] newData = {
                {"Average Word Length", newBook.averageWordLength()},
                {"Average Sentence Length", newBook.averageSentenceLength()},
                {"Ratio Word To Sentence Length", newBook.ratioWordToSentenceLength()},
                {"Relative Letter Frequency", newBook.relativeLetterFrequency()},
                {"Relative Letter-Pair Frequency", newBook.relativeLetterPairFrequencies()},
                {"Vocabulary Richness", newBook.vocabularyRichness()},
                {"Distribution of Word Lengths", newBook.distributionOfWordLengths()},
                {"Frequency of Noun Usage", newBook.frequencyOfNounUsage()},
                {"Frequency of Verb Usage", newBook.frequencyOfVerbUsage()},
                {"Ratio of Adjective to Noun Usage", newBook.ratioAdjectivesToNounUsage()},
                {"SimpleLexicalDensity", newBook.simpleLexicalDensity()}
            };
            
            data = newData;
            column = columnName;
        }
        
        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public int getColumnCount() {
            return 2;
        }
        @Override
        public String getColumnName(int column){
            if(this.column==null){
                return super.getColumnName(column);
            }else{
            return this.column[column].toString();
            }
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if((data[rowIndex][columnIndex] instanceof Map)) {
                return "+";
            }
            return data[rowIndex][columnIndex];
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        resultsPopup = new javax.swing.JFrame();
        popupScrollPane = new javax.swing.JScrollPane();
        popupTable = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        authorKnown = new javax.swing.JCheckBox();
        fileTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        unknownAuthorPane = new javax.swing.JPanel();
        computeMetrix = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        newAuthorTextField = new javax.swing.JTextField();
        newBookTextField = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        authorComboBox1 = new javax.swing.JComboBox();
        knownAuthorPane = new javax.swing.JPanel();
        authorComboBox2 = new javax.swing.JComboBox();
        bookComboBox2 = new javax.swing.JComboBox();
        searchMetrics2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        authorComboBox = new javax.swing.JComboBox();
        searchMetrics = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        resultsPopup.setName("popupResults");

        popupTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        popupScrollPane.setViewportView(popupTable);

        org.jdesktop.layout.GroupLayout resultsPopupLayout = new org.jdesktop.layout.GroupLayout(resultsPopup.getContentPane());
        resultsPopup.getContentPane().setLayout(resultsPopupLayout);
        resultsPopupLayout.setHorizontalGroup(
            resultsPopupLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(resultsPopupLayout.createSequentialGroup()
                .addContainerGap()
                .add(popupScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                .addContainerGap())
        );
        resultsPopupLayout.setVerticalGroup(
            resultsPopupLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, resultsPopupLayout.createSequentialGroup()
                .addContainerGap()
                .add(popupScrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        authorKnown.setText("Author Known");
        authorKnown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                authorKnownActionPerformed(evt);
            }
        });

        fileTextField.setText("Enter file location");

        jLabel1.setText("Book Location");

        jButton1.setText("Browse");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        computeMetrix.setText("Compute Metrics");

        jButton3.setText("View Match Rankings");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        newAuthorTextField.setText("FirstName LastName");

        newBookTextField.setText("Book Title");

        saveButton.setText("Save to Author");

        authorComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Author", "---------", "New Author" }));
        authorComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                authorComboBox1ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout unknownAuthorPaneLayout = new org.jdesktop.layout.GroupLayout(unknownAuthorPane);
        unknownAuthorPane.setLayout(unknownAuthorPaneLayout);
        unknownAuthorPaneLayout.setHorizontalGroup(
            unknownAuthorPaneLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(unknownAuthorPaneLayout.createSequentialGroup()
                .addContainerGap()
                .add(unknownAuthorPaneLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(newBookTextField)
                    .add(unknownAuthorPaneLayout.createSequentialGroup()
                        .add(unknownAuthorPaneLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(authorComboBox1, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(newAuthorTextField))
                        .addContainerGap())
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, unknownAuthorPaneLayout.createSequentialGroup()
                        .add(0, 85, Short.MAX_VALUE)
                        .add(unknownAuthorPaneLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, unknownAuthorPaneLayout.createSequentialGroup()
                                .add(saveButton)
                                .add(103, 103, 103))
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, unknownAuthorPaneLayout.createSequentialGroup()
                                .add(jButton3)
                                .add(82, 82, 82))
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, unknownAuthorPaneLayout.createSequentialGroup()
                                .add(computeMetrix)
                                .add(98, 98, 98))))))
        );
        unknownAuthorPaneLayout.setVerticalGroup(
            unknownAuthorPaneLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(unknownAuthorPaneLayout.createSequentialGroup()
                .add(17, 17, 17)
                .add(computeMetrix)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 7, Short.MAX_VALUE)
                .add(jButton3)
                .add(18, 18, 18)
                .add(authorComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(newAuthorTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(newBookTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(saveButton)
                .addContainerGap())
        );

        unknownAuthorPane.setBounds(0, 0, 350, 260);
        jLayeredPane1.add(unknownAuthorPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        authorComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Author", "----------" }));

        bookComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Books", "----------" }));

        searchMetrics2.setText("Compute Metrics");

        org.jdesktop.layout.GroupLayout knownAuthorPaneLayout = new org.jdesktop.layout.GroupLayout(knownAuthorPane);
        knownAuthorPane.setLayout(knownAuthorPaneLayout);
        knownAuthorPaneLayout.setHorizontalGroup(
            knownAuthorPaneLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(knownAuthorPaneLayout.createSequentialGroup()
                .add(knownAuthorPaneLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(knownAuthorPaneLayout.createSequentialGroup()
                        .add(42, 42, 42)
                        .add(knownAuthorPaneLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(authorComboBox2, 0, 261, Short.MAX_VALUE)
                            .add(bookComboBox2, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .add(knownAuthorPaneLayout.createSequentialGroup()
                        .add(95, 95, 95)
                        .add(searchMetrics2)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        knownAuthorPaneLayout.setVerticalGroup(
            knownAuthorPaneLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(knownAuthorPaneLayout.createSequentialGroup()
                .add(34, 34, 34)
                .add(authorComboBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(50, 50, 50)
                .add(bookComboBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(42, 42, 42)
                .add(searchMetrics2)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        knownAuthorPane.setBounds(0, 0, 350, 270);
        jLayeredPane1.add(knownAuthorPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLayeredPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                        .addContainerGap())
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(authorKnown)
                                .add(jPanel1Layout.createSequentialGroup()
                                    .add(12, 12, 12)
                                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(fileTextField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                                        .add(jLabel1))))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(210, 210, 210)
                                .add(jButton1)
                                .add(18, 18, 18)))
                        .add(16, 16, 16))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(22, 22, 22)
                .add(authorKnown)
                .add(18, 18, 18)
                .add(jLabel1)
                .add(3, 3, 3)
                .add(fileTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jButton1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 50, Short.MAX_VALUE)
                .add(jLayeredPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 275, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Upload", jPanel1);

        authorComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Author", "---------------" }));

        searchMetrics.setText("Search Metrics");
        searchMetrics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchMetricsActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(28, 28, 28)
                        .add(authorComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 277, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(101, 101, 101)
                        .add(searchMetrics)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(44, 44, 44)
                .add(authorComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(127, 127, 127)
                .add(searchMetrics)
                .addContainerGap(255, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("View Data", jPanel2);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Metric", "Quantity"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sub-metric", "Value"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 381, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 283, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 261, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(19, 19, 19)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                            .add(jScrollPane2))
                        .add(0, 0, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jTabbedPane1)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void authorKnownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_authorKnownActionPerformed
        if(authorKnown.isSelected()){
            knownAuthorPane.setVisible(true);
            unknownAuthorPane.setVisible(false);
        }else{
            knownAuthorPane.setVisible(false);
            unknownAuthorPane.setVisible(true);
        }
    }//GEN-LAST:event_authorKnownActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int returnVal = jFileChooser1.showOpenDialog(jLabel1);
        if(returnVal == JFileChooser.APPROVE_OPTION){
            fileTextField.removeAll();
            File fileName = jFileChooser1.getSelectedFile();
            fileTextField.setText(fileName.getPath());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        //Reset tabs to default config
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void searchMetricsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchMetricsActionPerformed
        //stuff
    }//GEN-LAST:event_searchMetricsActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //Test For Popup
        Object[][] doubData = {{0.0, 1.0},{2.0,2.5},{9.0,5.0}};
        MetricsTableModel test = new MetricsTableModel();
        test.setData(doubData);
        popupTable.setModel(test);
        resultsPopup.pack();
        resultsPopup.setLocationRelativeTo(this);
        resultsPopup.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void authorComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_authorComboBox1ActionPerformed
        //Had to use setEnabled because Visible was causing a need for a extra click
        if(authorComboBox1.getSelectedItem().equals("New Author")){
            newAuthorTextField.setEnabled(true);
        }else{
            newAuthorTextField.setEnabled(false);
        }
    }//GEN-LAST:event_authorComboBox1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox authorComboBox;
    private javax.swing.JComboBox authorComboBox1;
    private javax.swing.JComboBox authorComboBox2;
    private javax.swing.JCheckBox authorKnown;
    private javax.swing.JComboBox bookComboBox2;
    private javax.swing.JButton computeMetrix;
    private javax.swing.JTextField fileTextField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JPanel knownAuthorPane;
    private javax.swing.JTextField newAuthorTextField;
    private javax.swing.JTextField newBookTextField;
    private javax.swing.JScrollPane popupScrollPane;
    private javax.swing.JTable popupTable;
    private javax.swing.JFrame resultsPopup;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton searchMetrics;
    private javax.swing.JButton searchMetrics2;
    private javax.swing.JPanel unknownAuthorPane;
    // End of variables declaration//GEN-END:variables
}


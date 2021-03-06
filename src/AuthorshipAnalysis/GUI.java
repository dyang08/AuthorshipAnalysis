package AuthorshipAnalysis;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author david122288
 */
public class GUI extends javax.swing.JFrame {

    private final MetricsTableModel mtm = new MetricsTableModel();
    private Book currentBook;
    private final SqlConnection database;

    /**
     * Creates new form GUI
     */
    public GUI() {
        database = new SqlConnection();
        initComponents();
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent event) {
                // do some actions here, for example
                // print first column value from selected row
                // If statement prevents error when creating new table
                if (jTable1.getSelectedRowCount() != 0) {
                    //System.out.println(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
                    Object value = ((MetricsTableModel) jTable1.getModel()).data[jTable1.getSelectedRow()][1];
                    buildSecondaryTable(value);
                }
            }
        });
        MetricsTableModel defaultModel = new MetricsTableModel();
        defaultModel.setData();
        jTable1.setModel(defaultModel);
        jScrollPane2.setVisible(false);
        this.pack(); // used pack for tighter screen
        //this.setSize(this.getSize().width - 200, this.getSize().height);
        knownAuthorPane.setVisible(false);
        unknownAuthorPane.setVisible(true);
        newAuthorFirstNameTextField.setEnabled(false);
        setup();
    }

    private void buildSecondaryTable(Object value) {
        if (value instanceof Double) {
            jScrollPane2.setVisible(false);
            this.pack();
            //this.setSize(this.getSize().width - 200, this.getSize().height);

        } else if (value instanceof Map) {
            jScrollPane2.setVisible(true);
            this.pack();
            //this.setSize(this.getSize().width + 200, this.getSize().height);
            MetricsTableModel mtm2 = new MetricsTableModel();
            mtm2.setData((Map) value);
            jTable2.setModel(mtm2);

        }
    }

    class MetricsTableModel extends AbstractTableModel {

        Object[] column;
        Object[][] data;

        public void setData() {
            Object[][] newData = {};
            Object[] columnName = {"Metric", "Value"};
            column = columnName;
            data = newData;
        }

        public void setData(Map<?, ?> subMetricMap) {
            Object[] columnName = {"Metric", "Value"};
            setData(subMetricMap, columnName);
        }

        public void setData(Object[][] newData) {
            Object[] columnName = {"Metric", "Value"};
            setData(newData, columnName);
        }

        public void setData(Book newBook) {
            Object[] columnName = {"Metric", "Value"};
            setData(newBook, columnName);
        }

        public void setData(Map<?, ?> subMetricMap, Object[] columnName) {
            Object[][] newData = new Object[subMetricMap.size()][2];
            int iterator = 0;
            for (Map.Entry<?, ?> entry : subMetricMap.entrySet()) {
                newData[iterator][0] = entry.getKey();
                newData[iterator][1] = entry.getValue();
                iterator++;
            }
            data = newData;
            column = columnName;
        }

        public void setData(ArrayList<Results> resultsList) {
            Object[][] newData = new Object[resultsList.size()][3];
            Object[] columnName = {"Rank", "Author", "Proximity"};
            int i = 0;
            for (Results result : resultsList) {
                newData[i][0] = resultsList.indexOf(result) + 1;
                newData[i][1] = result.getBookAuthor();
                newData[i][2] = result.getScore();
                i++;
            }
            data = newData;
            column = columnName;
        }

        public void setData(Object[][] newData, Object[] columnName) {
            data = newData;
            column = columnName;
        }

        public void setData(Book newBook, Object[] columnName) {
            Object[][] newData = {
                {"Average Word Length", newBook.averageWordLength()},
                {"Average Sentence Length", newBook.averageSentenceLength()},
                {"Ratio Word To Sentence Length", newBook.ratioWordToSentenceLength()},
                {"Relative Letter Frequency", fixRelativeLetterFrequancy(newBook.relativeLetterFrequency())},
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

        public void setData(AuthorMetrics metrics) {
            Object[] columnName = {"Metric", "Value"};
            setData(metrics, columnName);
        }

        public void setData(AuthorMetrics metrics, Object[] columnName) {
            Object[][] newData = {
                {"Average Word Length", metrics.getAvgWordLength()},
                {"Average Sentence Length", metrics.getAvgSentenceLength()},
                {"Ratio Word To Sentence Length", metrics.getWordToSentenceRatio()},
                {"Relative Letter Frequency", fixRelativeLetterFrequancy(metrics.getRelativeLetterFreq())},
                {"Relative Letter-Pair Frequency", metrics.getRelativeLetterPairFrequencies()},
                {"Vocabulary Richness", metrics.getVocabRichness()},
                {"Distribution of Word Lengths", metrics.getDistributionOfWordLengths()},
                {"Frequency of Noun Usage", metrics.getFreqNoun()},
                {"Frequency of Verb Usage", metrics.getFreqVerb()},
                {"Ratio of Adjective to Noun Usage", metrics.getAdjectiveToNounRatio()},
                {"SimpleLexicalDensity", metrics.getLexicalDensity()}
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
            if (data.length == 0) {
                return 2;
            } else {
                return data[0].length;
            }
        }

        @Override
        public String getColumnName(int column) {
            if (this.column == null) {
                return super.getColumnName(column);
            } else {
                return this.column[column].toString();
            }
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            //causing issues with openning second table
            if ((data[rowIndex][columnIndex] instanceof Map
                    || data[rowIndex][columnIndex] instanceof double[])) {
                return "+";
            }
            return data[rowIndex][columnIndex];
        }
        //Used to fix Return value of Relative Letter Frequancy

        public Map fixRelativeLetterFrequancy(Double[] data) {
            if (data.length == 26) {
                HashMap<String, Double> newData = new HashMap<String, Double>();
                newData.put("A", data[0]);
                newData.put("B", data[1]);
                newData.put("C", data[2]);
                newData.put("D", data[3]);
                newData.put("E", data[4]);
                newData.put("F", data[5]);
                newData.put("G", data[6]);
                newData.put("H", data[7]);
                newData.put("I", data[8]);
                newData.put("J", data[9]);
                newData.put("K", data[10]);
                newData.put("L", data[11]);
                newData.put("M", data[12]);
                newData.put("N", data[13]);
                newData.put("O", data[14]);
                newData.put("P", data[15]);
                newData.put("Q", data[16]);
                newData.put("R", data[17]);
                newData.put("S", data[18]);
                newData.put("T", data[19]);
                newData.put("U", data[20]);
                newData.put("V", data[21]);
                newData.put("W", data[22]);
                newData.put("X", data[23]);
                newData.put("Y", data[24]);
                newData.put("Z", data[25]);
                return newData;
            }
            return null;
        }
        //Used to fix Return value of Relative Letter Frequancy

        public Map fixRelativeLetterFrequancy(double[] data) {
            if (data.length == 26) {
                HashMap<String, Double> newData = new HashMap<String, Double>();
                newData.put("A", data[0]);
                newData.put("B", data[1]);
                newData.put("C", data[2]);
                newData.put("D", data[3]);
                newData.put("E", data[4]);
                newData.put("F", data[5]);
                newData.put("G", data[6]);
                newData.put("H", data[7]);
                newData.put("I", data[8]);
                newData.put("J", data[9]);
                newData.put("K", data[10]);
                newData.put("L", data[11]);
                newData.put("M", data[12]);
                newData.put("N", data[13]);
                newData.put("O", data[14]);
                newData.put("P", data[15]);
                newData.put("Q", data[16]);
                newData.put("R", data[17]);
                newData.put("S", data[18]);
                newData.put("T", data[19]);
                newData.put("U", data[20]);
                newData.put("V", data[21]);
                newData.put("W", data[22]);
                newData.put("X", data[23]);
                newData.put("Y", data[24]);
                newData.put("Z", data[25]);
                return newData;
            }
            return null;
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
        loadingFrame = new javax.swing.JFrame();
        jPanel3 = new javax.swing.JPanel();
        displayMessageLabel = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        authorKnownChkBox = new javax.swing.JCheckBox();
        fileAddressTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        browseButton = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        unknownAuthorPane = new javax.swing.JPanel();
        viewMatchRankingsButton = new javax.swing.JButton();
        newBookTitleTextField = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        authorComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        knownAuthorPane = new javax.swing.JPanel();
        authorComboBox2 = new javax.swing.JComboBox();
        saveBookMetrics = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        newAuthorFirstNameTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        newAuthorLastNameTextField = new javax.swing.JTextField();
        newBookTitleTextField2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        computeMetricsButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        authorViewDataComboBox = new javax.swing.JComboBox();
        searchMetricsButton = new javax.swing.JButton();
        label1 = new java.awt.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        resultsPopup.setName("popupResults"); // NOI18N

        popupTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
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

        loadingFrame.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        displayMessageLabel.setText("Loading please wait");
        displayMessageLabel.setToolTipText("");

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(103, 103, 103)
                .add(displayMessageLabel)
                .addContainerGap(112, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(108, 108, 108)
                .add(displayMessageLabel)
                .addContainerGap(125, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout loadingFrameLayout = new org.jdesktop.layout.GroupLayout(loadingFrame.getContentPane());
        loadingFrame.getContentPane().setLayout(loadingFrameLayout);
        loadingFrameLayout.setHorizontalGroup(
            loadingFrameLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(loadingFrameLayout.createSequentialGroup()
                .add(27, 27, 27)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        loadingFrameLayout.setVerticalGroup(
            loadingFrameLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(loadingFrameLayout.createSequentialGroup()
                .add(22, 22, 22)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        authorKnownChkBox.setText("Author Known");
        authorKnownChkBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                authorKnownChkBoxActionPerformed(evt);
            }
        });

        fileAddressTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileAddressTextFieldActionPerformed(evt);
            }
        });

        jLabel1.setText("Book Location");

        browseButton.setText("Browse");
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        unknownAuthorPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Match Ranking and Upload"));

        viewMatchRankingsButton.setText("View Match Rankings");
        viewMatchRankingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewMatchRankingsButtonActionPerformed(evt);
            }
        });

        saveButton.setText("Save Book");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        authorComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "New Author", "Author1", "Author2" }));
        authorComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                authorComboBox1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Author");

        jLabel5.setText("Book Title");

        org.jdesktop.layout.GroupLayout unknownAuthorPaneLayout = new org.jdesktop.layout.GroupLayout(unknownAuthorPane);
        unknownAuthorPane.setLayout(unknownAuthorPaneLayout);
        unknownAuthorPaneLayout.setHorizontalGroup(
            unknownAuthorPaneLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(unknownAuthorPaneLayout.createSequentialGroup()
                .add(unknownAuthorPaneLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(unknownAuthorPaneLayout.createSequentialGroup()
                        .add(109, 109, 109)
                        .add(saveButton))
                    .add(unknownAuthorPaneLayout.createSequentialGroup()
                        .add(72, 72, 72)
                        .add(viewMatchRankingsButton))
                    .add(unknownAuthorPaneLayout.createSequentialGroup()
                        .add(34, 34, 34)
                        .add(unknownAuthorPaneLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(newBookTitleTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 262, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(authorComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 262, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel5)
                            .add(jLabel2))))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        unknownAuthorPaneLayout.setVerticalGroup(
            unknownAuthorPaneLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(unknownAuthorPaneLayout.createSequentialGroup()
                .add(18, 18, 18)
                .add(viewMatchRankingsButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jLabel2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(authorComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jLabel5)
                .add(12, 12, 12)
                .add(newBookTitleTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(saveButton)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jLayeredPane1.add(unknownAuthorPane);
        unknownAuthorPane.setBounds(0, 0, 350, 320);

        knownAuthorPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Upload"));

        authorComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        authorComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                authorComboBox2ActionPerformed(evt);
            }
        });

        saveBookMetrics.setText("Save Book");
        saveBookMetrics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBookMetricsActionPerformed(evt);
            }
        });

        jLabel3.setText("First Name");

        newAuthorFirstNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newAuthorFirstNameTextFieldActionPerformed(evt);
            }
        });

        jLabel4.setText("Last Name");

        jLabel6.setText("Book Title");

        jLabel7.setText("Author");

        org.jdesktop.layout.GroupLayout knownAuthorPaneLayout = new org.jdesktop.layout.GroupLayout(knownAuthorPane);
        knownAuthorPane.setLayout(knownAuthorPaneLayout);
        knownAuthorPaneLayout.setHorizontalGroup(
            knownAuthorPaneLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(knownAuthorPaneLayout.createSequentialGroup()
                .add(43, 43, 43)
                .add(knownAuthorPaneLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(knownAuthorPaneLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                        .add(newAuthorFirstNameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 261, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(newAuthorLastNameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 261, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(authorComboBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 261, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(newBookTitleTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 261, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(saveBookMetrics))
                    .add(knownAuthorPaneLayout.createSequentialGroup()
                        .add(6, 6, 6)
                        .add(knownAuthorPaneLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel6)
                            .add(jLabel4)))
                    .add(knownAuthorPaneLayout.createSequentialGroup()
                        .add(8, 8, 8)
                        .add(jLabel7))
                    .add(knownAuthorPaneLayout.createSequentialGroup()
                        .add(6, 6, 6)
                        .add(jLabel3)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        knownAuthorPaneLayout.setVerticalGroup(
            knownAuthorPaneLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, knownAuthorPaneLayout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel7)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(authorComboBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(10, 10, 10)
                .add(jLabel3)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(newAuthorFirstNameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel4)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 16, Short.MAX_VALUE)
                .add(newAuthorLastNameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jLabel6)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(newBookTitleTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(saveBookMetrics)
                .addContainerGap())
        );

        jLayeredPane1.add(knownAuthorPane);
        knownAuthorPane.setBounds(0, 0, 350, 320);

        computeMetricsButton.setText("Compute Metrics");
        computeMetricsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                computeMetricsButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLayeredPane1)
                .addContainerGap())
            .add(jPanel1Layout.createSequentialGroup()
                .add(28, 28, 28)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(fileAddressTextField)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(browseButton))
                    .add(jLabel1)
                    .add(authorKnownChkBox))
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(91, Short.MAX_VALUE)
                .add(computeMetricsButton)
                .add(118, 118, 118))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(7, 7, 7)
                .add(authorKnownChkBox)
                .add(18, 18, 18)
                .add(jLabel1)
                .add(2, 2, 2)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(fileAddressTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(browseButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(computeMetricsButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jLayeredPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 339, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Upload", jPanel1);

        authorViewDataComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Author", "---------------" }));
        authorViewDataComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                authorViewDataComboBoxActionPerformed(evt);
            }
        });

        searchMetricsButton.setText("Search Metrics");
        searchMetricsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchMetricsButtonActionPerformed(evt);
            }
        });

        label1.setText("Author");

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(22, 22, 22)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(authorViewDataComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 277, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(label1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(132, 132, 132)
                        .add(searchMetricsButton)))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(43, 43, 43)
                .add(label1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(authorViewDataComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(98, 98, 98)
                .add(searchMetricsButton)
                .addContainerGap(240, Short.MAX_VALUE))
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
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 261, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(19, 19, 19)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                            .add(jScrollPane2)))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fileAddressTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
        int returnVal = jFileChooser1.showOpenDialog(jLabel1);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            fileAddressTextField.removeAll();
            File fileName = jFileChooser1.getSelectedFile();
            fileAddressTextField.setText(fileName.getPath());
        }
    }

    private void authorKnownChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_authorKnownChkBoxActionPerformed
        if (authorKnownChkBox.isSelected()) {
            knownAuthorPane.setVisible(true);
            unknownAuthorPane.setVisible(false);
        } else {
            knownAuthorPane.setVisible(false);
            unknownAuthorPane.setVisible(true);
        }
    }//GEN-LAST:event_authorKnownChkBoxActionPerformed

    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
        int returnVal = jFileChooser1.showOpenDialog(jLabel1);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            fileAddressTextField.removeAll();
            File fileName = jFileChooser1.getSelectedFile();
            fileAddressTextField.setText(fileName.getPath());
        }
    }//GEN-LAST:event_browseButtonActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        //Reset tabs to default config
        setup();
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void searchMetricsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchMetricsButtonActionPerformed
        AuthorMetrics authorMetrics = database.getAuthorMetrics(authorViewDataComboBox.getSelectedItem().toString());
        MetricsTableModel model = new MetricsTableModel();
        model.setData(authorMetrics);
        jTable1.setModel(model);
    }//GEN-LAST:event_searchMetricsButtonActionPerformed

    private void viewMatchRankingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewMatchRankingsButtonActionPerformed
        //Test For Popup
//        Object[][] doubData = {{0.0, 1.0}, {2.0, 2.5}, {9.0, 5.0}};
//        MetricsTableModel test = new MetricsTableModel();
//        test.setData(doubData);
//        popupTable.setModel(test);
//        resultsPopup.pack();
//        resultsPopup.setLocationRelativeTo(this);
//        resultsPopup.setVisible(true);
        if (currentBook == null) {
            JOptionPane.showMessageDialog(this,
                    "An error occured while retrieving results. \n",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Comparitor bookComparison = new Comparitor(currentBook);
        bookComparison.compareMetrics();
        ArrayList<Results> results = bookComparison.getResults();

        MetricsTableModel resultsModel = new MetricsTableModel();
        resultsModel.setData(results);

        popupTable.setModel(resultsModel);
        TableColumnModel tcm = popupTable.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(1);      //id
        
        tcm.getColumn(2).setPreferredWidth(2);      //proximity
        resultsPopup.pack();
        resultsPopup.setLocationRelativeTo(this);
        resultsPopup.setVisible(true);
    }//GEN-LAST:event_viewMatchRankingsButtonActionPerformed

    private void authorComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_authorComboBox1ActionPerformed
        //Not used
    }//GEN-LAST:event_authorComboBox1ActionPerformed

    private void newAuthorFirstNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAuthorFirstNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newAuthorFirstNameTextFieldActionPerformed

    private void computeMetricsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_computeMetricsButtonActionPerformed
        MetricsTableModel tableModel = new MetricsTableModel();
        String fileAddress = fileAddressTextField.getText();
        String fileType;

        if (!fileAddress.contains(".")) {
            JOptionPane.showMessageDialog(this,
                    "File must be a zip. epub, or txt file.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        fileType = fileAddress.substring(fileAddress.lastIndexOf("."),
                fileAddress.length());
        if (fileType.matches(".zip") && fileType.matches(".epub")
                && fileType.matches("txt")) {
            JOptionPane.showMessageDialog(this,
                    "File must be a zip. epub, or txt file.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            //Loading screen set text for loading pane
            //displayMessageLabel.setText("Please wait while metrics are calculated...");
            //loadingFrame.pack();
            //loadingFrame.setLocationRelativeTo(this);
            //loadingFrame.setVisible(true);
            //displayMessageLabel.setVisible(true);
            currentBook = new Book("", "", fileAddressTextField.getText());
            tableModel.setData(currentBook);
            jTable1.setModel(tableModel);
            //make loading screen invisible again
            //loadingFrame.setVisible(false);
            computeMetricsButton.setEnabled(true);
            enableUpload();
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(this,
                    "An error occured while parsing the book. \n" + ioe.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_computeMetricsButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        if (authorComboBox1.getSelectedItem().toString().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Please Select A Author To Apply These"
                    + "Metrics Too", null, JOptionPane.ERROR_MESSAGE);
        } else {
            currentBook.setAuthor(authorComboBox1.getSelectedItem().toString());
            currentBook.setBookTitle(newBookTitleTextField.getText());
            database.addBookMetrics(currentBook);
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void authorComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_authorComboBox2ActionPerformed
        //Had to use setEnabled because Visible was causing a need for a extra click
        if (authorComboBox2.getSelectedItem() != null) {
            if (authorComboBox2.getSelectedItem().equals("New Author")) {
                newAuthorFirstNameTextField.setEnabled(true);
                newAuthorLastNameTextField.setEnabled(true);
            } else {
                newAuthorFirstNameTextField.setEnabled(false);
                newAuthorLastNameTextField.setEnabled(false);
            }
        }
    }//GEN-LAST:event_authorComboBox2ActionPerformed

    private void saveBookMetricsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBookMetricsActionPerformed
        if (authorComboBox2.getSelectedItem().toString().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Please Select A Author.", null, JOptionPane.ERROR_MESSAGE);
        } else {
            if (authorComboBox2.getSelectedItem().toString().equals("New Author")) {
                currentBook.setAuthor(newAuthorFirstNameTextField.getText() + " "
                        + newAuthorLastNameTextField.getText());
                currentBook.setBookTitle(newBookTitleTextField2.getText());
                database.addBookMetrics(currentBook);
            } else {
                currentBook.setAuthor(authorComboBox2.getSelectedItem().toString());
                currentBook.setBookTitle(newBookTitleTextField2.getText());
                database.addBookMetrics(currentBook);
            }
        }
    }//GEN-LAST:event_saveBookMetricsActionPerformed

    private void authorViewDataComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_authorViewDataComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_authorViewDataComboBoxActionPerformed

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

            @Override
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    private void setup() {
        //setup combo Boxes
        currentBook = null;
        authorComboBox1.removeAllItems();
        authorComboBox2.removeAllItems();
        authorViewDataComboBox.removeAllItems();
        authorComboBox1.addItem("");
        authorComboBox2.addItem("");
        authorComboBox2.addItem("New Author");
        authorViewDataComboBox.addItem("");
        List<String> authors = database.getListOfAuthors();
        for (int i = 0; i < authors.size(); i++) {
            authorComboBox1.addItem(authors.get(i));
            authorComboBox2.addItem(authors.get(i));
            authorViewDataComboBox.addItem(authors.get(i));
        }
        //Reset text boxes to blank
        newAuthorFirstNameTextField.setText(null);
        newAuthorLastNameTextField.setText(null);
        newBookTitleTextField.setText(null);
        newBookTitleTextField2.setText(null);
        fileAddressTextField.setText(null);
        unknownAuthorPane.setEnabled(false);
        knownAuthorPane.setEnabled(false);
        viewMatchRankingsButton.setEnabled(false);
        authorComboBox1.setEnabled(false);
        authorComboBox2.setEnabled(false);
        newBookTitleTextField.setEnabled(false);
        newBookTitleTextField2.setEnabled(false);
        saveButton.setEnabled(false);
        saveBookMetrics.setEnabled(false);
        jLabel2.setEnabled(false);
        jLabel3.setEnabled(false);
        jLabel4.setEnabled(false);
        jLabel5.setEnabled(false);
        jLabel6.setEnabled(false);
        jLabel7.setEnabled(false);
    }

    private void enableUpload() {
        unknownAuthorPane.setEnabled(true);
        knownAuthorPane.setEnabled(true);
        viewMatchRankingsButton.setEnabled(true);
        authorComboBox1.setEnabled(true);
        authorComboBox2.setEnabled(true);
        newBookTitleTextField.setEnabled(true);
        newBookTitleTextField2.setEnabled(true);
        saveButton.setEnabled(true);
        saveBookMetrics.setEnabled(true);
        jLabel2.setEnabled(true);
        jLabel3.setEnabled(true);
        jLabel4.setEnabled(true);
        jLabel5.setEnabled(true);
        jLabel6.setEnabled(true);
        jLabel7.setEnabled(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox authorComboBox1;
    private javax.swing.JComboBox authorComboBox2;
    private javax.swing.JCheckBox authorKnownChkBox;
    private javax.swing.JComboBox authorViewDataComboBox;
    private javax.swing.JButton browseButton;
    private javax.swing.JButton computeMetricsButton;
    private javax.swing.JLabel displayMessageLabel;
    private javax.swing.JTextField fileAddressTextField;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JPanel knownAuthorPane;
    private java.awt.Label label1;
    private javax.swing.JFrame loadingFrame;
    private javax.swing.JTextField newAuthorFirstNameTextField;
    private javax.swing.JTextField newAuthorLastNameTextField;
    private javax.swing.JTextField newBookTitleTextField;
    private javax.swing.JTextField newBookTitleTextField2;
    private javax.swing.JScrollPane popupScrollPane;
    private javax.swing.JTable popupTable;
    private javax.swing.JFrame resultsPopup;
    private javax.swing.JButton saveBookMetrics;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton searchMetricsButton;
    private javax.swing.JPanel unknownAuthorPane;
    private javax.swing.JButton viewMatchRankingsButton;
    // End of variables declaration//GEN-END:variables
}

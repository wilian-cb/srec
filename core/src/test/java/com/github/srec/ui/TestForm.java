package com.github.srec.ui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestForm {
    private static final Logger logger = Logger.getLogger(TestForm.class);
    private JComboBox calculationCB;
    private JFormattedTextField initialValueTF;
    private JPanel mainPnl;
    private JButton okButton;
    private JButton cancelButton;
    private JButton loadButton;
    private JButton dialogButton;
    private JTree tree1;
    private JTabbedPane tabbedPane;
    private JPanel tab1;
    private JCheckBox cb1;
    private JCheckBox cb2;
    private JCheckBox cb3;
    private JRadioButton radioButton1RadioButton;
    private JRadioButton radioButton2RadioButton;
    private JRadioButton radioButton3RadioButton;
    private JTextField textField1;
    private JPasswordField passwordField;
    private JFormattedTextField formattedTextField;
    private JTextArea textArea;
    private JTextPane textPane;
    private JEditorPane editorPane;
    private JToolBar toolbar;
    private JSlider slider;
    private JPanel tab3;
    private JPanel tab2;
    private JTable table;
    private JButton toolbarButton1;
    private JTextField textField2;
    private JTextField textField3;

    private JFrame frame;
    private JDesktopPane desktop;
    private JInternalFrame internalFrame;

    public TestForm() {
        $$$setupUI$$$();
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openScript();
            }
        });
        dialogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showDialog();
            }
        });
        cb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                textField2.setEnabled(true);
            }
        });
    }

    private void openScript() {
        final JFileChooser fc = new JFileChooser();
        fc.addChoosableFileFilter(new FileNameExtensionFilter("Ruby files", "rb"));
        int returnVal = fc.showOpenDialog(frame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            logger.debug(fc.getSelectedFile());
        }
    }

    private void showDialog() {
        JDialog dlg = new JDialog();
        dlg.setTitle("Test Dialog");
        dlg.setName("testDialog");

        dlg.setLayout(new FlowLayout());
        dlg.add(new JLabel("Test Label 1"));
        dlg.add(new JTextField("Test TextField 2"));
        dlg.add(new JTextField("Vlr Original: 50,00\nVlr Corrente: 50,00"));

        dlg.setSize(250, 90);
        dlg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dlg.setLocationRelativeTo(frame);
        dlg.setVisible(true);
    }


    public void init() {
        frame = new JFrame("TestForm");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setMinimumSize(new Dimension(800, 600));

        desktop = new JDesktopPane();
        frame.setContentPane(desktop);

        internalFrame = new JInternalFrame("Internal Frame");
        internalFrame.setClosable(true);
        internalFrame.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
        internalFrame.setResizable(true);
        internalFrame.setContentPane(mainPnl);
        internalFrame.pack();
        desktop.add(internalFrame);

        calculationCB.addItem("Current Value");
        calculationCB.addItem("Future Value");
        calculationCB.addItem("Wrong Value");

        JMenuBar menuBar = new JMenuBar();

        final JMenu menu1 = new JMenu("Menu1");
        final JMenu menu11 = new JMenu("Menu11");
        menu1.add(menu11);
        menuBar.add(menu1);

        final JMenu menu2 = new JMenu("Menu2");
        final JMenu menu21 = new JMenu("Menu21");
        final JMenuItem menuItem1 = new JMenuItem("Dummy");
        menu21.add(menuItem1);
        final JMenuItem menuItem2 = new JMenuItem("Show");
        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                internalFrame.show();
            }
        });
        menu21.add(menuItem2);
        menu2.add(menu21);
        menuBar.add(menu2);

        frame.setJMenuBar(menuBar);

        frame.setVisible(true);
        internalFrame.show();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                new TestForm().init();
            }
        });
    }

    private void createUIComponents() {
        String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};
        Object[][] data = {
                {"Mary", "Campione",
                        "Snowboarding", new Integer(5), new Boolean(false)},
                {"Alison", "Huml",
                        "Rowing", new Integer(3), new Boolean(true)},
                {"Kathy", "Walrath",
                        "Knitting", new Integer(2), new Boolean(false)},
                {"Sharon", "Zakhour",
                        "Speed reading", new Integer(20), new Boolean(true)},
                {"Philip", "Milne",
                        "Pool", "", new Boolean(false)}
        };

        table = new JTable(data, columnNames);
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        mainPnl = new JPanel();
        mainPnl.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JSplitPane splitPane1 = new JSplitPane();
        splitPane1.setDividerLocation(100);
        mainPnl.add(splitPane1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        splitPane1.setRightComponent(panel1);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Calculation:");
        panel2.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(58, 15), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Initial Value:");
        panel2.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(58, 15), null, 0, false));
        calculationCB = new JComboBox();
        calculationCB.setName("calculationCB");
        panel2.add(calculationCB, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        initialValueTF = new JFormattedTextField();
        initialValueTF.setName("initialValueTF");
        initialValueTF.setToolTipText("Initial Value");
        panel2.add(initialValueTF, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        panel2.add(panel3, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        cancelButton = new JButton();
        cancelButton.setFocusTraversalPolicyProvider(true);
        cancelButton.setName("cancelButton");
        cancelButton.setText("Cancel");
        panel3.add(cancelButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        okButton = new JButton();
        okButton.setName("okButton");
        okButton.setForeground(Color.BLUE);
        okButton.setText("Ok");
        panel3.add(okButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        loadButton = new JButton();
        loadButton.setName("loadBtn");
        loadButton.setText("Load...");
        panel3.add(loadButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dialogButton = new JButton();
        dialogButton.setName("dialogBtn");
        dialogButton.setText("Dialog");
        panel3.add(dialogButton, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tabbedPane = new JTabbedPane();
        tabbedPane.setName("tabbedPane");
        panel1.add(tabbedPane, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        tab1 = new JPanel();
        tab1.setLayout(new GridLayoutManager(6, 4, new Insets(0, 0, 0, 0), -1, -1));
        tab1.setName("tab1");
        tabbedPane.addTab("Buttons", tab1);
        final JLabel label3 = new JLabel();
        label3.setText("Checkboxes:");
        tab1.add(label3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Radio buttons:");
        tab1.add(label4, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cb1 = new JCheckBox();
        cb1.setName("cb1");
        cb1.setText("CheckBox1");
        tab1.add(cb1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cb2 = new JCheckBox();
        cb2.setName("cb2");
        cb2.setText("CheckBox2");
        tab1.add(cb2, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cb3 = new JCheckBox();
        cb3.setName("cb3");
        cb3.setText("CheckBox3");
        tab1.add(cb3, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButton1RadioButton = new JRadioButton();
        radioButton1RadioButton.setName("radioButton1");
        radioButton1RadioButton.setText("RadioButton1");
        tab1.add(radioButton1RadioButton, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButton2RadioButton = new JRadioButton();
        radioButton2RadioButton.setName("radioButton2");
        radioButton2RadioButton.setText("RadioButton2");
        tab1.add(radioButton2RadioButton, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        radioButton3RadioButton = new JRadioButton();
        radioButton3RadioButton.setName("radioButton3");
        radioButton3RadioButton.setText("RadioButton3");
        tab1.add(radioButton3RadioButton, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        tab1.add(spacer1, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Slider:");
        tab1.add(label5, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        slider = new JSlider();
        slider.setName("slider");
        tab1.add(slider, new GridConstraints(4, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		JComboBox orderedCombo = new JComboBox(
        		new String[]{"A", "B", "C", "D", "E", "F", "G"});
        orderedCombo.setName("orderedCombo");
        JComboBox unorderedCombo = new JComboBox(
        		new String[]{"1", "2", "3", "5", "4", "6", "7"});
        unorderedCombo.setName("unorderedCombo");
        JList orderedList = new JList(new Integer[] {10, 20, 30});
        orderedList.setName("orderedList");
        tab1.add(orderedCombo, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tab1.add(unorderedCombo, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tab1.add(new JScrollPane(orderedList), new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Value:");
        tab1.add(label6, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField2 = new JTextField();
        textField2.setEnabled(false);
        textField2.setName("textField2");
        tab1.add(textField2, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Value:");
        tab1.add(label7, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("value here");
        tab1.add(label8, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField3 = new JTextField();
        tab1.add(textField3, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tab2 = new JPanel();
        tab2.setLayout(new GridLayoutManager(6, 2, new Insets(0, 0, 0, 0), -1, -1));
        tab2.setName("tab2");
        tabbedPane.addTab("Text", tab2);
        textField1 = new JTextField();
        tab2.add(textField1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("Text field:");
        tab2.add(label9, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        passwordField = new JPasswordField();
        passwordField.setName("passwordField");
        tab2.add(passwordField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setText("Password field:");
        tab2.add(label10, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        formattedTextField = new JFormattedTextField();
        formattedTextField.setName("formattedTextField");
        tab2.add(formattedTextField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label11 = new JLabel();
        label11.setText("Formatted field:");
        tab2.add(label11, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textArea = new JTextArea();
        textArea.setName("textArea");
        tab2.add(textArea, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final JLabel label12 = new JLabel();
        label12.setText("Text area:");
        tab2.add(label12, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textPane = new JTextPane();
        textPane.setName("textPane");
        tab2.add(textPane, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        editorPane = new JEditorPane();
        editorPane.setName("editorPane");
        tab2.add(editorPane, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final JLabel label13 = new JLabel();
        label13.setText("Text pane:");
        tab2.add(label13, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label14 = new JLabel();
        label14.setText("Editor pane:");
        tab2.add(label14, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tab3 = new JPanel();
        tab3.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        tab3.setName("tab3");
        tabbedPane.addTab("Table", tab3);
        final JScrollPane scrollPane1 = new JScrollPane();
        tab3.add(scrollPane1, new GridConstraints(0, 1, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        table.setName("table");
        scrollPane1.setViewportView(table);
        final Spacer spacer2 = new Spacer();
        tab3.add(spacer2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setMinimumSize(new Dimension(100, 20));
        splitPane1.setLeftComponent(scrollPane2);
        tree1 = new JTree();
        tree1.setName("tree1");
        scrollPane2.setViewportView(tree1);
        toolbar = new JToolBar();
        mainPnl.add(toolbar, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 20), null, 0, false));
        toolbarButton1 = new JButton();
        toolbarButton1.setName("toolbarButton1");
        toolbarButton1.setText("Button");
        toolbar.add(toolbarButton1);
        label1.setLabelFor(calculationCB);
        label2.setLabelFor(initialValueTF);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPnl;
    }

}

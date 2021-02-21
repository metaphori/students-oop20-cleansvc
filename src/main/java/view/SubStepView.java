package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.Process;
import controller.ProcessImpl;
import model.step.Step;
import model.step.SubSteps;
import model.step.SubStepsImpl;
import model.step.enumerations.StepType;


public class SubStepView extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -2999321210083459030L;
    private static final String TITLE = "Inserimento nuova sottofase";
    private final JButton btnSubmit;
    //private final JButton btnRemove;
    private final JButton btnHome;
    private JTextField txtName;
    private JTextField txtDescription;
    private JTextField txtTime;
    private JComboBox<String> comboSteps;
    private Process p = ProcessImpl.getInstance();
    private List<SubSteps> subStepsList = p.getSubStepsList();
    private List<StepType> stepTypeList = p.getStepTypeList();

    private final String[] col = new String[] {"Nome", "Descrizione", "Tempo", "Fase"};
    private Object[][] data = new Object[subStepsList.size()][col.length];
    private DefaultTableModel model = new DefaultTableModel(data, col);
    private JTable table = new JTable(model);

    public SubStepView() {

        setTitle(SubStepView.TITLE);
        setMinimumSize(new Dimension(1200, 500));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panelTable = new JPanel();
        panelTable.setMinimumSize(new Dimension(1000, 200));
        panelTable.setBackground(SystemColor.activeCaption);
        panelTable.setLayout(new BorderLayout(0, 0));

        JPanel panelTitle = new JPanel();
        panelTitle.setMinimumSize(new Dimension(1000, 60));
        panelTitle.setBackground(SystemColor.activeCaption);
        panelTitle.setLayout(new BorderLayout(0, 0));

        JLabel lblTitle = new JLabel("Elenco sottofasi");
        lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
        lblTitle.setForeground(SystemColor.textText);
        lblTitle.setFont(new Font("Trebuchet MS", Font.CENTER_BASELINE,20));
        panelTitle.add(lblTitle, BorderLayout.WEST);

        btnHome = new JButton("BACK HOME");
        btnHome.setForeground(SystemColor.textText);
        btnHome.setBackground(SystemColor.activeCaption);
        btnHome.setPreferredSize(new Dimension(120, 20));
        btnHome.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        btnHome.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                model.setRowCount(0);
                HomeView cv = new HomeView();
                cv.display();
                dispose();
            }
 
        });
        panelTitle.add(btnHome, BorderLayout.EAST);
        panelTable.add(panelTitle, BorderLayout.NORTH);

        SubSteps subStep;
        StepType stepType;
        for (int i = 0; i < subStepsList.size(); i++) {
            subStep = p.getSubStepsList().get(i);
            stepType = p.getStepTypeList().get(i);
            model.insertRow(i, new Object[] {subStep.getName(), subStep.getDescription(), subStep.getTime(), stepType.getType()});
        }
 
        table.setPreferredScrollableViewportSize(new Dimension(1000, 200));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        panelTable.add(table, BorderLayout.CENTER);
        panelTable.add(new JScrollPane(table));

        final JPanel pnlSubmit = new JPanel();
        pnlSubmit.setBorder(new TitledBorder(null, "Dati Nuova Sottofase", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        pnlSubmit.setBackground(SystemColor.window);
        pnlSubmit.setPreferredSize(new Dimension(900, 140));
        pnlSubmit.setMinimumSize(new Dimension(900, 140));
        pnlSubmit.setLayout(new BorderLayout(0,0));

        final JPanel pnlData = new JPanel();
        pnlData.setBorder(null);
        pnlData.setBackground(SystemColor.window);
        pnlData.setPreferredSize(new Dimension(900, 60));
        pnlData.setMinimumSize(new Dimension(900, 60));
        pnlData.setLayout(new GridLayout(4,4,20,2));

        JLabel labelName = new JLabel("Nome:");
        labelName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(labelName);

        txtName = new JTextField(10);
        txtName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(txtName);

        JLabel labelDescription = new JLabel("Descrizione:");
        labelDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(labelDescription);

        txtDescription = new JTextField(20);
        txtDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(txtDescription);

        JLabel labelTime = new JLabel("Tempo:");
        labelTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(labelTime);

        txtTime = new JTextField(10);
        txtTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(txtTime);

        JLabel labelStep = new JLabel("Step:");
        labelStep.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(labelStep);

        comboSteps = new JComboBox<>();
        comboSteps.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        comboSteps.setToolTipText("Step");
        comboSteps.setBackground(SystemColor.inactiveCaption);
        comboSteps.setForeground(SystemColor.textText);
        comboSteps.setFont(new Font("Tahoma", Font.PLAIN, 13));
        pnlData.add(comboSteps);

        StepType st;
        for (int i = 0; i < stepTypeList.size(); i++) {
            st = p.getStepTypeList().get(i);
            comboSteps.addItem(st.getType());
        }
 
        final JPanel pnlButtons = new JPanel();
        pnlButtons.setBackground(SystemColor.window);
        pnlButtons.setBorder(null);
        pnlButtons.setPreferredSize(new Dimension(900, 30));
        pnlButtons.setMinimumSize(new Dimension(900, 30));
        pnlButtons.setLayout(new GridLayout(1,3,20,20));

        btnSubmit = new JButton("Conferma");
        btnSubmit.setForeground(SystemColor.textText);
        btnSubmit.setBackground(SystemColor.activeCaption);
        btnSubmit.setPreferredSize(new Dimension(120,20));
        btnSubmit.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        btnSubmit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                    SubSteps s  = new SubStepsImpl(getTime(), getName(), getDescription());
                    p.addStep(s);
            }
        });
        pnlButtons.add(btnSubmit);
        pnlSubmit.add(pnlButtons, BorderLayout.SOUTH);

        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(0)
                .addComponent(panelTable)
                .addGap(0)
                .addComponent(pnlSubmit)
                .addGap(0));

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(0)
                .addGroup(layout.createParallelGroup(Alignment.CENTER)
                        .addComponent(panelTable)
                        .addComponent(pnlSubmit))
                .addGap(0));

    }


    /**
     * 
     */
    public void clearInsertField() {
        comboSteps.setSelectedIndex(0);
        txtTime.setText("");
        txtName.setText("");
        txtDescription.setText("");
    }

    /**
     * 
     * @return 0 if there is an empty textArea.
     */

    public Boolean missingField() {
        return (Boolean.parseBoolean(txtTime.getText()) || getName().isEmpty() || getDescription().isEmpty() || comboSteps.getSelectedItem().equals("Seleziona step"));
    }

    /**
     * 
     * @return time.
     */
    public int getTime() {
        return Integer.parseInt(txtTime.getText());
    }

    /**
     * 
     * @return name.
     */
    public String getName() {
        return txtName.getText();
    }

    /**
     * @return description.
     */
    public String getDescription() {
        return txtDescription.getText();
    }

    /**
     * 
     */
    public void display() {
        setVisible(true);
        setResizable(true);

    }


}
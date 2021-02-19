package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;

import controller.Company;
import controller.CompanyImpl;
import model.Appointments;
import model.AppointmentsImpl;
import model.users.Clients;
import model.users.ClientsImpl;
import model.users.Staff;

public class NewAppointmentView extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 2089945830206989799L;
    private static final String TITLE = "NUOVO APPUNTAMENTO";
    private JComboBox<String> comboClients;
    private final JButton btnSubmit;
    private final JButton btnHome;
    private final DatePicker datepicker;
    private final TimePicker timepicker;
    private Company company = CompanyImpl.getInstance();
    private List<Clients> clientsList = company.getClient();
    public PopUp popUp = new PopUp();

    public NewAppointmentView() {

        setTitle(NewAppointmentView.TITLE);
        setMinimumSize(new Dimension(1200, 500));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panelTitle = new JPanel();
        panelTitle.setMinimumSize(new Dimension(1000, 60));
        panelTitle.setBackground(SystemColor.activeCaption);
        getContentPane().add(panelTitle, BorderLayout.NORTH);
        panelTitle.setLayout(new BorderLayout(0, 0));

        JLabel lblTitle = new JLabel("Richieste sanificazione");
        lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
        lblTitle.setForeground(SystemColor.textText);
        lblTitle.setFont(new Font("Trebuchet MS", Font.CENTER_BASELINE, 20));
        panelTitle.add(lblTitle, BorderLayout.WEST);

        btnHome = new JButton("BACK HOME");
        btnHome.setForeground(SystemColor.textText);
        btnHome.setBackground(SystemColor.activeCaption);
        btnHome.setPreferredSize(new Dimension(120, 20));
        btnHome.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        btnHome.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                HomeView cv = new HomeView();
                cv.display();
                dispose();
            }
        });
        panelTitle.add(btnHome, BorderLayout.EAST);

        JPanel mainPanel = new JPanel();
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(new BorderLayout(0, 0));

        final JPanel pnlSubmit = new JPanel();
        pnlSubmit.setBorder(new TitledBorder(null, "Dati nuovo appuntamento", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        pnlSubmit.setBackground(SystemColor.window);
        pnlSubmit.setPreferredSize(new Dimension(10, 100));
        pnlSubmit.setMinimumSize(new Dimension(1000, 100));
        mainPanel.add(pnlSubmit, BorderLayout.NORTH);

        JLabel labelCliente = new JLabel("Cliente:");
        labelCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSubmit.add(labelCliente);

        comboClients = new JComboBox<>();
        comboClients.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        comboClients.setToolTipText("Cliente");
        comboClients.setBackground(SystemColor.inactiveCaption);
        comboClients.setForeground(SystemColor.textText);
        comboClients.setFont(new Font("Tahoma", Font.PLAIN, 13));
        comboClients.addItem("Seleziona un cliente");
        pnlSubmit.add(comboClients);
        Clients cc;
        for (int i = 0; i < clientsList.size(); i++) {
            cc = company.getClient().get(i);
            comboClients.addItem(cc.getName() + " " + cc.getAddress());
        }
        /*
        comboClients.addItem("Mario Rossi" + " " + "Via degli omonimi 33");
        comboClients.addItem("Luigi Bianchi" + " " + "Via dell'Università 50");
       */

        JLabel labelDatePicker = new JLabel("Data:");
        labelDatePicker.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSubmit.add(labelDatePicker);

        datepicker = new DatePicker();
        datepicker.getComponentToggleCalendarButton().setForeground(SystemColor.textText);
        datepicker.getComponentDateTextField().setToolTipText("Data dell'appuntamento");
        datepicker.getComponentToggleCalendarButton().setBackground(SystemColor.activeCaption);
        datepicker.getComponentDateTextField().setFont(new Font("Tahoma", Font.PLAIN, 13));
        pnlSubmit.add(datepicker);

        JLabel labelTimePicker = new JLabel("Orario:");
        labelTimePicker.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSubmit.add(labelTimePicker);

        timepicker = new TimePicker();
        timepicker.getComponentToggleTimeMenuButton().setForeground(SystemColor.textText);
        timepicker.getComponentTimeTextField().setToolTipText("Orario dell'appuntamento");
        timepicker.getComponentToggleTimeMenuButton().setBackground(SystemColor.activeCaption);
        timepicker.getComponentTimeTextField().setFont(new Font("Tahoma", Font.PLAIN, 13));
        pnlSubmit.add(timepicker);

        btnSubmit = new JButton("Conferma");
        btnSubmit.setForeground(SystemColor.textText);
        btnSubmit.setBackground(SystemColor.activeCaption);
        btnSubmit.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        btnSubmit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                //if (!missingField()) {
                    Appointments a = new AppointmentsImpl(getDate(), getHour());
                    company.addAppointment(a);
                    popUp.popUpInfo("Appuntamento inserito con successo.");
                    //addAppointmentToTable(company.getAppointment().get(appointmentsList.size() - 1));
                    clearInsertField();
                //} else {
                    //popUp.popUpWarning("Ci sono dati mancanti o errati");
               // }
            }
        });
        pnlSubmit.add(btnSubmit);
        mainPanel.add(pnlSubmit);

    }

    public void display() {
        setVisible(true);
        setResizable(true);
    }

    public String getDate() {
        return datepicker.getDate().toString();
    }

    public String getHour() {
        return timepicker.getTime().toString();
    }

    public void clearInsertField() {
        comboClients.setSelectedIndex(0);
        datepicker.setText("");
        timepicker.setText("");
    }

    public Boolean missingField() {
        return (getDate().isEmpty() || getHour().isEmpty() || comboClients.getSelectedItem().equals("Seleziona un cliente"));
    }

}

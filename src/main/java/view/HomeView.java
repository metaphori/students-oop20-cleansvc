package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.backupFile.*;

public class HomeView extends JFrame{

    /**
     * 
     */
    private static final long serialVersionUID = -1440993813136999810L;
    private static final String TITLE = "CLEAN SERVICE MANAGER";
    final JButton btnClienti;
    final JButton btnGrafici;
    final JButton btnStaff;
    final JButton btnProduct;
    final JButton btnProcess;
    //final JButton btnApp;
    final JButton btnSaveAndExit;
    
    private SaveAndLoadClients backupClients = new SaveAndLoadClients();
    private SaveAndLoadStaff backupStaff = new SaveAndLoadStaff();
    private SaveAndLoadProducts backupProducts = new SaveAndLoadProducts();
    
    
    
    public HomeView() {
        
        setTitle(HomeView.TITLE);
        setLayout(new GridLayout(3,3));
        setMinimumSize(new Dimension(1000, 500));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel panelTitle = new JPanel();
        panelTitle.setMinimumSize(new Dimension(10, 100));
        //panelTitle.setBackground(SystemColor.activeCaption);
        getContentPane().add(panelTitle, BorderLayout.CENTER);
        panelTitle.setLayout(new FlowLayout());


        btnClienti = new JButton("Area Clienti");
        btnClienti.setForeground(SystemColor.textText);
        btnClienti.setBackground(SystemColor.activeCaption);
        btnClienti.setPreferredSize(new Dimension(280,70));
        btnClienti.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        btnClienti.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ClientsView cv = new ClientsView();
                cv.display();
                dispose();
            }
            
        });
        panelTitle.add(btnClienti);
        
        /*btnApp = new JButton("Area App");
        btnApp.setForeground(SystemColor.textText);
        btnApp.setBackground(SystemColor.activeCaption);
        btnApp.setPreferredSize(new Dimension(280,70));
        btnApp.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        btnApp.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AppointmentsView cv = new AppointmentsView();
                cv.display();
                dispose();
            }
            
        });
        panelTitle.add(btnApp);*/
        
        btnStaff = new JButton("Area Dipendenti");
        btnStaff.setForeground(SystemColor.textText);
        btnStaff.setBackground(SystemColor.activeCaption);
        btnStaff.setPreferredSize(new Dimension(280,70));
        btnStaff.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        btnStaff.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                StaffView sv = new StaffView();
                sv.display();
                dispose();
            }
            
        });
        panelTitle.add(btnStaff);
        
        btnProduct = new JButton("Area Prodotti");
        btnProduct.setForeground(SystemColor.textText);
        btnProduct.setBackground(SystemColor.activeCaption);
        btnProduct.setPreferredSize(new Dimension(280,70));
        btnProduct.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        btnProduct.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ProductView pv = new ProductView();
                pv.display();
                dispose();
            }
            
        });
        panelTitle.add(btnProduct);
        
        btnProcess = new JButton("Area Processo");
        btnProcess.setForeground(SystemColor.textText);
        btnProcess.setBackground(SystemColor.activeCaption);
        btnProcess.setPreferredSize(new Dimension(280,70));
        btnProcess.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        btnProcess.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                SubStepView pv = new SubStepView();
                pv.display();
                dispose();
            }

        });
        panelTitle.add(btnProcess);

        btnGrafici = new JButton("Area Grafici");
        btnGrafici.setForeground(SystemColor.textText);
        btnGrafici.setBackground(SystemColor.activeCaption);
        btnGrafici.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        btnGrafici.setPreferredSize(new Dimension(280,70));
        btnGrafici.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AdministratorChartsView av = new AdministratorChartsView();
                av.display();
                dispose();
            }

        });
        panelTitle.add(btnGrafici);
        
        btnSaveAndExit = new JButton("Salva ed Esci");
        btnSaveAndExit.setForeground(SystemColor.textText);
        btnSaveAndExit.setBackground(SystemColor.activeCaption);
        btnSaveAndExit.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        btnSaveAndExit.setPreferredSize(new Dimension(280,70));
        btnSaveAndExit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                backupClients.save();
                backupStaff.save();
                backupProducts.save();
                dispose();
            }
            
        });
        panelTitle.add(btnSaveAndExit);
    }

    public void display() {
        setVisible(true);
        setResizable(true);
    }
}

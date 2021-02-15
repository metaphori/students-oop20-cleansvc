package controller.backupFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.users.Clients;
import model.users.ClientsImpl;
import model.users.Company;
import model.users.CompanyImpl;

public class SaveAndLoadClients implements SaveAndLoad<List<Clients>> {

    private Company company = CompanyImpl.getInstance();
    private static final String FILE_CLIENTS = "doc/Clients.txt";
    private static final String CFPIVA_STR = "CFPIVA: ";
    private static final String NAME_STR = "NAME: ";
    private static final String ADDRESS_STR = "ADDRESS: ";
    private static final String CITY_STR = "CITY: ";
    private static final String CAP_STR = "CAP: ";
    private static final String TEL_STR = "TEL: ";
    private static final String EMAIL_STR = "EMAIL: ";
    private static final String MQ_STR = "MQSTRUCTURE: ";

    @Override
    public void save() {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(FILE_CLIENTS))) {
            for (final Clients c : this.company.getClient()) {
                w.write(CFPIVA_STR + c.getCFPIVA());
                w.newLine();
                w.write(NAME_STR + c.getName());
                w.newLine();
                w.write(ADDRESS_STR + c.getAddress());
                w.newLine();
                w.write(CITY_STR + c.getCity());
                w.newLine();
                w.write(CAP_STR + c.getCAP());
                w.newLine();
                w.write(TEL_STR + c.getTel());
                w.newLine();
                w.write(EMAIL_STR + c.getEmail());
                w.newLine();
                w.write(MQ_STR + c.getmqStructure());
                w.newLine();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Clients> load() {
        final List<Clients> clientsList = new ArrayList<>();
        final List<String> cfPIvaList = new ArrayList<>();
        final List<String> nameList = new ArrayList<>();
        final List<String> addressList = new ArrayList<>();
        final List<String> cityList = new ArrayList<>();
        final List<String> capList = new ArrayList<>();
        final List<String> telList = new ArrayList<>();
        final List<String> emailList = new ArrayList<>();
        final List<Integer> mqStructureList = new ArrayList<>();
        try (BufferedReader r = new BufferedReader(new FileReader(FILE_CLIENTS))) {
            r.lines().forEach(l -> {
                if (l.contains(CFPIVA_STR)) {
                    cfPIvaList.add(l.substring(CFPIVA_STR.length()));
                }
                if (l.contains(NAME_STR)) {
                    nameList.add(l.substring(NAME_STR.length()));
                }
                if (l.contains(ADDRESS_STR)) {
                    addressList.add(l.substring(ADDRESS_STR.length()));
                }
                if (l.contains(CITY_STR)) {
                    cityList.add(l.substring(CITY_STR.length()));
                }
                if (l.contains(CAP_STR)) {
                    capList.add(l.substring(CAP_STR.length()));
                }
                if (l.contains(TEL_STR)) {
                    telList.add(l.substring(TEL_STR.length()));
                }
                if (l.contains(EMAIL_STR)) {
                    emailList.add(l.substring(EMAIL_STR.length()));
                }
                if (l.contains(MQ_STR)) {
                    mqStructureList.add(Integer.parseInt(l.substring(MQ_STR.length())));
                }
            });
            for (int i = 0; i < cfPIvaList.size(); i++) {
                clientsList.add(new ClientsImpl(cfPIvaList.get(i), nameList.get(i), addressList.get(i), cityList.get(i),
                        capList.get(i), telList.get(i), emailList.get(i), mqStructureList.get(i)));
            }
            return clientsList;
        } catch (final IOException e) {
            return clientsList;
        }
    }

}
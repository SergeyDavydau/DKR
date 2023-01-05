package com.example.dkr.service;

import com.example.dkr.model.*;
import com.example.dkr.model.Dictionary;
import com.example.dkr.repo.*;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Date;
import java.util.*;

@Service
@AllArgsConstructor
public class MainService {

    DictionaryRepo dictionaryRepo;
    ClientRepo clientRepo;
    AccountRepo accountRepo;
    AdressRepo adressRepo;
    BankRepo bankRepo;
    WorkTableRepo workTableRepo;
    ParamsTableRepo paramsTableRepo;

    static Random random = new Random();

    public static Logger logger;

    final static List<String> names = Arrays.asList("Даниил", "Максим", "Владислав", "Никита", "Артем", "Иван", "Кирилл", "Егор", "Илья", "Андрей");
    final static List<String> surname = Arrays.asList("Иванов", "Смирнов", "Кузнецов", "Попов", "Васильев", "Петров", "Соколов", "Лазарев", "Медведев", "Ершов");
    final static List<String> unp = Arrays.asList("UNP123HF", "DL60DHE4", "HEOBM59X", "ZXP56U44", "ALO39NMV", "MA3098JK", "NHFG4567", "NVM9876Q", "ASD456JL");
    final static List<Integer> currCodes = Arrays.asList(933, 874, 456);
    final static List<String> country = Arrays.asList("Belarus", "Armenia", "Germany", "Japan", "USA", "Peru", "Kazahstan", "Poland", "French", "Italy");
    final static List<String> city = Arrays.asList("Minsk", "Erevan", "Berlin", "Tokyo", "Washington", "Lima", "Astana", "Warszawa", "Paris", "Rome");
    final static List<String> street = Arrays.asList("1", "Drope", "Bulgina", "Stain", "Joma", "2-arena", "Buda", "lout", "Dark", "light");
    final static List<String> banks = Arrays.asList("BELVEB", "BELARUSBANK", "IDEA", "DBB", "VTB", "SWISS", "BNB", "PRIOR", "GAZPROM", "BELINVEST");

    static {
        logger = LogManager.getLogger();
    }

    public void fillDictionary() {
        Dictionary dictionary;
        List<Dictionary> allDictionary = new ArrayList<>();
        for (int i = 1; i < 101; ++i) {
            dictionary = new Dictionary("Name dict_" + i, "Value dict_" + i);
            allDictionary.add(dictionary);
        }
        dictionaryRepo.saveAll(allDictionary);

        logger.info("--------------------Table Dictionary: add 100 records");
    }

    public void fillClientAndAdress() {
        Client client;
        Adress adress;
        List<Client> allClient = new ArrayList<>();
        for (int i = 0; i < 100; ++i) {
            client = new Client();
            setRandomName(client);

            adress = setRandomAdress();
            client.setAdress(adress);

            allClient.add(client);
        }
        clientRepo.saveAll(allClient);

        logger.info("--------------------Table Client: add 100 records \n" +
                "--------------------Table Adress: add 100 records \n");
    }

    public void fillBankAndAccounts(){
        List<Bank> allBank = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            allBank.add(CreateBankAndAccount(i));
        }
        bankRepo.saveAll(allBank);

        logger.info("--------------------Table Bank: add 10 records \n" +
                "--------------------Table Account: add 100 records \n");
    }

    public void setRandomName(Client client) {
        client.setName(names.get(random.nextInt(9)));
        client.setSurName(surname.get(random.nextInt(9)));
    }

    public Bank CreateBankAndAccount(int num) {
        List<Account> accountList = new ArrayList<>();
        Bank bank = new Bank(banks.get(num), num);
        for (int i = 0; i < 10; ++i) {
            accountList.add(new Account(unp.get(random.nextInt(9)), currCodes.get(random.nextInt(2)), bank));
        }
        bank.setAccounts(accountList);
        return bank;
    }

    public Adress setRandomAdress(){
        Adress adress = new Adress();
        int num = random.nextInt(9);
        adress.setCountry(country.get(num));
        adress.setCity(city.get(num));
        adress.setStreet(street.get(num));

        return adress;
    }

//    public void deleteBank(Bank bank){
//        accountRepo.deleteAllByBankCode(bank.getCode());
//        bankRepo.delete(bank);
//    }


    public void fillWorkTable(){
        List <WorkTable> workTableList = new ArrayList<>();
        Date curentDate = new Date(Calendar.getInstance().getTimeInMillis());

        for (int i = 0; i < 10; ++i) {
            WorkTable workTable = new WorkTable();
            ParamsTable paramsTable = new ParamsTable("Param_" + i, "value=" + i, curentDate);
            InfoTable infoTable = new InfoTable("part " + i, "description part " + i);
            workTable.setInfoTable(infoTable);
            infoTable.setWorkTable(workTable);

            paramsTable.setWorkTable(workTable);
            workTable.getParamsTables().add(paramsTable);

//            if(i % 2 == 0){
                SignatureTable signatureTable = new SignatureTable(i, "report for signatyre type " + i);
                workTable.setSignatureTable(signatureTable);
            signatureTable.setWorkTable(workTable);
//            }

            workTableList.add(workTable);
        }
        workTableRepo.saveAll(workTableList);
    }




}

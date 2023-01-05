package com.example.dkr;

import com.example.dkr.model.*;
import com.example.dkr.model.Dictionary;
import com.example.dkr.repo.*;
import com.example.dkr.repo.filter.DictionaryFilter;
import com.example.dkr.service.MainService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.function.Function;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DkrApplication.class})
public class DkrTest {

    @Autowired
    MainService mainService;
    @Autowired
    DictionaryRepo dictionaryRepo;
    @Autowired
    ClientRepo clientRepo;
    @Autowired
    AdressRepo adressRepo;
    @Autowired
    BankRepo bankRepo;
    @Autowired
    AccountRepo accountRepo;
    @Autowired
    WorkTableRepo workTableRepo;
    @Autowired
    InfoTableRepo infoTableRepo;
    @Autowired
    SignatureTableRepo signatureTableRepo;
    @Autowired
    ParamsTableRepo paramsTableRepo;

//    Logger logger;

//    ***    Цель 1.
//    Реализовать DML операций загрузки, отображения, изменения и удаления:
//        - для одиночной таблицы без связей
//        - для двух связанных таблиц вида «один к одному»
//            - для двух связанных таблиц вида «один ко многим».
//    Для связанных таблиц сравнить работу в жадном и ленивом режимах.
//    Реализовать получение набора данных из больше чем двух связанных между собой таблиц (как пример: Табель, День табеля, Сегмент дня табеля).
//    Реализовать получение данных для набора таблиц с разными типами связей между ними (как пример набор таблиц: Запросы, Параметры запросов,
//    Описание типов запросов, Описание параметра запроса).
//   + Реализация получения данных из базы с пагинацией.
//    Наполнить созданные таблицы тестовыми данными с учетом их предназначение (справочники - 100 записей, таблицы с фактами -100 000) и протестировать проект на производительность.

//    Обратить внимание на необходимость использования соответствующих индексов.
//    Проработать вопрос информационной безопасности данного проекта: какие существуют угрозы и как в проекте они сводятся к допустимому уровню.
//    DоD: Написать отчет по созданному демо проекту с подробным описанием проработки каждого требования

    @Before
    public void setUp() throws Exception {



    }

    //DML операциb - для одиночной таблицы без связей
    @Test
    public void dictionaryDMLOperationTest() {
        mainService.fillDictionary();
        StringBuilder res = new StringBuilder();
        List<Dictionary> allDictionary = dictionaryRepo.findAll();
        //отображение
        allDictionary.forEach(dict -> res.append(dict.toString() + "\n"));
        MainService.logger.info("--------------------Method dictionaryRepo.findAll() find " + allDictionary.size()
                + " records \n" + res);

        //Изменение
        allDictionary.forEach(dict -> dict.setName("EDIT_" + dict.getName()));
        dictionaryRepo.saveAll(allDictionary);
        allDictionary = dictionaryRepo.findAll();
        res.setLength(0);
        allDictionary.forEach(dict -> res.append(dict.toString() + "\n"));
        MainService.logger.info("--------------------Rename field 'name' \n"
                + "--------------------Save edit records"
                + "Method dictionaryRepo.findAll() find " + allDictionary.size()
                + " records \n" + res);

        //Удаление
        dictionaryRepo.deleteAll(allDictionary);
        allDictionary = dictionaryRepo.findAll();
        res.setLength(0);
        MainService.logger.info("--------------------Delete records"
                + "Method dictionaryRepo.findAll() find " + allDictionary.size());

    }

    //        - для двух связанных таблиц вида «один к одному»
    @Test
    public void clientAndAdressDMLOperationTest() {
        mainService.fillClientAndAdress();
        StringBuilder res = new StringBuilder();

        //отображение
        List<Client> belarusClient = clientRepo.getClientByAdress_Country("Belarus");
        belarusClient.forEach(client -> res.append(client.toString() + "\n"));
        MainService.logger.info("--------------------Method clientRepo.getClientByAdress_Country(\"Belarus\") find "
                + belarusClient.size() + " records \n"
                + res);

        //Изменение
        belarusClient.forEach(client -> client.setName("Karatkevich"));
        clientRepo.saveAll(belarusClient);
        belarusClient = clientRepo.getClientByAdress_Country("Belarus");
        res.setLength(0);
        belarusClient.forEach(client -> res.append(client.toString() + "\n"));
        MainService.logger.info("--------------------Rename client from Belarus \n"
                +"--------------------Method clientRepo.getClientByAdress_Country(\"Belarus\") find "
                + belarusClient.size() + " records \n"
                + res);

        //Удаление
        clientRepo.deleteAll(belarusClient);
        belarusClient = clientRepo.getClientByAdress_Country("Belarus");
        MainService.logger.info("--------------------Delete client from Belarus \n"
                + "--------------------Method clientRepo.getClientByAdress_Country(\"Belarus\") find "
                + belarusClient.size() + " records \n");


        //отображение
        List<Adress> adressGermany = adressRepo.getAdressByCountry("Germany");
        res.setLength(0);
        adressGermany.forEach(adress -> res.append(adress.toString() + "\n"));
        MainService.logger.info("--------------------Method adressRepo.getAdressByCountry(\"Germany\") find "
                + adressGermany.size() + " records \n"
                + res);

        //Изменение
        adressGermany.forEach(adress -> adress.setCity("Bremen"));
        adressRepo.saveAll(adressGermany);
        adressGermany = adressRepo.getAdressByCountry("Germany");
        res.setLength(0);
        adressGermany.forEach(adress -> res.append(adress.toString() + "\n"));
        MainService.logger.info("--------------------Rename city for adress from Germany \n"
                +"--------------------Method adressRepo.getAdressByCountry(\"Germany\") find "
                + adressGermany.size() + " records \n"
                + res);

        //Удаление
        adressRepo.deleteAll(adressGermany);
        adressGermany = adressRepo.getAdressByCountry("Germany");
        MainService.logger.info("--------------------Delete adress from Germany \n"
                + "--------------------Method adressRepo.getAdressByCountry(\"Germany\") find "
                + adressGermany.size() + " records \n");

        clientRepo.deleteAll(clientRepo.findAll());
    }

    //        - для двух связанных таблиц вида «один ко многим»
    @Test
    public void bankAndAccountDMLOperationTest() {
        mainService.fillBankAndAccounts();
        StringBuilder res = new StringBuilder();

        //Отображение
        List<Bank> banks = bankRepo.findAll();
        banks.forEach(bank -> res.append(bank.toString() + "\n"));
        MainService.logger.info("--------------------Method bankRepo.findAll() find "
                + banks.size() + " records \n"
                + res);

        //Изменение
        banks.forEach(bank -> bank.setName(bank.getName() + " FROM BELARUS"));
        bankRepo.saveAll(banks);
        banks = bankRepo.findAll();
        res.setLength(0);
        banks.forEach(bank -> res.append(bank.toString() + "\n"));
        MainService.logger.info("--------------------Rename Bank.name \n"
                +"--------------------Method bankRepo.findAll() find "
                + banks.size() + " records \n"
                + res);

        //Удаление
        bankRepo.delete(banks.get(5));
        banks = bankRepo.findAll();
        res.setLength(0);
        banks.forEach(bank -> res.append(bank.toString() + "\n"));
        MainService.logger.info("--------------------Delete Bank by code 5 \n"
                + "--------------------Method bankRepo.findAll() find "
                + banks.size() + " records \n"
                + res);


        //Отображение
        List<Account> accounts = accountRepo.findAll();
        res.setLength(0);
        accounts.forEach(acc -> res.append(acc.toString() + "\n"));
        MainService.logger.info("--------------------Method accountRepo.findAll() find "
                + accounts.size() + " records \n"
                + res);

        //Изменение
        Account account = accounts.get(0);
        account.setUnp("EDIT UNP");
        accountRepo.save(account);
        account = accountRepo.findByCode(account.getCode());
        MainService.logger.info("--------------------Set unp account with code=" + account.getCode() + "\n"
                + "--------------------Method accountRepo.findByCode(" + account.getCode() + ") find records \n"
                + account.toString());

        //Удаление
        accounts = accountRepo.findAllByBank_Name(banks.get(0).getName());
        accountRepo.deleteAll(accounts);
        accounts = accountRepo.findAll();
        res.setLength(0);
        accounts.forEach(acc -> res.append(acc.toString() + "\n"));
        MainService.logger.info("--------------------Delete 10 records"
                + "--------------------Method accountRepo.findAll() find "
                + accounts.size() + " records \n"
                + res);

        bankRepo.deleteAll(banks);
    }

    ///    Реализовать получение набора данных из больше чем двух связанных между собой таблиц
    @Test
    public void WorkTableDMLOperationTest() {
        mainService.fillWorkTable();
        List<WorkTable> workTableList = workTableRepo.findAllBySignatureTableTypeAndInfoTablePartAndParamTableName(2, "part 2", "Param_2");
        StringBuilder res = new StringBuilder();
        workTableList.forEach(workTable -> res.append(workTable.toString() + "\n"));
        if(workTableList.size() > 0){
            MainService.logger.info("--------------------Method workTableRepo.findAllBySignatureTableTypeAndInfoTablePart(2, \"part 2\") find "
                    + workTableList.size() + " records \n"
                    + "Infotable: " + workTableList.get(0).getInfoTable() + "\n"
            + "SignatureTable: " + workTableList.get(0).getSignatureTable() + "\n"
            + "ParamsTables: " + workTableList.get(0).getParamsTables() + "\n" );
        }
        workTableRepo.deleteAll();
    }


    //    Реализация получения данных из базы с пагинацией.
    @Test
    public void DictionaryTablePageTest() {
        mainService.fillDictionary();
        //Пагинация с сортировкой
        Pageable sortedByCode = PageRequest.of(0, 15, Sort.by("code"));
        Page<Dictionary> page = dictionaryRepo.findAll(sortedByCode);

        MainService.logger.info("Пагинация c сортировкой по полю code\n" +
                page.getContent().toString() + "\n");

        //Пагинация с сортировкой и фильтром
        sortedByCode = PageRequest.of(0, 15, Sort.by("code"));
        Specification dictSpec = DictionaryFilter.filterByCodeBetween(20, 25);
        page = dictionaryRepo.findAll(dictSpec, sortedByCode);

        MainService.logger.info("Пагинация c сортировкой по полю code и фильтром по коду между 20 и 25\n" +
                page.getContent().toString() + "\n");

        dictionaryRepo.deleteAll();
    }

    //    Наполнить созданные таблицы тестовыми данными с учетом их предназначение (справочники - 100 записей, таблицы с фактами -100 000)
//    и протестировать проект на производительность.
    @Test
    public void FillDictionaryAndClientTest() {

        Calendar satrtDictSaveTime = Calendar.getInstance();
        List<Dictionary> allDict = new ArrayList<>();
        for (int i = 0; i < 100; ++i) {
            allDict.add(new Dictionary("Name_" + i, "Value = " + 1));
        }

        dictionaryRepo.saveAll(allDict);
        Calendar stopDictSaveTime = Calendar.getInstance();
        long resultDictSaveTime = stopDictSaveTime.getTimeInMillis() - satrtDictSaveTime.getTimeInMillis();

        Calendar satrtClientSaveTime = Calendar.getInstance();
        List<Client> allClient = new ArrayList<>();
        List<Client> prepareSaveClient = new ArrayList<>();
        for (int i = 0; i < 100000; ++i) {
            prepareSaveClient.add(new Client(i, new Adress(), "Name_" + i, "Surname_" + i));
            if (prepareSaveClient.size() == 999) {
                clientRepo.saveAll(prepareSaveClient);
                allClient.addAll(prepareSaveClient);
                prepareSaveClient.clear();
            }
        }
        if (prepareSaveClient.size() > 0) {
            clientRepo.saveAll(prepareSaveClient);
            allClient.addAll(prepareSaveClient);
            prepareSaveClient.clear();
        }

        Calendar stopClientSaveTime = Calendar.getInstance();
        long resultClientSaveTime = stopClientSaveTime.getTimeInMillis() - satrtClientSaveTime.getTimeInMillis();

        dictionaryRepo.deleteAll(allDict);
        clientRepo.deleteAll(allClient);

        Date res = new Date(resultClientSaveTime);
        String result = res.getMinutes() + "min " + res.getSeconds() + "sec";
        resultClientSaveTime = (resultClientSaveTime / 1000) / 60;
        MainService.logger.info("Save Dictionary 100 records time = " + resultDictSaveTime + " m.s" +
                "\n Save Client 100000 records time = " + result);
    }

}

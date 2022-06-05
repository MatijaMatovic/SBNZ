package com.sbnz.physio.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.sbnz.template.CSVReader;
import com.sbnz.template.TherapyTemplateModel;

import org.drools.template.ObjectDataCompiler;
import org.springframework.stereotype.Service;

@Service
public class TherapyTemplateService {
	public void saveRulesInFile(String drl, String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(drl);
        writer.close();
    }

    public List<TherapyTemplateModel> getDataFromCSV(String filePath) {
        List<TherapyTemplateModel> data = new ArrayList<>();

        if (filePath.equals("")){
            //data.add(new ClassificationTemplateModel(10, 37, ClassificationTemplateModel.Category.BRONZE, ClassificationTemplateModel.Category.SILVER));
            //data.add(new ClassificationTemplateModel(18, 37, ClassificationTemplateModel.Category.SILVER, ClassificationTemplateModel.Category.GOLD));
        } else {
            data = CSVReader.readTherapyTemplateData(filePath);
        }
        return data;
    }

    public String insertDataIntoTemplate(List<?> data, InputStream template) {
        ObjectDataCompiler converter = new ObjectDataCompiler();
        return converter.compile(data, template);
    }

    public String generateRules(String templateFilePath, String dataFilePath) throws FileNotFoundException {
        InputStream template = new FileInputStream(templateFilePath);

        List<TherapyTemplateModel> data = getDataFromCSV(dataFilePath);

        String drl = insertDataIntoTemplate(data, template);

        System.out.println(drl);

        return drl;
    }

    public void generateRulesAndSaveThem(String templateFilePath, String dataFilePath, String resultFilePath) throws IOException {
        String drl = generateRules(templateFilePath, dataFilePath);
        saveRulesInFile(drl, resultFilePath);
    }
}

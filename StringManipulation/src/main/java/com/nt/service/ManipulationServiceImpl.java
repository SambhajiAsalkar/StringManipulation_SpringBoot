package com.nt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nt.model.MainModel;
import com.nt.model.Menu;
import com.nt.repo.MenuRepoImpl;

@Service
public class ManipulationServiceImpl implements IManipulationService{

    @Autowired
    private  MenuRepoImpl repo;

    private static final String DEFAULT_JSON = "{\"menu\": {\"id\": \"file\",\"value\": \"File\",\"popup\": {\"menuitem\": [{\"value\": \"New\",\"onclick\": \"CreateDoc()\"},{\"value\": \"Open\",\"onclick\": \"OpenDoc()\"},{\"value\": \"Save\",\"onclick\": \"SaveDoc()\"}]}}}";
    
    @Override
    public String manipulateJson(String replacements) {
        String jsonModel = DEFAULT_JSON;

        // Split replacements by comma
        String[] replacementArray = replacements.split(",");

        for (String replacement : replacementArray) {
            String[] keyValue = replacement.split(":::");
            if (keyValue.length == 2) {
                String key = keyValue[0].replace("\"", "");
                String value = keyValue[1].replace("\"", "");
                jsonModel = jsonModel.replace(key, value);
            }
        }

        return jsonModel;
    }
    @Transactional
    public MainModel saveJsonModel(String jsonModel) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Convert JSON string to Menu object
            Menu menu = objectMapper.readValue(jsonModel, Menu.class);

            MainModel mainModel = new MainModel();
            mainModel.setMenu(menu);

            return repo.insertMenu(mainModel);
        } catch (Exception e) {
            throw new RuntimeException("Error converting JSON to Menu object", e);
        }
    }
}


package com.literaturaPhatos.literaturaAPI.main;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.literaturaPhatos.literaturaAPI.service.ConsumeAPI;
import com.literaturaPhatos.literaturaAPI.service.Conversor;

public class Main {
    private final String URL_BASE = "https://gutendex.com/books/?page=1";
    private Conversor conversor = new Conversor();
    private ConsumeAPI consumeAPI = new ConsumeAPI();
    private final String MENU = """
            
            """;

    public void app() {
        String json = consumeAPI.getData(URL_BASE);
//        System.out.println(json);

        JsonObject jsonObject = consumeAPI.getDataResults(json);
        System.out.println(jsonObject.get("results"));
    }
}

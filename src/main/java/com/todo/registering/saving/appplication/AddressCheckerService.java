package com.todo.registering.saving.appplication;

import com.todo.common.annotations.DomainService;
import com.todo.registering.saving.appplication.commands.CheckAddressCommand;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@DomainService
public class AddressCheckerService {
    private final Logger logger = LogManager.getLogger(AddressCheckerService.class);
    private final OkHttpClient okHttpClient = new OkHttpClient();
    private final String requestPrefix = "https://restcountries.eu/rest/v2/name/";

    public boolean checkIfCountryExists(CheckAddressCommand checkAddressCommand){
        Request request = new Request.Builder()
                .url(requestPrefix + checkAddressCommand.getAddress().getCountry())
                .build();
        try {
            return okHttpClient.newCall(request).execute().code() == 200;
        } catch (IOException e) {
            logger.warn("Couldn't get response from Rest Countries API" + e.getMessage());
            return false;
        }

    }

}

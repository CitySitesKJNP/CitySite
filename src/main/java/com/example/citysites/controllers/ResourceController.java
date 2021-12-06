package com.example.citysites.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ResourceController {

    @Value("${mapbox.api.token}")
    private String mapboxKey;

    @Value("${weather.api.token}")
    private String weatherKey;

    @GetMapping(path = "/keys.js", produces = "application/javascript")
    @ResponseBody
    public String apiKey() {
        return "const MAPBOX_API_TOKEN_PERSONAL = '" + mapboxKey + "'\n" +
                "const OPEN_WEATHER_API_KEY = '" + weatherKey + "'";
    }
}

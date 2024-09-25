package org.codegym.demomvc.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.codegym.demomvc.response.WeatherResponse;
import org.codegym.demomvc.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WeatherController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/weather/{city}")
    public String getWeather(@PathVariable String city, Model model) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=Danang&appid=02e3323f29bc461c2346db2fe3989729";
        String weather = apiService.getData(url);

        // convert string to object
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        WeatherResponse weatherResponse = gson.fromJson(weather, WeatherResponse.class);

        int temp = (int) (weatherResponse.getMain().getTemp() - 273);
        model.addAttribute("temp", temp);
        String icon = "https://openweathermap.org/img/wn/" + weatherResponse.getWeather().get(0).getIcon() + "@4x.png";
        model.addAttribute("iconUrl", icon);
        return "weather";
    }
}

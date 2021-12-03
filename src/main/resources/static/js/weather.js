$.get("http://api.openweathermap.org/data/2.5/onecall", {
        appid: OPEN_WEATHER_API_KEY,
        lat: 29.424349,
        lon: -98.491142,
        units: 'imperial',
}).done(function (data) {

    $("#weather-cards").html("");
    data.daily.forEach(function (dailyWeather, index) {
        var dayOfWeek = new Date(dailyWeather.sunrise * 1000);
        var displayDay = (new Intl.DateTimeFormat('en-US', {weekday: 'long'}).format(dayOfWeek));

        if (index > 4) {
            return;
        }

        $("#weather-cards").append('<article class="card col-2 bg-light m-3">' + '<div>' +
            '<div class="card-header">' + displayDay + '</div>' +
            '<div class="card-body">' +
            '<p class="card-text">' + '<img src="http://openweathermap.org/img/wn/' + dailyWeather.weather[0].icon + '@2x.png"/>' + '</p>' +
            '<p class="card-text">' + dailyWeather.temp.min + "°F" + " - " + dailyWeather.temp.max + "°F" + '</p>' +
            '</div>' +
            '<ul class="list-group list-group-flush">' +
            '<li class="list-group-item">Description: <span class="description"><strong>' + dailyWeather.weather[0].description + '</strong></span></li>' +
            '<li class="list-group-item">Humidity: <span class="humidity"><strong>' + dailyWeather.humidity + '</strong></span></li>' +
            '<li class="list-group-item">Wind: <span class="wind"><strong>' + windCardinalDirection(dailyWeather.wind_deg) + '</strong></span></li>' +
            // '<li class="list-group-item">Pressure: <span class="pressure"><strong>' + dailyWeather.pressure + '</strong></span></li>' +
            '</ul>' +
            '</div>' + '</article>');
    });
});

function windCardinalDirection(degrees){
    let cardinalDirection = '';
    if ((degrees > 348.75 && degrees <= 360) || (degrees >=0 && degrees <= 11.25)){
        cardinalDirection = "N";
    } else if (degrees > 11.25 && degrees  <= 33.75) {
        cardinalDirection = "NNE";
    } else if (degrees > 33.75 && degrees <= 56.25) {
        cardinalDirection = "NE";
    } else if (degrees > 56.25 && degrees <= 78.75) {
        cardinalDirection = "ENE";
    } else if (degrees > 78.75 && degrees <= 101.25) {
        cardinalDirection = "E";
    } else if (degrees > 101.25 && degrees <= 123.75) {
        cardinalDirection = "ESE";
    } else if (degrees > 123.75 && degrees <= 146.25) {
        cardinalDirection = "SE";
    } else if (degrees > 146.25 && degrees <= 168.75) {
        cardinalDirection = "SSE";
    } else if (degrees > 168.75 && degrees <= 191.25) {
        cardinalDirection = "S";
    } else  if (degrees > 191.25 && degrees <= 213.75) {
        cardinalDirection = "SSW";
    } else if (degrees > 213.75 && degrees <= 236.25)  {
        cardinalDirection = "SW";
    } else if (degrees > 236.25 && degrees <= 258.75) {
        cardinalDirection = "WSW";
    } else if (degrees > 258.75 && degrees <= 281.25) {
        cardinalDirection = "W";
    } else if (degrees > 281.25 && degrees <= 303.75) {
        cardinalDirection = "WNW";
    } else if (degrees > 303.75 && degrees <= 326.25) {
        cardinalDirection = "NW";
    } else if (degrees > 326.75 && degrees <= 348.75) {
        cardinalDirection = "NNW";
    }
    return cardinalDirection;
}
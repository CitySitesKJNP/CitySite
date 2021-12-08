$.get("https://api.openweathermap.org/data/2.5/onecall", {
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

        $("#weather-cards").append(
            '<div class="card col-6 col-sm-3 col-md-2 col-lg-2" style="background: black; margin: 5px; height: 145px">' +
            '<div class="card-header d-flex justify-content-center" style="color: #f4cdc1">' + displayDay + '</div>' +
            '<div class="card-body d-flex justify-content-center">' +
                '<p class="card-text">' + '<img src="http://openweathermap.org/img/wn/' + dailyWeather.weather[0].icon + '@2x.png"/>' + '</p>' +
                '<p class="card-text" style="color: #f4cdc1">' + dailyWeather.temp.min + "°F" + " - " + dailyWeather.temp.max + "°F" + '</p>' +
            '</div>' +
            '</div>');
    });
});

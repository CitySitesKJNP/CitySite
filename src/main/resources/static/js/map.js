var map;
var service;
var infowindow;
// var userInput = $('#userInput');
var userInput = document.getElementById('userInput');


// This example adds a search box to a map, using the Google Place Autocomplete
// feature. People can enter geographical searches. The search box will return a
// pick list containing a mix of places and predicted search terms.
// This example requires the Places library. Include the libraries=places
// parameter when you first load the API. For example:
// <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places">
function initAutocomplete() {
    // mapboxgl.accessToken = MAPBOX_API_TOKEN_PERSONAL;

    map = new google.maps.Map(document.getElementById("map"), {
        mapId: "2b964882efa1deff",
        center: { lat: 29.424349, lng: -98.491142 },
        zoom: 11,
        mapTypeId: "roadmap",
    });


        var request = $.ajax({'url': '/api/map'});
        request.done(function (activities) {
            // console.log(activities[1].activityReviews[0].rating)
            var popup = '';
            Promise.all(
                activities.map(function (activity) {
                    return new Promise(((resolve, reject) => {
                        reverseGeocode({lat: activity.latitude, lng: activity.longitude}, MAPBOX_API_TOKEN_PERSONAL).then(function (addresses){
                            resolve(addresses);
                        })
                    }))
                })
            ).then(function (results){
                // console.log(activities[1].activityReviews[0].rating)
                // console.log(results[0]);
                // console.log(activities[0].id)
                // for (var i = 0; i <= activities.length; i++) {
                //     console.log(activities[i]);
                // }
                activities.forEach(function (currentActivity, index) {
                    // console.log(currentActivity.activityReviews[0].rating)
                    currentActivity.address = results[index];
                })
                activities.forEach(function (activity, index) {
                    var marker = new google.maps.Marker({
                        map: map,
                        position: {lat: activity.latitude, lng: activity.longitude}
                    })

                    // Activity Image in Popup

                    var imageLink;

                    if (activity.activityImages[0]) {
                        imageLink = activity.activityImages[0].imageUrl
                    } else {
                        imageLink = "https://st3.depositphotos.com/23594922/31822/v/600/depositphotos_318221368-stock-illustration-missing-picture-page-for-website.jpg";
                    }

                    // Activity Review in Popup
                    // Work in Progress: If no reviews, return "No Review(s) Found"
                    var reduceFxn;
                    if (activity.activityReviews[0]) {
                        reduceFxn = activity.activityReviews.reduce(function (accumulator, currentValue) {
                            accumulator += currentValue.rating;
                            return accumulator;
                        }, 0) / activity.activityReviews.length
                    } else {
                        reduceFxn = "No Review(s) Found";
                    }

                    // Popup

                    popup += '<div class="popupText">';
                    // First image for Activity
                    popup += `<img class="popupImages" src="${imageLink}">`;
                    popup += '<h1>' + activity.name + '</h1>';
                    popup += '<p style="font-size: 18px">' + activity.address + '</p>';
                    // Average of review for Activity (1-5). Maybe use filter?
                    // console.log(activity.activityReviews[0].rating)
                    // console.log(activity.activityReviews[0])
                    // console.log(activity.activityReviews[0] === undefined)
                    // console.log("Index = " + index);
                    popup += '<p style="font-size: 16px">' + "Average Rating: " + reduceFxn + '</p>';
                    popup += `<a style="font-size: 15px" href="/activity/${activity.id}"><button class="buttonStyle">Details for Activity</button></a>`;
                    popup += '</div>';

                    var infoWindow = new google.maps.InfoWindow({
                        content: popup
                    })

                    marker.addListener("click", () => {
                        infoWindow.open({
                            anchor: marker,
                            map,
                            shouldFocus: false
                        })
                    });

                    popup = '';
                });
            });
        })

    // Create the search box and link it to the UI element.
    const input = document.getElementById("pac-input");
    const searchBox = new google.maps.places.SearchBox(input);

    map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
    // Bias the SearchBox results towards current map's viewport.
    map.addListener("bounds_changed", () => {
        searchBox.setBounds(map.getBounds());
    });


    let markers = [];

    // Listen for the event fired when the user selects a prediction and retrieve
    // more details for that place.
    searchBox.addListener("places_changed", () => {
        const places = searchBox.getPlaces();

        if (places.length == 0) {
            return;

        }

        // Clear out the old markers.
        markers.forEach((marker) => {
            marker.setMap(null);
        });
        markers = [];

        // For each place, get the icon, name and location.
        const bounds = new google.maps.LatLngBounds();

        places.forEach((place) => {
            if (!place.geometry || !place.geometry.location) {
                console.log("Returned place contains no geometry");
                return;
            }

            const icon = {
                url: place.icon,
                size: new google.maps.Size(71, 71),
                origin: new google.maps.Point(0, 0),
                anchor: new google.maps.Point(17, 34),
                scaledSize: new google.maps.Size(25, 25),
            };

            // Create a marker for each place.
            markers.push(
                new google.maps.Marker({
                    map,
                    icon,
                    title: place.name,
                    position: place.geometry.location,
                })
            );
            if (place.geometry.viewport) {
                // Only geocodes have viewport.
                bounds.union(place.geometry.viewport);
            } else {
                bounds.extend(place.geometry.location);
            }
        });
        map.fitBounds(bounds);
    });

}



function searchInput() {
    geocode(userInput.value, MAPBOX_API_TOKEN_PERSONAL).then(function (result) {

        console.log(result);
        longitude = result[0];
        latitude = result[1];
        var marker = new mapboxgl.Marker({draggable: true})
            .setLngLat([longitude, latitude])
            .addTo(map);
        // marker.on('dragend', onDragEnd);
        // $('#currentCity').html(userInput.val());
        // map.flyTo({
        //     center: [longitude, latitude],
        //     zoom: 9
        // });

    });

}


const intervalId = setInterval(function() {
    if (document.querySelector('#map #pac-input')) {
        clearInterval(intervalId);
        setTimeout(function() {
            var input = document.querySelector("#map #pac-input");
            input.focus();
            google.maps.event.trigger(input, 'keydown', { keyCode: 13 });
        }, 100);
    }
}, 10);

// separate script or inbeded on html page


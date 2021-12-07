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
                activities.forEach(function (currentActivity, index) {
                    currentActivity.address = results[index];
                })
                activities.forEach(function (activity) {
                    var marker = new google.maps.Marker({
                        map: map,
                        position: {lat: activity.latitude, lng: activity.longitude}
                    })

                    popup += '<div>';
                    // First image for Activity
                    // popup += `<img src="${}">`;
                    popup += '<h1>' + "Name: " + activity.name + '</h1>';
                    popup += '<p>' + "Address: " + activity.address + '</p>';
                    // Average of review for Activity (1-5)
                    popup += '<p>' + "Review: " +  + '</p>';
                    popup += `<a href="/activity/${activity.id}">Details for Activity</a>`;
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


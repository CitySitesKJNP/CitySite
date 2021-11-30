var map;
var service;
var infowindow;
var geocoder;

// function initMap() {
//     // var san_antonio = new google.maps.LatLng(29.424349, -98.491142);
//
//     infowindow = new google.maps.InfoWindow();
//
//     // map = new google.maps.Map(
//     //     document.getElementById('map'), {center: san_antonio, zoom: 11});
// }

// function codeAddress() {
//     geocoder.geocode({
//         componentRestrictions: {
//             country: 'US',
//             postalCode: '78000, 78299'
//         }
//     }, function(results, status) {
//         if (status == 'OK') {
//             map.setCenter(results[0].geometry.location);
//             var marker = new google.maps.Marker({
//                 map: map,
//                 position: results[0].geometry.location
//             });
//         } else {
//             window.alert('Geocode was not successful for the following reason: ' + status);
//         }
//     });
//     console.log(codeAddress(status));
// }

// This example adds a search box to a map, using the Google Place Autocomplete
// feature. People can enter geographical searches. The search box will return a
// pick list containing a mix of places and predicted search terms.
// This example requires the Places library. Include the libraries=places
// parameter when you first load the API. For example:
// <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places">
function initAutocomplete() {
    geocoder = new google.maps.Geocoder;

    const map = new google.maps.Map(document.getElementById("map"), {
        mapId: "2b964882efa1deff",
        center: { lat: 29.424349, lng: -98.491142 },
        zoom: 11,
        mapTypeId: "roadmap",
    });


        var request = $.ajax({'url': '/api/map'});
        request.done(function (activities) {
            var popup = '';
            activities.forEach(function (activity) {
                var marker = new google.maps.Marker({
                    map: map,
                    position: {lat: activity.latitude, lng: activity.longitude}
                })

                // Under Construction...
                // Current Error: Geocoder failed due to: MapsRequestError: GEOCODER_GEOCODE: OVER_QUERY_LIMIT: The webpage has gone over the requests limit in too short a period of time.

                // setTimeout(function () {
                //     geocoder.geocode({location: {lng: activity.longitude, lat: activity.latitude}}).then((response) => {
                //         if (response.results[0]) {
                //             popup += '<div>';
                //             popup += '<h1>' + "Name: " + activity.name + '</h1>';
                //             popup += '<p>' + "Location: " + response.results[0] + '</p>';
                //             popup += '<p>' + "Activity ID: " + activity.id + '</p>';
                //             popup += '</div>';
                //         } else {
                //             popup += '<div>';
                //             popup += '<h1>' + "Name: " + activity.name + '</h1>';
                //             popup += '<p>' + "Longitude: " + activity.longitude + '</p>';
                //             popup += '<p>' + "Latitude: " + activity.latitude + '</p>';
                //             popup += '<p>' + "Activity ID: " + activity.id + '</p>';
                //             popup += '</div>';
                //         }
                //     })
                // }, 1000);

                popup += '<div>';
                popup += '<h1>' + "Name: " + activity.name + '</h1>';
                popup += '<p>' + "Longitude: " + activity.longitude + '</p>';
                popup += '<p>' + "Latitude: " + activity.latitude + '</p>';
                popup += '<p>' + "Activity ID: " + activity.id + '</p>';
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
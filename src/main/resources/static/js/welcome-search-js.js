var userInput = $('#userInput');

function searchInput() {
    geocode(userInput.val(), MAPBOX_API_TOKEN_PERSONAL).then(function (result) {
        console.log(result);
        longitude = result[0];
        latitude = result[1];
        var marker = new mapboxgl.Marker({draggable: true})
            .setLngLat([longitude, latitude])
            .addTo(map);
        marker.on('dragend', onDragEnd);
        $('#currentCity').html(userInput.val());
        map.flyTo({
            center: [longitude, latitude],
            zoom: 9
        });

    });

}
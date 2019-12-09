var placesAutocompleteStart = places({
    container: document.querySelector('#cityStart'),
    type: 'city',
    countries: ['fr'],
    aroundLatLngViaIP: false,
    appId: 'pl1NXUFWIA0R',
    apiKey: 'bce9e1e617ea94cbfce98b58c3725115',
    templates: {
        value: function(suggestion) {
            return suggestion.name;
        }
    }
});

var placesAutocompleteStop = places({
    container: document.querySelector('#cityStop'),
    type: 'city',
    countries: ['fr'],
    aroundLatLngViaIP: false,
    appId: 'pl1NXUFWIA0R',
    apiKey: 'bce9e1e617ea94cbfce98b58c3725115',
    templates: {
        value: function(suggestion) {
            return suggestion.name;
        }
    }
});

var placesAutocompletePosition = places({
    container: document.querySelector('#cityPosition'),
    type: 'city',
    countries: ['fr'],
    aroundLatLngViaIP: false,
    appId: 'pl1NXUFWIA0R',
    apiKey: 'bce9e1e617ea94cbfce98b58c3725115',
    templates: {
        value: function(suggestion) {
            return suggestion.name;
        }
    }
});

placesAutocompleteStart.on('change', e => {
    var coordinates = e.suggestion.latlng;
    if(document.getElementById('lat') !== undefined) {
        document.getElementById('lat').value = coordinates.lat;
        document.getElementById('lng').value = coordinates.lng;
    }

    document.getElementById('start').value = e.suggestion.name;

});


placesAutocompleteStop.on('change', e => {
    document.getElementById('stop').value = e.suggestion.name;
});


placesAutocompletePosition.on('change', e => {
    var coordinates = e.suggestion.latlng;
    if(document.getElementById('latPos') !== undefined) {
        document.getElementById('latPos').value = coordinates.lat;
        document.getElementById('lngPos').value = coordinates.lng;
    }
});
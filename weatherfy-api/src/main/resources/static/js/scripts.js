window.onload = function() {
	document.getElementById("choice-method").value = "";
};

function showForm() {
	let option = document.getElementById("choice-method").value;
	if (!option) {
		document.getElementById("div-name").style.display = "none";
		document.getElementById("div-lat-and-long").style.display = "none";
	} else if (option === "NAME") {
		document.getElementById("div-name").style.display = "";
		document.getElementById("div-lat-and-long").style.display = "none";
	} else if (option === "LAT_AND_LONG") {
		document.getElementById("div-name").style.display = "none";
		document.getElementById("div-lat-and-long").style.display = "";
	}
}

function getSongsByCityName() {
	let cityName = document.getElementById("city-name").value;
	if (isEmpty(cityName)) {
		document.getElementById("name-alert").style.display = "";
	} else {
		document.getElementById("name-alert").style.display = "none";
		getSongs('/city/' + cityName);
	}
}

function getSongsByLatAndLon() {
	let latitude = document.getElementById("latitude").value;
	let longitude = document.getElementById("longitude").value;
	if (isEmpty(latitude) || isEmpty(longitude)) {
		document.getElementById("lat-and-lon-alert").style.display = "";
	} else {
		document.getElementById("lat-and-lon-alert").style.display = "none";
		getSongs('/lat/' + latitude + '/lon/' + longitude);
	}
}

function isEmpty(value) {
	return !value || value.trim().lenght === 0;
}

function getSongs(url) {
	axios({
	  method: 'get',
	  url: '/songs' + url,
	  responseType: 'json'
	}).then(response => {
		document.getElementById("div-songs").style.display = "";
		document.getElementById("songs-alert").style.display = "none";
		alert("Deu certo");
	}).catch(error => {
		document.getElementById("div-songs").style.display = "";
		document.getElementById("songs-alert").style.display = "";
		document.getElementById("msg-error").innerHTML = error;
	});
}
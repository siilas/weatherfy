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
	document.getElementById("loading").style.display = "";
	axios({
	  method: 'get',
	  url: '/songs' + url,
	  responseType: 'json'
	}).then(response => {
		document.getElementById("loading").style.display = "none";
		document.getElementById("div-songs").style.display = "";
		document.getElementById("songs-alert").style.display = "none";
		setSongs(response.data);
		document.getElementById("table-songs").style.display = "";
	}).catch(error => {
		document.getElementById("loading").style.display = "none";
		document.getElementById("div-songs").style.display = "";
		document.getElementById("songs-alert").style.display = "";
		if (error.response) {
			document.getElementById("msg-error").innerHTML = error.response.data.message + " (" + error.response.status + ")";
		} else {
			document.getElementById("msg-error").innerHTML = error;
		}
		document.getElementById("table-songs").style.display = "none";
	});
}

function setSongs(songs) {
	if (songs.message) {
		document.getElementById("table-title").innerHTML = songs.message
	} else {
		document.getElementById("table-title").innerHTML = songs.city + " / " 
			+ songs.latitude + ", " + songs.longitude + " / "
			+ songs.temperature + "° / " 
			+ songs.genre;
	}
	let html = "";
	songs.tracks.forEach(function(song) {
		html += "<tr>"
			+ "	<td>"
			+ song.name
			+ "	</td>"
			+ "	<td>"
			+ getArtistsNames(song.artists)
			+ "	</td>"
			+ "	<td>"
			+ "		<a href=\"" + song.external_urls.spotify + "\" target=\"_blank\">"
			+ "			<img src=\"images/spotify.png\" width=\"32\" height=\"32\" title=\"Ouvir\" />"
			+ "		</a>"
			+ "	</td>"
			+ "</tr>";
	});
	document.getElementById("songs").innerHTML = html;
}

function getArtistsNames(artists) {
	let name = [];
	artists.forEach(function(artist) {
		name.push(artist.name);
	});
	return name.join(", ");
}

function verifySubmit(event, form) {
	let submit = (event.keyCode ? event.keyCode : event.which) == 13;
	if (submit && ("NAME" === form)) {
		getSongsByCityName();
	} else if (submit && ("LAT_AND_LONG" === form)) {
		getSongsByLatAndLon();
	} 
	if (submit) {
		event.preventDefault();
		return false;
	}
}
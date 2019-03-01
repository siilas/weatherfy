function showForm() {
	let option = document.getElementById("choice-method").value;
	if (!option) {
		document.getElementById("form-name").style.display = "none";
		document.getElementById("form-lat-and-long").style.display = "none";
	} else if (option === "NAME") {
		document.getElementById("form-name").style.display = "";
		document.getElementById("form-lat-and-long").style.display = "none";
	} else if (option === "LAT_AND_LONG") {
		document.getElementById("form-name").style.display = "none";
		document.getElementById("form-lat-and-long").style.display = "";
	}
}
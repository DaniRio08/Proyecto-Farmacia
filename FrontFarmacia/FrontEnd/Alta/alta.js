//Función que vuelve a la página de gestión
function gestio() {
    window.location.href = "../Gestio/gestio.html";
}

//Función que carga en el select todos los pacientes de la base de datos usando un JSON que se transforma en una lista
function getPatients() {
    var http = new XMLHttpRequest();

    let mail = window.sessionStorage.getItem("mail");
    let session = window.sessionStorage.getItem("session");

    http.open("GET", "http://localhost:3000/ProjecteEntorns/ServPatients?mail="+mail+"&session="+session, true);
    http.send();

    http.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            let jsonString = http.responseText;
            let pacientes = JSON.parse(jsonString);

            for (let i = 0; i < pacientes.length; i++) {
                let select = document.getElementById("pacient");
                let option = document.createElement("option");
                option.text = pacientes[i];
                option.value = pacientes[i];
                select.add(option);
            }
        }
    }
}

/*Función que carga las opciones de un select con todos los medicamentos que se encuentran en la base de datos usando recibiendo
un JSON por parte del backend*/
function getMedicines() {
    var http = new XMLHttpRequest();

    let mail = window.sessionStorage.getItem("mail");
    let session = window.sessionStorage.getItem("session");

    http.open("GET", "http://localhost:3000/ProjecteEntorns/ServMedicines?mail="+mail+"&session="+session, true);
    http.send();

    http.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            let jsonString = http.responseText;
            let medicinas = JSON.parse(jsonString);

            for (let i = 0; i < medicinas.length; i++) {
                let select = document.getElementById("medicament");
                let option = document.createElement("option");
                option.text = medicinas[i].name;
                option.value = medicinas[i].id;
                select.add(option);
            }
        }
    }
}

//Función que envía todos los valores del input para hacer una inserción en la tabla de chips asociada al doctor que está logeado
function enviar(event) {
    event.preventDefault(); //evita la recarga de la página porque sino los mensajes de error o "alta correcta" se borran
    var http = new XMLHttpRequest();

    let mail = window.sessionStorage.getItem("mail");
    let session = window.sessionStorage.getItem("session");
    let idXip = document.getElementById("idxip").value;
    let idMed = document.getElementById("medicament").value;
    let mailP = document.getElementById("pacient").value;
    let date = document.getElementById("limit").value;

    http.open("POST", "http://localhost:3000/ProjecteEntorns/Release", true);
    http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    http.send("mail="+mail+"&session="+session+"&idXip="+idXip+"&idMed="+idMed+"&mailP="+mailP+"&date="+date);

    http.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            let insercion = http.responseText;
            if (insercion == "ok") {
                document.getElementById("resultado").innerHTML = "<p style='color:green'>Alta correcta</p>";
                document.getElementById("formulario").reset(); //Restablece los valores del formulario
            } else {
                document.getElementById("resultado").innerHTML = "<p style='color:red'>Ha ocurrido algún error</p>";
                document.getElementById("formulario").reset(); //Restablece los valores del formulario
            }
        }
    }
}

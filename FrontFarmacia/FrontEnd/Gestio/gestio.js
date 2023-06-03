//Función que elimina los elementos del sessionStorage y vuelve a la página del Login sin opción a volver hacia atrás
function LogOut() {
    window.sessionStorage.removeItem("mail");
    window.sessionStorage.removeItem("session");
    window.location.replace("../Login/login.html")
}

//Función que cambia a la página de alta
function Alta() {
    window.location.href = "../Alta/alta.html";
}

/*Función que envia el mail y la sesión guardados en el session storage al servlet que comprueba que el doctor esté logeado 
y devuelve un string html con los datos de la tabla xip*/
function mostrarTaula() {
    var http = new XMLHttpRequest();

    let mail = window.sessionStorage.getItem("mail");
    let session = window.sessionStorage.getItem("session");

    http.open("GET", "http://localhost:3000/ProjecteEntorns/ServXips?mail="+mail+"&session="+session, true);
    http.send();

    http.onreadystatechange = function(){
        if (this.readyState == 4 && this.status == 200) {
            let html = http.responseText;
            if (html == "notLogged") {
                document.getElementById("taula").innerHTML = "<p style='color:red'>Vuelve a iniciar sesión, no estás loggeado</p>";
            } else if (html != null) { //Si el string no es null significa que la respuesta ha sido correcta y se añade al html
                document.getElementById("taula").innerHTML = html;
            } else {
                document.getElementById("taula").innerHTML = "<p style='color:red'>Error al cargar la tabla de xips</p>";
            }
        }
    }
}

function cambiarImagen(over) {
    var img = document.getElementById("logout");
    if (over) {
        img.src = "../Imagenes/logoutB.png"; // Ruta de la nueva imagen
    } else {
        img.src = "../Imagenes/logout.png"; // Ruta de la imagen original
    }
}
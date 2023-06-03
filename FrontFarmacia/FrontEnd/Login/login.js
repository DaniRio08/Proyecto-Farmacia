//Función que envía el mail y la contraseña al servlet, donde después se comprueba si es correcto y se devuelve un número de sesión
function enviarGET() {
    var http = new XMLHttpRequest();

    //Se cogen los valores de los input
    let mail = document.getElementById("email").value;
    let pass = document.getElementById("pass").value;

    //Se especifica el servlet
    http.open("GET", "http://localhost:3000/ProjecteEntorns/Login?mail="+mail+"&pass="+pass, true);
    http.send();

    //Cuando se complete la respuesta se hace el proceso
    http.onreadystatechange = function(){
        if (this.readyState == 4 && this.status == 200) {
            let session = this.responseText;
            if (session != 0) {
                //Se guarda tanto el mail como la sesión obtenida en el sessionStorage para poder utilizarlos más adelante
                window.sessionStorage.setItem("mail", mail);
                window.sessionStorage.setItem("session", session);
                window.location.href = "../Gestio/gestio.html";
            } else {
                document.getElementById("resultat").innerHTML = "Email o contraseña no válidas";
            }
        }
    }
}
function limpiarInput() {
    document.getElementById("email").value = "";
    document.getElementById("pass").value = "";
}
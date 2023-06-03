# Proyecto Farmacia


## Objetivo
El objetivo de este trabajo es realizar una aplicación sencilla que combine todo lo aprendido durante el curso, usando java para el BackEnd, MariaDB para la base de datos y HTML, CSS y JavaScript para el FrontEnd. La idea del proyecto era continuar con el trabajo del cual hicimos la documentación durante el segundo trimestre sobre una farmacia que necesitaba una aplicación para asegurarse que sus medicamentos se mantuviesen en buenas condiciones. Este proyecto es solo una parte de lo que se propuso en la documentación, y el resultado es una aplicación que permite hacer login a los doctores para después mostrarles una tabla con todos los medicamentos y pacientes asociados a ellos y también la posibilidad de añadir nuevas filas a la tabla.

## Distribución de las carpetas
- **FrontFarmacia** --> Contiene los archivos html, css y JavasScript en la subcarpeta "FrontEnd" y además contiene los scripts para crear e insertar los datos (simplemente con la intención de hacer pruebas) en la base de datos en la subcarpeta "ScriptsDB".
- **ProjecteEntorns** --> Contiene todo lo relacionado con el BackEnd (clases utilizadas y servlets con los cuales se comunica el JavaScript).


___

## Estructura de la base de datos

#### Tabla: doctor
- mail: clave primaria, varchar(50)
- pass: varchar(100) (contraseña) 
- name: varchar(100) (nombre completo del doctor)
- last_log: date (yyyy-mm-dd)
- session: bigint(10)

#### Tabla: patient
- mail: clave primaria, varchar(50)
- name: varchar(100) (nombre del paciente)

#### Tabla: medicine
- med_id: int, autoincremental (clave primaria)
- name: varchar(50) (nombre del medicamento)
- tmax: int (temperatura máxima)
- tmin: int (temperatura mínima)

#### Tabla: xip
- xip_id: int(10), clave primaria
- doctor_mail: varchar(50), clave foránea referenciando la columna mail en la tabla doctor
- id_medicine: int, clave foránea referenciando la columna med_id en la tabla medicine
- patient_mail: varchar(50), clave foránea referenciando la columna mail en la tabla patient
- end_date: date (yyyy-mm-dd)

___

## Estructura del BackEnd

#### Clase Persona (abstracta):
- Atributos:
    - String name: Nombre completo de la persona.
    - String mail: Correo electrónico.
- Constructores:
    - Vacío.
    - Con todos los parámetros.
- Métodos:
    - load(String id): Abstracto. No tiene retorno.

#### Clase Doctor (hereda de Persona):
- Atributos:
    - String pass: Código hash de la contraseña del doctor.
    - LocalDate lastLog: Fecha del último inicio de sesión del usuario.
    - String session: Código de 10 dígitos aleatorios que identifica la sesión del doctor. Debe ser único y, en caso de no realizar un inicio de sesión exitoso, debe ser nulo.
    - ArrayList releaseList: Array de Chips vinculados al doctor.
- Constructores:
    - Vacío.
    - Con todos los parámetros (excepto releaseList).
- Métodos:
    - login(String mail, String pass): No tiene retorno. Si el correo y la contraseña son correctos, carga los atributos del objeto desde la base de datos utilizando load(). También actualiza los atributos lastLog y session en la base de datos.
    - isLogged(String mail, String session): Retorna un booleano true si encuentra el correo con la sesión en la base de datos; carga los datos con login(). En caso contrario, retorna false.
    - load(String id): No tiene retorno. Carga en el objeto los datos del Doctor correspondientes a id=(BBDD: farmacia.doctor.mail).
    - loadReleaseList(): Carga en el array del Doctor todos los chips (que están en fecha) de la base de datos vinculados a él.
    - getTable(): Retorna un string que corresponde a una tabla HTML de todos los chips de alta, vigentes, del doctor.
    - getPatients(): Método que busca todos los pacientes en la base de datos y los devuelve en forma de JSON.
    - getMedicines(): Método que busca los medicamentos en la base de datos y devuelve un array de objetos JSON

#### Clase Paciente (hereda de Persona):
- Atributos: los que hereda de la clase persona, no tiene extra
- Constructores:
    - Vacío.
    - Con todos los parámetros.
- Métodos:
    - load(String id): No tiene retorno. Carga en el objeto los datos del Paciente correspondientes a id=BBDD.doctor.mail.

#### Clase Xip:
- Atributos:
    - int id: Identificador físico del Chip.
    - Medicine medicine. (objeto del tipo medicine)
    - Patient patient. (objeto del tipo patient)
    - Date: Fecha de finalización del uso del chip.
- Constructores:
    - Vacío.
    - Con todos los parámetros.
- Métodos:
    - load(int id): Carga los atributos desde la base de datos al objeto.

#### Clase Medicine:
- Atributos:
    - int id.
    - String name.
    - float Tmax: Temperatura máxima.
    - float Tmin: Temperatura mínima.
- Constructores:
    - Vacío.
    - Con todos los parámetros.
- Métodos:
    - load(int id): Carga los atributos desde la base de datos al objeto.

#### Clase Login (Servlet):
- doGet(): Obtiene los parámetros mail y pass, y llama al método login de Doctor. Retorna el valor session del doctor al frontend.

#### Clase ServeXips (Servlet):
- doGet(): Obtiene los parámetros mail y session. Ejecuta los métodos isLogged(), load(), loadRelease(), y getTable() de la clase Doctor, y retorna el String resultante.

#### Clase ServePatients (Servlet):
- doGet(): Obtiene los parámetros mail y session. Recupera de la base de datos la lista de pacientes y crea un array de strings con los correos de los pacientes y lo envía en formato JSON.

#### Clase ServeMedicines (Servlet):
- doGet(): Obtiene los parámetros mail y session. Recupera de la base de datos la lista de medicamentos y crea un array de objetos Medicine y lo envía en formato JSON.

#### Clase Release (Servlet):
- doPost(): Obtiene los parámetros mail, session, idXip, idMed, date y mailP. Inserta en la base de datos el nuevo registro en la tabla xip.

___

## Estructura del FrontEnd

#### Página: Login
**HTML:**
- Dos inputs, uno para el correo electrónico y otro para la contraseña.
- Botón para send().

**CSS:**
Estilo adecuado.

**JS:**
Función send():
> Servicio de backend: servlet Login.
GET, se envían los parámetros email y pass al backend.
En la respuesta del backend, si es nulo, el inicio de sesión no ha sido correcto.
De lo contrario, se obtiene un código de sesión que se debe almacenar en sessionStorage con la clave session.
Se almacena el correo electrónico en sessionStorage con la clave mail.
Una vez almacenada la clave de sesión, se avanza a la página Gestión.

![Imagen Login](/FrontFarmacia/FrontEnd/Imagenes/captura_login.jpg)

#### Página: Gestión
**HTML:**
- Botón para Alta (avanza a Alta).
- Botón para logOut().
- Tabla de todas las altas relacionadas con el doctor.

**CSS:**
Estilo adecuado.

**JS:**
Función logOut():
> Eliminar mail y session de sessionStorage.
Avanzar a Login.

Función getTable():
> Ejecutada por el evento onload.
Servicio de backend: servlet ServXips.
Método GET, se envían los parámetros mail y session de sessionStorage al backend.
La respuesta del backend es un HTML que corresponde a la tabla de Chips.

Función Alta():
> Avanza a la pñagina de altas

![Imagen Gestión](/FrontFarmacia/FrontEnd/Imagenes/captura_gestión.jpg)

#### Página: Alta
**HTML:**
- Input text para introducir el id físico del xip.
- Select con la lista de todos los pacientes.
- Select con la lista de todos los medicamentos.
- Input date para introducir la fecha límite para tomar el medicamento.
- Botón para enviar().
- Botón para avanzar a Gestión.

**CSS:**
Estilo adecuado.

**JS:**
Función getPatients():
> Ejecutada por el evento onload.
Servicio de backend: servlet ServPatients.
Método GET, se envían los parámetros mail y session de sessionStorage al backend.
La respuesta del backend es un array de strings que corresponden al correo de los pacientes en formato JSON.

Función getMedicines():
> Ejecutada por el evento onload.
Servicio de backend: servlet ServMedicines.
Método GET, se envían los parámetros mail y session de sessionStorage al backend.
La respuesta del backend es un array de medicamentos en formato JSON.


Función enviar():
> Servicio de backend: servlet Release().
Método POST, se envían los parámetros mail y session de sessionStorage, idXip, mailP del paciente, idMed del medicamento y date limit al backend.
La respuesta del backend es un mensaje con el resultado de la operación de alta

![Imagen Alta](/FrontFarmacia/FrontEnd/Imagenes/captura_alta.jpg)

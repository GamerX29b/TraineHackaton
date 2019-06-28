<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" session="false" %>

<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
<div>
    <table>
    <tr>
        <th>
            <label >
                id
            </label>
        </th>
        <th>
            <input id="idPerson"/>
        </th>
    </tr>

    <tr>
        <th>
            <label >
                Имя
            </label>
        </th>
        <th>
            <input id="namePerson"/>
        </th>
    </tr>
    <tr>
        <th>
            <label >
                Дата
            </label>
        </th>
        <th>
            <input type="date" id="davaToday">
        </th>
    </tr>
    </table>
    <button onclick="setPerson()">Отправить JSON</button>
    <label id = "responseLabel"/>
</div>
</body>
<script>
    document.getElementById('davaToday').valueAsDate = new Date();

    function setPerson (){
        var idValue = document.getElementById('idPerson').value;
        var nameValue = document.getElementById('namePerson').value;
        var dateThis = document.getElementById('davaToday').value;
        var json = {
            id:idValue,
            name:nameValue,
            birthdate:dateThis};
    $.ajax({
        type: "POST",
        url: "/person.html",
        data: JSON.stringify(json),
        cache: false,
        contentType: "application/json",
        dataType: 'json',
        success: function (response) {
            document.getElementById(responseLabel).setAttribute('value',"успех");
        },
        error: function (e) {
            document.getElementById(responseLabel).setAttribute('value',"неудача");
        }
    })}
</script>
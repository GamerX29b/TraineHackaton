<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" session="false" %>

<div>
    <tr>
        <td>
            <label >
                id
            </label>
        </td>
        <td>
            <form:input path="id"/>
        </td>
    </tr>

    <tr>
        <td>
            <label >
                Имя
            </label>
        </td>
        <td>
            <form:input path="name"/>
        </td>
    </tr>
    <tr>
        <td>
            <label >
                Дата
            </label>
        </td>
        <td>
            <form:input path="Date"/>
        </td>
    </tr>
    <label id = "responseLabel"/>
</div>
<script>
    var json = {"id":"1","name":"ivan","Date":"01.01.2019"}
    $.ajax({
        type: "POST",
        url: "/person.html",
        data: "jsonObject"+json,
        success: function (response) {
            document.getElementById(responseLabel).setAttribute('value',"успех");
        },
        error: function (e) {
            document.getElementById(responseLabel).setAttribute('value',"неудача");
        }
    })
</script>
$(document).ready(function () {

    $.ajax({
        url: '/oauth/clients',
        type: 'GET',
        headers: {'Authorization': 'Basic dXNlcjpwYXNzd29yZA=='},
        dataType: 'json',
        success: function (data) {
            for (var key in data) {
                $('#clients').append('<option value="' + key + '">' + data[key] + '</option>');
            }
        }
    })

    $('#submit').click(function () {
        $('#token').val('');
        $('#message').html('');
        $.ajax({
            url: '/oauth/tokens',
            type: 'GET',
            data: {clientId:$('#clients').val()},
            dataType: 'text',
            success: function (data, testStatus, jqXHR) {
                $('#token').val(data);
            }
        });
    });

    $('#token').click(function () {
        $(this).select();
        document.execCommand("copy");
        $('#message').html('已复制到剪切板');
    });
})

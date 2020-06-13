$(document).ready(function () {
    $('#submit').click(function () {
        $('#signature').val('');
        $('#encodeString').val('');
        $('#message').html('');
        var data = {
            'client_id': $('#clientId').val(),
            'request_url': $('#requestUrl').val(),
            'request_method': $('#requestMethod').val(),
            'request_body': $('#requestBody').val()
        };

        $.ajax({
            url: '/signature',
            type: 'POST',
            dataType: 'json',
            headers: {'Content-Type': 'application/json;charset=utf8', 'Authorization': 'Basic dXNlcjpwYXNzd29yZA=='},
            data: JSON.stringify(data),
            success: function (data) {
                $('#signature').val(data.signature);
                $('#encodeString').val(data.encode_string);
            }
        });
    });

    $('#signature').click(function () {
        $(this).select();
        document.execCommand("copy");
        $('#message').html('已复制到剪切板');
    });
})

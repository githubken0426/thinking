<!doctype html>
<html>
<head>
    <meta charset="utf-8"/>
    <link rel="icon" type="image/png" href="/img/favicon.ico">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

    <title>Signature Tool</title>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport'/>
    <meta name="viewport" content="width=device-width"/>

    <!-- Bootstrap core CSS     -->
    <link href="/css/bootstrap.min.css" rel="stylesheet"/>
    <!--  Bootstrap Table core CSS    -->
    <link href="/css/bootstrap-dashboard.css" rel="stylesheet"/>
    <!--     Fonts and icons     -->
    <link href="/css/bootstrap-theme.css" rel="stylesheet"/>

    <!--   Core JS Files   -->
    <script src="/js/jquery.3.2.1.min.js" type="text/javascript"></script>
    <script src="/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="/js/bootstrap-dashboard.js" type="text/javascript"></script>
    <script src="/js/signature.js" type="text/javascript"></script>
</head>
<body>

<div class="wrapper">
    <menu class="menu">
        <#include "menu.ftl"/>
    </menu>

    <div class="main-panel">
        <header class="menu">
            <#include "header.ftl"/>
        </header>

        <div class="content">
            <div class="container-fluid">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            Generate Signature
                        </h3>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">

                                <form role="form">
                                    <div class="form-group">
                                        <label for="name">请求方法</label>
                                        <select class="form-control" id="requestMethod">
                                            <option value="POST">POST</option>
                                            <option value="GET">GET</option>
                                            <option value="DELETE">DELETE</option>
                                            <option value="PUT">PUT</option>
                                            <option value="PATCH">PATCH</option>
                                            <option value="OPTIONS">OPTIONS</option>
                                            <option value="TRACE">TRACE</option>
                                            <option value="HEAD">HEAD</option>
                                            <option value="CONNECT">CONNECT</option>
                                        </select>
                                    </div>

                                    <div class="form-group">
                                        <label for="name">client ID</label>
                                        <input type="text" class="form-control" id="clientId" value="sina"
                                               placeholder="">
                                    </div>

                                    <div class="form-group">
                                        <label for="name">请求URL</label>
                                        <input type="text" class="form-control" id="requestUrl" placeholder="例如：/openapi/v1/users">
                                    </div>
                                    <div class="form-group">
                                        <label for="name">请求BODY</label>
                                        <textarea class="form-control" id="requestBody" rows="5"></textarea>
                                    </div>
                                    <div class="text-center">
                                        <button id="submit" type="button"
                                                class="btn btn-primary text-success text-center">提交
                                        </button>
                                    </div>
                                </form>

                            </div>
                        </div>

                    </div>
                </div>

                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">
                            返回结果
                        </h3>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="name">签名</label>
                                    <textarea class="form-control" id="signature" rows="5"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="row text-center">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="name" id="message"></label>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="name">加密字符串</label>
                                    <textarea class="form-control" id="encodeString" rows="5"></textarea>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>

            </div>

        </div>

        <footer class="footer">
            <#include "footer.ftl"/>
        </footer>

    </div>
</div>

</body>
</html>

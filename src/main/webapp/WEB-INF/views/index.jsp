<html>
<head>
    <script src="js/pager.js"></script>
    <script src="js/jquery-3.2.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <script>
        var page = location.search.split('page=')[1] ? location.search.split('page=')[1] : 0;
        var size = location.search.split('size=')[1] ? location.search.split('size=')[1] : 2;

    </script>
</head>
<body onload="loadPayments(page,size)">

<hr>
 <a href="payments">Payments</a>
<div class="panel panel-default" >
    <div class="panel-body" id="div1">A Basic Panel</div>
</div>
</body>
</html>

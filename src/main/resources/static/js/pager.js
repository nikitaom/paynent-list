function loadPayments(page, size) {



    // CSRF protection headers
    var csrfToken = $("meta[name='_csrf']").attr("content");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");

    // var data = JSON.stringify(search);
    var headers = {};

    // add CSRF protection headers
    headers[csrfHeader] = csrfToken;

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/api/paymentz?page="+page+"&size="+size,
        headers: headers,
        // data: data,
        dataType: 'json',
        timeout: 100000,
        success: function (data) {
            // console.log("SUCCESS: ", data);


            var tbl = $("<table/>").attr("id", "mytable");
            $("#div1").append(tbl);
            for (var i = 0; i < data["content"].length; i++) {
                // console.log("!!!1111! : ", data["content"][i]["links"]["href"]);
                // console.log("!!33333!! : ", data["content"][i]["links"][0]["href"]);

                var tr = "<tr>";
                var td1 = "<td>" + data["content"][i]["paymentId"] + "</td>";
                var td2 = "<td>" + data["content"][i]["paymentAmount"] + "</td>";
                var td3 = "<td>" + data["content"][i]["paymentDateAsString"] + "</td> ";
                var td4 = "<td>" + data["content"][i]["channel"] + "</td> ";
                var td5 = "<td><a href='" + data["content"][i]["_links"]["self"]["href"] + "'>Payment</a></td></tr>";

                // console.log("!!!!1111 : ", data["content"][i]["_links"]);
                // console.log("!!!!222 : ", data["content"][i]["_links"][0]);
                // console.log("!!!!3333 : ", data["_links"]["next"]);
                // console.log("!!!!444 : ", data["_links"]["next"]["href"]);
                // var td5 = "a";
                $("#mytable").append(tr + td1 + td2 + td3 + td4 + td5);


            }

            var next = "<a href='" + data["_links"]["next"]["href"] + "'>Next</a>";
            $("#div1").append("<br>");
            $("#div1").append(next);

            var last = "<a href='" + data["_links"]["last"]["href"] + "'>Last</a>";
            $("#div1").append("<br>");
            $("#div1").append(last);

            var first = "<a href='" + data["_links"]["first"]["href"] + "'>First</a>";
            $("#div1").append("<br>");
            $("#div1").append(first);


        },
        error: function (e) {
            console.log("ERROR: ", e);
        },
        done: function (e) {
            console.log("DONE");
        }
    });


}

/*
$(document).on("click", "#tab1", function () {
    console.log("Debug");

    $.get("/team", "owner", function (response) {
        $("#tab1").text(response);
    });
});

$(document).on("click", "#tab2", function () {
    console.log("Debug");

    $.get("/team", "member", function (response) {
        $("#tab2").text(response);
    });
});
*/

$(document).on("click", "#owner-link", function () {
    $.get("/team", "owner");
});

$(document).on("click", "#member-link", function () {
    $.get("/team", "member");
});
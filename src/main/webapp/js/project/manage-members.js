$(document).on("click", "#add-to-project", function () {
    $.post(`/join-project`);
    location.reload();
});

$(document).on("click", "#leave-project", function () {
    $.post(`/leave-project`);
    location.reload();
});
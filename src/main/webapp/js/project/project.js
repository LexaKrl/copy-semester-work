$(document).on("click", "#get-all-posts", function () {
    $.get(`/team/${teamId}/project/${project.id}`, "get-all-posts");
});

$(document).on("click", "#get-assignee-to-me", function () {
    $.get(`/team/${teamId}/project/${project.id}`, "get-assignee-to-me");
});

$(document).on("click", "#get-all-completed", function () {
    $.get(`/team/${teamId}/project/${project.id}`, "get-all-completed");
});
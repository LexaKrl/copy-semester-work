document.addEventListener("DOMContentLoaded", function () {
    const toggleButtons = document.querySelectorAll(".toggle-form");

    toggleButtons.forEach(button => {
        button.addEventListener("click", function () {
            const memberId = this.dataset.memberId;
            const form = document.getElementById(`assign-form-${memberId}`);

            if (form.style.display === "none") {
                form.style.display = "block";
            } else {
                form.style.display = "none";
            }
        });
    });
});
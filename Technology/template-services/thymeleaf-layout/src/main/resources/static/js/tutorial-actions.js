$(document).ready(function () {

    /**
     * Search Button
     */
    $(".btn-delete").on("click", function (e) {
        e.preventDefault();
        link = $(this);

        tutorialTitle = link.attr("tutorialTitle");
        $("#yesBtn").attr("href", link.attr("href"));
        $("#confirmText").html("Do you want to delete the Tutorial \<strong\>" + tutorialTitle + "\<\/strong\>?");
        $("#confirmModal").modal();
    });

    /**
     * Clear Button
     */
    $("#btnClear").on("click", function (e) {
        e.preventDefault();
        $("#keyword").text("");
        window.location = "[[@{/tutorials}]]";
    });

    /**
     * Add Tutorial Button
     */
    $("#btnAddTutorial").on("click", function (e) {
        e.preventDefault();
        window.location = "[[@{/tutorials/add}]]";
    });

});

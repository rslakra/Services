/**
 * On ready, submit logout action.
 */
$(document).ready(function () {

    /**
     * Logout
     */
    $("#logout").click(function (event) {
        console.log("logout handling ...")
        event.preventDefault();
        $("#logout-form").submit();
    });
});
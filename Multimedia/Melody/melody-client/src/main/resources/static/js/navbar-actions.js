/**
 * hamburger event handling
 * @type {Element}
 */
$(document).ready(function () {
    $(".hamburger .hamburger-inner").click(function () {
        $(".wrapper").toggleClass("active")
    })

    $(".top_navbar .fas").click(function () {
        $(".profile_dd").toggleClass("active");
    });
})

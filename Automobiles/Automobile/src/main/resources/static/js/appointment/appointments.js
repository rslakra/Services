/**
 * On ready, initialize the page object.
 */
$(document).ready(function () {
    var page = new Page();
    page.init();
});

/**
 *
 * @constructor
 */
function Page() {

}

/**
 *
 */
Page.prototype.init = function () {
    var that = this;
    /**
     *
     */
    $("#appointment-on").datepicker({
        autoclose: true
    });

    /**
     *
     */
    $("#services").multiselect();
    this.bindButtons();

    /**
     *
     */
    $.get(root + "/appointments/all", function (data) {
        that.displayAllAppointments(data);
    });
};

/**
 *
 */
Page.prototype.bindButtons = function () {
    this.bindSaveButton();
};

/**
 *
 */
Page.prototype.bindSaveButton = function () {
    var that = this;

    /**
     *
     */
    $("#appointment-form").submit(function (e) {
        e.preventDefault();
        var data = $(this).serialize();
        $.post(root + "/appointments/save", data, function (data) {
            that.displayAllAppointments(data);
            $(".multiselect").multiselect("refresh");
            $("#appointment-form")[0].reset();
            $("#add-appointment-form").modal('hide');
        }, "json");

    });
};

/**
 * Display all appointments.
 *
 * @param rows
 */
Page.prototype.displayAllAppointments = function (rows) {
    var allRows = [];
    var $tableBody = $("#appointment-table tbody");

    for (var i = 0; i < rows.length; i++) {
        allRows.push(this.createHtmlRow(rows[i]));
    }

    $tableBody.empty();
    $tableBody.append(allRows);
};

/**
 * Create HTML Row.
 * @param row
 * @returns {*|jQuery|HTMLElement}
 */
Page.prototype.createHtmlRow = function (row) {
    var appointmentOn = row.appointmentOn.monthValue + "/" + row.appointmentOn.dayOfMonth + "/" + row.appointmentOn.year;
    var $row = $("<tr/>");
    var $userCell = $("<td/>");
    var userLink = $("<a/>").attr("href", root + "/appointments/" + row.id).text(row.user.firstName + " " + row.user.lastName);
    $userCell.append(userLink);
    var $dateCell = $("<td/>").text(appointmentOn);
    var $makeCell = $("<td/>").text(row.automobile.make);
    var $modelCell = $("<td/>").text(row.automobile.model);
    var $yearCell = $("<td/>").text(row.automobile.year);
    var $servicesCell = $("<td/>").text(row.services.join(", "));
    var $statusCell = $("<td/>").text(row.status);
    $row.append($userCell, $dateCell, $makeCell, $modelCell, $yearCell, $servicesCell, $statusCell);
    return $row;
};

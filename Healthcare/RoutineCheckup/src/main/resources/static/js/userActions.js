import {sendJsonData} from "./util.js";

window.onload = function () {
    let updUrl = "/update_user";

    let updButton = document.getElementById("upd_user");
    updButton.onclick = sendUser;

    let cancelButton = document.getElementById("upd_user_cancel");
    cancelButton.onclick = back;

    function back() {
        window.history.back();
    }

    function sendUser() {
        let user = {};
        user.id = document.getElementById("user_id").getAttribute("_user_id");
        user.first_name = document.getElementById("first_name_inp").value;
        user.last_name = document.getElementById("last_name_inp").value;
        user.login = document.getElementById("user_login")
            .getAttribute("_user_login");

        let csrfHeaderName = document.getElementById("_csrf_header")
            .getAttribute("val");
        let csrfToken = document.getElementById("_csrf_token")
            .getAttribute("val");

        sendJsonData(updUrl, "PUT", user, csrfHeaderName, csrfToken)
    }

};

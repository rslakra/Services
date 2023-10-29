import {sendJsonData} from "./util.js";

window.onload = function () {
    let updUrl = "/update_patient";

    let updButton = document.getElementById("send_patient");
    updButton.onclick = sendPat;

    let cancelButton = document.getElementById("patient_cancel");
    cancelButton.onclick = back;

    function back() {
        window.history.back();
    }

    function sendPat() {
        let pat = {};
        pat.id = document.getElementById("pat_id").getAttribute("_pat_id");
        pat.disease = document.getElementById("disease_inp").value;
        pat.disease_onset_time = document.getElementById("disease_onset")
            .value;
        pat.end_time_of_illness = document.getElementById(
            "end_time_of_illness"
        ).value;
        pat.doctor_id = document.getElementById("doctor_inp").value;

        let csrfHeaderName = document.getElementById("_csrf_header")
            .getAttribute("val");
        let csrfToken = document.getElementById("_csrf_token")
            .getAttribute("val");

        sendJsonData(updUrl, "PUT", pat, csrfHeaderName, csrfToken)
    }

};

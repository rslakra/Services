import {sendJsonData, sendRequest} from "./util.js";
// import sendGetRequest from "./util.js";

window.onload = function () {
    let updUrl = "/update_doctor";
    let doc_id = document.getElementById("doc_id").getAttribute("_doc_id");
    let payUrl = "/doc_payment/" + doc_id;
    let csrfHeaderName = document.getElementById("_csrf_header")
        .getAttribute("val");
    let csrfToken = document.getElementById("_csrf_token").getAttribute("val");

    let updButton = document.getElementById("upd_doctor");
    updButton.onclick = sendDoc;

    let cancelButton = document.getElementById("upd_doctor_cancel");
    cancelButton.onclick = back;

    sendRequest(payUrl, 'GET', csrfHeaderName, csrfToken, getPayment);

    function back() {
        window.history.back();
    }

    function sendDoc() {
        let doc = {};
        doc.id = doc_id;
        doc.speciality = document.getElementById("spec_inp").value;

        sendJsonData(updUrl, "PUT", doc, csrfHeaderName, csrfToken)
    }

    function getPayment(response) {
        let payment_div = document.getElementById("payment_div");
        if (response !== undefined
            && response !== null
            && response.payment !== undefined
        ) {
            payment_div.innerText = 'payment:' + response.payment;
        }
    }
};

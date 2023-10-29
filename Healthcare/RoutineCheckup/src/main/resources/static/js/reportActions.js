import {sendRequest} from "./util.js";

function deleteReport(reportName) {
    let url = "/monthly_report/" + reportName;
    // console.log('test');

    let csrfHeaderName = document.getElementById("_csrf_header")
        .getAttribute("val");
    let csrfToken = document.getElementById("_csrf_token").getAttribute("val");

    sendRequest(url, 'DELETE', csrfHeaderName, csrfToken, () => {
    })
}

window.deleteReport = deleteReport;

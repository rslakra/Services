let baseUrl = "/RoutineCheckup";

function updPatient(patId) {
    let updUrl = "/update_patient";
    redirectByPrefixedId(updUrl, patId);
}

function updDoctor(docId) {
    let updUrl = "/update_doctor";
    redirectByPrefixedId(updUrl, docId);
}

function updUser() {
    let url = baseUrl + "/update_user";
    window.location.href = url;
}

function redirectByPrefixedId(url, pefId) {
    let id = pefId.substr(3);

    window.location.href = baseUrl + url + "?id=" + id;
}

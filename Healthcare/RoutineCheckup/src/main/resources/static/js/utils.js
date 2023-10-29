let basePath = "/RoutineCheckup";

export function sendJsonData(url, method, data, csrfHeaderName, csrfToken) {
    url = basePath + url;

    let xhr = new XMLHttpRequest();
    xhr.open(method, url, false);
    xhr.setRequestHeader(csrfHeaderName, csrfToken);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onload = function () {
        let status = xhr.status;
        if (status >= 200 && status < 300) {
            alert("OK")
        } else {
            alert('OOOPS: ' + status + ': ' + xhr.message);
        }
    };
    xhr.send(JSON.stringify(data));
}

export function sendRequest(url, method, csrfHeaderName, csrfToken, onResponse) {
    url = basePath + url;

    let xhr = new XMLHttpRequest();
    xhr.open(method, url, false);
    xhr.setRequestHeader(csrfHeaderName, csrfToken);
    xhr.onload = function () {
        let status = xhr.status;
        if (status >= 200 && status < 300) {
            let resp = JSON.parse(xhr.response);
            onResponse(resp)
        } else {
            alert('OOOPS: ' + status + ': ' + xhr.message);
        }
    };
    xhr.send();
}

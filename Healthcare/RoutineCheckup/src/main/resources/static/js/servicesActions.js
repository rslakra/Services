window.onload = function () {
    let type = document.getElementById("type_meta")
        .getAttribute("_type");

    function on_house(costDiv, additDataLabel) {
        costDiv.innerText = 1000;
        additDataLabel.innerText = "Address:"
    }

    function online(costDiv, additDataLabel) {
        costDiv.innerText = 500;
        additDataLabel.innerText = "Commentary:"
    }

    let costDiv = document.getElementById("cost");
    let dataLabel = document.getElementById("data_label");
    switch (type) {
        case "on_house":
            on_house(costDiv, dataLabel);
            break;
        case "online":
            online(costDiv, dataLabel);
            break;
    }
};

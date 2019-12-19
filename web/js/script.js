let oldY = 0;

let is_default_graphic = false;

function init() {
    [r_out, x_out, y_out].forEach(f => f.value = '_');
    //changeGraf(r_out.value);
}

function error(message) {
    let errorField = document.getElementById('error');
    errorField.innerText = message;
    setTimeout(() => errorField.innerText = '', 2000);
}

function clickCanvas(R) {
    let canvas = document.getElementById("canvas");
    if (is_default_graphic) {
        console.log('error: R is not set');
        createGraphic('canvas', 0);
        let canvas = document.getElementById("canvas"), context = canvas.getContext("2d");
        context.strokeStyle = "#000000";
        context.fillStyle = "#ff0014";
        context.font = '20px Arial';
        return;
    }

    let br = canvas.getBoundingClientRect();
    let left = br.left;
    let top = br.top;

    let event = window.event;
    let x = event.clientX - left;
    let y = event.clientY - top;

    markPointFromServer((x - 175) / 140 * R, (-y + 175) / 140 * R, R);
}

function markPointFromServer(x, y, r) {
    if (!checkAllParameters(x, y, r)) {
        error("Установите параметры")
        return false;
    } else {
        fetch("./check?&x_h=" + encodeURI(x) + "&y_h=" + encodeURI(y) + "&r_h=" + encodeURI(r), {
            method: 'GET',
            headers: {
                'Content-Type': 'text/plain;charset=UTF-8'
            }
        })


        document.getElementById('iFrame').src = document.getElementById('iFrame').src
        return true;
    }
}

function markPoint(x, y, r, hit) {
    let canvas = document.getElementById("canvas"), context = canvas.getContext("2d");

    context.beginPath();
    context.rect(Math.round(175 + ((x / r) * 140)) - 3, Math.round(175 - ((y / r) * 140)) - 3, 6, 6);
    context.closePath();
    context.strokeStyle = 'black';

    let color = 'red';
    hit = hit.toString();

    if (hit.trim() === "true") {
        color = 'green';
    }

    context.fillStyle = color;
    context.fill();
    context.stroke();
}


function setRadius(r) {
    let checked = document.getElementsByClassName('rb');
    r = 0;
    for (let el = 0; checked[el]; el++) {
        if (checked[el].checked) {
            r += Number(checked[el].value);
        }
    }

    r_h_id.value = r;
    r_out.value = r > 0 ? r : '_';
    changeGraf(r)
}


function isYcorrect(y) {
    let yFloat = parseFloat(y.replace(/,/, '.'));
    return isNumber(y.replace(/,/, '.')) && yFloat >= -3 && yFloat <= 5;
}

function isRcorrect(r) {
    return r >= 1 && r <= 100;
}

function setX(x) {
    x_h_id.value = x;
    x_out.value = x;
}

function verifyY(y) {
    let y1 = parseFloat(y.value.replace(/,/, '.'));
    let elem = document.getElementById("y_in");
    if (y.value !== '' && y.value !== '-') {
        if (!isYcorrect(y.value)) {
            y.focus();
            elem.style.backgroundColor = "red";
            y.value = oldY;
            return false;
        }
        oldY = y1;
        y_h_id.value = y1;
        y_out.value = y1;
        elem.style.backgroundColor = "white";
        return true;
    }
    elem.style.backgroundColor = "white";
    oldY = y.value;
    return true;
}

function isNumber(n) {
    return !isNaN(parseFloat(n)) && !isNaN(n - 0)
}


function checkAllParameters(x, y, r) {
    return isNumber(x) && isNumber(y) && isRcorrect(r);
}


function changeGraf(r) {
    let canvas = document.getElementById("canvas"), context = canvas.getContext("2d");
    context.clearRect(0, 0, canvas.width, canvas.height);
    document.getElementById("canvas").style.background = "url('css/img/area" + r + ".png')";

}
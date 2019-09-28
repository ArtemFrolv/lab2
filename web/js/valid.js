let check = false;

$(document).ready(function () {
    if (sessionStorage.getItem("wayPic") === null)
        sessionStorage.setItem("wayPic", 'url("./img/');
    if (sessionStorage.getItem(("max")) === null)
        sessionStorage.setItem("max", 5);
    if (sessionStorage.getItem("oreh") === 'active') {
        let oreh = $('#orehus');
        oreh[0].classList.toggle('active');
        let yCoord = +sessionStorage.getItem("Y");
        let xCoord = +sessionStorage.getItem("X");
        oreh.css('top', yCoord);
        oreh.css('left', xCoord);
    }
});

function error(target) {
    target.value = target.value.slice(0, -1);
    show_error(target);
    show_fruits();
}

function show_error(target) {
    target.classList.toggle('shake');
    setTimeout(() => {
        target.classList.toggle('shake');
    }, 500);
}

function show_fruits() {
    let fruits = $('#fruits')[0];
    let max = sessionStorage.getItem("max");
    let num = randomInteger(1, max);
    sessionStorage.setItem(num, num);

    let count = 0;
    for (let i = 1; i < 7; i++) {
        if (sessionStorage.getItem(i))
            count++;
    }

    if (count === 5) {
        sessionStorage.setItem("max", 6);
        sessionStorage.setItem(6, 6);
        let ach = $('#achievement')[0];

        ach.classList.toggle('active');
        setTimeout(() => {
            ach.classList.toggle('active');
        }, 2000);

        let oreh = $('#orehus');
        let coord = randomCoor();
        sessionStorage.setItem("X", coord['X']);
        sessionStorage.setItem("Y", coord['Y']);
        sessionStorage.setItem("oreh", 'active');
        oreh.css('top', coord['Y']);
        oreh.css('left', coord['X']);
        oreh[0].classList.toggle('active');
    }

    let wayPic = sessionStorage.getItem("wayPic");
    fruits.style.backgroundImage = wayPic + num + '.png")';
    fruits.classList.toggle('active');
    setTimeout(() => {
        fruits.classList.toggle('active');
    }, 1000);
}

$(document).ready(function () {
    $('#R').on('input', function (event) {

        let val = event.target.value;
        if (val.indexOf('-') !== -1) {
            error(event.target);
            return;
        }

        val = parse(val);
        if (((val > 4) || (val < 1)) || (val === null))
            error(event.target);
    });
});

$(document).ready(function () {
    $('#Y').on('input', function (event) {

        let val = event.target.value;
        val = parse(val);
        if (((val > 5) || (val < -3)) || (val === null))
            error(event.target);
    });
});

function randomInteger(min, max) {
    let rand = min - 0.5 + Math.random() * (max - min + 1);
    return Math.round(rand);
}

function isNumeral(symbol) {
    return (symbol.length === 1) && (symbol <= '9') && (symbol >= '0');
}

function parse(string) {
    string = string.replace(/,/g, '.');
    let count = 0;
    if (string[0] === '.')
        return null;

    for (let i = 0; i < string.length; i++) {
        if (string[i] === '.') {
            count++;
            if (count === 1 && isNumeral(string[i - 1]))
                continue;
        }

        if ((string[i] === '-') && (i === 0))
            continue;

        if (!isNumeral(string[i]))
            return null;
    }

    return parseFloat(string);
}

$(document).ready(function (event) {
    $('#form').submit(function (event) {
        let Y = $('#Y')[0];
        let R = $('#R')[0];
        if ((checkError(Y.value)) || (checkError(R.value))) {
            event.preventDefault();
            if (checkError(Y.value))
                show_error(Y);
            if (checkError(R.value))
                show_error(R);
            show_fruits();
            return false;
        }
    });
});

function checkError(string) {
    return ((string === '') || (string.slice(-1) === ',') || (string.slice(-1) === '.'))
}

function randomCoor() {
    let posMain = $('#main');
    let posMainTop = posMain.offset().top;
    let posMainLeft = posMain.offset().left;
    let mainHeight = posMain.height();
    let mainWidth = posMain.width();
    let winHeight = $(window).height();
    let winWidth = $(window).width();
    let headerHeight = $('#header').height();
    let xCoord = 0;
    let oreh = $('#orehus');
    let orehSize = oreh.height();


    let yCoord = Math.round(winHeight * Math.random());

    if ((yCoord >= headerHeight) && (yCoord <= (posMainTop - orehSize)))
        xCoord = Math.round(winWidth * Math.random());
    else if ((yCoord >= (posMainTop + mainHeight)) && (yCoord <= (winHeight - orehSize)))
        xCoord = Math.round(winWidth * Math.random());
    else if ((yCoord > posMainTop) && (yCoord < (posMainTop + mainHeight))) {
        xCoord = Math.round((posMainLeft * Math.random()) - orehSize);
        if (Math.random() > 0.5)
            xCoord = winWidth - xCoord;
    } else {
        yCoord = yCoord + headerHeight;
        xCoord = Math.round(winWidth * Math.random());
    }
    return {'X': xCoord, 'Y': yCoord};
}

$(document).ready(function () {
    $('#orehus').click(function (event) {
        if (!(check)) {
            check = true;
            sessionStorage.setItem("wayPic", 'url("./img/adidas/');
            let ach = $('#achievement')[0];
            sessionStorage.removeItem("oreh");
            ach.style.backgroundImage = 'url("./img/adidas/ach.png")';
            ach.classList.toggle('active');
            setTimeout(() => {
                ach.classList.toggle('active');
            }, 2000);
            $(this)[0].classList.toggle('active');
        }
    })
});
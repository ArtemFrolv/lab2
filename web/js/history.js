$(document).ready(function () {
    drawGraph();
    let canvas = $('#canvas');
    let arr = JSON.parse((canvas.attr('history')).replace(/'/g, '"'));
    let ctx = canvas[0].getContext('2d');

    let width = canvas.width();
    let height = canvas.height();
    for (let i = 0; i < arr.length; i++) {
        let X = arr[i]['X'];
        let Y = arr[i]['Y'];
        let R = arr[i]['R'];
        let color = arr[i]['check'];
        let Xinner = (width * (2 * (+ X) + 3 * (+ R))) / (6 * (+ R));
        let Yinner = (height * (3 * (+ R) - 2 * (+ Y))) / (6 * (+ R));
        draw(Xinner, Yinner, color);
    }

    function draw(X, Y, color) {
        if (color === 'Есть пробитие!!!')
            ctx.fillStyle = 'rgb(0, 256, 0)';
        else
            ctx.fillStyle = 'rgb(200, 0, 0)';
        ctx.beginPath();
        ctx.arc(X, Y, 2, 0, Math.PI * 2);
        ctx.fill();
    }

    function drawGraph() {
        let canvas = $('#canvasGraph');
        let ctx = canvas[0].getContext('2d');
        let img = new Image(200, 200);
        img.src = './graph.svg';
        img.onload = () => {
            ctx.drawImage(img, 0, 0);
        };
    }
});
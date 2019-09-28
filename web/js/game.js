$(document).ready(function () {
    setInterval(() => {
        let main = $('.main')[0].children;
        $.ajax({
            url: 'game',
            type: 'POST',
            data: {'num': 8},
            success: function (result) {
                let field = JSON.parse(result);
                for (let i = 0; i < 3; ++i)
                    for (let j = 0; j < 3; ++j) {
                        let elem = $(main[i].children[j]);
                        if (field[i][j] === 'X')
                            elem.css('background-image', 'url("img/adidas/orehus.png")');
                        else if (field[i][j] === 'O')
                            elem.css('background-image', 'url("img/klub.png")');
                        else if (field[i][j] === '_')
                            elem.css('background-image', 'none');
                    }

            }
        })
    }, 500);
});

$(document).ready(function () {
    $('.main').ready(function (event) {
        let main = $('.main')[0].children;
        for (let i = 0; i < 3; ++i) {
            let line = main[i].children;
            for (let j = 0; j < 3; ++j) {
                $(line[j]).click(function (evn) {
                    let num = 3 * i + j;
                    $.ajax({
                        url: 'game',
                        type: 'GET',
                        data: {'num': num},
                        success: function (result) {
                            let res = result;
                            if ((res === 'true') || (res === 'false')) {
                                let win = $('.win');
                                win.addClass('visibility');
                                setTimeout(() => {
                                    win.removeClass('visibility');
                                }, 5000);
                                let img = $('.img');
                                if (res === 'true')
                                    img.css('background-image', 'url("img/orehus_win.png")');
                                else
                                    img.css('background-image', 'url("img/klub_win.png")');
                                $.ajax({
                                    url: 'clean'
                                })
                            }
                            else {
                                let count = 0;
                                let field = JSON.parse(res);
                                for (let i = 0; i < 3; ++i)
                                    for (let j = 0; j < 3; ++j)
                                        if (field[i][j] !== '_')
                                            ++count;
                                if (count === 9) {
                                    let win = $('.win');
                                    win.addClass('visibility');
                                    setTimeout(() => {
                                        win.removeClass('visibility');
                                    }, 5000);
                                    $('.img').css('background-image', 'url("img/draw.png")');
                                    $.ajax({
                                        url: 'clean'
                                    })
                                }
                            }
                        }
                    })
                })
            }
        }
    })
});
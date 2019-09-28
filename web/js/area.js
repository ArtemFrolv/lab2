$(document).ready(function () {
    $('#canvas').click(function (event) {
        let R = $('#R')[0];
        if (R.value === '') {
            error(R);
            return;
        }

        let pos = $(this).offset();
        let elem_left = pos.left;
        let elem_top = pos.top;
        let Xinner = event.pageX - elem_left;
        let Yinner = event.pageY - elem_top;

        let width = $(this).width();
        let height = $(this).height();

        let k_x = (Xinner - (width / 2)) / width * 3;
        let k_y = ((height / 2) - Yinner) / height * 3;

        let X = $('#X')[0];
        let Y = $('#Y')[0];
        Y.value = (k_y * R.value).toFixed(2);

        let helpX = $('#helpX');
        helpX.attr('value',  (k_x * R.value).toFixed(2));
        X.value = (k_x * R.value).toFixed(2);

        $('#form').submit();
    });
});
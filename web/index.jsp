<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>ACE CHEMICALS</title>
    <link rel="stylesheet" id="style" type="text/css" href="css/bats.css">
    <script type="text/javascript" src="js/script.js"></script>
</head>

<body onload="init()">
<jsp:useBean id="dotbean" class="server.JavaBean" scope="session"/>
<header>
    <h1>Лабораторная работа №2</h1>
    <nav>Расковалова Алёна, группа Р3202, вариант-24</nav>
</header>

<br>

<table>
    <td class="param">
        <p>Параметр X
            <output name="x_output" id="x_out" class="output">_</output>
        </p>
    </td>
    <td>
        <button type="button" class="X one" name="x11" id="x_11" value="-5" autocomplete="off"
                onclick="setX(x_11.value)">-5
        </button>
        <button type="button" class="X two" name="x12" id="x_12" value="-4" autocomplete="off"
                onclick="setX(x_12.value)">-4
        </button>
        <button type="button" class="X three" name="x13" id="x_13" value="-3" autocomplete="off"
                onclick="setX(x_13.value)">-3
        </button>
        <br>
        <button type="button" class="X one" name="x13" id="x_21" value="-2" autocomplete="off"
                onclick="setX(x_21.value)">-3
        </button>
        <button type="button" class="X two" name="x22" id="x_22" value="-1" autocomplete="off"
                onclick="setX(x_22.value)">-1
        </button>
        <button type="button" class="X three" name="x23" id="x_23" value="0" autocomplete="off"
                onclick="setX(x_23.value)">0
        </button>
        <br>
        <button type="button" class="X one" name="x31" id="x_31" value="1" autocomplete="off"
                onclick="setX(x_31.value)">1
        </button>
        <button type="button" class="X two" name="x32" id="x_32" value="2" autocomplete="off"
                onclick="setX(x_32.value)">2
        </button>
        <button type="button" class="X three" name="x33" id="x_33" value="3" autocomplete="off"
                onclick="setX(x_33.value)">3
        </button>
        <br>
    </td>

    <tr>
        <td class="param">
            <p>Параметр Y
                <output name="y_output" id="y_out" class="output">_</output>
            </p>
        </td>
        <td>
            <input type="text" name="y_input" autocomplete="off" maxlength="5" id="y_in"
                   placeholder="{-3...5}" value="0" onblur="return verifyY(this);" oninput="return verifyY(this);">
        </td>
    </tr>

    <tr>
        <td class="param">
            <p>Параметр R
                <output name="r_output" id="r_out" class="output">_</output>
            </p>
        </td>
        <td>
            1<input type="radio" name="radius" autocomplete="off" class="rb" id="r_1" value="1"
                    onclick="setRadius(r_1.value)">
            2<input type="radio" name="radius" class="rb" autocomplete="off" id="r_2" value="2"
                    onclick="setRadius(r_2.value)">
            3<input type="radio" name="radius" class="rb" id="r_3" autocomplete="off" value="3"
                    onclick="setRadius(r_3.value)">
            4<input type="radio" name="radius" class="rb" id="r_4" value="4" autocomplete="off"
                    onclick="setRadius(r_4.value)">
            5<input type="radio" name="radius" class="rb" id="r_5" value="5" autocomplete="off"
                    onclick="setRadius(r_5.value)">
        </td>
    </tr>

    <tr>
        <td class="param">График</td>
        <td>
            <div class="graphic centered">
                <canvas id="canvas" class="canvas" onclick="clickCanvas(r_out.value)" width="350" height="350"
                        style="background-image: url(css/img/area.png)"></canvas>
            </div>
        </td>
    </tr>

    <tr id="formblock">
        <td colspan="2">
            <br>
            <form method="GET" action="check" target="result">
                <input type="hidden" autocomplete="off" name="x_h" id="x_h_id" value="0">
                <input type="hidden" autocomplete="off" name="r_h" id="r_h_id" value="0">
                <input type="hidden" autocomplete="off" name="y_h" id="y_h_id" value="0">
                <input type="hidden" autocomplete="off" name="load" id="load" value="0">
                <input type="submit" value="Проверить">
            </form>
        </td>
    </tr>

    <tr>
        <td colspan="2">
            <p id="error">
            </p>
        </td>
    </tr>
</table>
<br>

<div class="result">
    <p align="center">
        <iframe id="iFrame" name="result" src="check?x_h=1&r_h=2&y_h=1&load=1"></iframe>
    </p>
</div>

<br>

<footer>
    <img class="st" src="css/img/joker/j.png" id="imgClickAndChange" onclick="img_src()" alt="Change style">
    <i><br><br>Красивая и душевня цитатка от Харви Дента:</i>
    <em><br>You either die a hero or live long enough to see yourself become the villain</em>
    <i><br><a href="https://github.com/ResedentSleeper/Web2" target="_blank">Не открывать! Страшна </a></i>
</footer>
</body>
<script>
    let image = document.getElementById("imgClickAndChange");
    let st = document.getElementById("style");
    let imgs = ['css/img/batman/b.png', 'css/img/joker/j.png'];
    let sts = ['css/joker.css', 'css/bats.css'];
    let i = 0;
    let j = 0;

    function img_src() {
        i++;
        i %= imgs.length;
        image.src = imgs[i];

        j++;
        j %= sts.length;
        st.href = sts[j];
    }
</script>
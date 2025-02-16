/***** ページTOPに戻る *****/
document.addEventListener("DOMContentLoaded", function () {
    let topButton = document.getElementById("topButton");

    // スクロール時の処理
    window.onscroll = function () {
        if (document.body.scrollTop > 100 || document.documentElement.scrollTop > 100) {
            topButton.style.display = "block";
        } else {
            topButton.style.display = "none";
        }
    };

    // ボタンをクリックするとページのトップに戻る
    topButton.addEventListener("click", function () {
        window.scrollTo({ top: 0, behavior: "smooth" });
    });
});

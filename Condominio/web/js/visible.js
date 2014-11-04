function visible(opcao) {
    if (opcao) {
        $("#barra").hide();
        $("#myCarousel").hide();
        $("#navCond").show();
    } else {
        $("#barra").show();
        $("#myCarousel").show();
        $("#iscond").show();
        $("#navCond").hide();
    }

}

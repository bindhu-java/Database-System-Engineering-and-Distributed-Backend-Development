function optimizeRoute() {

    const result = document.getElementById("route-result");

    if (result) {
        result.style.display = "block";
    }
}


setInterval(function () {

    const trucks = document.querySelectorAll(".moving-truck");

    trucks.forEach(function (truck) {

        truck.classList.toggle("move-animation");

    });

}, 2000);
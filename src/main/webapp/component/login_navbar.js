/**
 * 
 */

const hamburger = document.getElementById("hamburger");
const navRight = document.getElementById("nav-bar-right");

hamburger.addEventListener("click", () => {
	navRight.classList.toggle("show");
});
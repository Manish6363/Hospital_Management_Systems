/**
 * 
 */

const hamburger = document.getElementById("hamburger");
const navRight = document.getElementById("nav-bar-right");

function toggleHamburgerMenu() {
	navRight.classList.toggle("show");
}

hamburger.addEventListener("click", toggleHamburgerMenu);

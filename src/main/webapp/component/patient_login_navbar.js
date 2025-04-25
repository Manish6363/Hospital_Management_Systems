/**
 * 
 */

const hamburger = document.getElementById("hamburger");
const navRight = document.getElementById("nav-bar-right");

hamburger.addEventListener("click", () => {
	navRight.classList.toggle("show");
});

// Appointment dropdown toggle (for mobile)
const appointmentDropdown = document.getElementById("appointment-dropdown");
const appointmentToggle = appointmentDropdown.querySelector(".dropdown-toggle");

appointmentToggle.addEventListener("click", (e) => {
	e.stopPropagation();
	appointmentDropdown.classList.toggle("open");
});

document.addEventListener("click", (e) => {
	if (!appointmentDropdown.contains(e.target)) {
		appointmentDropdown.classList.remove("open");
	}
});


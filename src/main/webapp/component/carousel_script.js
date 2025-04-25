// Carousel script
      const carousel = document.getElementById("carousel");
      const dotsContainer = document.getElementById("dots");
      const images = carousel.querySelectorAll("img");
      let currentIndex = 0;

      images.forEach((_, i) => {
        const dot = document.createElement("span");
        dot.classList.add("dot");
        if (i === 0) dot.classList.add("active");
        dot.addEventListener("click", () => goToSlide(i));
        dotsContainer.appendChild(dot);
      });

      const dots = dotsContainer.querySelectorAll(".dot");

      function updateCarousel() {
        carousel.style.transform = `translateX(-${currentIndex * 100}%)`;
        dots.forEach((dot) => dot.classList.remove("active"));
        dots[currentIndex].classList.add("active");
      }

      function goToSlide(index) {
        currentIndex = index;
        updateCarousel();
      }

      function autoSlide() {
        currentIndex = (currentIndex + 1) % images.length;
        updateCarousel();
      }

      setInterval(autoSlide, 1500); // Auto-slide every 2.5 seconds
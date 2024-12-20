// Cargar el header y agregar funcionalidad al botón de tema
function loadComponent(id, file, callback) {
  fetch(file)
      .then(response => response.text())
      .then(data => {
          document.getElementById(id).innerHTML = data;
          if (callback) callback(); // Ejecutar una función después de cargar el componente
      })
      .catch(error => console.error('Error loading component:', error));
}

// Cargar el header y agregar funcionalidad al botón de tema
loadComponent("header", "./components/header.html", () => {
  const botonTema = document.getElementById("boton-tema");
  if (botonTema) {
      botonTema.addEventListener("click", () => {
          document.body.classList.toggle("tema-claro");
          document.body.classList.toggle("tema-oscuro");
      });
  }
});

// Cargar el footer
loadComponent("footer", "./components/footer.html");


// // Cargar el footer
// loadComponent("footer", "./components/footer.html");

// // Alternar entre los temas claro y oscuro
//  document.getElementById("boton-tema").addEventListener("click", () => {
//      const body = document.body;
//      body.classList.toggle("tema-claro");
//      body.classList.toggle("tema-oscuro");
//    });

// Selecciona el contenedor del fondo
const background = document.querySelector(".background-animation");

// Genera múltiples estrellas con animaciones
for (let i = 0; i < 100; i++) {
  const star = document.createElement("div");
  star.classList.add("star");

  // Posición aleatoria
  star.style.top = `${Math.random() * 100}vh`;
  star.style.left = `${Math.random() * 100}vw`;

  // Duración aleatoria para la animación
  star.style.animationDuration = `${12 + Math.random() * 18}s`;

  background.appendChild(star);
}

function loadComponent(id, file, callback) {
  fetch(file)
      .then(response => response.text())
      .then(data => {
          document.getElementById(id).innerHTML = data;
          if (callback) callback(); // Ejecutar una función después de cargar el componente
      })
      .catch(error => console.error('Error loading component:', error));
}



document.addEventListener('keydown', (event) => {
   if (event.key === "F12" || 
       (event.ctrlKey && event.shiftKey && event.key === "I") || 
       (event.ctrlKey && event.shiftKey && event.key === "j") || 
       (event.ctrlKey && event.key === "u")) {
       event.preventDefault();
       //alert("Herramientas de desarrollo deshabilitadas.");
   }
 });

 document.addEventListener("contextmenu", (event) => {
   event.preventDefault();
   //alert("Clic derecho deshabilitado.");
 });

 document.oncontextmenu = function(){return false;}

// Captura el formulario y añade un evento 'submit'
 document.getElementById("search-form").addEventListener("submit", function (e) {
   e.preventDefault(); // Evita el envío normal del formulario
   const searchInput = document.getElementById("search-input").value.trim();
   if (searchInput) {
       // Redirige a productos.html con la palabra clave en la URL
       window.location.href = `productos.html?search=${encodeURIComponent(searchInput)}`;
   }
 });



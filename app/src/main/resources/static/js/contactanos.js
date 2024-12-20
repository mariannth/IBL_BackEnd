// Mostrar el modal con un mensaje dinámico
function showModal(message, duration = 5000) { // Duración por defecto: 5 segundos
  const modalMessage = document.getElementById("modalMessage");
  modalMessage.textContent = message;
  const alertModal = new bootstrap.Modal(document.getElementById("alertModal"));
  alertModal.show();

  // Cerrar el modal automáticamente después del tiempo especificado
  setTimeout(() => {
    alertModal.hide();
  }, duration);
}

// Validaciones con expresiones regulares
function validateName(name) {
  const nameRegex = /^[a-zA-ZÀ-ÿ\s]{3,50}$/; // Letras, espacios, longitud 3-50
  return nameRegex.test(name);
}

function validateEmail(email) {
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; // Formato de correo
  return emailRegex.test(email);
}

function validatePhone(phone) {
  const phoneRegex = /^\d{10,12}$/; // Solo números, 10-12 dígitos
  return phoneRegex.test(phone);
}

// Cambiar el estado visual de los campos (error o válido)
function toggleError(input, isValid) {
  if (isValid) {
    input.classList.remove("is-invalid");
    input.classList.add("is-valid");
  } else {
    input.classList.remove("is-valid");
    input.classList.add("is-invalid");
  }
}

// Configuración inicial de EmailJS
emailjs.init("gmiJc0yrehImR_piP"); // Sustituye "TU_PUBLIC_KEY" con la clave pública correcta (estaba esta) oKasd2SLlI5zI9Ks_

// Evento del botón de enviar
document.getElementById("enviarMensaje").addEventListener("click", function (event) {
  event.preventDefault(); // Prevenir el envío tradicional

  const nombre = document.querySelector("#nombre input").value.trim();
  const email = document.querySelector("#email-contactanos input").value.trim();
  const phone = document.querySelector("#phone input").value.trim();
  const mensaje = document.querySelector("#mensajito textarea").value.trim();
  const privacidad = document.querySelector("#terms input").checked;

  // Validaciones
  const isNameValid = validateName(nombre);
  const isEmailValid = validateEmail(email);
  const isPhoneValid = validatePhone(phone);

  toggleError(document.querySelector("#nombre input"), isNameValid);
  toggleError(document.querySelector("#email-contactanos input"), isEmailValid);
  toggleError(document.querySelector("#phone input"), isPhoneValid);

  if (!isNameValid) {
    showModal("Por favor, ingresa un nombre válido.");
  } else if (!isPhoneValid) {
    showModal("Por favor, ingresa un teléfono válido (10-12 dígitos).");
  } else if (!isEmailValid) {
    showModal("Por favor, ingresa un correo válido.");
  } else if (mensaje === "") {
    showModal("El campo de mensaje no puede estar vacío.");
  } else if (!privacidad) {
    showModal("Debes aceptar los términos y condiciones.");
  } else {
    // Enviar datos si todo es válido
    const datosFormulario = {
      nombre: nombre,
      email: email,
      telefono: phone,
      mensaje: mensaje,
    };

    // Envío del correo con EmailJS
    emailjs.send("service_nucnjfv", "template_mm46z4u", datosFormulario).then(
      function () {
        // Si el envío es exitoso
        showModal("¡Gracias por contactarnos! Tu mensaje ha sido enviado.", 5000); // Mostrar por 5 segundos
    
        // Limpiar los campos del formulario
        document.querySelector("#nombre input").value = "";
        document.querySelector("#email-contactanos input").value = "";
        document.querySelector("#phone input").value = "";
        document.querySelector("#mensajito textarea").value = "";
        document.querySelector("#terms input").checked = false;
    
        // Resetear validaciones visuales
        document.querySelectorAll(".is-valid").forEach((input) => {
          input.classList.remove("is-valid");
        });
    
        // Redirigir al index.html después de que el modal desaparezca
        setTimeout(() => {
          window.location.href = "index.html"; // Cambia a la página principal
        }, 5000); // Tiempo en milisegundos, coincide con la duración del modal
      },
      function (error) {
        // Si hay un error en el envío
        showModal("Hubo un problema al enviar tu mensaje. Inténtalo más tarde.", 5000); // Mostrar por 5 segundos
        console.error("Error al enviar el correo:", error);
      }
    );
    
  }
});

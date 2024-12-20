//-------------------------------------------------------------
//     Aplicando la conexión de la API con el front
//-------------------------------------------------------------

// Validaciones
function validateEmail(email) {
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return emailRegex.test(email);
}

function validatePhone(phone) {
  const phoneRegex = /^[0-9]{10}$/;
  return phoneRegex.test(phone);
}

// Mostrar modal de alerta
function showAlert(message, type, form) {
  const modal = new bootstrap.Modal(document.getElementById("alertModal"));
  const modalMessageBody = document.getElementById("modalMessageBody");

  // Establecer el mensaje
  modalMessageBody.innerHTML = `
    <div class="alert alert-${type}" role="alert">
      ${message}
    </div>
  `;

  // Mostrar el modal
  modal.show();
}

// Manejo del Registro
function handleRegister(event) {
  event.preventDefault();

  // Capturar valores del formulario
  const nombre = document.getElementById("nombre").value.trim();
  const apellido = document.getElementById("apellido").value.trim();
  const email = document.getElementById("email").value.trim();
  const password = document.getElementById("password").value.trim();
  const confirmPassword = document.getElementById("confirm_password").value.trim();
  const telefono = document.getElementById("telefono").value.trim();
  const formRegister = event.target;

  // Validaciones
  if (!nombre || !apellido || !email || !password || !confirmPassword || !telefono) {
    showAlert("Por favor, complete todos los campos.", "danger", formRegister);
    return;
  }

  if (!validateEmail(email)) {
    showAlert("Por favor, ingrese un correo electrónico válido.", "danger", formRegister);
    return;
  }

  if (password.length < 6) {
    showAlert("La contraseña debe tener al menos 6 caracteres.", "danger", formRegister);
    return;
  }

  if (password !== confirmPassword) {
    showAlert("Las contraseñas no coinciden.", "danger", formRegister);
    return;
  }

  if (!validatePhone(telefono)) {
    showAlert("Ingrese un número de teléfono válido.", "danger", formRegister);
    return;
  }

  // Crear el modelo de usuario
  const newUser = {
    nombre,
    apellido,
    email,
    password,
    telefono,
  };

  // Hacer la solicitud POST al backend
  fetch("http://localhost:8080/api/v1/new-usuario", { // URL corregida al backend
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(newUser),
  })
  .then(response => {
    if (!response.ok) {
      if (response.status === 409) {
        showAlert("Este correo ya está registrado.", "danger", formRegister);
      } else {
        showAlert("Hubo un error en el registro. Inténtalo de nuevo.", "danger", formRegister);
      }
      throw new Error('Error en el registro');
    }
    return response.json();
  })
  .then(data => {
    showAlert("Registro exitoso. Redirigiendo al login...", "success", formRegister);

    // Redirigir al login después de 1.5 segundos
    setTimeout(() => (window.location.href = "login.html"), 1500);
  })
  .catch(error => {
    console.error("Error en el registro:", error);
    showAlert("Hubo un error inesperado. Inténtalo de nuevo.", "danger", formRegister);
  });
}

// Manejo del Login
function handleLogin(event) {
  event.preventDefault();
  const email = document.getElementById("email").value.trim();
  const password = document.getElementById("password").value.trim();
  const formLogin = event.target;

  if (!email || !password) {
    showAlert("Por favor, complete todos los campos.", "danger", formLogin);
    return;
  }

  // Hacer la solicitud POST al backend para login
  fetch("http://localhost:8080/api/v1/usuario/email/" + email) // Endpoint para verificar usuario por email
    .then(response => {
      if (!response.ok) {
        showAlert("Correo electrónico no encontrado.", "danger", formLogin);
        throw new Error('Usuario no encontrado');
      }
      return response.json();
    })
    .then(user => {
      if (user.password === password) {
        showAlert(`Bienvenido, ${user.nombre}! Redirigiendo...`, "success", formLogin);
        // Redirigir al inicio después de 1.5 segundos
        setTimeout(() => (window.location.href = "index.html"), 1500);
      } else {
        showAlert("Contraseña incorrecta.", "danger", formLogin);
      }
    })
    .catch(error => {
      console.error("Error en el login:", error);
      showAlert("Hubo un error al intentar iniciar sesión. Inténtalo de nuevo.", "danger", formLogin);
    });
}

// Asociar eventos a los formularios
document.addEventListener("DOMContentLoaded", () => {
  const formRegister = document.querySelector("#formRegister"); // ID del formulario de registro
  const formLogin = document.querySelector("#formLogin"); // ID del formulario de login

  if (formRegister) {
    formRegister.addEventListener("submit", handleRegister);
  }

  if (formLogin) {
    formLogin.addEventListener("submit", handleLogin);
  }
});
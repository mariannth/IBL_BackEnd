//-------------------------------------------------------------
//     Aplicando LocalStorage en el proyecto
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
  
  // Mostrar alertas de Bootstrap
  //   function showAlert(message, type, form) {
  //     const alertPlaceholder = document.createElement("div");
  //     alertPlaceholder.innerHTML = `
  //       <div class="alert alert-${type} alert-dismissible fade show" role="alert">
  //         ${message}
  //         <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
  //       </div>
  //     `;
  //     form.prepend(alertPlaceholder);
  
  //     setTimeout(() => {
  //       alertPlaceholder.remove();
  //     }, 3000);
  //   }
  
  // Mostrar modal de alerta
  function showAlert(message, type, form) {
    const modal = new bootstrap.Modal(document.getElementById("alertModal"));
    const modalMessageBody = document.getElementById("modalMessageBody");
  
    // Establecer el mensaje
    modalMessageBody.innerHTML = message;
  
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
  
    // Obtener usuarios existentes en LocalStorage
    const users = JSON.parse(localStorage.getItem("users")) || [];
  
    // Verificar si el correo ya está registrado
    if (users.some(user => user.email === email)) {
      showAlert("Este correo ya está registrado.", "danger", formRegister);
      return;
    }
  
    // Agregar el nuevo usuario al arreglo
    users.push(newUser);
  
    // Almacenar el arreglo actualizado en LocalStorage
    localStorage.setItem("users", JSON.stringify(users));
  
    showAlert("Registro exitoso. Redirigiendo al login...", "success", formRegister);
  
    // Redirigir al login después de 1.5 segundos
    setTimeout(() => (window.location.href = "login.html"), 1500);
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
  
    // Obtener usuarios registrados desde LocalStorage
    const users = JSON.parse(localStorage.getItem("users")) || [];
  
    // Verificar si el usuario existe y la contraseña es correcta
    const foundUser = users.find(user => user.email === email && user.password === password);
  
    if (!foundUser) {
      showAlert("Correo electrónico o contraseña incorrectos.", "danger", formLogin);
      return;
    }
  
    // Inicio de sesión exitoso
    showAlert(`Bienvenido, ${foundUser.nombre}! Redirigiendo...`, "success", formLogin);
  
    // Redirigir al inicio después de 1.5 segundos
    setTimeout(() => (window.location.href = "index.html"), 1500);
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
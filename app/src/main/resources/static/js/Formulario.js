document
  .getElementById("formularioProducto")
  .addEventListener("submit", function (event) {
    event.preventDefault(); // Con esto el formulario no se envia deinmediato

    const nombre = document.getElementById("nombre").value.trim();
    const descripcion = document.getElementById("descripcion").value.trim();
    const marca = document.getElementById("marca").value.trim();
    const categoria = document.getElementById("seleccion-categoria").value;
    const precio = document.getElementById("precio").value.trim();
    const cantidad = document.getElementById("cantidad").value.trim();
    const codigo = document.getElementById("codigo").value.trim();
    const imagen = document.getElementById("imagen").files[0];

    // Sirsve para verificar si el campo "categoría" está vacío
    if (!categoria) {
      alert("Por favor, selecciona una categoria.");
      return; //Se detiene si falta la selección de la categoría
    }

    // Aqui sirve para verificar si el campo "nombre" está vacío
    if (!nombre) {
      alert("Por favor, ingresa el nombre del producto.");
      return; // Se detiene el envío si falta el nombre
    }

    // Para verificar si el campo "descripcion" está vacío
    if (!descripcion) {
      alert("Por favor, ingresa una descripción.");
      return; // Se detiene el envío si falta la descripcion
    }

    // Para verificar si el campo "marca" está vacío
    if (!marca) {
      alert("Por favor, ingresa una marca.");
      return; // Se detiene el envío si falta la marca
    }

    // Para verificar si el campo "codigo" está vacío
    if (!codigo) {
      alert("Por favor, ingresa el código del producto.");
      return; // Se detiene el envío si falta el codigo
    }

    // Para verificar si el campo "codigo" tiene solo números
    if (isNaN(codigo)) {
      alert("Por favor, ingresa solo números en el campo de código.");
      return; // Se detiene el envío si hay letras u otros caracteres
    }

    // Para vrificar si el campo "precio" está vacío
    if (!precio) {
      showAlert("Por favor, ingresa un precio.", "warning");
      return; // Detiene el envío si falta el precio
    }
    if (isNaN(precio)) {
      alert("Por favor, ingresa solo números en el campo de precio.");
      return; // Se detiene el envío si hay letras u otros caracteres
    }

    // Para verificar si el campo "cantidad" está vacío
    if (!cantidad) {
      alert("Por favor, ingresa una cantidad.");
      return; // Se detiene el envío si falta la cantidad
    }
    if (isNaN(cantidad)) {
      alert("Por favor, ingresa solo números en el campo de cantidad.");
      return; // Se detiene el envío si hay letras u otros caracteres
    }

    // Para verificar si el campo "imagen" está vacío
    if (!imagen) {
      alert("Por favor, ingresa una imagen.");
      return; // Se detiene el envío si falta una imagen
    }

    // Sirve para recargar la página después de un pequeño retraso
    setTimeout(() => {
      location.reload();
    }, 500); // Aqui se recarga la página después de 500 ms (medio segundo)
  });

// Creacion  del objeto FormData
const formData = new FormData();
formData.append("nombre", nombre);
formData.append("descripcion", descripcion);
formData.append("marca", marca);
formData.append("categoria", categoria);
formData.append("precio", precio);
formData.append("cantidad", cantidad);
formData.append("imagen", imagen); // Añadir el archivo de imagen

// Sirve para enviar datos al servidor usando fetch y enviarla al servidro  local
fetch("http://localhost:3000/registro-producto", {
  method: "POST",
  body: formData, // Se envia el FormData
})
  .then((response) => response.json())
  .then((data) => {
    console.log("Producto registrado:", data);
    alert("¡Producto registrado con éxito!");
    // Todavia no se si ponerlo aqui: para la página después del registro exitoso
    setTimeout(() => location.reload(), 500);
  })
  .catch((error) => {
    console.error("Error:", error);
    alert("Hubo un error al registrar el producto.");
  });

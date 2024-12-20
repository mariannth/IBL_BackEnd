document.getElementById("formularioProducto").addEventListener("submit", function(event) {
    event.preventDefault(); // Con esto el formulario no se envia deinmediato


    const nombre = document.getElementById("nombre").value.trim();
    const descripcion = document.getElementById("descripcion").value.trim();
    const marca = document.getElementById("marca").value.trim();
    const categoria = document.getElementById("seleccion-categoria").value;
    const precio = document.getElementById("precio").value.trim();
    const cantidad = document.getElementById("cantidad").value.trim();
    const codigo = document.getElementById("codigo").value.trim();
    const imagen= document.getElementById("imagen").files[0];

    // Imprimir los valores en la consola
    console.log("Nombre:", nombre);
    console.log("Descripción:", descripcion);
    console.log("Marca:", marca);
    console.log("Categoría:", categoria);
    console.log("Precio:", precio);
    console.log("Cantidad:", cantidad);
    console.log("Código:", codigo);
    console.log("Imagen:", imagen);

    function showAlert(mensaje, tipo) {
        const alertContainer = document.getElementById("alert-container");
        const alerta = document.createElement("div");
        alerta.className = `alert alert-${tipo} alert-dismissible fade show`;
        alerta.role = "alert";
        alerta.innerHTML = mensaje + 
            '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>';
        alertContainer.appendChild(alerta);
        // Opcional: Cerrar alerta después de 4 segundos
        setTimeout(() => {
            alerta.remove();
        }, 4000);
    }

    // Sirsve para verificar si el campo "categoría" está vacío
    if (!categoria) {
        showAlert("Por favor, selecciona una categoria.", "warning");
        return; //Se detiene si falta la selección de la categoría
    }

    // Aqui sirve para verificar si el campo "nombre" está vacío
    if (!nombre) {
        showAlert("Por favor, ingresa el nombre del producto.", "warning");
        return; // Se detiene el envío si falta el nombre
    }

    // Para verificar si el campo "descripcion" está vacío
    if (!descripcion) {
        showAlert("Por favor, ingresa una descripción.", "warning");
        return; // Se detiene el envío si falta la descripcion
    }

    // Para verificar si el campo "marca" está vacío
    if (!marca) {
        showAlert("Por favor, ingresa una marca.", "warning");
        return; // Se detiene el envío si falta la marca
    }
    
    // Para verificar si el campo "codigo" está vacío
    if (!codigo) {
        showAlert("Por favor, ingresa el código del producto.", "warning");
        return; // Se detiene el envío si falta el codigo
    }
    
    // Para verificar si el campo "codigo" tiene solo números
    if (isNaN(codigo)) {
        showAlert("Por favor, ingresa solo números en el campo de código.", "warning");
    return; // Se detiene el envío si hay letras u otros caracteres
    }


    // Para vrificar si el campo "precio" está vacío
    if (!precio) {
        showAlert("Por favor, ingresa un precio.", "warning");
        return; // Detiene el envío si falta el precio
    }
    if (isNaN(precio)) {
        showAlert("Por favor, ingresa solo números en el campo de precio.", "warning");
        return; // Se detiene el envío si hay letras u otros caracteres
    }

    
    // Para verificar si el campo "cantidad" está vacío
    if (!cantidad) {
        showAlert("Por favor, ingresa una cantidad.", "warning");
        return; // Se detiene el envío si falta la cantidad
    }
    if (isNaN(cantidad)) {
        showAlert("Por favor, ingresa solo números en el campo de cantidad.", "warning");
        return; // Se detiene el envío si hay letras u otros caracteres
    }

    // Para verificar si el campo "imagen" está vacío
    if (!imagen) {
        showAlert("Por favor, ingresa una imagen.", "warning");
        return; // Se detiene el envío si falta una imagen
    }



    // Crear el objeto productoData con los datos del formulario
    const productoData = {
        nombre: nombre,
        descripcion: descripcion,
        marca: marca,
        categoria: categoria,
        precio: parseFloat(precio), // Asegúrate de que el precio sea un número
        cantidad: parseInt(cantidad), // Asegúrate de que la cantidad sea un número entero
        codigo: codigo,
        imagen: imagen.name // O solo el nombre de la imagen
    };


     // Convertir el objeto JavaScript a formato JSON
     const productoJSON = JSON.stringify(productoData);

     // Mostrar el objeto JSON en la consola (opcional)
     console.log("Objeto JSON:", productoJSON);


     // Guardar el producto en localStorage
     let productos = JSON.parse(localStorage.getItem("productos")) || [];
     productos.push(productoData); // Agregar el nuevo producto al array
     localStorage.setItem("productos", JSON.stringify(productos));
 
     // Limpiar los campos del formulario
     document.getElementById("formularioProducto").reset();
 
     // Mostrar alerta de éxito
     showAlert("Producto registrado exitosamente.", "success");
 
     // Actualizar la lista de productos en el HTML
     actualizarListaDeProductos();
 });
 
 // Función para actualizar la lista de productos
 function actualizarListaDeProductos() {
     const productos = JSON.parse(localStorage.getItem("productos")) || [];
     const listaItems = document.getElementById("list-items");
     listaItems.innerHTML = ""; // Limpiar la lista antes de mostrarla
 
     productos.forEach((producto) => {
         const itemDiv = document.createElement("div");
         itemDiv.classList.add("col-md-4");
         itemDiv.innerHTML = `
             <div class="card mb-4">
                 <img src="img/${producto.imagen}" class="card-img-top" alt="${producto.nombre}">
                 <div class="card-body">
                     <h5 class="card-title">${producto.nombre}</h5>
                     <p class="card-text">${producto.descripcion}</p>
                     <p><strong>Precio:</strong> $${producto.precio}</p>
                     <p><strong>Cantidad:</strong> ${producto.cantidad}</p>
                     <p><strong>Código SKU:</strong> ${producto.codigo}</p>
                     <p><strong>Categoría:</strong> ${producto.categoria}</p>
                 </div>
             </div>
         `;
         listaItems.appendChild(itemDiv);
     });
 }
 
 // Al cargar la página, mostrar los productos guardados
 document.addEventListener("DOMContentLoaded", actualizarListaDeProductos);



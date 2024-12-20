// Función para cargar los productos en el carrito
function loadCart() {
  const cart = JSON.parse(localStorage.getItem("cart")) || [];
  const cartItemsContainer = document.getElementById("cartItems");
  const totalPriceElement = document.getElementById("totalPrice");

  cartItemsContainer.innerHTML = ""; // Limpiar contenedor de productos

  let total = 0;
  cart.forEach((product) => {
    const productTotal = product.price * product.quantity;
    total += productTotal;

    // Crear el HTML de cada producto
    const cartItem = `
            <div class="cart-item">
                <img src="${product.img}" alt="${product.name}">
                <div class="cart-item-info">
                    <h4>${product.name}</h4>
                    <p>$${product.price} x ${product.quantity}</p>
                    <div class="quantity-controls">
                        <button onclick="updateQuantity(${product.id}, -1)">-</button>
                        <span>${product.quantity}</span>
                        <button onclick="updateQuantity(${product.id}, 1)">+</button>
                    </div>
                    <button class="btn btn-danger btn-sm" onclick="removeFromCart(${
                      product.id
                    })">Eliminar</button>
                </div>
                <p>$${productTotal.toFixed(2)}</p>
            </div>
        `;
    cartItemsContainer.innerHTML += cartItem;
  });

  totalPriceElement.textContent = `${total.toFixed(2)}`;
}

// Función para actualizar la cantidad de productos
function updateQuantity(productId, change) {
  const cart = JSON.parse(localStorage.getItem("cart")) || [];
  const updatedCart = cart.map((item) => {
    if (item.id === productId) {
      item.quantity += change; // Aumentar o disminuir la cantidad
      if (item.quantity < 1) item.quantity = 1; // No permitir cantidades menores a 1
    }
    return item;
  });

  localStorage.setItem("cart", JSON.stringify(updatedCart));

  // Recargar el carrito
  loadCart();
}

// Función para eliminar productos del carrito
function removeFromCart(productId) {
  const cart = JSON.parse(localStorage.getItem("cart")) || [];
  const updatedCart = cart.filter((item) => item.id !== productId);
  localStorage.setItem("cart", JSON.stringify(updatedCart));

  // Recargar el carrito
  loadCart();
}

// Cargar el carrito cuando la página se carga
document.addEventListener("DOMContentLoaded", loadCart);

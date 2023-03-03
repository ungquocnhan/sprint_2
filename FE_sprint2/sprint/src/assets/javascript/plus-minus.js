const minusBtn = document.querySelector(".minus-btn");
const plusBtn = document.querySelector(".plus-btn");
const quantityInput = document.querySelector(".quantity-input");

minusBtn.addEventListener("click", function() {
  if (quantityInput.value > 1) {
    quantityInput.value = parseInt(quantityInput.value) - 1;
  }
});

plusBtn.addEventListener("click", function() {
  quantityInput.value = parseInt(quantityInput.value) + 1;
});

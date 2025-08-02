document.addEventListener("DOMContentLoaded", () => {
  const container = document.getElementById("produtos");

  fetch("http://localhost:8080/products")
    .then(response => {
      if (!response.ok) {
        throw new Error("Erro ao buscar produtos");
      }
      return response.json();
    })
    .then(produtos => {
      produtos.forEach(produto => {
        const card = document.createElement("div");
        card.classList.add("card");

        card.innerHTML = `
          <h2>${produto.productName}</h2>
          <p>R$ ${produto.productPrice.toFixed(2)}</p>
        `;

        container.appendChild(card);
      });
    })
    .catch(error => {
      container.innerHTML = `<p style="color: red; text-align: center;">${error.message}</p>`;
      console.error(error);
    });
});

function mostrarPainel() {
    document.getElementById('login-container').classList.add('hidden');
    document.getElementById('main-panel').classList.remove('hidden');
    // Ajusta o layout do corpo para o painel
    document.body.style.display = "block";
}

let map;
function initMap() {
    // Coordenadas iniciais (ex: Centro da sua cidade)
    const centro = { lat: -23.5505, lng: -46.6333 }; // São Paulo
    map = new google.maps.Map(document.getElementById("map"), {
        zoom: 12,
        center: centro,
    });
}

// Adicionar os 10 campos de endereços
document.getElementById('add-ponto').addEventListener('click', () => {
    const container = document.getElementById('inputs-container');
    const inputs = document.querySelectorAll('.ponto');
    if (inputs.length < 10) {
        const novoInput = document.createElement('input');
        novoInput.type = 'text';
        novoInput.className = 'ponto';
        novoInput.placeholder = `Ponto ${inputs.length + 1}`;
        container.appendChild(novoInput);
    } else {
        alert("Limite de 10 pontos atingido.");
    }
});

document.getElementById('btn-otimizar').addEventListener('click', () => {
    const campos = document.querySelectorAll('.ponto');
    const listaEnderecos = Array.from(campos).map(input => input.value).filter(val => val !== "");

    if (listaEnderecos.length < 2) {
        alert("Por favor, insira pelo menos 2 endereços.");
        return;
    }

    // Chamada para o Spring Boot
    fetch('/otimizar', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(listaEnderecos)
    })
    .then(response => response.text())
    .then(mensagem => {
        alert("Resposta do Servidor: " + mensagem);
    })
    .catch(error => console.error('Erro ao conectar com Java:', error));
});
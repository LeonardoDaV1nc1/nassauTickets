const API_URL = 'http://localhost:8080/api/senhas';

async function emitirSenha(tipo) {
    try {
        const response = await fetch(`${API_URL}/emitir?tipo=${tipo}`, {
            method: 'POST'
        });
        if (!response.ok) throw new Error('Erro ao emitir senha');
        const data = await response.json();
        
        document.getElementById('resultado-senha').innerText = `Senha Gerada: ${data.numero}`;
        carregarFila();
    } catch (error) {
        alert('Erro ao conectar com o backend. Verifique se a API está rodando.');
        console.error(error);
    }
}

async function carregarFila() {
    try {
        const response = await fetch(API_URL);
        if (!response.ok) throw new Error('Erro ao buscar fila');
        const senhas = await response.json();
        
        const lista = document.getElementById('lista-fila');
        lista.innerHTML = '';

        if (senhas.length === 0) {
            lista.innerHTML = '<li>A fila está vazia.</li>';
            return;
        }

        senhas.forEach(s => {
            const li = document.createElement('li');
            li.innerHTML = `<span><strong>${s.numero}</strong> (${s.tipo})</span> <span class="badge">${s.status}</span>`;
            lista.appendChild(li);
        });
    } catch (error) {
        console.error('Erro ao carregar fila:', error);
    }
}

async function chamarProxima() {
    try {
        const response = await fetch(`${API_URL}/chamar?guiche=1`, {
            method: 'PUT'
        });
        if (!response.ok) {
            alert('Não há senhas aguardando na fila.');
            return;
        }
        const data = await response.json();
        document.getElementById('resultado-senha').innerText = `Em atendimento: ${data.numero} (Guichê ${data.guiche})`;
        carregarFila();
    } catch (error) {
        alert('Erro ao chamar a próxima senha.');
        console.error(error);
    }
}

carregarFila();

setInterval(carregarFila, 5000);
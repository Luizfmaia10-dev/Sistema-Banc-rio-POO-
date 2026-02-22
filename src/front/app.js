let saldo = 0;

function executar() {
    const conta = document.getElementById("conta").value;
    const valor = Number(document.getElementById("valor").value);
    const acao = document.getElementById("acao").value;
    const resultado = document.getElementById("resultado");

    if (!conta) {
        resultado.textContent = "Digite o número da conta";
        return;
    }

    if (acao === "depositar") {
        saldo += valor;
        resultado.textContent = "Depósito feito. Saldo: " + saldo;
    }
    else if (acao === "sacar") {
        if (valor > saldo) {
            resultado.textContent = "Saldo insuficiente";
        } else {
            saldo -= valor;
            resultado.textContent = "Saque feito. Saldo: " + saldo;
        }
    }
    else {
        resultado.textContent = "Saldo atual: " + saldo;
    }
 function toggleValor(){
     const acao = document.getElementById("acao").value;
     const campoValor = document.getElementById("valor");

     if(acao === "saldo"){
         campoValor.style.display = "none";
     } else {
         campoValor.style.display = "block";
     }
 }


}
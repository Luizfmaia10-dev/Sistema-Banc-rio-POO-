function mostrarCampoValor(){
    const op = document.getElementById("operacao").value;
    document.getElementById("valor").style.display =
        op === "saldo" ? "none" : "block";
}

async function executar(){

    const conta = document.getElementById("conta").value;
    const op = document.getElementById("operacao").value;
    const valor = document.getElementById("valor").value;

    let url = `http://localhost:8080/${op}?conta=${conta}`;

    if(op !== "saldo")
        url += `&valor=${valor}`;

    const res = await fetch(url,{ method:"POST" });
    const texto = await res.text();

    document.getElementById("resultado").innerText = texto;
}
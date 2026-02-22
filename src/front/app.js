 async function consultarSaldo(){
        const numero = document.getElementById("conta").value;

        const res = await fetch(`http://localhost:8080/saldo?conta=${numero}`);

        const texto = await res.text();

        document.getElementById("resultado").innerText = texto;
    }
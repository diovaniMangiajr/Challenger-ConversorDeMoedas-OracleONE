# Challenger-ConversorDeMoedas-OracleONE

## 💱 Conversor de Moedas - Challenger Oracle ONE

Projeto desenvolvido como parte do **Challenger Oracle ONE - Back-End Java**.  
O objetivo é criar um **conversor de moedas** em Java, consumindo uma API de taxas de câmbio em tempo real e aplicando boas práticas de programação.

---

## 📌 Funcionalidades
- Conversão de moedas em tempo real.
- Entrada de valor e moedas de origem/destino via console.
- Integração com API externa para obter taxas de câmbio atualizadas.
- Tratamento de erros como:
  - Moeda inválida.
  - Valor inválido.
  - Falha de conexão com a API.

---

## 🛠️ Tecnologias Utilizadas
- **Java 24** (pode funcionar em versões anteriores compatíveis)
- **IntelliJ IDEA** (IDE utilizada no desenvolvimento)
- **API ExchangeRate** → [https://www.exchangerate-api.com](https://www.exchangerate-api.com)
- **Gson** (para conversão de JSON ↔ Objetos Java)

---

## 📂 Estrutura do Projeto
- **Main.java** → Classe principal que gerencia a interação com o usuário.
- **CurrencyConverterService.java** → Responsável pela lógica de conversão de moedas.
- **CurrencyResponse.java** → Classe/record que modela a resposta da API.

---

## 📖 Exemplo de Uso

=== Conversor de Moedas ===
- Digite o valor: 100
- Digite a moeda de origem (ex: USD): USD
- Digite a moeda de destino (ex: BRL): BRL

Resultado: 100 USD = 492.37 BRL

## 🧠 Boas Práticas Implementadas

  Uso de final em atributos imutáveis para segurança e clareza.

    Responsabilidades bem definidas de cada classe.

    Tratamento de exceções para entradas inválidas e erros de rede.

    Código limpo e legível seguindo princípios do Java.

## ✨ Autor

Diovani da Cruz Mangia Maciel Junior
Projeto desenvolvido para o programa Oracle ONE - Back-End Java.

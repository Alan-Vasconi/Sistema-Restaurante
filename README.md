# Esse projeto é um sistema que será aplicado em um restaurante da minha cidade

O sistema final deve conter as seguintes funções:

## Parte de vendas:
- Buscar no banco de dados os pratos, preços e quantidade ✅
- Ser possível efetuar o cadastro de produto na venda, atribuindo quantidade ✅
- Ter função de pagamento nas opções (Crédito, Débito, Dinheiro e PIX) ✅
- Caso for dinheiro calcular o troco e validar se foi inserido o valor correto e suficiente ✅
- Realizar a troca de senha, buscando a anteriormente cadastrada e ir para a seguinte
- Efetuar a geração de nota fiscal
- Poder ser capaz de gerar um relatório final com intervalo de datas

## Parte de Estoque:
- Ser possível efetuar a inserção de produtos novos  ✅
- Remoção de produtos e alteração na sua quantidade ✅
- Efetuação de pedido automático diretamente do apache ou sinalização de falta de estoque

## Segurança:
- Existir dois usuários - Gerente e Funcionário ✅
- Gerente possuir todas as funções administrativas e ser possível requisitar pedidos. 
- Funcionários podem apenas efetuar vendas, não podendo ser gerado relatório.

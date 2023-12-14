# Problema do Caixeiro Viajante em Clojure

Este é um projeto que visa implementar uma solução para o Problema do Caixeiro Viajante (TSP) utilizando a linguagem de programação Clojure.

## Descrição

O Problema do Caixeiro Viajante é um desafio clássico em otimização combinatória, onde o objetivo é encontrar a rota mais curta que passa por todos os pontos de uma lista e retorna ao ponto de origem. Este projeto implementa algoritmos e estruturas de dados em Clojure para resolver este problema.

## Funcionalidades

- Implementação de algoritmos de resolução para o Problema do Caixeiro Viajante.
- Implementação em força bruta, garantindo achar o menor caminho
- Implementação em algoritmo guloso, achando uma boa solução de forma rápida
- Geração parametrizada de mapas aleatórios
- Utilização de mapas definidos em json

## Requisitos

- Clojure instalado (versão 1.11.1)
- Lein
- Bibliotecas:
  - metosin/jsonista (versão 0.3.8)

## Como usar

1. **Instalação:** Clone este repositório.
2. **Execução:** Execute o programa principal com o comando `lein run`.
3. **Entrada de dados:**
   1. A execução sem parâmetros irá informar quais parâmetros informar para executar.
   2. Execução gerando um mapa aleatório: `lein run {greedy|bruteforce|both} {num_cidades} {distancia_cidades}`
   3. Execução com um mapa json: `lein run {greedy|bruteforce|both} {caminho_json}`
4. **Visualização do resultado:** O mapa gerado será impresso em forma de um mapa e também o resultado, caminho encontrado e tempo de execução para o algoritmo.

## Exemplo

```
$ lein run both 6 100
$ lein run greedy ./resources/exemplo_5_cidades.json
```

## Contribuição

Contribuições são bem-vindas! Se você deseja melhorar este projeto, por favor, abra uma issue para discutir as mudanças propostas.

## Licença

Copyright © 2023

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.

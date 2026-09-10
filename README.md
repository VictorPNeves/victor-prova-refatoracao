# Prova de Refatoração — FiapRide

## Contexto

Este repositório contém a refatoração do sistema de cadastro de carros do
aplicativo **FiapRide**. O código original, deixado por um estagiário,
funcionava, mas tinha problemas graves de segurança e não seguia boas
práticas de orientação a objetos.

O objetivo deste trabalho foi analisar, corrigir e blindar o código legado
aplicando os conceitos de **Classes, Métodos, Clean Code e Encapsulamento**.

## Estrutura do projeto

```
prova-refatoracao/
├── diagrama-veiculo-refatorado.png   ← Diagrama de classes (Astah) já corrigido
├── src/
│   └── br/com/fiapride/
│       ├── model/
│       │   └── Veiculo.java          ← Classe refatorada
│       └── main/
│           └── SistemaPrincipal.java ← Classe de teste
└── README.md
```

## O que havia de errado no código original

| Problema | Onde estava | Consequência |
|---|---|---|
| Atributos públicos (`individuo`, `pl`, `gas`) | classe `veiculos` | Qualquer parte do sistema podia alterar o estado do objeto livremente, sem validação |
| `v1.gas = -10;` era permitido | classe `principal` | Tanque de combustível podia ficar negativo, um estado sem sentido no mundo real |
| `gasta(double v)` sem validação | classe `veiculos` | Era possível consumir mais combustível do que o veículo tinha disponível |
| Tipos inconsistentes (`int gas` no diagrama, `double` nos métodos) | diagrama e código | Gerava conversões implícitas e comportamento confuso |
| Nomes de classe em minúsculo (`veiculos`, `principal`) | ambas as classes | Fere a convenção Java de nomear classes em PascalCase e no singular |
| Método `setGas` genérico | diagrama | Permitia burlar qualquer regra de negócio, alterando o combustível diretamente |

## O que foi corrigido

- **Encapsulamento real:** todos os atributos de `Veiculo` agora são `private`.
  O acesso externo só acontece por meio de métodos que garantem a
  consistência do objeto.
- **Regras de negócio dentro da classe:** `abastecer(double litros)` e
  `consumir(double litros)` validam os valores antes de alterar o estado do
  veículo, lançando `IllegalArgumentException` ou `IllegalStateException`
  quando a operação não faz sentido.
- **Remoção do `setGas` livre:** não existe mais uma forma de definir o
  combustível diretamente sem passar pelas regras de negócio.
- **Nomenclatura padronizada:** `Veiculo` e `SistemaPrincipal`, seguindo a
  convenção Java (classes em PascalCase, métodos e atributos em camelCase).
- **Diagrama de classes atualizado:** atributos com visibilidade `-`
  (privada), métodos de negócio com tipos consistentes (`double`) e sem o
  `setGas`.

## Como executar

1. Importe a pasta `src/` como um projeto Java no Eclipse (ou clone o
   repositório e importe via *Existing Projects into Workspace*).
2. Execute a classe `br.com.fiapride.main.SistemaPrincipal`.
3. A saída no console mostra:
   - O estado do veículo após um abastecimento e um consumo válidos;
   - Uma tentativa de consumir mais combustível do que o disponível
     (bloqueada, com mensagem de erro);
   - Uma tentativa de abastecer com valor negativo (bloqueada, com mensagem
     de erro).

## Autor

[seu nome aqui]

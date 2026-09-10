package br.com.fiapride.main;

import br.com.fiapride.model.Veiculo;

/**
 * Classe de teste do sistema.
 *
 * O codigo legado (classe "principal") acessava os atributos publicos
 * diretamente (v1.gas = -10, v1.individuo = "Carlos", etc.), o que permitia
 * estados invalidos, como um tanque de gasolina negativo ou consumo maior
 * que a quantidade disponivel.
 *
 * Com o Veiculo refatorado (atributos privados + metodos de negocio),
 * essas duas falhas de seguranca deixam de ser possiveis: o compilador
 * nem permite mais o acesso direto, e as regras sao garantidas dentro
 * da propria classe Veiculo.
 */
public class SistemaPrincipal {

    public static void main(String[] args) {

        Veiculo v1 = new Veiculo("Carlos", "ABC-1234");

        // v1.gas = -10; // Nao compila mais: atributo privado.
        // Antes isso deixava o tanque negativo. Agora, precisamos usar
        // abastecer(), que rejeita valores <= 0.

        v1.abastecer(50);
        v1.consumir(30);

        System.out.println(v1);

        // Tentativa de consumir mais gasolina do que o veiculo possui.
        try {
            v1.consumir(1000);
        } catch (IllegalStateException e) {
            System.out.println("Erro esperado ao tentar consumir mais do que o disponivel: " + e.getMessage());
        }

        // Tentativa de abastecer com valor negativo.
        try {
            v1.abastecer(-10);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro esperado ao tentar abastecer com valor invalido: " + e.getMessage());
        }

        System.out.println("Estado final -> " + v1);
    }
}

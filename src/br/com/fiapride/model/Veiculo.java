package br.com.fiapride.model;

/**
 * Representa um veiculo cadastrado no sistema FiapRide.
 *
 * Esta classe foi refatorada a partir da versao legada "veiculos" para
 * aplicar os conceitos de Encapsulamento, Clean Code e boas praticas de OO:
 *
 *  - Atributos privados (nao ha acesso direto de fora da classe);
 *  - Nomes de classe, atributos e metodos claros e no padrao Java
 *    (CamelCase para classes, camelCase para atributos/metodos);
 *  - Validacao de regras de negocio dentro da propria classe
 *    (nao existe mais "gas" negativo nem consumo maior que o disponivel);
 *  - Nao existe mais um "setGas" livre: quem quiser alterar o nivel de
 *    combustivel precisa usar os metodos de negocio abastecer()/consumir(),
 *    que garantem a consistencia do objeto.
 */
public class Veiculo {

    private final String proprietario;
    private final String placa;
    private double gasolina;

    public Veiculo(String proprietario, String placa) {
        if (proprietario == null || proprietario.isBlank()) {
            throw new IllegalArgumentException("O nome do proprietario e obrigatorio.");
        }
        if (placa == null || placa.isBlank()) {
            throw new IllegalArgumentException("A placa e obrigatoria.");
        }
        this.proprietario = proprietario;
        this.placa = placa;
        this.gasolina = 0.0;
    }

    /**
     * Adiciona combustivel ao veiculo (abastecimento).
     * Substitui o antigo metodo "adicionar", que aceitava int e nao
     * validava valores negativos.
     */
    public void abastecer(double litros) {
        if (litros <= 0) {
            throw new IllegalArgumentException("A quantidade abastecida deve ser maior que zero.");
        }
        this.gasolina += litros;
    }

    /**
     * Consome combustivel do veiculo.
     * Substitui o antigo metodo "gasta", que permitia consumir mais
     * combustivel do que o veiculo realmente tinha (gas ficando negativo).
     */
    public void consumir(double litros) {
        if (litros <= 0) {
            throw new IllegalArgumentException("A quantidade consumida deve ser maior que zero.");
        }
        if (litros > this.gasolina) {
            throw new IllegalStateException("Combustivel insuficiente para este consumo.");
        }
        this.gasolina -= litros;
    }

    public String getProprietario() {
        return proprietario;
    }

    public String getPlaca() {
        return placa;
    }

    public double getGasolina() {
        return gasolina;
    }

    @Override
    public String toString() {
        return "Dono: " + proprietario + " | Placa: " + placa + " | Gasolina: " + gasolina;
    }
}

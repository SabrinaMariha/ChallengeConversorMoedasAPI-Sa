public class Moeda {

    private String moedaBase;
    private double valorBase;

    public Moeda(String moedaBase, double valorBase) {
        this.moedaBase = moedaBase;
        this.valorBase = valorBase;
    }

    public double getValorBase() {
        return valorBase;
    }

    public void setValorBase(double valorBase) {
        this.valorBase = valorBase;
    }

    public String getMoedaBase() {
        return moedaBase;
    }

    public void setMoedaBase(String moedaBase) {
        this.moedaBase = moedaBase;
    }

    @Override
    public String toString() {
        return "Moeda [moedaBase=" + moedaBase + ", valorBase=" + valorBase + "]";
    }
}

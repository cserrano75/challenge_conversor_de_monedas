public class RegistroConversiones {

    private String monedaOrigen;
    private String monedaDestino;
    private double monto;
    private double tasaConversion;
    private double resultadoConversion;


    public RegistroConversiones (String monedaOrigen, String monedaDestino, double monto, double tasaConversion){
        this.monedaOrigen=monedaOrigen;
        this.monedaDestino=monedaDestino;
        this.monto=monto;
        this.tasaConversion=tasaConversion;
    }


    public String getMonedaOrigen() {
        return monedaOrigen;
    }

    public void setMonedaOrigen(String monedaOrigen) {
        this.monedaOrigen = monedaOrigen;
    }

    public String getMonedaDestino() {
        return monedaDestino;
    }

    public void setMonedaDestino(String monedaDestino) {
        this.monedaDestino = monedaDestino;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getTasaConversion() {
        return tasaConversion;
    }

    public void setTasaConversion(double tasaConversion) {
        this.tasaConversion = tasaConversion;
    }

    public double getResultadoConversion() {
        return this.monto*this.tasaConversion;
    }

    public void setResultadoConversion(double resultadoConversion) {
        this.resultadoConversion = resultadoConversion;
    }
}

public class Postava {

    private String jmeno;
    private String rasa;
    private String datumNarozeni;
    private String typZbrane;
    private int levelZkusenosti;

    //TADY MÁME HEZKY KONSTRUKTOR

    public Postava(String jmeno, String rasa, String datumNarozeni, String typZbrane, int levelZkusenosti) {
        this.jmeno = jmeno;
        this.rasa = rasa;
        this.datumNarozeni = datumNarozeni;
        this.typZbrane = typZbrane;
        this.levelZkusenosti = levelZkusenosti;
    }

    //TADY MÁME HEZKY GETTERY A SETTERY

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getRasa() {
        return rasa;
    }

    public void setRasa(String rasa) {
        this.rasa = rasa;
    }

    public String getDatumNarozeni() {
        return datumNarozeni;
    }

    public void setDatumNarozeni(String datumNarozeni) {
        this.datumNarozeni = datumNarozeni;
    }

    public String getTypZbrane() {
        return typZbrane;
    }

    public void setTypZbrane(String typZbrane) {
        this.typZbrane = typZbrane;
    }

    public int getLevelZkusenosti() {
        return levelZkusenosti;
    }

    public void setLevelZkusenosti(int levelZkusenosti) {
        this.levelZkusenosti = levelZkusenosti;
    }

    //TADY MÁME HEZKY toString výpis

    @Override
    public String toString() {
        return "Postava: " + jmeno + " " + rasa + " " + datumNarozeni + " " + typZbrane + " " + levelZkusenosti;
    }
}

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    //TADY MÁME HEZKY PATTERN PRO JMÉNO
    private Pattern patternJmeno = Pattern.compile("^[A-ZÁČĎÉĚÍŇÓŘŠŤÚŮÝŽ][a-záčďéěíňóřšťúůýž]+$");
    //TADY MÁME HEZKY PATTERN PRO LEVELZKUŠENOSTÍ
    private Pattern patternLevelZkusenosti = Pattern.compile("^\\d+$");

    public boolean validujRadek(String radek, List<String> chyby, List<Postava> platnePostavy) {
        String[] casti = radek.split(";");

        //TADY MÁME HEZKY KONTROLU JESTLI JE 5 ČÁSTÍ A JESTLI NENÍ ŘÁDEK PRÁZDNÝ

        if (casti.length != 5) {
            chyby.add("Chybějící udáj");
            return false;
        }

        if (radek.contains(";;")) {
            chyby.add("Chybějící udáj");
            return false;
        }

        //TADY HEZKY DÁVÁME TRIM ŘÁDKU PODLE ;

        String jmenoPostavy = casti[0].trim();
        String rasaPostavy = casti[1].trim();
        String datumNarozeni = casti[2].trim();
        String typZbrane = casti[3].trim();
        String levelZkusenosti = casti[4].trim();

        boolean ok = true;

        //TADY MÁME HEZKY MATCHER PRO JMÉNO

        Matcher mJmenoPostavy = patternJmeno.matcher(jmenoPostavy);
        if (!mJmenoPostavy.matches()) {
            chyby.add("Neplatné jméno");
            ok = false;
        }

        //TADY MÁME HEZKY KONTROLU ZDA JE TO JEDNA Z 5TI RAS

        if (!rasaPostavy.equals("Elf") &&
                !rasaPostavy.equals("Trpaslík") &&
                !rasaPostavy.equals("Člověk") &&
                !rasaPostavy.equals("Ork") &&
                !rasaPostavy.equals("Hobit")) {
            chyby.add("Neznámá rasa");
            ok = false;
        }

        //TADY MÁME HEZKY KONTROLU FORMÁTU DATUMU

        if (!datumNarozeni.matches("^\\d{1,2}\\.\\d{1,2}\\.\\d{4}$")) {
            System.out.println("Neplatné datum narození");
            ok = false;
        } else {
            try {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d.M.yyyy");
                LocalDate datumN = LocalDate.parse(datumNarozeni, dtf);

                /*if (radek.contains("31.2.")) {
                    System.out.println("Neexistující datum v kalendáři: " + datumNarozeni);
                    ok = false;
                }*/

                if (datumN.isAfter(LocalDate.now())) {
                    System.out.println("Datum narození nemůže být v budoucnosti: " + datumNarozeni);
                    ok = false;
                }
            } catch (Exception e) {
                System.out.println("Neexistující datum v kalendáři: " + datumNarozeni);
                ok = false;
            }
        }

        //TADY MÁME HEZKY KONTROLU ZDA JE TO JEDNA Z 5TI ZBRANÍ

        if (!typZbrane.equals("Meč") &&
                !typZbrane.equals("Kuše") &&
                !typZbrane.equals("Hůl") &&
                !typZbrane.equals("Dýka") &&
                !typZbrane.equals("Sekera")) {
            chyby.add("Neplatný typ zbraně");
            ok = false;
        }

        //TADY MÁME HEZKY KONTROLU ZDA JE LEVEL V ROZSAHU 1-100

        Matcher mLevelZkusenosti = patternLevelZkusenosti.matcher(levelZkusenosti);
        if (!mLevelZkusenosti.matches()) {
            chyby.add("Level mimo rozsah");
            ok = false;
        } else if (Integer.parseInt(levelZkusenosti) < 1 || Integer.parseInt(levelZkusenosti) > 100) {
            chyby.add("Level mimo rozsah");
            ok = false;
        }

        if (ok) {
            platnePostavy.add(new Postava(jmenoPostavy, rasaPostavy, datumNarozeni, typZbrane, Integer.parseInt(levelZkusenosti)));
        }

        return ok;
    }
}
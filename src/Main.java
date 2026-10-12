import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<Postava> postavos = new ArrayList<>();
        Validator validator = new Validator();

        try {
            //TADY HEZKY NAČÍTÁME TEXŤÁK

            File soubor = new File("data.txt");
            Scanner scanner = new Scanner(soubor);

            int cisloRadku = 1;
            while (scanner.hasNextLine()) {
                String radek = scanner.nextLine();
                if (radek.trim().isEmpty()) {
                    cisloRadku++;
                    continue;
                }

                List<String> chyby = new ArrayList<>();
                boolean jePlatny = validator.validujRadek(radek, chyby, postavos);

                //TADY MÁME HEZKY VÝPISY ŘÁDKŮ

                if (jePlatny) {
                    System.out.println("ŘÁDEK " + cisloRadku + ": Žádost postavy " + postavos.getLast().getJmeno() + " je validní");
                } else {
                    System.out.println("ŘÁDEK " + cisloRadku + ": Žádost není validní: " + String.join(", ", chyby));
                }

                cisloRadku++;
            }
            scanner.close();

            //TADY MÁME HEZKY 3 ÚKOLY

            System.out.println(" ");
            System.out.println("SEZNAM POSTAV MLADŠÍCH 18 LET");
            int aktualniRok = java.time.Year.now().getValue();
            boolean nalezenMladsi18 = false;

            /*for (Postava postava : postavos) {
                if (aktualniRok postava.getDatumNarozeni() < 18) {
                    System.out.println(postava.toString());
                    nalezenMladsi18 = true;
                }
            }*/
            if (!nalezenMladsi18) {
                System.out.println("NENÍ TAM NIKDO MLADŠÍ 18TI LET");
            }

            System.out.println(" ");
            System.out.println("VÝPIS POČTU VALIDNÍCH ŽÁDOSTÍ PODLE RASY");
            if (!postavos.isEmpty()) {
                java.util.Map<String, Integer> pocetRas = new java.util.HashMap<>();
                for (Postava p : postavos) {
                    String rasa = p.getRasa();
                    pocetRas.put(rasa, pocetRas.getOrDefault(rasa, 0) + 1);
                }

                java.util.List<java.util.Map.Entry<String, Integer>> seznam = new java.util.ArrayList<>(pocetRas.entrySet());
                seznam.sort(new java.util.Comparator<java.util.Map.Entry<String, Integer>>() {
                    @Override
                    public int compare(java.util.Map.Entry<String, Integer> o1, java.util.Map.Entry<String, Integer> o2) {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                });

                for (java.util.Map.Entry<String, Integer> entry : seznam) {
                    System.out.println(entry.getKey() + ": " + entry.getValue() + "x");
                }
            } else {
                System.out.println("SEZNAM POSTAV JE PRÁZDNÝ.");
            }

            System.out.println(" ");
            System.out.println("POSTAVA S NEJVYŠŠÍM LEVELEM");
            if (!postavos.isEmpty()) {
                Postava nejlepsiPostava = postavos.get(0);
                for (Postava p : postavos) {
                    if (p.getLevelZkusenosti() > nejlepsiPostava.getLevelZkusenosti()) {
                        nejlepsiPostava = p;
                    }
                }
                System.out.println(nejlepsiPostava.toString() + " (LEVEL: " + nejlepsiPostava.getLevelZkusenosti() + ")");
            } else {
                System.out.println("SEZNAM POSTAV JE PRÁZDNÝ.");
            }

        } catch (Exception e) {
            System.out.println("Soubor se nepodařilo načíst.");
            e.printStackTrace();
        }
    }
}

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class deneme {
    public static void main(String[] args) {
        int para;
        int toplam_para = 0;
        int bakiye;
        int adet;

        LinkedHashMap<Integer, Integer> banknot = new LinkedHashMap<>();
        Scanner klavye = new Scanner(System.in);
        System.out.println("çekmek istediğiniz parayı giriniz...");
        para = klavye.nextInt();

        try {
            String satir;
            banknot = new LinkedHashMap<Integer, Integer>();

            File file = new File("C://mine-development//bankamatik//src//bankamatik.csv");
            BufferedReader br = new BufferedReader(new FileReader(file));
            try {
                while ((satir = br.readLine()) != null) {
                    String[] bölüm = satir.split(";");

                    banknot.put(new Integer(bölüm[0]), new Integer(bölüm[1]));
                    toplam_para += (new Integer(bölüm[0]) * new Integer(bölüm[1]));
                }
            } catch (FileNotFoundException e) {
                System.out.println("dosya bulunamadı");
                e.printStackTrace();
            }
            bakiye = toplam_para - para;

            for (Map.Entry<Integer, Integer> bölüm : banknot.entrySet()) {
                Integer tip = bölüm.getKey();
                Integer sayi = bölüm.getValue();
                adet = para / bölüm.getKey();

                if (bölüm.getValue() < adet) {
                    adet = bölüm.getValue();
                } else {
                    para %= bölüm.getKey();
                }
                if (adet != 0 && sayi != 0) {
                    System.out.println(tip + "tlden" + adet + "kalmıştır");
                }
            }
        } catch (Exception e) {

        }
    }
}

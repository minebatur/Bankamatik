import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        int para;
        int toplam_para = 0;
        int adet;
        int bakiye;

        //HashMap<Integer, Integer> banknot = new HashMap<Integer, Integer>();
        LinkedHashMap<Integer, Integer> banknot = new LinkedHashMap<>();
        Scanner klavye = new Scanner(System.in);
        System.out.println("Çekmek istediğiniz para miktarını giriniz: (TL): ");
        para = klavye.nextInt();

        try {
            String satir;
            banknot = new LinkedHashMap<Integer, Integer>();

            File file = new File("C://mine-development//bankamatik//src//bankamatik.csv");
            BufferedReader br = new BufferedReader(new FileReader(file));
            //BufferedReader in= new BufferedReader(new FileReader("dosya.in"));
            //kodları, dosyayı bir bütün olarak buffer’a koyar. Oysa doğrudan read() ya da ReadLine() metotları
            //kullanılırsa, her okuma sonunda buffer’a giren verilerin byte ya da karektere dönüşmesi gerekir.


            try {
                while ((satir = br.readLine()) != null) {
                    String[] bölüm = satir.split(";");

                    banknot.put(new Integer(bölüm[0]), new Integer(bölüm[1]));//put (Object key, Object value): Anahtar - değer ikilisini kayıt eder.
                    toplam_para += (new Integer(bölüm[0]) * new Integer(bölüm[1]));

                }
            } catch (FileNotFoundException e) {
                System.out.print("bankamatik.txt dosyasi bulunamadi");
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
                    System.out.println(tip + " Tl'den" + " = " + adet + " adet var");
                }
            }
            if (para < bakiye) {
                System.out.println("Kalan Bakiyeniz " + bakiye + " TL'dir.");
                System.out.println("Paranızı Gösterilen Yerden Alabilirsiniz...");
            } else {
                System.out.println("bakiyeniz yetersiz");
            }

        } catch (Exception e) {
        }
    }
}


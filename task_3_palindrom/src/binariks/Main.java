package binariks;


import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();


        String word = new String("qwertyuiopasdfghqweqwertyqwertyuiopasdfghjklmnbvcxzsdfeqwertyqwertyuiioopasdfghjklmnbvcxzsdfgqwertyuiopasdfghjklmnbvcxzsdfguiopasdfghjklmnbvcxzsdfgrtyuiogqwertyuiopasdfghjklmnbvcxzsdfguiopasdfghjklmnbvcxzsdfgrtyuioqwqweqwertyqwertyuiopasdfghjklmnbvcxzsdfgqwertyuiopasdfghjklmnbvcxzsdfguiopasdfghjklmnbvcxzsdfgrtyuiopasdfghjklmnbvcxzsdfgertyuiopasdfghjklmnbvcxzsdfgertyuiopasdfghjklmnbvcxzsdfgjklmnbvcxzsdfgqwertyuiopasdfghjklmnbvcxzsdfg");
        System.out.println("start: "+start);

        canCreatePalindrom(word);

        long stop = System.currentTimeMillis();
        System.out.println("stop: "+stop);
        System.out.println((stop-start));//time im milisekund
    }

    static void canCreatePalindrom(String word){
        String[] check = word.split("");
        HashMap<String, Integer> cheking = new HashMap<String, Integer>();

        for (int i = 0; i < check.length; i++) {
            if (cheking.containsKey(check[i]))
                cheking.put(check[i], cheking.get(check[i]) + 1);//збільшеємо каунтер букви на один якщо літера вже існує
            else {
                cheking.put(check[i], 1);
            }

        }
        int count = 0;
        for (String entr: cheking.keySet()) {
            if (cheking.get(entr)%2!=0) {
                count++;
                if (count > 1) {
                    System.out.println("impossible");
                    break;
                }
            }
        }
        if (count<2)
            System.out.println("Possible");
    }

}

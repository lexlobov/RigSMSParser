import java.util.Optional;

public class Main {

    public static void main(String[] args){

        ReceiveSmsParser parser = new ReceiveSmsParser();
        String n = "17602781253";
        String code = parser.getLastSmsCodeForMobile(n).orElse("N/A");
        System.out.println(code);
    }
}

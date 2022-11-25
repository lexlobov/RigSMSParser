public class Main {

    public static void main(String[] args){

        ReceiveSmsParser parser = new ReceiveSmsParser();
        String n = "17602781253";
        System.out.println(parser.getLastSmsCodeForMobile(n));
    }
}

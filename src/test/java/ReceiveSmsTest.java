import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class ReceiveSmsTest {

    String testedNumber = "18057651210";

    @Test
    public void receiveSmsTest(){
        ReceiveSmsParser parser = new ReceiveSmsParser();
        String smsCode = parser.getLastSmsCode(testedNumber);
        System.out.println(parser);
        assertEquals(smsCode.equals("null"), false);
    }
}

import jdk.jfr.Description;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class ReceiveSmsTest {

    String testedNumber = "18057651210";

    @Test
    // Позитивный тест. Перед запуском нужно, чтобы Rig отправил сообщение на testedNumber
    public void receiveSmsTest(){
        ReceiveSmsParser parser = new ReceiveSmsParser();
        String json = parser.getLastSmsCode(testedNumber);
        System.out.println(parser);
        System.out.println(json);
        assertEquals(parser.isSuccess(), true);
    }
}

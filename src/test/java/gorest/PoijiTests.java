package gorest;

import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;
import gorest.pojos.GorestPoiji;

import java.io.File;
import java.util.List;

public class PoijiTests {
    public static void main(String[] args) {
        PoijiOptions option=PoijiOptions.PoijiOptionsBuilder.settings().addListDelimiter(";").build();
        List<GorestPoiji> data=Poiji.fromExcel(new File("src/test/resources/testData/PayloadExcel.xlsx"), GorestPoiji.class,option);
        System.out.println(data);
    }
}

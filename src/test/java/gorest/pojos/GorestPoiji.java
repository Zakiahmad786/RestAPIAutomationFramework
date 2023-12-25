package gorest.pojos;

import com.poiji.annotation.ExcelCell;
import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelUnknownCells;
import lombok.ToString;

import java.util.Map;

@ToString
public class GorestPoiji {

   // @ExcelCell(0)......Better to give name instead of index
    @ExcelCellName("name")
    private String name;
    @ExcelCellName("gender")
    private String gender;

    @ExcelCellName("email")
    private String email;
// Let's consider user have more than one email and wants to pass it then with the help of List we can achieve it

   // @ExcelCellName("email")
    //private List<String> email;

    @ExcelCellName("status")
    private String status;

    //Let's consider we are not sure how many cells will be there in our excel file , kind of dynamic then with the help of ExcelUnknownCells we can handle it.
    @ExcelUnknownCells
    private Map<String,String> extraCells;
}

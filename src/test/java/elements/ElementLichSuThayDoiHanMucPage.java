package elements;

import net.serenitybdd.screenplay.targets.Target;

public class ElementLichSuThayDoiHanMucPage {
    public static final Target soLuong = Target.the("So luong ban ghi")
            .locatedBy("//span[text() = 'Số lượng']/../div/span");
}

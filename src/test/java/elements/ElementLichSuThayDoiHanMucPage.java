package elements;

import net.serenitybdd.screenplay.targets.Target;

public class ElementLichSuThayDoiHanMucPage {
    public static final Target soLuong = Target.the("So luong ban ghi")
            .locatedBy("//span[text() = 'Số lượng']/../div/span");
    public static String spanValueFields(String nameField) {
        return "//div[text() = 'Điều chỉnh hạn mức']/ancestor::div[@role = 'dialog']//label[@title = '"+nameField+"']/../..//span[@class = 'ant-select-selection-item']";
    }

    public static String inputValueFields(String nameField) {
        return "//div[text() = 'Điều chỉnh hạn mức']/ancestor::div[@role = 'dialog']//label[@title = '"+nameField+"']/../..//input";
    }
}

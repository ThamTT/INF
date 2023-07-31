Feature: UI Lich su thay doi han muc

  @LSHM_001
  Scenario: Kiem tra UI danh sach lich su thay doi san pham
    Given "Actor" Navigate successfully to Login Page on "staging"
    When Input valid account with "OPS" role
    And User click login button
    Then Verify login successfully with message "Chào mừng bạn đến với Innofin."
    And Go to menu
      | Lịch sử thay đổi hạn mức |
    Then Verify item show on the page
      | Lọc nâng cao                           |
      | Điều chỉnh hạn mức                     |
      | $placeholder_Tìm kiếm tên GDV, đội thu |
      | Số lượng                               |
      | Tên đội thu                            |
      | Giao dịch viên                         |
      | Kiểu hạn mức                           |
      | Hình thức                              |
      | Loại hạn mức                           |
      | Trước thay đổi                         |
      | Sau thay đổi                           |
      | TG thực hiện                           |
      | Người thực hiện                        |

  @LSHM_002
  Scenario: Kiem tra so luong ban ghi trong bang Lich su thay doi
    Given "Actor" Navigate successfully to Login Page on "staging"
    When Input valid account with "OPS" role
    And User click login button
    Then Verify login successfully with message "Chào mừng bạn đến với Innofin."
    And Go to menu
      | Lịch sử thay đổi hạn mức |
    Then Verify so luong ban ghi

  @LSHM_003
  Scenario: Kiem tra chi tiet ban ghi
#    Given "Actor" Navigate successfully to Login Page on "staging"
#    When Input valid account with "OPS" role
#    And User click login button
#    Then Verify login successfully with message "Chào mừng bạn đến với Innofin."
#    When Go to menu
#      | Lịch sử thay đổi hạn mức |
#    And Click chi tiet record1
    And Get value span field in Chi Tiet form
      | Đối tượng                |
      | Giao dịch viên / Đội thu |
      | Kiểu hạn mức             |
      | Loại hạn mức             |
      | Hình thức                |
    And Get value input fields in Chi Tiet form
      | Tổng hạn mức hiện tại           |
      | Hạn mức nắm giữ tối đa          |
      | Số tiền hiện tại                |
      | Số tiền điều chỉnh              |
      | Tổng hạn mức sau khi điều chỉnh |
    Given Send Api Login with username and password "DataProject.json_validAccount_OPS20"
    And Get "ID" record by API with param
      | Key  | Value |
      | page | 0     |
      | size | 50    |
    Given Get all record table "collector_limits_fluctuations" via condition in DB
      | Column | Value               |
      | id     | key_IDRecordHistory |
    And Add map record in DB

  @LSHM_004
  Scenario: Kiem tra phan trang
    Given "Actor" Navigate successfully to Login Page on "staging"
    When Input valid account with "OPS" role
    And User click login button
    Then Verify login successfully with message "Chào mừng bạn đến với Innofin."
    When Go to menu
      | Lịch sử thay đổi hạn mức |
    And Select "10" item on one page
    And Click chi tiet record1
    And Get number of record in the table
    Then Verify records in the list
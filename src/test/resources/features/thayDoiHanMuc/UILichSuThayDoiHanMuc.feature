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

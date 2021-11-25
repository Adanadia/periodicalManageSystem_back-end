package top.ahcdc.periodical.form;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterForm {
    @NotBlank(message = "用户名不能为空")
    private String user_num;
    @NotBlank(message = "昵称不能为空")
    private String user_name;
    @NotBlank(message = "密码不能为空")
    private String password;
    @Email(message = "邮箱格式错误")
    private String email;
}

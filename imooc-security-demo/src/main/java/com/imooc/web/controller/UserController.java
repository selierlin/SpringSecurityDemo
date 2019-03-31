package com.imooc.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.User;
import com.imooc.dto.UserQueryCondition;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    //在Controller方法上指定视图
    @JsonView(User.UserSimpleView.class)
    @GetMapping
    public List<User> query(UserQueryCondition condition
            , @PageableDefault(page = 2, size = 10, sort = "username,desc") Pageable pageable) {
        //System.out.println(userQueryCondition);
        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
        System.out.println(ReflectionToStringBuilder.toString(pageable, ToStringStyle.MULTI_LINE_STYLE));
        ArrayList<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    //在Controller方法上指定视图
    @JsonView(User.UserDetailView.class)
    @GetMapping("/{id:\\d+}")//id:\d+表示正则匹配数字
    public User getInfo(@PathVariable String id) {
        System.out.println("进入getInfo服务");
        User user = new User();
        user.setUsername("tom");
        return user;
    }

    @PostMapping
    public User create(@Valid @RequestBody User user //@Valid验证请求参数的合法性
            , BindingResult errors) {//BingdResult 查看错误信息，处理检验结果
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
        }
        //获取到的时间戳会自己被转化成Date类型
        System.out.println(ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));
        user.setId("1");
        return user;
    }

    @PutMapping("/{id:\\d+}")
    public User update(@Valid @RequestBody User user, BindingResult errors) {

        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error -> {
                FieldError fieldError = (FieldError) error;
                String message = fieldError.getField() +":"+ error.getDefaultMessage();
                System.out.println(message);
                /**
                 * 控制台输出：
                 * birthday:must be in the past
                 * password:may not be empty
                 */
            });
        }
        System.out.println(user.getId());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getBirthday());

        user.setId("1");
        return user;
    }

	/*@PostMapping("/regist")
	public void regist(User user, HttpServletRequest request) {

		//不管是注册用户还是绑定用户，都会拿到一个用户唯一标识。
		String userId = user.getUsername();
		providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
	}

	@GetMapping("/me")
	public Object getCurrentUser(@AuthenticationPrincipal UserDetails user) {
		return user;
	}





	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable String id) {
		System.out.println(id);
	}*/

}

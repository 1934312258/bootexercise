package com.kevin.javaDemo.controllerValidation;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author kevin
 * @date 2020-7-22 14:56
 * @description todo
 * @Null 被注释的元素必须为null
 * @NotNull 不能为null
 * @AssertTrue 被注释的元素必须为true
 * @AssertFalse 被注释的元素必须为false
 * @Min(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 * @Max(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 * @DecimalMin（value）被注释的元素必须是一个数字，其值必须大于等于指定的最小值，当数值大于long的最大值时使用
 * @DecimalMax(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 * @Size(max,min) 被注释的元素的大小必须在指定的范围内
 * @Digits（integer，fraction）被注释的元素必须是一个数字，其值必须在可接受的范围内
 * @Past 被注释的元素必须是一个过去的日期
 * @Future 被注释的元素必须是一个将来的日期
 * @Pattern（value）被注释的元素必须符合指定的正则表达式
 **/
public class User {
    //分组控制不通逻辑使用不同的校验规则，此处只有修改的方法才会校验主键
    @NotNull(message = "更新操作作id不能为空", groups = Update.class)
    private Long Id;

    @NotBlank(message = "姓名不能为空")
    private String name;

    @Min(value = 18, message = "年龄须大于18")
    @NotNull(message = "年龄不能为空")
    private Integer age;

    @Email(message = "非法的邮箱格式")
    private String email;

    @Length(min = 11, max = 11, message = "非法的手机号")
    @Pattern(regexp = "^1([34578])\\d{9}$", message = "非法的手机号")
    @NotBlank(message = "手机号不能为空")
    private String phone;


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

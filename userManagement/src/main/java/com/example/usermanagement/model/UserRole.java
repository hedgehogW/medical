package com.example.usermanagement.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * @author wyz
 * @date 2024/10/12
 * @version 1.0.0
 */

@Data
@ApiModel("用户-角色对应关系实体类")
@TableName("user_role")
@AllArgsConstructor
@NoArgsConstructor
public class UserRole implements Serializable {

    public static final Long SerialVersionUID = 23451L;

    @TableId("id")
    private Long id;

    private Long userId;

    private Long roleId;

}

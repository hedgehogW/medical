package com.example.usermanagement.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("department")
public class Department implements Serializable {
    @TableId("department_id")
    private Long departmentId;

    private Long hospitalId;

    private String departmentName;

    private static final long serialVersionUID = 1L;

//    public Long getDepartmentId() {
//        return departmentId;
//    }
//
//    public void setDepartmentId(Long departmentId) {
//        this.departmentId = departmentId;
//    }
//
//    public Long getHospitalId() {
//        return hospitalId;
//    }
//
//    public void setHospitalId(Long hospitalId) {
//        this.hospitalId = hospitalId;
//    }
//
//    public String getDepartmentName() {
//        return departmentName;
//    }
//
//    public void setDepartmentName(String departmentName) {
//        this.departmentName = departmentName == null ? null : departmentName.trim();
//    }

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(getClass().getSimpleName());
//        sb.append(" [");
//        sb.append("Hash = ").append(hashCode());
//        sb.append(", departmentId=").append(departmentId);
//        sb.append(", hospitalId=").append(hospitalId);
//        sb.append(", departmentName=").append(departmentName);
//        sb.append(", serialVersionUID=").append(serialVersionUID);
//        sb.append("]");
//        return sb.toString();
//    }
//
//    @Override
//    public boolean equals(Object that) {
//        if (this == that) {
//            return true;
//        }
//        if (that == null) {
//            return false;
//        }
//        if (getClass() != that.getClass()) {
//            return false;
//        }
//        Department other = (Department) that;
//        return (this.getDepartmentId() == null ? other.getDepartmentId() == null : this.getDepartmentId().equals(other.getDepartmentId()))
//            && (this.getHospitalId() == null ? other.getHospitalId() == null : this.getHospitalId().equals(other.getHospitalId()))
//            && (this.getDepartmentName() == null ? other.getDepartmentName() == null : this.getDepartmentName().equals(other.getDepartmentName()));
//    }
//
//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((getDepartmentId() == null) ? 0 : getDepartmentId().hashCode());
//        result = prime * result + ((getHospitalId() == null) ? 0 : getHospitalId().hashCode());
//        result = prime * result + ((getDepartmentName() == null) ? 0 : getDepartmentName().hashCode());
//        return result;
//    }
}
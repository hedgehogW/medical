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
@TableName("doctor")
public class Doctor implements Serializable {

    @TableId("doctor_id")
    private Long doctorId;

    private Long hospitalId;

    private Long departmentId;

    private String doctorName;

    private String specialty;

    private Boolean available;

    private static final long serialVersionUID = 1L;

//    public Long getDoctorId() {
//        return doctorId;
//    }
//
//    public void setDoctorId(Long doctorId) {
//        this.doctorId = doctorId;
//    }
//
//    public Long getDepartmentId() {
//        return departmentId;
//    }
//
//    public void setDepartmentId(Long departmentId) {
//        this.departmentId = departmentId;
//    }
//
//    public String getDoctorName() {
//        return doctorName;
//    }
//
//    public void setDoctorName(String doctorName) {
//        this.doctorName = doctorName == null ? null : doctorName.trim();
//    }
//
//    public String getSpecialty() {
//        return specialty;
//    }
//
//    public void setSpecialty(String specialty) {
//        this.specialty = specialty == null ? null : specialty.trim();
//    }
//
//    public Boolean getAvailable() {
//        return available;
//    }
//
//    public void setAvailable(Boolean available) {
//        this.available = available;
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(getClass().getSimpleName());
//        sb.append(" [");
//        sb.append("Hash = ").append(hashCode());
//        sb.append(", doctorId=").append(doctorId);
//        sb.append(", departmentId=").append(departmentId);
//        sb.append(", doctorName=").append(doctorName);
//        sb.append(", specialty=").append(specialty);
//        sb.append(", available=").append(available);
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
//        Doctor other = (Doctor) that;
//        return (this.getDoctorId() == null ? other.getDoctorId() == null : this.getDoctorId().equals(other.getDoctorId()))
//            && (this.getDepartmentId() == null ? other.getDepartmentId() == null : this.getDepartmentId().equals(other.getDepartmentId()))
//            && (this.getDoctorName() == null ? other.getDoctorName() == null : this.getDoctorName().equals(other.getDoctorName()))
//            && (this.getSpecialty() == null ? other.getSpecialty() == null : this.getSpecialty().equals(other.getSpecialty()))
//            && (this.getAvailable() == null ? other.getAvailable() == null : this.getAvailable().equals(other.getAvailable()));
//    }
//
//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((getDoctorId() == null) ? 0 : getDoctorId().hashCode());
//        result = prime * result + ((getDepartmentId() == null) ? 0 : getDepartmentId().hashCode());
//        result = prime * result + ((getDoctorName() == null) ? 0 : getDoctorName().hashCode());
//        result = prime * result + ((getSpecialty() == null) ? 0 : getSpecialty().hashCode());
//        result = prime * result + ((getAvailable() == null) ? 0 : getAvailable().hashCode());
//        return result;
//    }
}
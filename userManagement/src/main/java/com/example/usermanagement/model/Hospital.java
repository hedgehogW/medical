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
@TableName("hospital")
public class Hospital implements Serializable {
    @TableId("hospital_id")
    private Long hospitalId;

    private String hospitalName;

    private static final long serialVersionUID = 1L;

//    public Long getHospitalId() {
//        return hospitalId;
//    }
//
//    public void setHospitalId(Long hospitalId) {
//        this.hospitalId = hospitalId;
//    }
//
//    public String getHospitalName() {
//        return hospitalName;
//    }
//
//    public void setHospitalName(String hospitalName) {
//        this.hospitalName = hospitalName == null ? null : hospitalName.trim();
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append(getClass().getSimpleName());
//        sb.append(" [");
//        sb.append("Hash = ").append(hashCode());
//        sb.append(", hospitalId=").append(hospitalId);
//        sb.append(", hospitalName=").append(hospitalName);
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
//        Hospital other = (Hospital) that;
//        return (this.getHospitalId() == null ? other.getHospitalId() == null : this.getHospitalId().equals(other.getHospitalId()))
//            && (this.getHospitalName() == null ? other.getHospitalName() == null : this.getHospitalName().equals(other.getHospitalName()));
//    }
//
//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((getHospitalId() == null) ? 0 : getHospitalId().hashCode());
//        result = prime * result + ((getHospitalName() == null) ? 0 : getHospitalName().hashCode());
//        return result;
//    }
}
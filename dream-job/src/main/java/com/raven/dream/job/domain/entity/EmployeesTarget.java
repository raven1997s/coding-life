package com.raven.dream.job.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@ApiModel
@Table(name = "t_employees_target")
public class EmployeesTarget {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name")
	@ApiModelProperty(value = "")
	private String firstName;

	@Column(name = "last_name")
	@ApiModelProperty(value = "")
	private String lastName;

	@Column(name = "salary")
	@ApiModelProperty(value = "")
	private BigDecimal salary;

}


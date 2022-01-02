package com.taiwanlife.paratest.domain.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Function implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private int parentId;
	private String name;
	private String url;
}

package com.example.demo.domain.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Table("user")
public class DbUser {

  @Id
  private Integer id;

  private String username;
}

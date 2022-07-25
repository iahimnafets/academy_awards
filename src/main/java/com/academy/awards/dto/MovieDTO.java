package com.academy.awards.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDTO  {

  private String  year;
  private Integer rate;
  private String  category;
  private String  nominee;
  private String  additionalInfo;
  private String  won;
}

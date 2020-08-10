package com.code.training.step1.data;

import java.io.File;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CodeInfo {
  private File file;
  private String fileName;
  private Long lineCount;

  @Override
  public String toString() {
    return "{" +
        "fileName='" + fileName + '\'' +
        ", lineCount=" + lineCount +
        '}';
  }
}

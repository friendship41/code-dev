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
  private String content;
  private Long lineCount;
  private float stringRatio;

  @Override
  public String toString() {
    return "CodeInfo{" +
        "fileName='" + fileName + '\'' +
        ", lineCount=" + lineCount +
        ", stringRatio=" + stringRatio +
        '}';
  }
}

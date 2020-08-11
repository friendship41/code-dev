package com.code.training.step1.service;

import com.code.training.step1.data.CodeInfo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public abstract class LineCountService {
  public List<CodeInfo> countAllLine(final List<CodeInfo> codeInfoList) {
    if (codeInfoList == null || codeInfoList.size() == 0) {
      return null;
    }

    for (CodeInfo codeInfo : codeInfoList) {
      this.countFileLine(codeInfo);
    }

    return codeInfoList;
  }

  private void countFileLine(CodeInfo codeInfo) {

    BufferedReader bufferedReader;
    try {
      bufferedReader = new BufferedReader(new FileReader(codeInfo.getFile()));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return;
    }

    String line;
    Long result = 0L;
    StringBuilder stringBuilder = new StringBuilder();
    try {
      while ((line = bufferedReader.readLine()) != null) {
        stringBuilder.append(line.trim());
        if (this.filterStrategy(line)) {
          result++;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }
    codeInfo.setLineCount(result);
    codeInfo.setContent(stringBuilder.toString());
  }

  protected abstract boolean filterStrategy(final String line);
}

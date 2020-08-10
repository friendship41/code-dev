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
      codeInfo.setLineCount(this.countFileLine(codeInfo.getFile()));
    }

    return codeInfoList;
  }

  private Long countFileLine(File file) {

    BufferedReader bufferedReader;
    try {
      bufferedReader = new BufferedReader(new FileReader(file));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return null;
    }

    String line;
    Long result = 0L;
    try {
      while ((line = bufferedReader.readLine()) != null) {
        if (this.filterStrategy(line)) {
          result++;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
    return result;
  }

  protected abstract boolean filterStrategy(final String line);
}

package com.code.training.step1.service;

import com.code.training.step1.data.CodeInfo;
import java.util.List;
import java.util.StringTokenizer;

public class StyleCheckServiceImpl implements StyleCheckService {
  @Override
  public List<CodeInfo> checkStringRatio(final List<CodeInfo> codeInfoList) {
    codeInfoList.forEach(codeInfo -> codeInfo.setStringRatio(this.calcStringRatio(codeInfo)));
    return codeInfoList;
  }

  @Override
  public List<CodeInfo> checkRemarkRatio(final List<CodeInfo> codeInfoList) {
    codeInfoList.forEach(codeInfo -> codeInfo.setRemarkRatio(this.calcRemarkRatio(codeInfo)));
    return codeInfoList;
  }

  private float calcStringRatio(final CodeInfo codeInfo) {
    StringTokenizer stringTokenizer = new StringTokenizer(
        codeInfo.getContent().replace(" ", ""), ";");
    double result = 0f;
    while (stringTokenizer.hasMoreTokens()) {
      String line = stringTokenizer.nextToken();
      if (!line.contains("\"")) {
        continue;
      }
      result += this.countStringWord(line);
    }

    return (float) (result/codeInfo.getContent().replace(" ", "").length());
  }

  private double countStringWord(final String line) {
    boolean addFlag = false;
    double result = 0;

    String[] parsedStr = line.split("\"");
    for (String str : parsedStr) {
      if (addFlag) {
        result += str.length();
        if (str.charAt(str.length()-1) != '\\') {
          addFlag = false;
        }
      } else {
        addFlag = true;
      }
    }

    return result;
  }

  private float calcRemarkRatio(final CodeInfo codeInfo) {
    StringTokenizer stringTokenizer = new StringTokenizer(
        codeInfo.getContent().replace(" ", ""), ";");
    double remarkWordCount = 0;
    while (stringTokenizer.hasMoreTokens()) {
      String line = stringTokenizer.nextToken();
      if (line.startsWith("//") || line.startsWith("*") || line.startsWith("/*") || line.startsWith("*/")) {
        remarkWordCount += line.length();
      }
    }

    return (float) (remarkWordCount/codeInfo.getContent().replace(" ", "").length());
  }

}

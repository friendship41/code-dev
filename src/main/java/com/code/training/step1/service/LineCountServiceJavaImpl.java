package com.code.training.step1.service;

public class LineCountServiceJavaImpl extends LineCountService {

  @Override
  protected boolean filterStrategy(final String line) {
    if (line == null) {
      return false;
    }

    String trimedLine = line.trim();
    switch (trimedLine) {
      case "":
      case "{":
      case "}":
      case "(":
      case ")":
        return false;
    }
    if (trimedLine.length() > 1) {
      return !trimedLine.startsWith("//")
          && !trimedLine.startsWith("/*")
          && !trimedLine.startsWith("*")
          && !trimedLine.startsWith("*/");
    }

    return true;
  }
}

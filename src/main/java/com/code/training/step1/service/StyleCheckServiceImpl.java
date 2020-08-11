package com.code.training.step1.service;

import com.code.training.step1.data.CodeInfo;
import com.code.training.step1.data.WordInfo;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicBoolean;

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

  @Override
  public List<CodeInfo> checkWordRanking(final List<CodeInfo> codeInfoList) {
    codeInfoList.forEach(codeInfo -> {
      List<WordInfo> wordInfoList = countWords(codeInfo);
      wordInfoList.sort(Collections.reverseOrder());
      codeInfo.setWordInfoList(wordInfoList);
    });

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

  private List<WordInfo> countWords(final CodeInfo codeInfo) {
    Map<String, Long> wordsMap = new HashMap<>();
    StringTokenizer stringTokenizer = new StringTokenizer(codeInfo.getContent(), ";");
    while (stringTokenizer.hasMoreTokens()) {
      String line = stringTokenizer.nextToken();
      char[] chArray = line.toCharArray();
      StringBuffer stringBuffer = new StringBuffer();

      CharBuffer.wrap(chArray).chars().forEach(ch -> {
        if (ch >= 'a' && ch <= 'z') {
          stringBuffer.append((char) ch);
        } else if (ch >= 'A' && ch <= 'Z') {
          if (stringBuffer.length() != 0) {
            addWordToMap(wordsMap, stringBuffer.toString());
            stringBuffer.setLength(0);
          }
          stringBuffer.append(String.valueOf((char) ch).toLowerCase());
        } else {
          if (stringBuffer.length() != 0) {
            addWordToMap(wordsMap, stringBuffer.toString());
          }
          stringBuffer.setLength(0);
        }
      });
      if (stringBuffer.length() != 0) {
        addWordToMap(wordsMap, stringBuffer.toString());
      }
    }

    List<WordInfo> wordInfoList = new ArrayList<>();
    wordsMap.entrySet().stream()
        .map(entry -> new WordInfo(entry.getKey(), entry.getValue()))
        .forEach(wordInfoList::add);

    return wordInfoList;
  }

  private void addWordToMap(Map<String, Long> wordsMap, String word) {
    Long temp = wordsMap.get(word);
    wordsMap.put(word, temp == null ? 1L : temp+1);
  }
}

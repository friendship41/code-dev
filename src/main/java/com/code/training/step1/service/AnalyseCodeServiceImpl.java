package com.code.training.step1.service;

import com.code.training.step1.data.CodeInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AnalyseCodeServiceImpl implements AnalyseCodeService {
  @Override
  public List<CodeInfo> analyseCode(final String rootPath) {
    FileSearchService fileSearch = new FileSearchServiceImpl();
    LineCountService lineCountService = new LineCountServiceJavaImpl();
    StyleCheckService styleCheckService = new StyleCheckServiceImpl();

    List<File> fileList = fileSearch.getAllFile(rootPath, "java");

    List<CodeInfo> codeInfoList = new ArrayList<>();
    if (fileList != null) {
      fileList.forEach(file ->
          codeInfoList.add(CodeInfo.builder()
          .file(file)
          .fileName(file.getName())
          .build()));
      lineCountService.countAllLine(codeInfoList);
    }

    styleCheckService.checkStringRatio(codeInfoList);
    styleCheckService.checkRemarkRatio(codeInfoList);

    return codeInfoList;
  }
}

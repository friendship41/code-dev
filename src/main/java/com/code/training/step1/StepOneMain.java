package com.code.training.step1;

import com.code.training.step1.data.CodeInfo;
import com.code.training.step1.service.FileSearchService;
import com.code.training.step1.service.FileSearchServiceImpl;
import com.code.training.step1.service.LineCountService;
import com.code.training.step1.service.LineCountServiceJavaImpl;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StepOneMain {
  public static void main(String[] args) {
    FileSearchService fileSearch = new FileSearchServiceImpl();
    LineCountService lineCountService = new LineCountServiceJavaImpl();
    List<File> fileList = fileSearch.getAllFile("D:\\1_workspace\\intelliJ\\comingsoon\\src\\main\\java\\com\\web" +
        "\\comingsoon", "java");

    List<CodeInfo> codeInfoList = new ArrayList<>();
    if (fileList != null) {
      fileList.forEach(file -> {
        System.out.println(file.getName());
        codeInfoList.add(CodeInfo.builder()
            .file(file)
            .fileName(file.getName())
            .build());
      });
      System.out.println("lineCount: "+lineCountService.countAllLine(codeInfoList));
    }
  }
}

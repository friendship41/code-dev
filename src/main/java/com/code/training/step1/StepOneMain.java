package com.code.training.step1;

import com.code.training.step1.data.CodeInfo;
import com.code.training.step1.service.AnalyseCodeService;
import com.code.training.step1.service.AnalyseCodeServiceImpl;
import java.util.List;

public class StepOneMain {
  public static void main(String[] args) {
    AnalyseCodeService analyseCodeService = new AnalyseCodeServiceImpl();
    List<CodeInfo> codeInfoList = analyseCodeService.analyseCode(
        "D:\\1_workspace\\intelliJ\\comingsoon\\" +
            "src\\main\\java\\com\\web\\comingsoon", "java");
    codeInfoList.forEach(System.out::println);
  }
}

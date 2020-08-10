package com.code.training.step1.service;

import com.code.training.step1.data.CodeInfo;
import java.util.List;

public interface AnalyseCodeService {
  List<CodeInfo> analyseCode(String rootPath);
}

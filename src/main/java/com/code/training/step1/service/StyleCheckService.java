package com.code.training.step1.service;

import com.code.training.step1.data.CodeInfo;
import java.util.List;

public interface StyleCheckService {
  List<CodeInfo> checkStringRatio(List<CodeInfo> codeInfoList);
  List<CodeInfo> checkRemarkRatio(List<CodeInfo> codeInfoList);
  List<CodeInfo> checkWordRanking(List<CodeInfo> codeInfoList);
}

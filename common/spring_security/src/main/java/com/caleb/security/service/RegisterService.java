package com.caleb.security.service;

import com.caleb.common_utils.Result;
import com.caleb.security.entity.vo.*;

public interface RegisterService {

    Result judgeRegister(RegisterJudgeReqVo registerVo);

    Result submitRegister(RegisterReqVo registerVo);
}

package top.ahcdc.periodical.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.ahcdc.periodical.common.lang.CommonResponse;
import top.ahcdc.periodical.service.BorrowService;
import top.ahcdc.periodical.utils.JWTUtils;
import top.ahcdc.periodical.vo.BorrowPageVO;
import top.ahcdc.periodical.vo.integration.PeriodicalNotBorrowVO;

@RestController
public class BorrowController {
    @Autowired
    BorrowService borrowService;
    @CrossOrigin
    @GetMapping("/borrow")
    public CommonResponse<BorrowPageVO> borrowPage(@RequestHeader String token){
        DecodedJWT tokenInfo = JWTUtils.getTokenInfo(token);
        String userNum = tokenInfo.getClaim("userNum").asString();
        BorrowPageVO borrowPageVO = borrowService.getBorrowPageInfo(userNum);
        if(borrowPageVO == null){
            return CommonResponse.createForError();
        }
        return CommonResponse.createForSuccess(borrowPageVO);
    }
    @CrossOrigin
    @GetMapping("/borrow/search/{search_content}/{type}")
    public CommonResponse<PeriodicalNotBorrowVO> borrowSearch(@PathVariable("search_content") String searchContent,
                                                              @PathVariable("type") int type){

    }
}

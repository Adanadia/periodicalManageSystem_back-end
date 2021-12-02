package top.ahcdc.periodical.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import net.sf.jsqlparser.statement.create.index.CreateIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.ahcdc.periodical.common.lang.CommonResponse;
import top.ahcdc.periodical.entity.PeriodicalContentEntity;
import top.ahcdc.periodical.service.BorrowService;
import top.ahcdc.periodical.utils.JWTUtils;
import top.ahcdc.periodical.vo.BorrowPageVO;
import top.ahcdc.periodical.vo.integration.PeriodicalNotBorrowVO;

import java.util.List;

@RestController
public class BorrowController {
    @Autowired
    BorrowService borrowService;

    @CrossOrigin
    @GetMapping("/borrow")
    public CommonResponse<BorrowPageVO> borrowPage(@RequestHeader("Authorization") String token) {
        DecodedJWT tokenInfo = JWTUtils.getTokenInfo(token);
        String userNum = tokenInfo.getClaim("userNum").asString();
        BorrowPageVO borrowPageVO = borrowService.getBorrowPageInfo(userNum);
        if (borrowPageVO == null) {
            return CommonResponse.createForError();
        }
        return CommonResponse.createForSuccess(borrowPageVO);
    }

    @CrossOrigin
    @GetMapping("/borrow/search/{search_content}/{type}")
    public CommonResponse<List<PeriodicalNotBorrowVO>> borrowSearch(@PathVariable("search_content") String searchContent,
                                                                    @PathVariable("type") int type) {
        List<PeriodicalNotBorrowVO> borrowSearchRes = borrowService.BorrowSearch(type, searchContent);
        if (borrowSearchRes.isEmpty()) {
            return CommonResponse.createForError("未查询到相关期刊");
        }
        return CommonResponse.createForSuccess(borrowSearchRes);
    }//未借出期刊的按照关键字和作者查询

    @CrossOrigin
    @GetMapping("/borrow/search/detail/{periodical_name}/{year}/{volume}/{stage}")
    public CommonResponse<PeriodicalContentEntity> periodicalDetailDisp(@PathVariable("periodical_name") String pName,
                                                                        @PathVariable("year") int year, @PathVariable("volume") int volume,
                                                                        @PathVariable("stage") int stage) {

        return CommonResponse.createForSuccess(borrowService.detailDisp(pName, year, volume, stage));
    }//必须保证前端传过来的是未被借出的期刊信息

    @CrossOrigin
    @PostMapping("/borrow/search/borrowbooks")
    public CommonResponse<Object> borrowBooks(@RequestParam("periodical_name") String pName, @RequestParam("volume") int volume,
                                              @RequestParam("stage") int stage, @RequestParam("year") int year,
                                              @RequestHeader("Authorization") String token) {
        DecodedJWT tokenInfo = JWTUtils.getTokenInfo(token);
        String userNum = tokenInfo.getClaim("userNum").asString();
        System.out.println(userNum);
        return borrowService.borrowBooks(pName, userNum, year, stage, volume);
    }
}

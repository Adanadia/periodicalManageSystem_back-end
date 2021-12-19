package top.ahcdc.periodical.controller;
import com.auth0.jwt.interfaces.DecodedJWT;
import net.sf.jsqlparser.statement.create.index.CreateIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.ahcdc.periodical.common.lang.CommonResponse;
import top.ahcdc.periodical.entity.PeriodicalContentEntity;
import top.ahcdc.periodical.service.BorrowService;
import top.ahcdc.periodical.service.ReserveService;
import top.ahcdc.periodical.utils.JWTUtils;
import top.ahcdc.periodical.vo.BorrowPageVO;
import top.ahcdc.periodical.vo.ReservePageVO;
import top.ahcdc.periodical.vo.integration.PeriodicalBorrowVO;
import top.ahcdc.periodical.vo.integration.PeriodicalNotBorrowVO;

import java.util.List;

@RestController
public class ReserveController {
    @Autowired
    ReserveService reserveService;
    @CrossOrigin
    @GetMapping("/reserve")
    public CommonResponse<ReservePageVO> reservePage(@RequestHeader("Authorization") String token) {
        DecodedJWT tokenInfo = JWTUtils.getTokenInfo(token);
        String userNum = tokenInfo.getClaim("userNum").asString();
        ReservePageVO reservePageVO = reserveService.getReservePageInfo(userNum);
        if (reservePageVO == null) {
            return CommonResponse.createForError();
        }
        return CommonResponse.createForSuccess(reservePageVO);
    }
    @GetMapping("/reserve/search/{search_content}/{type}")
    public CommonResponse<List<PeriodicalBorrowVO>> reserveSearch(@PathVariable("search_content") String searchContent,
                                                                  @PathVariable("type") int type){
        List<PeriodicalBorrowVO> reserveSearchRes = reserveService.ReserveSearch(type, searchContent);
        if (reserveSearchRes.isEmpty()) {
            return CommonResponse.createForError("未查询到相关期刊");
        }
        return CommonResponse.createForSuccess(reserveSearchRes);
    }
    @GetMapping("/reserve/search/detail/{periodical_name}/{year}/{volume}/{stage}")
    public CommonResponse<List<PeriodicalContentEntity>> periodicalDetailDisp(@PathVariable("periodical_name") String pName,
                                                                              @PathVariable("year") int year, @PathVariable("volume") int volume,
                                                                              @PathVariable("stage") int stage) {

        return CommonResponse.createForSuccess(reserveService.detailDisp(pName, year, volume, stage));
    }
    @PostMapping("/reserve/search/reservebooks")
    public CommonResponse<Object> reserveBooks(@RequestParam("periodical_name") String pName, @RequestParam("volume") int volume,
                                              @RequestParam("stage") int stage, @RequestParam("year") int year,
                                              @RequestHeader("Authorization") String token) {
        DecodedJWT tokenInfo = JWTUtils.getTokenInfo(token);
        String userNum = tokenInfo.getClaim("userNum").asString();
        if(reserveService.getByUserNum(userNum,pName)) {
            return CommonResponse.createForError("不能重复预定");
        }
        reserveService.ReserveBooks(pName, userNum, year, stage, volume);
        return CommonResponse.createForSuccessMessage("预定成功！");
    }
    @PutMapping("/reserve/delete")
    public CommonResponse<Object> deleteReserve(@RequestParam("periodical_name") String pName,
                                                @RequestParam("volume") int volume,
                                                @RequestParam("stage") int stage,
                                                @RequestParam("year") int year,
                                                @RequestHeader("Authorization") String token){
        DecodedJWT tokenInfo = JWTUtils.getTokenInfo(token);
        String userNum = tokenInfo.getClaim("userNum").asString();
        System.out.println(pName);
        reserveService.DeleteReserve(pName,userNum,year,stage,volume);
        return CommonResponse.createForSuccessMessage("已取消预定");
    }
}

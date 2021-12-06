package top.ahcdc.periodical.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.ahcdc.periodical.common.lang.CommonResponse;
import top.ahcdc.periodical.service.BookDestinationService;
import top.ahcdc.periodical.utils.Authorization;
import top.ahcdc.periodical.vo.BookDestination_PeriodicalPageVO;
import top.ahcdc.periodical.vo.BookDestination_UserPageVO;

@RestController
public class BookDestinationController {
    @Autowired
    BookDestinationService bookDestinationService;
    @CrossOrigin
    @PostMapping("/bookdestination/search/{search_content}/{type}")
    public CommonResponse<Object> getBookDestination_PeriodicalPageVO(@RequestHeader("Authorization")String token,
                                                                                                @PathVariable("search_content") String search_content,
                                                                                                @PathVariable("type") int type){
        if(type==1){
            BookDestination_UserPageVO bookDestination_userPageVO=bookDestinationService.getBookDestinationPage_User(search_content);
            return CommonResponse.createForSuccess(bookDestination_userPageVO);
        }
        else if(type==2){
            BookDestination_PeriodicalPageVO bookDestination_periodicalPageVO=bookDestinationService.getBookDestination_Periodical(search_content);
            return CommonResponse.createForSuccess(bookDestination_periodicalPageVO);
        }
        return CommonResponse.createForError("查询失败！");
    }
}

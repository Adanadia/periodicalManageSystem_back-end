package top.ahcdc.periodical.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ahcdc.periodical.entity.BorrowTabelEntity;
import top.ahcdc.periodical.entity.UserEntity;
import top.ahcdc.periodical.mapper.BorrowTableMapper;
import top.ahcdc.periodical.mapper.UserMapper;
import top.ahcdc.periodical.service.BookDestinationService;
import top.ahcdc.periodical.vo.BookDestination_PeriodicalPageVO;
import top.ahcdc.periodical.vo.integration.BookDestination_PeriodicalVO;
import top.ahcdc.periodical.vo.BookDestination_UserPageVO;
import top.ahcdc.periodical.vo.integration.BookDestination_UserVO;

import java.util.LinkedList;
import java.util.List;

@Service

public class BookDestinationServiceImpl implements BookDestinationService {
    @Autowired
    BorrowTableMapper borrowTableMapper;
    @Autowired
    UserMapper userMapper;
    @Override
    public BookDestination_UserPageVO getBookDestinationPage_User(String search_content) {
        QueryWrapper<BorrowTabelEntity> borrowTabelQueryWrapper=new QueryWrapper<>();
        borrowTabelQueryWrapper.eq("user_num",search_content);
        BookDestination_UserPageVO bookDestination_userPageVO=new BookDestination_UserPageVO();
        List<BookDestination_UserVO> bookDestination_userVOList=new LinkedList<>();
        List<BorrowTabelEntity> borrowTabelEntities=borrowTableMapper.selectList(borrowTabelQueryWrapper);
        for(BorrowTabelEntity borrowTabelEntity1:borrowTabelEntities){
            BookDestination_UserVO bookDestination_userVO=new BookDestination_UserVO(
                    borrowTabelEntity1.getPeriodicalName(),
                    borrowTabelEntity1.getVolume(),
                    borrowTabelEntity1.getStage(),
                    borrowTabelEntity1.getYear(),
                    borrowTabelEntity1.getBorrowDate(),
                    borrowTabelEntity1.getDueDate()
            );
            bookDestination_userVOList.add(bookDestination_userVO);
        }
        bookDestination_userPageVO.setBookDestination_userList(bookDestination_userVOList);
        return bookDestination_userPageVO;
    }

    @Override
    public BookDestination_PeriodicalPageVO getBookDestination_Periodical(String search_content) {
        QueryWrapper<BorrowTabelEntity> borrowTabelQueryWrapper=new QueryWrapper<>();
        List<BookDestination_PeriodicalVO> bookDestination_periodicalVOList=new LinkedList<>();
        borrowTabelQueryWrapper.eq("periodical_name",search_content);
        List<BorrowTabelEntity> borrowTabelEntities=borrowTableMapper.selectList(borrowTabelQueryWrapper);
        for(BorrowTabelEntity borrowTabelEntity:borrowTabelEntities){
            QueryWrapper<UserEntity> userQueryWrapper=new QueryWrapper<>();
            userQueryWrapper.like("user_num",borrowTabelEntity.getUserNum());
            UserEntity userEntity=userMapper.selectOne(userQueryWrapper);
            bookDestination_periodicalVOList.add(new BookDestination_PeriodicalVO(
                        userEntity.getUserName(),
                        borrowTabelEntity.getVolume(),
                        borrowTabelEntity.getYear(),
                        borrowTabelEntity.getStage(),
                        borrowTabelEntity.getBorrowDate(),
                        borrowTabelEntity.getDueDate()
                    ));
        }
        BookDestination_PeriodicalPageVO bookDestination_periodicalPageVO=new BookDestination_PeriodicalPageVO(bookDestination_periodicalVOList);
        return bookDestination_periodicalPageVO;
    }
}

package pers.jxy.service;

import com.github.pagehelper.PageInfo;
import pers.jxy.entity.Appeal;

public interface AppealService {
    Boolean insertAppeal(Appeal appeal);

    PageInfo selectAppeals(Integer page);

    Appeal getAppeal(Integer no);

    Boolean auditaAppeal(Integer no);

    Boolean appealSuccess(Integer adType,Integer uno,Integer ano,String reason,String noteName,Integer noteNo);
}

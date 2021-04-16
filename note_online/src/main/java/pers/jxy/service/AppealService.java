package pers.jxy.service;

import com.github.pagehelper.PageInfo;
import pers.jxy.entity.Appeal;

public interface AppealService {
    Boolean insertAppeal(Appeal appeal);

    PageInfo selectAppeals(Integer page);

    Appeal getAppeal(Integer no);

    Boolean auditaAppeal(Integer no, Integer id, Integer aType);

    Boolean appealSuccess(Integer adType, Integer uno, Integer ano, String reason, String noteName, Integer noteNo, Integer id, Integer aType);

    PageInfo<Appeal> selectAppealLog(Integer page);

    PageInfo<Appeal> getAppealLog(Integer id, Integer page);

    Boolean userAppeal(Integer no, Integer iNo);
}

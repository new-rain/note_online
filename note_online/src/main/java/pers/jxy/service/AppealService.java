package pers.jxy.service;

import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;
import pers.jxy.entity.Appeal;

@Transactional(readOnly = true)
public interface AppealService {
    @Transactional(readOnly = false)
    Boolean insertAppeal(Appeal appeal);

    PageInfo selectAppeals(Integer page);

    Appeal getAppeal(Integer no);

    @Transactional(readOnly = false)
    Boolean auditaAppeal(Integer no, Integer id, Integer aType);

    @Transactional(readOnly = false)
    Boolean appealSuccess(Integer adType, Integer uno, Integer ano, String reason, String noteName, Integer noteNo, Integer id, Integer aType);

    PageInfo<Appeal> selectAppealLog(Integer page);

    PageInfo<Appeal> getAppealLog(Integer id, Integer page);
}

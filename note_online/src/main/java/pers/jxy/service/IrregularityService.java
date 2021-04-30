package pers.jxy.service;

import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;
import pers.jxy.entity.Irregularity;


public interface IrregularityService {
    @Transactional(readOnly = false)
    Boolean irr(Irregularity irr);

    PageInfo<Irregularity> selectIrrs(Integer page);

    PageInfo<Irregularity> selctAuditLog(Integer page);

    Irregularity selectByINo(Integer no);

    @Transactional(readOnly = false)
    Boolean aduitIrr(Integer inNo, Integer iType, Integer iNo, Integer res, Integer irNo, Integer isNo, String[] msg, Boolean onekey, Integer id);

    PageInfo<Irregularity> getIrrLog(Integer id, Integer page);

    Irregularity getBanReason(Integer no);
}

package pers.jxy.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.jxy.entity.Irregularity;
import pers.jxy.service.IrregularityService;
import pers.jxy.util.NoteBookOnlineUtils;

/**
 * @author:靳新宇
 * @date : 02-21 16:48
 */
@RestController
public class IrregularityController {
    @Autowired
    IrregularityService irregularityService;

    @PostMapping("/irr")
    public Boolean irr(Integer iType, String iBody, Integer irNo, Integer isNo, Integer inNo, String inName) {
        Irregularity irr = new Irregularity();
        irr.setiType(iType);
        irr.setIBody(iBody);
        irr.setIrNo(irNo);
        irr.setIsNo(isNo);
        irr.setInNo(inNo);
        irr.setInName(inName);
        irr.setITime(NoteBookOnlineUtils.getNow());
        return irregularityService.irr(irr);
    }

    @GetMapping("/selectIrrs")
    public PageInfo<Irregularity> selectIrrs(Integer page) {
        return irregularityService.selectIrrs(page);
    }

    @GetMapping("/selectIrrLogs")
    public PageInfo selectIrrLogs(Integer page) {
        return irregularityService.selctAuditLog(page);
    }

    /**
     * 根据序号查询违规记录
     */
    @GetMapping("/selectIrrByINo")
    public Irregularity selectByINo(Integer no) {
        return irregularityService.selectByINo(no);
    }

    /**
     * 对举报进行处理
     */
    @PostMapping("/aduitIrr")
    public Boolean aduitIrr(Integer inNo, Integer iType, Integer iNo, Integer res, Integer irNo, Integer isNo, String[] msg, Boolean onekey, Integer id) {
        return irregularityService.aduitIrr(inNo, iType, iNo, res, irNo, isNo, msg, onekey, id);
    }

    /**
     * 查询管理员处理日志
     */
    @GetMapping("/getIrrLog")
    public PageInfo<Irregularity> getIrrLog(Integer id, Integer page) {
        return irregularityService.getIrrLog(id, page);
    }

    /**
     * 查询账号封禁原因
     */
    @GetMapping("/getBanReason")
    public Irregularity getBanReason(Integer no) {
        return irregularityService.getBanReason(no);
    }
}

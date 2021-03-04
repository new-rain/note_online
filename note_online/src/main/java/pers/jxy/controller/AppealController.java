package pers.jxy.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.jxy.entity.Appeal;
import pers.jxy.service.AppealService;
import pers.jxy.util.NoteBookOnlineUtils;

/**
 * @author:靳新宇
 * @date : 02-26 19:09
 */
@RestController
public class AppealController {
    @Autowired
    AppealService appealService;

    @PostMapping("/insertAppeal")
    public Boolean insertAppeal(Integer uNo, Integer noteNo, String abody, String reason, Integer adType) {
        Appeal appeal = new Appeal();
        appeal.setAuNo(uNo);
        appeal.setAnNo(noteNo);
        appeal.setABody(abody);
        appeal.setAReason(reason);
        appeal.setAdType(adType);
        appeal.setATime(NoteBookOnlineUtils.getNow());
        return appealService.insertAppeal(appeal);
    }

    @GetMapping("/selectAppeals")
    public PageInfo selectAppeals(Integer page) {
        return appealService.selectAppeals(page);
    }

    @GetMapping("/getAppeal")
    public Appeal getAppeal(Integer no) {
        return appealService.getAppeal(no);
    }

    @PutMapping("/auditaAppeal")
    public Boolean auditaAppeal(Integer no) {
        return appealService.auditaAppeal(no);
    }

    @PutMapping("/appealSuccess")
    public Boolean appealSuccess(Integer type, Integer uno, Integer ano, String reason, String noteName, Integer noteNo) {
        return appealService.appealSuccess(type, uno, ano, reason, noteName, noteNo);
    }
}

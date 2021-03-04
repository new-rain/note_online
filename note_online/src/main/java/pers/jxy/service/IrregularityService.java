package pers.jxy.service;

import pers.jxy.entity.Irregularity;


public interface IrregularityService {
    Boolean irr(Irregularity irr);

    Object[] selectIrrs(Integer page, Integer type);

    Irregularity selectByINo(Integer no);

    Boolean aduitIrr(Integer inNo, Integer iType,Integer iNo, Integer res, Integer irNo, Integer isNo, String[] msg,Boolean onekey);
}

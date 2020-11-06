package com.newzen.nzscrap.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.newzen.nzscrap.model.dto.HistoryReq;
import com.newzen.nzscrap.model.dto.HistoryRes;
import com.newzen.nzscrap.model.dto.SBankB0002;
import com.newzen.nzscrap.model.dto.SCardC0005;
import com.newzen.nzscrap.model.dto.SCardsalesB0002;
import com.newzen.nzscrap.model.dto.SCardsalesB0011Dtl;
import com.newzen.nzscrap.model.dto.SCardsalesB0011Sum;
import com.newzen.nzscrap.model.dto.SCardsalesB0021Dtl;
import com.newzen.nzscrap.model.dto.SCardsalesB0021Sum;
import com.newzen.nzscrap.model.dto.SCardsalesB0031Dtl;
import com.newzen.nzscrap.model.dto.SCardsalesB0031Sum;
import com.newzen.nzscrap.model.dto.SCbkB0002;
import com.newzen.nzscrap.model.dto.SCcdC0005;
import com.newzen.nzscrap.model.dto.SHometaxZ0006;
import com.newzen.nzscrap.model.dto.SHometaxZ0006Dtl;
import com.newzen.nzscrap.model.dto.SHometaxZ300X;
import com.newzen.nzscrap.model.dto.SHometaxZ300XItem;
import com.newzen.nzscrap.model.dto.SHometaxZ4001;
import com.newzen.nzscrap.model.dto.SHometaxZ4002;
import com.newzen.nzscrap.model.dto.SHometaxZ4010;
import com.newzen.nzscrap.model.dto.SHometaxZ4020;
import com.newzen.nzscrap.model.dto.SHometaxZ4050;
import com.newzen.nzscrap.model.dto.SHometaxZ4060;
import com.newzen.nzscrap.model.dto.SHometaxZ5002;
import com.newzen.nzscrap.model.dto.SSbkB0002;

@Mapper
public interface ScrapMapper {
	int insertHistoryReqList(ArrayList<HistoryReq> historyReqList);
	int insertHistoryResList(ArrayList<HistoryRes> historyResList);
	
	int insertSHometaxZ300XList(ArrayList<SHometaxZ300X> sHometaxZ300XList);
	int insertSHometaxZ300XItemList(ArrayList<SHometaxZ300XItem> sHometaxZ300XItemList);
	int insertSHometaxZ4001List(ArrayList<SHometaxZ4001> sHometaxZ4001List);
	int insertSHometaxZ4002List(ArrayList<SHometaxZ4002> sHometaxZ4002List);
	int insertSHometaxZ4010List(ArrayList<SHometaxZ4010> sHometaxZ4010List);
	int insertSHometaxZ4020List(ArrayList<SHometaxZ4020> sHometaxZ4020List);
	int insertSHometaxZ4050List(ArrayList<SHometaxZ4050> sHometaxZ4050List);
	int insertSHometaxZ4060List(ArrayList<SHometaxZ4060> sHometaxZ4060List);
	int insertSHometaxZ5002List(ArrayList<SHometaxZ5002> sHometaxZ5002List);
	int insertSHometaxZ0006List(ArrayList<SHometaxZ0006> sHometaxZ0006List);
	int insertSHometaxZ0006DtlList(ArrayList<SHometaxZ0006Dtl> sHometaxZ0006DtlList);
	int insertSCardsalesB0002List(ArrayList<SCardsalesB0002> sCardsalesB0002List);
	int insertSCardsalesB0011DtlList(ArrayList<SCardsalesB0011Dtl> sCardsalesB0011DtlList);
	int insertSCardsalesB0011SumList(ArrayList<SCardsalesB0011Sum> sCardsalesB0011SumList);
	int insertSCardsalesB0021DtlList(ArrayList<SCardsalesB0021Dtl> sCardsalesB0021DtlList);
	int insertSCardsalesB0021SumList(ArrayList<SCardsalesB0021Sum> sCardsalesB0021SumList);
	int insertSCardsalesB0031DtlList(ArrayList<SCardsalesB0031Dtl> sCardsalesB0031DtlList);
	int insertSCardsalesB0031SumList(ArrayList<SCardsalesB0031Sum> sCardsalesB0031SumList);
	int insertSBankB0002List(ArrayList<SBankB0002> sBankB0002List);
	int insertSCbkB0002List(ArrayList<SCbkB0002> sCbkB0002List);
	int insertSSbkB0002List(ArrayList<SSbkB0002> sSbkB0002List);
	int insertSCardC0005List(ArrayList<SCardC0005> sCardC0005List);
	int insertSCcdC0005List(ArrayList<SCcdC0005> sCcdC0005List);
	
	int deleteSHometaxZ4010List(SHometaxZ4010 sHometaxZ4010);
	int deleteSHometaxZ4020List(SHometaxZ4020 sHometaxZ4020);
	int deleteSCardsalesB0011DtlList(SCardsalesB0011Dtl sCardsalesB0011Dtl);
	int deleteSCardsalesB0021DtlList(SCardsalesB0021Dtl sCardsalesB0021Dtl);
	int deleteSCardsalesB0031DtlList(SCardsalesB0031Dtl sCardsalesB0031Dtl);
	
	ArrayList<HistoryReq> selectHistoryReqList(HistoryReq historyReqParam);
	ArrayList<HistoryRes> selectHistoryResList(HistoryRes historyResParam);
	ArrayList<SHometaxZ300X> selectSHometaxZ300XList(SHometaxZ300X sHometaxZ300XParam);
	ArrayList<SHometaxZ300XItem> selectSHometaxZ300XItemList(SHometaxZ300XItem sHometaxZ300XItemParam);
	ArrayList<SHometaxZ4001> selectSHometaxZ4001List(SHometaxZ4001 sHometaxZ4001Param);
	ArrayList<SHometaxZ4002> selectSHometaxZ4002List(SHometaxZ4002 sHometaxZ4002Param);
	ArrayList<SHometaxZ4010> selectSHometaxZ4010List(SHometaxZ4010 sHometaxZ4010Param);
	ArrayList<SHometaxZ4020> selectSHometaxZ4020List(SHometaxZ4020 sHometaxZ4020Param);
	ArrayList<SHometaxZ4050> selectSHometaxZ4050List(SHometaxZ4050 sHometaxZ4050Param);
	ArrayList<SHometaxZ4060> selectSHometaxZ4060List(SHometaxZ4060 sHometaxZ4060Param);
	ArrayList<SHometaxZ5002> selectSHometaxZ5002List(SHometaxZ5002 sHometaxZ5002Param);
	ArrayList<SHometaxZ0006> selectSHometaxZ0006List(SHometaxZ0006 sHometaxZ0006Param);
	ArrayList<SHometaxZ0006Dtl> selectSHometaxZ0006DtlList(SHometaxZ0006Dtl sHometaxZ0006DtlParam);
	ArrayList<SCardsalesB0002> selectSCardsalesB0002List(SCardsalesB0002 sCardsalesB0002Param);
	ArrayList<SCardsalesB0011Dtl> selectSCardsalesB0011DtlList(SCardsalesB0011Dtl sCardsalesB0011DtlParam);
	ArrayList<SCardsalesB0011Sum> selectSCardsalesB0011SumList(SCardsalesB0011Sum sCardsalesB0011SumParam);
	ArrayList<SCardsalesB0021Dtl> selectSCardsalesB0021DtlList(SCardsalesB0021Dtl sCardsalesB0021DtlParam);
	ArrayList<SCardsalesB0021Sum> selectSCardsalesB0021SumList(SCardsalesB0021Sum sCardsalesB0021SumParam);
	ArrayList<SCardsalesB0031Dtl> selectSCardsalesB0031DtlList(SCardsalesB0031Dtl sCardsalesB0031DtlParam);
	ArrayList<SCardsalesB0031Sum> selectSCardsalesB0031SumList(SCardsalesB0031Sum sCardsalesB0031SumParam);
	ArrayList<SBankB0002> selectSBankB0002List(SBankB0002 sBankB0002Param);
	ArrayList<SCbkB0002> selectSCbkB0002List(SCbkB0002 sCbkB0002Param);
	ArrayList<SBankB0002> selectSSbkB0002List(SSbkB0002 sSbkB0002Param);
	ArrayList<SCardC0005> selectSCardC0005List(SCardC0005 sCardC0005Param);
	ArrayList<SCcdC0005> selectSCcdC0005List(SCcdC0005 sCcdC0005Param);
}

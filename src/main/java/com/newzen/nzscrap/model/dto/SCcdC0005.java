package com.newzen.nzscrap.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Minhyeok Kim
 *
 * 카드-법인_승인내역조회 DTO
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SCcdC0005 {
	private String compCd;		// 회사코드
	private String useDt;		// 이용일
	private String useTm;		// 이용시간
	private String apprNo;		// 승인번호
	private String useCard;		// 이용카드
	private String useStore;	// 이용가맹점명
	private String useAmt;		// 이용금액(원화)
	private String useExAmt;	// 이용금액(외화)
	private String exCurCd;		// 통화코드(외화)
	private String useDiv;		// 이용구분(결제방법)
	private String apprSt;		// 승인상태
	private String instMon;		// 할부개월
	private String discAmt;		// 할인금액
	private String savePoint;	// 적립포인트
	private String settleDt;	// 결제일
	private String storeBizNo;	// 가맹점사업자번호
	private String storeCeo;	// 가맹점대표자명
	private String storeAddr;	// 가맹점주소
	private String storeTel;	// 가맹점전화번호
	private String storeType;	// 가맹점업종
	private String addTax;		// 부가세
	private String tip;			// 봉사료
	private String taxType;		// 과세유형
	private String storeSt;		// 가맹점상태
	private String storeNo;		// 가맹점번호
	private String rmk;			// 비고
	private String reqCd;		// 요청코드
	private String regId;		// 등록자ID
	private String regDt;		// 등록일시
	private String uptId;		// 수정자ID
	private String uptDt;		// 수정일시
	
	private String givenCardNo;	// _param에 포함 (카드번호 -> TODO: 기업용은 확인 필요)

	public String getCompCd() {
		return compCd;
	}

	public void setCompCd(String compCd) {
		this.compCd = compCd;
	}

	public String getUseDt() {
		return useDt;
	}

	public void setUseDt(String useDt) {
		this.useDt = useDt;
	}

	public String getUseTm() {
		return useTm;
	}

	public void setUseTm(String useTm) {
		this.useTm = useTm;
	}

	public String getApprNo() {
		return apprNo;
	}

	public void setApprNo(String apprNo) {
		this.apprNo = apprNo;
	}

	public String getUseCard() {
		return useCard;
	}

	public void setUseCard(String useCard) {
		this.useCard = useCard;
	}

	public String getUseStore() {
		return useStore;
	}

	public void setUseStore(String useStore) {
		this.useStore = useStore;
	}

	public String getUseAmt() {
		return useAmt;
	}

	public void setUseAmt(String useAmt) {
		this.useAmt = useAmt;
	}

	public String getUseExAmt() {
		return useExAmt;
	}

	public void setUseExAmt(String useExAmt) {
		this.useExAmt = useExAmt;
	}

	public String getExCurCd() {
		return exCurCd;
	}

	public void setExCurCd(String exCurCd) {
		this.exCurCd = exCurCd;
	}

	public String getUseDiv() {
		return useDiv;
	}

	public void setUseDiv(String useDiv) {
		this.useDiv = useDiv;
	}

	public String getApprSt() {
		return apprSt;
	}

	public void setApprSt(String apprSt) {
		this.apprSt = apprSt;
	}

	public String getInstMon() {
		return instMon;
	}

	public void setInstMon(String instMon) {
		this.instMon = instMon;
	}

	public String getDiscAmt() {
		return discAmt;
	}

	public void setDiscAmt(String discAmt) {
		this.discAmt = discAmt;
	}

	public String getSavePoint() {
		return savePoint;
	}

	public void setSavePoint(String savePoint) {
		this.savePoint = savePoint;
	}

	public String getSettleDt() {
		return settleDt;
	}

	public void setSettleDt(String settleDt) {
		this.settleDt = settleDt;
	}

	public String getStoreBizNo() {
		return storeBizNo;
	}

	public void setStoreBizNo(String storeBizNo) {
		this.storeBizNo = storeBizNo;
	}

	public String getStoreCeo() {
		return storeCeo;
	}

	public void setStoreCeo(String storeCeo) {
		this.storeCeo = storeCeo;
	}

	public String getStoreAddr() {
		return storeAddr;
	}

	public void setStoreAddr(String storeAddr) {
		this.storeAddr = storeAddr;
	}

	public String getStoreTel() {
		return storeTel;
	}

	public void setStoreTel(String storeTel) {
		this.storeTel = storeTel;
	}

	public String getStoreType() {
		return storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}

	public String getAddTax() {
		return addTax;
	}

	public void setAddTax(String addTax) {
		this.addTax = addTax;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getTaxType() {
		return taxType;
	}

	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}

	public String getStoreSt() {
		return storeSt;
	}

	public void setStoreSt(String storeSt) {
		this.storeSt = storeSt;
	}

	public String getStoreNo() {
		return storeNo;
	}

	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}

	public String getRmk() {
		return rmk;
	}

	public void setRmk(String rmk) {
		this.rmk = rmk;
	}

	public String getReqCd() {
		return reqCd;
	}

	public void setReqCd(String reqCd) {
		this.reqCd = reqCd;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getUptId() {
		return uptId;
	}

	public void setUptId(String uptId) {
		this.uptId = uptId;
	}

	public String getUptDt() {
		return uptDt;
	}

	public void setUptDt(String uptDt) {
		this.uptDt = uptDt;
	}

	public String getGivenCardNo() {
		return givenCardNo;
	}

	public void setGivenCardNo(String givenCardNo) {
		this.givenCardNo = givenCardNo;
	}

	@Override
	public String toString() {
		return "SCcdC0005 [compCd=" + compCd + ", useDt=" + useDt + ", useTm=" + useTm + ", apprNo=" + apprNo
				+ ", useCard=" + useCard + ", useStore=" + useStore + ", useAmt=" + useAmt + ", useExAmt=" + useExAmt
				+ ", exCurCd=" + exCurCd + ", useDiv=" + useDiv + ", apprSt=" + apprSt + ", instMon=" + instMon
				+ ", discAmt=" + discAmt + ", savePoint=" + savePoint + ", settleDt=" + settleDt + ", storeBizNo="
				+ storeBizNo + ", storeCeo=" + storeCeo + ", storeAddr=" + storeAddr + ", storeTel=" + storeTel
				+ ", storeType=" + storeType + ", addTax=" + addTax + ", tip=" + tip + ", taxType=" + taxType
				+ ", storeSt=" + storeSt + ", storeNo=" + storeNo + ", rmk=" + rmk + ", reqCd=" + reqCd + ", regId="
				+ regId + ", regDt=" + regDt + ", uptId=" + uptId + ", uptDt=" + uptDt + ", givenCardNo=" + givenCardNo
				+ "]";
	}
}

package net.jagunma.backbone.auth.application.queryService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntity;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityDao;
import net.jagunma.backbone.auth.usecase.operator.OperatorSearchRequest;
import net.jagunma.common.ddd.model.orders.Orders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OperatorReferenceService {
	/**
	 * オペレータ一覧の１ページ当たりの行数
	 */
	private final int PAGE_SIZE = 10;

	private OperatorEntityDao operatorEntityDao;
	private OperatorSubSystemRoleReferenceService operatorSubSystemRoleReferenceService;
	private OperatorBizTranRoleReferenceService operatorBizTranRoleReferenceService;

	public OperatorReferenceService(OperatorEntityDao operatorEntityDao,
		OperatorSubSystemRoleReferenceService operatorSubSystemRoleReferenceService,
		OperatorBizTranRoleReferenceService operatorBizTranRoleReferenceService) {

		this.operatorEntityDao = operatorEntityDao;
		this.operatorSubSystemRoleReferenceService = operatorSubSystemRoleReferenceService;
		this.operatorBizTranRoleReferenceService = operatorBizTranRoleReferenceService;
	}

	/**
	 * オペレータリストを取得します。
	 * @param request 条件
	 * @return
	 */
	public List<Operator> getOperatorList(OperatorSearchRequest request) {

		// オペレータ検索
		Orders orders = Orders.empty()
			.addOrder("TempoCode")
			.addOrder("OperatorCode");
		List<OperatorEntity> operatorEntitys = operatorEntityDao.findBy(request.getOperatorEntityCriteria(request), orders);

		// オペレーター_サブシステムロール割当検索
		List<OperatorSubSystemRole> operatorSubSystemRoles = operatorSubSystemRoleReferenceService.getOperatorSubSystemRoleList(request);
		// オペレーター_取引ロール割当リスト
		List<OperatorBizTranRole> operatorBizTranRoles = operatorBizTranRoleReferenceService.getOperatorBizTranRoleList(request);


		List<Operator> operatorList = new ArrayList<Operator>();

		operatorEntitys.forEach(o -> {
			Operator entity = new Operator();
			// オペレーターID
			entity.setOperatorId(o.getOperatorId());
			// オペレーターコード
			entity.setOperatorCode(o.getOperatorCode());
			// オペレーター名
			entity.setOperatorName(o.getOperatorName());
			// 有効期限開始日
			entity.setExpirationStartDate(o.getExpirationStartDate());
			// 有効期限終了日
			entity.setExpirationEndDate(o.getExpirationEndDate());
			// JAID
			entity.setJaId(o.getJaId());
			// JAコード
			entity.setJaCode(o.getJaCode());
			// 店舗ID
			entity.setTempoId(o.getTempoId());
			// 店舗コード
			entity.setTempoCode(o.getTempoCode());
			// 利用可否状態
			entity.setAvailableStatus(o.getAvailableStatus());
			// 店舗名
			//entity.setTempoName("");
			// ロック状態
			//entity.setLockStatus(0);

			// オペレーター_サブシステムロール割当リスト
			operatorSubSystemRoles.stream().filter(s->s.getOperatorId() == o.getOperatorId()).forEach(s ->  {
				entity.getOperatorSubSystemRoleList().add(s);
			});
			// オペレーター_取引ロール割当リスト
			operatorBizTranRoles.stream().filter(s->s.getOperatorId() == o.getOperatorId()).forEach(s ->  {
				entity.getOperatorBizTranRoleList().add(s);
			});

			operatorList.add(entity);
		});
		return operatorList;
	}

//	//TODO: オペレータ情報を読む
//	public List<Operator> getDummyOperatorList(OperatorSearchRequest request) {
//		// 【仮】リスト作成
//		LocalDate startdate = LocalDate.of(2020, 4, 1);
//		LocalDate enddate = LocalDate.of(9999, 12, 31);
//		List<Object[]> oplist = new ArrayList<Object[]>();
//		oplist.add(new Object[]{1, "OP101001", "前橋太郎", startdate, enddate, 6, "006", 1, "001", "本所", 0, 0, "2110", "業務統括者（販売・青果）", startdate, enddate, "YSAG20", "（青果）精算担当", startdate, enddate});
//		oplist.add(new Object[]{1, "OP101001", "前橋太郎", startdate, enddate, 6, "006", 1, "001", "本所", 0, 0, "2110", "業務統括者（販売・青果）", startdate, enddate, "YSAG30", "（青果）集荷場担当", startdate, enddate});
//		oplist.add(new Object[]{1, "OP101001", "前橋太郎", startdate, enddate, 6, "006", 1, "001", "本所", 0, 0, "2120", "業務統括者（販売・花卉）", startdate, enddate, "YFAG20", "（花卉）精算担当", startdate, enddate});
//		oplist.add(new Object[]{1, "OP101001", "前橋太郎", startdate, enddate, 6, "006", 1, "001", "本所", 0, 0, "2120", "業務統括者（販売・花卉）", startdate, enddate, "YFAG30", "（花卉）集荷場担当", startdate, enddate});
//		oplist.add(new Object[]{2, "OP101002", "前橋次郎", startdate, enddate, 6, "006", 1, "001", "本所", 0, 1, "2120", "業務統括者（販売・花卉）", startdate, enddate, "YFAG20", "（花卉）精算担当", startdate, enddate});
//		oplist.add(new Object[]{2, "OP101002", "前橋次郎", startdate, enddate, 6, "006", 1, "001", "本所", 0, 1, "2120", "業務統括者（販売・花卉）", startdate, enddate, "YFAG30", "（花卉）集荷場担当", startdate, enddate});
//		oplist.add(new Object[]{3, "OP101003", "前橋三郎", startdate, enddate, 6, "006", 1, "001", "本所", 1, 0, "2300", "業務統括者（販売・畜産）", startdate, enddate, "ANAG20", "（畜産）精算担当", startdate, enddate});
//		oplist.add(new Object[]{4, "OP101004", "前橋四郎", startdate, enddate, 6, "006", 1, "001", "本所", 0, 0, "2210", "業務統括者（販売・米）", startdate, enddate, "HKAG20", "（米）精算担当", startdate, enddate});
//		oplist.add(new Object[]{5, "OP101005", "前橋五郎", startdate, enddate, 6, "006", 1, "001", "本所", 0, 0, "2220", "業務統括者（販売・麦）", startdate, enddate, "HMAG20", "（麦）精算担当", startdate, enddate});
//		oplist.add(new Object[]{6, "OP101006", "前橋六郎", startdate, enddate, 6, "006", 1, "001", "本所", 0, 0, "1000", "業務統括者（購買）", startdate, enddate, "KBAG20", "（購買）精算担当", startdate, enddate});
//		oplist.add(new Object[]{11, "OP101011", "前橋花太郎", startdate, enddate, 6, "006", 2, "002", "○○○支所", 0, 0, "2120", "業務統括者（販売・花卉）", startdate, enddate, "YFAG99", "（花卉）ＮＮＮ担当", startdate, enddate});
//		oplist.add(new Object[]{12, "OP101012", "前橋花次郎", startdate, enddate, 6, "006", 2, "002", "○○○支所", 0, 0, "2120", "業務統括者（販売・花卉）", startdate, enddate, "YFAG99", "（花卉）ＮＮＮ担当", startdate, enddate});
//		oplist.add(new Object[]{13, "OP101013", "前橋花三郎", startdate, enddate, 6, "006", 2, "002", "○○○支所", 0, 0, "2120", "業務統括者（販売・花卉）", startdate, enddate, "YFAG98", "（花卉）ＮＮ担当", startdate, enddate});
//		oplist.add(new Object[]{13, "OP101013", "前橋花三郎", startdate, enddate, 6, "006", 2, "002", "○○○支所", 0, 0, "2120", "業務統括者（販売・花卉）", startdate, enddate, "YFAG99", "（花卉）ＮＮＮ担当", startdate, enddate});
//		oplist.add(new Object[]{14, "OP101014", "前橋花四郎", startdate, enddate, 6, "006", 2, "002", "○○○支所", 0, 0, "2120", "業務統括者（販売・花卉）", startdate, enddate, "YFAG99", "（花卉）ＮＮＮ担当", startdate, enddate});
//		oplist.add(new Object[]{15, "OP101015", "前橋花五郎", startdate, enddate, 6, "006", 2, "002", "○○○支所", 0, 0, "2120", "業務統括者（販売・花卉）", startdate, enddate, "YFAG99", "（花卉）ＮＮＮ担当", startdate, enddate});
//		oplist.add(new Object[]{16, "OP101016", "前橋花六郎", startdate, enddate, 6, "006", 2, "002", "○○○支所", 0, 0, "2120", "業務統括者（販売・花卉）", startdate, enddate, "YFAG99", "（花卉）ＮＮＮ担当", startdate, enddate});
//		oplist.add(new Object[]{17, "OP101017", "前橋花七郎", startdate, enddate, 6, "006", 2, "002", "○○○支所", 0, 0, "2120", "業務統括者（販売・花卉）", startdate, enddate, "YFAG96", "（花卉）Ｎ担当", startdate, enddate});
//		oplist.add(new Object[]{17, "OP101017", "前橋花七郎", startdate, enddate, 6, "006", 2, "002", "○○○支所", 0, 0, "2120", "業務統括者（販売・花卉）", startdate, enddate, "YFAG97", "（花卉）ＮＮ担当", startdate, enddate});
//		oplist.add(new Object[]{17, "OP101017", "前橋花七郎", startdate, enddate, 6, "006", 2, "002", "○○○支所", 0, 0, "2120", "業務統括者（販売・花卉）", startdate, enddate, "YFAG98", "（花卉）ＮＮＮ担当", startdate, enddate});
//		oplist.add(new Object[]{17, "OP101017", "前橋花七郎", startdate, enddate, 6, "006", 2, "002", "○○○支所", 0, 0, "2120", "業務統括者（販売・花卉）", startdate, enddate, "YFAG99", "（花卉）ＮＮＮＮ担当", startdate, enddate});
//		oplist.add(new Object[]{21, "OP101021", "前橋麦太郎", startdate, enddate, 6, "006", 3, "003", "△△センター", 0, 0, "2220", "業務統括者（販売・麦）", startdate, enddate, "HMAG99", "（麦）ＮＮＮ担当", startdate, enddate});
//		oplist.add(new Object[]{22, "OP101022", "前橋麦次郎", startdate, enddate, 6, "006", 3, "003", "△△センター", 0, 0, "2220", "業務統括者（販売・麦）", startdate, enddate, "HMAG99", "（麦）ＮＮＮ担当", startdate, enddate});
//		oplist.add(new Object[]{23, "OP101023", "前橋麦三郎", startdate, enddate, 6, "006", 3, "003", "△△センター", 0, 0, "2220", "業務統括者（販売・麦）", startdate, enddate, "HMAG99", "（麦）ＮＮＮ担当", startdate, enddate});
//		oplist.add(new Object[]{24, "OP101024", "前橋米太郎", startdate, enddate, 6, "006", 3, "003", "△△センター", 0, 0, "2210", "業務統括者（販売・米）", startdate, enddate, "HHAG99", "（米）ＮＮＮ担当", startdate, enddate});
//		oplist.add(new Object[]{25, "OP101025", "前橋米次郎", startdate, enddate, 6, "006", 3, "003", "△△センター", 0, 0, "2210", "業務統括者（販売・米）", startdate, enddate, "HHAG99", "（米）ＮＮＮ担当", startdate, enddate});
//		oplist.add(new Object[]{26, "OP101026", "前橋米三郎", startdate, enddate, 6, "006", 3, "003", "△△センター", 0, 0, "2210", "業務統括者（販売・米）", startdate, enddate, "HHAG99", "（米）ＮＮＮ担当", startdate, enddate});
//		oplist.add(new Object[]{27, "OP101027", "前橋米四郎", startdate, enddate, 6, "006", 3, "003", "△△センター", 0, 0, "2210", "業務統括者（販売・米）", startdate, enddate, "HHAG99", "（米）ＮＮＮ担当", startdate, enddate});
//
//		List<Operator> operatorList = new ArrayList<>();
//		oplist.forEach(s -> {
//			Operator entity = new Operator();
//			// オペレーターID
//			entity.setOperatorId(((Integer) s[0]).longValue());
//			// オペレーターコード
//			entity.setOperatorCode(s[1].toString());
//			// オペレーター名
//			entity.setOperatorName(s[2].toString());
//			// 有効期限開始日
//			entity.setExpirationStartDate((LocalDate) s[3]);
//			// 有効期限終了日
//			entity.setExpirationEndDate((LocalDate) s[4]);
//			// JAID
//			entity.setJaId(((Integer) s[5]).longValue());
//			// JAコード
//			entity.setJaCode(s[6].toString());
//			// 店舗ID
//			entity.setTempoId(((Integer) s[7]).longValue());
//			// 店舗コード
//			entity.setTempoCode(s[8].toString());
//			// 店舗名
//			entity.setTempoName(s[9].toString());
//			// 利用可否状態
//			entity.setAvailableStatus((Integer) s[10]);
//			// ロック状態
//			entity.setLockStatus((Integer) s[11]);
//			// サブシステムロールコード
//			entity.setSubSystemRoleCode(s[12].toString());
//			// サブシステムロール名
//			entity.setSubSystemRoleName(s[13].toString());
//			// オペレーター_サブシステムロール割当有効期限開始日
//			entity.setSubSystemRoleExpirationStartDate((LocalDate) s[14]);
//			// オペレーター_サブシステムロール割当有効期限終了日
//			entity.setSubSystemRoleExpirationEndDate((LocalDate) s[15]);
//			// 取引ロールコード
//			entity.setBizTranRoleCode(s[16].toString());
//			// 取引ロール名
//			entity.setBizTranRoleName(s[17].toString());
//			// オペレーター_取引ロール割当有効期限開始日
//			entity.setBizTranRoleExpirationStartDate((LocalDate) s[18]);
//			// オペレーター_取引ロール割当有効期限終了日
//			entity.setBizTranRoleExpirationEndDate((LocalDate) s[19]);
//			operatorList.add(entity);
//		});
//		return operatorList;
//	}

	/**
	 * オペレータ一覧の最終ページを取得します、
	 * @param list オペレーターリスト
	 * @return オペレータ一覧の最終ページ
	 */
	public int getMaxPage(List<Operator> list) {
		return (int) Math.ceil((double)list.size() / PAGE_SIZE);
	}

	/**
	 * 該当ページのオペレータ一覧を取得します。
	 * @param list オペレーターリスト
	 * @param pageNo 対象ページ
	 * @return 該当ページのオペレータ一覧
	 */
	public List<Operator> getPageList(List<Operator> list, int pageNo) {
		int skip = pageNo * PAGE_SIZE - PAGE_SIZE;
		return list.stream().skip(skip).limit(10).collect(Collectors.toList());
	}
}

package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.ArrayList;
import java.util.List;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntity;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityDao;
import net.jagunma.backbone.shared.application.branch.BranchAtMomentRepository;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.util.strings2.Strings2;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAtMomentCriteria;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.branch.BranchType;
import net.jagunma.common.values.model.branch.BranchesAtMoment;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAttribute;
import net.jagunma.common.values.model.ja.JaCode;
import org.springframework.stereotype.Service;

@Service
public class BranchReference {

    private final BranchAtMomentRepository branchAtMomentRepository;
    private final OperatorEntityDao operatorEntityDao;

    // コンストラクタ
    public BranchReference(BranchAtMomentRepository branchAtMomentRepository,
        OperatorEntityDao operatorEntityDao) {

        this.branchAtMomentRepository = branchAtMomentRepository;
        this.operatorEntityDao =operatorEntityDao;
    }


    /**
     * 店舗群AtMomentを取得します、
     * @param jaId ＪＡID
     * @return 店舗群AtMoment
     */
    public BranchesAtMoment getBranchesAtMoment(long jaId) {

        BranchAtMomentCriteria criteria = new BranchAtMomentCriteria();
        criteria.getJaIdentifierCriteria().setEqualTo(jaId);
        //return branchAtMomentRepository.selectBy(criteria, Orders.empty());
        // TODO: 店舗の取得は BranchAtMomentで取得
        // TODO: 暫定でオペレーターテーブルからJA毎に店舗コードを取得
        return branchAtMomentSelectBy(criteria);
    }

    // TODO: 暫定でオペレーターテーブルからJA毎に店舗コードを取得
    public BranchesAtMoment branchAtMomentSelectBy(BranchAtMomentCriteria branchAtMomentCriteria) {
        //BranchesAtMoment branchesAtMoment = BranchesAtMoment();

        if (branchAtMomentCriteria == null ||
            branchAtMomentCriteria.getJaIdentifierCriteria() == null ||
            branchAtMomentCriteria.getJaIdentifierCriteria().getEqualTo() == null) {
            return BranchesAtMoment.of(new ArrayList<BranchAtMoment>());
        }

        Long jaId = branchAtMomentCriteria.getJaIdentifierCriteria().getEqualTo();
        String jaCode = Strings2.padEnd(jaId.toString(), 3, '0');

        OperatorEntityCriteria criteria = new OperatorEntityCriteria();
        criteria.getJaIdCriteria().setEqualTo(jaId);
        Orders orders = Orders.empty().addOrder("jaId").addOrder("branchId");

        List<OperatorEntity> operatorEntities = operatorEntityDao.findBy(criteria, orders);
        List<String> branchList = newArrayList();
        operatorEntities.forEach(o -> {
            branchList.add(o.getBranchCode());
        });

        List<BranchAtMoment> list = newArrayList();
        // 重複削除
        branchList.stream().distinct().forEach(t -> {
            list.add(BranchAtMoment.builder()
                .withIdentifier(operatorEntities.stream().filter(o->o.getBranchCode().equals(t)).findFirst().orElse(null).getBranchId())
                .withJaAtMoment(createJaAtMoment(jaId, jaCode, jaCode))
                .withBranchAttribute(BranchAttribute.builder()
                    .withBranchType(BranchType.一般)
                    .withBranchCode(BranchCode.of(t))
                    .withName(t+"店舗")
                    .build())
                .build());
        });
        return BranchesAtMoment.of(list);
    }

    private static JaAtMoment createJaAtMoment(Long jaId, String jaCode, String jaName) {
        return JaAtMoment.builder()
            .withIdentifier(jaId)
            .withJaAttribute(JaAttribute
                .builder()
                .withJaCode(JaCode.of(jaCode))
                .withName(jaName)
                .withFormalName("")
                .withAbbreviatedName("")
                .build())
            .build();
    }

}

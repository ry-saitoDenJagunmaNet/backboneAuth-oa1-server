package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.io.StringBufferInputStream;
import java.util.List;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.TempoReferenceDto;
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
public class TempoReference {

    private final BranchAtMomentRepository branchAtMomentRepository;
    private final OperatorEntityDao operatorEntityDao;

    // コンストラクタ
    public TempoReference(BranchAtMomentRepository branchAtMomentRepository,
        OperatorEntityDao operatorEntityDao) {

        this.branchAtMomentRepository = branchAtMomentRepository;
        this.operatorEntityDao =operatorEntityDao;
    }


    /**
     * 店舗群を取得します、
     * @param jaid ＪＡID
     * @return 店舗群
     */
    public BranchesAtMoment getTempos(long jaid) {

        BranchAtMomentCriteria criteria = new BranchAtMomentCriteria();
        criteria.getJaIdentifierCriteria().setEqualTo(jaid);
        //return branchAtMomentRepository.selectBy(criteria, Orders.empty());
        // TODO: 店舗の取得は BranchAtMomentで取得
        // TODO: 暫定でオペレーターテーブルからJA毎に店舗コードを取得
        return branchAtMomentSelectBy(criteria);
    }

    public BranchesAtMoment branchAtMomentSelectBy(BranchAtMomentCriteria branchAtMomentCriteria) {
        //BranchesAtMoment branchesAtMoment = BranchesAtMoment();

        Long jaId = branchAtMomentCriteria.getJaIdentifierCriteria().getEqualTo();
        String jaCode = Strings2.padEnd(jaId.toString(), 3, '0');

        OperatorEntityCriteria criteria = new OperatorEntityCriteria();
        criteria.getJaIdCriteria().setEqualTo(jaId);
        Orders orders = Orders.empty().addOrder("jaId").addOrder("tempoId");

        List<OperatorEntity> operatorEntities = operatorEntityDao.findBy(criteria, orders);
        List<String> tempoList = newArrayList();
        operatorEntities.forEach(o -> {
            tempoList.add(o.getTempoCode());
        });

        List<BranchAtMoment> list = newArrayList();
        // 重複削除
        tempoList.stream().distinct().forEach(t -> {
            list.add(BranchAtMoment.builder()
                .withIdentifier(operatorEntities.stream().filter(o->o.getTempoCode().equals(t)).findFirst().orElse(null).getTempoId())
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

    public static JaAtMoment createJaAtMoment(Long jaId, String jaCode, String jaName) {
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

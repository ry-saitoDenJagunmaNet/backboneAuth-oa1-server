package net.jagunma.backbone.auth.authmanager.infra.datasource.operator;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operator;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorCriteria;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.Operators;
import net.jagunma.backbone.auth.authmanager.model.domain.operator.OperatorsRepository;
import net.jagunma.backbone.auth.authmanager.model.types.AvailableStatus;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntity;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityCriteria;
import net.jagunma.backbone.auth.model.dao.operator.OperatorEntityDao;
import net.jagunma.backbone.shared.application.branch.BranchAtMomentRepository;
import net.jagunma.common.ddd.model.orders.Orders;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAtMomentCriteria;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.branch.BranchType;
import net.jagunma.common.values.model.ja.JaAtMoment;
import net.jagunma.common.values.model.ja.JaAttribute;
import net.jagunma.common.values.model.ja.JaCode;
import org.springframework.stereotype.Component;

/**
 * オペレーター群検索
 */
@Component
public class OperatorsDataSource implements OperatorsRepository {

    private final OperatorEntityDao oeratorEntityDao;
    private final BranchAtMomentRepository branchAtMomentRepository;

    // コンストラクタ
    OperatorsDataSource(OperatorEntityDao oeratorEntityDao,
        BranchAtMomentRepository branchAtMomentRepository) {

        this.oeratorEntityDao = oeratorEntityDao;
        this.branchAtMomentRepository = branchAtMomentRepository;
    }

    /**
     * オペレーター群の検索を行います
     *
     * @param operatorCriteria オペレーターの検索条件
     * @param orders           オーダー指定
     * @return オペレーター群
     */
    public Operators selectBy(OperatorCriteria operatorCriteria, Orders orders) {

        BranchAtMomentCriteria branchAtMomentCriteria = new BranchAtMomentCriteria();
        branchAtMomentCriteria.getJaIdentifierCriteria().setEqualTo(operatorCriteria.getJaIdentifierCriteria().getEqualTo());

        // 店舗群検索
        // TODO: 店舗の取得は BranchAtMomentで取得
        // TODO: 暫定で店舗は仮に設定
//        BranchesAtMoment branchesAtMoment = branchAtMomentRepository.selectBy(branchAtMomentCriteria,
//            Orders.empty().addOrder("branchAttribute.BranchCode.value"));

        // オペレーター検索
        OperatorEntityCriteria entityCriteria = new OperatorEntityCriteria();
        entityCriteria.getOperatorIdCriteria().assignFrom(operatorCriteria.getOperatorIdCriteria());
        entityCriteria.getOperatorCodeCriteria().assignFrom(operatorCriteria.getOperatorCodeCriteria());
        entityCriteria.getOperatorNameCriteria().assignFrom(operatorCriteria.getOperatorNameCriteria());
        entityCriteria.getMailAddressCriteria().assignFrom(operatorCriteria.getMailAddressCriteria());
        entityCriteria.getValidThruStartDateCriteria().assignFrom(operatorCriteria.getValidThruStartDateCriteria());
        entityCriteria.getValidThruEndDateCriteria().assignFrom(operatorCriteria.getValidThruEndDateCriteria());
        entityCriteria.getIsDeviceAuthCriteria().assignFrom(operatorCriteria.getIsDeviceAuthCriteria());
        entityCriteria.getJaIdCriteria().setEqualTo(operatorCriteria.getJaIdentifierCriteria().getEqualTo());
        entityCriteria.getJaCodeCriteria().assignFrom(operatorCriteria.getJaCodeCriteria());
        entityCriteria.getBranchIdCriteria().assignFrom(operatorCriteria.getBranchIdCriteria());
        entityCriteria.getBranchCodeCriteria().assignFrom(operatorCriteria.getBranchCodeCriteria());
        entityCriteria.getAvailableStatusCriteria().assignFrom(operatorCriteria.getAvailableStatusCriteria());

        List<Operator> list = newArrayList();
        for (OperatorEntity entity : oeratorEntityDao.findBy(entityCriteria, orders)) {
            list.add(Operator.createFrom(
                entity.getOperatorId(),
                entity.getOperatorCode(),
                entity.getOperatorName(),
                entity.getMailAddress(),
                entity.getValidThruStartDate(),
                entity.getValidThruEndDate(),
                entity.getIsDeviceAuth(),
                entity.getJaId(),
                entity.getJaCode(),
                entity.getBranchId(),
                entity.getBranchCode(),
                AvailableStatus.codeOf(entity.getAvailableStatus()),
                entity.getRecordVersion(),
//                branchesAtMoment.getValue().stream().filter(b->b.getIdentifier().equals(entity.getBranchId())).findFirst().orElse(null)
                // TODO: 暫定で店舗は仮に設定 provisional
                createBranchAtMoment(entity)
            ));
        }

        return Operators.createFrom(list);
    }



    private static BranchAtMoment createBranchAtMoment(OperatorEntity entity) {
        JaAtMoment jaAtMoment = JaAtMoment.builder()
            .withIdentifier(entity.getJaId())
            .withJaAttribute(JaAttribute
                .builder()
                .withJaCode(JaCode.of(entity.getJaCode()))
                .withName("ＪＡ"+entity.getJaCode())
                .withFormalName("")
                .withAbbreviatedName("")
                .build())
            .build();
        return BranchAtMoment.builder()
            .withIdentifier(entity.getBranchId())
            .withJaAtMoment(jaAtMoment)
            .withBranchAttribute(BranchAttribute.builder()
                .withBranchType(BranchType.一般)
                .withBranchCode(BranchCode.of(entity.getBranchCode()))
                .withName(entity.getBranchCode()+"店舗")
                .build())
            .build();
    }
}

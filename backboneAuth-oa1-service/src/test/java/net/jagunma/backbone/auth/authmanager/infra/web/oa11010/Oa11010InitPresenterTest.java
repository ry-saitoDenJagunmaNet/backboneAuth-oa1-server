package net.jagunma.backbone.auth.authmanager.infra.web.oa11010;

import static net.jagunma.common.util.collect.Lists2.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import net.jagunma.backbone.auth.authmanager.infra.web.common.SelectOptionItemsSource;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010BizTranRoleVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010SubSystemRoleVo;
import net.jagunma.backbone.auth.authmanager.infra.web.oa11010.vo.Oa11010Vo;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole.BizTranRole;
import net.jagunma.backbone.auth.authmanager.model.domain.bizTranRole.BizTranRoles;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystem;
import net.jagunma.backbone.auth.authmanager.model.types.SubSystemRole;
import net.jagunma.common.tests.constants.TestSize;
import net.jagunma.common.values.model.branch.BranchAtMoment;
import net.jagunma.common.values.model.branch.BranchAttribute;
import net.jagunma.common.values.model.branch.BranchCode;
import net.jagunma.common.values.model.branch.BranchType;
import net.jagunma.common.values.model.branch.BranchesAtMoment;
import net.jagunma.common.values.model.ja.JaAtMoment;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class Oa11010InitPresenterTest {

    /**
     * {@link Oa11010InitPresenter}のテスト
     *  ●パターン
     *    通常
     *
     *  ●検証事項
     *  ・Voへのセット
     */
    @Test
    @Tag(TestSize.SMALL)
    void bindTo_test0() {

        // 実行既定値
        long jaId = 6;
        String jaCode = "006";
        String jaName = "JA前橋市";
        List<BranchAtMoment> branchList = newArrayList();
        branchList.add(BranchAtMoment.builder()
            .withIdentifier(1L).withJaAtMoment(new JaAtMoment()).withBranchAttribute(BranchAttribute.builder()
                .withBranchType(BranchType.一般).withBranchCode(BranchCode.of("001")).withName("店舗001").build())
            .build());
        branchList.add(BranchAtMoment.builder()
            .withIdentifier(2L).withJaAtMoment(new JaAtMoment()).withBranchAttribute(BranchAttribute.builder()
                .withBranchType(BranchType.一般).withBranchCode(BranchCode.of("002")).withName("店舗002").build())
            .build());
        Integer expirationSelect = 1;
        Integer subSystemRoleConditionsSelect = 1;
        Integer bizTranRoleConditionsSelect = 1;
        List<BizTranRole> bizTranRoleList = newArrayList();
        bizTranRoleList.add(BizTranRole.createFrom(1L, "KBXX001", "購買管理者", "KB", SubSystem.購買));
        bizTranRoleList.add(BizTranRole.createFrom(1L, "KBXX002", "購買XX担当", "KB", SubSystem.購買));

        // 実行値
        Oa11010Vo vo = new Oa11010Vo();
        Oa11010InitPresenter presenter = new Oa11010InitPresenter();
        presenter.setJaId(jaId);
        presenter.setJaCode(jaCode);
        presenter.setJaName(jaName);
        presenter.setBranchesAtMoment(BranchesAtMoment.of(branchList));
        presenter.setExpirationSelect(expirationSelect);
        presenter.setSubSystemRoleConditionsSelect(subSystemRoleConditionsSelect);
        presenter.setBizTranRoleConditionsSelect(bizTranRoleConditionsSelect);
        presenter.setBizTranRoles(BizTranRoles.createFrom(bizTranRoleList));

        // 期待値
        Oa11010Vo expectedVo = new Oa11010Vo();
        expectedVo.setJa(jaCode + " " + jaName);
        expectedVo.setJaId(jaId);
        expectedVo.setBranchItemsSource(SelectOptionItemsSource.createFrom(BranchesAtMoment.of(branchList)).getValue());
        expectedVo.setExpirationSelect(expirationSelect);
        expectedVo.setSubSystemRoleConditionsSelect(subSystemRoleConditionsSelect);
        List<Oa11010SubSystemRoleVo> subSystemRoleVoList = newArrayList();
        for(SubSystemRole subSystemRole : SubSystemRole.values()) {
            if (subSystemRole.getCode().length() == 0) { continue; }
            Oa11010SubSystemRoleVo subSystemRoleVo = new Oa11010SubSystemRoleVo();
            subSystemRoleVo.setSubSystemRoleCode(subSystemRole.getCode());
            subSystemRoleVo.setSubSystemRoleName(subSystemRole.getName());
            subSystemRoleVo.setExpirationSelect(0);
            subSystemRoleVoList.add(subSystemRoleVo);
        }
        expectedVo.setSubSystemRoleList(subSystemRoleVoList);
        expectedVo.setBizTranRoleConditionsSelect(bizTranRoleConditionsSelect);
        expectedVo.setBizTranRoleSubSystemList(SelectOptionItemsSource.createFrom(SubSystem.values()).getValue());
        List<Oa11010BizTranRoleVo> bizTranRoleVoList = newArrayList();
        for (BizTranRole bizTranRole : bizTranRoleList) {
            Oa11010BizTranRoleVo bizTranRoleVo = new Oa11010BizTranRoleVo();
            bizTranRoleVo.setBizTranRoleId(bizTranRole.getBizTranRoleId());
            bizTranRoleVo.setBizTranRoleCode(bizTranRole.getBizTranRoleCode());
            bizTranRoleVo.setBizTranRoleName(bizTranRole.getBizTranRoleName());
            bizTranRoleVo.setSubSystemCode(bizTranRole.getSubSystemCode());
            bizTranRoleVo.setExpirationSelect(0);
            bizTranRoleVoList.add(bizTranRoleVo);
        }
        expectedVo.setBizTranRoleList(bizTranRoleVoList);

        // 実行
        presenter.bindTo(vo);

        // 結果検証
        assertThat(vo).usingRecursiveComparison().isEqualTo(expectedVo);
    }
}
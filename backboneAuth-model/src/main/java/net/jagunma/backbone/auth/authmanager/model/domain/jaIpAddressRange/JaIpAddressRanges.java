package net.jagunma.backbone.auth.authmanager.model.domain.jaIpAddressRange;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.Collection;
import java.util.List;

/**
 * JA割当IPアドレス範囲群
 */
public class JaIpAddressRanges {

    private final List<JaIpAddressRange> list = newArrayList();

    // コンストラクタ
    JaIpAddressRanges(Collection<JaIpAddressRange> collection) {
        this.list.addAll(collection);
    }

    /**
     * JA割当IPアドレス範囲リストから作成します
     *
     * @param jaIpAddressRangeList JA割当IPアドレス範囲リスト
     * @return JA割当IPアドレス範囲群
     */
    public static JaIpAddressRanges createFrom(Collection<JaIpAddressRange> jaIpAddressRangeList) {
        return new JaIpAddressRanges(jaIpAddressRangeList);
    }

    /**
     * JA割当IPアドレス範囲リストを取得します
     *
     * @return JA割当IPアドレス範囲リスト
     */
    public List<JaIpAddressRange> getValues() {
        return list;
    }
}

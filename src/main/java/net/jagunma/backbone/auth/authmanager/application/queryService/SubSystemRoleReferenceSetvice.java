package net.jagunma.backbone.auth.authmanager.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import java.util.stream.Collectors;
import net.jagunma.backbone.auth.authmanager.application.model.types.SubSystemRole;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemReferenceDto;
import net.jagunma.backbone.auth.authmanager.application.queryService.dto.SubSystemRoleReferenceDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * サブシステムール参照サービス
 */
@Service
@Transactional
public class SubSystemRoleReferenceSetvice {

	/**
	 * サブシステムロールリストを取得します。
	 * @return サブシステムロールリスト
	 */
	public List<SubSystemRoleReferenceDto> getSubSystemRoleList() {
		List<SubSystemRoleReferenceDto> list = newArrayList();
		SubSystemReferenceService subSystemReferenceService = new SubSystemReferenceService();

		// サブシステムリスト取得
		List<SubSystemReferenceDto> subSystemReferenceDto = subSystemReferenceService.getSubSystemList();

		// ENUMのサブシステムロールリスト取得
		for(SubSystemRole sybsysremRole : SubSystemRole.values()) {
			SubSystemRoleReferenceDto subSystemRoleReferenceDto = new SubSystemRoleReferenceDto();
			subSystemRoleReferenceDto.setSubSystemRoleCode(sybsysremRole.name());
			subSystemRoleReferenceDto.setSubSystemRoleName(sybsysremRole.getSubSystemRoleName());
			subSystemRoleReferenceDto.setSubSystemReferenceDtoList(newArrayList()) ;
			for(String subsystemCode : sybsysremRole.getSubSystemCode()) {
				// 該当サブシステムを検索
				List<SubSystemReferenceDto> tempDtos = subSystemReferenceDto
					.stream().filter(s->s.getSubSystemCode().equals(subsystemCode)).collect(Collectors.toList());
				if (tempDtos.size() == 0) {
					subSystemRoleReferenceDto
						.getSubSystemReferenceDtoList().add(new SubSystemReferenceDto(subsystemCode,""));
				} else {
					subSystemRoleReferenceDto
						.getSubSystemReferenceDtoList().add(new SubSystemReferenceDto(subsystemCode,tempDtos.get(0).getSubSystemName()));
				}
			}
			list.add(subSystemRoleReferenceDto);
		}

		return list;
	}
}

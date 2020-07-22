package net.jagunma.backbone.auth.application.queryService;

import static net.jagunma.common.util.collect.Lists2.newArrayList;

import java.util.List;
import net.jagunma.backbone.auth.application.queryService.dto.SubSystemDto;
import net.jagunma.backbone.auth.application.queryService.dto.SubSystemRoleDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubSystemRoleReferenceSetvice {
	public List<SubSystemRoleDto> getSubSystemRoleList() {
		List<SubSystemRoleDto> entitys = newArrayList();
		SubSystemRoleDto entity = new SubSystemRoleDto();
		entity.setSubSystemRoleCode("JaAdmin");
		entity.setSubSystemRoleName("JA管理者");
		List<SubSystemDto> sslist = newArrayList();
		sslist.add(new SubSystemDto("KB", "購買・全般"));
		sslist.add(new SubSystemDto("YS", "販売・青果"));
		sslist.add(new SubSystemDto("YF", "販売・花卉"));
		sslist.add(new SubSystemDto("HK", "販売・米"));
		sslist.add(new SubSystemDto("HM", "販売・麦"));
		sslist.add(new SubSystemDto("AN", "販売・畜産"));
		entity.setSubSystemCodeList(sslist);
		entitys.add(entity);

		entity = new SubSystemRoleDto();
		entity.setSubSystemRoleCode("KbManager");
		entity.setSubSystemRoleName("業務統括者（購買）");
		sslist = newArrayList();
		sslist.add(new SubSystemDto("KB", "購買・全般"));
		entity.setSubSystemCodeList(sslist);
		entitys.add(entity);

		entity = new SubSystemRoleDto();
		entity.setSubSystemRoleCode("YsManager");
		entity.setSubSystemRoleName("業務統括者（販売・野菜）");
		sslist = newArrayList();
		sslist.add(new SubSystemDto("YS", "販売・青果"));
		entity.setSubSystemCodeList(sslist);
		entitys.add(entity);

		entity = new SubSystemRoleDto();
		entity.setSubSystemRoleCode("YfManager");
		entity.setSubSystemRoleName("業務統括者（販売・花卉）");
		sslist = newArrayList();
		sslist.add(new SubSystemDto("YF", "販売・花卉"));
		entity.setSubSystemCodeList(sslist);
		entitys.add(entity);

		entity = new SubSystemRoleDto();
		entity.setSubSystemRoleCode("HkManager");
		entity.setSubSystemRoleName("業務統括者（販売・米）");
		sslist = newArrayList();
		sslist.add(new SubSystemDto("HK", "販売・米"));
		entity.setSubSystemCodeList(sslist);
		entitys.add(entity);

		entity = new SubSystemRoleDto();
		entity.setSubSystemRoleCode("HmManager");
		entity.setSubSystemRoleName("業務統括者（販売・麦）");
		sslist = newArrayList();
		sslist.add(new SubSystemDto("HM", "販売・麦"));
		entity.setSubSystemCodeList(sslist);
		entitys.add(entity);

		entity = new SubSystemRoleDto();
		entity.setSubSystemRoleCode("AnManager");
		entity.setSubSystemRoleName("業務統括者（販売・畜産）");
		sslist = newArrayList();
		sslist.add(new SubSystemDto("AN", "販売・畜産"));
		entity.setSubSystemCodeList(sslist);
		entitys.add(entity);

		return entitys;
	}
}

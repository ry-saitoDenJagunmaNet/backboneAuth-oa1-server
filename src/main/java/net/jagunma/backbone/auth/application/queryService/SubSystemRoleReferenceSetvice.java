package net.jagunma.backbone.auth.application.queryService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubSystemRoleReferenceSetvice {
	public List<SubSystemRole> getSubSystemRoleList() {
		List<SubSystemRole> entitys = new ArrayList<SubSystemRole>();
		SubSystemRole entity = new SubSystemRole();
		entity.setSubSystemRoleCode("JaAdmin");
		entity.setSubSystemRoleName("JA管理者");
		Map<String, String> map = new HashMap<String, String>();
		map.put("KB", "購買・全般");
		map.put("YS", "販売・青果");
		map.put("YF", "販売・花卉");
		map.put("HK", "販売・米");
		map.put("HM", "販売・麦");
		map.put("AN", "販売・畜産");
		entity.setSubSystemCodeList(map);
		entitys.add(entity);

		entity = new SubSystemRole();
		entity.setSubSystemRoleCode("KbManager");
		entity.setSubSystemRoleName("業務統括者（購買）");
		entity.setSubSystemCodeList(new HashMap<String, String>(){{put("KB", "購買・全般");}});
		entitys.add(entity);

		entity = new SubSystemRole();
		entity.setSubSystemRoleCode("YsManager");
		entity.setSubSystemRoleName("業務統括者（販売・野菜）");
		entity.setSubSystemCodeList(new HashMap<String, String>(){{put("YF", "販売・花卉");}});
		entitys.add(entity);

		entity = new SubSystemRole();
		entity.setSubSystemRoleCode("YfManager");
		entity.setSubSystemRoleName("業務統括者（販売・花卉）");
		entity.setSubSystemCodeList(new HashMap<String, String>(){{put("YS", "販売・青果");}});
		entitys.add(entity);

		entity = new SubSystemRole();
		entity.setSubSystemRoleCode("HkManager");
		entity.setSubSystemRoleName("業務統括者（販売・米）");
		entity.setSubSystemCodeList(new HashMap<String, String>(){{put("HK", "販売・米");}});
		entitys.add(entity);

		entity = new SubSystemRole();
		entity.setSubSystemRoleCode("HmManager");
		entity.setSubSystemRoleName("業務統括者（販売・麦）");
		entity.setSubSystemCodeList(new HashMap<String, String>(){{put("HM", "販売・麦");}});
		entitys.add(entity);

		entity = new SubSystemRole();
		entity.setSubSystemRoleCode("AnManager");
		entity.setSubSystemRoleName("業務統括者（販売・畜産）");
		entity.setSubSystemCodeList(new HashMap<String, String>(){{put("AN", "販売・畜産");}});
		entitys.add(entity);

		return entitys;
	}
}

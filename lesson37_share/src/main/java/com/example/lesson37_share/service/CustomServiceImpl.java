package com.ssm.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ssm.dao.ICustomDao;
import com.ssm.entity.Custom;
import com.ssm.util.TextUtil;

@Service("customService")
public class CustomServiceImpl implements ICustomService {

	// @Autowired
	// @Qualifier("customDao")
	@Resource // 以名称自动装配，找不到名称则以类型自动装配
	private ICustomDao customDao;

	public void setCustomDao(ICustomDao customDao) {
		this.customDao = customDao;
	}

	@Override
	public Map<String, Object> queryCustoms(int page, int rows) {
		Map<String, Object> map = new HashMap<String, Object>();

		int total = customDao.queryCustomCount();
		map.put("total", total);

		int start = (page - 1) * rows;
		map.put("rows", customDao.queryCustoms(start, rows));
		return map;
	}

	@Override
	public Map<String, Object> addCustom(Custom custom) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (custom == null || (custom.getAge() == null && TextUtil.isEmpty(custom.getEmail())
				&& TextUtil.isEmpty(custom.getPhone()) && TextUtil.isEmpty(custom.getName()))) {
			map.put("message", "不能添加完全为空的用户");
			map.put("success", false);
			return map;
		}
		int id = customDao.addCustom(custom);
		if (id > 0) {
			map.put("message", "添加成功");
			map.put("success", true);
		} else {
			map.put("message", "添加失败");
			map.put("success", false);
		}
		return map;
	}

	@Override
	public Map<String, Object> delCustom(int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		int num = customDao.deleteCustom(id);
		if (num > 0) {
			map.put("message", "删除成功");
			map.put("success", true);
		} else {
			map.put("message", "删除失败");
			map.put("success", false);
		}
		return map;
	}

	@Override
	public Custom queryCustom(int id) {
		return customDao.queryCustom(id);
	}

	@Override
	public Map<String, Object> updateCustom(Custom custom) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (custom == null || (custom.getAge() == null && TextUtil.isEmpty(custom.getEmail())
				&& TextUtil.isEmpty(custom.getPhone()) && TextUtil.isEmpty(custom.getName()))) {
			map.put("message", "不能修改为完全为空");
			map.put("success", false);
			return map;
		}
		int num = customDao.updateCustom(custom);
		if (num > 0) {
			map.put("message", "修改成功");
			map.put("success", true);
		} else {
			map.put("message", "修改失败");
			map.put("success", false);
		}
		return map;
	}

}
